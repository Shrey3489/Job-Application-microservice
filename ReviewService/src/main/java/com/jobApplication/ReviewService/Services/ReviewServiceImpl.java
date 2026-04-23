package com.jobApplication.ReviewService.Services;

import com.jobApplication.ReviewService.AsyncServiceInteract.CompanyRateingDto;
import com.jobApplication.ReviewService.ClientServieConfig.CompanyClient;
import com.jobApplication.ReviewService.Entity.Review;
import com.jobApplication.ReviewService.ExternalDto.CompanyDto;
import com.jobApplication.ReviewService.Repository.ReviewRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    private ReviewRepository reviewRepository;

    private CompanyClient companyClient;

    private RabbitTemplate rabbitTemplate;

    public ReviewServiceImpl(ReviewRepository reviewRepository,CompanyClient companyClient, RabbitTemplate rabbitTemplate)
    {
        this.companyClient = companyClient;
        this.reviewRepository = reviewRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public List<Review> getAllReview(Long companyId) {
        if(!isCompanyPresent(companyId))
        {
            return null;
        }
        List<Review> reviews = reviewRepository.findBycompanyId(companyId);
        return reviews;
    }

    @Override
    public String addReview(Long companyId, Review review)
    {
        if(companyId!=null && review!=null)
        {
            if(!isCompanyPresent(companyId))
            {
                return "Company Not found";
            }
            review.setCompanyId(companyId);
            reviewRepository.save(review);

            CompanyRateingDto loCompanyRateingDto = new CompanyRateingDto();
            loCompanyRateingDto.setCompanyId(companyId);
            loCompanyRateingDto.setRateing(calculateCompanyRating(companyId));
            rabbitTemplate.convertAndSend("reviewRating.Exchange","reviewRating.RouteKey",loCompanyRateingDto);
            return "Review Added";
        }
        return "Review not added";
    }

    public Double calculateCompanyRating(long companyId)
    {
        List<Review> reviews = reviewRepository.findBycompanyId(companyId);
        return reviews.stream()
                .mapToDouble(Review::getRating)
                .average()
                .orElse(0.0);
    }

    @Override
    public Review getReviewById(Long id) {

        return reviewRepository.findById(id).orElse(null);
    }

    @Override
    public String updateReview(Long id, Review review) {
        Review loReview1 = reviewRepository.findById(id).orElse(null);
        if(loReview1!=null)
        {
            if(!isCompanyPresent(review.getCompanyId()))
            {
                return "Company Not found";
            }
            loReview1.setDescription(review.getDescription());
            loReview1.setTitle(review.getTitle());
            loReview1.setRating(review.getRating());
            loReview1.setCompanyId(review.getCompanyId());
            reviewRepository.save(loReview1);
            return "Updated";
        }
        else
        {
            return "Update failed";
        }

    }

    @Override
    public boolean deleteReview(Long id)
    {
        if(reviewRepository.existsById(id))
        {
            reviewRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean isCompanyPresent(Long id)
    {
        try
        {
            Optional<CompanyDto> loCompanyDto = companyClient.getCompanyDetail(id);
            return loCompanyDto.isPresent();
        } catch (Exception e) {
            return false;
        }
    }
}
