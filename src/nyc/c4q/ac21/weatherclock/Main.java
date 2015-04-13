package nyc.c4q.ac21.weatherclock;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import java.util.Timer;

/*
 * Access Code 2.1
 * Team Dream : Jorge, Ray, Vanice, Luke
 *
 * Our Product Quality
 * 1. Exception handling : in progress
 * 2. Offline handling : in progress
 * 3. Data Use Efficiency : Quote data fetch, in progress
 *
 * Basic Requirement
 *
 *
 * Additional Features
 * 1. 5 Days Forecast
 * 2. Zip code selection
 * 3. Celcius Farenheit option
 * 4. 12/24 hour format
 */

public class Main {

    /**
     * SAMPLE CODE: Returns sunset time for the current day.
     */
    public static Calendar getSunset(String address) {
        URL url = HTTP.stringToURL(address);
        String doc = HTTP.get(url);
        JSONObject obj = (JSONObject) JSONValue.parse(doc);

        JSONObject sys = (JSONObject) obj.get("sys");
        if (sys == null)
            return null;
        Long sunsetTimestamp = (Long) sys.get("sunset");
        if (sunsetTimestamp == null)
            return null;
        return DateTime.fromTimestamp(sunsetTimestamp);
    }

    public static Calendar getSunrise(String address) {


        URL url = HTTP.stringToURL(address);
        String doc = HTTP.get(url);
        JSONObject obj = (JSONObject) JSONValue.parse(doc);

        JSONObject sys = (JSONObject) obj.get("sys");
        if (sys == null)
            return null;
        Long sunriseTimestamp = (Long) sys.get("sunrise");
        if (sunriseTimestamp == null)
            return null;
        return DateTime.fromTimestamp(sunriseTimestamp);
    }

    public static String greeting(Calendar date) {
        String greeting;

        int hour = date.get(Calendar.HOUR_OF_DAY);

        if (hour >= 6 && hour < 12) {
            greeting = "Good morning";
        } else if (hour >= 12 && hour <= 18) {
            greeting = "Good afternoon";
        } else if (hour >= 19 && hour <= 21) {
            greeting = "Good evening";
        } else
            greeting = "Hello";

        return greeting;
    }

    public static String getToday(Calendar date) {
        int year = date.get(Calendar.YEAR);
        int month = date.get(Calendar.MONTH);
        int day = date.get(Calendar.DAY_OF_MONTH);

        String today;
        String dayOfWeek = DateTime.getDayOfWeekNames().get(date.get(Calendar.DAY_OF_WEEK));
        String dayOfMonth = DateTime.getMonthNames().get(month);

        today = dayOfWeek + ", " + dayOfMonth + " " + day + " " + year;
        return today;
    }

    public static boolean is24(String hourFormat) {
        return hourFormat.equals("24");
    }

    public static boolean isCelcius(String tempFormat) {
        return tempFormat.equalsIgnoreCase("C");
    }

    public static String displayTimeSeconds(Calendar date, boolean is24) {
        String time;
        if (is24) {
            // Write the time, including seconds, in white.
            time = DateTime.formatTime24(date, true);

        } else {
            // Write the time, including seconds, in white.
            time = DateTime.formatTime(date, true);
            if (date.get(Calendar.HOUR_OF_DAY) >= 12)
                time += " PM";
            else
                time += " AM";
        }
        return time;
    }

    public static String displayTime(Calendar date, boolean is24) {
        String time;
        if (is24) {
            // Write the time, including seconds, in white.
            time = DateTime.formatTime24(date, false);

        } else {
            // Write the time, including seconds, in white.
            time = DateTime.formatTime(date, false);
            if (date.get(Calendar.HOUR_OF_DAY) >= 12)
                time += " PM";
            else
                time += " AM";
        }
        return time;
    }

    public static void setColor(AnsiTerminal terminal, Calendar sunrise, Calendar sunset, Calendar time) {

        int current = time.get(Calendar.HOUR_OF_DAY);
        int rise = sunrise.get(Calendar.HOUR_OF_DAY);
        int set = sunset.get(Calendar.HOUR_OF_DAY);

        // current >= rise && current <= set
        if (false) {
            terminal.setBackgroundColor(AnsiTerminal.Color.WHITE);
            terminal.setTextColor(AnsiTerminal.Color.BLACK, false);
        } else {
            terminal.setBackgroundColor(AnsiTerminal.Color.BLACK);
            terminal.setTextColor(AnsiTerminal.Color.WHITE, false);
        }

    }

