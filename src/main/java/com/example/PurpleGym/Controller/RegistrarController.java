package com.example.PurpleGym.Controller;

import com.example.PurpleGym.Main;
import com.example.PurpleGym.Model.Usuario;
import com.example.PurpleGym.Repository.UsuarioRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegistrarController {

    @Autowired
    public UsuarioRepository usuarioRepository;

    @FXML
    private Button BtnFechar;

    @FXML
    private Button BtnMinimizar;

    @FXML
    private Button BtnVoltar;

    @FXML
    private Button BtnVoltarTelaEntrar;

    @FXML
    private Button btnFinalizarRegistro;

    @FXML
    private Button btnLoginFacebook;

    @FXML
    private Button btnLoginGoogle;

    @FXML
    public TextField lblEmail;

    @FXML
    public TextField lblNome;

    @FXML
    public PasswordField lblSenha;

    @FXML
    void minimizar(ActionEvent event) {
        Stage stage = (Stage) BtnMinimizar.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    void finalizarRegistro() throws Exception {
        AvisoController avisoController = new AvisoController();
        try {
            if (lblNome.getText().isEmpty()) {
                new Exception("Você não preencheu o campo Nome Completo, favor tentar novamente!");
            } else if (lblEmail.getText().isEmpty()) {
                new Exception("Você não preencheu o campo E-mail, favor tentar novamente!");
            } else if (lblSenha.getText().isEmpty()) {
                new Exception("Você não preencheu o campo Senha, favor tentar novamente!");
            } else {
                try {
                    usuarioRepository.save(new Usuario(null,lblNome.getText(), lblEmail.getText(), lblSenha.getText()));
                    avisoController.showAlerta(new Stage(), "Cadastro realizado com sucesso!", false);
                } catch (Exception e) {
                    avisoController.showAlerta(new Stage(), e.getLocalizedMessage(), true);
                }
                lblEmail.setText("");
                lblNome.setText("");
                lblSenha.setText("");
            }
        } catch (Exception e) {
            avisoController.showAlerta(new Stage(),e.getMessage(), true);
        }

    }
    @FXML
    void voltarTelaEntrar(ActionEvent event) throws Exception {
        Main.trocarTela("login");
    }

    @FXML
    void voltar(ActionEvent event) throws Exception {
        Main.trocarTela("login");
    }

    @FXML
    void fechar(ActionEvent event) {
        Stage stage = (Stage) BtnFechar.getScene().getWindow();
        stage.close();
    }

    @FXML
    void loginFacebook(ActionEvent event) {
        System.out.println("Login Facebook");
    }

    @FXML
    void loginGoogle(ActionEvent event) {
        System.out.println("Login Google");
    }

}