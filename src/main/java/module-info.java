module isp {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires com.jfoenix;
    requires java.sql;

    opens isp to javafx.fxml;
    exports isp;
    exports isp.controllers;
    opens isp.controllers to javafx.fxml;
}