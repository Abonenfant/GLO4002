package ca.ulaval.glo4002.inn.interfaces.resources;

import java.util.List;

import ca.ulaval.glo4002.inn.application.SubscriptionService;
import ca.ulaval.glo4002.inn.interfaces.rest.assemblers.SubscriptionAssembler;
import ca.ulaval.glo4002.inn.interfaces.rest.dto.requests.SubscriptionRequest;
import ca.ulaval.glo4002.inn.interfaces.rest.dto.responses.SubscriptionResponse;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/subscriptions")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SubscriptionResource {
    private final SubscriptionService subscriptionService;

    @Inject
    public SubscriptionResource(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @POST
    public Response subscribe(SubscriptionRequest subscriptionRequest) {
        subscriptionService.subscribe(SubscriptionAssembler.toSubscriptionTerms(subscriptionRequest));
        return Response.accepted().build();
    }

    @DELETE
    @Path("/{subscriptionName}")
    public Response unsubscribe(@PathParam("subscriptionName") String subscriptionName) {
        subscriptionService.unsubscribe(subscriptionName);
        return Response.accepted().build();
    }

    @GET
    public Response getSubscriptions() {
        int currentNight = subscriptionService.getCurrentNight();
        List<SubscriptionResponse> responses = subscriptionService.getSubscriptions().stream()
                .map(subscription -> SubscriptionAssembler.toResponse(subscription, currentNight))
                .toList();
        return Response.ok(responses).build();
    }
}
