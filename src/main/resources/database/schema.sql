-- Создание таблицы для хранения выданных номеров
create table if not exists "public"."issued_license_plate"
(
    "id" bigint not null primary key generated by default as identity,
    "plate_value" bigint unique not null
);