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
import java.sql.*;
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
    private static final Logger logger = LogManager.getLogger(Climate.class);
    private ArrayList<Weather> weathers;
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
    public void writeToTXT(String path) {
        logger.info("writing climate to .txt file " + path);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path))) {
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
        } catch (IOException ioException) {
            logger.info(ioException.getMessage());
        }
    }
    /** method which writes current climate object to .xml file
     * @param path String as path to .xml file in which will be written */
    public void writeToXML(String path) {
        logger.info("writing climate to .xml file " + path);
        XStream xStream = new XStream();
        xStream.alias("climate", Climate.class);
        xStream.alias("weather", Weather.class);
        xStream.alias("day", Day.class);
        try (PrintWriter printWriter = new PrintWriter(new FileWriter(path));) {
            printWriter.println(xStream.toXML(this));
        } catch (IOException ioException) {
            logger.error(ioException.getMessage());
        }
    }
    /** method which writes current climate object to .json file
     * @param path String as path to .json file in which will be written */
    public void writeToJSON(String path) {
        logger.info("writing climate to .json file " + path);
        XStream xStream = new XStream(new JettisonMappedXmlDriver());
        xStream.alias("climate", Climate.class);
        xStream.alias("weather", Weather.class);
        xStream.alias("day", Day.class);
        try (PrintWriter printWriter = new PrintWriter(new FileWriter(path));) {
            printWriter.println(xStream.toXML(this));
        } catch (IOException ioException) {
            logger.error(ioException.getMessage());
        }
    }
    /** method which reads and writes to current climate object from .txt file
     * @param path String as path to .txt file which will be read */
    public void readFromTXT(String path) {
        logger.info("reading to climate from .txt file " + path);
        List<String> list = new ArrayList<>();
        try {
            list = Files.readAllLines(Path.of(path));
        } catch (IOException ioException) {
            logger.error(ioException.getMessage());
        }
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
    public void readFromXML(String path) {
        logger.info("reading to climate from .xml file " + path);
        XStream xStream = new XStream();
        xStream.addPermission(AnyTypePermission.ANY);
        xStream.alias("climate", Climate.class);
        xStream.alias("weather", Weather.class);
        xStream.alias("day", Day.class);
        try {
            Climate tempClimate = (Climate) xStream.fromXML(new FileInputStream(path));
            weathers = tempClimate.weathers;
        } catch (IOException ioException) {
            logger.error(ioException.getMessage());
        }
    }
    /** method which reads and writes to current climate object from .json file
     * @param path String as path to .json file which will be read */
    public void readFromJSON(String path) {
        logger.info("reading to climate from .json file " + path);
        XStream xStream = new XStream(new JettisonMappedXmlDriver());
        xStream.addPermission(AnyTypePermission.ANY);
        xStream.alias("climate", Climate.class);
        xStream.alias("weather", Weather.class);
        xStream.alias("day", Day.class);
        try {
            Climate tempClimate = (Climate) xStream.fromXML(new FileInputStream(path));
            weathers = tempClimate.weathers;
        } catch (IOException ioException) {
            logger.error(ioException.getMessage());
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // DATA BASES
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void createDB(String path) {
        logger.info("creating tables in .db " + path);
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + path);
            statement = connection.createStatement();
            //statement.executeUpdate("DROP TABLE IF EXISTS days");
            statement.executeUpdate(
                    "CREATE TABLE days (" +
                    "ID INTEGER," +
                    "Temperature Double," +
                    "DATE String," +
                    "Comment String," +
                    "FOREIGN KEY(ID) REFERENCES weathers(ID));"
            );
            //statement.executeUpdate("DROP TABLE IF EXISTS weathers");
            statement.executeUpdate(
                    "CREATE TABLE weathers (" +
                    "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "Season String," +
                    "Comment String);"
            );
        } catch (SQLException sqlException) {
            logger.error(sqlException.getMessage());
        } finally {
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException sqlException) {
                logger.error(sqlException.getMessage());
            }
        }
    }
    public void writeToDB(String path) {
        logger.info("writing climate to .db" + path);
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + path);
            DatabaseMetaData metaData = connection.getMetaData();
            if (!metaData.getTables(null, null, "days", null).next() ||
                    !metaData.getTables(null, null, "weathers", null).next()) {
                createDB(path);
            } else {
                preparedStatement = connection.prepareStatement("DROP TABLE IF EXISTS days;");
                preparedStatement = connection.prepareStatement("DROP TABLE IF EXISTS weathers;");
            }
            for (int id = 0; id < weathers.size(); id++) {
                preparedStatement = connection.prepareStatement("INSERT INTO weathers (ID, Season, Comment) VALUES (?, ?, ?);");
                preparedStatement.setInt(1, id);
                preparedStatement.setString(3, weathers.get(id).getSeason());
                preparedStatement.setString(2, weathers.get(id).getComment());
                preparedStatement.executeUpdate();
                for (Day day : weathers.get(id).getDays()) {
                    preparedStatement = connection.prepareStatement("INSERT INTO days (ID, Temperature, Date, Comment) VALUES (?, ?, ?, ?);");
                    preparedStatement.setInt(1, id);
                    preparedStatement.setDouble(2, day.getTemperature());
                    preparedStatement.setString(3, day.getDate().toString());
                    preparedStatement.setString(4, day.getComment());
                    preparedStatement.executeUpdate();
                }
            }
        } catch (SQLException | ClassNotFoundException exception) {
            logger.error(exception.getMessage());
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException sqlException) {
                logger.error(sqlException.getMessage());
            }
        }
    }
    public void readFromDB(String path) {
        logger.info("reading to climate from .db " + path);
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + path);
            DatabaseMetaData metaData = connection.getMetaData();
            if (!metaData.getTables(null, null, "days", null).next() ||
                    !metaData.getTables(null, null, "weathers", null).next()) {
                logger.info("in db no weathers or days tables");
                return;
            }
            weathers.clear();
            preparedStatement = connection.prepareStatement("SELECT MAX(rowID) FROM weathers;");
            for (int weatherIndex = 0, weathersCount = preparedStatement.executeQuery().getInt(1); weatherIndex <= weathersCount; weatherIndex++) {
                preparedStatement = connection.prepareStatement("SELECT ID FROM weathers WHERE rowID = ?;");
                preparedStatement.setInt(1, weatherIndex);
                int id = preparedStatement.executeQuery().getInt(1);
                preparedStatement = connection.prepareStatement("SELECT Season, Comment FROM weathers WHERE ID = ?;");
                preparedStatement.setInt(1, id);
                String season = preparedStatement.executeQuery().getString(1);
                String comment = preparedStatement.executeQuery().getString(2);
                ArrayList<Day> days = new ArrayList<>();
                preparedStatement = connection.prepareStatement("SELECT * FROM days WHERE ID = ?;");
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    days.add(new Day(
                            resultSet.getDouble("Temperature"),
                            LocalDate.parse(resultSet.getString("Date")),
                            resultSet.getString("Comment")
                    ));
                }
                weathers.add(new Weather(
                        season,
                        comment,
                        days
                ));
            }
        } catch (SQLException | ClassNotFoundException exception) {
            logger.error(exception.getMessage());
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException sqlException) {
                logger.error(sqlException.getMessage());
            }
        }
    }
}
