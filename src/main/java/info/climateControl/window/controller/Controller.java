package info.climateControl.window.controller;

import info.climateControl.window.tabs.EditTab;
import info.climateControl.window.tabs.FileTab;
import info.climateControl.window.tabs.ViewTab;
import info.climateControl.climate.Climate;
import info.climateControl.weather.Weather;
import info.climateControl.day.Day;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.scene.layout.Pane;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Locale;

public class Controller {
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // WINDOW`S ITEMS
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // FILE TAB
    @FXML
    private Tab fileTab = new Tab();
    @FXML
    private Label fileLabel = new Label();
    @FXML
    private Button openFileButton = new Button();
    @FXML
    private Button newFileButton = new Button();
    @FXML
    private Button saveFileButton = new Button();
    @FXML
    private Button saveAsFileButton = new Button();
    @FXML
    private Button closeFileButton = new Button();
    // EDIT TAB
    @FXML
    private Tab editTab = new Tab();
    @FXML
    private Label deleteWeatherLabel =  new Label();
    @FXML
    private Button deleteWeatherBySeasonButton = new Button();
    @FXML
    private Button deleteWeatherByCommentButton = new Button();
    @FXML
    private Button deleteWeatherByPositionButton = new Button();
    @FXML
    private Label deleteDayLabel = new Label();
    @FXML
    private Button deleteDayByTemperatureButton = new Button();
    @FXML
    private Button deleteDayByDateButton = new Button();
    @FXML
    private Button deleteDayByCommentButton = new Button();
    @FXML
    private Button deleteDayByPositionButton = new Button();
    @FXML
    private Label editWeatherLabel = new Label();
    @FXML
    private Button editWeatherBySeasonButton = new Button();
    @FXML
    private Button editWeatherByCommentButton = new Button();
    @FXML
    private Button editWeatherByPositionButton = new Button();
    @FXML
    private Label editDayLabel = new Label();
    @FXML
    private Button editDayByTemperatureButton = new Button();
    @FXML
    private Button editDayByDateButton = new Button();
    @FXML
    private Button editDayByCommentButton = new Button();
    @FXML
    private Button editDayByPositionButton = new Button();
    @FXML
    private Label addLabel = new Label();
    @FXML
    private Button addWeatherButton = new Button();
    @FXML
    private Button addDayButton = new Button();
    // VIEW TAB
    @FXML
    private Tab viewTab = new Tab();
    @FXML
    private Label setLanguageLabel = new Label();
    @FXML
    private ToggleButton setEnToggleButton = new ToggleButton();
    @FXML
    private ToggleButton setUaToggleButton = new ToggleButton();
    @FXML
    private ToggleButton setRuToggleButton = new ToggleButton();
    @FXML
    private Label fontLabel = new Label();
    @FXML
    private ChoiceBox fontChoiceBox = new ChoiceBox();
    @FXML
    private ChoiceBox fontSizeChoiceBox = new ChoiceBox();
    @FXML
    private ColorPicker fontColorPicker = new ColorPicker();
    // LEFT PANE
    @FXML
    private Pane leftPane = new Pane();
    @FXML
    private Button showAllDaysInWeatherButton = new Button();
    @FXML
    private Button dayWithLongestCommentInWeatherButton = new Button();
    @FXML
    private Button dayWithBiggestTemperatureInWeatherButton = new Button();
    @FXML
    private TextField findCommentsTextField = new TextField();
    @FXML
    private Button findCommentsButton = new Button();
    @FXML
    private TextArea findCommentsTextArea = new TextArea();
    // LEFT WEATHER TABLE
    @FXML
    private TableView<Weather> weathersTable = new TableView<>();
    @FXML
    private TableColumn<Weather, String> weathersSeasonColumn = new TableColumn<>();
    @FXML
    private TableColumn<Weather, String> weathersCommentColumn = new TableColumn<>();
    @FXML
    private TableColumn<Weather, Weather> weathersPositionColumn = new TableColumn<>();
    // RIGHT DAYS TABLE
    @FXML
    private TableView<Day> daysTable = new TableView<>();
    @FXML
    private TableColumn<Day, Double> daysTemperatureColumn = new TableColumn<>();
    @FXML
    private TableColumn<Day, LocalDate> daysDateColumn = new TableColumn<>();
    @FXML
    private TableColumn<Day, String> daysCommentColumn = new TableColumn<>();
    @FXML
    private TableColumn<Day, Day> daysPositionColumn = new TableColumn<>();
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // GETTERS
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Button getOpenFileButton() { return openFileButton; }
    public Button getNewFileButton() { return newFileButton; }
    public Button getSaveFileButton() { return saveFileButton; }
    public Button getSaveAsFileButton() { return saveAsFileButton; }
    public Button getCloseFileButton() { return closeFileButton; }

