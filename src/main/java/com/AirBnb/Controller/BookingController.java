package com.AirBnb.Controller;

import com.AirBnb.Payload.BookingsDto;
import com.AirBnb.Service.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    //api/bookings/addBooking/user/{userId}/listing{listingId}

    @PostMapping("/addBooking/user/{userId}/listing/{listingId}")
    public ResponseEntity<BookingsDto> saveBookingHandler(@RequestBody BookingsDto dto,
                                                          @PathVariable("userId") Long userId,
                                                          @PathVariable("listingId") Long listingId){
        return new ResponseEntity<BookingsDto>(bookingService.createBooking(dto,userId,listingId), HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteBooking/userid/{userid}/bookingId/{bookingId}")
    public ResponseEntity<String> deleteBooking(@PathVariable("userId")Long userId,
                                                @PathVariable("bookingId")Long bookingId){
        return new ResponseEntity<String>(bookingService.deleteBooking(userId,bookingId),HttpStatus.OK);
    }

    @GetMapping("/getByBookingId/bookingId/{bookingId}")
    public ResponseEntity<BookingsDto> viewByBookingId(@PathVariable("bookingId")Long bookingId){
        return new ResponseEntity<BookingsDto>(bookingService.viewByBookingId(bookingId),HttpStatus.OK);
    }

    @GetMapping("/getAllBookings")
    public ResponseEntity<List<BookingsDto>> viewAllBBooking(){
        return new ResponseEntity<List<BookingsDto>>( bookingService.viewAllBookings(),HttpStatus.OK);
    }

    @PutMapping("/update/bookingId/{bookingId}")
    public ResponseEntity<BookingsDto> updateBookings(@RequestBody BookingsDto dto, @PathVariable("bookingId") Long bookingId){
        return new ResponseEntity<BookingsDto>(bookingService.updateBookings(dto,bookingId),HttpStatus.OK);
    }
}
