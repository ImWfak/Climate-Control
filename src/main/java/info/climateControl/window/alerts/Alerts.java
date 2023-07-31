package info.climateControl.window.alerts;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert;
import java.util.ResourceBundle;

public interface Alerts {
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // WEATHER ALERTS
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    static Alert createNoSuchWeathersAlert(ResourceBundle resourceBundle) {
        Alert noSuchWeathersAlert = new Alert(Alert.AlertType.WARNING);
        noSuchWeathersAlert.setTitle(resourceBundle.getString("noSuchWeathersAlertTitle"));
        noSuchWeathersAlert.setHeaderText(resourceBundle.getString("noSuchWeathersAlertHeader"));
        noSuchWeathersAlert.setContentText(resourceBundle.getString("noSuchWeathersAlertContent"));
        return noSuchWeathersAlert;
    }
    static Alert createNoSelectedWeatherAlert(ResourceBundle resourceBundle) {
       Alert noSelectedWeatherAlert = new Alert(Alert.AlertType.ERROR);
       noSelectedWeatherAlert.setTitle(resourceBundle.getString("noSelectedWeatherAlertTitle"));
       noSelectedWeatherAlert.setHeaderText(resourceBundle.getString("noSelectedWeatherAlertHeader"));
       noSelectedWeatherAlert.setContentText(resourceBundle.getString("noSelectedWeatherAlertContent"));
       return noSelectedWeatherAlert;
    }
    static Alert createNoWeathersInFile(ResourceBundle resourceBundle) {
        Alert noWeathersInFile = new Alert(Alert.AlertType.WARNING);
        noWeathersInFile.setTitle(resourceBundle.getString("noWeatherInFileAlertTitle"));
        noWeathersInFile.setHeaderText(resourceBundle.getString("noWeatherInFileAlertHeader"));
        noWeathersInFile.setContentText(resourceBundle.getString("noWeatherInFileAlertContent"));
        return noWeathersInFile;
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
    // DAYS ALERTS
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    static Alert createNoSuchDaysAlert(ResourceBundle resourceBundle) {
        Alert noSuchDaysAlert = new Alert(Alert.AlertType.WARNING);
        noSuchDaysAlert.setTitle(resourceBundle.getString("noSuchDaysAlertTitle"));
        noSuchDaysAlert.setHeaderText(resourceBundle.getString("noSuchDaysAlertHeader"));
        noSuchDaysAlert.setContentText(resourceBundle.getString("noSuchDaysAlertContent"));
        return noSuchDaysAlert;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // OTHER ALERTS
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    static Alert createIndexOutOfBoundsAlert(ResourceBundle resourceBundle) {
        Alert indexOutOfBoundsAlert = new Alert(Alert.AlertType.ERROR);
        indexOutOfBoundsAlert.setTitle(resourceBundle.getString("indexOutOfBoundsAlertTitle"));
        indexOutOfBoundsAlert.setHeaderText(resourceBundle.getString("indexOutOfBoundsAlertHeader"));
        indexOutOfBoundsAlert.setContentText(resourceBundle.getString("indexOutOfBoundsAlertContent"));
        return indexOutOfBoundsAlert;
    }
    static Alert createWrongInputAlert(ResourceBundle resourceBundle) {
        Alert wrongInputAlert = new Alert(Alert.AlertType.ERROR);
        wrongInputAlert.setTitle(resourceBundle.getString("wrongInputAlertTitle"));
        wrongInputAlert.setHeaderText(resourceBundle.getString("wrongInputAlertHeader"));
        wrongInputAlert.setContentText(resourceBundle.getString("wrongInputAlertContent"));
        return wrongInputAlert;
    }
    static Alert createTableIsEmptyAlert(ResourceBundle resourceBundle) {
        Alert tableIsEmptyAlert = new Alert(Alert.AlertType.WARNING);
        tableIsEmptyAlert.setTitle(resourceBundle.getString("tableIsEmptyAlertTitle"));
        tableIsEmptyAlert.setHeaderText(resourceBundle.getString("tableIsEmptyAlertHeader"));
        tableIsEmptyAlert.setContentText(resourceBundle.getString("tableIsEmptyAlertContent"));
        return tableIsEmptyAlert;
    }
    static Alert createNoSelectedRowAlert(ResourceBundle resourceBundle) {
        Alert noSelectedRowAlert = new Alert(Alert.AlertType.ERROR);
        noSelectedRowAlert.setTitle(resourceBundle.getString("noSelectedRowAlertTitle"));
        noSelectedRowAlert.setHeaderText(resourceBundle.getString("noSelectedRowAlertHeader"));
        noSelectedRowAlert.setContentText(resourceBundle.getString("noSelectedRowAlertContent"));
        return noSelectedRowAlert;
    }
    static Alert createWrongFileExtensionAlert(ResourceBundle resourceBundle) {
        Alert wrongFileExtensionAlert = new Alert(Alert.AlertType.ERROR);
        wrongFileExtensionAlert.setTitle(resourceBundle.getString("wrongFileExtensionAlertTitle"));
        wrongFileExtensionAlert.setHeaderText(resourceBundle.getString("wrongFileExtensionAlertHeader"));
        wrongFileExtensionAlert.setContentText(resourceBundle.getString("wrongFileExtensionAlertContent"));
        return wrongFileExtensionAlert;
    }
    static Alert createWrongFileContentAlert(ResourceBundle resourceBundle) {
        Alert wrongFileContentAlert = new Alert(Alert.AlertType.ERROR);
        wrongFileContentAlert.setTitle(resourceBundle.getString("wrongFileContentAlertTitle"));
        wrongFileContentAlert.setHeaderText(resourceBundle.getString("wrongFileContentAlertHeader"));
        wrongFileContentAlert.setContentText(resourceBundle.getString("wrongFileContentAlertContent"));
        return wrongFileContentAlert;
    }
    static Alert createCanNotSaveFileAlert(ResourceBundle resourceBundle) {
        Alert canNotSaveFileAlert = new Alert(Alert.AlertType.ERROR);
        canNotSaveFileAlert.setTitle(resourceBundle.getString("canNotSaveFileAlertTitle"));
        canNotSaveFileAlert.setHeaderText(resourceBundle.getString("canNotSaveFileAlertHeader"));
        canNotSaveFileAlert.setContentText(resourceBundle.getString("canNotSaveFileAlertContent"));
        return canNotSaveFileAlert;
    }
    static Alert createFileHasNotChosenAlert(ResourceBundle resourceBundle) {
        Alert fileHasNotChosenAlert = new Alert(Alert.AlertType.INFORMATION);
        fileHasNotChosenAlert.setTitle(resourceBundle.getString("fileHasNotChosenAlertTitle"));
        fileHasNotChosenAlert.setHeaderText(resourceBundle.getString("fileHasNotChosenAlertHeader"));
        fileHasNotChosenAlert.setContentText(resourceBundle.getString("fileHasNotChosenAlertContent"));
        return fileHasNotChosenAlert;
    }
    static Alert createOpenAnotherFile(ResourceBundle resourceBundle) {
        Alert openAnotherFile = new Alert(Alert.AlertType.CONFIRMATION);
        openAnotherFile.setTitle(resourceBundle.getString("openAnotherFileAlertTitle"));
        openAnotherFile.setHeaderText(resourceBundle.getString("openAnotherFileAlertHeader"));
        openAnotherFile.setContentText(resourceBundle.getString("openAnotherFileAlertContent"));
        ButtonType openAlertButton = new ButtonType(resourceBundle.getString("openAlertButton"));
        ButtonType doNotOpenAlertButton = new ButtonType(resourceBundle.getString("doNotOpenAlertButton"));
        openAnotherFile.getButtonTypes().setAll(
                openAlertButton,
                doNotOpenAlertButton
        );
        return openAnotherFile;
    }
    static Alert createOpenAnotherFileWithUnsavedFile(ResourceBundle resourceBundle) {
        Alert openAnotherFileWithUnsavedFileAlert = new Alert(Alert.AlertType.CONFIRMATION);
        openAnotherFileWithUnsavedFileAlert.setTitle(resourceBundle.getString("openAnotherFileWithUnsavedFileAlertTitle"));
        openAnotherFileWithUnsavedFileAlert.setHeaderText(resourceBundle.getString("openAnotherFileWithUnsavedFileAlertHeader"));
        openAnotherFileWithUnsavedFileAlert.setContentText(resourceBundle.getString("openAnotherFileWithUnsavedFileAlertContent"));
        ButtonType saveAndOpenAlertButton = new ButtonType(resourceBundle.getString("saveAndOpenAlertButton"));
        ButtonType doNotSaveAndOpenAlertButton = new ButtonType(resourceBundle.getString("doNotSaveAndOpenAlertButton"));
        ButtonType saveAndDoNotOpenAlertButton = new ButtonType(resourceBundle.getString("saveAndDoNotOpenAlertButton"));
        ButtonType doNotSaveAndDoNotOpenAlertButton = new ButtonType(resourceBundle.getString("doNotSaveAndDoNotOpenAlertButton"));
        openAnotherFileWithUnsavedFileAlert.getButtonTypes().setAll(
                saveAndOpenAlertButton,
                doNotSaveAndOpenAlertButton,
                saveAndDoNotOpenAlertButton,
                doNotSaveAndDoNotOpenAlertButton
        );
        return openAnotherFileWithUnsavedFileAlert;
    }
    static Alert createCloseFile(ResourceBundle resourceBundle) {
        Alert closeFileAlert = new Alert(Alert.AlertType.CONFIRMATION);
        closeFileAlert.setTitle(resourceBundle.getString("closeFileAlertTitle"));
        closeFileAlert.setHeaderText(resourceBundle.getString("closeFileAlertHeader"));
        closeFileAlert.setContentText(resourceBundle.getString("closeFileAlertContent"));
        ButtonType closeFileAlertButton = new ButtonType(resourceBundle.getString("closeFileAlertButton"));
        ButtonType doNotCloseFileAlertButton = new ButtonType(resourceBundle.getString("doNotCloseFileAlertButton"));
        closeFileAlert.getButtonTypes().setAll(
                closeFileAlertButton,
                doNotCloseFileAlertButton
        );
        return closeFileAlert;
    }
    static Alert createCloseUnsavedFile(ResourceBundle resourceBundle) {
        Alert closeUnsavedFileAlert = new Alert(Alert.AlertType.CONFIRMATION);
        closeUnsavedFileAlert.setTitle(resourceBundle.getString("closeUnsavedFileAlertTitle"));
        closeUnsavedFileAlert.setHeaderText(resourceBundle.getString("closeUnsavedFileAlertHeader"));
        closeUnsavedFileAlert.setContentText(resourceBundle.getString("closeUnsavedFileAlertContent"));
        ButtonType saveAndCloseAlertButton = new ButtonType(resourceBundle.getString("saveAndCloseAlertButton"));
        ButtonType doNotSaveAndCloseAlertButton = new ButtonType(resourceBundle.getString("doNotSaveAndCloseAlertButton"));
        ButtonType saveAndDoNotCloseAlertButton = new ButtonType(resourceBundle.getString("saveAndDoNotCloseAlertButton"));
        ButtonType doNotSaveAndDoNotCloseAlertButton = new ButtonType(resourceBundle.getString("doNotSaveAndDoNotCloseAlertButton"));
        closeUnsavedFileAlert.getButtonTypes().setAll(
                saveAndCloseAlertButton,
                doNotSaveAndCloseAlertButton,
                saveAndDoNotCloseAlertButton,
                doNotSaveAndDoNotCloseAlertButton
        );
        return closeUnsavedFileAlert;
    }
    static Alert createCreateNewFile(ResourceBundle resourceBundle) {
        Alert createNewFileAlert = new Alert(Alert.AlertType.CONFIRMATION);
        createNewFileAlert.setTitle(resourceBundle.getString("createNewFileAlertTitle"));
        createNewFileAlert.setHeaderText(resourceBundle.getString("createNewFileAlertHeader"));
        createNewFileAlert.setContentText(resourceBundle.getString("createNewFileAlertContent"));
        ButtonType createNewAlertButton = new ButtonType(resourceBundle.getString("createNewAlertButton"));
        ButtonType doNotCreateNewAlertButton = new ButtonType(resourceBundle.getString("doNotCreateNewAlertButton"));
        createNewFileAlert.getButtonTypes().setAll(
                createNewAlertButton,
                doNotCreateNewAlertButton
        );
        return createNewFileAlert;
    }
    static Alert createCreateNewFileWithUnsavedFile(ResourceBundle resourceBundle) {
        Alert NewFileWithUnsavedFileAlert = new Alert(Alert.AlertType.CONFIRMATION);
        NewFileWithUnsavedFileAlert.setTitle(resourceBundle.getString("createNewFileWithUnsavedFileAlertTitle"));
        NewFileWithUnsavedFileAlert.setHeaderText(resourceBundle.getString("createNewFileWithUnsavedFileAlertHeader"));
        NewFileWithUnsavedFileAlert.setContentText(resourceBundle.getString("createNewFileWithUnsavedFileAlertContent"));
        ButtonType saveAndCreateNewAlertButton = new ButtonType(resourceBundle.getString("saveAndCreateNewAlertButton"));
        ButtonType doNotSaveAndCreateNewAlertButton = new ButtonType(resourceBundle.getString("doNotSaveAndCreateNewAlertButton"));
        ButtonType saveAndDoNotCreateNewAlertButton = new ButtonType(resourceBundle.getString("saveAndDoNotCreateNewAlertButton"));
        ButtonType doNotSaveAndDoNotCreateAlertButton = new ButtonType(resourceBundle.getString("doNotSaveAndDoNotCreateAlertButton"));
        NewFileWithUnsavedFileAlert.getButtonTypes().setAll(
                saveAndCreateNewAlertButton,
                doNotSaveAndCreateNewAlertButton,
                saveAndDoNotCreateNewAlertButton,
                doNotSaveAndDoNotCreateAlertButton
        );
        return NewFileWithUnsavedFileAlert;
    }
    static Alert createFileIsNotOpened(ResourceBundle resourceBundle) {
        Alert fileIsNotOpenedAlert = new Alert(Alert.AlertType.ERROR);
        fileIsNotOpenedAlert.setTitle(resourceBundle.getString("fileIsNotOpenedAlertTitle"));
        fileIsNotOpenedAlert.setHeaderText(resourceBundle.getString("fileIsNotOpenedAlertHeader"));
        fileIsNotOpenedAlert.setContentText(resourceBundle.getString("fileIsNotOpenedAlertContent"));
        return fileIsNotOpenedAlert;
    }
}
