package com.company.innowise.airticketsapp.businessservice.repository;

import com.company.innowise.airticketsapp.businessservice.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long>, JpaSpecificationExecutor<Company> {
    List<Company> findCompaniesByCompanyName(String name);
}
