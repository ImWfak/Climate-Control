package info.climateControl.window.tabs;

import info.climateControl.window.controller.FileChangesSaved;
import info.climateControl.window.controller.Controller;
import info.climateControl.window.alerts.Alerts;
import info.climateControl.weather.Weather;
import info.climateControl.day.Day;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.FileChooser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.google.common.io.Files;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.Optional;

/**<p><b>methods in FileTab</b></p>
 * <p>private:</p>
 * <li>{@link #getFileChooser()}</li>
 * <li>{@link #openFile(String)}</li>
 * <li>{@link #saveFile(String)}</li>
 * <li>{@link #setVariables(FileChangesSaved, boolean, String, Weather, ArrayList, ArrayList)}</li>
 * <p>public:</p>
 * <li>{@link #pressedOpenFileButton()}</li>
 * <li>{@link #pressedNewFileButton()}</li>
 * <li>{@link #pressedSaveFileButton()}</li>
 * <li>{@link #pressedSaveAsFileButton()}</li>
 * <li>{@link #pressedCloseFileButton()}</li> */
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
        try {
            switch (Files.getFileExtension(filePath)) {
                case "db" -> controller.getClimate().readFromDB(filePath);
                case "xml" -> controller.getClimate().readFromXML(filePath);
                case "json" -> controller.getClimate().readFromJSON(filePath);
                case "txt" -> controller.getClimate().readFromTXT(filePath);
                default -> Alerts.createWrongFileExtensionAlert(controller.getAlertResourceBundle()).show();
            }
        } catch (IOException ioException) {
            logger.error(ioException.getMessage());
            Alerts.createWrongFileContentAlert(controller.getAlertResourceBundle()).show();
        }
    }
    /** method which write current climate object to .db, .xml, .json and .txt files
     * @param filePath String as path to file */
    private void saveFile(String filePath) {
        try {
            switch (Files.getFileExtension(filePath)) {
                case "db" -> controller.getClimate().writeToDB(filePath);
                case "xml" -> controller.getClimate().writeToXML(filePath);
                case "json" -> controller.getClimate().writeToJSON(filePath);
                case "txt" -> controller.getClimate().writeToTXT(filePath);
                default -> Alerts.createWrongFileExtensionAlert(controller.getAlertResourceBundle()).show();
            }
        } catch (IOException ioException) {
            logger.error(ioException.getMessage());
            Alerts.createCanNotSaveFileAlert(controller.getAlertResourceBundle()).show();
        }
    }
    /** method which will set controller variables
     * @param fileChangesSaved enum which has three states
     * @param fileOpen boolean value
     * @param filePath String as path to file
     * @param selectedWeather Weather which sets as selected weather
     * @param weathers ArrayList of weathers which will be set in weathersTable
     * @param days ArrayList of days which will be set in daysTable */
    private void setVariables(
            FileChangesSaved fileChangesSaved,
            boolean fileOpen,
            String filePath,
            Weather selectedWeather,
            ArrayList<Weather> weathers,
            ArrayList<Day> days
    ) {
        controller.setFileChangesSaved(fileChangesSaved);
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
                if (controller.getFileChangesSaved() == FileChangesSaved.FALSE) {
                    Optional<ButtonType> choice = Alerts.createOpenAnotherFileWithUnsavedFile(controller.getAlertResourceBundle()).showAndWait();
                    switch (choice.get().toString()) {
                        case "saveAndOpenAlertButton" -> {
                            if (controller.getFilePath() == null) {
                                saveFile(getFileFromSaveDialog(controller.getOpenFileButton()).getPath());
                            } else {
                                saveFile(controller.getFilePath());
                            }
                            String filePath = getFileFromOpenDialog(controller.getOpenFileButton()).getPath();
                            openFile(filePath);
                            setVariables(
                                    FileChangesSaved.TRUE,
                                    true,
                                    filePath,
                                    new Weather(),
                                    controller.getClimate().getWeathers(),
                                    new ArrayList<>()
                            );
                        }
                        case "doNotSaveAndOpenAlertButton" -> {
                            String filePath = getFileFromOpenDialog(controller.getOpenFileButton()).getPath();
                            openFile(filePath);
                            setVariables(
                                    FileChangesSaved.TRUE,
                                    true,
                                    filePath,
                                    new Weather(),
                                    controller.getClimate().getWeathers(),
                                    new ArrayList<>()
                            );
                        }
                        case "saveAndDoNotOpenAlertButton" -> {
                            String filePath = null;
                            if (controller.getFilePath() == null) {
                                filePath = getFileFromSaveDialog(controller.getOpenFileButton()).getPath();
                                saveFile(filePath);
                            } else {
                                saveFile(controller.getFilePath());
                            }
                            setVariables(
                                    FileChangesSaved.TRUE,
                                    true,
                                    filePath,
                                    controller.getSelectedWeather(),
                                    controller.getClimate().getWeathers(),
                                    controller.getSelectedWeather().getDays()
                            );
                        }
                        case "doNotSaveAndDoNotOpenAlertButton" -> {

                        }
                    }
                } else {
                    Optional<ButtonType> choice = Alerts.createOpenAnotherFile(controller.getAlertResourceBundle()).showAndWait();
                    switch (choice.get().toString()) {
                        case "openAlertButton" -> {
                            String filePath = getFileFromOpenDialog(controller.getOpenFileButton()).getPath();
                            openFile(filePath);
                            setVariables(
                                    FileChangesSaved.TRUE,
                                    true,
                                    filePath,
                                    new Weather(),
                                    controller.getClimate().getWeathers(),
                                    new ArrayList<>()
                            );
                        }
                        case "doNotOpenAlertButton" -> {

                        }
                    }
                }
            } else {
                String filePath = getFileFromOpenDialog(controller.getOpenFileButton()).getPath();
                openFile(filePath);
                setVariables(
                        FileChangesSaved.TRUE,
                        true,
                        filePath,
                        new Weather(),
                        controller.getClimate().getWeathers(),
                        new ArrayList<>()
                );
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
    public void pressedCloseFileButton() {
        controller.getCloseFileButton().setOnAction(actionEvent -> {
            logger.info("pressed 'closeFileButton'");
        });
    }
}
