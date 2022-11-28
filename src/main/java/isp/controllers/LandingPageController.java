package isp.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import isp.constants.IConstant;
import isp.database.ProjectDB;
import isp.models.Project;
import isp.models.Response;
import isp.models.ResultModel;
import isp.services.EstimateService;
import isp.services.GenerateService;
import isp.services.ValidityService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.math.BigInteger;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ResourceBundle;

import static isp.constants.IConstant.dataLimit;

public class LandingPageController implements Initializable {
    @FXML
    private JFXRadioButton acoc, ecc, bcc;
    @FXML
    private ToggleGroup radioGroup;
    @FXML
    TextArea inputArea, responseArea, historyInputArea;
    @FXML
    private JFXButton estimate, generate;

    @FXML
    private TableView<ResultModel> resultTableView, historyResultTableView;
    @FXML
    private TableView<Project> historyTableView;
    @FXML
    private TableColumn<Project, String> id, time, category;
    @FXML
    private TableColumn<Project, Integer> counter, generatedTests;
    @FXML
    private TableColumn<Project, BigInteger> possibleTests;

    @FXML
    TabPane tabpane;
    @FXML
    Tab help;

    private final ProjectDB projectDB = new ProjectDB();

    ObservableList<ResultModel> obs = FXCollections.observableArrayList();
    ObservableList<Project> projects = FXCollections.observableArrayList();
    ObservableList<ResultModel> historyObs = FXCollections.observableArrayList();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        inputArea.setPromptText(IConstant.inputPrompt);
        projectDB.createTable();

        Label label = new Label("To obtain test cases click 'Generate'");
        label.setStyle("-fx-text-fill: #294469; -fx-font-size: 18px;");
        resultTableView.setPlaceholder(label);

        historyTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                historyInputArea.setText( newSelection.input.getValue() );
                generateForHistory( newSelection.input.getValue(), newSelection.category.getValue());
            }
        });

        label = new Label("Select a row from history to view test cases.");
        label.setStyle("-fx-text-fill: #294469; -fx-font-size: 18px;");
        historyResultTableView.setPlaceholder(label);

        label = new Label("No history found");
        label.setStyle("-fx-text-fill: #294469; -fx-font-size: 18px;");
        historyTableView.setPlaceholder(label);
    }

    private String getSelectedRadioButton() {
        return ((JFXRadioButton) radioGroup.getSelectedToggle().selectedProperty().getBean()).idProperty().getValue();
    }

    @FXML
    private void estimate(){

        responseArea.setText("");
        ValidityService service = new ValidityService();
        Response response = service.checkIfValid(inputArea.getText());
        if( response.type == Response.Type.FAILED) {
            responseArea.setText( response.message );
            resultTableView.getColumns().clear();
            return;
        }

        LinkedHashMap<String, String> data = service.getdata(inputArea.getText());
        EstimateService estimateService = new EstimateService();

        String button = getSelectedRadioButton();
        responseArea.setText( "Total possible combinations: " +estimateService.findEstimate(data, button).toString());

    }

    @FXML
    private void generate(){
        responseArea.setText("");
        ValidityService service = new ValidityService();
        Response response = service.checkIfValid(inputArea.getText());
        if( response.type == Response.Type.FAILED) {
            responseArea.setText( response.message );
            resultTableView.getColumns().clear();
            return;
        }

        LinkedHashMap<String, String> data = service.getdata(inputArea.getText());
        String button = getSelectedRadioButton();

        List<ResultModel> output = GenerateService.generate(data, button);
        obs.clear();
        obs.addAll(output);

        resultTableView.getColumns().clear();
        TableColumn<ResultModel, Integer> numberColumn = new TableColumn<>("Line" );
        numberColumn.setPrefWidth(70);
        resultTableView.getColumns().add(numberColumn);
        numberColumn.setCellValueFactory(cellData -> cellData.getValue().serial.asObject());

        int i = 0;
        for(String header: GenerateService.getHeaders(data)) {
            TableColumn<ResultModel, String> col = new TableColumn<>(header );
            col.setPrefWidth(150);
            resultTableView.getColumns().add(col);
            int finalI = i++;
            col.setCellValueFactory(cellData -> cellData.getValue().characteristics.get(finalI));
        }

        BigInteger estimate = new EstimateService().findEstimate(data, button);
        if( obs.size() >= dataLimit) responseArea.setText( estimate.toString()+" test cases are possible.\nBut only first "+dataLimit+" test cases are generated.\n\n" +
                "This is the maximum limit that this tool can generate." );
        else responseArea.setText( obs.size()+" test cases are generated." );
        resultTableView.setItems(obs);


        projectDB.addProject( new Project(button, inputArea.getText(), obs.size(), estimate));


    }


    @FXML
    private void loadHistory(){
        loadDB();

        id.setCellValueFactory(cellData -> cellData.getValue().projectId);
        counter.setCellValueFactory(cellData -> cellData.getValue().counter.asObject());
        time.setCellValueFactory(cellData -> cellData.getValue().dateTime);
        category.setCellValueFactory(cellData -> cellData.getValue().category);
        generatedTests.setCellValueFactory(cellData -> cellData.getValue().generatedTests.asObject());
        possibleTests.setCellValueFactory(cellData -> cellData.getValue().possibleTests);

        historyTableView.setItems(projects);
    }


    private void loadDB() {
        projects.clear();
        projects.addAll(projectDB.projectList());
    }


    @FXML
    private void generateForHistory(String input, String category){

        ValidityService service = new ValidityService();
        LinkedHashMap<String, String> data = service.getdata(input);

        List<ResultModel> output = GenerateService.generate(data, category);
        historyObs.clear();
        historyObs.addAll(output);

        historyResultTableView.getColumns().clear();
        TableColumn<ResultModel, Integer> numberColumn = new TableColumn<>("Line" );
        numberColumn.setPrefWidth(70);
        historyResultTableView.getColumns().add(numberColumn);
        numberColumn.setCellValueFactory(cellData -> cellData.getValue().serial.asObject());

        int i = 0;
        for(String header: GenerateService.getHeaders(data)) {
            TableColumn<ResultModel, String> col = new TableColumn<>(header );
            col.setPrefWidth(150);
            historyResultTableView.getColumns().add(col);
            int finalI = i++;
            col.setCellValueFactory(cellData -> cellData.getValue().characteristics.get(finalI));
        }

        historyResultTableView.setItems(historyObs);

    }

    @FXML
    private void deleteAllHisory(){

        if(projects.size() == 0 ) return;
        projectDB.dropProject();
        loadDB();
        historyInputArea.setText("");
        historyResultTableView.getColumns().clear();
    }

    @FXML
    private void goToHelpTab() {
        tabpane.getSelectionModel().select(help);
    }


}