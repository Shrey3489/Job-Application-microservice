package com.jobApplication.Jobservice.InterserviceCommunication;

import com.jobApplication.Jobservice.ExternalDto.ReviewDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange("/review")
public interface ReviewServiceClient {

    @GetExchange("/{id}")
    ReviewDto getReviewDetails(@PathVariable Long id);
}
