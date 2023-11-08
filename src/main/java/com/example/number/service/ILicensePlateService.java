package com.example.number.service;

/**
 * Контракт сервиса выдачи номерных знаков.
 */
public interface ILicensePlateService {

    /**
     * Возвращает случайный номерной знак.
     * 
     * @return случайный номерной знак
     */
    String getRandomNumber();

    /**
     * Возвращает следующий номерной знак.
     * 
     * @return возвращает следующий номерной знак
     */
    String getNextNumber();

}
