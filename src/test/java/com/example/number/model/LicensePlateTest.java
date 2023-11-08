package com.example.number.model;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import com.example.number.generator.LicensePlateConstants;

public class LicensePlateTest {

    @Test
    void testLicencePlateParsing() {
        var plateA000AA = LicensePlate.builder().value(0).build();
        var plateX999XX =
                LicensePlate.builder().value(LicensePlateConstants.PLATE_MAX_VALUE).build();

        assertThat(plateA000AA).extracting(LicensePlate::getNumber).isEqualTo("А000АА");
        assertThat(plateX999XX).extracting(LicensePlate::getNumber).isEqualTo("Х999ХХ");
    }
}
