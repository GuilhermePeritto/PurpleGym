package com.example.PurpleGym.Controller;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.util.Duration;
import org.springframework.stereotype.Component;

@Component
public class DashBoardController {

    @FXML
    private VBox menuSlider;

    @FXML
    private Button homeBtn;

    @FXML
    private Button logoutBtn;

    @FXML
    private Button configuracaoBtn;

    @FXML
    private Line lineEspacamento;

    @FXML
    private Button clientesBtn;

    @FXML
    private Button perfilBtn;

    @FXML
    private Button produtosBtn;

    @FXML
    private AnchorPane dashBoardClientes;

    @FXML
    private AnchorPane dashBoardConfiguracao;

    @FXML
    private AnchorPane dashBoardProdutos;

    @FXML
    private AnchorPane dashBoardHome;

    @FXML
    private AnchorPane dashBoardPerfil;

    @FXML
    private Label moduloLbl;

    private static final double ORIGINAL_WIDTH = 23.0;
    private static final double EXPANDED_WIDTH = 250.0;

    @FXML
    public void initialize() {
        // Adicione ou ajuste outros inicializadores aqui, se necessário
        menuSlider.setPrefWidth(ORIGINAL_WIDTH);
        setMouseEvents();
    }

    private void setMouseEvents() {
        menuSlider.setOnMouseEntered(event -> expandMenuSlider());
        menuSlider.setOnMouseExited(event -> shrinkMenuSlider());
    }

    private void expandMenuSlider() {
        animateWidth(EXPANDED_WIDTH);
        produtosBtn.setText("Produtos");
        configuracaoBtn.setText("Configurações");
        perfilBtn.setText("Perfil");
        clientesBtn.setText("Clientes");
        homeBtn.setText("Home");
        logoutBtn.setText("Logout");
    }

    private void shrinkMenuSlider() {
        animateWidth(ORIGINAL_WIDTH);
        produtosBtn.setText("");
        configuracaoBtn.setText("");
        perfilBtn.setText("");
        clientesBtn.setText("");
        homeBtn.setText("");
        logoutBtn.setText("");
    }
    @FXML
    private void ClientesBtnEvent(ActionEvent event) {
        dashBoardClientes.setVisible(true);
        dashBoardConfiguracao.setVisible(false);
        dashBoardProdutos.setVisible(false);
        dashBoardHome.setVisible(false);
        dashBoardPerfil.setVisible(false);
        moduloLbl.setText("Clientes");
    }

    @FXML
    private void ConfiguracaoBtnEvent(ActionEvent event) {
        dashBoardConfiguracao.setVisible(true);
        dashBoardClientes.setVisible(false);
        dashBoardProdutos.setVisible(false);
        dashBoardHome.setVisible(false);
        dashBoardPerfil.setVisible(false);
        moduloLbl.setText("Configurações");
    }

    @FXML
    private void HomeBtnEvent(ActionEvent event) {
        dashBoardConfiguracao.setVisible(false);
        dashBoardClientes.setVisible(false);
        dashBoardProdutos.setVisible(false);
        dashBoardHome.setVisible(true);
        dashBoardPerfil.setVisible(false);
        moduloLbl.setText("Home");
    }

    @FXML
    private void LogoutBtnEvent(ActionEvent event) {
    }

    @FXML
    private void PerfilBtnEvent(ActionEvent event) {
        dashBoardConfiguracao.setVisible(false);
        dashBoardClientes.setVisible(false);
        dashBoardProdutos.setVisible(false);
        dashBoardHome.setVisible(false);
        dashBoardPerfil.setVisible(true);
        moduloLbl.setText("Perfil");
    }

    @FXML
    private void ProdutosBtnEvent(ActionEvent event) {
        dashBoardProdutos.setVisible(true);
        dashBoardClientes.setVisible(false);
        dashBoardConfiguracao.setVisible(false);
        dashBoardHome.setVisible(false);
        dashBoardPerfil.setVisible(false);
        moduloLbl.setText("Produtos");
    }

    private void showPane(AnchorPane pane) {
        // Tornar o AnchorPane clicado visível e os outros invisíveis
        dashBoardClientes.setVisible(pane == dashBoardClientes);
        dashBoardConfiguracao.setVisible(pane == dashBoardConfiguracao);
        dashBoardProdutos.setVisible(pane == dashBoardProdutos);
        dashBoardHome.setVisible(pane == dashBoardHome);
        dashBoardPerfil.setVisible(pane == dashBoardPerfil);
    }


    private void animateWidth(double targetWidth) {
        Timeline timeline = new Timeline();
        KeyValue keyValue = new KeyValue(menuSlider.prefWidthProperty(), targetWidth);
        KeyFrame keyFrame = new KeyFrame(Duration.millis(300), keyValue);
        timeline.getKeyFrames().add(keyFrame);

        KeyValue keyValuesClientes = new KeyValue(clientesBtn.prefWidthProperty(), targetWidth);
        KeyFrame keyFrameClientes = new KeyFrame(Duration.millis(300), keyValuesClientes);
        timeline.getKeyFrames().add(keyFrameClientes);

        KeyValue keyValuesPerfil = new KeyValue(perfilBtn.prefWidthProperty(), targetWidth);
        KeyFrame keyFramePerfil = new KeyFrame(Duration.millis(300), keyValuesPerfil);
        timeline.getKeyFrames().add(keyFramePerfil);

        KeyValue keyValuesConfiguracao = new KeyValue(configuracaoBtn.prefWidthProperty(), targetWidth);
        KeyFrame keyFrameConfiguracao = new KeyFrame(Duration.millis(300), keyValuesConfiguracao);
        timeline.getKeyFrames().add(keyFrameConfiguracao);


        KeyValue keyValuesProdutos = new KeyValue(produtosBtn.prefWidthProperty(), targetWidth);
        KeyFrame keyFrameProdutos = new KeyFrame(Duration.millis(300), keyValuesProdutos);
        timeline.getKeyFrames().add(keyFrameProdutos);

        KeyValue keyValuesHome = new KeyValue(homeBtn.prefWidthProperty(), targetWidth);
        KeyFrame keyFrameHome = new KeyFrame(Duration.millis(300), keyValuesHome);
        timeline.getKeyFrames().add(keyFrameHome);

        KeyValue keyValuesLogout = new KeyValue(logoutBtn.prefWidthProperty(), targetWidth);
        KeyFrame keyFrameLogout = new KeyFrame(Duration.millis(300), keyValuesLogout);
        timeline.getKeyFrames().add(keyFrameLogout);

        KeyValue keyValuesLine = new KeyValue(lineEspacamento.endXProperty(), targetWidth);
        KeyFrame keyFrameLine = new KeyFrame(Duration.millis(300), keyValuesLine);
        timeline.getKeyFrames().add(keyFrameLine);

        timeline.play();
    }
}