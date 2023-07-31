package info.climateControl.window.tabs;

import info.climateControl.climate.Climate;
import info.climateControl.window.controller.Controller;
import info.climateControl.window.alerts.Alerts;
import info.climateControl.weather.Weather;
import info.climateControl.day.Day;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.FileChooser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.google.common.io.Files;
import java.io.File;
import java.util.ArrayList;
import java.util.Optional;

/**<p><b>methods in FileTab</b></p>
 * <p>private:</p>
 * <li>{@link #getFileChooser()}</li>
 * <li>{@link #openFile(String)}</li>
 * <li>{@link #saveFile(String)}</li>
 * <li>{@link #setVariables(boolean, boolean, String, Weather, ArrayList, ArrayList)}</li>
 * <p>public:</p>
 * <li>{@link #pressedOpenFileButton()}</li>
 * <li>{@link #pressedNewFileButton()}</li>
 * <li>{@link #pressedSaveFileButton()}</li>
 * <li>{@link #pressedSaveAsFileButton()}</li>
 * <li>{@link #pressedCloseFileButton()}</li> */
public class FileTab implements Alerts {
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // MAIN VARIABLES
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private static final Logger logger = LogManager.getLogger(FileTab.class);
    private final Controller controller;
    /** constructor which sets controller of current fileTab object
     * @param controller    fxml class which will be set as controller */
    public FileTab(Controller controller) {
        this.controller = controller;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // PRIVATE METHODS
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /** method which return FileChooser which extensionFilters
     * @return FileChooser */
    private FileChooser getFileChooser() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Files", "*.*"),
                new FileChooser.ExtensionFilter("Data Bases", "*.db"),
                new FileChooser.ExtensionFilter("XML Files", "*.xml"),
                new FileChooser.ExtensionFilter("JSON Files", "*.json"),
                new FileChooser.ExtensionFilter("TXT Files", "*.txt")
        );
        return fileChooser;
    }
    private File getFileFromSaveDialog(Button button) {
        return getFileChooser().showSaveDialog(button.getParent().getScene().getWindow());
    }
    private File getFileFromOpenDialog(Button button) {
        return getFileChooser().showOpenDialog(button.getParent().getScene().getWindow());
    }
    /** method which read .db, .xml, .json and .txt files and write to current climate object
     * @param filePath String as path to file */
    private void openFile(String filePath) {
        switch (Files.getFileExtension(filePath)) {
            case "db" -> controller.getClimate().readFromDB(filePath);
            case "xml" -> controller.getClimate().readFromXML(filePath);
            case "json" -> controller.getClimate().readFromJSON(filePath);
            case "txt" -> controller.getClimate().readFromTXT(filePath);
            default -> Alerts.createWrongFileExtensionAlert(controller.getAlertResourceBundle()).show();
        }
    }
    /** method which write current climate object to .db, .xml, .json and .txt files
     * @param filePath String as path to file */
    private void saveFile(String filePath) {
        switch (Files.getFileExtension(filePath)) {
            case "db" -> controller.getClimate().writeToDB(filePath);
            case "xml" -> controller.getClimate().writeToXML(filePath);
            case "json" -> controller.getClimate().writeToJSON(filePath);
            case "txt" -> controller.getClimate().writeToTXT(filePath);
            default -> Alerts.createWrongFileExtensionAlert(controller.getAlertResourceBundle()).show();
        }
    }
    /** method which will set controller variables
     * @param changesInFileSaved boolean value
     * @param fileOpen boolean value
     * @param filePath String as path to file
     * @param selectedWeather Weather which sets as selected weather
     * @param weathers ArrayList of weathers which will be set in weathersTable
     * @param days ArrayList of days which will be set in daysTable */
    private void setVariables(
            boolean changesInFileSaved,
            boolean fileOpen,
            String filePath,
            Weather selectedWeather,
            ArrayList<Weather> weathers,
            ArrayList<Day> days
    ) {
        controller.setChangesInFileSaved(changesInFileSaved);
        controller.setFileOpen(fileOpen);
        controller.setFilePath(filePath);
        controller.setSelectedWeather(selectedWeather);
        controller.fillWeathersTable(weathers);
        controller.fillDaysTable(days);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // PUBLIC METHODS
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void pressedOpenFileButton() {
        controller.getOpenFileButton().setOnAction(actionEvent -> {
            logger.info("pressed 'openFileButton'");
            if (controller.getFileOpen()) {
                if (!controller.getChangesInFileSaved()) {
                    Alert openAnotherFileWithUnsavedFileAlert = Alerts.createOpenAnotherFileWithUnsavedFile(controller.getAlertResourceBundle());
                    Optional<ButtonType> choice = openAnotherFileWithUnsavedFileAlert.showAndWait();
                    if (choice.get() == openAnotherFileWithUnsavedFileAlert.getButtonTypes().get(0)) {
                        logger.info("pressed 'saveAndOpenAlertButton'");
                        if (controller.getFilePath() == null)
                            saveFile(getFileFromSaveDialog(controller.getOpenFileButton()).getPath());
                        else
                            saveFile(controller.getFilePath());
                        File file = getFileFromOpenDialog(controller.getOpenFileButton());
                        if (file == null) {
                            Alerts.createFileHasNotChosenAlert(controller.getAlertResourceBundle()).show();
                        } else {
                            openFile(file.getPath());
                            setVariables(
                                    true,
                                    true,
                                    file.getPath(),
                                    new Weather(),
                                    controller.getClimate().getWeathers(),
                                    new ArrayList<>()
                            );
                        }
                    } else if (choice.get() == openAnotherFileWithUnsavedFileAlert.getButtonTypes().get(1)) {
                        logger.info("pressed 'doNotSaveAndOpenAlertButton'");
                        File file = getFileFromSaveDialog(controller.getOpenFileButton());
                        if (file == null) {
                            Alerts.createFileHasNotChosenAlert(controller.getAlertResourceBundle()).show();
                        } else {
                            openFile(file.getPath());
                            setVariables(
                                    true,
                                    true,
                                    file.getPath(),
                                    new Weather(),
                                    controller.getClimate().getWeathers(),
                                    new ArrayList<>()
                            );
                        }
                    } else if (choice.get() == openAnotherFileWithUnsavedFileAlert.getButtonTypes().get(2)) {
                        logger.info("pressed 'saveAndDoNotOpenAlertButton'");
                        if (controller.getFilePath() == null) {
                            File file = getFileFromSaveDialog(controller.getOpenFileButton());
                            if (file == null) {
                                Alerts.createFileHasNotChosenAlert(controller.getAlertResourceBundle()).show();
                            } else {
                                saveFile(file.getPath());
                                controller.setChangesInFileSaved(true);
                                controller.setFileOpen(true);
                                controller.setFilePath(file.getPath());
                            }
                        } else {
                            saveFile(controller.getFilePath());
                            controller.setChangesInFileSaved(true);
                            controller.setFileOpen(true);
                        }
                    } else {
                        logger.info("doNotSaveAndDoNotOpenAlertButton");
                    }
                } else {
                    Alert openAnotherFileAlert = Alerts.createOpenAnotherFile(controller.getAlertResourceBundle());
                    Optional<ButtonType> choice = openAnotherFileAlert.showAndWait();
                    if (choice.get() == openAnotherFileAlert.getButtonTypes().get(0)) {
                        logger.info("pressed 'openAlertButton'");
                        File file = getFileFromOpenDialog(controller.getOpenFileButton());
                        if (file == null) {
                            Alerts.createFileHasNotChosenAlert(controller.getAlertResourceBundle()).show();
                        } else {
                            openFile(file.getPath());
                            setVariables(
                                    true,
                                    true,
                                    file.getPath(),
                                    new Weather(),
                                    controller.getClimate().getWeathers(),
                                    new ArrayList<>()
                            );
                        }
                    } else {
                        logger.info("pressed 'doNotOpenAlertButton'");
                    }
                }
            } else {
                File file = getFileFromOpenDialog(controller.getOpenFileButton());
                if (file == null) {
                    Alerts.createFileHasNotChosenAlert(controller.getAlertResourceBundle()).show();
                } else {
                    openFile(file.getPath());
                    setVariables(
                            true,
                            true,
                            file.getPath(),
                            new Weather(),
                            controller.getClimate().getWeathers(),
                            new ArrayList<>()
                    );
                }
            }
        });
    }
    public void pressedNewFileButton() {
        controller.getNewFileButton().setOnAction(actionEvent -> {
            logger.info("pressed 'newFileButton'");
            if (controller.getFileOpen()) {
                if (!controller.getChangesInFileSaved()) {
                    Alert createNewFileWithUnsavedFileAlert = Alerts.createCreateNewFileWithUnsavedFile(controller.getAlertResourceBundle());
                    Optional<ButtonType> choice = createNewFileWithUnsavedFileAlert.showAndWait();
                    if (choice.get() == createNewFileWithUnsavedFileAlert.getButtonTypes().get(0)) {
                        logger.info("pressed 'saveAndCreateNewAlertButton'");
                        if (controller.getFilePath() == null)
                            saveFile(getFileFromSaveDialog(controller.getOpenFileButton()).getPath());
                        else
                            saveFile(controller.getFilePath());
                        setVariables(
                                true,
                                true,
                                null,
                                new Weather(),
                                new ArrayList<>(),
                                new ArrayList<>()
                        );
                        controller.setClimate(new Climate());
                    } else if (choice.get() == createNewFileWithUnsavedFileAlert.getButtonTypes().get(1)) {
                        logger.info("pressed 'doNotSaveAndCreateNewAlertButton'");
                        setVariables(
                                true,
                                true,
                                null,
                                new Weather(),
                                new ArrayList<>(),
                                new ArrayList<>()
                        );
                        controller.setClimate(new Climate());
                    } else if (choice.get() == createNewFileWithUnsavedFileAlert.getButtonTypes().get(2)) {
                        logger.info("pressed 'saveAndDoNotCreateNewAlertButton'");
                        if (controller.getFilePath() == null)
                            saveFile(getFileFromSaveDialog(controller.getOpenFileButton()).getPath());
                        else
                            saveFile(controller.getFilePath());
                        setVariables(
                                true,
                                true,
                                null,
                                new Weather(),
                                new ArrayList<>(),
                                new ArrayList<>()
                        );
                        controller.setClimate(new Climate());
                    } else {
                        logger.info("pressed 'doNotSaveAndDoNotCreateAlertButton'");
                    }
                } else {
                    Alert createNewFileAlert = Alerts.createCreateNewFile(controller.getAlertResourceBundle());
                    Optional<ButtonType> choice = createNewFileAlert.showAndWait();
                    if (choice.get() == createNewFileAlert.getButtonTypes().get(0)) {
                        logger.info("pressed 'createNewAlertButton'");
                        setVariables(
                                true,
                                true,
                                null,
                                new Weather(),
                                new ArrayList<>(),
                                new ArrayList<>()
                        );
                        controller.setClimate(new Climate());
                    } else {
                        logger.info("pressed 'doNotCreateNewAlertButton'");
                    }
                }
            } else {
                controller.setFileOpen(true);
            }
        });
    }
    public void pressedSaveFileButton() {
        controller.getSaveFileButton().setOnAction(actionEvent -> {
            logger.info("pressed 'saveFileButton'");
        });
    }
    public void pressedSaveAsFileButton() {
        controller.getSaveAsFileButton().setOnAction(actionEvent -> {
            logger.info("pressed 'saveAsFileButton'");
        });
    }
    public void pressedCloseFileButton() {
        controller.getCloseFileButton().setOnAction(actionEvent -> {
            logger.info("pressed 'closeFileButton'");
        });
    }
}
