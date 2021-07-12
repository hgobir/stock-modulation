package com.fdm.stockmodulation.project.stockmodulation.logic;

import com.fdm.stockmodulation.project.stockmodulation.entity.Company;
import com.fdm.stockmodulation.project.stockmodulation.entity.Stock;
import com.fdm.stockmodulation.project.stockmodulation.model.StockList;
import com.fdm.stockmodulation.project.stockmodulation.service.StockService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ServerObserverTest {

    public static boolean STOCKS_PERSISTED_FLAG = true;
    private static final String SERVER_URL = "http://localhost:8081/api/v1/server/stockModulation/currentStockPrices";
//    private final StockService stockService;


//    @Autowired
    private TestRestTemplate testRestTemplate;


    private ServerObserver underTest;

    @Mock
    private StockService stockService;

    @BeforeEach
    void setUp() {
        underTest = new ServerObserver(stockService);
        testRestTemplate = new TestRestTemplate();
    }

    @Test
    void update() {

        List<Stock> stocks = new ArrayList<>();

        stocks.add(new Stock(1L, 1.0,1L,"TEST1",5.0,5.0,10.0));
//        stocks.add(new Stock(1.0,1L,"TEST2",5.0,5.0,10.0));

        StockList stockList = new StockList();
        stockList.setStockList(stocks);

//        Mockito.when(stockService.getStocks()).thenReturn(stocks);

        testRestTemplate.postForObject( "http://localhost:8081/api/v1/server/stockModulation/currentStockPrices" , stockList,StockList.class);

        underTest.update();

//        Mockito.verify(stockService).getStocks();


    }
}