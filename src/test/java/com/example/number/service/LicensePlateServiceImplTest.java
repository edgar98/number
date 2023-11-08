package com.example.number.service;

import static org.assertj.core.api.Assertions.assertThat;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import com.example.number.NumberGeneratorApplication;
import com.example.number.generator.LicensePlateConstants;
import com.example.number.generator.NumberGenerator;
import com.example.number.model.LicensePlate;
import com.example.number.repository.LicensePlateRepository;
import com.example.number.utils.NumberUtils;

@SpringBootTest
@ContextConfiguration(classes = NumberGeneratorApplication.class)
@ActiveProfiles("test")
public class LicensePlateServiceImplTest {

    @Autowired
    ILicensePlateService service;

    @Autowired
    LicensePlateRepository repository;

    @BeforeEach
    void cleanData() {
        repository.deleteAll();
    }

    @Test
    void testGetNextNumber() {
        var firstNumber = service.getNextNumber().split(" ")[0];
        var ng = new NumberGenerator();
        String expected = "";
        while (!expected.equals(firstNumber)) {
            expected = ng.next();
        }
        expected = ng.next();
        var nextNumber = service.getNextNumber();
        assertThat(nextNumber).isEqualTo(NumberUtils.toTatarRegionLicensePlate(expected));
    }

    @Test
    void testGetRandomNumber() {
        assertThat(service.getRandomNumber())
                .matches("^[УКЕНХВАРОСМТ][0-9]{3}[УКЕНХВАРОСМТ]{2} 116 RUS$");
    }

    void testGetNextNumberAfterLast() {
        repository
                .save(LicensePlate.builder().value(LicensePlateConstants.PLATE_MAX_VALUE).build());
        assertThat(repository.findTopByOrderByIdDesc())
                .extracting(LicensePlate::getValue, InstanceOfAssertFactories.INTEGER)
                .isEqualTo(LicensePlateConstants.PLATE_MAX_VALUE);
        assertThat(service.getNextNumber()).isEqualTo("А000АА 116 RUS");
    }

}
