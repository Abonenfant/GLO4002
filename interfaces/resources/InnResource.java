package ca.ulaval.glo4002.inn.interfaces.resources;

import ca.ulaval.glo4002.inn.domain.Inn;
import ca.ulaval.glo4002.inn.interfaces.rest.dto.responses.NightNumberResponse;
import ca.ulaval.glo4002.inn.interfaces.rest.mappers.InnMapper;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class InnResource {
    private final Inn inn;
    private final InnMapper mapper;

    @Inject
    public InnResource(Inn inn, InnMapper mapper) {
        this.inn = inn;
        this.mapper = mapper;
    }

    @Path("/inn")
    @GET
    public Response getInnInfo() {
        return Response.ok().entity(new NightNumberResponse(inn.getCurrentNight())).build();
    }

    @POST
    @Path("/nights")
    public Response passNight() {
        inn.passNight();
        return Response.ok().entity(new NightNumberResponse(inn.getCurrentNight())).build();
    }

}
