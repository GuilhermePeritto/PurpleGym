package com.example.testespring;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HelloController {

    @Autowired
    public UsuarioRepository usuarioRepository;

    @FXML
    private Button BtnEntrar;

    @FXML
    private Button BtnRegistrar;

    @FXML
    private Button BtnMinimize;

    @FXML
    private Button BtnSair;

    @FXML
    private Label LblloginError;

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
        LblloginError.setText("Você digitou algo errado, por favor tente novamente!");
    }

    @FXML
    void registrar() {
        try {
            Usuario teste = new Usuario(null, "teste", "teste");
            usuarioRepository.save(teste);

        } catch (Exception e) {
            System.out.println("Erro ao registrar usuário!");
            e.printStackTrace();
        }

    }
}