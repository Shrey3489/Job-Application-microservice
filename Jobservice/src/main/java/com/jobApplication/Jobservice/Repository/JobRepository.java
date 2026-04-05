package com.jobApplication.Jobservice.Repository;

import com.jobApplication.Jobservice.Entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<Job,Long>
{
}
