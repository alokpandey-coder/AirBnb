package com.AirBnb.Service;

import com.AirBnb.Model.Amenities;
import com.AirBnb.Payload.AmenitiesDto;
import com.AirBnb.Repository.AmenitiesRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AmenitiesService {

    private final AmenitiesRepository amenitiesRepository;
    private final ModelMapper modelMapper;

    public AmenitiesService(AmenitiesRepository amenitiesRepository, ModelMapper modelMapper) {
        this.amenitiesRepository = amenitiesRepository;
        this.modelMapper = modelMapper;
    }

   Amenities mapToEntity(AmenitiesDto dto){
        return modelMapper.map(dto,Amenities.class);
    }

   AmenitiesDto mapToDto(Amenities amenities){
        return modelMapper.map(amenities,AmenitiesDto.class);
    }

    public AmenitiesDto addNewAmenities(AmenitiesDto dto) {

       Optional<Amenities> opAmenities = amenitiesRepository.findByAmenityName(dto.getAmenityName());

       if(opAmenities.isEmpty()){
           Amenities entity = mapToEntity(dto);
           Amenities saved = amenitiesRepository.save(entity);
           return mapToDto(saved);
       }
       throw new RuntimeException("Amenities are already present in Record");
    }


    public String deleteAmenities(Long amenityId) {
        amenitiesRepository.deleteById(amenityId);
        return "Amenities Deleted From Record";
    }


    public List<AmenitiesDto> getAllRecords() {
        List<Amenities> lists = amenitiesRepository.findAll();
        return lists.stream().map(e->mapToDto(e)).collect(Collectors.toList());
    }


    public AmenitiesDto getSpecificRecord(Long amenityId) {
       Amenities amenities= amenitiesRepository.findById(amenityId)
                                              .orElseThrow(()-> new RuntimeException("Records Not Found"));

        return mapToDto(amenities);
    }

    public AmenitiesDto updateAmenities(Long amenityId, AmenitiesDto dto) {

       Amenities amenities = amenitiesRepository.findById(amenityId).orElseThrow(()-> new RuntimeException("Records Not Found"));
           Amenities amenity =mapToEntity(dto);
           amenity.setAmenityName(dto.getAmenityName());
           Amenities amt =amenitiesRepository.save(amenity);
           return mapToDto(amt);
    }
}
