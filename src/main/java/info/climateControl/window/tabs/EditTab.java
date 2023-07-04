package info.climateControl.window.tabs;

import info.climateControl.window.controller.Controller;
import info.climateControl.window.alerts.Alerts;
import info.climateControl.weather.Weather;
import info.climateControl.day.Day;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.*;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.util.Objects;

public class EditTab implements Alerts {
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // WINDOW`S ITEMS
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private final AnchorPane anchorPane = new AnchorPane();
    private final ScrollPane scrollPane = new ScrollPane();
    private final TableView<Weather> weathersTable = new TableView<>();
    private final TableColumn<Weather, Integer> weathersPositionColumn = new TableColumn<>();
    private final TableColumn<Weather, String> weathersSeasonColumn = new TableColumn<>();
    private final TableColumn<Weather, String> weathersCommentColumn = new TableColumn<>();
    private final TableView<Day> daysTable = new TableView<>();
    private final TableColumn<Day, Integer> daysPositionColumn = new TableColumn<>();
    private final TableColumn<Day, Double> daysTemperatureColumn = new TableColumn<>();
    private final TableColumn<Day, LocalDate> daysDateColumn = new TableColumn<>();
    private final TableColumn<Day, String> daysCommentColumn = new TableColumn<>();
    private final TextField findField = new TextField();
    private final Button findButton = new Button();
    private final Button deleteAllButton = new Button();
    private final Button deleteSelectedButton = new Button();
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // MAIN VARIABLES
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private static final Logger logger = LogManager.getLogger(EditTab.class);
    private final Controller controller;
    /** constructor which sets controller of current editTab object
     * @param controller    fxml class which will be set as controller */
    public EditTab(Controller controller) {
        this.controller = controller;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // GETTERS
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public TableView<Weather> getWeathersTable() { return weathersTable; }
    public TableColumn<Weather, Integer> getWeathersPositionColumn() { return weathersPositionColumn; }
    public TableColumn<Weather, String> getWeathersSeasonColumn() {return weathersSeasonColumn; }
    public TableColumn<Weather, String> getWeathersCommentColumn() { return weathersCommentColumn; }
    public TableView<Day> getDaysTable() { return daysTable; }
    public TableColumn<Day, Integer> getDaysPositionColumn() { return daysPositionColumn; }
    public TableColumn<Day, Double> getDaysTemperatureColumn() { return daysTemperatureColumn; }
    public TableColumn<Day, LocalDate> getDaysDateColumn() { return daysDateColumn; }
    public TableColumn<Day, String> getDaysCommentColumn() { return daysCommentColumn; }
    public TextField getFindField() { return findField; }
    public Button getFindButton() { return findButton; }
    public Button getDeleteAllButton() { return deleteAllButton; }
    public Button getDeleteSelectedButton() { return deleteSelectedButton; }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // METHODS
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void buildDeleteWeatherWindow() {
        anchorPane.setPrefWidth(625);
        anchorPane.setPrefHeight(400);
        scrollPane.setLayoutX(25);
        scrollPane.setLayoutY(25);
        scrollPane.setPrefWidth(575);
        scrollPane.setPrefHeight(300);
        weathersPositionColumn.setPrefWidth(95);
        weathersSeasonColumn.setPrefWidth(240);
        weathersCommentColumn.setPrefWidth(240);
        weathersPositionColumn.setCellValueFactory(column -> {
            return new TableCell<Weather, Integer>() {
                @Override
                protected void updateItem(Integer item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty)
                        setText("");
                    else
                        setText(String.valueOf(getIndex() + 1));
                }
            }.itemProperty();
        });
        weathersSeasonColumn.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getSeason()));
        weathersCommentColumn.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getComment()));
        scrollPane.setContent(weathersTable);
        findField.setLayoutX(25);
        findField.setLayoutY(325);
        findField.setPrefWidth(150);
        findField.setPrefHeight(25);
        findButton.setLayoutX(200);
        findButton.setLayoutY(325);
        findButton.setPrefWidth(100);
        findButton.setPrefHeight(25);
        deleteAllButton.setLayoutX(325);
        deleteAllButton.setLayoutY(325);
        deleteAllButton.setPrefWidth(100);
        deleteAllButton.setPrefHeight(25);
        deleteSelectedButton.setLayoutX(450);
        deleteSelectedButton.setLayoutY(325);
        deleteSelectedButton.setPrefWidth(100);
        deleteSelectedButton.setPrefHeight(25);
        anchorPane.getChildren().addAll(
                scrollPane,
                findField,
                findButton,
                deleteAllButton,
                deleteSelectedButton
        );
    }
    public void showDeleteWeatherBySeasonWindow() {
        if (controller.getClimate().getWeathers().isEmpty()) {
            Alerts.createNoWeathersInFile(controller.getAlertResourceBundle()).show();
        } else {
            findButton.setOnAction(actionEvent -> {
                    controller.getClimate().getWeathers().forEach(weather -> {
                        if (Objects.equals(weather.getSeason(), findField.getCharacters().toString()))
                            weathersTable.getItems().add(weather);
                    });
                    if (weathersTable.getItems().isEmpty())
                        Alerts.createNoSuchWeathersAlert(controller.getAlertResourceBundle()).show();
                });
            Stage stage = new Stage();
            stage.setScene(new Scene(anchorPane, 625, 400));
            stage.setTitle("");
            stage.show();
        }
    }
    public void showDeleteWeatherByCommentWindow() {
        if (controller.getClimate().getWeathers().isEmpty()) {
            Alerts.createNoWeathersInFile(controller.getAlertResourceBundle()).show();
        } else {
            findButton.setOnAction(actionEvent -> {
                controller.getClimate().getWeathers().forEach(weather -> {
                    if (Objects.equals(weather.getComment(), findField.getCharacters().toString()))
                        weathersTable.getItems().add(weather);
                });
                if (weathersTable.getItems().isEmpty())
                    Alerts.createNoSuchWeathersAlert(controller.getAlertResourceBundle()).show();
            });
            Stage stage = new Stage();
            stage.setScene(new Scene(anchorPane, 625, 400));
            stage.setTitle("");
            stage.show();
        }
    }
    public void showDeleteWeatherByPositionWindow() {
        if (controller.getClimate().getWeathers().isEmpty()) {
            Alerts.createNoWeathersInFile(controller.getAlertResourceBundle()).show();
        } else {
            findButton.setOnAction(actionEvent -> {
                int index = Integer.parseInt(findField.getCharacters().toString());
                if (index < 0 || index > controller.getClimate().getWeathers().size())
                    Alerts.createIndexOutOfBoundsAlert(controller.getAlertResourceBundle()).show();
                else
                    weathersTable.getItems().add(controller.getClimate().getWeathers().get(index));
                if (weathersTable.getItems().isEmpty())
                    Alerts.createNoSuchWeathersAlert(controller.getAlertResourceBundle()).show();
            });
            Stage stage = new Stage();
            stage.setScene(new Scene(anchorPane, 625, 400));
            stage.setTitle("");
            stage.show();
        }
    }
    private void buildDeleteDayWindow() {
        anchorPane.setPrefWidth(625);
        anchorPane.setPrefHeight(400);
        scrollPane.setLayoutX(25);
        scrollPane.setLayoutY(25);
        scrollPane.setPrefWidth(575);
        scrollPane.setPrefHeight(300);
        daysPositionColumn.setPrefWidth(95);
        daysTemperatureColumn.setPrefWidth(160);
        daysDateColumn.setPrefWidth(160);
        daysCommentColumn.setPrefWidth(160);
        daysPositionColumn.setCellValueFactory(column -> {
            return new TableCell<Weather, Integer>() {
                @Override
                protected void updateItem(Integer item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item != null || !empty)
                        setText(String.valueOf(getIndex() + 1));
                }
            }.itemProperty();
        });
        daysTemperatureColumn.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getTemperature()));
        daysDateColumn.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getDate()));
        daysCommentColumn.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getComment()));
        scrollPane.setContent(daysTable);
        findField.setLayoutX(25);
        findField.setLayoutY(325);
        findField.setPrefWidth(150);
        findField.setPrefHeight(25);
        findButton.setLayoutX(200);
        findButton.setLayoutY(325);
        findButton.setPrefWidth(100);
        findButton.setPrefHeight(25);
        deleteAllButton.setLayoutX(325);
        deleteAllButton.setLayoutY(325);
        deleteAllButton.setPrefWidth(100);
        deleteAllButton.setPrefHeight(25);
        deleteSelectedButton.setLayoutX(450);
        deleteSelectedButton.setLayoutY(325);
        deleteSelectedButton.setPrefWidth(100);
        deleteSelectedButton.setPrefHeight(25);
        anchorPane.getChildren().addAll(
                scrollPane,
                findField,
                findButton,
                deleteAllButton,
                deleteSelectedButton
        );

    }
    public void showDeleteDayByTemperatureWindow() {

    }
    public void showDeleteDayByDateWindow() {

    }
    public void showDeleteDayByCommentWindow() {

    }
    public void showDeleteDayByPositionWindow() {

    }
}
