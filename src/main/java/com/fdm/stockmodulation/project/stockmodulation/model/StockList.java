package com.fdm.stockmodulation.project.stockmodulation.model;

import com.fdm.stockmodulation.project.stockmodulation.entity.Stock;

import java.util.List;
import java.util.Objects;

public class StockList {

    private List<Stock> stockList;

    public StockList() {
    }

    public StockList(List<Stock> stockList) {
        this.stockList = stockList;
    }

    public List<Stock> getStockList() {
        return stockList;
    }

    public void setStockList(List<Stock> stockList) {
        this.stockList = stockList;
    }

    @Override
    public String toString() {
        return "StockList{" +
                "stockList=" + stockList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StockList stockList1 = (StockList) o;
        return Objects.equals(stockList, stockList1.stockList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stockList);
    }
}
