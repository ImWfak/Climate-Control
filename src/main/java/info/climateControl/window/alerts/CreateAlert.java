package info.climateControl.window.alerts;

import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public interface CreateAlert {
     static Alert createAlert(
            AlertType alertType,
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
}
