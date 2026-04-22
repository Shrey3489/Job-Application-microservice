package com.jobApplication.Jobservice.Service;

import com.jobApplication.Jobservice.ExternalDto.CompanyDto;
import com.jobApplication.Jobservice.ExternalDto.ReviewDto;
import com.jobApplication.Jobservice.InterserviceCommunication.CompanyServiceClient;
import com.jobApplication.Jobservice.InterserviceCommunication.ReviewServiceClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ExternalService {
    private final CompanyServiceClient companyServiceClient;
    private final ReviewServiceClient reviewServiceClient;

    public ExternalService(@Qualifier("getCompanySericeClinet") CompanyServiceClient companyServiceClient,
                           @Qualifier("getReviewSericeClinet") ReviewServiceClient reviewServiceClient) {
        this.companyServiceClient = companyServiceClient;
        this.reviewServiceClient = reviewServiceClient;
    }

    @CircuitBreaker(name = "CompanyBreaker", fallbackMethod = "companyFallback")
    public CompanyDto getCompany(Long id) {
        return companyServiceClient.getCompanyDetail(id).orElse(null);
    }

    @CircuitBreaker(name = "reviewBreaker", fallbackMethod = "reviewFallback")
    public ReviewDto getReview(Long id) {
        return reviewServiceClient.getReviewDetails(id);
    }

    public CompanyDto companyFallback(Long id, Throwable t) {
        return null;
    }

    public ReviewDto reviewFallback(Long id, Throwable t) {
        return null;
    }
}
