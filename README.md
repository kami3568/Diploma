[![Build status](https://ci.appveyor.com/api/projects/status/5y09h5acm5genwyg/branch/main?svg=true)](https://ci.appveyor.com/project/kamizinatulina/diploma/branch/main)

**Запуск тестов:**
* Скачать код проекта с репозитория
* Запустить Docker Desktop
* Открыть терминал в папке с проектом
* Выполнить команду docker-compose up
* Запустить SUT aqa-shop.jar командой java -jar aqa-shop/aqa-shop.jar &
* Запустить тесты командой gradlew test

**Отчёты Allure:**
* Создать отчёты Allure и открыть в браузере
./gradlew allureReport allureServe

По умолчанию подключается MySQL

**Для работы с PostgreSQL:**
* перезапустить SUT java -jar aqa-shop/aqa-shop.jar --spring.datasource.url=jdbc:postgresql://localhost:5432/app &
* запустить тесты, используя PostgreSQL ./gradlew clean test -DdataBase.url=jdbc:postgresql://localhost:5432/app -Dusername=app -Dpassword=pass --info
