package com.jobApplication.Jobservice.ExternalDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobWithCompanyDto {
    private String title;
    private String minSalary;
    private String maxSalary;
    private String location;
    private String description;
    private CompanyDto companyDto;
}