    public Button getDeleteWeatherBySeasonButton() { return deleteWeatherBySeasonButton; }
    public Button getDeleteWeatherByCommentButton() { return deleteWeatherByCommentButton; }
    public Button getDeleteWeatherByPositionButton() { return deleteWeatherByPositionButton; }
    public Button getDeleteDayByTemperatureButton() { return deleteDayByTemperatureButton; }
    public Button getDeleteDayByDateButton() { return deleteDayByDateButton; }
    public Button getDeleteDayByCommentButton() { return deleteDayByCommentButton; }
    public Button getDeleteDayByPositionButton() { return deleteDayByPositionButton; }
    public Button getEditWeatherBySeasonButton() { return editWeatherBySeasonButton; }
    public Button getEditWeatherByCommentButton() { return editWeatherByCommentButton; }
    public Button getEditWeatherByPositionButton() { return editWeatherByPositionButton; }
    public Button getEditDayByTemperatureButton() { return editDayByTemperatureButton; }
    public Button getEditDayByDateButton() { return editDayByDateButton; }
    public Button getEditDayByCommentButton() { return editDayByCommentButton; }
    public Button getEditDayByPositionButton() { return editDayByPositionButton; }
    public Button getAddWeatherButton() { return addWeatherButton; }
    public Button getAddDayButton() { return addDayButton; }

    public ToggleButton getSetEnToggleButton() { return setEnToggleButton; }
    public ToggleButton getSetUaToggleButton() { return setUaToggleButton; }
    public ToggleButton getSetRuToggleButton() { return setRuToggleButton; }
    public ChoiceBox getFontChoiceBox() { return fontChoiceBox; }
    public ChoiceBox getFontSizeChoiceBox() { return fontSizeChoiceBox; }
    public ColorPicker getFontColorPicker() { return fontColorPicker; }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // MAIN VARIABLES
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private static final Logger logger = LogManager.getLogger(Controller.class);
    private Climate climate;
    private Weather selectedWeather;
    private ResourceBundle mainWindowResourceBundle;
    private ResourceBundle additionalWindowsResourceBundle;
    private ResourceBundle alertResourceBundle;
    private boolean changesInFileSaved;
    private boolean fileOpen;
    private String filePath;

