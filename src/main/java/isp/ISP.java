package isp;

import isp.constants.IConstant;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.Objects;

public class ISP extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ISP.class.getResource("controllers"+findSystemPathSeparator()+"landingPage-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle(IConstant.title);
        stage.setScene(scene);
        stage.show();
    }

    private String findSystemPathSeparator(){
        return Objects.requireNonNull(ISP.class.getResource("")).toString().split("isp")[1];

    }

    public static void main(String[] args) {
        launch();
    }
}
