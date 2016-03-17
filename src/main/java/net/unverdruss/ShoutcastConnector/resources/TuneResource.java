package net.unverdruss.ShoutcastConnector.resources;

import com.codahale.metrics.annotation.Timed;

import javax.validation.constraints.Min;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/tune")
@Produces(MediaType.APPLICATION_JSON)
public class TuneResource {
    private final StationsResource stationsResource;
    public TuneResource(StationsResource stationsResource) {
        this.stationsResource = stationsResource;
    }

    @GET
    @Timed
    public Response tuneIn(@QueryParam("station") @Min(0) long stationId) {
        return stationsResource.tuneIn(stationId);
    }
}