    /**
     * SAMPLE CODE: Displays a very primitive clock.
     */
    public static void main(String[] args) throws IOException {

        String name, zipCode, address, hourFormat, tempFormat;
        int alarmHour = 0;
        int alarmMinute = 0;

        Scanner input = new Scanner(System.in);

        // Get name from the user.
        System.out.print("Enter your name : ");
        name = input.nextLine();

        // Get ZIP code from the user.
        System.out.print("Enter your ZIP code : ");
        zipCode = input.nextLine();
        address = "http://api.openweathermap.org/data/2.5/weather?zip=" + zipCode + ",us";
        URL url = HTTP.stringToURL(address);


        // Get 12-24 Hour format from the user.
        System.out.print("Choose hour format (12/24) : ");
        hourFormat = input.nextLine();
        boolean is24 = is24(hourFormat);

        // Get Farenheit/Celcius format from the user.
        System.out.print("Choose temperature format (C/F) : ");
        tempFormat = input.nextLine();
        boolean isCelcius = isCelcius(tempFormat);

        // Get the alarm time.
//        System.out.print("Set the alarm clock? (Y/N) : ");
//        boolean isAlarm = input.nextLine().equalsIgnoreCase("Y");
//        if (isAlarm) {
//            System.out.print("Set the hour in 24-hour format : ");
//            alarmHour = input.nextInt();
//            System.out.print("Set the minute : ");
//            alarmMinute = input.nextInt();
//        }

        // Find out the size of the terminal currently.
        final int numCols = TerminalSize.getNumColumns();
        final int numRows = TerminalSize.getNumLines();

        // Create the terminal.
        final AnsiTerminal terminal = new AnsiTerminal();

        // When the program shuts down, reset the terminal to its original state.
        // This code makes sure the terminal is reset even if you kill your
        // program by pressing Control-C.
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                terminal.showCursor();
                terminal.reset();
                terminal.scroll(1);
                terminal.moveTo(numRows, 0);
            }
        });

        // Get sunset time for the current day.
        Calendar sunset = getSunset(address);

        // Get sunset time for the current day.
        Calendar sunrise = getSunrise(address);

        // Get starting time.
        Calendar startingTime = Calendar.getInstance();

        // Set background color and text color
        setColor(terminal, sunrise, sunset, startingTime);

        // Clear the screen to black.
        terminal.clear();

        // Don't show the cursor.
        terminal.hideCursor();


        // Write calendar.
        CalendarPrinter.printCalendar(startingTime, terminal);

        // Write the quote of the day.
        Quote.printQuote(startingTime, terminal);


        // Write DST.
        DST.printDST(terminal, startingTime);


        // this while loop updates every 3 hour.
        while (true) {

            // Get the current date and time.
            Calendar cal = Calendar.getInstance();

            // weather picture test
            Weather.printWeatherPicture(terminal, url);

            Weather.printTemperature(terminal, url, isCelcius);

            Weather.printPressure(terminal, url);

            Weather.printHumidity(terminal, url);


            // Write sunrise time in dark yellow.
            String sunriseTime = displayTime(sunrise, is24);
            terminal.moveTo(14, 54);
            terminal.write("Sunrise at " + sunriseTime);

            // Write sunset time in dark yellow.
            String sunsetTime = displayTime(sunset, is24);
            terminal.moveTo(15, 54);
            terminal.write("Sunset at " + sunsetTime);


            // Write the day of the week in green on a blue background.
            String today = getToday(cal);
            terminal.moveTo(17, 54);
            terminal.write(today);

            // Write holiday.
            String holiday = Holidays.getNationalHoliday(cal);
            terminal.moveTo(18, 54);
            terminal.write(holiday);

            // Write greeting.
            terminal.moveTo(11, 15);
            terminal.write(greeting(cal) + ", " + name);


            // this while loop updates every second.
            for(int i  = 1; i <= 3600*3; i++) {


                // Get the current date and time.
                Calendar cal2 = Calendar.getInstance();

//                int hour = cal2.get(Calendar.HOUR_OF_DAY);
//                int minute = cal2.get(Calendar.MINUTE);
//
//                terminal.moveTo(11, 15);
//                terminal.write(hour + " " + minute);

//                if (hour == alarmHour && minute == alarmMinute) {
//                    terminal.moveTo(11, 3);
//                    terminal.write("Alarm is on");
//                }


//                terminal.moveTo(12, 3);
//                terminal.write("Alarm : " + alarmHour + ":" + alarmMinute);

                String time = displayTimeSeconds(cal2, is24);

                PrintNumbers.printClock(terminal, time);

                PrintNumbers.printDot(terminal, 23, 4);
                DateTime.pause(0.5);
                PrintNumbers.printDotOpposite(terminal, 23, 4);

                // Pause for one second, and do it again.
                DateTime.pause(0.5);
                terminal.setTextColor(AnsiTerminal.Color.WHITE, false);
            }


        }


    }
}
