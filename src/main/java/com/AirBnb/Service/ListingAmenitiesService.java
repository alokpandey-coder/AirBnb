package com.AirBnb.Service;

import com.AirBnb.Model.Amenities;
import com.AirBnb.Model.Listing;
import com.AirBnb.Model.ListingAmenities;
import com.AirBnb.Repository.AmenitiesRepository;
import com.AirBnb.Repository.ListingAmenitiesRepository;
import com.AirBnb.Repository.ListingRepository;
import org.springframework.stereotype.Service;

@Service
public class ListingAmenitiesService {

    private final ListingAmenitiesRepository listingAmenitiesRepository;
    private final ListingRepository listingRepository;
    private final AmenitiesRepository amenitiesRepository;

    public ListingAmenitiesService(ListingAmenitiesRepository listingAmenitiesRepository, ListingRepository listingRepository, AmenitiesRepository amenitiesRepository) {
        this.listingAmenitiesRepository = listingAmenitiesRepository;
        this.listingRepository = listingRepository;
        this.amenitiesRepository = amenitiesRepository;
    }

    public String assignListingAmenities(Long listingId, Long amenityId) {

        Listing listing = listingRepository.findById(listingId)
                                          .orElseThrow(() -> new RuntimeException("Listing Not Available"));
        Amenities amenities = amenitiesRepository.findById(amenityId)
                                             .orElseThrow(() -> new RuntimeException("Amenities Not Available"));
        ListingAmenities listAmenities = new ListingAmenities();
        listAmenities.setListing(listing);
        listAmenities.setAmenities(amenities);

        listingAmenitiesRepository.save(listAmenities);

        return "Assigned Listing-> "+listingId+ " to amenities with-> "+ amenityId;

    }

    public String deleteListingAmenities(Long listingAmenitiesId) {

        ListingAmenities listingAmenities = listingAmenitiesRepository.findById(listingAmenitiesId).orElseThrow(()->new RuntimeException("ListingAmenitiesId is not Present in records"));

        listingAmenities.setAmenities(null);
        listingAmenities.setListing(null);
        listingAmenitiesRepository.deleteById(listingAmenitiesId);

        return "Successfully deleted......";
    }
}
