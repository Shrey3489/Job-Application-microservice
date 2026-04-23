package com.jobApplication.CompanyService.AsyncServiceInteract;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReviewListner {

    @RabbitListener(queues = "reviewRating.Queue")
    public CompanyRateingDto getCompanyReview(CompanyRateingDto companyRateingDto)
    {
        return companyRateingDto;
    }
}
