/* создание БД и пользователя. выполняется под администратором */
CREATE USER letter_user with encrypted password 'letter_user';
ALTER USER letter_user WITH SUPERUSER;
CREATE DATABASE letter_dev encoding 'UTF8';


/* следующая часть скрипта выполняется под уже созданным пользователем или существующим пользователем .
под учетной записью которого и будет производится подключение */
/*
Создание tablespace для хранения файлов. Необязательно можно не выполнять
Но тогда следует закоментарить пердпоследнюю строчку
или указать соответствующий путь к директории tablespace
*/
CREATE TABLESPACE tbsp_blob OWNER letter_user LOCATION E'/var/lib/pgsql/9.3/data/pg_tblspc';

DROP TABLE IF EXISTS lt_order CASCADE;

CREATE TABLE lt_order
(
	order_id 		bigserial PRIMARY KEY,
  	order_num 		varchar(20) NOT NULL,
    order_date		date NOT NULL,
    order_name  	varchar(250) NOT NULL,
    order_publish  	smallint NOT NULL CHECK (order_publish = 1 OR order_publish = 0),
    order_desc  	varchar(1000),
    order_scan_type varchar(50) NOT NULL
);

DROP TABLE IF EXISTS lt_order_scan CASCADE;
CREATE TABLE lt_order_scan
(
	order_id 		bigint PRIMARY KEY REFERENCES lt_order (order_id) ON DELETE CASCADE,
    order_scan		bytea NOT NULL
) 
TABLESPACE tbsp_blob
;