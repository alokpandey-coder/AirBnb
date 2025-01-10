package com.AirBnb.Controller;

import com.AirBnb.Payload.LocationDto;
import com.AirBnb.Service.LocationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/location")
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @PostMapping("/addLocation")
    public ResponseEntity<LocationDto> enterLocation(@RequestBody LocationDto dto){

        return new ResponseEntity<LocationDto>(locationService.enterLocation(dto), HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteLocation")
    public ResponseEntity<String> deleteLocation(@RequestParam Long locationId){
        return new ResponseEntity<String>(locationService.deleteLocation(locationId),HttpStatus.OK);
    }

    @PutMapping("/updateLocation")
    public ResponseEntity<LocationDto> updateLocation(@RequestParam Long locationId,@RequestBody LocationDto dto){
        return new ResponseEntity<LocationDto>(locationService.updateLocation(locationId,dto),HttpStatus.OK);
    }


    @GetMapping("/allLocations")
    public ResponseEntity<List<LocationDto>> getAllLocations(){
        List<LocationDto> llist = locationService.getAllLocation();
        return new ResponseEntity<>(llist,HttpStatus.OK);
    }

    @GetMapping("/location/{locationId}")
    public ResponseEntity<LocationDto> getLocationById(@PathVariable Long locationId) {
        LocationDto locationDto = locationService.getSpecificLocation(locationId);
        return new ResponseEntity<>(locationDto, HttpStatus.OK);
    }


}
