package com.example.PurpleGym.Controller;

import com.example.PurpleGym.Model.Cliente;
import com.example.PurpleGym.Repository.ClienteRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static javafx.stage.StageStyle.*;

@Component
public class ClienteController {

    @FXML
    private TextField cepLbl;

    @FXML
    private ComboBox<String> cidadeCb;

    @FXML
    private TextField complementoLbl;

    @FXML
    private TextField cpfLbl;

    @FXML
    private DatePicker dataNascLbl;

    @FXML
    private TextField emailLbl;

    @FXML
    private TextField enderecoLbl;

    @FXML
    private TextField nomeLbl;

    @FXML
    private TextField numeroLbl;

    @FXML
    private TextField outroContatoLbl;

    @FXML
    private ComboBox<String> ufCb;

    @FXML
    private TextField whatsLbl;

    @Autowired
    ClienteRepository clienteRepository;

    @FXML
    public void initialize() {
        // Preencher o ComboBox de cidades
        cidadeCb.getItems().addAll(
                "São Paulo",
                "Rio de Janeiro",
                "Belo Horizonte",
                "Salvador",
                "Brasília"
        );

        // Preencher o ComboBox de UFs
        ufCb.getItems().addAll(
                "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE",
                "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"
        );
    }

    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoaderCliente = new FXMLLoader(getClass().getClassLoader().getResource("View/Cliente.fxml"));
        Parent parent = fxmlLoaderCliente.load();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void cancelar(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    public void salvar(ActionEvent event) {
        try {
            Cliente cliente = new Cliente(null,
                    nomeLbl.getText(),
                    cpfLbl.getText(),
                    whatsLbl.getText(),
                    outroContatoLbl.getText(),
                    emailLbl.getText(),
                    dataNascLbl.getValue(),
                    enderecoLbl.getText(),
                    numeroLbl.getText(),
                    complementoLbl.getText(),
                    cepLbl.getText(),
                    cidadeCb.getValue().toString(),
                    ufCb.getValue().toString());
            clienteRepository.save(cliente);
            cancelar(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
