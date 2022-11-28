package isp.models;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;
import java.util.List;

public class ResultModel extends RecursiveTreeObject<ResultModel> {
    public SimpleIntegerProperty serial;
    public List<SimpleStringProperty> characteristics;

    public ResultModel(List<String> row, int serial){
        this.serial = new SimpleIntegerProperty(serial);
        characteristics = new ArrayList<>();
        for (String cell: row) {
            characteristics.add( new SimpleStringProperty(cell));
        }
    }

    @Override
    public String toString() {
        StringBuilder temp = new StringBuilder(this.serial.toString()+" ");
        for(SimpleStringProperty property: characteristics) temp.append(property.getValue()).append(" ");
        return temp.toString();
    }
}
