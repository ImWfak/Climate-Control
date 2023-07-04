module info.climateControl.application {
    // Logger
    requires org.apache.logging.log4j;
    // JDBC
    requires org.xerial.sqlitejdbc;
    requires java.sql;
    // Guava
    requires com.google.common;
    // JavaFX
    requires javafx.controls;
    requires javafx.fxml;
    // XStream
    requires xstream;

    opens info.climateControl.window.alerts to javafx.fxml;
    opens info.climateControl.window.controller to javafx.fxml;
    opens info.climateControl.window.tabs to javafx.fxml;
    opens info.climateControl.application to javafx.fxml;
    opens info.climateControl.day to xstream;
    opens info.climateControl.weather to xstream;
    opens info.climateControl.climate to xstream;

    exports info.climateControl.application;
    exports info.climateControl.day;
    exports info.climateControl.weather;
    exports info.climateControl.climate;
}