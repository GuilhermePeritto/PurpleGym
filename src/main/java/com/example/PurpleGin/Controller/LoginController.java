package com.example.PurpleGin.Controller;

import com.example.PurpleGin.Model.Usuario;
import com.example.PurpleGin.Repository.UsuarioRepository;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static javafx.scene.paint.Color.GREEN;
import static javafx.scene.paint.Color.RED;

@Component
public class LoginController {

    @Autowired
    public UsuarioRepository usuarioRepository;
    public TextField lblusuario;
    public PasswordField lblsenha;

    @FXML
    private Button BtnEntrar;

    @FXML
    private Button BtnRegistrar;

    @FXML
    private Button BtnMinimize;

    @FXML
    private Button BtnSair;

    @FXML
    private Label LblAviso;

    @FXML
    void sair(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void minimizar(ActionEvent event) {
        Stage stage = (Stage) BtnMinimize.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    void entrar(ActionEvent event) {
        LblAviso.setText("Você digitou algo errado, por favor tente novamente!");
    }

    @FXML
    void registrar() throws Exception {
        try {
            usuarioRepository.save(new Usuario(null, lblusuario.getText(),lblsenha.getText()));
            LblAviso.setTextFill(GREEN);
            LblAviso.setText("Usuário registrado com sucesso!");
        } catch (Exception e) {
            LblAviso.setTextFill(RED);
            LblAviso.setText(e.getMessage());

        }

    }
}