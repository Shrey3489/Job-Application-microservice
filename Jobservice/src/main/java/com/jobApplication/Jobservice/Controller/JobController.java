package com.jobApplication.Jobservice.Controller;

import com.jobApplication.Jobservice.Entity.Job;
import com.jobApplication.Jobservice.ExternalDto.JobWithCompanyDto;
import com.jobApplication.Jobservice.Service.JobService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService)
    {
        this.jobService = jobService;
    }

    @GetMapping
    public List<JobWithCompanyDto> findAll()
    {
        return jobService.findAll();
    }

    @PostMapping("/admin")
    public ResponseEntity<String> createJobs(@RequestBody Job job)
    {
        return new ResponseEntity<>(jobService.createJob(job),HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<JobWithCompanyDto> getJobById(@PathVariable Long id)
    {
        JobWithCompanyDto lojob = jobService.getJobById(id);
        if(lojob==null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else
        {
            return new ResponseEntity<>(lojob,HttpStatus.FOUND);
        }
    }

    @DeleteMapping("admin/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id)
    {
        boolean deleted = jobService.deleteJobById(id);
        if(deleted)
        {
            return new ResponseEntity<>("Delete Job entry",HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Job entry Not Found",HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("admin/{id}")
    public ResponseEntity<String> updateJob(@PathVariable Long id, @RequestBody Job updateJob)
    {
        boolean updated = jobService.updateJob(id, updateJob);
        if(updated)
        {
            return new ResponseEntity<>("Update Successfull",HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>("Job entry Not Found",HttpStatus.NOT_FOUND);
        }
    }
}
