package isp.models;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Project  extends RecursiveTreeObject<Project> {

    public SimpleStringProperty projectId;
    public SimpleIntegerProperty counter;
    public SimpleStringProperty dateTime;
    public SimpleStringProperty category;
    public SimpleStringProperty input;
    public SimpleIntegerProperty generatedTests;
    public SimpleObjectProperty<BigInteger> possibleTests;

    public Project(ResultSet rs, Integer counter) {
        try {
            this.projectId = new SimpleStringProperty(rs.getString(1));
            this.dateTime = new SimpleStringProperty(rs.getString(2));
            this.category = new SimpleStringProperty(rs.getString(3));
            this.input = new SimpleStringProperty( rs.getString(4));
            this.generatedTests = new SimpleIntegerProperty(rs.getInt(5));
            this.possibleTests = new SimpleObjectProperty<>( new BigInteger(rs.getString(6)) );

            this.counter = new SimpleIntegerProperty( counter );

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Project(String category, String input, Integer generatedTests, BigInteger possibleTests){

        this.category = new SimpleStringProperty(category);
        LocalDateTime now = LocalDateTime.now();
        this.dateTime = new SimpleStringProperty( DateTimeFormatter.ofPattern("HH:mm:ss dd-MMM-yyyy").format(now) );
        this.input = new SimpleStringProperty(input);
        this.generatedTests = new SimpleIntegerProperty(generatedTests);
        this.possibleTests = new SimpleObjectProperty<>(possibleTests);
    }

}
