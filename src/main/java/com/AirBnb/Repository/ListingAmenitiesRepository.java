package com.AirBnb.Repository;

import com.AirBnb.Model.ListingAmenities;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListingAmenitiesRepository extends JpaRepository<ListingAmenities, Long> {
}