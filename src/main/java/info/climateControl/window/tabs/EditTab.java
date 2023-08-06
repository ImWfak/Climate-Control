package info.climateControl.window.tabs;

import info.climateControl.window.controller.Controller;
import info.climateControl.window.alerts.Alerts;
import info.climateControl.weather.Weather;
import info.climateControl.day.Day;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.*;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.time.LocalDate;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.Objects;

/**<p><b>methods in EditTab</b></p>
 * <p>private:</p>
 * <li>{@link #buildWeathersTable()}</li>
 * <li>{@link #loadWeathersTableBySeason()}</li>
 * <li>{@link #loadWeathersTableByComment()}</li>
 * <li>{@link #loadWeatherTableByPosition()}</li>
 * <li>{@link #buildDaysTable()}</li>
 * <li>{@link #loadDaysTableByTemperature()}</li>
 * <li>{@link #loadDaysTableByDate()}</li>
 * <li>{@link #loadDaysTableByComment()}</li>
 * <li>{@link #loadDaysTableByPosition()}</li>
 * <li>{@link #showDeleteWeatherWindow()}</li>
 * <li>{@link #showDeleteDayWindow()}</li>
 * <li>{@link #showEditWeatherWindow()} d}</li>
 * <li>{@link #showEditDayWindow()}</li>
 * <li>{@link #showAddWeatherWindow()}</li>
 * <li>{@link #showAddDayWindow()}</li>
 * <p>public:</p>
 * <li>{@link #pressedDeleteWeatherBySeasonButton()}</li>
 * <li>{@link #pressedDeleteWeatherByCommentButton()}</li>
 * <li>{@link #pressedDeleteWeatherByPositionButton()}</li>
 * <li>{@link #pressedDeleteDayByTemperatureButton()}</li>
 * <li>{@link #pressedDeleteDayByDateButton()}</li>
 * <li>{@link #pressedDeleteDayByCommentButton()}</li>
 * <li>{@link #pressedDeleteDayByPositionButton()}</li>
 * <li>{@link #pressedEditWeatherBySeasonButton()} </li>
 * <li>{@link #pressedEditWeatherByCommentButton()}</li>
 * <li>{@link #pressedEditWeatherByPositionButton()}</li>
 * <li>{@link #pressedEditDayByTemperatureButton()}</li>
 * <li>{@link #pressedEditDayByDateButton()}</li>
 * <li>{@link #pressedEditDayByCommentButton()}</li>
 * <li>{@link #pressedEditeDayByPositionButton()}</li>
 * <li>{@link #pressedAddWeatherButton()}</li>
 * <li>{@link #pressedAddDayButton()}</li> */
