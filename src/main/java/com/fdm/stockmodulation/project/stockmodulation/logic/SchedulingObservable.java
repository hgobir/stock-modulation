package com.fdm.stockmodulation.project.stockmodulation.logic;

import com.fdm.stockmodulation.project.stockmodulation.service.StockService;
import com.fdm.stockmodulation.project.stockmodulation.service.StockServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableScheduling
public class SchedulingObservable implements Observable {

    private static final List<Observer> OBSERVERS = new ArrayList<>();

    @Autowired
    public SchedulingObservable(StockService stockService) {
        Observer modulatorObserver = new ModulatorObserver(stockService);
        Observer serverObserver = new ServerObserver(stockService);
        OBSERVERS.add(modulatorObserver);
        OBSERVERS.add(serverObserver);
    }

    @Scheduled(cron = "0 0/1 * * * MON-SUN")
    void modulationTask() { notifyObserver(); }

    @Override
    public void notifyObserver() {
        OBSERVERS.forEach(Observer::update);
    }
}
