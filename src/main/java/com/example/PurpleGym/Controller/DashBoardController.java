package com.example.PurpleGym.Controller;

import com.example.PurpleGym.Main;
import com.example.PurpleGym.Model.Cliente;
import com.example.PurpleGym.Repository.ClienteRepository;
import com.example.PurpleGym.Repository.UsuarioRepository;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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
    private Label usuarioLogadoLbl;

    @FXML
    private AnchorPane dashBoardListCliente;

    @FXML
    private Button adicionarClienteBtn;

    @FXML
    private AnchorPane listCliente;

    @FXML
    private Pagination paginacaoCliente;

    @FXML
    private TextField pesquisaClienteTf;

    private Integer itensPorPagina = 15;// Defina o número de itens por página conforme necessário

    private ObservableList<Node> paginatedItems;

    @Autowired
    public ClienteRepository clienteRepository;

    @Autowired
    public UsuarioRepository usuarioRepository;

    public static Scene dashBoardView;

    private static final double ORIGINAL_WIDTH = 23.0;
    private static final double EXPANDED_WIDTH = 250.0;

    private static final double ORIGINAL_WIDTH_MOUSE = 54;
    private static final double EXPANDED_WIDTH_MOUSE = 150;

    @FXML
    public void initialize() {
        // Adicione ou ajuste outros inicializadores aqui, se necessário
        menuSlider.setPrefWidth(ORIGINAL_WIDTH);
        setMouseEvents();

        paginatedItems = FXCollections.observableArrayList();
        paginacaoCliente.setPageFactory(this::createPage);
    }

    private void setMouseEvents() {
        menuSlider.setOnMouseEntered(event -> expandMenuSlider());
        menuSlider.setOnMouseExited(event -> shrinkMenuSlider());

        setButtonMouseEvents(adicionarClienteBtn);
    }

    private void setButtonMouseEvents(Button button) {
        button.setOnMouseEntered(event -> expandirBotaoNovoCliente(button));
        button.setOnMouseExited(event -> encolherBotaoNovoCliente(button));
    }

    private void expandirBotaoNovoCliente(Button button) {
        animateButtonWidth(button, EXPANDED_WIDTH_MOUSE);
    }

    private void encolherBotaoNovoCliente(Button button) {
        animateButtonWidth(button, ORIGINAL_WIDTH_MOUSE);
    }

    private void animateButtonWidth(Button button, double targetWidth) {
        Timeline timeline = new Timeline();
        KeyValue keyValue = new KeyValue(button.prefWidthProperty(), targetWidth);
        KeyFrame keyFrame = new KeyFrame(Duration.millis(300), keyValue);
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
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
    private void ListaClientesBtnEvent(ActionEvent event) throws IOException {
        this.showPane(dashBoardListCliente);
        paginacaoCliente.setVisible(false);

    }

    @FXML
    public void PesquisarClientesBtnEvent(ActionEvent event) throws IOException {
        LoadingController loadingController = new LoadingController();
        Stage loadingStage = new Stage();

        // Inicia o loading
        loadingController.iniciaLoading(loadingStage);

        // Cria uma thread para realizar a consulta em segundo plano
        Thread consultaThread = new Thread(() -> {
            List<Cliente> listaClientes;
            if(!pesquisaClienteTf.getText().isEmpty()) {
                listaClientes = clienteRepository.findByNomeContainingIgnoreCase(pesquisaClienteTf.getText());
            } else {
                listaClientes = clienteRepository.findAll();
            }

            // Atualiza a UI na thread principal após a consulta
            Platform.runLater(() -> {
                try {
                    // Preenche a lista paginada
                    paginatedItems.clear();
                    for (int i = 0; i < listaClientes.size(); i++) {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("View/ClienteList.fxml"));
                        BorderPane borderPane = fxmlLoader.load();

                        ClientesListController clientesListController = fxmlLoader.getController();
                        clientesListController.setData((Cliente) listaClientes.get(i));

                        paginatedItems.add(borderPane);
                    }

                    int pageCount = (int) Math.ceil((double) paginatedItems.size() / itensPorPagina);
                    paginacaoCliente.setPageCount(pageCount);
                    paginacaoCliente.setCurrentPageIndex(0);
                    paginacaoCliente.setPageFactory(this::createPage);

                    // Mostra a paginação
                    paginacaoCliente.setVisible(true);


                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    // Fecha o loading após a consulta
                    loadingController.fecharLoading(loadingStage);
                    trocarPaginas(paginacaoCliente);
                }
            });
        });

        // Inicia a thread de consulta
        consultaThread.start();
    }

    private void trocarPaginas(Pagination paginacaoCliente) {
        paginacaoCliente.currentPageIndexProperty().addListener((obs, oldIndex, newIndex) -> {
            paginacaoCliente.setCurrentPageIndex(newIndex.intValue());
        });

    }

    @FXML
    private void AdicionarClienteBtnEvent(ActionEvent event) throws IOException {
        ClienteController clienteController = new ClienteController();
        clienteController.start(new Stage());
    }

    private Node createPage(int pageIndex) {
        int fromIndex = pageIndex * itensPorPagina;
        int toIndex = Math.min(fromIndex + itensPorPagina, paginatedItems.size());

        ListView<Node> listView = new ListView<>();
        listView.setItems(FXCollections.observableArrayList(paginatedItems.subList(fromIndex, toIndex)));
        listView.setStyle("-fx-background-color: transparent;");

        AnchorPane anchorPane = new AnchorPane(listView);
        AnchorPane.setTopAnchor(listView, 0.0);
        AnchorPane.setBottomAnchor(listView, 0.0);
        AnchorPane.setLeftAnchor(listView, 0.0);
        AnchorPane.setRightAnchor(listView, 0.0);

        return anchorPane;
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

    public void showDashBoard(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("View/DashBoard.fxml"));
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        Parent fxmlDashBoardView = fxmlLoader.load();
        dashBoardView = new Scene(fxmlDashBoardView);
        stage.setScene(dashBoardView);
        stage.initStyle(StageStyle.DECORATED);
        stage.setMaximized(true);
        stage.show();
    }
}