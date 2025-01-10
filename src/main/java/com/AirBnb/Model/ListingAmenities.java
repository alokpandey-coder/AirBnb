package com.AirBnb.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "listing_amenities")
public class ListingAmenities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long listingAmenitiesId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "listing_id")
    @JsonBackReference
    private Listing listing;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "amenities_id")
    private Amenities amenities;

    public Long getListingAmenitiesId() {
        return listingAmenitiesId;
    }

    public void setListingAmenitiesId(Long listingAmenitiesId) {
        this.listingAmenitiesId = listingAmenitiesId;
    }

    public Listing getListing() {
        return listing;
    }

    public void setListing(Listing listing) {
        this.listing = listing;
    }

    public Amenities getAmenities() {
        return amenities;
    }

    public void setAmenities(Amenities amenities) {
        this.amenities = amenities;
    }
}