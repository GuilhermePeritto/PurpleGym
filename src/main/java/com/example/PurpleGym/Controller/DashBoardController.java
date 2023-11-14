package com.example.PurpleGym.Controller;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import org.springframework.stereotype.Component;

@Component
public class DashBoardController {

    @FXML
    private VBox menuSlider;

    @FXML
    private Button clientesBtn;

    @FXML
    private Button perfilBtn;

    @FXML
    private Button permissaoBtn;

    @FXML
    private Button produtosBtn;

    @FXML
    private AnchorPane dashBoardClientes;

    @FXML
    private AnchorPane dashBoardPermissoes;

    @FXML
    private AnchorPane dashBoardProdutos;

    private static final double ORIGINAL_WIDTH = 97.0;
    private static final double EXPANDED_WIDTH = 300.0;

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
        permissaoBtn.setText("Permissões");
        perfilBtn.setText("Perfil");
        clientesBtn.setText("Clientes");
    }

    private void shrinkMenuSlider() {
        animateWidth(ORIGINAL_WIDTH);
        produtosBtn.setText("");
        permissaoBtn.setText("");
        perfilBtn.setText("");
        clientesBtn.setText("");
    }
    @FXML
    private void ClientesBtnEvent(ActionEvent event) {
        dashBoardClientes.setVisible(true);
        dashBoardPermissoes.setVisible(false);
        dashBoardProdutos.setVisible(false);
    }

    @FXML
    private void PermissaoBtnEvent(ActionEvent event) {
        dashBoardPermissoes.setVisible(true);
        dashBoardClientes.setVisible(false);
        dashBoardProdutos.setVisible(false);
    }

    @FXML
    private void ProdutosBtnEvent(ActionEvent event) {
        dashBoardProdutos.setVisible(true);
        dashBoardClientes.setVisible(false);
        dashBoardPermissoes.setVisible(false);
    }

    private void showPane(AnchorPane pane) {
        // Tornar o AnchorPane clicado visível e os outros invisíveis
        dashBoardClientes.setVisible(pane == dashBoardClientes);
        dashBoardPermissoes.setVisible(pane == dashBoardPermissoes);
        dashBoardProdutos.setVisible(pane == dashBoardProdutos);
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

        KeyValue keyValuesPermissao = new KeyValue(permissaoBtn.prefWidthProperty(), targetWidth);
        KeyFrame keyFramePermissao = new KeyFrame(Duration.millis(300), keyValuesPermissao);
        timeline.getKeyFrames().add(keyFramePermissao);


        KeyValue keyValuesProdutos = new KeyValue(produtosBtn.prefWidthProperty(), targetWidth);
        KeyFrame keyFrameProdutos = new KeyFrame(Duration.millis(300), keyValuesProdutos);
        timeline.getKeyFrames().add(keyFrameProdutos);

        timeline.play();
    }
}