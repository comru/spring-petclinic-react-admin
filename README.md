# Spring PetСlinic React Admin

Этот проект является демонстрацией реализации [Spring (Boot) PetClinic demo](https://github.com/spring-projects/spring-petclinic) с frontend на [React-Admin](https://marmelab.com/react-admin/) и
[TypeScript](https://www.typescriptlang.org/) и разворачиванием приложения в [Yandex Cloud](https://yandex.cloud/ru/). Структура файла README будет сформирована по основным этапам разработки приложения. Каждый пункт оглавления будет сопровождаться ссылкой на комит и кратким описанием. Также, в ходе реализации проекта будут внесены некоторые изменения в базовую функциональность Spring PetClinic. PetClinic скорее взят за основу приложения для демо.

## Оглавление
* [Список используемых инструментов при разработке приложения](#список-используемых-инструментов-при-разработке-приложения)
* [Создание проекта](#создание-проекта) [commit](https://github.com/comru/spring-petclinic-react-admin/commit/74fb6610c336fc4a3778aaad0f2ea0414cbe0149)
* [Создание JPA модели](#создание-jpa-модели)
* [Используемые технологии](#используемые-технологии)

## Список используемых инструментов при разработке приложения
* [Rancher Desktop](https://rancherdesktop.io/) для запуска команд `docker`, `docker-compose`
* [Intellij IDEA Community](https://www.jetbrains.com/idea/download) и [Amplicode](https://amplicode.ru/documentation/installation-guide-intellij/) plugin для IntelliJ для написания backend
* [Visual Studio Code](https://code.visualstudio.com/) и [Amplicode Fullstack](https://amplicode.ru/documentation/installation-guide-vs-code/) extension pack для VS Code для написания frontend

## Создание проекта
Создадим проект с помощью [start.spring.io](https://start.spring.io/). Выберем следующие зависимости и настройки:
* Java 21
* Gradle (groovy)
* Lombok
* Spring Data JPA
* PostgreSQL Driver
* Validation
* Spring Boot DevTools
* Spring Web

Откроем проект в IntelliJ IDEA Community c плагином Amplicode настроим `spring.datasource` в application.properties и создадим docker-compose файл с сервисом postgres. Запустим приложение, убедимся что приложение работает.

## Создание JPA модели
* Настройка JPA(Hibernate) свойств
* Создание JPA модели
* Создание Spring Data JPA repositories

## Используемые технологии
* Spring Boot
* Java 21
* Gradle (groovy)
* Lombok
* Spring Data JPA
* PostgreSQL Driver
* Validation
* Spring Boot DevTools
* Spring Web