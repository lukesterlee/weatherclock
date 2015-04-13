package nyc.c4q.ac21.weatherclock;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Luke Lee on 4/9/15.
 */
public class PrintNumbers {


    public static void printClock(AnsiTerminal terminal, String time) {

        int x = 2;
        int y = 2;

        ArrayList<String> hh1 = getNumber(time.charAt(0));
        for (int j = 0; j < hh1.size(); j++) {
            terminal.moveTo(y++, x);
            terminal.write(hh1.get(j));
        }

        ArrayList<String> hh2 = getNumber(time.charAt(1));
        x += 10;
        y = 2;
        for (int j = 0; j < hh2.size(); j++) {
            terminal.moveTo(y++, x);
            terminal.write(hh2.get(j));
        }

        ArrayList<String> mm1 = getNumber(time.charAt(3));
        x += 16;
        y = 2;
        for (int j = 0; j < mm1.size(); j++) {
            terminal.moveTo(y++, x);
            terminal.write(mm1.get(j));
        }

        ArrayList<String> mm2 = getNumber(time.charAt(4));
        x += 10;
        y = 2;
        for (int j = 0; j < mm2.size(); j++) {
            terminal.moveTo(y++, x);
            terminal.write(mm2.get(j));
        }

        ArrayList<String> ss1 = getNumber(time.charAt(6));
        ArrayList<String> ss2 = getNumber(time.charAt(7));

    }

    public static void printDot(AnsiTerminal terminal, int x, int y) {

        ArrayList<String> dot = new ArrayList<String>();
        dot.add("■■\\ \n");
        dot.add("\\__|\n");
        dot.add("    \n");
        dot.add("■■\\ \n");
        dot.add("\\__|");

        for (int j = 0; j < dot.size(); j++) {
            terminal.setTextColor(AnsiTerminal.Color.WHITE, false);
            terminal.moveTo(y++, x);
            terminal.write(dot.get(j));
        }
    }

    public static void printDotOpposite(AnsiTerminal terminal, int x, int y) {

        ArrayList<String> dot = new ArrayList<String>();
        dot.add("■■\\ \n");
        dot.add("\\__|\n");
        dot.add("    \n");
        dot.add("■■\\ \n");
        dot.add("\\__|");

        for (int j = 0; j < dot.size(); j++) {
            terminal.setTextColor(AnsiTerminal.Color.BLACK, false);
            terminal.moveTo(y++, x);
            terminal.write(dot.get(j));
        }
    }



    public static ArrayList<String> getNumber(char number) {

        HashMap<Character, ArrayList<String>> numbers = new HashMap<Character, ArrayList<String>>();


        ArrayList<String> one = new ArrayList<String>();
        one.add("   ■■\\    \n");
        one.add(" ■■■■ |   \n");
        one.add(" \\_■■ |   \n");
        one.add("   ■■ |   \n");
        one.add("   ■■ |   \n");
        one.add("   ■■ |   \n");
        one.add(" ■■■■■■\\  \n");
        one.add(" \\______| ");

        ArrayList<String> two = new ArrayList<String>();
        two.add(" ■■■■■■\\  \n");
        two.add("■■  __■■\\ \n");
        two.add("\\__/  ■■ |\n");
        two.add(" ■■■■■■  |\n");
        two.add("■■  ____/ \n");
        two.add("■■ |      \n");
        two.add("■■■■■■■■\\ \n");
        two.add("\\________|");


        ArrayList<String> three = new ArrayList<String>();
        three.add(" ■■■■■■\\  \n");
        three.add("■■ ___■■\\ \n");
        three.add("\\_/   ■■ |\n");
        three.add("  ■■■■■ / \n");
        three.add("  \\___■■\\ \n");
        three.add("■■\\   ■■ |\n");
        three.add("\\■■■■■■  |\n");
        three.add(" \\______/ ");


        ArrayList<String> four = new ArrayList<String>();
        four.add("■■\\   ■■\\ \n");
        four.add("■■ |  ■■ |\n");
        four.add("■■ |  ■■ |\n");
        four.add("■■■■■■■■ |\n");
        four.add("\\_____■■ |\n");
        four.add("      ■■ |\n");
        four.add("      ■■ |\n");
        four.add("      \\__|");


        ArrayList<String> five = new ArrayList<String>();
        five.add("■■■■■■■\\  \n");
        five.add("■■  ____| \n");
        five.add("■■ |      \n");
        five.add("■■■■■■■\\  \n");
        five.add("\\_____■■\\ \n");
        five.add("■■\\   ■■ |\n");
        five.add("\\■■■■■■  |\n");
        five.add(" \\______/ ");


        ArrayList<String> six = new ArrayList<String>();
        six.add(" ■■■■■■\\  \n");
        six.add("■■  __■■\\ \n");
        six.add("■■ /  \\__|\n");
        six.add("■■■■■■■\\  \n");
        six.add("■■  __■■\\ \n");
        six.add("■■ /  ■■ |\n");
        six.add(" ■■■■■■  |\n");
        six.add(" \\______/ ");


        ArrayList<String> seven = new ArrayList<String>();
        seven.add("■■■■■■■■\\ \n");
        seven.add("\\____■■  |\n");
        seven.add("    ■■  / \n");
        seven.add("   ■■  /  \n");
        seven.add("  ■■  /   \n");
        seven.add(" ■■  /    \n");
        seven.add("■■  /     \n");
        seven.add("\\__/      ");


        ArrayList<String> eight = new ArrayList<String>();
        eight.add(" ■■■■■■\\  \n");
        eight.add("■■  __■■\\ \n");
        eight.add("■■ /  ■■ |\n");
        eight.add(" ■■■■■■  |\n");
        eight.add("■■  __■■< \n");
        eight.add("■■ /  ■■ |\n");
        eight.add("\\■■■■■■  |\n");
        eight.add(" \\______/ ");


        ArrayList<String> nine = new ArrayList<String>();
        nine.add(" ■■■■■■\\  \n");
        nine.add("■■  __■■\\ \n");
        nine.add("■■ /  ■■ |\n");
        nine.add("\\■■■■■■■ |\n");
        nine.add(" \\____■■ |\n");
        nine.add("■■\\   ■■ |\n");
        nine.add("\\■■■■■■  |\n");
        nine.add(" \\______/ ");


        ArrayList<String> zero = new ArrayList<String>();
        zero.add(" ■■■■■■\\  \n");
        zero.add("■■  __■■\\ \n");
        zero.add("■■    ■■ |\n");
        zero.add("■■    ■■ |\n");
        zero.add("■■    ■■ |\n");
        zero.add("■■ |  ■■ |\n");
        zero.add("\\■■■■■■  /\n");
        zero.add(" \\______/ ");

        numbers.put('1', one);
        numbers.put('2', two);
        numbers.put('3', three);
        numbers.put('4', four);
        numbers.put('5', five);
        numbers.put('6', six);
        numbers.put('7', seven);
        numbers.put('8', eight);
        numbers.put('9', nine);
        numbers.put('0', zero);

        return numbers.get(number);
    }


}
