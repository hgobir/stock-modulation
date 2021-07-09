package com.fdm.stockmodulation.project.stockmodulation.service;

import com.fdm.stockmodulation.project.stockmodulation.entity.Company;
import com.fdm.stockmodulation.project.stockmodulation.entity.Stock;
import com.fdm.stockmodulation.project.stockmodulation.model.ListOfIds;

import java.util.List;
import java.util.Optional;

public interface CompanyService {

    List<Company> getCompanies();

    Optional<Company> getCompany(Long companyId);

    Stock createCompany(String name, String description, String sector, String ceo, String address, Long valuation);

    Company updateCompany(Long companyId, String name, String description, String sector, String ceo, String address, Long valuation);

    boolean deleteCompany(Long companyId);

    Company saveCompany(Company company);

    List<Company> getSpecificCompanies(List<Long> companyIds);
}
