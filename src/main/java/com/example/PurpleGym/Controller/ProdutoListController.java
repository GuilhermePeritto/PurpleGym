package com.example.PurpleGym.Controller;

import com.example.PurpleGym.Model.Cliente;
import com.example.PurpleGym.Model.Produto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import org.springframework.stereotype.Component;

@Component
public class ProdutoListController {

    @FXML
    private Label ValorProdutoLbl;
    @FXML
    private Label nomeProdutoLbl;

    private Produto produto;

    public void setData(Produto produto) {
        this.produto = produto;
        nomeProdutoLbl.setText(produto.getNome());
        ValorProdutoLbl.setText(produto.getValor().toString());
    }

    public void EditarProdutoBtnEvent(ActionEvent event) {
    }

    public void ExcluirProdutoBtnEvent(ActionEvent event) {
    }

    public void InspecionarProdutoBtnEvent(ActionEvent event) {
    }
}
