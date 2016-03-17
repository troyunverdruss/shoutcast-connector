package net.unverdruss.ShoutcastConnector.resources;

import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
@Produces(MediaType.TEXT_HTML)
public class RootResource {
    public final static String ROOT = "Shoutcast Connector API: " +
            "<ul>" +
            "<li>/stations (returns 10 stations)</li>" +
            "<li>/stations?count={count}</li>" +
            "<li>/stations/{id}/tune</li>" +
            "<li>/stations/{id}/stream</li>" +
            "<li>/tune?station={id}</li>" +
            "</ul>";

    @GET
    @Timed
    public Response root() {
        return Response.ok(ROOT).build();
    }
}
