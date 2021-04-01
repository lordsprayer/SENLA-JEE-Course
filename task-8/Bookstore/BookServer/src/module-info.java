module bookServer {
    requires java.logging;
    requires di;
    exports com.senla.courses.facade;
    exports com.senla.courses.model;
    exports com.senla.courses.config;
    exports com.senla.courses.exception;
    exports com.senla.courses.util;
    opens com.senla.courses.facade;
    opens com.senla.courses.dao;
    opens com.senla.courses.service;
}