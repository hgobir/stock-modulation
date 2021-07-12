package com.fdm.stockmodulation.project.stockmodulation.service;

import com.fdm.stockmodulation.project.stockmodulation.entity.Company;
import com.fdm.stockmodulation.project.stockmodulation.entity.Stock;
import com.fdm.stockmodulation.project.stockmodulation.repository.CompanyRepository;
import com.fdm.stockmodulation.project.stockmodulation.repository.StockRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;


@ExtendWith(MockitoExtension.class)
class StockServiceImplTest {


    @Mock
    private StockRepository stockRepository;

//    @Mock
//    private StockService stockService;

    private StockService underTest;

    @BeforeEach
    void setUp() {

        underTest = new StockServiceImpl(stockRepository);
    }

    @Test
    void getStocks() {

        List<Stock> stockList = new ArrayList<>();
        stockList.add(new Stock(1.0,1L,"TEST1",5.0,5.0,10.0));
        stockList.add(new Stock(1.0,1L,"TEST2",5.0,5.0,10.0));

        Mockito.when(stockRepository.findAll()).thenReturn(stockList);

//        List<Company> testList =
        underTest.getStocks();

        Mockito.verify(stockRepository).findAll();
    }

    @Test
    void saveStock() {

        Stock s1 = new Stock(1.0,1L,"TEST1",5.0,5.0,10.0);

//        Company company =new Company("name1", "desc", "sector", "ceo", "address", 10L, s1);
        Mockito.when(stockRepository.saveAndFlush(any())).thenReturn(s1);

        underTest.saveStock(s1);

        Mockito.verify(stockRepository).saveAndFlush(any());



    }

    @Test
    void getStockById() {

        Stock s1 = new Stock(1.0,1L,"TEST1",5.0,5.0,10.0);

//        Company company =new Company("name1", "desc", "sector", "ceo", "address", 10L, s1);
        Mockito.when(stockRepository.findById(anyLong())).thenReturn(Optional.of(s1));

        underTest.getStockById(anyLong());

        Mockito.verify(stockRepository).findById(anyLong());



    }
}