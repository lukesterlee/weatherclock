package nyc.c4q.ac21.weatherclock;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by Luke Lee on 4/7/15.
 *
 *  4/13/15 : In progress : creating Weather class, change to modular coding
 */
public class Weather {


    private URL url;
    private String jsonFile;

    private Calendar sunrise;
    private Calendar sunset;

    private float temperatureInKelvin;
    private float pressureInhPa;
    private int humidity;
    private int weatherCode;
    private String weatherDescription;

    private boolean isCelcius;


    public Weather() {

    }

    public Weather(URL url) {

        this.url = url;

    }




    public static String getWeather(URL url) {
        String doc = HTTP.get(url);
        JSONObject obj = (JSONObject) JSONValue.parse(doc);
        JSONArray weatherArray = (JSONArray) obj.get("weather");
        JSONObject weather = (JSONObject) weatherArray.get(0);
        String description = (String) weather.get("description");
        return description;
    }

    public static Integer getWeatherCode(URL url) {
        String doc = HTTP.get(url);
        JSONObject obj = (JSONObject) JSONValue.parse(doc);
        JSONArray weatherArray = (JSONArray) obj.get("weather");
        JSONObject weather = (JSONObject) weatherArray.get(0);
        long weatherId = (Long) weather.get("id");
        Integer weatherCode = (int) (long) weatherId;
        return weatherCode;
    }


    public static String getTemperature(URL url, boolean isCelcius) {

        String doc = HTTP.get(url);
        JSONObject obj = (JSONObject) JSONValue.parse(doc);
        JSONObject main = (JSONObject) obj.get("main");
        DecimalFormat df = new DecimalFormat("#.0");
        Double temp = (Double) main.get("temp");
        temp -= 273;

        if (isCelcius)
            return df.format(temp) + "°C";
        else {
            temp = temp*(9/5) + 32;
            return df.format(temp) + "°F";
        }


    }

    public static String getPressure(URL url) {

        String doc = HTTP.get(url);
        JSONObject obj = (JSONObject) JSONValue.parse(doc);
        JSONObject main = (JSONObject) obj.get("main");
        DecimalFormat df = new DecimalFormat("#.0");

        Double pressure = (Double) main.get("pressure");
        pressure = pressure*0.0296133971008484;
        return df.format(pressure);
    }

    public static Integer getHumidity(URL url) {

        String doc = HTTP.get(url);
        JSONObject obj = (JSONObject) JSONValue.parse(doc);
        JSONObject main = (JSONObject) obj.get("main");
        DecimalFormat df = new DecimalFormat("#.0");

        long humidity = (Long) main.get("humidity");
        Integer hum = (int) (long) humidity;
        return hum;
    }

    public static void printTemperature(AnsiTerminal terminal, URL url, boolean isCelcius) {

        int x = 21;
        int y = 13;

        // Write temperature.
        String temp = Weather.getTemperature(url, isCelcius);
        terminal.moveTo(15, x);
        terminal.write("Temperature : " + temp);


    }

    public static void printPressure(AnsiTerminal terminal, URL url) {

        int x = 21;
        int y = 13;

        // Write pressure.
        String pressure = Weather.getPressure(url);
        terminal.moveTo(16, x);
        terminal.write("Pressure : " + pressure + " inHg");
    }

    public static void printHumidity(AnsiTerminal terminal, URL url) {

        int x = 21;
        int y = 13;

        // Write humidity.
        Integer humidity = Weather.getHumidity(url);
        terminal.moveTo(17, x);
        terminal.write("Humidity : " + humidity + "%");
    }

    public static void printWeatherPicture(AnsiTerminal terminal, URL url) {
        ArrayList<String> weapic = Weather.weatherPicture(Weather.getWeatherCode(url));
        int x = 5;
        int y = 13;
        for (int i = 0; i < weapic.size(); i++) {
            terminal.moveTo(y++, x);
            terminal.write(weapic.get(i));
        }

        String weather = getWeather(url);
        terminal.moveTo(y++, x);
        terminal.write(weather);
    }

