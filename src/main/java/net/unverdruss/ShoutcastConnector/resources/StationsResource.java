package net.unverdruss.ShoutcastConnector.resources;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Optional;
import net.unverdruss.ShoutcastConnector.ShoutcastConnectorConfiguration;
import net.unverdruss.ShoutcastConnector.models.ShoutcastStation;
import net.unverdruss.ShoutcastConnector.models.Station;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.eclipse.jetty.util.StringUtil;

import javax.validation.constraints.Min;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Path("/stations")
@Produces(MediaType.APPLICATION_JSON)
public class StationsResource {
    private final static String URL_TOP = "/Home/Top";
    private final static String URL_GET_STREAM = "/Player/GetStreamUrl";
    private final static String UNAVAILABLE = "unavailable";
    private final static ConcurrentHashMap<Long, String> SIMPLE_CACHE = new ConcurrentHashMap<>();

    private final String shoutcastServer;
    private final String shoutcastPort;

    public StationsResource(ShoutcastConnectorConfiguration configuration) {
        shoutcastServer = configuration.getShoutcastServer();
        shoutcastPort = configuration.getShoutcastServerPort();
    }

    @GET
    @Timed
    public List<Station> getStations(@QueryParam("count") Optional<Integer> count) {
        List<ShoutcastStation> shoutcastStations = getShoutcastTopStations(count.or(10));
        return shoutcastStations.stream()
                .parallel()
                .map(s -> new Station(s.getId(), getShoutcastStationUrl(s.getId())))
                .collect(Collectors.toList());
    }

    @GET
    @Timed
    @Path("{id}/tune")
    public Response tuneIn(@PathParam("id") @Min(0) long id) {
        String target = getShoutcastStationUrl(id);
        if (UNAVAILABLE.equals(target)) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Stream URL is unavailable for ID: " + id)
                    .type(MediaType.TEXT_HTML)
                    .build();
        } else {
            URI uri = URI.create(target);
            return Response.seeOther(uri).build();
        }
    }

    @GET
    @Timed
    @Path("{id}/stream")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public StreamingOutput stream(@PathParam("id") long id) {
        String target = getShoutcastStationUrl(id);
        if (UNAVAILABLE.equals(target)) {
            return null;
        } else {
            return new StreamingOutput() {
                @Override
                public void write(OutputStream outputStream) throws IOException, WebApplicationException {
                    URL url = new URL(target);
                    Socket socket = new Socket();
                    String host = url.getHost();
                    int port = url.getPort();
                    PrintWriter s_out;
                    InputStream s_in;

                    try {
                        socket.connect(new InetSocketAddress(host, port));
                        s_out = new PrintWriter(socket.getOutputStream(), true);
                        s_in = socket.getInputStream();
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                        return;
                    }

                    s_out.println("GET / HTTP/1.1\r\n\r\n");

                    byte[] buffer = new byte[1024*1024];
                    int bytesRead;

                    try {
                        while ((bytesRead = s_in.read(buffer)) != -1) {
                            outputStream.write(buffer, 0, bytesRead);
                            outputStream.flush();
                        }
                    } catch (IOException e) {
                        System.out.print("Client disconnected");
                    }
                }
            };
        }
    }

    private List<ShoutcastStation> getShoutcastTopStations(int count) {
        List<ShoutcastStation> shoutcastStations = new ArrayList<>();

        try {
            String content = sendRequest(buildShoutcastUrl(URL_TOP), new ArrayList<>());
            ObjectMapper mapper = new ObjectMapper();
            shoutcastStations = mapper.readValue(content, new TypeReference<List<ShoutcastStation>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return shoutcastStations.subList(0, count);
    }

    public String getShoutcastStationUrl(long stationId) {
        String url = UNAVAILABLE;
        if (SIMPLE_CACHE.containsKey(stationId)) {
            url = SIMPLE_CACHE.get(stationId);
        } else {
            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("station", String.valueOf(stationId)));

            try {
                String content = sendRequest(buildShoutcastUrl(URL_GET_STREAM), params);
                ObjectMapper mapper = new ObjectMapper();
                String returnedUrl = mapper.readValue(content, new TypeReference<String>(){});
                if (StringUtil.isNotBlank(returnedUrl)) {
                    url = returnedUrl;
                    SIMPLE_CACHE.putIfAbsent(stationId, url);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return url;
    }

    private String buildShoutcastUrl(String url) {
        return shoutcastServer + ":" + shoutcastPort + url;
    }

    private String sendRequest(String targetUrl, List<NameValuePair> params) throws IOException {
        HttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(targetUrl);
        post.addHeader("Accept", "*/*");
        post.addHeader("Accept-Encoding", "gzip, deflate");
        post.addHeader("Accept-Language", "en-US");
        post.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

        HttpResponse response = client.execute(post);
        HttpEntity entity = response.getEntity();

        return EntityUtils.toString(entity);
    }
}
