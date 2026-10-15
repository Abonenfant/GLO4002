package ca.ulaval.glo4002.inn.interfaces.resources;

import java.util.List;

import ca.ulaval.glo4002.inn.application.OrderService;
import ca.ulaval.glo4002.inn.interfaces.rest.assemblers.OrderAssembler;
import ca.ulaval.glo4002.inn.interfaces.rest.dto.requests.OrderRequest;
import ca.ulaval.glo4002.inn.interfaces.rest.dto.responses.OrderResponse;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/orders")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class OrderResource {
    private final OrderService orderService;

    @Inject
    public OrderResource(OrderService orderService) {
        this.orderService = orderService;
    }

    @POST
    public Response placeOrder(OrderRequest orderRequest) {
        orderService.placeOrder(orderRequest.foodType(), orderRequest.quantity(), orderRequest.deliveryDelay());
        return Response.accepted().build();
    }

    @GET
    public List<OrderResponse> findOrdersInTransit() {
        return orderService.findOrdersInTransit().stream()
                .map(OrderAssembler::toResponse)
                .toList();
    }
}
