package info.climateControl.climate;

import info.climateControl.weather.Weather;
import info.climateControl.day.Day;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;
import com.thoughtworks.xstream.XStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.time.LocalDate;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ResourceBundle;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

/**<p><b>methods in AbstractClimate</b></p>
 * <p>getters:</p>
 * <li>{@link #getWeathers()}</li>
 * <p>setters:</p>
 * <li>{@link #setWeathers(ArrayList)}</li>
 * <p>other:</p>
 * <li>{@link #toString(ResourceBundle)}</li>
 * <li>{@link #hashCode()}</li>
 * <li>{@link #equals(Object)}</li>
 * <p>files:</p>
 * <li>{@link #writeToTXT(String)}</li>
 * <li>{@link #writeToXML(String)}</li>
 * <li>{@link #writeToJSON(String)}</li>
 * <li>{@link #readFromTXT(String)}</li>
 * <li>{@link #readFromXML(String)}</li>
 * <li>{@link #readFromJSON(String)}</li>
 * <p>data bases:</p>
 * <li>{@link #createDB(String)}</li>
 * <li>{@link #writeToDB(String)}</li> */
public class Climate {
    private ArrayList<Weather> weathers;
    private static final Logger logger = LogManager.getLogger(Climate.class);
    /** constructor which sets weathers of current climate object
     * @param weathers    ArrayList which will be set as weathers */
    public Climate(ArrayList<Weather> weathers) {
        this.weathers = weathers;
    }
    /** constructor which makes weather empty */
    public Climate() {
        weathers = new ArrayList<>();
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // GETTERS
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /** method which returns weathers of current climate object
     * @return ArrayList of weathers */
    public ArrayList<Weather> getWeathers() {
        return weathers;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // SETTERS
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /** method which sets weathers of current climate object
     * @param weathers ArrayList which will be set as weathers */
    public void setWeathers(ArrayList<Weather> weathers) {
        this.weathers = weathers;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // OTHER
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public String toString(ResourceBundle resourceBundle) {
        return resourceBundle.getString("climateWeathers") + getWeathers() + "\n";
    }
    @Override
    public int hashCode() {
        return getWeathers().hashCode();
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // FILES
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /** method which writes current climate object to .txt file
     * @param path String as path to .txt file in which will be written */
    public void writeToTXT(String path) throws IOException {
        logger.info("write climate to .txt file " + path);
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path));
            for (Weather weather : weathers) {
                bufferedWriter.newLine();
                bufferedWriter.write(weather.getSeason() + " " + weather.getComment());
                bufferedWriter.newLine();
                for (Day day : weather.getDays()) {
                    bufferedWriter.write(
                            day.getTemperature() + " " + day.getDate().toString() + " " + day.getComment());
                    bufferedWriter.newLine();
                }
            }
    }
    /** method which writes current climate object to .xml file
     * @param path String as path to .xml file in which will be written */
    public void writeToXML(String path) throws IOException {
        logger.info("write climate to .xml file " + path);
        XStream xStream = new XStream();
        xStream.alias("climate", Climate.class);
        xStream.alias("weather", Weather.class);
        xStream.alias("day", Day.class);
        PrintWriter printWriter = new PrintWriter(new FileWriter(path));
            printWriter.println(xStream.toXML(this));
    }
    /** method which writes current climate object to .json file
     * @param path String as path to .json file in which will be written */
    public void writeToJSON(String path) throws IOException {
        logger.info("write climate to .json file " + path);
        XStream xStream = new XStream(new JettisonMappedXmlDriver());
        xStream.alias("climate", Climate.class);
        xStream.alias("weather", Weather.class);
        xStream.alias("day", Day.class);
        PrintWriter printWriter = new PrintWriter(new FileWriter(path));
            printWriter.println(xStream.toXML(this));
    }
    /** method which reads and writes to current climate object from .txt file
     * @param path String as path to .txt file which will be read */
    public void readFromTXT(String path) throws IOException{
        logger.info("read to climate from .txt file " + path);
            List<String> list = Files.readAllLines(Path.of(path));
            weathers.clear();
            for (int index = 0; index < list.size(); index++) {
                if (list.get(index).isEmpty() && index + 1 < list.size()) {
                    index++;
                    String[] seasonComment = list.get(index).split("\s");
                    ArrayList<Day> days = new ArrayList<>();
                    while ((index + 1) < list.size() && !list.get(index + 1).isEmpty()) {
                        String[] dayParts = list.get(index + 1).split("\s");
                        days.add(new Day(Double.parseDouble(dayParts[0]), LocalDate.parse(dayParts[1]), dayParts[2]));
                        index++;
                    }
                    weathers.add(new Weather(seasonComment[0], seasonComment[1], days));
                }
            }
    }
    /** method which reads and writes to current climate object from .xml file
     * @param path String as path to .xml file which will be read */
    public void readFromXML(String path) throws IOException {
        logger.info("read to climate from .xml file " + path);
        XStream xStream = new XStream();
        xStream.addPermission(AnyTypePermission.ANY);
        xStream.alias("climate", Climate.class);
        xStream.alias("weather", Weather.class);
        xStream.alias("day", Day.class);
            Climate tempClimate = (Climate) xStream.fromXML(new FileInputStream(path));
            weathers = tempClimate.weathers;
    }
    /** method which reads and writes to current climate object from .json file
     * @param path String as path to .json file which will be read */
    public void readFromJSON(String path) throws IOException {
        logger.info("read to climate from .json file " + path);
        XStream xStream = new XStream(new JettisonMappedXmlDriver());
        xStream.addPermission(AnyTypePermission.ANY);
        xStream.alias("climate", Climate.class);
        xStream.alias("weather", Weather.class);
        xStream.alias("day", Day.class);
            Climate tempClimate = (Climate) xStream.fromXML(new FileInputStream(path));
            weathers = tempClimate.weathers;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // DATA BASES
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void createDB(String path) throws IOException {

    }
    public void writeToDB(String path) throws IOException {

    }
    public void readFromDB(String path) throws IOException {

    }
}