    private final FileTab fileTabObject = new FileTab(this);
    private final EditTab editTabObject = new EditTab(this);
    private final ViewTab viewTabObject = new ViewTab();
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // GETTERS
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Climate getClimate() { return climate; }
    public Weather getSelectedWeather() {
        return selectedWeather;
    }
    public ResourceBundle getMainWindowResourceBundle() {
        return mainWindowResourceBundle;
    }
    public ResourceBundle getAdditionalWindowsResourceBundle() { return additionalWindowsResourceBundle; }
    public ResourceBundle getAlertResourceBundle() { return alertResourceBundle; }
    public boolean getChangesInFileSaved() {
        return changesInFileSaved;
    }
    public boolean getFileOpen() {
        return fileOpen;
    }
    public String getFilePath() {
        return filePath;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // SETTERS
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void setClimate(Climate climate) {
        logger.info("changed value of climate");
        this.climate = climate;
    }
    public void setSelectedWeather(Weather weather) {
        logger.info("changed value of selectedWeather");
        selectedWeather = weather;
    }
    public void setMainWindowResourceBundle(ResourceBundle mainWindowResourceBundle) {
        logger.info("changed value of mainWindowResourceBundle from '" + this.mainWindowResourceBundle + "' to '" + mainWindowResourceBundle);
        this.mainWindowResourceBundle = mainWindowResourceBundle;
    }
    public void setAdditionalWindowsResourceBundle(ResourceBundle additionalWindowsResourceBundle) {
        logger.info("changed value of additionalWindowsResourceBundle from '" + this.additionalWindowsResourceBundle + "' to '" + additionalWindowsResourceBundle + "'");
        this.additionalWindowsResourceBundle = additionalWindowsResourceBundle;
    }
    public void setAlertResourceBundle(ResourceBundle alertResourceBundle) {
        logger.info("changed value of alertResourceBundle from '" + this.alertResourceBundle + "' to '" + alertResourceBundle + "'");
        this.alertResourceBundle = alertResourceBundle;
    }
    public void setChangesInFileSaved(boolean changesInFileSaved) {
        logger.info("changed value of changesInFileSaved from '" + this.changesInFileSaved + "' to '" + changesInFileSaved + "'");
        this.changesInFileSaved = changesInFileSaved;
    }
    public void setFileOpen(boolean fileOpen) {
        logger.info("changed value of fileOpen from '" + this.fileOpen + "' to '" + fileOpen + "'");
        this.fileOpen = fileOpen;
    }
    public void setFilePath(String filePath) {
        logger.info("changed value of filePath from '" + this.filePath + "' to '" + filePath + "'");
        this.filePath = filePath;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // METHODS
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    {
        climate = new Climate();
        selectedWeather = new Weather();
        mainWindowResourceBundle = ResourceBundle.getBundle("resourceBundles/mainWindowBundle", Locale.US);
        additionalWindowsResourceBundle = ResourceBundle.getBundle("resourceBundles/additionalWindowsBundle", Locale.US);
        alertResourceBundle = ResourceBundle.getBundle("resourceBundles/alertBundle", Locale.US);
        changesInFileSaved = true;
        fileOpen = false;
        filePath = null;
    }
    public void setDisable(boolean bool) {

    }
    public void setLanguage() {
        openFileButton.setText(mainWindowResourceBundle.getString("openFileButton"));
        newFileButton.setText(mainWindowResourceBundle.getString("newFileButton"));
        saveFileButton.setText(mainWindowResourceBundle.getString("saveFileButton"));
        saveAsFileButton.setText(mainWindowResourceBundle.getString("saveAsFileButton"));
        closeFileButton.setText(mainWindowResourceBundle.getString("closeFileButton"));

        deleteWeatherLabel.setText(mainWindowResourceBundle.getString("deleteWeatherLabel"));
        deleteWeatherBySeasonButton.setText(mainWindowResourceBundle.getString("deleteWeatherBySeasonButton"));
        deleteWeatherByCommentButton.setText(mainWindowResourceBundle.getString("deleteWeatherByCommentButton"));
        deleteWeatherByPositionButton.setText(mainWindowResourceBundle.getString("deleteWeatherByPositionButton"));

        deleteDayLabel.setText(mainWindowResourceBundle.getString("deleteDayLabel"));
        deleteDayByTemperatureButton.setText(mainWindowResourceBundle.getString("deleteDayByTemperatureButton"));
        deleteDayByDateButton.setText(mainWindowResourceBundle.getString("deleteDayByDateButton"));
        deleteDayByCommentButton.setText(mainWindowResourceBundle.getString("deleteDayByCommentButton"));
        deleteDayByPositionButton.setText(mainWindowResourceBundle.getString("deleteDayByPositionButton"));

        editWeatherLabel.setText(mainWindowResourceBundle.getString("editWeatherLabel"));
        editWeatherBySeasonButton.setText(mainWindowResourceBundle.getString("editWeatherBySeasonButton"));
        editWeatherByCommentButton.setText(mainWindowResourceBundle.getString("editWeatherByCommentButton"));
        editWeatherByPositionButton.setText(mainWindowResourceBundle.getString("editWeatherByPositionButton"));

        editDayLabel.setText(mainWindowResourceBundle.getString("editDayLabel"));
        editDayByTemperatureButton.setText(mainWindowResourceBundle.getString("editDayByTemperatureButton"));
        editDayByDateButton.setText(mainWindowResourceBundle.getString("editDayByDateButton"));
        editDayByCommentButton.setText(mainWindowResourceBundle.getString("editDayByCommentButton"));
        editDayByPositionButton.setText(mainWindowResourceBundle.getString("editDayByPositionButton"));

        addLabel.setText(mainWindowResourceBundle.getString("addLabel"));
        addWeatherButton.setText(mainWindowResourceBundle.getString("addWeatherButton"));
        addDayButton.setText(mainWindowResourceBundle.getString("addDayButton"));

        editTabObject.getWeathersPositionColumn().setText(additionalWindowsResourceBundle.getString("weathersPositionColumn"));
        editTabObject.getWeathersSeasonColumn().setText(additionalWindowsResourceBundle.getString("weathersSeasonColumn"));
        editTabObject.getWeathersCommentColumn().setText(additionalWindowsResourceBundle.getString("weathersCommentColumn"));
        editTabObject.getDaysPositionColumn().setText(additionalWindowsResourceBundle.getString("daysPositionColumn"));
        editTabObject.getDaysTemperatureColumn().setText(additionalWindowsResourceBundle.getString("daysTemperatureColumn"));
        editTabObject.getDaysDateColumn().setText(additionalWindowsResourceBundle.getString("daysDateColumn"));
        editTabObject.getDaysCommentColumn().setText(additionalWindowsResourceBundle.getString("daysCommentColumn"));
        editTabObject.getFindField().setPromptText(additionalWindowsResourceBundle.getString("findField"));
        editTabObject.getFindButton().setText(additionalWindowsResourceBundle.getString("findButton"));
        editTabObject.getDeleteAllButton().setText(additionalWindowsResourceBundle.getString("deleteAllButton"));
        editTabObject.getDeleteSelectedButton().setText(additionalWindowsResourceBundle.getString("deleteSelectedButton"));
        editTabObject.getWeatherSeasonField().setPromptText(additionalWindowsResourceBundle.getString("weatherSeasonField"));
        editTabObject.getWeatherCommentField().setPromptText(additionalWindowsResourceBundle.getString("weatherCommentField"));
        editTabObject.getDayTemperatureField().setPromptText(additionalWindowsResourceBundle.getString("dayTemperatureField"));
        editTabObject.getDayDateField().setPromptText(additionalWindowsResourceBundle.getString("dayDateField"));
        editTabObject.getDayCommentField().setPromptText(additionalWindowsResourceBundle.getString("dayCommentField"));
        editTabObject.getEditButton().setText(additionalWindowsResourceBundle.getString("editButton"));
        editTabObject.getAddButton().setText(additionalWindowsResourceBundle.getString("addButton"));

        setLanguageLabel.setText(mainWindowResourceBundle.getString("setLanguageLabel"));
        setEnToggleButton.setText(mainWindowResourceBundle.getString("setEnToggleButton"));
        setUaToggleButton.setText(mainWindowResourceBundle.getString("setUaToggleButton"));
        setRuToggleButton.setText(mainWindowResourceBundle.getString("setRuToggleButton"));
        fontLabel.setText(mainWindowResourceBundle.getString("fontLabel"));

        showAllDaysInWeatherButton.setText(mainWindowResourceBundle.getString("showAllDaysInWeatherButton"));
        dayWithLongestCommentInWeatherButton.setText(mainWindowResourceBundle.getString("dayWithLongestCommentInWeatherButton"));
        dayWithBiggestTemperatureInWeatherButton.setText(mainWindowResourceBundle.getString("dayWithBiggestTemperatureInWeatherButton"));
        findCommentsTextField.setPromptText(mainWindowResourceBundle.getString("findCommentTextField"));
        findCommentsButton.setText(mainWindowResourceBundle.getString("findCommentButton"));

        weathersSeasonColumn.setText(mainWindowResourceBundle.getString("weathersSeasonColumn"));
        weathersCommentColumn.setText(mainWindowResourceBundle.getString("weathersCommentColumn"));
        weathersPositionColumn.setText(mainWindowResourceBundle.getString("weathersPositionColumn"));

        daysTemperatureColumn.setText(mainWindowResourceBundle.getString("daysTemperatureColumn"));
        daysDateColumn.setText(mainWindowResourceBundle.getString("daysDateColumn"));
        daysCommentColumn.setText(mainWindowResourceBundle.getString("daysCommentColumn"));
        daysPositionColumn.setText(mainWindowResourceBundle.getString("daysPositionColumn"));
    }
    public void setFont() {

    }
    public void setFontSize() {

    }
    public void setFontColor() {

    }
    public void initializeWeathersTable() {
        weathersPositionColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue()));
        weathersPositionColumn.setCellFactory(cellData -> new TableCell<Weather, Weather>() {
            @Override
            protected void updateItem(Weather weather, boolean empty) {
                super.updateItem(weather, empty);
                if (weather != null || !empty)
                    setText(String.valueOf(getIndex() + 1));
            }
        });
        weathersSeasonColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSeason()));
        weathersCommentColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getComment()));
        weathersTable.getColumns().setAll(
                weathersPositionColumn,
                weathersSeasonColumn,
                weathersCommentColumn
        );
    }
    public void initializeDaysTable() {
        daysPositionColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue()));
        daysPositionColumn.setCellFactory(cellData -> new TableCell<Day, Day>() {
            @Override
            protected void updateItem(Day day, boolean empty) {
                super.updateItem(day, empty);
                if (day != null || !empty)
                    setText(String.valueOf(getIndex() + 1));
            }
        });
        daysTemperatureColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getTemperature()).asObject());
        daysDateColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getDate()));
        daysCommentColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getComment()));
        daysTable.getColumns().setAll(
                daysPositionColumn,
                daysTemperatureColumn,
                daysDateColumn,
                daysCommentColumn
        );
    }
    public void fillWeathersTable(ArrayList<Weather> weathers) {
        weathersTable.getItems().setAll(FXCollections.observableArrayList(weathers));
    }
    public void fillDaysTable(ArrayList<Day> days) {
        daysTable.getItems().setAll(FXCollections.observableArrayList(days));
    }
    public void selectWeatherInTable() {
        weathersTable.setOnMouseClicked(actionEvent -> {
            if (!weathersTable.getSelectionModel().isEmpty()) {
                setSelectedWeather(weathersTable.getSelectionModel().getSelectedItem());
                fillDaysTable(weathersTable.getSelectionModel().getSelectedItem().getDays());
            }
        });
    }
    @FXML
    public void initialize() {
        initializeWeathersTable();
        initializeDaysTable();
        setLanguage();

        fileTabObject.pressedOpenFileButton();
        fileTabObject.pressedNewFileButton();
        fileTabObject.pressedSaveFileButton();
        fileTabObject.pressedSaveAsFileButton();
        fileTabObject.pressedCloseFileButton();

        editTabObject.pressedDeleteWeatherBySeasonButton();
        editTabObject.pressedDeleteWeatherByCommentButton();
        editTabObject.pressedDeleteWeatherByPositionButton();
        editTabObject.pressedDeleteDayByTemperatureButton();
        editTabObject.pressedDeleteDayByDateButton();
        editTabObject.pressedDeleteDayByCommentButton();
        editTabObject.pressedDeleteDayByPositionButton();
        editTabObject.pressedEditWeatherBySeasonButton();
        editTabObject.pressedEditWeatherByCommentButton();
        editTabObject.pressedEditWeatherByPositionButton();
        editTabObject.pressedEditDayByTemperatureButton();
        editTabObject.pressedEditDayByDateButton();
        editTabObject.pressedEditDayByCommentButton();
        editTabObject.pressedEditeDayByPositionButton();
        editTabObject.pressedAddWeatherButton();
        editTabObject.pressedAddDayButton();

        selectWeatherInTable();
    }
}
