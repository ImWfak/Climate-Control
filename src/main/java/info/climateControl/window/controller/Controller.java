package info.climateControl.window.controller;

import info.climateControl.climate.Climate;
import info.climateControl.weather.Weather;
import info.climateControl.day.Day;
import info.climateControl.window.tabs.EditTab;
import info.climateControl.window.tabs.FileTab;
import info.climateControl.window.tabs.ViewTab;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.time.LocalDate;
import java.util.Locale;
import java.util.ResourceBundle;

public class Controller {
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // WINDOW`S ITEMS
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // FILE TAB
    @FXML
    private Tab fileTab;
    @FXML
    private Label fileLabel;
    @FXML
    private Button openFileButton;
    @FXML
    private Button newFileButton;
    @FXML
    private Button saveFileButton;
    @FXML
    private Button saveAsFileButton;
    @FXML
    private Button closeFileButton;
    // EDIT TAB
    @FXML
    private Tab editTab;
    @FXML
    private Label deleteWeatherLabel;
    @FXML
    private Button deleteWeatherBySeasonButton;
    @FXML
    private Button deleteWeatherByCommentButton;
    @FXML
    private Button deleteWeatherByPositionButton;
    @FXML
    private Label deleteDayLabel;
    @FXML
    private Button deleteDayByTemperatureButton;
    @FXML
    private Button deleteDayByDateButton;
    @FXML
    private Button deleteDayByCommentButton;
    @FXML
    private Button deleteDayByPositionButton;
    @FXML
    private Label editWeatherLabel;
    @FXML
    private Button editWeatherBySeasonButton;
    @FXML
    private Button editWeatherByCommentButton;
    @FXML
    private Button editWeatherByPositionButton;
    @FXML
    private Label editDayLabel;
    @FXML
    private Button editDayByTemperatureButton;
    @FXML
    private Button editDayByDateButton;
    @FXML
    private Button editDayByCommentButton;
    @FXML
    private Button editDayByPositionButton;
    @FXML
    private Label addLabel;
    @FXML
    private Button addWeatherButton;
    @FXML
    private Button addDayButton;
    // VIEW TAB
    @FXML
    private Tab viewTab;
    @FXML
    private Label setLanguageLabel;
    @FXML
    private ToggleButton setEnToggleButton;
    @FXML
    private ToggleButton setUaToggleButton;
    @FXML
    private ToggleButton setRuToggleButton;
    @FXML
    private Label fontLabel;
    @FXML
    private ChoiceBox fontChoiceBox;
    @FXML
    private ChoiceBox fontSizeChoiceBox;
    @FXML
    private ColorPicker fontColorPicker;
    // LEFT PANE
    @FXML
    private Button showAllDaysInWeatherButton;
    @FXML
    private Button dayWithLongestCommentInWeatherButton;
    @FXML
    private Button dayWithBiggestTemperatureInWeatherButton;
    @FXML
    private TextField findCommentsTextField;
    @FXML
    private Button findCommentsButton;
    @FXML
    private TextArea findCommentsTextArea;
    // LEFT WEATHER TABLE
    @FXML
    private TreeTableView<Weather> weathersTreeTable;
    @FXML
    private TreeTableColumn<Weather, Integer> weathersPositionColumn;
    @FXML
    private TreeTableColumn<Weather, String> weathersSeasonColumn;
    @FXML
    private TreeTableColumn<Weather, String> weathersCommentColumn;
    // RIGHT DAYS TABLE
    @FXML
    private TableView<Day> daysTable;
    @FXML
    private TableColumn<Day, Integer> daysPositionColumn;
    @FXML
    private TableColumn<Day, Double> daysTemperatureColumn;
    @FXML
    private TableColumn<Day, LocalDate> daysDateColumn;
    @FXML
    private TableColumn<Day, String> daysCommentColumn;
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
    public Button getEditWeatherByCommentButton() { return editWeatherByPositionButton; }
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
        climate = null;
        selectedWeather = null;
        mainWindowResourceBundle = ResourceBundle.getBundle("resourceBundles/mainWindowBundle", Locale.US);
        additionalWindowsResourceBundle = ResourceBundle.getBundle("resourceBundles/additionalWindowsBundle", Locale.US);
        alertResourceBundle = ResourceBundle.getBundle("resourceBundles/alertBundle", Locale.US);
        fileChangesSaved = true;
        fileOpen = false;
        filePath = null;
    }
    public void setLanguage() {

    }
    @FXML
    public void initialize() {

    }
}
