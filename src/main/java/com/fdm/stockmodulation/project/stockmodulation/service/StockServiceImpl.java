package com.fdm.stockmodulation.project.stockmodulation.service;

import com.fdm.stockmodulation.project.stockmodulation.entity.Stock;
import com.fdm.stockmodulation.project.stockmodulation.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;

    @Autowired
    public StockServiceImpl(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Stock> getStocks() {
        return stockRepository.findAll();
    }

    @Override
    @Transactional
    public Stock saveStock(Stock stock) {
        if(stock.getStockId() == null) {
            long stockNumber = stockRepository.findAll().size();
            Long newStockId = stockNumber + 1;
            stock.setStockId(newStockId);
        }
        return stockRepository.saveAndFlush(stock);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Stock> getStockById(Long stockId) {
        return stockRepository.findById(stockId);
    }

}
