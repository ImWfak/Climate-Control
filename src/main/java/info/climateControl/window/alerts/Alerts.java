package info.climateControl.window.alerts;

import javafx.scene.control.Alert;
import java.util.ResourceBundle;

public interface Alerts {
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // WEATHER ALERTS
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    static Alert createNoSuchWeathersAlert(ResourceBundle resourceBundle) {
        Alert noSuchWeathersAlert = new Alert(Alert.AlertType.ERROR);
        noSuchWeathersAlert.setTitle(resourceBundle.getString("noSuchWeatherAlertTitle"));
        noSuchWeathersAlert.setHeaderText(resourceBundle.getString("noSuchWeatherAlertHeader"));
        noSuchWeathersAlert.setContentText(resourceBundle.getString("noSuchWeatherAlertContent"));
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
        Alert noWeathersInFile = new Alert(Alert.AlertType.ERROR);
        noWeathersInFile.setTitle(resourceBundle.getString("noWeatherInFileAlertTitle"));
        noWeathersInFile.setHeaderText(resourceBundle.getString("noWeatherInFileAlertHeader"));
        noWeathersInFile.setContentText(resourceBundle.getString("noWeatherInFileAlertContent"));
        return noWeathersInFile;
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
    // DAYS ALERTS
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    static Alert createNoSuchDaysAlert(ResourceBundle resourceBundle) {
        Alert noSuchDaysAlert = new Alert(Alert.AlertType.ERROR);
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
        indexOutOfBoundsAlert.setHeaderText(resourceBundle.getString("indexOutOfBoundsHeader"));
        indexOutOfBoundsAlert.setContentText(resourceBundle.getString("indexOutOfBoundsContent"));
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
        Alert tableIsEmptyAlert = new Alert(Alert.AlertType.ERROR);
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
}
