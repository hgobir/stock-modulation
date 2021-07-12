package com.fdm.stockmodulation.project.stockmodulation.logic;

import com.fdm.stockmodulation.project.stockmodulation.entity.Stock;
import com.fdm.stockmodulation.project.stockmodulation.service.StockService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(MockitoExtension.class)
class ModulatorObserverTest {


    private ModulatorObserver underTest;

    @Mock
    private StockService stockService;


    @BeforeEach
    void setUp() {
        underTest = new ModulatorObserver(stockService);
    }

    @Test
    void update() {
        List<Stock> stocks = new ArrayList<>();

        stocks.add(new Stock(1.0,1L,"TEST1",5.0,5.0,10.0));
        stocks.add(new Stock(1.0,1L,"TEST2",5.0,5.0,10.0));

        Mockito.when(stockService.getStocks()).thenReturn(stocks);
        underTest.update();

        Mockito.verify(stockService).getStocks();
    }
}