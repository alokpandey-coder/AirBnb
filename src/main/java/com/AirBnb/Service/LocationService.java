package com.AirBnb.Service;

import com.AirBnb.Model.Location;
import com.AirBnb.Payload.LocationDto;
import com.AirBnb.Repository.LocationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LocationService {

    private final LocationRepository locationRepository;
    private final ModelMapper modelMapper;

    public LocationService(LocationRepository locationRepository, ModelMapper modelMapper) {
        this.locationRepository = locationRepository;
        this.modelMapper = modelMapper;
    }

   LocationDto mapToDto(Location location){
      return  modelMapper.map(location,LocationDto.class);
   }

  Location mapToEntity(LocationDto dto){
        return modelMapper.map(dto,Location.class);
   }


    public LocationDto enterLocation(LocationDto dto) {

       Location location = mapToEntity(dto);
       locationRepository.save(location);
       return mapToDto(location);
    }

    public String deleteLocation(Long locationId) {

       locationRepository.deleteById(locationId);
       return "Location Deleted";
    }

    public LocationDto updateLocation(Long locationId, LocationDto dto) {

        Optional<Location> opLocation = locationRepository.findById(locationId);

        if(opLocation.isPresent()){

            Location location = opLocation.get();
            location.setAddress(dto.getAddress());
            location.setPinCode(dto.getPinCode());
            location.setCity(dto.getCity());
            location.setState(dto.getState());
            location.setCountry(dto.getCountry());

            Location loc =locationRepository.save(location);
            return mapToDto(loc);

        }
        else{
            throw new RuntimeException("Location are not Available!!!!!");
        }
    }

    public List<LocationDto> getAllLocation() {

        List<Location> location = locationRepository.findAll();
        return location.stream().map(e->mapToDto(e)).collect(Collectors.toList());
    }


    public LocationDto getSpecificLocation(Long locationId) {
      Location location =  locationRepository.findById(locationId)
                            .orElseThrow(()-> new RuntimeException("Invalid locationId"+locationId));

        return mapToDto(location);
    }
}
