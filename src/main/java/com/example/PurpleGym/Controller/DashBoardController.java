package com.example.PurpleGym.Controller;

import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import org.springframework.stereotype.Component;

@Component
public class DashBoardController {

    @FXML
    private AnchorPane menuDashBoard;

    private TranslateTransition menuSlider;
    private ScaleTransition menuExpander;
    private boolean menuExpanded = false;

    public void initialize() {
        menuSlider = new TranslateTransition(Duration.millis(200), menuDashBoard);
        menuSlider.setByX(-menuDashBoard.getPrefWidth());

        menuExpander = new ScaleTransition(Duration.millis(200), menuDashBoard);
        menuExpander.setToX(1.0);

        // Comece com a escala inicial (menu recolhido)
        menuExpander.setFromX(0.0);
        menuExpander.setToX(1.0);
    }

    @FXML
    public void expandirMenu() {
        if (!menuExpanded) {
            menuSlider.play();
            menuExpander.play();
            menuExpanded = true;
        }
    }

    @FXML
    public void recolherMenu() {
        if (menuExpanded) {
            menuSlider.setToX(-menuDashBoard.getPrefWidth());
            menuSlider.play();
            menuExpander.setToX(1.0);
            menuExpander.play();
            menuExpanded = false;
        }
    }
}
