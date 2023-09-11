package info.climateControl.day;

import java.time.LocalDate;
import java.util.ResourceBundle;

/**<p><b>methods in Day</b></p>
 * <p>getters:</p>
 * <li>{@link #getTemperature()}</li>
 * <li>{@link #getDate()}</li>
 * <li>{@link #getComment()}</li>
 * <p>setters:</p>
 * <li>{@link #setTemperature(double)}</li>
 * <li>{@link #setDate(LocalDate)}</li>
 * <li>{@link #setComment(String)}</li>
 * <p>other:</p>
 * <li>{@link #toString(ResourceBundle)}</li>
 * <li>{@link #hashCode()}</li> */
public class Day {
    private Double temperature;
    private LocalDate date;
    private String comment;
    /** constructor which sets: temperature, date, comment and resourceBundle - of current day object
     * @param temperature   double which will be set as temperature
     * @param date          LocalDate which will be set as date
     * @param comment       String which will be set as comment */
    public Day(double temperature, LocalDate date, String comment) {
        this.temperature = temperature;
        this.date = date;
        this.comment = comment;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // GETTERS
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /** method which returns temperature of current day object
     * @return Double as temperature */
    public Double getTemperature() {
        return temperature;
    }
    /** method which returns date of current day object
     * @return LocalDate as date */
    public LocalDate getDate() {
        return date;
    }
    /** method which returns comment of current day object
     * @return String as comment */
    public String getComment() {
        return comment;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // SETTERS
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /** method which sets temperature of current day object
     * @param temperature Double which will be set as temperature */
    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }
    /** method which sets date of current day object
     * @param date LocalDate which will be set as date */
    public void setDate(LocalDate date) {
        this.date = date;
    }
    /** method which sets comment of current day object
     * @param comment String which will be set as comment */
    public void setComment(String comment) {
        this.comment = comment;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // OTHER
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public String toString(ResourceBundle resourceBundle) {
        return resourceBundle.getString("dayTemperature") + getTemperature() + "\n" +
                resourceBundle.getString("dayDate") + getComment() + "\n" +
                resourceBundle.getString("dayComment") + "\n";
    }
    @Override
    public int hashCode() {
        return getTemperature().intValue() + getDate().hashCode() + getComment().hashCode();
    }
}
