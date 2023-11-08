package com.example.number.controller;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import com.example.number.service.ILicensePlateService;

@WebMvcTest(LicensePlateController.class)
public class LicensePlateControllerTest {

    public static String NEXT_PLATE = "А000АА 116 RUS";
    public static String RANDOM_PLATE = "А123АА 116 RUS";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ILicensePlateService service;

    @Test
    void testGetNextNumber() throws Exception {
        when(service.getNextNumber()).thenReturn(NEXT_PLATE);
        this.mockMvc.perform(get("/next")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString(NEXT_PLATE)));
    }

    @Test
    void testGetRandomNumber() throws Exception {
        when(service.getRandomNumber()).thenReturn(RANDOM_PLATE);
        this.mockMvc.perform(get("/random")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString(RANDOM_PLATE))); 
    }

}
