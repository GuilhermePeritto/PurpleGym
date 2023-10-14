package com.example.PurpleGym;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.net.URL;

import static javafx.stage.StageStyle.UNDECORATED;


@SpringBootApplication
public class Main extends Application{

    public ConfigurableApplicationContext springContext;
    public static Parent rootNode;

    public static Stage stage;
    public static Scene loginView;
    public static Scene registrarView;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void init() throws Exception {
        springContext = SpringApplication.run(Main.class);
        URL fxmlLocation = getClass().getClassLoader().getResource("View/Login.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(fxmlLocation);
        fxmlLoader.setControllerFactory(springContext::getBean);
        rootNode = fxmlLoader.load();
    }

    @Override
    public void start(Stage startStage) throws Exception {
        stage = startStage;
        Parent fxmlLoginView = FXMLLoader.load(getClass().getClassLoader().getResource("View/Login.fxml"));
        loginView = new Scene(fxmlLoginView, 975, 501);
        Parent fxmlRegistrarView = FXMLLoader.load(getClass().getClassLoader().getResource("View/Registrar.fxml"));
        registrarView = new Scene(fxmlRegistrarView, 975, 501);


        startStage.setScene(new Scene(rootNode, 975, 501));
        startStage.initStyle(UNDECORATED);
        startStage.show();
    }

    @Override
    public void stop() throws Exception {
        springContext.stop();
    }

    public static void trocarTela(String tela){
        switch(tela){
            case "login":
                stage.setScene(loginView);
                break;
            case "registrar":
                stage.setScene(registrarView);
                break;
        }
    }
}