    public static ArrayList<String> weatherPicture(int weatherCode) {

        ArrayList<ArrayList<Integer>> codes = new ArrayList<ArrayList<Integer>>();


        codes.add(new ArrayList<Integer>());
        codes.get(0).add(800);

        codes.add(new ArrayList<Integer>());
        codes.get(1).add(801);
        codes.get(1).add(802);
        codes.get(1).add(803);
        codes.get(1).add(804);

        codes.add(new ArrayList<Integer>());
        codes.get(2).add(300);
        codes.get(2).add(301);
        codes.get(2).add(302);
        codes.get(2).add(310);
        codes.get(2).add(311);
        codes.get(2).add(312);
        codes.get(2).add(313);
        codes.get(2).add(314);
        codes.get(2).add(321);
        codes.get(2).add(500);
        codes.get(2).add(501);
        codes.get(2).add(502);
        codes.get(2).add(503);
        codes.get(2).add(504);
        codes.get(2).add(511);
        codes.get(2).add(520);
        codes.get(2).add(521);
        codes.get(2).add(522);
        codes.get(2).add(531);

        codes.add(new ArrayList<Integer>());
        codes.get(3).add(600);
        codes.get(3).add(601);
        codes.get(3).add(602);
        codes.get(3).add(611);
        codes.get(3).add(612);
        codes.get(3).add(615);
        codes.get(3).add(616);
        codes.get(3).add(620);
        codes.get(3).add(621);
        codes.get(3).add(622);

        codes.add(new ArrayList<Integer>());
        codes.get(4).add(200);
        codes.get(4).add(201);
        codes.get(4).add(202);
        codes.get(4).add(210);
        codes.get(4).add(211);
        codes.get(4).add(212);
        codes.get(4).add(221);
        codes.get(4).add(230);
        codes.get(4).add(231);
        codes.get(4).add(232);

        codes.add(new ArrayList<Integer>());
        codes.get(5).add(701);
        codes.get(5).add(711);
        codes.get(5).add(721);
        codes.get(5).add(731);
        codes.get(5).add(741);
        codes.get(5).add(751);
        codes.get(5).add(761);
        codes.get(5).add(762);
        codes.get(5).add(771);
        codes.get(5).add(781);

        ArrayList<String> clear = new ArrayList<String>();
        clear.add("┌───────────┐\n");
        clear.add("     __      \n");
        clear.add("    ( ・∋    \n");
        clear.add("    /( )ヽ   \n");
        clear.add("     ´｀     \n");
        clear.add("    Happy    \n");
        clear.add("└───────────┘");


        ArrayList<String> cloud = new ArrayList<String>();
        cloud.add("┌───────────┐\n");
        cloud.add(" .:::::）    \n");
        cloud.add("      '⌒ヽ   \n");
        cloud.add(" ::（  ::）  \n");
        cloud.add(" ....::::）  \n");
        cloud.add(" _ _  ノ     \n");
        cloud.add("└───────────┘");


        ArrayList<String> rain = new ArrayList<String>();
        rain.add("┌───────────┐ \n");
        rain.add("  / ,__/_,   \n");
        rain.add("  ／ ／|＼＼  \n");
        rain.add("  ^,^^|^/^^, \n");
        rain.add("   /  |  /   \n");
        rain.add("  ,  /J    / \n");
        rain.add("└───────────┘");


        ArrayList<String> thunder = new ArrayList<String>();
        thunder.add("┌───────────┐\n");
        thunder.add(" .      ノ.ノ \n");
        thunder.add(" （,.....,ノ  \n");
        thunder.add("   ／  /     \n");
        thunder.add("   ＼   ＼    \n");
        thunder.add("   ／   /    \n");
        thunder.add("└───────────┘");


        ArrayList<String> snow = new ArrayList<String>();
        snow.add("┌───────────┐\n");
        snow.add("   ゜　　.　  \n");
        snow.add("   o   ゜ o  \n");
        snow.add("    ○   .    \n");
        snow.add(" ゜ 　 ○  .   \n");
        snow.add("   ⌒⌒⌒  .     \n");
        snow.add("└───────────┘");


        ArrayList<String> mist = new ArrayList<String>();
        mist.add("┌───────────┐\n");
        mist.add("  彡  彡  彡  \n");
        mist.add("        彡   \n");
        mist.add("   ・ ε ・   \n");
        mist.add("            \n");
        mist.add("   彡    彡  \n");
        mist.add("└───────────┘");



        int index = 6;

        for (int i = 0; i < 6; i++) {
            if (codes.get(i).contains(weatherCode)) {
                index = i;
            }
        }


        switch (index) {
            case 0 :
                return clear;
            case 1 :
                return cloud;
            case 2 :
                return rain;
            case 3 :
                return snow;
            case 4 :
                return thunder;
            case 5 :
                return mist;
        }

        return null;

    }
}
