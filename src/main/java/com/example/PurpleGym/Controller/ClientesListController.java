package com.example.PurpleGym.Controller;

import com.example.PurpleGym.Model.Cliente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
public class ClientesListController {

    @FXML
    private Button abrirClienteBtn;

    @FXML
    private Button editarClienteBtn;

    @FXML
    private Button excluirClienteBtn;

    @FXML
    private Label nomeClienteLbl;

    @FXML
    private Label emailClienteLbl;

    @FXML
    private Label telefoneClienteLbl;

    private Cliente cliente;

    public void setData(Cliente cliente) {
        this.cliente = cliente;
        nomeClienteLbl.setText(cliente.getNome());
        emailClienteLbl.setText(cliente.getEmail());
        telefoneClienteLbl.setText(cliente.getTelefone());
    }

    @FXML
    private void InspecionarClienteBtnEvent(ActionEvent event) throws IOException {
        ClienteController clienteController = new ClienteController();
        clienteController.start(new Stage());
    }

    @FXML
    private void EditarClienteBtnEvent(ActionEvent event) throws IOException {
        ClienteController clienteController = new ClienteController();
        clienteController.start(new Stage());
    }

}
