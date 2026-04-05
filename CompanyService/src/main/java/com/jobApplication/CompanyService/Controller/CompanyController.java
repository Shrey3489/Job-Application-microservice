package com.jobApplication.CompanyService.Controller;

import com.jobApplication.CompanyService.Service.CompanyService;
import com.jobApplication.CompanyService.Entity.Company;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController
{
    private CompanyService companyService;

    public CompanyController(CompanyService loCompanyService)
    {
        this.companyService = loCompanyService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Company>> getAllCompany()
    {
        return new ResponseEntity<>(companyService.gindAllCompany(), HttpStatus.FOUND);
    }

    @PutMapping("/admin/editCompany/{id}")
    public ResponseEntity<Company> updateCompany(@RequestBody Company updaetCompany, @PathVariable Long id)
    {
        Company loCompany = companyService.updateCompany(updaetCompany,id);
        if(loCompany==null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(loCompany,HttpStatus.OK);
    }

    @PostMapping("/admin/createNew")
    public ResponseEntity<String> createCompany(@RequestBody Company company)
    {
        companyService.createCompany(company);
        return new ResponseEntity<>("Created",HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id)
    {
        Company loCompany = companyService.getCompanyById(id);
        System.out.println("invoked");
        if(loCompany==null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(loCompany,HttpStatus.OK);
    }

    @DeleteMapping("admin/{id}")
    public ResponseEntity<String> deleteCompanyById(@PathVariable Long id)
    {
        return new ResponseEntity<>(companyService.deleteById(id),HttpStatus.OK);
    }
}
