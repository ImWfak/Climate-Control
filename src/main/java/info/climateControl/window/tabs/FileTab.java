package info.climateControl.window.tabs;

import info.climateControl.window.controller.FileChangesSaved;
import info.climateControl.window.controller.Controller;
import info.climateControl.window.alerts.Alerts;
import info.climateControl.climate.Climate;
import info.climateControl.weather.Weather;
import info.climateControl.day.Day;
import javafx.scene.control.ButtonType;
import javafx.stage.FileChooser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.google.common.io.Files;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
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
                new FileChooser.ExtensionFilter("All Files", "*.*"),
                new FileChooser.ExtensionFilter("Data Bases", "*.db"),
                new FileChooser.ExtensionFilter("XML Files", "*.xml"),
                new FileChooser.ExtensionFilter("JSON Files", "*.json"),
                new FileChooser.ExtensionFilter("TXT Files", "*.txt")
        );
        return fileChooser.showOpenDialog(controller.getOpenFileButton().getParent().getScene().getWindow());
    }
    private void openFile(String filePath) throws IOException {
        switch (Files.getFileExtension(filePath)) {
            case "db" -> controller.getClimate().readFromDB(filePath);
            case "xml" -> controller.getClimate().readFromXML(filePath);
            case "json" -> controller.getClimate().readFromJSON(filePath);
            case "txt" -> controller.getClimate().readFromTXT(filePath);
            default -> Alerts.createWrongFileExtensionAlert(controller.getAlertResourceBundle()).show();
        }
    }
    private void saveFile(String filePath) throws IOException {
        switch (Files.getFileExtension(filePath)) {
            case "db" -> controller.getClimate().writeToDB(filePath);
            case "xml" -> controller.getClimate().writeToXML(filePath);
            case "json" -> controller.getClimate().writeToJSON(filePath);
            case "txt" -> controller.getClimate().writeToTXT(filePath);
            default -> Alerts.createWrongFileExtensionAlert(controller.getAlertResourceBundle()).show();
        }
    }
    private void setVariables(
            FileChangesSaved fileChangesSaved,
            boolean fileOpen,
            String filePath,
            Weather weather,
            ArrayList<Weather> weathers,
            ArrayList<Day> days
    ) {
        controller.setFileChangesSaved(fileChangesSaved);
        controller.setFileOpen(fileOpen);
        controller.setFilePath(filePath);
        controller.setSelectedWeather(weather);
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
                Optional<ButtonType> choice = Alerts.createFileAlreadyOpenedAlert(controller.getAlertResourceBundle()).showAndWait();
                try {
                    switch (choice.get().toString()) {
                        case "saveAndOpenAlertButton" -> {
                            if (controller.getFilePath() == null)
                                controller.setFilePath(getFile().getPath());
                            saveFile(controller.getFilePath());
                            openFile(getFile().getPath());
                        }
                        case "doNotSaveAndOpenAlertButton" -> {
                            openFile(getFile().getPath());
                        }
                        case "saveAndDoNotOpenAlertButton" -> {
                            if (controller.getFilePath() == null)
                                controller.setFilePath(getFile().getPath());
                            saveFile(controller.getFilePath());
                        }
                    }
                } catch (IOException ioException) {
                    logger.error(ioException.getMessage());
                    Alerts.createWrongFileContentAlert(controller.getAlertResourceBundle()).show();
                }
                setVariables(
                        FileChangesSaved.TRUE,
                        true,
                        controller.getFilePath(),
                        new Weather(),
                        controller.getClimate().getWeathers(),
                        new ArrayList<>()
                );
            } else {
                File file = getFile();
                if (file != null) {
                    try {
                        openFile(file.getPath());
                    } catch (IOException ioException) {
                        logger.error(ioException.getMessage());
                        Alerts.createWrongFileContentAlert(controller.getAlertResourceBundle()).show();
                    }
                    setVariables(
                            FileChangesSaved.TRUE,
                            true,
                            controller.getFilePath(),
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
            setVariables(
                    FileChangesSaved.TRUE,
                    true,
                    null,
                    new Weather(),
                    new ArrayList<>(),
                    new ArrayList<>()
            );
        });
    }
    public void pressedSaveFileButton() {
        controller.getSaveFileButton().setOnAction(actionEvent -> {
            logger.info("pressed 'saveFileButton'");
            if (controller.getFilePath() == null)
                controller.setFilePath(getFile().getPath());
            try {
                saveFile(controller.getFilePath());
            } catch (IOException ioException) {
                logger.error(ioException.getMessage());
                Alerts.createWrongFileContentAlert(controller.getAlertResourceBundle()).show();
            }
            controller.setFileChangesSaved(FileChangesSaved.TRUE);
        });
    }
    public void pressedSaveAsFileButton() {
        controller.getSaveAsFileButton().setOnAction(actionEvent -> {
            logger.info("pressed 'saveAsFileButton'");
            controller.setFilePath(getFile().getPath());
            try {
                saveFile(controller.getFilePath());
            } catch (IOException ioException) {
                logger.error(ioException.getMessage());
                Alerts.createWrongFileContentAlert(controller.getAlertResourceBundle()).show();
            }
            controller.setFileChangesSaved(FileChangesSaved.TRUE);
        });
    }
    public void pressedClosedFileButton() {
        controller.getCloseFileButton().setOnAction(actionEvent -> {
            logger.info("pressed 'closeFileButton'");
            if (controller.getFileChangesSaved() == FileChangesSaved.FALSE) {
                Optional<ButtonType> choice = Alerts.createFileIsNotSavedAlert(controller.getAlertResourceBundle()).showAndWait();
                try {
                    switch (choice.get().toString()) {
                        case "saveAndCloseAlertButton" -> {
                            if (controller.getFilePath() == null)
                                controller.setFilePath(getFile().getPath());
                            saveFile(controller.getFilePath());
                            setVariables(
                                    FileChangesSaved.IS_NOT_OPEN,
                                    false,
                                    null,
                                    new Weather(),
                                    new ArrayList<>(),
                                    new ArrayList<>()
                            );
                        } case "doNotSaveAndCloseAlertButton" -> {
                            setVariables(
                                    FileChangesSaved.IS_NOT_OPEN,
                                    false,
                                    null,
                                    new Weather(),
                                    new ArrayList<>(),
                                    new ArrayList<>()
                            );
                            controller.setClimate(new Climate());
                        } case "saveAndDoNotCloseAlertButton" -> {
                            if (controller.getFilePath() == null)
                                controller.setFilePath(getFile().getPath());
                            saveFile(controller.getFilePath());
                        }
                    }
                } catch (IOException ioException) {
                    logger.error(ioException.getMessage());
                    Alerts.createWrongFileContentAlert(controller.getAlertResourceBundle()).show();
                }
            } else {
                setVariables(
                        FileChangesSaved.IS_NOT_OPEN,
                        false,
                        null,
                        new Weather(),
                        new ArrayList<>(),
                        new ArrayList<>()
                );
                controller.setClimate(new Climate());
            }
        });
    }
}
