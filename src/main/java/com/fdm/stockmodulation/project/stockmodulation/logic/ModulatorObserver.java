package com.fdm.stockmodulation.project.stockmodulation.logic;

import com.fdm.stockmodulation.project.stockmodulation.entity.Stock;
import com.fdm.stockmodulation.project.stockmodulation.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.LocalTime;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Predicate;

@Component
public final class ModulatorObserver implements Observer {

    private static final LocalTime START_TIME = LocalTime.parse("08:00");
    private static final LocalTime END_TIME = LocalTime.parse("16:30");
    private static final double MAX_VALUE = 550;
    private static final double MIN_VALUE = 5;
    private static final int MAX_VOLUME = 1_000_000_000;
    private static final int MIN_VOLUME = 2_000_000;
    private final StockService stockService;

    @Autowired
    public ModulatorObserver(StockService stockService) {
        this.stockService = stockService;
    }

    @Override
    public void update() {

        List<Stock> stocks = stockService.getStocks();
        //todo - need to revisit modulation, can go beyond/below max/min volume/value its just on generation (initial creation) they have to be within those parameters
        stocks.forEach(stock -> {
            int calculatedVolumeToSave = calculateRandomVolumeFunction.apply(MAX_VOLUME, MIN_VOLUME);
            double calculatedValueToSave = calculateRandomValueFunction.apply(MAX_VALUE, MIN_VALUE);
            boolean isOpen = calculateOpenOrClosed.test(START_TIME);
            boolean isClosed = calculateOpenOrClosed.test(END_TIME);
            stock.setCurrentVolume(calculatedVolumeToSave);
            stock.setCurrentValue(calculatedValueToSave);
            if (isOpen) {
                stock.setOpen(calculatedValueToSave);
            } else if(isClosed) {
                stock.setClose(calculatedValueToSave);
            }
            double gains = calculatedValueToSave - stock.getOpen();
            stock.setGains(gains);
            stockService.saveStock(stock);
        });

        ServerObserver.STOCKS_PERSISTED_FLAG = true;
    }


    static BiFunction<Integer, Integer, Integer> calculateRandomVolumeFunction = (maxVolume, minVolume) ->  (int) (Math.random() * (maxVolume - minVolume) + 1) + minVolume;

    static BiFunction<Double, Double, Double> calculateRandomValueFunction = (maxValue, minValue) -> (Math.random() * (maxValue - minValue) + 1) + minValue;

    static Predicate<LocalTime> calculateOpenOrClosed = localTime -> LocalTime.now().equals(localTime);
}
