package com.example.PurpleGym.Controller;

import com.example.PurpleGym.Main;
import com.example.PurpleGym.Model.Cliente;
import com.example.PurpleGym.Model.Produto;
import com.example.PurpleGym.Repository.ClienteRepository;
import com.example.PurpleGym.Repository.ProdutoRepository;
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
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.shape.Line;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.springframework.beans.factory.annotation.Autowired;
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
    private AnchorPane dashBoardListProdutos;

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
    private Button adicionarProdutoBtn;

    @FXML
    private AnchorPane listCliente;

    @FXML
    private TextField pesquisaProdutoTf;

    @FXML
    private Pagination paginacaoCliente;

    @FXML
    private TextField pesquisaClienteTf;

    private Integer itensPorPaginaCliente = 15;
    private Integer itensPorPaginaProduto = 15;

    private Integer produtosPorPagina = 8;

    private ObservableList<Node> paginatedClientes;

    private ObservableList<Node> paginatedProdutos;

    @FXML
    private Pagination paginacaoProduto;

    @Autowired
    public ClienteRepository clienteRepository;

    @Autowired
    public UsuarioRepository usuarioRepository;

    @Autowired
    public ProdutoRepository produtoRepository;

    public static Scene dashBoardView;

    private static final double ORIGINAL_WIDTH = 23.0;
    private static final double EXPANDED_WIDTH = 250.0;

    private static final double ORIGINAL_WIDTH_MOUSE = 54;
    private static final double EXPANDED_WIDTH_MOUSE = 155;

    @FXML
    public void initialize() {
        // Adicione ou ajuste outros inicializadores aqui, se necessário
        menuSlider.setPrefWidth(ORIGINAL_WIDTH);
        setMouseEvents();

        paginatedClientes = FXCollections.observableArrayList();
        paginacaoCliente.setPageFactory(this::createPageCliente);
        paginatedProdutos = FXCollections.observableArrayList();
        paginacaoProduto.setPageFactory(this::createPageProduto);

        // Calcula dinamicamente o número de itens por página para clientes com base no tamanho do componente
        double clienteNodeHeight = 150.0; // Ajuste o tamanho médio desejado para cada nó de cliente
        int numLinhasClientes = (int) (dashBoardListCliente.getHeight() / clienteNodeHeight);

        // Define o número de itens por página para clientes com base no número de linhas
        int itensPorPaginaClientes = numLinhasClientes;
        paginacaoCliente.setPageCount(1); // Apenas uma página para clientes

        // Calcula dinamicamente o número de itens por página para produtos com base na altura da tela
        double produtoNodeHeight = 150.0; // Ajuste o tamanho médio desejado para cada nó de produto
        int numLinhasProdutos = (int) (Screen.getPrimary().getVisualBounds().getHeight() / produtoNodeHeight);

        itensPorPaginaCliente = 15;// Define o número de itens por página para produtos com base no número de linhas
        itensPorPaginaProduto = numLinhasProdutos;
        paginacaoProduto.setPageCount(1); // Apenas uma página para produtos

    }

    private void setMouseEvents() {
        menuSlider.setOnMouseEntered(event -> expandMenuSlider());
        menuSlider.setOnMouseExited(event -> shrinkMenuSlider());

        setButtonMouseEvents(adicionarClienteBtn);
        setButtonMouseEvents(adicionarProdutoBtn);
    }

    private void setButtonMouseEvents(Button button) {
        button.setOnMouseEntered(event -> expandirBotaoCadastro(button));
        button.setOnMouseExited(event -> encolherBotaoCadastro(button));
    }

    private void expandirBotaoCadastro(Button button) {
        animateButtonWidth(button, EXPANDED_WIDTH_MOUSE);
    }

    private void encolherBotaoCadastro(Button button) {
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
    private void LogoutBtnEvent(ActionEvent event) throws Exception {
        LoginController loginController = new LoginController();
        loginController.showLogin(Main.stage);
        fecharDashBoard();

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
    private void ListaProdutosBtnEvent(ActionEvent event) throws IOException {
        this.showPane(dashBoardListProdutos);
        paginacaoCliente.setVisible(false);
        paginacaoProduto.setVisible(true);

    }

    @FXML
    private void AdicionarProdutoBtnEvent(ActionEvent event) throws IOException {
        ProdutoController produtoController = new ProdutoController();
        produtoController.start(new Stage());
    }

    @FXML
    public void PesquisarProdutosBtnEvent(ActionEvent event) throws IOException {
        LoadingController loadingController = new LoadingController();
        Stage loadingStage = new Stage();

        // Inicia o loading
        loadingController.iniciaLoading(loadingStage);

        // Cria uma thread para realizar a consulta em segundo plano
        Thread consultaThread = new Thread(() -> {
            List<Produto> listaProdutos;
            if (!pesquisaProdutoTf.getText().isEmpty()) {
                listaProdutos = produtoRepository.findByNomeContainingIgnoreCaseOrderByNomeAsc(pesquisaProdutoTf.getText());
            } else {
                listaProdutos = produtoRepository.findAllByOrderByNomeAsc();
            }

            // Atualiza a UI na thread principal após a consulta
            Platform.runLater(() -> {
                try {
                    // Preenche a lista paginada
                    paginatedProdutos.clear();
                    for (int i = 0; i < listaProdutos.size(); i++) {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("View/ProdutoList.fxml"));
                        BorderPane borderPane = fxmlLoader.load();

                        ProdutoListController produtoListController = fxmlLoader.getController();
                        produtoListController.setData((Produto) listaProdutos.get(i));

                        paginatedProdutos.add(borderPane);
                    }

                    int pageCount = (int) Math.ceil((double) paginatedProdutos.size() / produtosPorPagina);
                    paginacaoProduto.setPageCount(pageCount);
                    paginacaoProduto.setCurrentPageIndex(0);
                    paginacaoProduto.setPageFactory(this::createPageProduto);

                    // Mostra a paginação
                    paginacaoProduto.setVisible(true);


                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    // Fecha o loading após a consulta
                    loadingController.fecharLoading(loadingStage);
                    trocarPaginas(paginacaoProduto);
                }
            });
        });

        // Inicia a thread de consulta
        consultaThread.start();
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
            if (!pesquisaClienteTf.getText().isEmpty()) {
                listaClientes = clienteRepository.findByNomeContainingIgnoreCaseOrderByNomeAsc(pesquisaClienteTf.getText());
            } else {
                listaClientes = clienteRepository.findAllByOrderByNomeAsc();
            }

            // Atualiza a UI na thread principal após a consulta
            Platform.runLater(() -> {
                try {
                    // Preenche a lista paginada
                    paginatedClientes.clear();
                    for (int i = 0; i < listaClientes.size(); i++) {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("View/ClienteList.fxml"));
                        BorderPane borderPane = fxmlLoader.load();

                        ClientesListController clientesListController = fxmlLoader.getController();
                        clientesListController.setData((Cliente) listaClientes.get(i));

                        paginatedClientes.add(borderPane);
                    }

                    int pageCount = (int) Math.ceil((double) paginatedClientes.size() / itensPorPaginaCliente);
                    paginacaoCliente.setPageCount(pageCount);
                    paginacaoCliente.setCurrentPageIndex(0);
                    paginacaoCliente.setPageFactory(this::createPageCliente);

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

    private void trocarPaginas(Pagination paginacao) {
        paginacao.currentPageIndexProperty().addListener((obs, oldIndex, newIndex) -> {
            paginacao.setCurrentPageIndex(newIndex.intValue());
        });
    }

    @FXML
    private void AdicionarClienteBtnEvent(ActionEvent event) throws IOException {
        ClienteController clienteController = new ClienteController();
        clienteController.start(new Stage());
    }

    private Node createPageCliente(int pageIndex) {
        int fromIndex = pageIndex * itensPorPaginaCliente;
        int toIndex = Math.min(fromIndex + itensPorPaginaCliente, paginatedClientes.size());

        ListView<Node> listView = new ListView<>();
        listView.setItems(FXCollections.observableArrayList(paginatedClientes.subList(fromIndex, toIndex)));
        listView.setStyle("-fx-background-color: transparent;");

        AnchorPane anchorPane = new AnchorPane(listView);
        AnchorPane.setTopAnchor(listView, 0.0);
        AnchorPane.setBottomAnchor(listView, 0.0);
        AnchorPane.setLeftAnchor(listView, 0.0);
        AnchorPane.setRightAnchor(listView, 0.0);

        return anchorPane;
    }

    private Node createPageProduto(int pageIndex) {
        int fromIndex = pageIndex * produtosPorPagina;
        int toIndex = Math.min(fromIndex + produtosPorPagina, paginatedProdutos.size());

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20));
        gridPane.setHgap(20);
        gridPane.setVgap(20);

        double produtoNodeWidth = 150.0;
        int numColunasProdutos = (int) (dashBoardListProdutos.getPrefWidth() / (produtoNodeWidth + 20));

        for (int i = fromIndex; i < toIndex; i++) {
            Node produtoNode = paginatedProdutos.get(i);
            int col = (i - fromIndex) % numColunasProdutos;
            int row = (i - fromIndex) / numColunasProdutos;
            gridPane.add(produtoNode, col, row);
        }

        ScrollPane scrollPane = new ScrollPane(gridPane);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent; ");

        // Centralize o GridPane na tela
        gridPane.setAlignment(Pos.CENTER);

        AnchorPane anchorPane = new AnchorPane(scrollPane);
        anchorPane.setStyle("-fx-background-color: transparent;");

        // Configure as âncoras do ScrollPane dentro da AnchorPane
        AnchorPane.setTopAnchor(scrollPane, 0.0);
        AnchorPane.setBottomAnchor(scrollPane, 0.0);
        AnchorPane.setLeftAnchor(scrollPane, 0.0);
        AnchorPane.setRightAnchor(scrollPane, 0.0);

        return anchorPane;
    }



    private void showPane(AnchorPane pane) {
        dashBoardClientes.setVisible(pane == dashBoardClientes);
        dashBoardConfiguracao.setVisible(pane == dashBoardConfiguracao);
        dashBoardProdutos.setVisible(pane == dashBoardProdutos);
        dashBoardHome.setVisible(pane == dashBoardHome);
        dashBoardPerfil.setVisible(pane == dashBoardPerfil);
        dashBoardListCliente.setVisible(pane == dashBoardListCliente);
        dashBoardListProdutos.setVisible(pane == dashBoardListProdutos);
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

    public void showDashBoard(Stage stage, LoginController loginController) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("View/DashBoard.fxml"));
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        Parent fxmlDashBoardView = fxmlLoader.load();
        dashBoardView = new Scene(fxmlDashBoardView);
        stage.setScene(dashBoardView);
        stage.initStyle(StageStyle.DECORATED);
        stage.setMaximized(true);
        stage.show();
        DashBoardController dashBoardController = fxmlLoader.getController();
        dashBoardController.usuarioLogadoLbl.setText("Olá, " + loginController.getUsuarioLogado().getNome());
    }

    public void fecharDashBoard() {
        Stage stage = (Stage) homeBtn.getScene().getWindow();
        stage.close();
    }
}