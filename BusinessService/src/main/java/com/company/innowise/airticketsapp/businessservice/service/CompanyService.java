package com.company.innowise.airticketsapp.businessservice.service;

import com.company.innowise.airticketsapp.businessservice.model.Airport;
import com.company.innowise.airticketsapp.businessservice.model.Airport_;
import com.company.innowise.airticketsapp.businessservice.model.Company;
import com.company.innowise.airticketsapp.businessservice.model.Company_;
import com.company.innowise.airticketsapp.businessservice.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    public List<Company> getAll() {
        return companyRepository.findAll();
    }

    public List<Company> getAll(String name) {
        return companyRepository.findCompaniesByName(name);
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
