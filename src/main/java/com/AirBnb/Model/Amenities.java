package com.AirBnb.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "amenities")
public class Amenities {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long amenityId;

    @Lob
    @Column(name = "amenity_name", nullable = false)
    private String amenityName;

    @OneToMany
    private List<ListingAmenities> listingAmenities;

    public Long getAmenityId() {
        return amenityId;
    }

    public void setAmenityId(Long amenityId) {
        this.amenityId = amenityId;
    }

    public String getAmenityName() {
        return amenityName;
    }

    public void setAmenityName(String amenityName) {
        this.amenityName = amenityName;
    }

    public List<ListingAmenities> getListingAmenities() {
        return listingAmenities;
    }

    public void setListingAmenities(List<ListingAmenities> listingAmenities) {
        this.listingAmenities = listingAmenities;
    }
}