* JDK 1.7
* TOMCAT 7
* postgresql 9.3
* Spring 3.2.9
* Hibernate 4.1.9
* IDE Intellij IDEA 13.1

Обоснование хранения файлов в БД

Некоторые базы позволяют хранить BLOB данные в отдельном дисковом пространстве, 
что позволяет распределить нагрузку при чтении/записи BLOB полей.
Есть возможность партицирования такой таблицы.
Можно использовать вариант хранения в другой БД и работой через db_link (в разных БД это название может отличаться).
для этого в задании я вынес хранение BLOB полей в отдельную таблицу.
в зависимости от выбранной БД, возможна настройка хранения этой таблицы.


Решается вопрос с доступом к BLOB данным с помощью ролей,
один подход с предоставлением доступа. 

нет необходимости решать проблемы ссылок на файлы в файловой системе, при переезде на другой сервер.

в файловой системе необходимо будет дополнительно продублировать предоставление доступа.
минус - отсутствие транзакционности при работе с файловой системой.
в файловой системе есть возможность заражения вирусами.

в своем решении я произвел денормализацию таблицы приказов и разбил на две таблицы.
во второй таблице хранятся сканы приказов, эта таблица находится в другом tablespace - 
как пример возможного распределения нагрузки на диск.


Установка
Для создание БД скрипт
src\main\db_scripts\create_db.sql
В нем все комментарии по созданию БД

После развертывания на tomcate следует изменить параметры подключения к БД в файле 
WEB-INF\classes\jdbc.properties
на данный момент используется подключение к существующей БД, она вида в инете и таблицы пустые