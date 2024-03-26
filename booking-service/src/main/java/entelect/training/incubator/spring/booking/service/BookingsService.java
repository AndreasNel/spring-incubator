package entelect.training.incubator.spring.booking.service;

import entelect.training.incubator.spring.booking.model.Booking;
import entelect.training.incubator.spring.booking.model.BookingSearchRequest;
import entelect.training.incubator.spring.booking.model.Customer;
import entelect.training.incubator.spring.booking.model.Flight;
import entelect.training.incubator.spring.booking.repository.BookingRepository;
import entelect.training.incubator.spring.booking.repository.CustomerRepository;
import entelect.training.incubator.spring.booking.repository.FlightRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class BookingsService {

    private final BookingRepository bookingRepository;
    private final FlightRepository flightRepository;
    private final CustomerRepository customerRepository;

    public BookingsService(BookingRepository bookingRepository, FlightRepository flightRepository, CustomerRepository customerRepository) {
        this.bookingRepository = bookingRepository;
        this.flightRepository = flightRepository;
        this.customerRepository = customerRepository;
    }

    public Booking createBooking(Booking booking) {
        Customer customer = customerRepository.getCustomer(booking.getCustomerId());
        Flight flight = flightRepository.getFlight(booking.getFlightId());
        booking.setReferenceNumber(1);
        return bookingRepository.save(booking);
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
