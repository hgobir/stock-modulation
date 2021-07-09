package com.fdm.stockmodulation.project.stockmodulation.util;

import com.fdm.stockmodulation.project.stockmodulation.entity.Stock;
import com.fdm.stockmodulation.project.stockmodulation.model.StockData;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class StockToStockDataConverter {

    public List<StockData> convert(List<Stock> updatedStocks) {

        return Collections.unmodifiableList(updatedStocks.stream()
                .map(stock -> new StockData(stock.getSymbol(), stock.getCurrentValue(), stock.getCurrentVolume()))
                .collect(Collectors.toList()));
    }
}
