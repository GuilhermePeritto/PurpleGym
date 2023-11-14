package com.example.PurpleGym.Controller;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
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

    private void animateWidth(double targetWidth) {
        Timeline timeline = new Timeline();
        KeyValue keyValue = new KeyValue(menuSlider.prefWidthProperty(), targetWidth);
        KeyFrame keyFrame = new KeyFrame(Duration.millis(300), keyValue);
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }
}