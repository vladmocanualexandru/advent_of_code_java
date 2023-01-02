package mov.aoc.templates;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;

import mov.aoc.utils.InputDataReader;

public abstract class AbstractSolution {
    protected InputDataReader inputDataReader;

    public AbstractSolution(int year, int day){
        inputDataReader = new InputDataReader(year, day);
    }

    public abstract void init() throws FileNotFoundException, URISyntaxException;
    public abstract int run(String[] args) throws URISyntaxException, FileNotFoundException;
}
