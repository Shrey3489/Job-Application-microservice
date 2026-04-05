package com.jobApplication.CompanyService.Service;

import com.jobApplication.CompanyService.Repository.CompanyRepository;
import com.jobApplication.CompanyService.Entity.Company;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService
{
    private CompanyRepository moCompanyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository)
    {
        this.moCompanyRepository = companyRepository;
    }

    public List<Company> gindAllCompany()
    {
        return moCompanyRepository.findAll();
    }

    @Override
    public Company updateCompany(Company updatedData, Long id)
    {
        Company loCompany = moCompanyRepository.findById(id).orElse(null);
        if(loCompany!=null)
        {
            loCompany.setDescription(updatedData.getDescription());
            loCompany.setName(updatedData.getName());
            moCompanyRepository.save(loCompany);
            return  loCompany;
        }
        return null;
    }

    @Override
    public void createCompany(Company loCompany)
    {
        moCompanyRepository.save(loCompany);
    }

    @Override
    public Company getCompanyById(Long id)
    {
        return moCompanyRepository.findById(id).orElse(null);
    }

    @Override
    public String deleteById(Long id)
    {
        if(moCompanyRepository.existsById(id))
        {
            moCompanyRepository.deleteById(id);
            return "Deleted Company";
        }
        return "Company not found";
    }
}
