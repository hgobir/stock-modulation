package com.fdm.stockmodulation.project.stockmodulation;

import com.fdm.stockmodulation.project.stockmodulation.entity.Company;
import com.fdm.stockmodulation.project.stockmodulation.entity.Stock;
import com.fdm.stockmodulation.project.stockmodulation.repository.CompanyRepository;
import com.fdm.stockmodulation.project.stockmodulation.repository.StockRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.CorsRegistration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@EnableAsync
@EnableTransactionManagement
@EnableScheduling
public class StockModulationApplication  {

//    @Autowired
//    private Environment env;

    public static void main(String[] args) {
        SpringApplication.run(StockModulationApplication.class, args);
    }
//
//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurerAdapter() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                String urls = env.getProperty("cors.urls");
//                CorsRegistration reg = registry.addMapping("/api/**");
//                for(String url: urls.split(",")) {
//                    reg.allowedOrigins(url);
//                }
//            }
//        };
//    }

//    @Bean
//    CommandLineRunner commandLineRunner(StockRepository stockRepository, CompanyRepository companyRepository) {
//        return args -> {
//
//            Stock s = new Stock(
//                    10d,
//                    10L,
//                    10d,
//                    10L,
//                    10d,
//                    10L,
//                    "STK",
//                    10d,
//                    10d,
//                    10d
//                    );
//
//            Company c = new Company(
//                    "test",
//                    "test",
//                    "test",
//                    "test",
//                    "test",
//                    10L,
//                    s
//                    );
//            stockRepository.save(s);
//            companyRepository.save(c);
//        };
//    }
}
