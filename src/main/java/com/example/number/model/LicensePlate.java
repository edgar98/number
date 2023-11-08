package com.example.number.model;

import com.example.number.utils.NumberUtils;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Сущность номерного знака. <br>
 * Для хранения значения номера используется число, оно же является первичным ключом. <br>
 * В БД хранятся только выданные номера
 */
@Data
@NoArgsConstructor
@Builder
@Entity
@Table(name = "issued_license_plate")
public class LicensePlate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /** Числовое значение номера */
    @Column(name = "plate_value")
    private long value;

    /** Строковое представление номера */
    @Transient
    private String number;

    /**
     * Конструктор.
     * 
     * @param id числовое значение номера
     * @param value cтроковое представление номера
     */
    public LicensePlate(long id, long value, String number) {
        this(value);
    }


    /**
     * Конструктор.
     * 
     * @param value числовое значение номера
     */
    public LicensePlate(long value) {
        this.value = value;
        int letterPart = (int) (this.value / 1000);
        int digitPart = (int) this.value % 1000;
        number = NumberUtils.getFormatted(letterPart, digitPart);
    }

}
