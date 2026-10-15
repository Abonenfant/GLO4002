package ca.ulaval.glo4002.inn.interfaces.resources;

import ca.ulaval.glo4002.inn.application.StayService;
import ca.ulaval.glo4002.inn.domain.ExistenceNumber;
import ca.ulaval.glo4002.inn.interfaces.rest.assemblers.StayAssembler;
import ca.ulaval.glo4002.inn.interfaces.rest.dto.requests.StayRequest;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/stays")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StayResource {
    private final StayService stayService;

    @Inject
    public StayResource(StayService stayService) {
        this.stayService = stayService;
    }

    @POST
    public Response bookStay(StayRequest request) {
        stayService.bookStay(
                new ExistenceNumber(request.existenceNumber()),
                StayAssembler.toTravellerProfile(request),
                request.stayPackage(),
                request.nights()
        );
        return Response.accepted().build();
    }
}
