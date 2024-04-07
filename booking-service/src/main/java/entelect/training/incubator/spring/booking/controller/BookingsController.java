package entelect.training.incubator.spring.booking.controller;

import entelect.training.incubator.spring.booking.model.Booking;
import entelect.training.incubator.spring.booking.model.BookingSearchRequest;
import entelect.training.incubator.spring.booking.service.BookingsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("bookings")
public class BookingsController {
    private final Logger LOGGER = LoggerFactory.getLogger(BookingsController.class);

    private final BookingsService bookingsService;

    public BookingsController(BookingsService bookingsService) {
        this.bookingsService = bookingsService;
    }

    @PostMapping
    public ResponseEntity<?> createBooking(@RequestBody Booking booking) {
        LOGGER.info("Processing booking creation request for booking={}", booking);
        final Booking savedBooking = bookingsService.createBooking(booking);
        LOGGER.trace("Booking created");
        return new ResponseEntity<>(savedBooking, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBooking(@PathVariable Integer id) {
        LOGGER.info("Fetching booking with id={}", id);
        final Booking foundBooking = bookingsService.getBooking(id);
        if (foundBooking != null) {
            LOGGER.trace("Booking found");
            return new ResponseEntity<>(foundBooking, HttpStatus.OK);
        }
        LOGGER.trace("Booking not found");
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/search")
    public ResponseEntity<?> searchBookingsByCustomer(@RequestBody BookingSearchRequest bookingSearchRequest) {
        if (bookingSearchRequest.getCustomerId() == null && bookingSearchRequest.getReferenceNumber() == null) {
           return new ResponseEntity<>("At least 1 of 'customerId' or 'referenceNumber' required.", HttpStatus.BAD_REQUEST);
        }
        LOGGER.info("Searching for all bookings with search request={}", bookingSearchRequest);
        final List<Booking> customerBookings = bookingsService.searchBookings(bookingSearchRequest);
        LOGGER.trace("Bookings found");
        return new ResponseEntity<>(customerBookings, HttpStatus.OK);
    }

}
