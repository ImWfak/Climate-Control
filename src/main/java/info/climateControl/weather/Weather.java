package info.climateControl.weather;

import info.climateControl.day.Day;
import java.util.ResourceBundle;
import java.util.ArrayList;

/**<p><b>methods in Weather</b></p>
 * <p>getters:</p>
 * <li>{@link #getSeason()}</li>
 * <li>{@link #getComment()}</li>
 * <li>{@link #getDays()}</li>
 * <p>setters:</p>
 * <li>{@link #setSeason(String)}</li>
 * <li>{@link #setComment(String)}</li>
 * <li>{@link #setDays(ArrayList)}</li>
 * <p>other:</p>
 * <li>{@link #toString(ResourceBundle)}</li>
 * <li>{@link #hashCode()}</li> */
public class Weather {
    private String season;
    private String comment;
    private ArrayList<Day> days;
    /** constructor which sets: season, comment, days and resourceBundle - of current weather object
     * @param season    String which will be set as season
     * @param comment   String which will be set as comment
     * @param days      ArrayList which will be set as days */
    public Weather(String season, String comment, ArrayList<Day> days) {
        this.season = season;
        this.comment = comment;
        this.days = days;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // GETTERS
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /** method which returns season of current weather object
     * @return String as season */
    public String getSeason() {
        return season;
    }
    /** method which returns comment of current weather object
     * @return String as comment */
    public String getComment() {
        return comment;
    }
    /** method which returns days of current weather object
     * @return ArraysList as days */
    public ArrayList<Day> getDays() {
        return days;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // SETTERS
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /** method which sets season of current weather object
     * @param season String which will be set as season */
    public void setSeason(String season) {
        this.season = season;
    }
    /** method which sets comment of current weather object
     * @param comment String which will be set as comment */
    public void setComment(String comment) {
        this.comment = comment;
    }
    /** method which sets days of current weather object
     * @param days ArrayList which will be set as days */
    public void setDays(ArrayList<Day> days) {
        this.days = days;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // OTHER
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public String toString(ResourceBundle resourceBundle) {
        return resourceBundle.getString("weatherSeason") + getSeason() + "\n" +
                resourceBundle.getString("weatherComment") + getComment() + "\n" +
                resourceBundle.getString("weatherDays") + getDays() + "\n";
    }
    @Override
    public int hashCode() {
        return getSeason().hashCode() + getComment().hashCode() + getDays().hashCode();
    }
}
