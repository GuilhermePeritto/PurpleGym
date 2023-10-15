package com.example.PurpleGym.Controller;

import com.example.PurpleGym.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import org.springframework.stereotype.Component;

@Component
public class AvisosController {

    @FXML
    private Button btnOk;
    @FXML
    private static ImageView imgIcone = new ImageView();
    @FXML
    private static Label lblMensagem = new Label();


    @FXML
    public static void AlertaSucesso(String mensagem) {
        lblMensagem.setText(mensagem);
        imgIcone.setImage(new javafx.scene.image.Image("/Image/iconeSucesso.png"));
        Main.trocarTela("avisos");
    }
    @FXML
    static void AlertaFalha(String mensagem) {
        lblMensagem.setText(mensagem);
        imgIcone.setImage(new javafx.scene.image.Image("/Image/iconeFalha.png"));
    }

    @FXML
    void onClickBtnOk(ActionEvent event) throws Exception {
        Main.trocarTela("login");
        Main.desativarAvisos();
    }

}
