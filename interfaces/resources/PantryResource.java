package ca.ulaval.glo4002.inn.interfaces.resources;

import ca.ulaval.glo4002.inn.application.PantryService;
import ca.ulaval.glo4002.inn.interfaces.rest.assemblers.PantryAssembler;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/pantry")
@Produces(MediaType.APPLICATION_JSON)
public class PantryResource {
    private final PantryService pantryService;

    @Inject
    public PantryResource(PantryService pantryService) {
        this.pantryService = pantryService;
    }

    @GET
    public Response getPantry() {
        return Response.ok(PantryAssembler.toResponse(pantryService.findReserves())).build();
    }
}
