package com.fdm.stockmodulation.project.stockmodulation.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fdm.stockmodulation.project.stockmodulation.entity.Company;
import com.fdm.stockmodulation.project.stockmodulation.entity.Stock;
import com.fdm.stockmodulation.project.stockmodulation.model.ListOfIds;
import com.fdm.stockmodulation.project.stockmodulation.service.CompanyService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;


@ExtendWith(SpringExtension.class)
@WebMvcTest(AdminController.class)
//@AutoConfigureMockMvc(addFilters = false)
//@ContextConfiguration(classes = {RabbitMqConfiguration.class, SecurityConfig.class})
class AdminControllerIntegrationTest {

    private final String URL = "http://localhost:8080/api/v1/stockModulation/admin";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CompanyService companyService;


//    @BeforeEach
//    void setUp() {
//        underTest = new AdminController(companyService);
//    }

//    @AfterEach
//    void tearDown() {
//    }

    @Test
    void getCompanies() throws Exception {

        Stock s1 = new Stock(1.0,1L,"TEST1",5.0,5.0,10.0);

        Company testCompany1 = new Company("name1", "desc", "sector", "ceo", "address", 10L, s1);

        Stock s2 = new Stock(1.0,1L,"TEST2",5.0,5.0,10.0);

        Company testCompany2 = new Company( "name", "desc", "sector", "ceo", "address", 10L, s2);


        List<Company> companyList = new ArrayList<>();
        companyList.add(testCompany1);
        companyList.add(testCompany2);

        Mockito.when(companyService.getCompanies()).thenReturn(companyList);

        RequestBuilder request = MockMvcRequestBuilders.get(URL + "/companies" );

        MvcResult result = mockMvc.perform(request).andReturn();

        assertEquals(200, result.getResponse().getStatus());
        Mockito.verify(companyService).getCompanies();
    }

    @Test
    void getCompany() throws Exception {

        Stock s1 = new Stock(1.0,1L,"TEST1",5.0,5.0,10.0);

        Company testCompany1 = new Company("name1", "desc", "sector", "ceo", "address", 10L, s1);
        Mockito.when(companyService.getCompany(anyLong())).thenReturn(java.util.Optional.of(testCompany1));

        RequestBuilder request = MockMvcRequestBuilders.get(URL + "/companies/" + 1L );

        MvcResult result = mockMvc.perform(request).andReturn();

        assertEquals(200, result.getResponse().getStatus());
        Mockito.verify(companyService).getCompany(anyLong());


    }

    @Test
    void testGetCompanies() throws Exception {

        List<Long> longList = new ArrayList<>();

        longList.add(1L);
        longList.add(2L);
        longList.add(3L);

        ListOfIds listOfIds = new ListOfIds();

        listOfIds.setLongList(longList);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();

        String requestJson =ow.writeValueAsString(listOfIds );


        RequestBuilder request = MockMvcRequestBuilders.post(URL + "/companies/specific").contentType(APPLICATION_JSON).content(requestJson);

        MvcResult result = mockMvc.perform(request).andReturn();

        assertEquals(200, result.getResponse().getStatus());
        Mockito.verify(companyService).getSpecificCompanies(any());

    }

    @Test
    void testCreateCompany() throws Exception {

        Stock s1 = new Stock(1.0,1L,"TEST1",5.0,5.0,10.0);


        Mockito.when(companyService.createCompany(anyString(),anyString(),anyString(),anyString(),anyString() ,anyLong())).thenReturn(s1);

        RequestBuilder request = MockMvcRequestBuilders.post(URL + "/companies/test-name/test-desc/test-sector/test-ceo/test-address/" + 1L );

        MvcResult result = mockMvc.perform(request).andReturn();

        assertEquals(200, result.getResponse().getStatus());
        Mockito.verify(companyService).createCompany(anyString(),anyString(),anyString(),anyString(),anyString() ,anyLong());
    }

    @Test
    void deleteCompany() throws Exception {

        Stock s1 = new Stock(1.0,1L,"TEST1",5.0,5.0,10.0);

        Company testCompany1 = new Company("name1", "desc", "sector", "ceo", "address", 10L, s1);
        Mockito.when(companyService.deleteCompany(anyLong())).thenReturn(true);

        RequestBuilder request = MockMvcRequestBuilders.delete(URL + "/companies/" + 1L );

        MvcResult result = mockMvc.perform(request).andReturn();

        assertEquals(200, result.getResponse().getStatus());
        Mockito.verify(companyService).deleteCompany(anyLong());
    }

    @Test
    void updateCompanyDetails() throws Exception {



        Stock s1 = new Stock(1.0,1L,"TEST1",5.0,5.0,10.0);

        Company testCompany1 = new Company("name1", "desc", "sector", "ceo", "address", 10L, s1);

        Mockito.when(companyService.updateCompany(anyLong(),anyString(),anyString(),anyString(),anyString(),anyString() ,anyLong())).thenReturn(testCompany1);

        RequestBuilder request = MockMvcRequestBuilders.put(URL + "/companies/" + 1L+ "/test-name/test-desc/test-sector/test-ceo/test-address/" + 100000L );

        MvcResult result = mockMvc.perform(request).andReturn();

        assertEquals(200, result.getResponse().getStatus());
        Mockito.verify(companyService).updateCompany(anyLong(),anyString(),anyString(),anyString(),anyString(),anyString() ,anyLong());
    }






}