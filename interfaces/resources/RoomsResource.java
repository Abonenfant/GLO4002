package ca.ulaval.glo4002.inn.interfaces.resources;

import java.util.List;

import ca.ulaval.glo4002.inn.application.RoomService;
import ca.ulaval.glo4002.inn.interfaces.rest.assemblers.RoomAssembler;
import ca.ulaval.glo4002.inn.interfaces.rest.assemblers.RoomRejectionAssembler;
import ca.ulaval.glo4002.inn.interfaces.rest.dto.requests.ReconfigureRoomRequest;
import ca.ulaval.glo4002.inn.interfaces.rest.dto.requests.SetupRoomRequest;
import ca.ulaval.glo4002.inn.interfaces.rest.dto.responses.RoomRejectionResponse;
import ca.ulaval.glo4002.inn.interfaces.rest.dto.responses.RoomResponse;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/rooms")
public class RoomsResource {

    private final RoomService service;

    @Inject
    public RoomsResource(RoomService service) {
        this.service = service;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response setupRoom(SetupRoomRequest request) {
        service.requestRoomSetup(
                request.roomNumber(),
                request.atmosphere(),
                request.gravity(),
                request.temperature(),
                request.capacity()
        );
        return Response.accepted().build();
    }

    @PUT
    @Path("/{roomNumber}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response reconfigureRoom(
            @PathParam("roomNumber") int roomNumber,
            ReconfigureRoomRequest request) {
        service.requestRoomReconfiguration(
                roomNumber,
                request.atmosphere(),
                request.gravity(),
                request.temperature(),
                request.capacity()
        );

        return Response.accepted().build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllRooms() {
        List<RoomResponse> rooms = service.findAllRooms().stream()
                .map(RoomAssembler::toResponse)
                .toList();
        return Response.ok(rooms).build();
    }

    @GET
    @Path("/rejections")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllRejections() {
        List<RoomRejectionResponse> rejections = service.findAllRejections().stream()
                .map(RoomRejectionAssembler::toResponse)
                .toList();
        return Response.ok(rejections).build();
    }
}
