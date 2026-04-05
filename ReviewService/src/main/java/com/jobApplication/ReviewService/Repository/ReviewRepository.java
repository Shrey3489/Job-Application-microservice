package com.jobApplication.ReviewService.Repository;

import com.jobApplication.ReviewService.Entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {

    List<Review> findBycompanyId(Long id);
}
