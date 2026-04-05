package com.jobApplication.ReviewService.ClientServieConfig;

import com.jobApplication.ReviewService.ExternalDto.CompanyDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.Optional;

@HttpExchange("/companies")
public interface CompanyClient {

    @GetExchange("/get/{id}")
    Optional<CompanyDto> getCompanyDetail(@PathVariable Long id);
}
