package com.example.PurpleGym.Controller;

import com.example.PurpleGym.Model.Cliente;
import com.example.PurpleGym.Model.Produto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Component
public class ProdutoListController {

    @FXML
    private Label ValorProdutoLbl;

    @FXML
    private Button btnInspecionarProduto;

    @FXML
    private ImageView imgProduto;

    @FXML
    private MenuButton menuButtonMaisOp;

    @FXML
    private Label nomeProdutoLbl;

    private Produto produto;

    public void initialize() {
        // Configurar os itens do MenuButton
        MenuItem editarItem = new MenuItem("Editar");
        MenuItem excluirItem = new MenuItem("Excluir");

        // Configurar os eventos dos itens
        editarItem.setOnAction(event -> {
            try {
                handleEditarProduto();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        excluirItem.setOnAction(event -> {
            try {
                handleExcluirProduto();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        // Adicionar os itens ao MenuButton
        menuButtonMaisOp.getItems().addAll(editarItem, excluirItem);

        // ... (outros códigos)
    }

    private void handleEditarProduto() throws IOException {
        // Lógica para editar o produto
        EditarProdutoBtnEvent(new ActionEvent());

        // Ocultar o MenuButton após a seleção
        menuButtonMaisOp.hide();
    }

    private void handleExcluirProduto() throws IOException {
        // Lógica para excluir o produto
        ExcluirProdutoBtnEvent(new ActionEvent());

        // Ocultar o MenuButton após a seleção
        menuButtonMaisOp.hide();
    }

    public void setData(Produto produto) {
        this.produto = produto;
        nomeProdutoLbl.setText(produto.getNome());
        ValorProdutoLbl.setText(produto.getValor().toString());
        imgProduto.setImage(new Image("File:" + produto.getLocalDaImagem()));
    }

    @FXML
    public void EditarProdutoBtnEvent(ActionEvent event) throws IOException {
        ProdutoController produtoController = new ProdutoController();
        produtoController.setData(produto);
        produtoController.start(new Stage());
    }
    @FXML
    public void ExcluirProdutoBtnEvent(ActionEvent event) throws IOException {
        ProdutoController produtoController = new ProdutoController();
        produtoController.setData(produto);
        produtoController.excluiProduto();
    }
    @FXML
    public void InspecionarProdutoBtnEvent(ActionEvent event) throws IOException {
        ProdutoController produtoController = new ProdutoController();
        produtoController.setData(produto);
        produtoController.desabilitar = true;
        produtoController.start(new Stage());
        produtoController.setProduto(null);
    }

}
