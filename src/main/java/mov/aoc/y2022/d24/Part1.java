package mov.aoc.y2022.d24;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Part1 {

    private static List<Storm> buildStorms(List<String> strLines) throws URISyntaxException, FileNotFoundException {
        List<Storm> storms = new ArrayList<>();

        int y = 0;
        for (String line : strLines) {
            for (int x = 0; x < line.length(); x++) {
                switch (line.charAt(x)) {
                    case '>': {
                        storms.add(new Storm(y, x, Direction.E));
                        break;
                    }
                    case 'v': {
                        storms.add(new Storm(y, x, Direction.S));
                        break;
                    }
                    case '<': {
                        storms.add(new Storm(y, x, Direction.W));
                        break;
                    }
                    case '^': {
                        storms.add(new Storm(y, x, Direction.N));
                        break;
                    }
                }
            }

            y++;
        }

        return storms;
    }

    public static void main(String[] args) throws URISyntaxException, FileNotFoundException  {
        // read input file
        List<String> strLines = new ArrayList<>();

        URL dataResource = Part1.class.getClassLoader().getResource("y2022/d24/input.txt");
        File dataFile = new File(dataResource.toURI());
        Scanner fileScanner = new Scanner(dataFile);

        while (fileScanner.hasNext())
            strLines.add(fileScanner.nextLine());

        fileScanner.close();

        // build storms
        List<Storm> storms = buildStorms(strLines);

        

        for (Storm s : storms) {
            System.out.println(s);
        }

    }
}
