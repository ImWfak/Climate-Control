package info.climateControl.window.alerts;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert;
import java.util.ResourceBundle;

public interface Alerts {
    static Alert createAlertWithButtons(
        Alert.AlertType alertType,
        String title,
        String header,
        String context,
        ButtonType... buttons
    ) {
            Alert alert = new Alert(alertType);
            alert.setTitle(title);
            alert.setHeaderText(header);
            alert.setContentText(context);
            alert.getButtonTypes().setAll(buttons);
            return alert;
    }
    private static Alert createAlert(
            Alert.AlertType alertType,
            String title,
            String header,
            String context
    ) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(context);
        return alert;
    }
    static Alert createWrongFileExtensionAlert(ResourceBundle resourceBundle) {
        return createAlert(
                Alert.AlertType.ERROR,
                resourceBundle.getString("wrongFileExtensionAlertTitle"),
                resourceBundle.getString("wrongFileExtensionAlertHeader"),
                resourceBundle.getString("wrongFileExtensionAlertContent")
        );
    }
    static Alert createFileHasNotChosenAlert(ResourceBundle resourceBundle) {
        return createAlert(
                Alert.AlertType.WARNING,
                resourceBundle.getString("fileHasNotChosenAlertTitle"),
                resourceBundle.getString("fileHasNotChosenAlertHeader"),
                resourceBundle.getString("fileHasNotChosenAlertContent")
        );
    }
    static Alert createFileIsNotOpenAlert(ResourceBundle resourceBundle) {
        return createAlert(
                Alert.AlertType.ERROR,
                resourceBundle.getString("fileIsNotOpenAlertTitle"),
                resourceBundle.getString("fileIsNotOpenAlertHeader"),
                resourceBundle.getString("fileIsNotOpenAlertContent")
        );
    }
    static Alert createNoSuchWeatherAlert(ResourceBundle resourceBundle) {
        return createAlert(
                Alert.AlertType.WARNING,
                resourceBundle.getString("noSuchWeathersAlertTitle"),
                resourceBundle.getString("noSuchWeathersAlertHeader"),
                resourceBundle.getString("noSuchWeathersAlertContent")
        );
    }
    static Alert createIndexOutOfBoundsAlert(ResourceBundle resourceBundle) {
        return createAlert(
                Alert.AlertType.ERROR,
                resourceBundle.getString("indexOutOfBoundsAlertTitle"),
                resourceBundle.getString("indexOutOfBoundsAlertHeader"),
                resourceBundle.getString("indexOutOfBoundsAlertContent")
        );
    }
    static Alert createWrongInputAlert(ResourceBundle resourceBundle) {
        return createAlert(
                Alert.AlertType.ERROR,
                resourceBundle.getString("wrongInputAlert"),
                resourceBundle.getString("wrongInputAlertHeader"),
                resourceBundle.getString("wrongInputAlertContent")
        );
    }
    static Alert createNoSuchDaysAlert(ResourceBundle resourceBundle) {
        return createAlert(
                Alert.AlertType.WARNING,
                resourceBundle.getString("noSuchDaysAlertTitle"),
                resourceBundle.getString("noSuchDaysAlertHeader"),
                resourceBundle.getString("noSuchDaysAlertContent")
        );
    }
    static Alert createNoWeatherInFileAlert(ResourceBundle resourceBundle) {
        return createAlert(
                Alert.AlertType.WARNING,
                resourceBundle.getString("noWeatherInFileAlertTitle"),
                resourceBundle.getString("noWeatherInFileAlertHeader"),
                resourceBundle.getString("noWeatherInFileAlertContent")
        );
    }
    static Alert createTableIsEmptyAlert(ResourceBundle resourceBundle) {
        return createAlert(
                Alert.AlertType.WARNING,
                resourceBundle.getString("tableIsEmptyAlertTitle"),
                resourceBundle.getString("tableIsEmptyAlertHeader"),
                resourceBundle.getString("tableIsEmptyAlertContent")
        );
    }
    static Alert createNoSelectedRowAlert(ResourceBundle resourceBundle) {
        return createAlert(
                Alert.AlertType.ERROR,
                resourceBundle.getString("noSelectedRowAlertTitle"),
                resourceBundle.getString("noSelectedRowAlertHeader"),
                resourceBundle.getString("noSelectedRowAlertContent")
        );
    }
    static Alert createNoSelectedWeatherAlert(ResourceBundle resourceBundle) {
        return createAlert(
                Alert.AlertType.ERROR,
                resourceBundle.getString("noSelectedWeatherAlertTitle"),
                resourceBundle.getString("noSelectedWeatherAlertHeader"),
                resourceBundle.getString("noSelectedWeatherAlertContent")
        );
    }
    static Alert createWrongFileContentAlert(ResourceBundle resourceBundle) {
        return createAlert(
                Alert.AlertType.ERROR,
                resourceBundle.getString("wrongFileContentAlertTitle"),
                resourceBundle.getString("wrongFileContentAlertHeader"),
                resourceBundle.getString("wrongFileContentAlertContent")
        );
    }
}
