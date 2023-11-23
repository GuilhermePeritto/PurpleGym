package com.example.PurpleGym.Controller;

import com.example.PurpleGym.Model.Cliente;
import com.example.PurpleGym.Model.Produto;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class ProdutoListController {

    @FXML
    private Label ValorLbl;
    @FXML
    private Label nomeLbl;

    private Produto produto;

    public void setData(Produto produto) {
        this.produto = produto;
        nomeLbl.setText(produto.getNome());
        ValorLbl.setText(produto.getValor().toString());
    }
}
