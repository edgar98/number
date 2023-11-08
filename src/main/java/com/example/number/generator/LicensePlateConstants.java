package com.example.number.generator;

import java.util.Map;
import lombok.experimental.UtilityClass;

/**
 * Константы параметров номерного знака.
 */
@UtilityClass
public class LicensePlateConstants {

    /** Словарь преобразования цифр в буквы */
    public static final Map<Character, String> LETTERS_MAP = Map.ofEntries(Map.entry('0', "А"),
            Map.entry('1', "В"), Map.entry('2', "Е"), Map.entry('3', "К"), Map.entry('4', "М"),
            Map.entry('5', "Н"), Map.entry('6', "О"), Map.entry('7', "Р"), Map.entry('8', "С"),
            Map.entry('9', "Т"), Map.entry('a', "У"), Map.entry('b', "Х"));

    /** Максимальное значение числовой части номера */
    public static final int NUMBER_MAX = 999;

    /** Количетво букв в номере */
    public static final byte LETTER_PART_LENGHT = 3;

    /** Максимальное значение буквенной части номера */
    public static final int LETTER_MAX = Integer.parseInt("BBB", LETTERS_MAP.size());

    /** Максимальное численное значение номера */
    public static final int PLATE_MAX_VALUE = (NUMBER_MAX + 1) * (LETTER_MAX + 1) - 1;

}
