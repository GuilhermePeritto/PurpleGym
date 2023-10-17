package com.example.PurpleGym.Controller;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import org.springframework.stereotype.Component;

@Component
public class DashBoardController {


    @FXML
    private AnchorPane dashBoard;

    @FXML
    private AnchorPane menuDashBoard;

    private void initialize() {

    }

    private void expandirMenu(ActionEvent event) {
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(javafx.util.Duration.seconds(0.5));
        transition.setNode(menuDashBoard);

        transition.setToX(0);
        transition.play();

        menuDashBoard.setTranslateX(-190);

        dashBoard.setLeftAnchor(menuDashBoard, 0.0);
    }

    private void 'recolherMenu'() {
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(javafx.util.Duration.seconds(0.5));
        transition.setNode(menuDashBoard);

        transition.setToX(-190);
        transition.play();

        menuDashBoard.setTranslateX(0);

        dashBoard.setLeftAnchor(menuDashBoard, -190.0);

    }

}
