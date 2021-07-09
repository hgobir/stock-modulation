package com.fdm.stockmodulation.project.stockmodulation.controller;

import com.fdm.stockmodulation.project.stockmodulation.entity.Company;
import com.fdm.stockmodulation.project.stockmodulation.entity.Stock;
import com.fdm.stockmodulation.project.stockmodulation.model.CompanyList;
import com.fdm.stockmodulation.project.stockmodulation.model.ListOfIds;
import com.fdm.stockmodulation.project.stockmodulation.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/stockModulation/admin")
public class AdminController {

    /*
        todo - dont forget to write about this insane complication of getting company name while building the transaction object in server
        having to build another service, potentially securing stock modulator project
        starting off with complications using promises in front end then giving up to configure in backend

     */

    private final CompanyService companyService;

    @Autowired
    public AdminController(CompanyService companyService) {
        this.companyService = companyService;
    }

//    @CrossOrigin
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(value = "/companies" )
    public List<Company> getCompanies() {
        final List<Company> companies = companyService.getCompanies();
        System.out.println("api has been hit!");
        System.out.println("below is list of companies");
        companies.forEach(company -> {
            System.out.println(company.toString());
        });
        return companies;
    }

    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:8081"})
    @GetMapping(value = "/companies/{companyId}")
    public Company getCompany(@PathVariable("companyId") Long companyId) {
        System.out.println("admin controller in stock modulation project hit!!");
        System.out.println("companyId is " + companyId);
        return companyService.getCompany(companyId).get();
    }

    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:8081"})
    @PostMapping(value = "/companies/specific")
    public CompanyList getCompanies(@RequestBody ListOfIds companyIds) {
        System.out.println("admin controller in stock modulation project hit!!");
        System.out.println("number of ids is " + companyIds.getLongList().size());

        companyIds.getLongList().forEach(companyId -> System.out.println("this is one of stock ids about to send ["+companyId+"]"));

        List<Long> listOfCompanyIds = companyIds.getLongList();
        List<Company> specificCompanies = companyService.getSpecificCompanies(listOfCompanyIds);

        System.out.println("list of specific companies received!! - number is [" + specificCompanies.size() + "]" );


        CompanyList companyList = new CompanyList(specificCompanies);

        return companyList;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(value = "/companies/{name}/{description}/{sector}/{ceo}/{address}/{valuation}")
    public Stock createCompany(@PathVariable("name") String name,
                               @PathVariable("description") String description,
                               @PathVariable("sector") String sector,
                               @PathVariable("ceo") String ceo,
                               @PathVariable("address") String address,
                               @PathVariable("valuation") Long valuation) {
        System.out.println("create company api hit! ");
        return companyService.createCompany(name, description, sector, ceo, address, valuation);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping(value = "/companies/{companyId}")
    public boolean deleteCompany(@PathVariable("companyId") Long companyId) {
        System.out.println("delete company api hit! - id is " + companyId);
        return companyService.deleteCompany(companyId);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping(value = "/companies/{companyId}/{name}/{description}/{sector}/{ceo}/{address}/{valuation}")
    public Company updateCompanyDetails(@PathVariable("companyId") Long companyId,
                                        @PathVariable("name") String name,
                                        @PathVariable("description") String description,
                                        @PathVariable("sector") String sector,
                                        @PathVariable("ceo") String ceo,
                                        @PathVariable("address") String address,
                                        @PathVariable("valuation") Long valuation) {
        return companyService.updateCompany(companyId, name, description, sector, ceo, address, valuation);
    }





}
