package View;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class InputOutput {

    Scanner sc = new Scanner(System.in);
    Printer printer = new Printer();

    public int getIntInput() {
        return sc.nextInt();
    }

    public String getStringInput() {
        return sc.next();
    }

    public int[] getInputCoordinatesFromHuman(String message) {
        printer.printMessage(message);
        //TODO implement validation of input
        //for example A1, D10
        String input = sc.next();
        Map<String, Integer> rowMap = Stream.of(new Object[][]{
                {"A", 0},
                {"B", 1},
                {"C", 2},
                {"D", 3},
                {"E", 4},
                {"F", 5},
                {"G", 6},
                {"H", 7},
                {"I", 8},
                {"J", 9},
        }).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]));

        int[] coordinates = new int[2];
        coordinates[0] = rowMap.get( String.valueOf(input.charAt(0)) );
        coordinates[1] = Integer.parseInt(input.substring(1)) -1 ;

        return coordinates;
    }

    public int[] getCoordinatesFromComputer() {
        Random ran = new Random();
        int[] result = new int[2];
        result[0] = ran.nextInt(10);
        result[1] = ran.nextInt(10);
        return result;
    }
}
