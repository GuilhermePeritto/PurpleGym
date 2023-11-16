package com.example.PurpleGym.Controller;

import com.example.PurpleGym.Model.Cliente;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ClientesListController {

    @FXML
    private Button abrirClienteBtn;

    @FXML
    private Button editarClienteBtn;

    @FXML
    private Button excluirClienteBtn;

    @FXML
    private Label nomeClienteLbl;

    private Cliente cliente;

    public void setData(Cliente cliente) {
        this.cliente = cliente;
        nomeClienteLbl.setText(cliente.getNome());
    }

}
