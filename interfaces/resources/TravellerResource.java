package ca.ulaval.glo4002.inn.interfaces.resources;

import ca.ulaval.glo4002.inn.application.TravellerService;
import ca.ulaval.glo4002.inn.domain.ExistenceNumber;
import ca.ulaval.glo4002.inn.interfaces.rest.assemblers.TravellerAssembler;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/travellers")
@Produces(MediaType.APPLICATION_JSON)
public class TravellerResource {
    private final TravellerService travellerService;

    @Inject
    public TravellerResource(TravellerService travellerService) {
        this.travellerService = travellerService;
    }

    @GET
    @Path("/{existenceNumber}")
    public Response findTraveller(@PathParam("existenceNumber") String existenceNumber) {
        return travellerService.findTraveller(new ExistenceNumber(existenceNumber))
                .map(traveller -> Response.ok(TravellerAssembler.toResponse(traveller)).build())
                .orElseGet(() -> Response.status(Response.Status.NOT_FOUND)
                        .entity(TravellerAssembler.toNotFoundResponse(existenceNumber))
                        .build());
    }
}
