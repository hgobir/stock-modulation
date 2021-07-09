package com.fdm.stockmodulation.project.stockmodulation.service;

import com.fdm.stockmodulation.project.stockmodulation.entity.Stock;
import java.util.List;
import java.util.Optional;

public interface StockService {

    List<Stock> getStocks();

    Stock saveStock(Stock stock);

    Optional<Stock> getStockById(Long stockId);

}
