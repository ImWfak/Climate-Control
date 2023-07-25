import info.climateControl.climate.Climate;
import info.climateControl.weather.Weather;
import info.climateControl.day.Day;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import java.time.LocalDate;
import java.util.ArrayList;

public class TestClimate {
    private static final Logger logger = LogManager.getLogger(TestClimate.class);
    private final Climate climate;
    {
        ArrayList<Day> daysForFirstWeather = new ArrayList<>();
        daysForFirstWeather.add(new Day(10.5, LocalDate.now(), "FirstDayComment"));
        daysForFirstWeather.add(new Day(11.5, LocalDate.now(), "FirstDayComment"));
        daysForFirstWeather.add(new Day(12.5, LocalDate.now(), "FirstDayComment"));

        ArrayList<Day> daysForSecondWeather = new ArrayList<>();
        daysForSecondWeather.add(new Day(-2.5, LocalDate.now(), "SecondDayComment"));
        daysForSecondWeather.add(new Day(-12.5, LocalDate.now(), "SecondDayComment"));
        daysForSecondWeather.add(new Day(-22.5, LocalDate.now(), "SecondDayComment"));

        ArrayList<Weather> weathers = new ArrayList<>();
        weathers.add(new Weather("Season", "comment", daysForFirstWeather));
        weathers.add(new Weather("Season", "comment", daysForSecondWeather));
        climate = new Climate(weathers);
    }
    @Test
    public void testWriteAndReadFromTXT() {
        String path = "out/test.txt";
        climate.writeToTXT(path);
        Climate tempClimate = new Climate();
        tempClimate.readFromTXT(path);
        Assert.assertEquals(climate.toString(), tempClimate.toString());
    }
    @Test
    public void testWriteAndReadFromXML() {
        String path = "out/test.xml";
        climate.writeToXML(path);
        Climate tempClimate = new Climate();
        tempClimate.readFromXML(path);
        Assert.assertEquals(climate.toString(), tempClimate.toString());
    }
    @Test
    public void testWriteAndReadFromJSON() {
        String path = "out/test.json";
        climate.writeToJSON(path);
        Climate tempClimate = new Climate();
        tempClimate.readFromJSON(path);
        Assert.assertEquals(climate.toString(), tempClimate.toString());
    }
}
