package com.AirBnb.Repository;

import com.AirBnb.Model.Amenities;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AmenitiesRepository extends JpaRepository<Amenities, Long> {

    Optional<Amenities> findByAmenityName(String amenityName);
}