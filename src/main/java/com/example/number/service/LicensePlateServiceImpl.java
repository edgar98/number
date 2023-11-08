package com.example.number.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.number.generator.LicensePlateConstants;
import com.example.number.model.LicensePlate;
import com.example.number.repository.LicensePlateRepository;
import jakarta.annotation.PostConstruct;

/**
 * Реализация сервиса выдачи номерных знаков без предварительного заполнения БД.
 */
@Service
public class LicensePlateServiceImpl implements ILicensePlateService {

    @Autowired
    private LicensePlateRepository repository;

    /** Идентификатор последнего выданного знака */
    private LicensePlate lastIssued = null;

    @PostConstruct
    private void loadLastIssuedId() {
        lastIssued = repository.findTopByOrderByIdDesc();
    }

    @Override
    public String getRandomNumber() {
        var plateValue = repository.getRandomLicensePlate(LicensePlateConstants.PLATE_MAX_VALUE);
        lastIssued = repository.save(LicensePlate.builder().value(plateValue).build());
        return "%s 116 RUS".formatted(new LicensePlate(lastIssued.getValue()).getNumber());
    }

    @Override
    public String getNextNumber() {
        Long plateValue;
        if (null == lastIssued) {
            plateValue = 0L;
        } else {
            // В случае, когда значение номера равно максимальному
            // возвращаем первый невыданный номер
            plateValue = repository.getNextAvailable(
                    lastIssued.getValue() == LicensePlateConstants.PLATE_MAX_VALUE ? 0
                            : lastIssued.getValue());
        }
        lastIssued = repository.save(LicensePlate.builder().value(plateValue).build());
        return "%s 116 RUS".formatted(new LicensePlate(lastIssued.getValue()).getNumber());
    }

}
