package info.climateControl.window.tabs;

import info.climateControl.window.controller.Controller;

import java.util.Locale;

public class ViewTab {
    private Controller controller;
    public ViewTab(Controller controller) {
        this.controller = controller;
    }
    public void pressedSetEnToggleButton() {
        controller.getSetEnToggleButton().setOnAction(actionEvent -> {
            controller.setLocale(Locale.US);
            controller.setLanguage();
            controller.getSetEnToggleButton().setDisable(true);
            controller.getSetUaToggleButton().setDisable(false);
            controller.getSetRuToggleButton().setDisable(false);
        });
    }
    public void pressedSetUaToggleButton() {
        controller.getSetUaToggleButton().setOnAction(actionEvent -> {
            controller.setLocale(new Locale("ua", "UK"));
            controller.setLanguage();
            controller.getSetEnToggleButton().setDisable(false);
            controller.getSetUaToggleButton().setDisable(true);
            controller.getSetRuToggleButton().setDisable(false);
        });
    }
    public void pressedSetRuToggleButton() {
        controller.getSetRuToggleButton().setOnAction(actionEvent -> {
            controller.setLocale(new Locale("ru", "RU"));
            controller.setLanguage();
            controller.getSetEnToggleButton().setDisable(false);
            controller.getSetUaToggleButton().setDisable(false);
            controller.getSetRuToggleButton().setDisable(true);
        });
    }
}
