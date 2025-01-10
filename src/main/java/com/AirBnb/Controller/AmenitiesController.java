package com.AirBnb.Controller;

import com.AirBnb.Payload.AmenitiesDto;
import com.AirBnb.Service.AmenitiesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/amenities")
public class AmenitiesController {

    private final AmenitiesService amenitiesService;

    public AmenitiesController(AmenitiesService amenitiesService) {
        this.amenitiesService = amenitiesService;
    }

    //http:localhost:8080/api/amenities/addAmenities
    @PostMapping("/addAmenities")
    private ResponseEntity<AmenitiesDto> addNewAmenities(@RequestBody AmenitiesDto dto){
        return new ResponseEntity<AmenitiesDto>(amenitiesService.addNewAmenities(dto), HttpStatus.CREATED);
    }

    //http:localhost:8080/api/amenities/deleteAmenities
    @DeleteMapping("/deleteAmenities")
    private ResponseEntity<String> deleteAmenities(@RequestParam Long amenityId){
        return new ResponseEntity<String>(amenitiesService.deleteAmenities(amenityId),HttpStatus.OK);
    }

    //http:localhost:8080/api/amenities/getAllAmenities
    @GetMapping("/getAllAmenities")
    private ResponseEntity<List<AmenitiesDto>> getAllRecords(){
        return new ResponseEntity<List<AmenitiesDto>>(amenitiesService.getAllRecords(),HttpStatus.OK);
    }

    //http:localhost:8080/api/amenities/getSpecificAmenities/{amenityId}
    @GetMapping("getSpecificAmenities/{amenityId}")
    private ResponseEntity<AmenitiesDto> getSpecificRecord(@PathVariable Long amenityId){
        return new ResponseEntity<AmenitiesDto>(amenitiesService.getSpecificRecord(amenityId),HttpStatus.OK);
    }

    //http:localhost:8080/api/amenities/updateAmenities
    @PutMapping("/updateAmenities")
    private ResponseEntity<AmenitiesDto> updateAmenities(@RequestParam Long amenityId,@RequestBody AmenitiesDto dto){
        return new ResponseEntity<AmenitiesDto>(amenitiesService.updateAmenities(amenityId,dto),HttpStatus.OK);
    }
}
