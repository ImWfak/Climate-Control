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
import java.time.LocalDate;
import java.util.regex.Pattern;
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
    private final TextField weatherSeasonField = new TextField();
    private final TextField weatherCommentField = new TextField();
    private final TextField dayTemperatureField = new TextField();
    private final TextField dayDateField = new TextField();
    private final TextField dayCommentField = new TextField();
    private final Button editButton = new Button();
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
    public TextField getWeatherSeasonField() { return weatherSeasonField; }
    public TextField getWeatherCommentField() { return weatherCommentField; }
    public TextField getDayTemperatureField() { return dayTemperatureField; }
    public TextField getDayDateField() { return dayDateField; }
    public TextField getDayCommentField() { return dayCommentField; }
    public Button editButton() { return editButton; }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // METHODS
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // TABLES
    private void buildWeathersTable() {
        weathersPositionColumn.setPrefWidth(95);
        weathersSeasonColumn.setPrefWidth(240);
        weathersCommentColumn.setPrefWidth(240);
        weathersPositionColumn.setCellValueFactory(column -> {
            return new TableCell<Weather, Integer>() {
                @Override
                protected void updateItem(Integer item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item != null || !empty)
                        setText(String.valueOf(getIndex() + 1));
                }
            }.itemProperty();
        });
        weathersSeasonColumn.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getSeason()));
        weathersCommentColumn.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getComment()));
        weathersTable.getColumns().addAll(
                weathersPositionColumn,
                weathersSeasonColumn,
                weathersCommentColumn
        );
    }
    private void loadWeathersTableBySeason() {
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
        }
    }
    private void loadWeathersTableByComment() {
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
        }
    }
    private void loadWeatherTableByPosition() {
        if (controller.getClimate().getWeathers().isEmpty()) {
            Alerts.createNoWeathersInFile(controller.getAlertResourceBundle()).show();
        } else {
            findButton.setOnAction(actionEvent -> {
                if (Pattern.matches("^-?\\d+$", findField.getCharacters())) {
                    int index = Integer.parseInt(findField.getCharacters().toString());
                    if (index < 0 || index > controller.getClimate().getWeathers().size())
                        Alerts.createIndexOutOfBoundsAlert(controller.getAlertResourceBundle()).show();
                    else
                        weathersTable.getItems().add(controller.getClimate().getWeathers().get(index));
                } else {
                    Alerts.createWrongInputAlert(controller.getAlertResourceBundle()).show();
                }
            });
        }
    }
    private void buildDaysTable() {
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
        daysTable.getColumns().addAll(
                daysPositionColumn,
                daysTemperatureColumn,
                daysDateColumn,
                daysCommentColumn
        );
    }
    private void loadDaysTableByTemperature() {
        if (controller.getClimate().getWeathers().isEmpty()) {
            Alerts.createNoWeathersInFile(controller.getAlertResourceBundle()).show();
        } else if (controller.getSelectedWeather() == null) {
            Alerts.createNoSelectedWeatherAlert(controller.getAlertResourceBundle()).show();
        } else {
            findButton.setOnAction(actionEvent -> {
                if (Pattern.matches("^[+-]?(\\d*\\.?\\d+|\\d+\\.?\\d*)$", findField.getCharacters())) {
                    controller.getSelectedWeather().getDays().forEach(day -> {
                        if (day.getTemperature() == Double.parseDouble(findField.getCharacters().toString()))
                            daysTable.getItems().add(day);
                    });
                    if (daysTable.getItems().isEmpty())
                        Alerts.createNoSuchDaysAlert(controller.getAlertResourceBundle()).show();
                } else {
                    Alerts.createWrongInputAlert(controller.getAlertResourceBundle()).show();
                }
            });
        }
    }
    private void loadDaysTableByDate() {
        if (controller.getClimate().getWeathers().isEmpty()) {
            Alerts.createNoWeathersInFile(controller.getAlertResourceBundle()).show();
        } else if (controller.getSelectedWeather() == null) {
            Alerts.createNoSelectedWeatherAlert(controller.getAlertResourceBundle()).show();
        } else {
            findButton.setOnAction(actionEvent -> {
                if (Pattern.matches("^\\d{4}-\\d{2}-\\d{2}$", findField.getCharacters())) {
                    controller.getSelectedWeather().getDays().forEach(day -> {
                        if (day.getDate() == LocalDate.parse(findField.getCharacters().toString()))
                            daysTable.getItems().add(day);
                    });
                    if (daysTable.getItems().isEmpty())
                        Alerts.createNoSuchDaysAlert(controller.getAlertResourceBundle()).show();
                } else {
                    Alerts.createWrongInputAlert(controller.getAlertResourceBundle()).show();
                }
            });
        }
    }
    private void loadDaysTableByComment() {
        if (controller.getClimate().getWeathers().isEmpty()) {
            Alerts.createNoWeathersInFile(controller.getAlertResourceBundle()).show();
        } else if (controller.getSelectedWeather() == null) {
            Alerts.createNoSelectedWeatherAlert(controller.getAlertResourceBundle()).show();
        } else {
            findButton.setOnAction(actionEvent -> {
                controller.getSelectedWeather().getDays().forEach(day -> {
                    if (Objects.equals(day.getComment(), findField.getCharacters().toString()))
                        daysTable.getItems().add(day);
                });
                if (daysTable.getItems().isEmpty())
                    Alerts.createNoSuchDaysAlert(controller.getAlertResourceBundle()).show();
            });
        }
    }
    private void loadDaysTableByPosition() {
        if (controller.getClimate().getWeathers().isEmpty()) {
            Alerts.createNoWeathersInFile(controller.getAlertResourceBundle()).show();
        } else if (controller.getSelectedWeather() == null) {
            Alerts.createNoSelectedWeatherAlert(controller.getAlertResourceBundle()).show();
        } else {
            findButton.setOnAction(actionEvent -> {
                if (Pattern.matches("^-?\\d+$", findField.getCharacters())) {
                    int index = Integer.parseInt(findField.getCharacters().toString());
                    if (index < 0 || index > controller.getSelectedWeather().getDays().size())
                        Alerts.createIndexOutOfBoundsAlert(controller.getAlertResourceBundle()).show();
                    else
                        daysTable.getItems().add(controller.getSelectedWeather().getDays().get(index));
                } else {
                    Alerts.createWrongInputAlert(controller.getAlertResourceBundle()).show();
                }
            });
        }
    }
    // DELETE WEATHER WINDOW
    private void buildDeleteWeatherWindow() {
        anchorPane.setPrefWidth(625);
        anchorPane.setPrefHeight(400);
        scrollPane.setLayoutX(25);
        scrollPane.setLayoutY(25);
        scrollPane.setPrefWidth(575);
        scrollPane.setPrefHeight(300);
        buildWeathersTable();
        scrollPane.setContent(weathersTable);
        findField.setLayoutX(25);
        findField.setLayoutY(350);
        findField.setPrefWidth(150);
        findField.setPrefHeight(25);
        findButton.setLayoutX(200);
        findButton.setLayoutY(350);
        findButton.setPrefWidth(100);
        findButton.setPrefHeight(25);
        deleteAllButton.setLayoutX(325);
        deleteAllButton.setLayoutY(350);
        deleteAllButton.setPrefWidth(100);
        deleteAllButton.setPrefHeight(25);
        deleteSelectedButton.setLayoutX(450);
        deleteSelectedButton.setLayoutY(350);
        deleteSelectedButton.setPrefWidth(100);
        deleteSelectedButton.setPrefHeight(25);
        anchorPane.getChildren().addAll(
                scrollPane,
                findField,
                findButton,
                deleteAllButton,
                deleteSelectedButton
        );
        deleteAllButton.setOnAction(actionEvent -> {
            if (weathersTable.getItems().isEmpty()) {
                Alerts.createTableIsEmptyAlert(controller.getAlertResourceBundle()).show();
            } else {
                weathersTable.getItems().forEach(weather -> {
                    controller.getClimate().getWeathers().remove(weather);
                });
                weathersTable.getItems().clear();
                controller.updateWeathersTable();
                controller.setFileChangesSaved(false);
            }
        });
        deleteSelectedButton.setOnAction(actionEvent -> {
            if (weathersTable.getItems().isEmpty()) {
                Alerts.createTableIsEmptyAlert(controller.getAlertResourceBundle()).show();
            } else if (weathersTable.getSelectionModel().isEmpty()) {
                Alerts.createNoSelectedRowAlert(controller.getAlertResourceBundle()).show();
            } else {
                Weather weather = weathersTable.getSelectionModel().getSelectedItem();
                weathersTable.getItems().remove(weather);
                controller.getClimate().getWeathers().remove(weather);
                controller.updateWeathersTable();
                controller.setFileChangesSaved(false);
            }
        });
    }
    // DELETE DAY WINDOW
    private void buildDeleteDayWindow() {
        anchorPane.setPrefWidth(625);
        anchorPane.setPrefHeight(400);
        scrollPane.setLayoutX(25);
        scrollPane.setLayoutY(25);
        scrollPane.setPrefWidth(575);
        scrollPane.setPrefHeight(300);
        buildDaysTable();
        scrollPane.setContent(daysTable);
        findField.setLayoutX(25);
        findField.setLayoutY(350);
        findField.setPrefWidth(150);
        findField.setPrefHeight(25);
        findButton.setLayoutX(200);
        findButton.setLayoutY(350);
        findButton.setPrefWidth(100);
        findButton.setPrefHeight(25);
        deleteAllButton.setLayoutX(325);
        deleteAllButton.setLayoutY(350);
        deleteAllButton.setPrefWidth(100);
        deleteAllButton.setPrefHeight(25);
        deleteSelectedButton.setLayoutX(450);
        deleteSelectedButton.setLayoutY(350);
        deleteSelectedButton.setPrefWidth(100);
        deleteSelectedButton.setPrefHeight(25);
        anchorPane.getChildren().addAll(
                scrollPane,
                findField,
                findButton,
                deleteAllButton,
                deleteSelectedButton
        );
        deleteAllButton.setOnAction(actionEvent -> {
            if (daysTable.getItems().isEmpty()) {
                Alerts.createTableIsEmptyAlert(controller.getAlertResourceBundle()).show();
            } else {
                daysTable.getItems().forEach(day -> {
                    controller.getSelectedWeather().getDays().remove(day);
                });
                daysTable.getItems().clear();
                controller.updateDaysTable();
                controller.setFileChangesSaved(false);
            }
        });
        deleteSelectedButton.setOnAction(actionEvent -> {
            if (daysTable.getItems().isEmpty()) {
                Alerts.createTableIsEmptyAlert(controller.getAlertResourceBundle()).show();
            } else if (daysTable.getSelectionModel().isEmpty()){
                Alerts.createNoSelectedRowAlert(controller.getAlertResourceBundle()).show();
            } else {
                Day day = daysTable.getSelectionModel().getSelectedItem();
                daysTable.getItems().remove(day);
                controller.getSelectedWeather().getDays().remove(day);
                controller.updateDaysTable();
                controller.setFileChangesSaved(false);
            }
        });
    }
    // EDIT WEATHER WINDOW
    private void buildEditWeatherWindow() {
        anchorPane.setPrefWidth(625);
        anchorPane.setPrefHeight(450);
        scrollPane.setLayoutX(25);
        scrollPane.setLayoutY(25);
        scrollPane.setPrefWidth(575);
        scrollPane.setPrefHeight(300);
        buildWeathersTable();
        scrollPane.setContent(weathersTable);
        findField.setLayoutX(25);
        findField.setLayoutY(350);
        findField.setPrefWidth(150);
        findField.setPrefHeight(25);
        findButton.setLayoutX(200);
        findButton.setLayoutY(350);
        findButton.setPrefWidth(100);
        findButton.setPrefHeight(25);
        weatherSeasonField.setLayoutX(400);
        weatherSeasonField.setLayoutY(25);
        weatherSeasonField.setPrefWidth(150);
        weatherSeasonField.setPrefHeight(25);
        weatherCommentField.setLayoutX(400);
        weatherCommentField.setLayoutY(200);
        weatherCommentField.setPrefWidth(150);
        weatherCommentField.setPrefHeight(25);
        editButton.setLayoutX(400);
        editButton.setLayoutY(375);
        editButton.setPrefWidth(100);
        editButton.setPrefHeight(25);
        anchorPane.getChildren().addAll(
                scrollPane,
                findField,
                findButton,
                weatherSeasonField,
                weatherCommentField,
                editButton
        );
        editButton.setOnAction(actionEvent -> {
            if (controller.getClimate().getWeathers().isEmpty()) {
                Alerts.createNoWeathersInFile(controller.getAlertResourceBundle()).show();
            } else if (weathersTable.getSelectionModel().isEmpty()) {
                Alerts.createNoSelectedRowAlert(controller.getAlertResourceBundle()).show();
            } else {
                int index = controller.getClimate().getWeathers().indexOf(weathersTable.getSelectionModel().getSelectedItem());
                Weather weather = controller.getClimate().getWeathers().get(index);
                weather.setSeason(weatherSeasonField.getCharacters().toString());
                weather.setComment(weatherCommentField.getCharacters().toString());
                controller.getClimate().getWeathers().set(index, weather);
                controller.updateWeathersTable();
                controller.setFileChangesSaved(false);
            }
        });
    }
}
