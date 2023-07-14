package info.climateControl.window.controller;

import info.climateControl.window.tabs.EditTab;
import info.climateControl.window.tabs.FileTab;
import info.climateControl.window.tabs.ViewTab;
import info.climateControl.climate.Climate;
import info.climateControl.weather.Weather;
import info.climateControl.day.Day;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.layout.Pane;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.time.LocalDate;
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
    private TableView<Weather> weathersTable;
    @FXML
    private TableColumn<Weather, Integer> weathersPositionColumn = new TableColumn<>();
    @FXML
    private TableColumn<Weather, String> weathersSeasonColumn = new TableColumn<>();
    @FXML
    private TableColumn<Weather, String> weathersCommentColumn = new TableColumn<>();
    // RIGHT DAYS TABLE
    @FXML
    private TableView<Day> daysTable;
    @FXML
    private TableColumn<Day, Integer> daysPositionColumn = new TableColumn<>();
    @FXML
    private TableColumn<Day, Double> daysTemperatureColumn = new TableColumn<>();
    @FXML
    private TableColumn<Day, LocalDate> daysDateColumn = new TableColumn<>();
    @FXML
    private TableColumn<Day, String> daysCommentColumn = new TableColumn<>();
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
    private boolean fileChangesSaved;
    private boolean fileOpen;
    private String filePath;

    private final FileTab fileTabObject = new FileTab();
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
    public boolean getFileChangesSaved() {
        return fileChangesSaved;
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
        this.climate = climate;
        logger.info("changed value of climate");
    }
    public void setSelectedWeather(Weather weather) {
        selectedWeather = weather;
        logger.info("changed value of selectedWeather");
    }
    public void setMainWindowResourceBundle(ResourceBundle mainWindowResourceBundle) {
        this.mainWindowResourceBundle = mainWindowResourceBundle;
        logger.info("changed value of mainWindowResourceBundle");
    }
    public void setAdditionalWindowsResourceBundle(ResourceBundle additionalWindowsResourceBundle) {
        this.additionalWindowsResourceBundle = additionalWindowsResourceBundle;
        logger.info("changed value of 'additionalWindowsResourceBundle'");
    }
    public void setAlertResourceBundle(ResourceBundle alertResourceBundle) {
        this.alertResourceBundle = alertResourceBundle;
        logger.info("changed value of alertResourceBundle");
    }
    public void setFileChangesSaved(boolean fileChangesSaved) {
        this.fileChangesSaved = fileChangesSaved;
        logger.info("changed value of fileChangesSaved to '" + this.fileChangesSaved + "'");
    }
    public void setFileOpen(boolean fileOpen) {
        this.fileOpen = fileOpen;
        logger.info("changed value of fileOpen to '" + this.fileOpen + "'");
    }
    public void setFilePath(String filePath) {
        this.filePath = filePath;
        logger.info("changed value of filePath");
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
        fileChangesSaved = true;
        fileOpen = false;
        filePath = null;

        weathersPositionColumn.setCellValueFactory(column -> {
            return new TableCell<Weather, Integer>() {
                @Override
                protected void updateItem(Integer item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item != null || !empty)
                        setText(String.valueOf(getIndex() + 1));
                }
            }.itemProperty();
        });
        weathersSeasonColumn.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getSeason()));
        weathersCommentColumn.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getComment()));
        daysPositionColumn.setCellValueFactory(column -> {
            return new TableCell<Weather, Integer>() {
                @Override
                protected void updateItem(Integer item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item != null || !empty)
                        setText(String.valueOf(getIndex() + 1));
                }
            }.itemProperty();
        });
        daysTemperatureColumn.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getTemperature()));
        daysDateColumn.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getDate()));
        daysCommentColumn.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getComment()));

        if (!fileOpen)
            setDisable(true);
    }
    public void setDisable(boolean bool) {
        saveFileButton.setDisable(bool);
        saveAsFileButton.setDisable(bool);
        closeFileButton.setDisable(bool);
        editTab.setDisable(bool);
        leftPane.setDisable(bool);
    }
    public void setLanguage() {

    }
    public void setFont() {

    }
    public void setFontSize() {

    }
    public void setFontColor() {

    }
    public void updateWeathersTable() {

    }
    public void updateDaysTable() {

    }
    @FXML
    public void initialize() {
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
    }
}
