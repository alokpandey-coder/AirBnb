package com.AirBnb.Service;

import com.AirBnb.Enums.Status;
import com.AirBnb.Model.Bookings;
import com.AirBnb.Model.Listing;
import com.AirBnb.Model.Users;
import com.AirBnb.Payload.BookingsDto;
import com.AirBnb.Repository.BookingsRepository;
import com.AirBnb.Repository.ListingRepository;
import com.AirBnb.Repository.UsersRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingService {

    private final BookingsRepository bookingsRepository;
    private final ModelMapper modelMapper;
    private final UsersRepository usersRepository;
    private final ListingRepository listingRepository;

    public BookingService(BookingsRepository bookingsRepository, ModelMapper modelMapper,
                          UsersRepository usersRepository,
                          ListingRepository listingRepository) {
        this.bookingsRepository = bookingsRepository;
        this.modelMapper = modelMapper;
        this.usersRepository = usersRepository;
        this.listingRepository = listingRepository;
    }

   BookingsDto mapToDto(Bookings bookings){
        return modelMapper.map(bookings,BookingsDto.class);
    }

   Bookings mapToEntity(BookingsDto dto){
        return modelMapper.map(dto,Bookings.class);
    }

    public BookingsDto createBooking(BookingsDto dto, Long userId, Long listingId) {

        Users user = usersRepository.findById(userId).orElseThrow(()-> new RuntimeException("User are not Present in Records"));
        Listing listing = listingRepository.findById(listingId).orElseThrow(()-> new RuntimeException("Listing Id are not present in Record"));

        Bookings bookings =mapToEntity(dto);

        bookings.setCheckIn(LocalDate.now());
        bookings.setCheckOut(LocalDate.now());
        bookings.setUsers(user);
        bookings.setListing(listing);
        bookings.setStatus(String.valueOf(Status.CONFIRMED));

        Bookings savedBookings = bookingsRepository.save(bookings);
        return mapToDto(savedBookings);
    }

    public String deleteBooking(Long userId, Long bookingId) {

        Users user = usersRepository.findById(userId).orElseThrow(()-> new RuntimeException("User are not found in Records"));
        Bookings bookings= bookingsRepository.findById(bookingId).orElseThrow(()-> new RuntimeException("Bookings are not Found in Records"));

        bookingsRepository.deleteById(bookingId);
        return "Booking Deleted from Records";
    }

    public BookingsDto viewByBookingId(Long bookingId) {

        Bookings bookings= bookingsRepository.findById(bookingId).orElseThrow(()-> new RuntimeException("Bookings are not Found in Records"));

        return mapToDto(bookings);
    }


    public List<BookingsDto> viewAllBookings() {

        List<Bookings> list = bookingsRepository.findAll();
        List<BookingsDto> listDtos = list.stream().map(e->mapToDto(e)).collect(Collectors.toList());
        return listDtos;
    }

    public BookingsDto updateBookings(BookingsDto dto, Long bookingId) {

        Bookings booking=bookingsRepository.findById(bookingId).orElseThrow(()->new RuntimeException("Invalid BookingId!!!"));

        booking.setCheckIn(dto.getCheckIn());
        booking.setCheckOut(dto.getCheckOut());
        booking.setTotalPrice(dto.getTotalPrice());
        booking.setStatus(dto.getStatus());

        Bookings bookings = bookingsRepository.save(booking);
        return mapToDto(bookings);
    }
}
