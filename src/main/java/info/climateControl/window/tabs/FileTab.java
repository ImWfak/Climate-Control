package info.climateControl.window.tabs;

import info.climateControl.window.controller.Controller;
import info.climateControl.window.alerts.Alerts;
import javafx.stage.FileChooser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.google.common.io.Files;

import java.io.File;

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
    private void showFileChooser() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Data Bases", "*.db"),
                new FileChooser.ExtensionFilter("XML Files", "*.xml"),
                new FileChooser.ExtensionFilter("JSON Files", "*.json"),
                new FileChooser.ExtensionFilter("TXT Files", "*.txt"),
                new FileChooser.ExtensionFilter("All Files", "*.*")
        );
        File selectedFile = fileChooser.showOpenDialog(controller.getAnchorPane().getParent().getScene().getWindow());
        if (selectedFile != null) {
            switch (Files.getFileExtension(selectedFile.getName())) {
                case "db" -> controller.getClimate().readFromDB(selectedFile.getPath());
                case "xml" -> controller.getClimate().readFromXML(selectedFile.getPath());
                case "json" -> controller.getClimate().readFromJSON(selectedFile.getPath());
                case "txt" -> controller.getClimate().readFromTXT(selectedFile.getPath());
                default -> Alerts.createWrongFileExtension(controller.getAlertResourceBundle()).show();
            }
        }
    }
    private void openFile() {

    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // PUBLIC METHODS
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void pressedOpenFileButton() {
        controller.getOpenFileButton().setOnAction(actionEvent -> {
            logger.info("pressed 'openFileButton'");
            if (controller.getFileOpen()) {
                Alerts.createFileAlreadyOpenedAlert(controller.getAlertResourceBundle());
            } else {
                openFile();
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
