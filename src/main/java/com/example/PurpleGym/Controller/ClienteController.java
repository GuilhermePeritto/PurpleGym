package com.example.PurpleGym.Controller;

import com.example.PurpleGym.Main;
import com.example.PurpleGym.Model.Cliente;
import com.example.PurpleGym.Repository.ClienteRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ClienteController {

    private ConfigurableApplicationContext springContext = Main.getContext();

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

    private Cliente cliente;

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

    public void start(Stage stage, Cliente cliente) throws IOException {
        FXMLLoader fxmlLoaderCliente = new FXMLLoader(getClass().getClassLoader().getResource("View/Cliente.fxml"));
        fxmlLoaderCliente.setControllerFactory(springContext::getBean);
        Parent parent = fxmlLoaderCliente.load();

        // Obtém a instância do controlador
        ClienteController clienteController = fxmlLoaderCliente.getController();

        // Chama o método alterarCampos com o cliente fornecido
        clienteController.alterarCampos(cliente);

        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void fecharTela(ActionEvent event) {
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
            fecharTela(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void alterarCampos(Cliente cliente) {

        if(cliente == null) {
            return;
        }
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
        // Define os valores nos ComboBoxes
        cidadeCb.setValue(cliente.getCidade());
        ufCb.setValue(cliente.getUf());
    }

    public void excluirCliente(Cliente cliente) {
        try {
            clienteRepository.deleteById(cliente.getIdCliente());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
