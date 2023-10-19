package com.example.PurpleGym;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.net.URL;

import static javafx.stage.StageStyle.TRANSPARENT;
import static javafx.stage.StageStyle.UNDECORATED;


@SpringBootApplication
public class Main extends Application{

    public ConfigurableApplicationContext springContext;
    public static Parent rootNode;

    public static Stage stage;
    public static Scene loginView;
    public static Scene registrarView;
    public static Scene avisosView;
    public static Scene dashBoardView;
    static Stage stageAvisos = new Stage();

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
        FXMLLoader fxmlLoaderLogin = new FXMLLoader(getClass().getClassLoader().getResource("View/Login.fxml"));
        fxmlLoaderLogin.setControllerFactory(springContext::getBean);//passa o contexto para o banco de dados
        Parent fxmlLoginView = fxmlLoaderLogin.load();
        loginView = new Scene(fxmlLoginView, 975, 501);
        FXMLLoader fxmlLoaderRegistrar = new FXMLLoader(getClass().getClassLoader().getResource("View/Registrar.fxml"));
        fxmlLoaderRegistrar.setControllerFactory(springContext::getBean);
        Parent fxmlRegistrarView = fxmlLoaderRegistrar.load();
        registrarView = new Scene(fxmlRegistrarView, 975, 501);
        FXMLLoader fxmlLoaderAvisos = new FXMLLoader(getClass().getClassLoader().getResource("View/AvisoSucesso.fxml"));
        fxmlLoaderAvisos.setControllerFactory(springContext::getBean);
        Parent fxmlAvisosView = fxmlLoaderAvisos.load();
        avisosView = new Scene(fxmlAvisosView, 415, 400);
        avisosView.setFill(Color.TRANSPARENT);
        FXMLLoader fxmlLoaderDashBoard = new FXMLLoader(getClass().getClassLoader().getResource("View/DashBoard.fxml"));
        fxmlLoaderDashBoard.setControllerFactory(springContext::getBean);
        Parent fxmlDashBoardView = fxmlLoaderDashBoard.load();
        dashBoardView = new Scene(fxmlDashBoardView, 1920, 1080);
        dashBoardView.setFill(Color.TRANSPARENT);
        startStage.setTitle("PurpleGym");


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
            case "avisos":
                stageAvisos.initStyle(TRANSPARENT);
                stageAvisos.setScene(avisosView);
                stageAvisos.show();
                break;
                case "dashboard":
                stage.setScene(dashBoardView);
                stage.setMaximized(true);
                break;
        }
    }

    public static void desativarAvisos() {
        stageAvisos.close();
    }
}
