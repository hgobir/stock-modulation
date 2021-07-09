package com.fdm.stockmodulation.project.stockmodulation.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity(name = "Stock")
@Table(
        name = "stock"
//        uniqueConstraints = {
//                @UniqueConstraint(name="stock_symbol_unique", columnNames = "symbol")
//        }
)
public class Stock {

    @Id
    @Column(
            name = "stock_id",
            nullable = false,
            unique = true,
            columnDefinition = "bigint"
    )
    private Long stockId;

    @Column(
            name = "current_value",
            columnDefinition = "double precision"
    )
    private Double currentValue;

    @Column(
            name = "current_volume",
            columnDefinition = "bigint"
    )
    private Long currentVolume;

    @Column(
            name = "gains",
            columnDefinition = "double precision"
    )
    private Double gains;

    @Column(
            name = "open",
            columnDefinition = "double precision"
    )
    private Double open;

    @Column(
            name = "close",
            columnDefinition = "double precision"
    )
    private Double close;

    @Column(
            name = "symbol",
            nullable = false,
            columnDefinition = "text"
    )
    private String symbol;

    @OneToOne(
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @Transient
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Company company;

//    todo: when currentVolume is 0 need to deactive stock!
    @Column(
            name = "active",
            columnDefinition = "boolean"
    )
    private Boolean active;

    public Stock(
            Double currentValue,
            Long currentVolume,
            String symbol,
            Double gains,
            Double open,
            Double close
    ) {
        this.currentValue = currentValue;
        this.currentVolume = currentVolume;
        this.symbol = symbol;
        this.gains = gains;
        this.open = open;
        this.close = close;
    }


//    public Stock(
//            Double currentValue,
//            Long currentVolume,
//            String symbol,
//            Double gains,
//            Double open,
//            Double close,
//            Company company
//    ) {
//        this.currentValue = currentValue;
//        this.currentVolume = currentVolume;
//        this.symbol = symbol;
//        this.gains = gains;
//        this.open = open;
//        this.close = close;
//        this.company = company;
//    }

    public Stock() { }

    public Long getStockId() {
        return stockId;
    }

    public void setStockId(Long stockId) { this.stockId = stockId; }

    public Double getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(double currentValue) {
        this.currentValue = currentValue;
    }

    public Long getCurrentVolume() { return currentVolume; }

    public void setCurrentVolume(long currentVolume) {
        this.currentVolume = currentVolume;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) { this.symbol = symbol; }

    public Double getGains() {
        return gains;
    }

    public void setGains(double gains) {
        this.gains = gains;
    }

    public Double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public Double getClose() {
        return close;
    }

    public void setClose(double close) {
        this.close = close;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "stockId=" + stockId +
                ", currentValue=" + currentValue +
                ", currentVolume=" + currentVolume +
                ", gains=" + gains +
                ", open=" + open +
                ", close=" + close +
                ", symbol='" + symbol + '\'' +
                ", company=" + company +
                ", active=" + active +
                '}';
    }
}
