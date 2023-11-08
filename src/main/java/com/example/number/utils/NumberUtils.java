package com.example.number.utils;

import com.example.number.generator.LicensePlateConstants;
import lombok.experimental.UtilityClass;

/**
 * Вспомогательные инструменты преобразования значений номеров.
 */
@UtilityClass
public class NumberUtils {

    /**
     * Возвращает строку номерного знака в формате знаков РФ (А111АА).
     * 
     * @param letters числовое значение букв номера
     * @param numbers числовое значение цифр номера
     * @return форматированная строка номерного знака
     */
    public String getFormatted(int letters, int numbers) {
        var lettersString = lettersIntToAlphabetBase(letters);
        return new StringBuilder()
                .append(LicensePlateConstants.LETTERS_MAP.get(lettersString.charAt(0)))
                .append("%03d".formatted(numbers))
                .append(LicensePlateConstants.LETTERS_MAP.get(lettersString.charAt(1)))
                .append(LicensePlateConstants.LETTERS_MAP.get(lettersString.charAt(2))).toString();
    }

    /**
     * Преобразует числовое значение буквенной части в систему счисления с основанием, равным
     * размеру алфавита.
     * 
     * @param intValue числовое значение буквенной части номера
     * @return число в системе счисления с основанием, равным размеру алфавита
     */
    public String lettersIntToAlphabetBase(int intValue) {
        var res = Integer.toString(intValue, LicensePlateConstants.LETTERS_MAP.size());

        // Дополняем предшествующими нулями до длины 3
        while (res.length() < LicensePlateConstants.LETTER_PART_LENGHT) {
            res = "0" + res;
        }
        return res;
    }

    /**
     * Возвращает номерной знак с кодом региона Татарстан (116 RUS).
     * 
     * @param number номерной знак без указания региона
     * @return номерной знак с кодом региона
     */
    public String toTatarRegionLicensePlate(String number) {
        return "%s 116 RUS".formatted(number);
    }

}
