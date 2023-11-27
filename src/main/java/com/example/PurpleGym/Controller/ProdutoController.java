package com.example.PurpleGym.Controller;

import com.example.PurpleGym.Main;
import com.example.PurpleGym.Model.Cliente;
import com.example.PurpleGym.Model.Produto;
import com.example.PurpleGym.Repository.ClienteRepository;
import com.example.PurpleGym.Repository.ProdutoRepository;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.Date;

@Component
public class ProdutoController {

    @FXML
    private Button btnEditarImagem;

    @FXML
    private TextField descricaoLbl;

    @FXML
    private ImageView imgProduto;

    @FXML
    private TextField nomeLbl;

    @FXML
    private TextField quantLbl;

    @FXML
    private BorderPane backgroundImagem;

    @FXML
    private TextField valorLbl;

    private ConfigurableApplicationContext springContext = Main.getContext();

    private static Produto produto;

    private Long idProduto;

    @FXML
    private Button btnSalvar;

    @FXML
    private Button btnCancelar;

    private String LocalDaImagem;

    public Boolean desabilitar = false;

    @Autowired
    ProdutoRepository produtoRepository;

    public void initialize() {
        btnEditarImagem.setVisible(false);
        // Adicionar evento para controlar a visibilidade do botão de editar imagem
        backgroundImagem.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btnEditarImagem.setVisible(true);
            }
        });

        backgroundImagem.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btnEditarImagem.setVisible(false);
            }
        });
    }

    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoaderProduto = new FXMLLoader(getClass().getClassLoader().getResource("View/Produto.fxml"));
        fxmlLoaderProduto.setControllerFactory(springContext::getBean);
        Parent parent = fxmlLoaderProduto.load();
        // Obtém a instância do controlador
        ProdutoController produtoController = fxmlLoaderProduto.getController();
        produtoController.alterarDados(produto);
        produtoController.desabilitarCampos(desabilitar);
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }

    private void alterarDados(Produto produto) {
        if(produto == null) {
            return;
        }
        nomeLbl.setText(produto.getNome());
        descricaoLbl.setText(produto.getDescricao());
        quantLbl.setText(produto.getQuantidade());
        valorLbl.setText(produto.getValor());
        imgProduto.setImage(new Image("file:" + produto.getLocalDaImagem()));
        idProduto = produto.getIdProduto();
        setData(produto);
    }

    public void setData(Produto produto) {
        this.produto = produto;
    }

    @FXML
    public void uploadImagem(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Imagens", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );

        // Abrir a janela de diálogo para seleção do arquivo
        File selectedFile = fileChooser.showOpenDialog(new Stage());

        if (selectedFile != null) {
            // Diretório onde as imagens serão salvas localmente
            String localDirectory = "C:/PurpleGym/Imagens/Produtos/" + idProduto + "/";

            // Verificar se o diretório existe, se não, criar
            File directory = new File(localDirectory);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Copiar a imagem para o diretório local
            Path localDaImagem = new File(localDirectory + idProduto + ".png").toPath();
            try {
                Files.copy(selectedFile.toPath(), localDaImagem, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace(); // Tratar a exceção conforme necessário
            }
            LocalDaImagem = (localDaImagem.toString());

            // Atualizar a imagem exibida no aplicativo
            Image image = new Image("file:" + localDaImagem.toString());
            imgProduto.setImage(image);
        }
    }

    @FXML
    public void salvarProduto(ActionEvent event) throws IOException {
        produto.setNome(nomeLbl.getText());
        produto.setDescricao(descricaoLbl.getText());
        produto.setQuantidade(quantLbl.getText());
        produto.setValor(valorLbl.getText());
        produto.setLocalDaImagem(LocalDaImagem);
        produtoRepository.save(produto);
        produto = new Produto();
        voltar(event);
    }

    private void voltar(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    public void cancelarProduto(ActionEvent event) throws IOException {
        voltar(event);
    }

    public void excluiProduto() {
        AvisoConfirmacaoController avisoConfirmacaoController = new AvisoConfirmacaoController();
        try {
            AvisoConfirmacaoController.ConfirmacaoCallback callback = resultado -> {
                if (resultado) {
                    springContext.getBean(ProdutoRepository.class).deleteById(produto.getIdProduto());
                    AvisoController avisoController = new AvisoController();
                    avisoController.showAlerta(new Stage(), "Produto excluído com sucesso!", false);
                    produto = new Produto();
                }
            };

            avisoConfirmacaoController.showAlertaConfirmacao(new Stage(), "Você tem certeza que deseja excluir este produto?", callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void desabilitarCampos(Boolean desabilitar) {
        if (desabilitar) {
            nomeLbl.setDisable(true);
            descricaoLbl.setDisable(true);
            quantLbl.setDisable(true);
            valorLbl.setDisable(true);
            btnEditarImagem.setDisable(true);
            btnSalvar.setDisable(true);
        }
    }

    public static Produto getProduto() {
        return produto;
    }

    public static void setProduto(Produto produto) {
        ProdutoController.produto = produto;
    }
}
