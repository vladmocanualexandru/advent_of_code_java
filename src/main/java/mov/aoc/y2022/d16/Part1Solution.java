package mov.aoc.y2022.d16;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mov.aoc.templates.AbstractSolution;

public class Part1Solution extends AbstractSolution{

    // private List<Valve> valves = new ArrayList<>();
    private Map<String,Valve> valveDirectory = new HashMap<>();

    public Part1Solution() {
        super(2022, 16);
    }

    @Override
    public void init() throws FileNotFoundException, URISyntaxException {
        List<String[]> tuples = this.inputDataReader.asTuples(new String[]{"Valve ", " has flow rate=", "; tunnels lead to valves ", "; tunnel leads to valve ", ", "});

        for (String[] tuple : tuples) {
            Valve newValve = new Valve(Integer.parseInt(tuple[1]));
            valveDirectory.put(tuple[0], newValve);
        }

        for (String[] tuple : tuples) {
            Valve currentValve = valveDirectory.get(tuple[0]);

            for (int valveLabelIndex = 2; valveLabelIndex<tuple.length; valveLabelIndex++) {
                currentValve.getConnections().add(valveDirectory.get(tuple[valveLabelIndex]));
            }
        }
    }

    @Override
    public int run(String[] args) throws URISyntaxException, FileNotFoundException {
        init();
        return 0;
    }

    public static void main(String[] args) throws FileNotFoundException, URISyntaxException {
        Part1Solution p1Solution = new Part1Solution();

        p1Solution.run(args);
    }
    
}
