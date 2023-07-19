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
    static Alert createFileAlreadyOpenedAlert(ResourceBundle resourceBundle) {
        Alert fileAlreadyOpenedAlert = new Alert(Alert.AlertType.CONFIRMATION);
        fileAlreadyOpenedAlert.setTitle(resourceBundle.getString("fileAlreadyOpenedAlertTitle"));
        fileAlreadyOpenedAlert.setHeaderText(resourceBundle.getString("fileAlreadyOpenedAlertHeader"));
        fileAlreadyOpenedAlert.setContentText(resourceBundle.getString("fileAlreadyOpenAlertContent"));
        ButtonType saveAndOpenAlertButton = new ButtonType(resourceBundle.getString("saveAndOpenAlertButton"));
        ButtonType doNotSaveAndOpenAlertButton = new ButtonType(resourceBundle.getString("doNotSaveAndOpenAlertButton"));
        ButtonType saveAndDoNotOpenAlertButton = new ButtonType(resourceBundle.getString("saveAndDoNotOpenAlertButton"));
        ButtonType doNotSaveAndDoNotOpenAlertButton = new ButtonType(resourceBundle.getString("doNotSaveAndDoNotOpenAlertButton"));
        fileAlreadyOpenedAlert.getButtonTypes().setAll(
                saveAndOpenAlertButton,
                doNotSaveAndOpenAlertButton,
                saveAndDoNotOpenAlertButton,
                doNotSaveAndDoNotOpenAlertButton
        );
        return fileAlreadyOpenedAlert;
    }
    static Alert createFileIsNotSavedAlert(ResourceBundle resourceBundle) {
        Alert fileIsNotSavedAlert = new Alert(Alert.AlertType.CONFIRMATION);
        fileIsNotSavedAlert.setTitle(resourceBundle.getString("fileIsNotSavedAlertTitle"));
        fileIsNotSavedAlert.setHeaderText(resourceBundle.getString("fileIsNotSavedAlertHeader"));
        fileIsNotSavedAlert.setContentText(resourceBundle.getString("fileIsNotSavedAlertContent"));
        ButtonType saveAndCloseAlertButton = new ButtonType(resourceBundle.getString("saveAndCloseAlertButton"));
        ButtonType doNotSaveAndCloseAlertButton = new ButtonType(resourceBundle.getString("doNotSaveAndCloseAlertButton"));
        ButtonType saveAndDoNotCloseAlertButton = new ButtonType(resourceBundle.getString("saveAndDoNotCloseAlertButton"));
        ButtonType doNotSaveAndDoNotCloseAlertButton = new ButtonType(resourceBundle.getString("doNotSaveAndDoNotCloseAlertButton"));
        fileIsNotSavedAlert.getButtonTypes().setAll(
                saveAndCloseAlertButton,
                doNotSaveAndCloseAlertButton,
                saveAndDoNotCloseAlertButton,
                doNotSaveAndDoNotCloseAlertButton
        );
        return fileIsNotSavedAlert;
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
}
