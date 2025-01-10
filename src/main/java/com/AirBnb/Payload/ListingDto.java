package com.AirBnb.Payload;

import java.util.List;

public class ListingDto {

    private Long listingId;
    private String title;
    private String description;
    private Double price;
    private Integer guestLimits;
    private Long userId;
    private Long locationId;
    private List<Long> listingAmenitiesIds;

    public Long getListingId() {
        return listingId;
    }

    public void setListingId(Long listingId) {
        this.listingId = listingId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getGuestLimits() {
        return guestLimits;
    }

    public void setGuestLimits(Integer guestLimits) {
        this.guestLimits = guestLimits;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public List<Long> getListingAmenitiesIds() {
        return listingAmenitiesIds;
    }

    public void setListingAmenitiesIds(List<Long> listingAmenitiesIds) {
        this.listingAmenitiesIds = listingAmenitiesIds;
    }
}
