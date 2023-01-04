package mov.aoc.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputDataReader {

    private int year,day;
    private String fileName;

    public InputDataReader(int year, int day) {
        this(year,day, "input.txt");
    }

    public InputDataReader(int year, int day, String fileName) {
        this.year=year;
        this.day=day;
        this.fileName = fileName;
    }

    private List<String> getStringLines() throws URISyntaxException, FileNotFoundException {
        List<String> strLines = new ArrayList<>();

        URL dataResource = InputDataReader.class.getClassLoader().getResource(String.format("y%d/d%d/%s", year, day, fileName));
        File dataFile = new File(dataResource.toURI());
        Scanner fileScanner = new Scanner(dataFile);

        while (fileScanner.hasNext())
            strLines.add(fileScanner.nextLine());

        fileScanner.close();

        return strLines;
    }

    public List<String> asStringLines() throws FileNotFoundException, URISyntaxException {
        return getStringLines();
    }

    public List<String[]> asTuples(String[] separators) throws FileNotFoundException, URISyntaxException {
        List<String[]> tuples = new ArrayList<>();

        for (String line : this.asStringLines()) {
            tuples.add(line.split(separators[0]));
        }
        
        for (int separatorIndex = 1; separatorIndex<separators.length; separatorIndex++){
            List<String[]> newTuples = new ArrayList<>();

            for (String[] tuple : tuples) {
                List<String> fragments = new ArrayList<>();

                for (String element : tuple) {
                    for (String fragment : element.split(separators[separatorIndex])){
                        if (fragment.strip() != "") {
                            fragments.add(fragment);
                        }
                    }
                }

                newTuples.add(fragments.toArray(new String[]{}));
            }

            tuples = newTuples;
        }

        return tuples;
    }

}
