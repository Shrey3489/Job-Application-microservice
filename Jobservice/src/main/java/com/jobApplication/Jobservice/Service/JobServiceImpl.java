package com.jobApplication.Jobservice.Service;

import com.jobApplication.Jobservice.Entity.Job;
import com.jobApplication.Jobservice.ExternalDto.CompanyDto;
import com.jobApplication.Jobservice.ExternalDto.JobWithCompanyDto;
import com.jobApplication.Jobservice.ExternalDto.ReviewDto;
import com.jobApplication.Jobservice.Repository.JobRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {

    private final JobRepository moJobRepository;

    private final ExternalService externalService;

    public JobServiceImpl(JobRepository jobRepository,
                          ExternalService externalService)
    {
        this.moJobRepository = jobRepository;
        this.externalService = externalService;
    }


    @Override
    public List<JobWithCompanyDto> findAll()
    {
        List<Job> loJobs = moJobRepository.findAll();
        return loJobs.stream().map(this::convertToResponceDto).collect(Collectors.toList());
    }

    public JobWithCompanyDto convertToResponceDto(Job foJob)
    {
        CompanyDto loCompanyDto = isCompanyPresent(foJob.getCompanyId());
        if(loCompanyDto==null)
        {
            return null;
        }
        JobWithCompanyDto loJobWithCompanyDto = new JobWithCompanyDto();
        loJobWithCompanyDto.setTitle(foJob.getTitle());
        loJobWithCompanyDto.setDescription(foJob.getDescription());
        loJobWithCompanyDto.setLocation(foJob.getLocation());
        loJobWithCompanyDto.setMaxSalary(foJob.getMaxSalary());
        loJobWithCompanyDto.setMinSalary(foJob.getMinSalary());
        loJobWithCompanyDto.setCompanyDto(loCompanyDto);
        ReviewDto loReviewDto = getReviewDto(loCompanyDto.getId());
        loJobWithCompanyDto.getCompanyDto().setReviewDto(loReviewDto);
        return loJobWithCompanyDto;
    }

    @Override
    public String createJob(Job job)
    {
        if(isCompanyPresent(job.getCompanyId())==null)
        {
            return "Company not found";
        }
        moJobRepository.save(job);
        return "Created successfully";
    }

    @Override
    public JobWithCompanyDto getJobById(Long id)
    {
        Job lojob = moJobRepository.findById(id).orElse(null);
        return convertToResponceDto(lojob);
    }

    @Override
    public boolean deleteJobById(Long id) {
        Job loJob = moJobRepository.findById(id).orElse(null);
        if(loJob!=null)
        {
            moJobRepository.delete(loJob);
            return true;
        }
        return false;
  }

    @Override
    public boolean updateJob(Long id, Job updateJob) {

        Job job = moJobRepository.findById(id).orElse(null);
            if(job!=null)
            {
                if(isCompanyPresent(job.getCompanyId())==null)
                {
                    return false;
                }
                job.setTitle(updateJob.getTitle());
                job.setDescription(updateJob.getDescription());
                job.setLocation(updateJob.getLocation());
                job.setMaxSalary(updateJob.getMaxSalary());
                job.setMinSalary(updateJob.getMinSalary());
                job.setCompanyId(updateJob.getCompanyId());
                moJobRepository.save(job);
                return true;
            }
        return false;
    }

    public CompanyDto isCompanyPresent(Long id)
    {
            return externalService.getCompany(id);
    }

    public ReviewDto getReviewDto(Long id)
    {
        return externalService.getReview(id);
    }

}
