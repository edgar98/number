package com.example.number.generator;

import com.example.number.utils.NumberUtils;

/**
 * Генератор последовательности номерных знаков.
 */
public class NumberGenerator {

    /** Текущее значение числовой части номера */
    private int _currNumber = -1;

    /** Текущее значение буквенной части номера */
    private int _currLetter = 0;

    /**
     * Возвращает признак наличия следующего номера.
     * 
     * @return признак наличия следующего номера
     */
    public boolean hasNext() {
        // Следующий номер доступен если хотя бы одна из частей номера меньше своего предела
        return (_currNumber < LicensePlateConstants.NUMBER_MAX)
                || _currLetter < LicensePlateConstants.LETTER_MAX;
    }

    /**
     * Возвращает следующий номер.
     * 
     * @return следующий номер
     */
    public String next() {
        if (!hasNext()) {
            throw new IllegalStateException("Нет следующего номера");
        }
        _currNumber++;
        if (_currNumber > LicensePlateConstants.NUMBER_MAX) {
            _currLetter++;
            _currNumber = 0;
        }
        return NumberUtils.getFormatted(_currLetter, _currNumber);
    }

}
