package info.climateControl.window.tabs;

import info.climateControl.window.controller.Controller;
import info.climateControl.window.alerts.Alerts;
import info.climateControl.weather.Weather;
import info.climateControl.climate.Climate;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.google.common.io.Files;
import java.util.Optional;
import java.io.File;

/**<p><b>methods in FileTab</b></p>
 * <p>private:</p>
 * <li>{@link #getFileChooser()}</li>
 * <li>{@link #openFile(String)}</li>
 * <li>{@link #saveFile(String)}</li>
 * <li>{@link #openFileAndSetVariables(Button)}</li>
 * <li>{@link #createNewFileAndSetVariables()}</li>
 * <li>{@link #saveFileAndSetVariables(Button)}</li>
 * <li>{@link #closeFileAndSetVariables()}</li>
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
     * @param controller fxml class which will be set as controller */
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
    /** method which write current climate object to .db, .xml, .json and .txt files 277353
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
    /** method which will open file and set variables
     * @param button Button which will be set as parent for FileChooser */
    private void openFileAndSetVariables(Button button) {
        File file = getFileChooser().showOpenDialog(button.getParent().getScene().getWindow());
        if (file == null) {
            Alerts.createFileHasNotChosenAlert(controller.getAlertResourceBundle()).show();
        } else {
            openFile(file.getPath());
            controller.setSelectedWeather(new Weather());
            controller.setChangesInFileSaved(true);
            controller.setFileOpen(true);
            controller.setFilePath(file.getPath());
            controller.fillWeathersTable(controller.getClimate().getWeathers());
            controller.fillDaysTable(controller.getSelectedWeather().getDays());
        }
    }
    /** method which will create new file and set variables */
    private void createNewFileAndSetVariables() {
        controller.setClimate(new Climate());
        controller.setSelectedWeather(new Weather());
        controller.setChangesInFileSaved(false);
        controller.setFileOpen(true);
        controller.setFilePath(null);
        controller.fillWeathersTable(controller.getClimate().getWeathers());
        controller.fillDaysTable(controller.getSelectedWeather().getDays());
    }
    /** method which will save file and set variables
     * @param button Button which will be set as parent for FileChooser
     * @return true if file has been saved, or false if it has not */
    private boolean saveFileAndSetVariables(Button button) {
        if (controller.getFilePath().isEmpty()) {
            File file = getFileChooser().showOpenDialog(button.getParent().getScene().getWindow());
            if (file == null) {
                Alerts.createFileHasNotChosenAlert(controller.getAlertResourceBundle()).show();
                return false;
            } else {
                saveFile(file.getPath());
                controller.setFilePath(file.getPath());
            }
        } else {
            saveFile(controller.getFilePath());
        }
        controller.setChangesInFileSaved(true);
        return true;
    }
    /** method which will close file and set variables */
    private void closeFileAndSetVariables() {
        controller.setClimate(new Climate());
        controller.setSelectedWeather(new Weather());
        controller.setChangesInFileSaved(true);
        controller.setFileOpen(false);
        controller.setFilePath(null);
        controller.fillWeathersTable(controller.getClimate().getWeathers());
        controller.fillDaysTable(controller.getSelectedWeather().getDays());
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // PUBLIC METHODS
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /** method which will set on action openFileButton */
    public void pressedOpenFileButton() {
        controller.getOpenFileButton().setOnAction(actionEvent -> {
            logger.info("pressed 'openFileButton'");
            if (controller.getFileOpen()) {
                if (controller.getChangesInFileSaved()) {
                    ButtonType openFileButton = new ButtonType(controller.getAlertResourceBundle().getString("openAlertButton"));
                    ButtonType doNotOpenFileButton = new ButtonType(controller.getAlertResourceBundle().getString("doNotOpenAlertButton"));
                    Optional<ButtonType> choice = Alerts.createAlertWithButtons(
                            Alert.AlertType.CONFIRMATION,
                            controller.getAlertResourceBundle().getString("openAnotherFileAlertTitle"),
                            controller.getAlertResourceBundle().getString("openAnotherFileAlertHeader"),
                            controller.getAlertResourceBundle().getString("openAnotherFileAlertContent"),
                            openFileButton, doNotOpenFileButton
                    ).showAndWait();
                    if (choice.get().equals(openFileButton)) {
                        logger.info("pressed 'openFileButton'");
                        openFileAndSetVariables(controller.getOpenFileButton());
                    } else {
                        logger.info("pressed 'doNotOpenFileButton'");
                    }
                } else {
                    ButtonType saveAndOpenFileButton = new ButtonType(controller.getAlertResourceBundle().getString("saveAndOpenAlertButton"));
                    ButtonType doNotSaveAndOpenFileButton = new ButtonType(controller.getAlertResourceBundle().getString("doNotSaveAndOpenAlertButton"));
                    ButtonType saveAndDoNotOpenFileButton = new ButtonType(controller.getAlertResourceBundle().getString("saveAndDoNotOpenAlertButton"));
                    ButtonType doNotSaveAndDoNotOpenFileButton = new ButtonType(controller.getAlertResourceBundle().getString("doNotSaveAndDoNotOpenAlertButton"));
                    Optional<ButtonType> choice = Alerts.createAlertWithButtons(
                            Alert.AlertType.CONFIRMATION,
                            controller.getAlertResourceBundle().getString("openAnotherFileWithUnsavedFileAlertTitle"),
                            controller.getAlertResourceBundle().getString("openAnotherFileWithUnsavedFileAlertHeader"),
                            controller.getAlertResourceBundle().getString("openAnotherFileWithUnsavedFileAlertContent"),
                            saveAndOpenFileButton, doNotSaveAndOpenFileButton,
                            saveAndDoNotOpenFileButton, doNotSaveAndDoNotOpenFileButton
                    ).showAndWait();
                    if (choice.get().equals(saveAndOpenFileButton)) {
                        logger.info("pressed 'saveAndOpenFileButton'");
                        boolean saved = saveFileAndSetVariables(controller.getOpenFileButton());
                        if (saved)
                            openFileAndSetVariables(controller.getOpenFileButton());
                    } else if (choice.get().equals(doNotSaveAndOpenFileButton)) {
                        logger.info("pressed 'doNotSaveAndOpenFileButton'");
                        openFileAndSetVariables(controller.getOpenFileButton());
                    } else if (choice.get().equals(saveAndDoNotOpenFileButton)) {
                        logger.info("pressed 'saveAndDoNotOpenFileButton'");
                        saveFileAndSetVariables(controller.getOpenFileButton());
                    } else {
                        logger.info("pressed 'doNotSaveAndDoNotOpenFileButton'");
                    }
                }
            } else {
                openFileAndSetVariables(controller.getOpenFileButton());
            }
        });
    }
    /** method which will set on action newFileButton */
    public void pressedNewFileButton() {
        controller.getNewFileButton().setOnAction(actionEvent -> {
            logger.info("pressed 'newFileButton'");
            if (controller.getFileOpen()) {
                if (controller.getChangesInFileSaved()) {
                    ButtonType crateNewFileButton = new ButtonType(controller.getAlertResourceBundle().getString("createNewAlertButton"));
                    ButtonType doNotCreateNewFileButton = new ButtonType(controller.getAlertResourceBundle().getString("doNotCreateNewAlertButton"));
                    Optional<ButtonType> choice = Alerts.createAlertWithButtons(
                            Alert.AlertType.CONFIRMATION,
                            controller.getAlertResourceBundle().getString("createNewFileAlertTitle"),
                            controller.getAlertResourceBundle().getString("createNewFileAlertHeader"),
                            controller.getAlertResourceBundle().getString("createNewFileAlertContent"),
                            crateNewFileButton, doNotCreateNewFileButton
                    ).showAndWait();
                    if (choice.get().equals(crateNewFileButton)) {
                        logger.info("pressed 'createNewFileButton'");
                        createNewFileAndSetVariables();
                    } else {
                        logger.info("pressed 'doNotCreateNewFileButton'");
                    }
                } else {
                    ButtonType saveAndCreateNewFileButton = new ButtonType(controller.getAlertResourceBundle().getString("saveAndCreateNewAlertButton"));
                    ButtonType doNotSaveAndCreateNewFileButton = new ButtonType(controller.getAlertResourceBundle().getString("doNotSaveAndCreateNewAlertButton"));
                    ButtonType saveAndDoNotCreateNewFileButton = new ButtonType(controller.getAlertResourceBundle().getString("saveAndDoNotCreateNewAlertButton"));
                    ButtonType doNotSaveAndDoNotCreateNewFileButton = new ButtonType(controller.getAlertResourceBundle().getString("doNotSaveAndDoNotCreateAlertButton"));
                    Optional<ButtonType> choice = Alerts.createAlertWithButtons(
                            Alert.AlertType.CONFIRMATION,
                            controller.getAlertResourceBundle().getString("createNewFileWithUnsavedFileAlertTitle"),
                            controller.getAlertResourceBundle().getString("createNewFileWithUnsavedFileAlertHeader"),
                            controller.getAlertResourceBundle().getString("createNewFileWithUnsavedFileAlertContent"),
                            saveAndCreateNewFileButton, doNotSaveAndCreateNewFileButton,
                            saveAndDoNotCreateNewFileButton, doNotSaveAndDoNotCreateNewFileButton
                    ).showAndWait();
                    if (choice.get().equals(saveAndCreateNewFileButton)) {
                        logger.info("pressed 'saveAndCreateNewFileButton'");
                        boolean saved = saveFileAndSetVariables(controller.getNewFileButton());
                        if (saved)
                            createNewFileAndSetVariables();
                    } else if (choice.get().equals(doNotSaveAndCreateNewFileButton)) {
                        logger.info("pressed 'doNotSaveAndCreateNewFileButton'");
                        createNewFileAndSetVariables();
                    } else if (choice.get().equals(saveAndDoNotCreateNewFileButton)) {
                        logger.info("pressed 'saveAndDoNotCreateNewFileButton'");
                        saveFileAndSetVariables(controller.getNewFileButton());
                    } else {
                        logger.info("pressed 'doNotSaveAndDoNotCreateNewFileButton'");
                    }
                }
            } else {
                createNewFileAndSetVariables();
            }
        });
    }
    /** method which will set on action saveFileButton */
    public void pressedSaveFileButton() {
        controller.getSaveFileButton().setOnAction(actionEvent -> {
            logger.info("pressed 'saveFileButton'");
            saveFileAndSetVariables(controller.getSaveFileButton());
        });
    }
    /** method which will set on action saveAsFileButton */
    public void pressedSaveAsFileButton() {
        controller.getSaveAsFileButton().setOnAction(actionEvent -> {
            logger.info("pressed 'saveAsFileButton'");
            File file = getFileChooser().showOpenDialog(controller.getSaveAsFileButton().getParent().getScene().getWindow());
            if (file == null) {
                Alerts.createFileHasNotChosenAlert(controller.getAlertResourceBundle()).show();
                return;
            } else {
                saveFile(file.getPath());
                controller.setFilePath(file.getPath());
            }
            controller.setChangesInFileSaved(true);
        });
    }
    /** method which will set on action closeFileButton */
    public void pressedCloseFileButton() {
        controller.getCloseFileButton().setOnAction(actionEvent -> {
            logger.info("pressed 'closeFileButton'");
            if (controller.getFileOpen()) {
                if (controller.getChangesInFileSaved()) {
                    ButtonType closeFileButton = new ButtonType(controller.getAlertResourceBundle().getString("closeFileAlertButton"));
                    ButtonType doNotCloseFileButton = new ButtonType(controller.getAlertResourceBundle().getString("doNotCloseFileAlertButton"));
                    Optional<ButtonType> choice = Alerts.createAlertWithButtons(
                            Alert.AlertType.CONFIRMATION,
                            controller.getAlertResourceBundle().getString("closeFileAlertTitle"),
                            controller.getAlertResourceBundle().getString("closeFileAlertHeader"),
                            controller.getAlertResourceBundle().getString("closeFileAlertContent"),
                            closeFileButton, doNotCloseFileButton
                    ).showAndWait();
                    if (choice.get().equals(closeFileButton)) {
                        logger.info("pressed 'closeFileButton'");
                        closeFileAndSetVariables();
                    } else {
                        logger.info("pressed 'doNotCloseFileButton'");
                    }
                } else {
                    ButtonType saveAndCloseFileButton = new ButtonType(controller.getAlertResourceBundle().getString("saveAndCloseAlertButton"));
                    ButtonType doNotSaveAndCloseFileButton = new ButtonType(controller.getAlertResourceBundle().getString("doNotSaveAndCloseAlertButton"));
                    ButtonType saveAndDoNotCloseFileButton = new ButtonType(controller.getAlertResourceBundle().getString("saveAndDoNotCloseAlertButton"));
                    ButtonType doNotSaveAndDoNotCloseFileButton = new ButtonType(controller.getAlertResourceBundle().getString("doNotSaveAndDoNotCloseAlertButton"));
                    Optional<ButtonType> choice = Alerts.createAlertWithButtons(
                            Alert.AlertType.CONFIRMATION,
                            controller.getAlertResourceBundle().getString("closeUnsavedFileAlertTitle"),
                            controller.getAlertResourceBundle().getString("closeUnsavedFileAlertHeader"),
                            controller.getAlertResourceBundle().getString("closeUnsavedFileAlertContent"),
                            saveAndCloseFileButton, doNotSaveAndCloseFileButton,
                            saveAndDoNotCloseFileButton, doNotSaveAndDoNotCloseFileButton
                    ).showAndWait();
                    if (choice.get().equals(saveAndDoNotCloseFileButton)) {
                        logger.info("pressed 'saveAndDoNotCloseFileButton'");
                        boolean saved = saveFileAndSetVariables(controller.getCloseFileButton());
                        if (saved)
                            closeFileAndSetVariables();
                    } else if (choice.get().equals(doNotSaveAndCloseFileButton)) {
                        logger.info("pressed 'doNotSaveAndCloseFileButton'");
                        closeFileAndSetVariables();
                    } else if (choice.get().equals(saveAndDoNotCloseFileButton)) {
                        logger.info("pressed 'saveAndDoNotCloseFileButton'");
                        saveFileAndSetVariables(controller.getCloseFileButton());
                    } else {
                        logger.info("pressed 'doNotSaveAndDoNotCloseFileButton'");
                    }
                }
            } else {
                Alerts.createFileIsNotOpenAlert(controller.getAlertResourceBundle()).show();
            }
        });
    }
}
