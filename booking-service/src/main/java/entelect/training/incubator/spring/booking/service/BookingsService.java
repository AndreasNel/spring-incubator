package entelect.training.incubator.spring.booking.service;

import entelect.training.incubator.spring.booking.client.loyalty.LoyaltyClient;
import entelect.training.incubator.spring.booking.client.loyalty.generated.CaptureRewardsResponse;
import entelect.training.incubator.spring.booking.model.Booking;
import entelect.training.incubator.spring.booking.model.BookingMessage;
import entelect.training.incubator.spring.booking.model.BookingSearchRequest;
import entelect.training.incubator.spring.booking.model.Customer;
import entelect.training.incubator.spring.booking.model.Flight;
import entelect.training.incubator.spring.booking.repository.BookingRepository;
import entelect.training.incubator.spring.booking.repository.CustomerRepository;
import entelect.training.incubator.spring.booking.repository.FlightRepository;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class BookingsService {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(BookingsService.class);

    private final BookingRepository bookingRepository;
    private final FlightRepository flightRepository;
    private final CustomerRepository customerRepository;
    private final LoyaltyClient loyaltyClient;
    private final MessagingService messagingService;

    public BookingsService(BookingRepository bookingRepository, FlightRepository flightRepository, CustomerRepository customerRepository, LoyaltyClient loyaltyClient, MessagingService messagingService) {
        this.bookingRepository = bookingRepository;
        this.flightRepository = flightRepository;
        this.customerRepository = customerRepository;
        this.loyaltyClient = loyaltyClient;
        this.messagingService = messagingService;
    }

    public Booking createBooking(Booking booking) {
        Customer customer = customerRepository.getCustomer(booking.getCustomerId());
        Flight flight = flightRepository.getFlight(booking.getFlightId());
        booking.setReferenceNumber((int) (Math.random() * (100 - 1) + 1));
        Booking response = bookingRepository.save(booking);
        captureLoyaltyRewards(booking, customer, flight);
        sendConfirmationMessage(booking, customer, flight);
        return response;
    }

    private void sendConfirmationMessage(Booking booking, Customer customer, Flight flight) {
        try {
            BookingMessage bookingMessage = new BookingMessage();
            bookingMessage.setPhoneNumber(customer.getPhoneNumber());
            bookingMessage.setMessage("Molo Air: Confirming flight %s booked for %s %s on %s.".formatted(flight.getFlightNumber(), customer.getFirstName(), customer.getLastName(), flight.getDepartureTime()));
            messagingService.sendMessage(bookingMessage);
            LOGGER.info("Message request sent");
        } catch (Exception e) {
            LOGGER.error("Unable to send confirmation message for booking={}", booking, e);
        }
    }

    private void captureLoyaltyRewards(Booking booking, Customer customer, Flight flight) {
        try {
            CaptureRewardsResponse rewardsResponse = loyaltyClient.captureRewards(customer.getPassportNumber(), flight.getSeatCost());
            LOGGER.info("Capture rewards response: {}", rewardsResponse.getBalance());
        } catch (Exception e) {
            LOGGER.error("Unable to capture rewards for booking={}", booking, e);
        }
    }

    public Booking getBooking(Integer id) {
        return bookingRepository.findById(id).orElse(null);
    }

    public List<Booking> searchBookings(BookingSearchRequest bookingSearchRequest) {
        Iterable<Booking> allBookings = bookingRepository.findAll();
        List<Booking> bookings = new ArrayList<>();
        for (Booking booking : allBookings) {
            if (Objects.equals(booking.getCustomerId(), bookingSearchRequest.getCustomerId()) || Objects.equals(booking.getReferenceNumber(), bookingSearchRequest.getReferenceNumber())) {
                bookings.add(booking);
            }
        }
        return bookings;
    }

}
