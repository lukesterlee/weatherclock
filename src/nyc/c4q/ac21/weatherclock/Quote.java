package nyc.c4q.ac21.weatherclock;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.io.BufferedWriter;
import java.util.HashMap;

/**
 * Created by Luke Lee on 4/8/15.
 */
public class Quote {

    static ArrayList<String> lines = FileTools.readLinesFromFile("quote.csv");

    public static void printQuote(Calendar date, AnsiTerminal terminal) throws IOException {
        int x = 40;
        int y = 22;

        String quote = getQuote(date);
        terminal.moveTo(y, x);
        terminal.write("Quote of the day : " + quote);
    }

    public static String getQuote(Calendar date) throws IOException {

        HashMap<String, String> quoteData = new HashMap<String, String>();

        for (String line : lines) {
            String today = line.substring(0,10);
            String quote = line.substring(11);
            quoteData.put(today, quote);
        }

        if (quoteData.containsKey(DateTime.parseDateReverse(date))) {
            return quoteData.get(DateTime.parseDateReverse(date));
        }
        else {
            String quote;

            URL url = HTTP.stringToURL("http://api.theysaidso.com/qod.json");
            String doc = HTTP.get(url);
            JSONObject obj = (JSONObject) JSONValue.parse(doc);

            JSONObject contents = (JSONObject) obj.get("contents");
            quote = (String) contents.get("quote");
            quote += " -" + (String) contents.get("author");

            // Write a quote and date into quote.csv
            String today = DateTime.parseDateReverse(date);
            BufferedWriter writer = new BufferedWriter(new FileWriter("quote.csv"));
            writer.write(today + "," + quote);
            writer.newLine();
            writer.close();

            return quote;
        }




    }
}
