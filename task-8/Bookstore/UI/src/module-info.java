module ui {
    requires java.logging;
    requires bookServer;
    requires di;
    exports com.senla.courses.ui.menu;
    opens com.senla.courses.ui.menu;
}