public class EditTab implements Alerts {
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // WINDOW`S ITEMS
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private final Stage stage = new Stage();
    private final AnchorPane anchorPane = new AnchorPane();
    private final ScrollPane scrollPane = new ScrollPane();
    private final TableView<Weather> weathersTable = new TableView<>();
    private final TableColumn<Weather, Weather> weathersPositionColumn = new TableColumn<>();
    private final TableColumn<Weather, String> weathersSeasonColumn = new TableColumn<>();
    private final TableColumn<Weather, String> weathersCommentColumn = new TableColumn<>();
    private final TableView<Day> daysTable = new TableView<>();
    private final TableColumn<Day, Day> daysPositionColumn = new TableColumn<>();
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
    private final Button addButton = new Button();
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
    {
        stage.setScene(new Scene(anchorPane));
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // GETTERS
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public TableView<Weather> getWeathersTable() { return weathersTable; }
    public TableColumn<Weather, Weather> getWeathersPositionColumn() { return weathersPositionColumn; }
    public TableColumn<Weather, String> getWeathersSeasonColumn() {return weathersSeasonColumn; }
    public TableColumn<Weather, String> getWeathersCommentColumn() { return weathersCommentColumn; }
    public TableView<Day> getDaysTable() { return daysTable; }
    public TableColumn<Day, Day> getDaysPositionColumn() { return daysPositionColumn; }
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
    public Button getEditButton() { return editButton; }
    public Button getAddButton() { return addButton; }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // PRIVATE METHODS
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // TABLES
    /** private method which build table of weathers */
    private void buildWeathersTable() {
        weathersPositionColumn.setPrefWidth(95);
        weathersSeasonColumn.setPrefWidth(240);
        weathersCommentColumn.setPrefWidth(240);
        weathersPositionColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue()));
        weathersPositionColumn.setCellFactory(cellData -> new TableCell<Weather, Weather>() {
            @Override
            protected void updateItem(Weather weather, boolean empty) {
                super.updateItem(weather, empty);
                if (weather != null || !empty)
                    setText(String.valueOf(getIndex() + 1));
            }
        });
        weathersSeasonColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSeason()));
        weathersCommentColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getComment()));
        weathersTable.getColumns().setAll(
                weathersPositionColumn,
                weathersSeasonColumn,
                weathersCommentColumn
        );
    }
    /** private method which fill table of weathers by season */
    private void loadWeathersTableBySeason() {
        findButton.setOnAction(actionEvent -> {
            controller.getClimate().getWeathers().forEach(weather -> {
                if (Objects.equals(weather.getSeason(), findField.getCharacters().toString()))
                    weathersTable.getItems().add(weather);
            });
                if (weathersTable.getItems().isEmpty())
                    Alerts.createNoSuchWeatherAlert(controller.getAlertResourceBundle()).show();
        });
    }
    /** private method which fill table of weather by comment */
    private void loadWeathersTableByComment() {
        findButton.setOnAction(actionEvent -> {
            controller.getClimate().getWeathers().forEach(weather -> {
                if (Objects.equals(weather.getComment(), findField.getCharacters().toString()))
                    weathersTable.getItems().add(weather);
            });
            if (weathersTable.getItems().isEmpty())
                Alerts.createNoSuchWeatherAlert(controller.getAlertResourceBundle()).show();
        });
    }
    /** private method which fill table of weather by position */
    private void loadWeatherTableByPosition() {
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
    /** private method which build table of days */
    private void buildDaysTable() {
        daysPositionColumn.setPrefWidth(95);
        daysTemperatureColumn.setPrefWidth(160);
        daysDateColumn.setPrefWidth(160);
        daysCommentColumn.setPrefWidth(160);
        daysPositionColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue()));
        daysPositionColumn.setCellFactory(cellData -> new TableCell<Day, Day>() {
            @Override
            protected void updateItem(Day day, boolean empty) {
                super.updateItem(day, empty);
                if (day != null || !empty)
                    setText(String.valueOf(getIndex() + 1));
            }
        });
        daysTemperatureColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getTemperature()).asObject());
        daysDateColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getDate()));
        daysCommentColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getComment()));
        daysTable.getColumns().setAll(
                daysPositionColumn,
                daysTemperatureColumn,
                daysDateColumn,
                daysCommentColumn
        );
    }
    /** private method which fill table of days by temperature */
    private void loadDaysTableByTemperature() {
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
    /** private method which fill table of days by date */
    private void loadDaysTableByDate() {
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
    /** private method which fill table of days by comment */
    private void loadDaysTableByComment() {
        findButton.setOnAction(actionEvent -> {
            controller.getSelectedWeather().getDays().forEach(day -> {
                if (Objects.equals(day.getComment(), findField.getCharacters().toString()))
                    daysTable.getItems().add(day);
            });
            if (daysTable.getItems().isEmpty())
                Alerts.createNoSuchDaysAlert(controller.getAlertResourceBundle()).show();
        });
    }
    /** private method which fill table of days by position */
    private void loadDaysTableByPosition() {
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
    // DELETE
    /** private method which build delete weather window */
    private void showDeleteWeatherWindow() {
        if (controller.getClimate().getWeathers().isEmpty()) {
            Alerts.createNoWeatherInFileAlert(controller.getAlertResourceBundle()).show();
        } else {
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
            deleteAllButton.setLayoutX(375);
            deleteAllButton.setLayoutY(350);
            deleteAllButton.setPrefWidth(100);
            deleteAllButton.setPrefHeight(25);
            deleteSelectedButton.setLayoutX(500);
            deleteSelectedButton.setLayoutY(350);
            deleteSelectedButton.setPrefWidth(100);
            deleteSelectedButton.setPrefHeight(25);
            anchorPane.getChildren().setAll(
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
                    controller.fillWeathersTable(controller.getClimate().getWeathers());
                    controller.setChangesInFileSaved(false);
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
                    controller.fillWeathersTable(controller.getClimate().getWeathers());
                    controller.setChangesInFileSaved(false);
                }
            });
            stage.show();
        }
    }
    /** private method which build delete day window */
    private void showDeleteDayWindow() {
        if (controller.getClimate().getWeathers().isEmpty()) {
            Alerts.createNoWeatherInFileAlert(controller.getAlertResourceBundle()).show();
        } else if (controller.getSelectedWeather() == null) {
            Alerts.createNoSelectedWeatherAlert(controller.getAlertResourceBundle()).show();
        } else {
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
            deleteAllButton.setLayoutX(375);
            deleteAllButton.setLayoutY(350);
            deleteAllButton.setPrefWidth(100);
            deleteAllButton.setPrefHeight(25);
            deleteSelectedButton.setLayoutX(500);
            deleteSelectedButton.setLayoutY(350);
            deleteSelectedButton.setPrefWidth(100);
            deleteSelectedButton.setPrefHeight(25);
            anchorPane.getChildren().setAll(
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
                    controller.fillDaysTable(controller.getSelectedWeather().getDays());
                    controller.setChangesInFileSaved(false);
                }
            });
            deleteSelectedButton.setOnAction(actionEvent -> {
                if (daysTable.getItems().isEmpty()) {
                    Alerts.createTableIsEmptyAlert(controller.getAlertResourceBundle()).show();
                } else if (daysTable.getSelectionModel().isEmpty()) {
                    Alerts.createNoSelectedRowAlert(controller.getAlertResourceBundle()).show();
                } else {
                    Day day = daysTable.getSelectionModel().getSelectedItem();
                    daysTable.getItems().remove(day);
                    controller.getSelectedWeather().getDays().remove(day);
                    controller.fillDaysTable(controller.getSelectedWeather().getDays());
                    controller.setChangesInFileSaved(false);
                }
            });
            stage.show();
        }
    }
    // EDIT
    /** private method which will build edit weather window */
    private void showEditWeatherWindow() {
        if (controller.getClimate().getWeathers().isEmpty()) {
            Alerts.createNoWeatherInFileAlert(controller.getAlertResourceBundle()).show();
        } else {
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
            weatherSeasonField.setLayoutX(25);
            weatherSeasonField.setLayoutY(400);
            weatherSeasonField.setPrefWidth(150);
            weatherSeasonField.setPrefHeight(25);
            weatherCommentField.setLayoutX(200);
            weatherCommentField.setLayoutY(400);
            weatherCommentField.setPrefWidth(150);
            weatherCommentField.setPrefHeight(25);
            editButton.setLayoutX(500);
            editButton.setLayoutY(400);
            editButton.setPrefWidth(100);
            editButton.setPrefHeight(25);
            anchorPane.getChildren().setAll(
                    scrollPane,
                    findField,
                    findButton,
                    weatherSeasonField,
                    weatherCommentField,
                    editButton
            );
            editButton.setOnAction(actionEvent -> {
                if (controller.getClimate().getWeathers().isEmpty()) {
                    Alerts.createNoWeatherInFileAlert(controller.getAlertResourceBundle()).show();
                } else if (weathersTable.getSelectionModel().isEmpty()) {
                    Alerts.createNoSelectedRowAlert(controller.getAlertResourceBundle()).show();
                } else {
                    int index = controller.getClimate().getWeathers().indexOf(weathersTable.getSelectionModel().getSelectedItem());
                    Weather weather = controller.getClimate().getWeathers().get(index);
                    weather.setSeason(weatherSeasonField.getCharacters().toString());
                    weather.setComment(weatherCommentField.getCharacters().toString());
                    controller.getClimate().getWeathers().set(index, weather);
                    controller.fillWeathersTable(controller.getClimate().getWeathers());
                    controller.setChangesInFileSaved(false);
                }
            });
            stage.show();
        }
    }
    /** private method which will build edit day window */
    private void showEditDayWindow() {
        if (controller.getClimate().getWeathers().isEmpty()) {
            Alerts.createNoWeatherInFileAlert(controller.getAlertResourceBundle()).show();
        } else if (controller.getSelectedWeather() == null) {
            Alerts.createNoSelectedWeatherAlert(controller.getAlertResourceBundle()).show();
        } else {
            anchorPane.setPrefWidth(625);
            anchorPane.setPrefHeight(450);
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
            dayTemperatureField.setLayoutX(25);
            dayTemperatureField.setLayoutY(400);
            dayTemperatureField.setPrefWidth(125);
            dayTemperatureField.setPrefHeight(25);
            dayDateField.setLayoutX(175);
            dayDateField.setLayoutY(400);
            dayDateField.setPrefWidth(125);
            dayDateField.setPrefHeight(25);
            dayCommentField.setLayoutX(325);
            dayCommentField.setLayoutY(400);
            dayCommentField.setPrefWidth(125);
            dayCommentField.setPrefHeight(25);
            editButton.setLayoutX(500);
            editButton.setLayoutY(400);
            editButton.setPrefWidth(100);
            editButton.setPrefHeight(25);
            anchorPane.getChildren().setAll(
                    scrollPane,
                    findField,
                    findButton,
                    dayTemperatureField,
                    dayDateField,
                    dayCommentField,
                    editButton
            );
            editButton.setOnAction(actionEvent -> {
                if (controller.getClimate().getWeathers().isEmpty()) {
                    Alerts.createNoWeatherInFileAlert(controller.getAlertResourceBundle()).show();
                } else if (controller.getSelectedWeather() == null) {
                    Alerts.createNoSelectedWeatherAlert(controller.getAlertResourceBundle()).show();
                } else if (daysTable.getSelectionModel().isEmpty()) {
                    Alerts.createNoSelectedRowAlert(controller.getAlertResourceBundle()).show();
                } else {
                    if (Pattern.matches("^[+-]?(\\d*\\.?\\d+|\\d+\\.?\\d*)$", dayTemperatureField.getCharacters()) ||
                            Pattern.matches("^\\d{4}-\\d{2}-\\d{2}$", dayDateField.getCharacters())) {
                        int index = controller.getSelectedWeather().getDays().indexOf(daysTable.getSelectionModel().getSelectedItem());
                        Day day = new Day(
                                Double.parseDouble(dayTemperatureField.getCharacters().toString()),
                                LocalDate.parse(dayDateField.getCharacters().toString()),
                                dayCommentField.getCharacters().toString()
                        );
                        controller.getSelectedWeather().getDays().set(index, day);
                        controller.fillDaysTable(controller.getSelectedWeather().getDays());
                        controller.setChangesInFileSaved(false);
                    } else {
                        Alerts.createWrongInputAlert(controller.getAlertResourceBundle()).show();
                    }
                }
            });
            stage.show();
        }
    }
    // ADD
    /** private method which will build add weather window */
    private void showAddWeatherWindow() {
        if (!controller.getFileOpen()) {
            Alerts.createFileIsNotOpenAlert(controller.getAlertResourceBundle()).show();
        } else {
            anchorPane.setPrefWidth(625);
            anchorPane.setPrefHeight(125);
            weatherSeasonField.setLayoutX(25);
            weatherSeasonField.setLayoutY(50);
            weatherSeasonField.setPrefWidth(150);
            weatherSeasonField.setPrefHeight(25);
            weatherCommentField.setLayoutX(200);
            weatherCommentField.setLayoutY(50);
            weatherCommentField.setPrefWidth(150);
            weatherCommentField.setPrefHeight(25);
            addButton.setLayoutX(500);
            addButton.setLayoutY(50);
            addButton.setPrefWidth(100);
            addButton.setPrefHeight(25);
            anchorPane.getChildren().setAll(
                    weatherSeasonField,
                    weatherCommentField,
                    addButton
            );
            addButton.setOnAction(actionEvent -> {
                controller.getClimate().getWeathers().add(new Weather(
                        weatherSeasonField.getCharacters().toString(),
                        weatherCommentField.getCharacters().toString(),
                        new ArrayList<Day>()
                ));
                controller.fillWeathersTable(controller.getClimate().getWeathers());
                controller.setChangesInFileSaved(false);
            });
            stage.show();
        }
    }
    /** private method which will build add day window */
    private void showAddDayWindow() {
        if (!controller.getFileOpen()) {
            Alerts.createFileIsNotOpenAlert(controller.getAlertResourceBundle()).show();
        } else if (controller.getClimate().getWeathers().isEmpty()) {
            Alerts.createNoWeatherInFileAlert(controller.getAlertResourceBundle()).show();
        } else if (controller.getSelectedWeather() == null) {
            Alerts.createNoSelectedWeatherAlert(controller.getAlertResourceBundle()).show();
        } else {
            anchorPane.setPrefWidth(625);
            anchorPane.setPrefHeight(125);
            dayTemperatureField.setLayoutX(25);
            dayTemperatureField.setLayoutY(50);
            dayTemperatureField.setPrefWidth(125);
            dayTemperatureField.setPrefHeight(25);
            dayDateField.setLayoutX(175);
            dayDateField.setLayoutY(50);
            dayDateField.setPrefWidth(125);
            dayDateField.setPrefHeight(25);
            dayCommentField.setLayoutX(325);
            dayCommentField.setLayoutY(50);
            dayCommentField.setPrefWidth(125);
            dayCommentField.setPrefHeight(25);
            addButton.setLayoutX(500);
            addButton.setLayoutY(50);
            addButton.setPrefWidth(100);
            addButton.setPrefHeight(25);
            anchorPane.getChildren().setAll(
                    dayTemperatureField,
                    dayDateField,
                    dayCommentField,
                    addButton
            );
            addButton.setOnAction(actionEvent -> {
                if (controller.getClimate().getWeathers().isEmpty()) {
                    Alerts.createNoWeatherInFileAlert(controller.getAlertResourceBundle()).show();
                } else if (controller.getSelectedWeather() == null) {
                    Alerts.createNoSelectedWeatherAlert(controller.getAlertResourceBundle()).show();
                } else {
                    if (Pattern.matches("^[+-]?(\\d*\\.?\\d+|\\d+\\.?\\d*)$", dayTemperatureField.getCharacters()) ||
                            Pattern.matches("^\\d{4}-\\d{2}-\\d{2}$", dayDateField.getCharacters())) {
                        controller.getSelectedWeather().getDays().add(new Day(
                                Double.parseDouble(dayTemperatureField.getCharacters().toString()),
                                LocalDate.parse(dayDateField.getCharacters().toString()),
                                dayCommentField.getCharacters().toString()
                        ));
                        controller.fillDaysTable(controller.getSelectedWeather().getDays());
                        controller.setChangesInFileSaved(false);
                    } else {
                        Alerts.createWrongInputAlert(controller.getAlertResourceBundle()).show();
                    }
                }
            });
            stage.show();
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // PUBLIC METHODS
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // DELETE
    public void pressedDeleteWeatherBySeasonButton() {
        controller.getDeleteWeatherBySeasonButton().setOnAction(actionEvent -> {
            logger.info("pressed 'deleteWeatherBySeasonButton'");
            showDeleteWeatherWindow();
            loadWeathersTableBySeason();
        });
    }
    public void pressedDeleteWeatherByCommentButton() {
        controller.getDeleteWeatherByCommentButton().setOnAction(actionEvent -> {
            logger.info("pressed 'deleteWeatherByCommentButton'");
            showDeleteWeatherWindow();
            loadWeathersTableByComment();
        });
    }
    public void pressedDeleteWeatherByPositionButton() {
        controller.getDeleteWeatherByPositionButton().setOnAction(actionEvent -> {
            logger.info("pressed 'deleteWeatherByPositionButton'");
            showDeleteWeatherWindow();
            deleteAllButton.setVisible(false);
            loadWeatherTableByPosition();
        });
    }
    public void pressedDeleteDayByTemperatureButton() {
        controller.getDeleteDayByTemperatureButton().setOnAction(actionEvent -> {
            logger.info("pressed 'deleteDayByTemperatureButton'");
            showDeleteDayWindow();
            loadDaysTableByTemperature();
        });
    }
    public void pressedDeleteDayByDateButton() {
        controller.getDeleteDayByDateButton().setOnAction(actionEvent -> {
            logger.info("pressed 'deleteDayByDateButton'");
            showDeleteDayWindow();
            loadDaysTableByDate();
        });
    }
    public void pressedDeleteDayByCommentButton() {
        controller.getDeleteDayByCommentButton().setOnAction(actionEvent -> {
            logger.info("pressed 'deleteDayByCommentButton'");
            showDeleteDayWindow();
            loadDaysTableByComment();
        });
    }
    public void pressedDeleteDayByPositionButton() {
        controller.getDeleteDayByPositionButton().setOnAction(actionEvent -> {
            logger.info("pressed 'deleteDayByPositionButton'");
            showDeleteDayWindow();
            deleteAllButton.setVisible(false);
            loadDaysTableByPosition();
        });
    }
    // EDIT
    public void pressedEditWeatherBySeasonButton() {
        controller.getEditWeatherBySeasonButton().setOnAction(actionEvent -> {
            logger.info("pressed 'editWeatherBySeasonButton'");
            showEditWeatherWindow();
            loadWeathersTableBySeason();
        });
    }
    public void pressedEditWeatherByCommentButton() {
        controller.getEditWeatherByCommentButton().setOnAction(actionEvent -> {
            logger.info("pressed 'editWeatherByCommentButton'");
            showEditWeatherWindow();
            loadWeathersTableByComment();
        });
    }
    public void pressedEditWeatherByPositionButton() {
        controller.getEditWeatherByPositionButton().setOnAction(actionEvent -> {
            logger.info("pressed 'editWeatherByPositionButton'");
            showEditWeatherWindow();
            deleteAllButton.setVisible(false);
            loadWeatherTableByPosition();
        });
    }
    public void pressedEditDayByTemperatureButton() {
        controller.getEditDayByTemperatureButton().setOnAction(actionEvent -> {
            logger.info("pressed 'editDayByTemperatureButton'");
            showEditDayWindow();
            loadDaysTableByTemperature();
        });
    }
    public void pressedEditDayByDateButton() {
        controller.getEditDayByDateButton().setOnAction(actionEvent -> {
            logger.info("pressed 'editDayByDateButton'");
            showEditDayWindow();
            loadDaysTableByDate();
        });
    }
    public void pressedEditDayByCommentButton() {
        controller.getEditDayByCommentButton().setOnAction(actionEvent -> {
            logger.info("pressed 'editDayByCommentButton'");
            showEditDayWindow();
            loadDaysTableByComment();
        });
    }
    public void pressedEditeDayByPositionButton() {
        controller.getEditDayByPositionButton().setOnAction(actionEvent -> {
            logger.info("pressed 'editDayByPositionButton'");
            showEditDayWindow();
            deleteAllButton.setVisible(false);
            loadDaysTableByPosition();
        });
    }
    // ADD
    public void pressedAddWeatherButton() {
        controller.getAddWeatherButton().setOnAction(actionEvent -> {
            logger.info("pressed 'addWeatherButton'");
            showAddWeatherWindow();
        });
    }
    public void pressedAddDayButton() {
        controller.getAddDayButton().setOnAction(actionEvent -> {
            logger.info("pressed 'addDayButton'");
            showAddDayWindow();
        });
    }
}
