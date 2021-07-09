package com.fdm.stockmodulation.project.stockmodulation.logic;

import com.fdm.stockmodulation.project.stockmodulation.entity.Stock;
import com.fdm.stockmodulation.project.stockmodulation.model.StockData;
import com.fdm.stockmodulation.project.stockmodulation.model.StockDataList;
import com.fdm.stockmodulation.project.stockmodulation.model.StockList;
import com.fdm.stockmodulation.project.stockmodulation.service.StockService;
import com.fdm.stockmodulation.project.stockmodulation.util.StockToStockDataConverter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/stockModulation/serverClient")
public final class ServerObserver implements Observer {

    public static boolean STOCKS_PERSISTED_FLAG = false;
    private static final String SERVER_URL = "http://localhost:8081/api/v1/server/stockModulation/currentStockPrices";
    private final StockService stockService;

    public ServerObserver(StockService stockService) {
        this.stockService = stockService;
    }

    @Override
    @PostMapping(path = "/update")
    public void update() {
        while (STOCKS_PERSISTED_FLAG) {
            List<Stock> updatedStocks = stockService.getStocks();
            StockList stockList = new StockList(updatedStocks);
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.postForObject(SERVER_URL, stockList, StockList.class);
            System.out.println("data sent!");
            STOCKS_PERSISTED_FLAG = false;
        }
    }


}
