package com.jobApplication.CompanyService.Service;

import com.jobApplication.CompanyService.Entity.Company;

import java.util.List;

public interface CompanyService {
    public List<Company> gindAllCompany();
    Company updateCompany(Company updatedData, Long id);
    void createCompany(Company loCompany);
    Company getCompanyById(Long id);
    String deleteById(Long id);
}
