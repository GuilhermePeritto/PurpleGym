package com.example.PurpleGym.Controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static javafx.stage.StageStyle.*;

@Component
public class ClienteController {

    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoaderCliente = new FXMLLoader(getClass().getClassLoader().getResource("View/Cliente.fxml"));
        Parent parent = fxmlLoaderCliente.load();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }

}
