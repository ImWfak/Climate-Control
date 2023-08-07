package info.climateControl.window.tabs;

import info.climateControl.window.controller.Controller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Locale;

public class ViewTab {
    private static final Logger logger = LogManager.getLogger(ViewTab.class);
    private Controller controller;
    public ViewTab(Controller controller) {
        this.controller = controller;
    }
    public void pressedSetEnToggleButton() {
        controller.getSetEnToggleButton().setOnAction(actionEvent -> {
            logger.info("pressed 'setEnToggleButton'");
            controller.setLocale(Locale.US);
            controller.setLanguage();
            controller.getSetEnToggleButton().setDisable(true);
            controller.getSetUaToggleButton().setDisable(false);
            controller.getSetRuToggleButton().setDisable(false);
            controller.getSetEnToggleButton().setSelected(true);
            controller.getSetUaToggleButton().setSelected(false);
            controller.getSetRuToggleButton().setSelected(false);
        });
    }
    public void pressedSetUaToggleButton() {
        controller.getSetUaToggleButton().setOnAction(actionEvent -> {
            logger.info("pressed 'setUaToggleButton'");
            controller.setLocale(new Locale("ua", "UK"));
            controller.setLanguage();
            controller.getSetEnToggleButton().setDisable(false);
            controller.getSetUaToggleButton().setDisable(true);
            controller.getSetRuToggleButton().setDisable(false);
            controller.getSetEnToggleButton().setSelected(false);
            controller.getSetUaToggleButton().setSelected(true);
            controller.getSetRuToggleButton().setSelected(false);
        });
    }
    public void pressedSetRuToggleButton() {
        controller.getSetRuToggleButton().setOnAction(actionEvent -> {
            logger.info("pressed 'setRuToggleButton'");
            controller.setLocale(new Locale("ru", "RU"));
            controller.setLanguage();
            controller.getSetEnToggleButton().setDisable(false);
            controller.getSetUaToggleButton().setDisable(false);
            controller.getSetRuToggleButton().setDisable(true);
            controller.getSetEnToggleButton().setSelected(false);
            controller.getSetUaToggleButton().setSelected(false);
            controller.getSetRuToggleButton().setSelected(true);
        });
    }
}
