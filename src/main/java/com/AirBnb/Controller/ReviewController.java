package com.AirBnb.Controller;

import com.AirBnb.Model.Users;
import com.AirBnb.Payload.ReviewsDto;
import com.AirBnb.Service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

//    @PostMapping("/addReview")
//    public ResponseEntity<ReviewsDto> addReview(
//                                     @RequestBody ReviewsDto reviewsDto,
//                                     @RequestParam Long listingId,
//                                     @AuthenticationPrincipal Users userId
//    ){
//         ReviewsDto reviews = reviewService.addReview(reviewsDto,listingId,userId);
//         return new ResponseEntity<>(reviews, HttpStatus.OK);
//    }
//
//    //Review of a Person who currently Login to System
//    @GetMapping("/myReview")
//    public ResponseEntity<ReviewsDto> getMyReview(@AuthenticationPrincipal Users user){
//
//        reviewService.getMyReview(user);
//    }

}
