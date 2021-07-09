package com.fdm.stockmodulation.project.stockmodulation.service;

import com.fdm.stockmodulation.project.stockmodulation.entity.Company;
import com.fdm.stockmodulation.project.stockmodulation.entity.Stock;
import com.fdm.stockmodulation.project.stockmodulation.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final StockService stockService;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository, StockService stockService) {
        this.companyRepository = companyRepository;
        this.stockService = stockService;
    }

    @Transactional(readOnly = true)
    public List<Company> getCompanies() {
        return companyRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Company> getCompany(Long companyId) {
        System.out.println("inside getCompany method in company service, companyId passed in is " + companyId);
        return companyRepository.findById(companyId);
    }

    @Transactional
    public Stock createCompany(String name, String description, String sector, String ceo, String address, Long valuation) {
        String newCompanyStockSymbol = name.replaceAll("\\s+","").toUpperCase().substring(0, 3).trim();
        Predicate<String> checkIfSymbolExists = newSymbol -> {
            List<String> stockSymbols = stockService.getStocks().stream().map(Stock::getSymbol).collect(Collectors.toList());
            stockSymbols.forEach(System.out::println);
            boolean isSymbolPresent = stockSymbols.contains(newSymbol);
            return isSymbolPresent;
        };

        //@todo - improve validation and randomisation of symbol!!
        if(!checkIfSymbolExists.test(newCompanyStockSymbol)) {
            Stock savedStock = stockService.saveStock(new Stock(0.0, 0L, newCompanyStockSymbol, 0.0, 0.0, 0.0));
            System.out.println("this is stock that will be saved! "+ savedStock.toString());
            Company savedCompany = saveCompany(new Company(name, description, sector, ceo, address, valuation, savedStock));
            System.out.println("this is company that will be saved! "+ savedCompany.toString());

            return savedCompany.getStock();
        }
        return null;
    }

//    todo - need to update stock symbol if name has changed!!
    @Override
    @Transactional
    public synchronized Company updateCompany(Long companyId, String name, String description, String sector, String ceo, String address, Long valuation) {
        Optional<Company> companyToUpdate = companyRepository.findById(companyId);
        if(companyToUpdate.isPresent()) {
            Company companyUpdated = companyToUpdate.get();
            companyUpdated.setName(name);
            companyUpdated.setDescription(description);
            companyUpdated.setSector(sector);
            companyUpdated.setCeo(ceo);
            companyUpdated.setAddress(address);
            companyUpdated.setValuation(valuation);
            return companyRepository.save(companyUpdated);
        }
        return null;
    }

    @Override
    @Transactional
    public synchronized boolean deleteCompany(Long companyId) {
//        Long companyIdAsLong = Long.getLong(companyId);
        System.out.println("this is id in deleteCompany method " + companyId);
        companyRepository.deleteById(companyId);
        return companyRepository.findById(companyId).isPresent();
    }

    @Override
    @Transactional
    public Company saveCompany(Company company) {
        if(company.getCompanyId() == null) {
            long companyNumber = companyRepository.findAll().size();
            Long newCompanyId = companyNumber + 1;
            company.setCompanyId(newCompanyId);
        }
        return companyRepository.saveAndFlush(company);
    }

    @Override
    public List<Company> getSpecificCompanies(List<Long> companyIds) {

        System.out.println("in specificCompanies method - about to get company objects by ids");

        List<Company> companyList = companyIds.stream().map(companyId -> companyRepository.findById(companyId).get()).collect(Collectors.toList());

        System.out.println("this is number of companies in list! ["+ companyList.size() + "]");
        companyList.forEach(company -> System.out.println("this is company in company list " + company.toString()));
        return companyList;
    }


}
