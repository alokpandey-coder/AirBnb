package com.AirBnb.Service;

import com.AirBnb.Model.Users;
import com.AirBnb.Payload.ReviewsDto;
import com.AirBnb.Repository.ReviewsRepository;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    private final ReviewsRepository reviewsRepository;

    public ReviewService(ReviewsRepository reviewsRepository) {
        this.reviewsRepository = reviewsRepository;
    }



}
