package com.example.testespring;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.net.URL;


@SpringBootApplication
public class TesteSpringApplication extends Application{

    public ConfigurableApplicationContext springContext;
    public Parent rootNode;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void init() throws Exception {
        springContext = SpringApplication.run(TesteSpringApplication.class);
        URL fxmlLocation = getClass().getClassLoader().getResource("teste.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(fxmlLocation);
        fxmlLoader.setControllerFactory(springContext::getBean);
        rootNode = fxmlLoader.load();
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(rootNode));
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        springContext.stop();
    }
}
