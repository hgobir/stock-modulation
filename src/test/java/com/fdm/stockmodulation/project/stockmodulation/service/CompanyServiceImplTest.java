package com.fdm.stockmodulation.project.stockmodulation.service;

import com.fdm.stockmodulation.project.stockmodulation.entity.Company;
import com.fdm.stockmodulation.project.stockmodulation.entity.Stock;
import com.fdm.stockmodulation.project.stockmodulation.repository.CompanyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;


@ExtendWith(MockitoExtension.class)
class CompanyServiceImplTest {

    @Mock
    private CompanyRepository companyRepository;

    @Mock
    private StockService stockService;

    private CompanyService underTest;


    @BeforeEach
    void setUp() {
        underTest = new CompanyServiceImpl(companyRepository, stockService);
    }

    @Test
    void getCompanies() {

        Stock s1 = new Stock(1.0,1L,"TEST1",5.0,5.0,10.0);

        Stock s2 = new Stock(1.0,1L,"TEST2",5.0,5.0,10.0);

        List<Company> companyList = new ArrayList<>();
        companyList.add(new Company("name1", "desc", "sector", "ceo", "address", 10L, s1));
        companyList.add(new Company("name", "desc", "sector", "ceo", "address", 10L, s2));

        Mockito.when(companyRepository.findAll()).thenReturn(companyList);

        underTest.getCompanies();

        Mockito.verify(companyRepository).findAll();
    }

    @Test
    void getCompany() {

        Stock s1 = new Stock(1.0,1L,"TEST1",5.0,5.0,10.0);

        Company company =new Company("name1", "desc", "sector", "ceo", "address", 10L, s1);
        Mockito.when(companyRepository.findById(anyLong())).thenReturn(Optional.of(company));

        underTest.getCompany(anyLong());

        Mockito.verify(companyRepository).findById(anyLong());
    }

//    @Test
//    void createCompany() {
//
////        Stock s1 = new Stock(1.0,1L,"TEST1",5.0,5.0,10.0);
////        Stock s2 = new Stock(1.0,1L,"CAL2",5.0,5.0,10.0);
//        List<Stock> stockList = new ArrayList<>();
//
//        stockList.add(new Stock(1.0,1L,"TEST1",5.0,5.0,10.0));
//        stockList.add(new Stock(1.0,1L,"CAL2",5.0,5.0,10.0));
//
//        Mockito.when(stockService.getStocks()).thenReturn(stockList);
//        Mockito.when(stockService.saveStock(any())).thenReturn(new Stock(1.0,1L,"TEST1",5.0,5.0,10.0));
//
//        Stock stock = new Stock(1.0,1L,"TEST1",5.0,5.0,10.0);
//        Company company =new Company(1L,"name1", "desc", "sector", "ceo", "address", 10L, stock);
//
//
//
//        Mockito.when(underTest.saveCompany(any())).thenReturn(company);
//
//        List<Company> companyList = new ArrayList<>();
//        companyList.add(new Company("name1", "desc", "sector", "ceo", "address", 10L, stock));
////        companyList.add(new Company("name", "desc", "sector", "ceo", "address", 10L, s2));
//
//
//        Mockito.when(companyRepository.findAll()).thenReturn(companyList);
//        Mockito.when(companyRepository.saveAndFlush(any())).thenReturn(company);
//
//
//        underTest.createCompany("new-name", "new-desc", "new-sector", "new-ceo", "new-address", 10000L);
//    }

    @Test
    void updateCompany() {

        Stock s1 = new Stock(1.0,1L,"TEST1",5.0,5.0,10.0);

        Company company =new Company("name1", "desc", "sector", "ceo", "address", 10L, s1);
        Mockito.when(companyRepository.findById(anyLong())).thenReturn(Optional.of(company));
        Mockito.when(companyRepository.save(any())).thenReturn(company);

        underTest.updateCompany(1L, "name1", "desc", "sector", "ceo", "address", 10L);

        Mockito.verify(companyRepository).findById(anyLong());

    }

    @Test
    void deleteCompany() {

        Stock s1 = new Stock(1.0,1L,"TEST1",5.0,5.0,10.0);

//        Company company =new Company("name1", "desc", "sector", "ceo", "address", 10L, s1);
//        Mockito.when(companyRepository.deleteById(anyLong())).thenReturn(Optional.of(company));

        underTest.deleteCompany(anyLong());

        Mockito.verify(companyRepository).deleteById(anyLong());

    }

    @Test
    void saveCompany() {

        Stock s1 = new Stock(1.0,1L,"TEST1",5.0,5.0,10.0);

        Company company =new Company("name1", "desc", "sector", "ceo", "address", 10L, s1);
        Mockito.when(companyRepository.saveAndFlush(any())).thenReturn(company);

        underTest.saveCompany(company);

        Mockito.verify(companyRepository).saveAndFlush(any());
    }

    @Test
    void getSpecificCompanies() {

        Stock stock = new Stock(1.0,1L,"TEST1",5.0,5.0,10.0);
        Company company =new Company("name1", "desc", "sector", "ceo", "address", 10L, stock);

        Mockito.when(companyRepository.findById(anyLong())).thenReturn(Optional.of(company));

        List<Long> longList = new ArrayList<>();
        longList.add(1L);

        List<Company> testCompanies = underTest.getSpecificCompanies(longList);

        assertEquals(1, testCompanies.size());




    }
}