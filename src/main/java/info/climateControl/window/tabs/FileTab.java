package info.climateControl.window.tabs;

import info.climateControl.weather.Weather;
import info.climateControl.window.controller.FileChangesSaved;
import info.climateControl.window.controller.Controller;
import info.climateControl.window.alerts.Alerts;
import javafx.scene.control.ButtonType;
import javafx.stage.FileChooser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.google.common.io.Files;
import java.io.IOException;
import java.io.File;
import java.util.Optional;

public class FileTab implements Alerts {
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
    private File getFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Data Bases", "*.db"),
                new FileChooser.ExtensionFilter("XML Files", "*.xml"),
                new FileChooser.ExtensionFilter("JSON Files", "*.json"),
                new FileChooser.ExtensionFilter("TXT Files", "*.txt"),
                new FileChooser.ExtensionFilter("All Files", "*.*")
        );
        return fileChooser.showOpenDialog(controller.getAnchorPane().getParent().getScene().getWindow());
    }
    private void setValues(FileChangesSaved fileChangesSaved, boolean fileOpen, String filePath) {
        controller.setFileChangesSaved(fileChangesSaved);
        controller.setFileOpen(fileOpen);
        controller.setFilePath(filePath);
        controller.setSelectedWeather(new Weather());
        controller.fillWeathersTable(controller.getClimate().getWeathers());
        controller.fillDaysTable(null);
    }
    private void openFile(String filePath) {
        switch (Files.getFileExtension(filePath)) {
            case "db" -> {
                try {
                    controller.getClimate().readFromDB(filePath);
                } catch (IOException ioException) {
                    logger.info(ioException.getMessage());
                    Alerts.createWrongFileContentAlert(controller.getAlertResourceBundle()).show();
                }
            } case "xml" -> {
                try {
                    controller.getClimate().readFromXML(filePath);
                } catch (IOException ioException) {
                    logger.info(ioException.getMessage());
                    Alerts.createWrongFileContentAlert(controller.getAlertResourceBundle()).show();
                }
            } case "json" -> {
                try {
                    controller.getClimate().readFromJSON(filePath);
                } catch (IOException ioException) {
                    logger.info(ioException.getMessage());
                    Alerts.createWrongFileContentAlert(controller.getAlertResourceBundle()).show();
                }
            } case "txt" -> {
                try {
                    controller.getClimate().readFromTXT(filePath);
                } catch (IOException ioException) {
                    logger.info(ioException.getMessage());
                    Alerts.createWrongFileContentAlert(controller.getAlertResourceBundle()).show();
                }
            } default -> Alerts.createWrongFileExtensionAlert(controller.getAlertResourceBundle()).show();
        }
    }
    private void saveFile(String filePath) {
        switch (Files.getFileExtension(filePath)) {
            case "db" -> {
                try {
                    controller.getClimate().writeToDB(filePath);
                } catch (IOException ioException) {
                    logger.info(ioException.getMessage());
                    Alerts.createCanNotSaveFileAlert(controller.getAlertResourceBundle()).show();
                }
            } case "xml" -> {
                try {
                    controller.getClimate().writeToXML(filePath);
                } catch (IOException ioException) {
                    logger.info(ioException.getMessage());
                    Alerts.createCanNotSaveFileAlert(controller.getAlertResourceBundle()).show();
                }
            } case "json" -> {
                try {
                    controller.getClimate().writeToJSON(filePath);
                } catch (IOException ioException) {
                    logger.info(ioException.getMessage());
                    Alerts.createCanNotSaveFileAlert(controller.getAlertResourceBundle()).show();
                }
            } case "txt" -> {
                try {
                    controller.getClimate().writeToTXT(filePath);
                } catch (IOException ioException) {
                    logger.info(ioException.getMessage());
                    Alerts.createCanNotSaveFileAlert(controller.getAlertResourceBundle()).show();
                }
            } default -> Alerts.createWrongFileExtensionAlert(controller.getAlertResourceBundle()).show();
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // PUBLIC METHODS
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void pressedOpenFileButton() {
        controller.getOpenFileButton().setOnAction(actionEvent -> {
            logger.info("pressed 'openFileButton'");
            if (controller.getFileOpen()) {
                Optional<ButtonType> choice = Alerts.createFileAlreadyOpenedAlert(controller.getAlertResourceBundle()).showAndWait();
                switch (choice.get().toString()) {
                    case "saveAndOpenAlertButton" -> {
                        if (controller.getFileOpen()) {
                            saveFile(controller.getFilePath());
                        } else {
                            controller.setFilePath(getFile().getPath());
                            saveFile(controller.getFilePath());
                        }
                        openFile(controller.getFilePath());
                        setValues(FileChangesSaved.TRUE, true, controller.getFilePath());
                    } case "doNotSaveAndOpenAlertButton" -> {
                        openFile(controller.getFilePath());
                        setValues(FileChangesSaved.TRUE, true, controller.getFilePath());
                    } case "saveAndDoNotOpenAlertButton" -> {
                        if (controller.getFileOpen()) {
                            saveFile(controller.getFilePath());
                        } else {
                            controller.setFilePath(getFile().getPath());
                            saveFile(controller.getFilePath());
                        }
                        setValues(FileChangesSaved.TRUE, true, controller.getFilePath());
                    }
                }
            }
        });
    }
    public void pressedNewFileButton() {
        controller.getNewFileButton().setOnAction(actionEvent -> {
            logger.info("pressed 'newFileButton'");
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
    public void pressedClosedFileButton() {
        controller.getCloseFileButton().setOnAction(actionEvent -> {
            logger.info("pressed 'closeFileButton'");
        });
    }
}
