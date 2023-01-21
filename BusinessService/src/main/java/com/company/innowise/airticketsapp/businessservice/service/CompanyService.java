package com.company.innowise.airticketsapp.businessservice.service;

import com.company.innowise.airticketsapp.businessservice.model.Company;
import com.company.innowise.airticketsapp.businessservice.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    public List<Company> getAll() {
        return companyRepository.findAll();
    }

    public List<Company> getAll(String name) {
        return companyRepository.findCompaniesByCompanyName(name);
    }

    public Company getCompany(Long id) {
        return companyRepository.getReferenceById(id);
    }

    public Company addCompany(Company company) {
        companyRepository.save(company);
        return company;
    }

    public void deleteCompany(long id) {
        companyRepository.deleteById(id);
    }

}
