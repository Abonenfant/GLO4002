package ca.ulaval.glo4002.inn.interfaces.configuration;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

import ca.ulaval.glo4002.inn.application.OrderService;
import ca.ulaval.glo4002.inn.application.PantryService;
import ca.ulaval.glo4002.inn.application.RoomService;
import ca.ulaval.glo4002.inn.application.StayService;
import ca.ulaval.glo4002.inn.application.SubscriptionService;
import ca.ulaval.glo4002.inn.application.TravellerService;
import ca.ulaval.glo4002.inn.domain.Inn;
import ca.ulaval.glo4002.inn.domain.Pantry;
import ca.ulaval.glo4002.inn.domain.food.FoodFactory;
import ca.ulaval.glo4002.inn.domain.order.OrderFactory;
import ca.ulaval.glo4002.inn.domain.order.OrderRepository;
import ca.ulaval.glo4002.inn.domain.room.RoomAssignmentPolicy;
import ca.ulaval.glo4002.inn.domain.room.RoomDirectory;
import ca.ulaval.glo4002.inn.domain.room.RoomRejectionRepository;
import ca.ulaval.glo4002.inn.domain.room.RoomRepository;
import ca.ulaval.glo4002.inn.domain.stay.StayFactory;
import ca.ulaval.glo4002.inn.domain.stay.StayLifecycle;
import ca.ulaval.glo4002.inn.domain.stay.TravellerRepository;
import ca.ulaval.glo4002.inn.domain.subscription.SubscriptionFactory;
import ca.ulaval.glo4002.inn.domain.subscription.SubscriptionRepository;
import ca.ulaval.glo4002.inn.infrastructure.persistence.InMemoryOrderRepository;
import ca.ulaval.glo4002.inn.infrastructure.persistence.InMemoryRoomRejectionRepository;
import ca.ulaval.glo4002.inn.infrastructure.persistence.InMemoryRoomRepository;
import ca.ulaval.glo4002.inn.infrastructure.persistence.InMemorySubscriptionRepository;
import ca.ulaval.glo4002.inn.infrastructure.persistence.InMemoryTravellerRepository;
import ca.ulaval.glo4002.inn.interfaces.rest.mappers.InnMapper;

/*
 * Composition root: every shared collaborator is instantiated exactly once here and bound as an
 * instance, so all services and per-request resources work on the same inn and repositories.
 */
public class ApplicationBinder extends AbstractBinder {

    @Override
    protected void configure() {
        Pantry pantry = new Pantry(new FoodFactory());

        OrderRepository orderRepository = new InMemoryOrderRepository();
        SubscriptionRepository subscriptionRepository = new InMemorySubscriptionRepository();
        TravellerRepository travellerRepository = new InMemoryTravellerRepository();
        RoomRepository roomRepository = new InMemoryRoomRepository();
        RoomRejectionRepository rejectionRepository = new InMemoryRoomRejectionRepository();
        RoomDirectory roomDirectory = new RoomDirectory(roomRepository);
        RoomAssignmentPolicy roomAssignmentPolicy = new RoomAssignmentPolicy();
        OrderFactory orderFactory = new OrderFactory();
        SubscriptionFactory subscriptionFactory = new SubscriptionFactory();
        StayFactory stayFactory = new StayFactory();

        StayLifecycle stayLifecycle = new StayLifecycle(travellerRepository, roomDirectory);

        Inn inn = new Inn(orderRepository, subscriptionRepository, pantry, stayLifecycle);

        bind(pantry).to(Pantry.class);
        bind(orderRepository).to(OrderRepository.class);
        bind(subscriptionRepository).to(SubscriptionRepository.class);
        bind(travellerRepository).to(TravellerRepository.class);
        bind(roomRepository).to(RoomRepository.class);
        bind(rejectionRepository).to(RoomRejectionRepository.class);
        bind(roomDirectory).to(RoomDirectory.class);
        bind(roomAssignmentPolicy).to(RoomAssignmentPolicy.class);
        bind(orderFactory).to(OrderFactory.class);
        bind(subscriptionFactory).to(SubscriptionFactory.class);
        bind(stayFactory).to(StayFactory.class);
        bind(inn).to(Inn.class);
        bind(new InnMapper()).to(InnMapper.class);

        bind(new PantryService(pantry)).to(PantryService.class);
        bind(new OrderService(inn, orderRepository, orderFactory)).to(OrderService.class);
        bind(new SubscriptionService(inn, subscriptionRepository, subscriptionFactory)).to(SubscriptionService.class);
        bind(new StayService(inn, travellerRepository, stayFactory, roomAssignmentPolicy, roomDirectory))
                .to(StayService.class);
        bind(new RoomService(inn, roomRepository, rejectionRepository))
                .to(RoomService.class);
        bind(new TravellerService(travellerRepository)).to(TravellerService.class);
    }
}
