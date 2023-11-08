package com.example.number.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.number.model.LicensePlate;

/**
 * Репозиторий номерных знаков.
 */
@Repository
public interface LicensePlateRepository extends JpaRepository<LicensePlate, Long> {

    /**
     * Возвращает случайный номер, которого нет в базе, с чиловым значением не больше указанного.
     * 
     * @param maxValue максимальное числовое значение номера
     * @return случайный номер
     */
    @Query(value = """
            select series as "id"
            from generate_series(0, ?1, 1) t(series)
            left join issued_license_plate on series = issued_license_plate.plate_value
            where id is null
            order by random()
            limit 1
            """, nativeQuery = true)
    Long getRandomLicensePlate(long maxValue);

    /**
     * Возвращает следующий номер после указанного.
     * 
     * @param value числовое значение номера
     * @return следующий номер
     */
    @Query(value = """
            select t1.plate_value + 1 as "plate_value"
            from issued_license_plate t1
            where not exists (
                select null
                from issued_license_plate t2
                where t2.plate_value = t1.plate_value + 1)
                and t1.plate_value >= ?1
            order by
                plate_value
            limit 1
                """, nativeQuery = true)
    Long getNextAvailable(long value);

    /**
     * Возвращает последний выданный номер (номер с максимальным идентификатором).
     * 
     * @return последний выданный номер
     */
    LicensePlate findTopByOrderByIdDesc();

}
