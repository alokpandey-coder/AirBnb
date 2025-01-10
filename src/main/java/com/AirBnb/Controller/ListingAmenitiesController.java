package com.AirBnb.Controller;

import com.AirBnb.Model.ListingAmenities;
import com.AirBnb.Service.ListingAmenitiesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/listingAmenities")
public class ListingAmenitiesController {

    private final ListingAmenitiesService listingAmenitiesService;

    public ListingAmenitiesController(ListingAmenitiesService listingAmenitiesService) {
        this.listingAmenitiesService = listingAmenitiesService;
    }


    @PostMapping("/assignListingAmenities/listingId/{listingId}/AmenitiesId/{amenityId}")
    public ResponseEntity<String> assignListingAmenities(@PathVariable("listingId") Long listingId,
                                                                   @PathVariable("amenityId")  Long amenityId){

        return new ResponseEntity<String>(listingAmenitiesService.assignListingAmenities(listingId,amenityId), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/listingAmenitiesId/{listingAmenitiesId}")
    public ResponseEntity<String> deleteListingAmenities(@PathVariable("listingAmenitiesId")Long listingAmenitiesId){
        return new ResponseEntity<String>(listingAmenitiesService.deleteListingAmenities(listingAmenitiesId),HttpStatus.OK);
    }
}
