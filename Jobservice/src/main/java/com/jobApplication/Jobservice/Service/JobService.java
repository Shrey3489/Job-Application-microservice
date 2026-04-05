package com.jobApplication.Jobservice.Service;

import com.jobApplication.Jobservice.Entity.Job;
import com.jobApplication.Jobservice.ExternalDto.JobWithCompanyDto;

import java.util.List;

public interface JobService {
    List<JobWithCompanyDto> findAll();
    String createJob(Job job);

    JobWithCompanyDto getJobById(Long id);

    boolean deleteJobById(Long id);

    boolean updateJob(Long id, Job updateJob);
}
