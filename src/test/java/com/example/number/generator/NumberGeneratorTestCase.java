package com.example.number.generator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;
import org.junit.jupiter.api.Test;

public class NumberGeneratorTestCase {

    @Test
    public void testNumberGeneratorFirstNumber() throws Exception {
        NumberGenerator ng = new NumberGenerator();
        assertThat(ng.next()).isEqualTo("А000АА");
    }

    @Test
    public void testNumberGeneratorLetterChange() throws Exception {
        NumberGenerator ng = new NumberGenerator();
        for (var i = 0; i < 1000; i++) {
            ng.next();
        }
        assertThat(ng.next()).isEqualTo("А000АВ");
    }

    @Test
    public void testNumberGeneratorLastNumber() throws Exception {
        NumberGenerator ng = new NumberGenerator();
        var number = "";
        while (!number.equals("Х999ХХ")) {
            number = ng.next();
        }
        assertThat(ng.hasNext()).isFalse();
    }

    @Test
    public void testNumberGeneratorNextAfterLastNumber() throws Exception {
        NumberGenerator ng = new NumberGenerator();
        var number = "";
        while (!number.equals("Х999ХХ")) {
            number = ng.next();
        }
        assertThat(ng.hasNext()).isFalse();
        assertThatIllegalStateException().isThrownBy(ng::next);
    }

}
