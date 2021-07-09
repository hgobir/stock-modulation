package com.fdm.stockmodulation.project.stockmodulation.model;

public class StockData {

    private String symbol;
    private Double value;
    private Long volume;

    public StockData(String symbol, Double value, Long volume) {
        this.symbol = symbol;
        this.value = value;
        this.volume = volume;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Long getVolume() {
        return volume;
    }

    public void setVolume(Long volume) {
        this.volume = volume;
    }

    @Override
    public String toString() {
        return "StockData{" +
                "symbol='" + symbol + '\'' +
                ", value=" + value +
                ", volume=" + volume +
                '}';
    }
}
