package com.example.number.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.number.service.ILicensePlateService;
import io.swagger.annotations.ApiOperation;

/**
 * REST контроллер генерации номерных знаков.
 */
@RestController
public class LicensePlateController {

    /** Сервис выдачи номерных знаков */
    @Autowired
    private ILicensePlateService _licensePlateService;

    /**
     * Возвращает следующий номерной знак.
     * 
     * @return следующий номерной знак
     */
    @ApiOperation(value = "Получить следующий номерной знак")
    @GetMapping(value = "/next", produces = "text/plain")
    private String getNextNumber() {
        return _licensePlateService.getNextNumber();
    }

    /**
     * Возвращает случайный номерной знак.
     * 
     * @return случайный номерной знак
     */
    @ApiOperation(value = "Получить случайный номерной знак")
    @GetMapping(value = "/random", produces = "text/plain")
    private String getRandomNumber() {
        return _licensePlateService.getRandomNumber();
    }

}
