package org.ncu.movieappcollege.Controller;

import org.ncu.movieappcollege.Model.Booking;
import org.ncu.movieappcollege.Repository.BookingRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking")
public class BookingController {

    BookingRepository bookingRepository;

    public BookingController(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }


    @GetMapping
    public List<Booking> getBookings() {
        return bookingRepository.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity<Booking> saveBooking(Booking booking) {

        if(bookingRepository.findById(booking.getBookingId()).isPresent()) {
            return ResponseEntity.badRequest().body(null);
        }
        else{
            bookingRepository.save(booking);
            return ResponseEntity.ok(booking);
        }

    }

    @PostMapping("/bulksave")
    public ResponseEntity<Booking> saveAllBookings(List<Booking> bookings) {
        bookingRepository.saveAll(bookings);
        return ResponseEntity.ok(bookings.getFirst());
    }

    @DeleteMapping("/remove")
    public ResponseEntity<Booking> deleteBooking(Booking booking) {
        bookingRepository.delete(booking);
        return ResponseEntity.ok(booking);
    }

    @PutMapping("update")
    public ResponseEntity<Booking> updateBooking(Booking booking) {
        if(bookingRepository.findById(booking.getBookingId()).isPresent()) {
            bookingRepository.save(booking);
            return ResponseEntity.ok(booking);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
