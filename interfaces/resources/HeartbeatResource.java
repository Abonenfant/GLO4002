package ca.ulaval.glo4002.inn.interfaces.resources;

import ca.ulaval.glo4002.inn.interfaces.rest.dto.responses.HeartbeatResponse;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/heartbeat")
@Produces(MediaType.APPLICATION_JSON)
public class HeartbeatResource {

    @GET
    public HeartbeatResponse heartbeat() {
        return new HeartbeatResponse("ok");
    }
}
