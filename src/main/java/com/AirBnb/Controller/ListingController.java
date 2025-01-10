package com.AirBnb.Controller;

import com.AirBnb.Payload.ListingDto;
import com.AirBnb.Service.ListingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/listing")
public class ListingController {
    private final ListingService listingService;

    public ListingController(ListingService listingService) {
        this.listingService = listingService;
    }

    @PostMapping("/addListing/User/{userId}/Location/{locationId}")
    public ResponseEntity<ListingDto> addNewListing(@PathVariable("userId") Long userId,
                                                    @PathVariable("locationId") Long locationId,
                                                    @RequestBody ListingDto dto){
        return new ResponseEntity<ListingDto>(listingService.addNewListing(userId,locationId,dto), HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteListing/User/{userId}/Listing/{listingId}")
    public ResponseEntity<String> deleteListing(@PathVariable("listingId")Long listingId,
                                                    @PathVariable("userId") Long userId){

        return new ResponseEntity<String>(listingService.deleteListing(listingId,userId),HttpStatus.OK);
    }

    @PutMapping("/updateListing/listing{listingId}")
    public ResponseEntity<ListingDto> updateListing(@RequestBody ListingDto dto,
                                                    @PathVariable("listingId") Long listingId){
        return new ResponseEntity<ListingDto>(listingService.updateListing(dto,listingId),HttpStatus.OK);
    }



    @GetMapping("/getAllListingBy/listing/{listingId}")
    public ResponseEntity<ListingDto> getaAllListingById(@PathVariable("listingId") Long listingId){
        return new ResponseEntity<ListingDto>(listingService.getaAllListingByListingId(listingId),HttpStatus.OK);
    }

    //Single user has more than 1 listing than in get Mapping with help of  User we have to use List<>

    @GetMapping("/getListing/user/{userId}")
    public ResponseEntity<List<ListingDto>> getListingByUserId(@PathVariable("userId")Long userId){
        return new ResponseEntity<List<ListingDto>>(listingService.getListingByUserId(userId),HttpStatus.OK);
    }

    @GetMapping("/getAllListing")
    public ResponseEntity<List<ListingDto>> getAllListing(){
        return new ResponseEntity<List<ListingDto>>(listingService.getAllListing(),HttpStatus.OK);
    }

    //Search By Keywords
    @GetMapping("/searchByKeywords/{keywords}")
    public ResponseEntity<List<ListingDto>> searchByKeywords(@PathVariable("keywords") String keywords){
        return new ResponseEntity<List<ListingDto>>(listingService.searchByKeywords(keywords),HttpStatus.OK);
    }


}
