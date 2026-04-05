package com.jobApplication.Jobservice.InterserviceCommunication;

import com.jobApplication.Jobservice.ExternalDto.CompanyDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.Optional;

@HttpExchange("/companies")
public interface CompanyServiceClient {

    @GetExchange("/get/{id}")
   Optional<CompanyDto> getCompanyDetail(@PathVariable Long id);
}
