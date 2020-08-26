package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ResumeTestData {
    public static void main(String[] args) {
        Resume resume = createResume("uuid1", "Grigory Kislin");

        for(Map.Entry<ContactType, String> contact : resume.getContacts().entrySet()) {
            System.out.println(contact.getKey().getTitle() + ": " + contact.getValue());
        }

        System.out.println("\n------------------------------------------------------------");

        for(Map.Entry<SectionType, AbstractSection> section : resume.getSections().entrySet()) {
            SectionType sectionType = section.getKey();
            AbstractSection sectionValue = section.getValue();
            System.out.println(sectionType + " / " + sectionType.getTitle());
            switch (sectionType) {
                case PERSONAL:
                case OBJECTIVE:
                    System.out.println(((TextSection) sectionValue).getText());
                    break;
                case ACHIEVEMENT:
                case QUALIFICATIONS:
                    ((ListSection) sectionValue).getItems().forEach(System.out::println);
                    break;
                case EXPERIENCE:
                case EDUCATION:
                    ((OrganizationSection) sectionValue).getOrganizations().forEach(System.out::println);
                    break;
                default:
                    System.out.println("No data in resume");
                    break;
            }
            System.out.println("===========");
        }

    }

    public static Resume createResume(String uuid, String fullName) {
        Resume resume = new Resume(uuid, fullName);
        List<String> achievement = new ArrayList<>();
        List<String> qualifications = new ArrayList<>();
        List<Organization> experience = new ArrayList<>();
        List<Organization> education = new ArrayList<>();

        TextSection objectiveSection = new TextSection("");
        TextSection personalSection = new TextSection("");
        ListSection achievementSection = new ListSection(achievement);
        ListSection qualificationsSection = new ListSection(qualifications);
        OrganizationSection experienceSection = new OrganizationSection(experience);
        OrganizationSection educationSection = new OrganizationSection(education);

        resume.addContact(ContactType.MOBILE_PHONE, "+7(921) 855-0482");
        resume.addContact(ContactType.SKYPE, "grigory.kislin");
        resume.addContact(ContactType.EMAIL, "gkislin@yandex.ru");
        resume.addContact(ContactType.LINKEDIN,"https://www.linkedin.com/in/gkislin");
        resume.addContact(ContactType.GITHUB, "https://github.com/gkislin");
        resume.addContact(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473");
        resume.addContact(ContactType.HOME_PAGE, "http://gkislin.ru");

        achievement.add("С 2013 года: разработка проектов \"Разработка " +
                "Web приложения\", \"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). " +
                "Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок " +
                "и ведение проектов. Более 1000 выпускников.");
        achievement.add("Реализация двухфакторной аутентификации для онлайн платформы " +
                "управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.");
        achievement.add("Налаживание процесса разработки и непрерывной интеграции " +
                "ERP системы River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения " +
                "управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и " +
                "авторизации различных ERP модулей, интеграция CIFS/SMB java сервера.");
        achievement.add("Реализация c нуля Rich Internet Application приложения " +
                "на стеке технологий JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, " +
                "Highstock для алгоритмического трейдинга.");
        achievement.add("Создание JavaEE фреймворка для отказоустойчивого " +
                "взаимодействия слабо-связанных сервисов (SOA-base архитектура, JAX-WS, JMS, AS Glassfish). " +
                "Сбор статистики сервисов и информации о состоянии через систему мониторинга Nagios. Реализация " +
                "онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django).");
        achievement.add("Реализация протоколов по приему платежей всех основных " +
                "платежных системы России (Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа.");
        qualifications.add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        qualifications.add("Version control: Subversion, Git, Mercury, ClearCase, Perforce");
        qualifications.add("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle");
        qualifications.add("MySQL, SQLite, MS SQL, HSQLDB");
        qualifications.add("Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy");
        qualifications.add("XML/XSD/XSLT, SQL, C/C++, Unix shell scripts");
        qualifications.add("Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, " +
                "Spring (MVC, Security, Data, Clouds, Boot), JPA (Hibernate, EclipseLink), Guice, " +
                "GWT(SmartGWT, ExtGWT/GXT), Vaadin, Jasperreports, Apache Commons, Eclipse SWT, JUnit, " +
                "Selenium (htmlelements).");
        qualifications.add("Python: Django");
        qualifications.add("JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js");
        qualifications.add("Scala: SBT, Play2, Specs2, Anorm, Spray, Akka");
        qualifications.add("Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, " +
                "StAX, SAX, DOM, XSLT, MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, CMIS, " +
                "BPMN2, LDAP, OAuth1, OAuth2, JWT");
        qualifications.add("Инструменты: Maven + plugin development, Gradle, настройка Ngnix");
        qualifications.add("администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, " +
                "Nagios, iReport, OpenCmis, Bonita, pgBouncer.");
        qualifications.add("Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, " +
                "архитектурных шаблонов, UML, функционального программирования");
        qualifications.add("Родной русский, английский \"upper intermediate\"");
        experience.add(new Organization("Java Online Projects","http://javaops.ru", new Organization.Position(LocalDate.of(2013, 10,1), LocalDate.of(2020, 7, 30),
                 "Автор проекта", "Создание, организация и проведение Java онлайн проектов и стажировок.")));
        experience.add(new Organization("Wrike", "https://www.wrike.com",new Organization.Position(LocalDate.of(2014, 10, 1), LocalDate.of(2016, 1, 31),
                "Старший разработчик (backend)", "Проектирование и разработка " +
                "онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, " +
                "PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.")));
        experience.add(new Organization("RIT Center", "", new Organization.Position(LocalDate.of(2012, 4, 1), LocalDate.of(2014, 10, 31),
                "Java архитектор", "Организация процесса разработки системы ERP для разных окружений: " +
                "релизная политика, версионирование, ведение CI (Jenkins), миграция базы (кастомизация Flyway), " +
                "конфигурирование системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части системы. " +
                "Разработка интергационных сервисов: CMIS, BPMN2, 1C (WebServices), " +
                "сервисов общего назначения (почта, экспорт в pdf, doc, html). " +
                "Интеграция Alfresco JLAN для online редактирование из браузера документов MS Office. " +
                "Maven + plugin development, Ant, Apache Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, " +
                "OpenCmis, Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python.")));
        experience.add(new Organization("Luxoft (Deutsche Bank)", "http://www.luxoft.ru", new Organization.Position(LocalDate.of(2010, 12, 1),LocalDate.of(2012, 4, 30),
                "Ведущий программист", "Участие в проекте Deutsche Bank CRM (WebLogic, Hibernate," +
                "Spring, Spring MVC, SmartGWT, GWT, Jasper, Oracle). Реализация клиентской и серверной части CRM. " +
                "Реализация RIA-приложения для администрирования, мониторинга и анализа результатов " +
                "в области алгоритмического трейдинга. JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Highstock, Commet, HTML5.")));
        experience.add(new Organization("Yota", "https://www.yota.ru", new Organization.Position(LocalDate.of(2008, 6, 1), LocalDate.of(2010, 12, 31),
                "Ведущий специалист", "Дизайн и имплементация Java EE фреймворка " +
                "для отдела \"Платежные Системы\" (GlassFish v2 .1, v3, OC4J, EJB3, JAX - WS RI 2.1, Servlet 2.4, JSP, " +
                "JMX, JMS, Maven2). Реализация администрирования, статистики и мониторинга фреймворка. " +
                "Разработка online JMX клиента (Python / Jython, Django, ExtJS)")));
        experience.add(new Organization("Enkata", "http://enkata.com", new Organization.Position(LocalDate.of(2007, 3, 1), LocalDate.of(2008, 6, 30),
                "Разработчик ПО", "Реализация клиентской (Eclipse RCP) " +
                "и серверной (JBoss 4.2, Hibernate 3.0, Tomcat, JMS)частей кластерного J2EE приложения (OLAP, Data mining)")));
        experience.add(new Organization("Siemens AG", "https://www.siemens.com/ru/ru/home.html", new Organization.Position(LocalDate.of(2005, 1, 1), LocalDate.of(2007, 2, 28),
                "Разработчик ПО", "Разработка информационной модели, проектирование интерфейсов," +
                "реализация и отладка ПО на мобильной IN платформе Siemens @vantage(Java, Unix).")));
        experience.add(new Organization("Alcatel", "http://www.alcatel.ru",new Organization.Position(LocalDate.of(1997, 9, 1), LocalDate.of(2005, 1, 31),
                "Инженер по аппаратному и программному тестированию", "Тестирование, отладка, " +
                "внедрение ПО цифровой телефонной станции Alcatel 1000 S12(CHILL, ASM).")));
        education.add(new Organization("Coursera", "https://www.coursera.org/course/progfun", new Organization.Position(LocalDate.of(2013, 3, 1), LocalDate.of(2013, 5, 31),
                "\"Functional Programming Principles in Scala\" by Martin Odersky", "")));
        education.add(new Organization("Luxoft", "http://www.luxoft-training.ru/training/catalog/course.html?ID=22366",new Organization.Position(LocalDate.of(2011, 3, 1), LocalDate.of(2011, 4, 30),
                "Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.", "")));
        education.add(new Organization("Siemens AG","http://www.siemens.ru", new Organization.Position(LocalDate.of(2005, 1, 1), LocalDate.of(2004, 4, 30),
                "3 месяца обучения мобильным IN сетям (Берлин)", "")));
        education.add(new Organization("Alcatel","http://www.alcatel.ru",new Organization.Position(LocalDate.of(1997, 9, 1),LocalDate.of(1998, 3, 31),
                "6 месяцев обучения цифровым телефонным сетям (Москва)", "")));
        education.add(new Organization("Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики",
                "http://www.ifmo.ru", new Organization.Position(LocalDate.of(1993, 9, 1), LocalDate.of(1996, 7, 31), "Аспирантура (программист С, С++)", ""),
                new Organization.Position(LocalDate.of(1987, 9, 1), LocalDate.of(1993, 7, 31),
                        "Инженер (программист Fortran, C)", "")));
        education.add(new Organization("Заочная физико-техническая школа при МФТИ", "http://www.school.mipt.ru", new Organization.Position(LocalDate.of(1984, 9, 1), LocalDate.of(1987, 6, 30),
                "Закончил с отличием", "")));

        /*resume.addSection(SectionType.OBJECTIVE, new TextSection("Ведущий стажировок и" +
                "корпоративного обучения по Java Web и Enterprise технологиям."));
        resume.addSection(SectionType.PERSONAL, new TextSection("Аналитический склад ума," +
                "сильная логика, креативность,инициативность. Пурист кода и архитектуры."));
        resume.addSection(SectionType.QUALIFICATIONS, qualificationsSection);
        resume.addSection(SectionType.ACHIEVEMENT, achievementSection);
        resume.addSection(SectionType.EDUCATION, educationSection);
        resume.addSection(SectionType.EXPERIENCE, experienceSection);*/

        return resume;
    }
}
