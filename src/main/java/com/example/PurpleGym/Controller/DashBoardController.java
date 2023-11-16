package com.example.PurpleGym.Controller;

import com.example.PurpleGym.Model.Cliente;
import com.example.PurpleGym.Repository.ClienteRepository;
import com.example.PurpleGym.Repository.UsuarioRepository;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.util.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class DashBoardController {

    @FXML
    private VBox menuSlider;

    @FXML
    private Button homeBtn;

    @FXML
    private Button logoutBtn;

    @FXML
    private Button configuracaoBtn;

    @FXML
    private Line lineEspacamento;

    @FXML
    private Button clientesBtn;

    @FXML
    private Button perfilBtn;

    @FXML
    private Button produtosBtn;

    @FXML
    private AnchorPane dashBoardClientes;

    @FXML
    private AnchorPane dashBoardConfiguracao;

    @FXML
    private AnchorPane dashBoardProdutos;

    @FXML
    private AnchorPane dashBoardHome;

    @FXML
    private AnchorPane dashBoardPerfil;

    @FXML
    private Label moduloLbl;

    @FXML
    private AnchorPane dashBoardListCliente;

    @FXML
    private Button pesquisarClientesBtn;

    @FXML
    private ListView listaClienteLv;

    @FXML
    private GridPane grid;

    @Autowired
    public ClienteRepository clienteRepository;

    @Autowired
    public UsuarioRepository usuarioRepository;

    private static final double ORIGINAL_WIDTH = 23.0;
    private static final double EXPANDED_WIDTH = 250.0;

    @FXML
    public void initialize() {
        // Adicione ou ajuste outros inicializadores aqui, se necessário
        menuSlider.setPrefWidth(ORIGINAL_WIDTH);
        setMouseEvents();


    }

    private void setMouseEvents() {
        menuSlider.setOnMouseEntered(event -> expandMenuSlider());
        menuSlider.setOnMouseExited(event -> shrinkMenuSlider());
    }

    private void expandMenuSlider() {
        animateWidth(EXPANDED_WIDTH);
        produtosBtn.setText("Produtos");
        configuracaoBtn.setText("Configurações");
        perfilBtn.setText("Perfil");
        clientesBtn.setText("Clientes");
        homeBtn.setText("Home");
        logoutBtn.setText("Logout");
    }

    private void shrinkMenuSlider() {
        animateWidth(60.0);
        produtosBtn.setText("");
        configuracaoBtn.setText("");
        perfilBtn.setText("");
        clientesBtn.setText("");
        homeBtn.setText("");
        logoutBtn.setText("");
    }
    @FXML
    private void ClientesBtnEvent(ActionEvent event) {
        this.showPane(dashBoardClientes);
        moduloLbl.setText("Clientes");
    }

    @FXML
    private void ConfiguracaoBtnEvent(ActionEvent event) {
        this.showPane(dashBoardConfiguracao);
        moduloLbl.setText("Configurações");

    }

    @FXML
    private void HomeBtnEvent(ActionEvent event) {
        this.showPane(dashBoardHome);
        moduloLbl.setText("Home");
    }

    @FXML
    private void LogoutBtnEvent(ActionEvent event) {
    }

    @FXML
    private void PerfilBtnEvent(ActionEvent event) {
        this.showPane(dashBoardPerfil);
        moduloLbl.setText("Perfil");
    }

    @FXML
    private void ProdutosBtnEvent(ActionEvent event) {
        this.showPane(dashBoardProdutos);
        moduloLbl.setText("Produtos");
    }

    @FXML
    private void ListaClientesBtnEvent(ActionEvent event) {
        this.showPane(dashBoardListCliente);
    }

    @FXML
    private void PesquisarClientesBtnEvent(ActionEvent event) throws IOException {
        List listaClientes = clienteRepository.findAll();
        for (int i = 0; i < listaClientes.size(); i++) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("View/ClienteList.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();

            ClientesListController clientesListController = fxmlLoader.getController();
            clientesListController.setData((Cliente) listaClientes.get(i));



            grid.add(anchorPane, 0, i); //(child,column,row)

            //set grid width
            grid.setMinWidth(Region.USE_COMPUTED_SIZE);
            grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
            grid.setMaxWidth(Region.USE_PREF_SIZE);

            //set grid height
            grid.setMinHeight(Region.USE_COMPUTED_SIZE);
            grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
            grid.setMaxHeight(Region.USE_PREF_SIZE);

            GridPane.setMargin(anchorPane, new Insets(10));
        }
    }

    private void showPane(AnchorPane pane) {
        dashBoardClientes.setVisible(pane == dashBoardClientes);
        dashBoardConfiguracao.setVisible(pane == dashBoardConfiguracao);
        dashBoardProdutos.setVisible(pane == dashBoardProdutos);
        dashBoardHome.setVisible(pane == dashBoardHome);
        dashBoardPerfil.setVisible(pane == dashBoardPerfil);
        dashBoardListCliente.setVisible(pane == dashBoardListCliente);
    }


    private void animateWidth(double targetWidth) {
        Timeline timeline = new Timeline();
        KeyValue keyValue = new KeyValue(menuSlider.prefWidthProperty(), targetWidth);
        KeyFrame keyFrame = new KeyFrame(Duration.millis(300), keyValue);
        timeline.getKeyFrames().add(keyFrame);

        KeyValue keyValuesClientes = new KeyValue(clientesBtn.prefWidthProperty(), targetWidth);
        KeyFrame keyFrameClientes = new KeyFrame(Duration.millis(300), keyValuesClientes);
        timeline.getKeyFrames().add(keyFrameClientes);

        KeyValue keyValuesPerfil = new KeyValue(perfilBtn.prefWidthProperty(), targetWidth);
        KeyFrame keyFramePerfil = new KeyFrame(Duration.millis(300), keyValuesPerfil);
        timeline.getKeyFrames().add(keyFramePerfil);

        KeyValue keyValuesConfiguracao = new KeyValue(configuracaoBtn.prefWidthProperty(), targetWidth);
        KeyFrame keyFrameConfiguracao = new KeyFrame(Duration.millis(300), keyValuesConfiguracao);
        timeline.getKeyFrames().add(keyFrameConfiguracao);


        KeyValue keyValuesProdutos = new KeyValue(produtosBtn.prefWidthProperty(), targetWidth);
        KeyFrame keyFrameProdutos = new KeyFrame(Duration.millis(300), keyValuesProdutos);
        timeline.getKeyFrames().add(keyFrameProdutos);

        KeyValue keyValuesHome = new KeyValue(homeBtn.prefWidthProperty(), targetWidth);
        KeyFrame keyFrameHome = new KeyFrame(Duration.millis(300), keyValuesHome);
        timeline.getKeyFrames().add(keyFrameHome);

        KeyValue keyValuesLogout = new KeyValue(logoutBtn.prefWidthProperty(), targetWidth);
        KeyFrame keyFrameLogout = new KeyFrame(Duration.millis(300), keyValuesLogout);
        timeline.getKeyFrames().add(keyFrameLogout);

        KeyValue keyValuesLine = new KeyValue(lineEspacamento.endXProperty(), targetWidth);
        KeyFrame keyFrameLine = new KeyFrame(Duration.millis(300), keyValuesLine);
        timeline.getKeyFrames().add(keyFrameLine);

        timeline.play();
    }
}