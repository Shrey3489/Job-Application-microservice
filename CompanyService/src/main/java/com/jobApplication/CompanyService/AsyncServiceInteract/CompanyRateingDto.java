package com.jobApplication.CompanyService.AsyncServiceInteract;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyRateingDto {

    public long companyId;
    public Double rateing;
}
