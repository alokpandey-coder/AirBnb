package com.AirBnb.Service;

import com.AirBnb.Model.Listing;
import com.AirBnb.Model.Location;
import com.AirBnb.Model.Users;
import com.AirBnb.Payload.ListingDto;
import com.AirBnb.Payload.LocationDto;
import com.AirBnb.Payload.UserDto;
import com.AirBnb.Repository.ListingRepository;
import com.AirBnb.Repository.LocationRepository;
import com.AirBnb.Repository.UsersRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListingService {

    private final ListingRepository listingRepository;
    private final ModelMapper modelMapper;
    private final UsersRepository usersRepository;
    private final LocationRepository locationRepository;

    public ListingService(ListingRepository listingRepository, ModelMapper modelMapper,
                          UsersRepository usersRepository,
                          LocationRepository locationRepository) {
        this.listingRepository = listingRepository;
        this.modelMapper = modelMapper;
        this.usersRepository = usersRepository;
        this.locationRepository = locationRepository;
    }

   ListingDto mapToDto(Listing listing){
        return modelMapper.map(listing,ListingDto.class);
    }

   Listing mapToEntity(ListingDto dto){
        return modelMapper.map(dto,Listing.class);
    }


    public ListingDto addNewListing(Long userId, Long locationId, ListingDto dto) {

        Users user = usersRepository.findById(userId).orElseThrow(()->new RuntimeException("User not found"));
        Location location = locationRepository.findById(locationId).orElseThrow(()->new RuntimeException("Location not found"));

        Listing listing = mapToEntity(dto);
        listing.setUser(user);
        listing.setLocation(location);
        Listing savedListing =listingRepository.save(listing);

        ListingDto savedDto = mapToDto(savedListing);

        dto.setUserId(user.getUserId());
        dto.setLocationId(location.getLocationId());

        return savedDto;
    }

    public String deleteListing(Long listingId, Long userId) {
        Users id = usersRepository.findById(userId).orElseThrow(()->new RuntimeException("User not found"));
        Listing listing = listingRepository.findById(listingId).orElseThrow(()->new RuntimeException("Listing not found"));

        listing.setUser(null);
        listing.setListingId(null);

        listingRepository.deleteById(listingId);
        return "Listing successFully deleted...";
    }


    public ListingDto updateListing(ListingDto dto, Long listingId) {
        Listing listing = listingRepository.findById(listingId).orElseThrow(()->new RuntimeException("Listing not found"));

        listing.setTitle(dto.getTitle());
        listing.setDescription(dto.getDescription());
        listing.setPrice(dto.getPrice());
        listing.setGuestLimits(dto.getGuestLimits());

       Listing listingRecord = listingRepository.save(listing);
       return mapToDto(listingRecord);
    }

    public ListingDto getaAllListingByListingId(Long listingId) {

        Listing listing = listingRepository.findById(listingId).orElseThrow(()->new RuntimeException("Listing"));

        return mapToDto(listing);
    }

    public List<ListingDto> getListingByUserId(Long userId) {

        Users user = usersRepository.findById(userId).orElseThrow(()->new RuntimeException("User not found"));

        List<Listing> listing  = listingRepository.findByUser(user);

        return listing.stream().map(e->mapToDto(e)).collect(Collectors.toList());
    }

    public List<ListingDto> getAllListing() {

        List<Listing> listing = listingRepository.findAll();
        return listing.stream().map(e->mapToDto(e)).collect(Collectors.toList());
    }

    public List<ListingDto> searchByKeywords(String keywords) {

        List<Listing> list = listingRepository.findByTitleContainingIgnoreCase(keywords);

        if(list.isEmpty()){
            throw new RuntimeException("No Matching Found ");
        }

        return list.stream().map(e->mapToDto(e)).collect(Collectors.toList());
    }
    }

