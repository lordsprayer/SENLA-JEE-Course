module bookServer {
    requires java.logging;
    requires di;
    requires java.sql;
    exports com.senla.courses.facade;
    exports com.senla.courses.model;
    exports com.senla.courses.config;
    exports com.senla.courses.exception;
    exports com.senla.courses.util;
    exports com.senla.courses.dbdao;
    opens com.senla.courses.facade;
    opens com.senla.courses.service;
    opens com.senla.courses.dbdao;
}