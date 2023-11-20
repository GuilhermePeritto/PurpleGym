package com.example.PurpleGym.Controller;

import com.example.PurpleGym.Main;
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
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static javafx.stage.StageStyle.*;

@Component
public class ClienteController {

    private ConfigurableApplicationContext springContext = Main.getContext();

    @FXML
    private TextField cepLbl = new TextField("");

    @FXML
    private ComboBox<String> cidadeCb = new ComboBox<String>();

    @FXML
    private TextField complementoLbl = new TextField("");

    @FXML
    private TextField cpfLbl= new TextField("");

    @FXML
    private DatePicker dataNascLbl = new DatePicker();

    @FXML
    private TextField emailLbl= new TextField("");

    @FXML
    private TextField enderecoLbl= new TextField("");

    @FXML
    private TextField nomeLbl = new TextField("");

    @FXML
    private TextField numeroLbl = new TextField("");

    @FXML
    private TextField outroContatoLbl = new TextField("");

    @FXML
    private ComboBox<String> ufCb = new ComboBox<String>();

    @FXML
    private TextField whatsLbl = new TextField("");

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
        fxmlLoaderCliente.setControllerFactory(springContext::getBean);
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

    public void setData(Cliente cliente) {
        nomeLbl.setText(cliente.getNome());
        cpfLbl.setText(cliente.getCpf());
        whatsLbl.setText(cliente.getWhatsapp());
        outroContatoLbl.setText(cliente.getOutroContato());
        emailLbl.setText(cliente.getEmail());
        dataNascLbl.setValue(cliente.getDataNascimento());
        enderecoLbl.setText(cliente.getEndereco());
        numeroLbl.setText(cliente.getNumero());
        complementoLbl.setText(cliente.getComplemento());
        cepLbl.setText(cliente.getCep());
        cidadeCb.setValue(cliente.getCidade());
        ufCb.setValue(cliente.getUf());
    }
}
