package com.AirBnb.Repository;

import com.AirBnb.Model.Listing;
import com.AirBnb.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ListingRepository extends JpaRepository<Listing, Long> {

    List<Listing> findByUser(Users user);;


    public List<Listing> findByTitleContainingIgnoreCase(String title);
}