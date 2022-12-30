package mov.aoc.y2022.d24;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RunPart1 {

    private YXTuple[] NEIGHBOR_POSITIONS = new YXTuple[]{new YXTuple(-1, 0),new YXTuple(0, 0),new YXTuple(1, 0),new YXTuple(0, -1),new YXTuple(0, 1)};
    
    private int mapHeight;
    private int mapWidth;
    private List<Storm> storms;
    private List<StormConfig> stormConfigs;


    private List<Storm> buildStorms(List<String> strLines) throws URISyntaxException, FileNotFoundException {
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

    private List<StormConfig> findStormsMovementPattern() {
        List<StormConfig> stormConfigs = new ArrayList<>();

        stormConfigs.add(new StormConfig(storms));

        while (true) {
            for (Storm storm : storms) {
                storm.move(mapHeight, mapWidth);
            }

            StormConfig stormConfig = new StormConfig(storms);

            if (stormConfigs.contains(stormConfig)) {
                break;
            } else {
                stormConfigs.add(stormConfig);
            }
        }

        return stormConfigs;
    }

    private List<String> readInputDataAsStrLines() throws URISyntaxException, FileNotFoundException {
        List<String> strLines = new ArrayList<>();

        URL dataResource = RunPart1.class.getClassLoader().getResource("y2022/d24/input.txt");
        File dataFile = new File(dataResource.toURI());
        Scanner fileScanner = new Scanner(dataFile);

        while (fileScanner.hasNext())
            strLines.add(fileScanner.nextLine());

        fileScanner.close();
        return strLines;
    }

    private boolean isPositionFree(TemporalYXTuple tYXTuple) {
        return stormConfigs.get(tYXTuple.getMinute() % stormConfigs.size()).isPositionFree(tYXTuple.getYxTuple());
    }

    private Integer calculateSteps(int startMinute, YXTuple from, YXTuple to) throws InterruptedException {

        List<TemporalYXTuple> unvisitedQueue = new ArrayList<>();
        List<TemporalYXTuple> visitedQueue = new ArrayList<>();

        unvisitedQueue.add(new TemporalYXTuple(startMinute, from));
        visitedQueue.add(new TemporalYXTuple(startMinute, from));

        while (!unvisitedQueue.isEmpty()) {
            TemporalYXTuple currentTuple = unvisitedQueue.remove(0);

            for (YXTuple neighborYXTuple : getNeighbors(currentTuple.getYxTuple())) {
                TemporalYXTuple neighborTYXTuple = new TemporalYXTuple(currentTuple.getMinute()+1, neighborYXTuple);

                if (neighborYXTuple.equals(to)) {
                    return neighborTYXTuple.getMinute();
                }

                if (!visitedQueue.contains(neighborTYXTuple) && isPositionFree(neighborTYXTuple)) {
                    unvisitedQueue.add(neighborTYXTuple);
                    visitedQueue.add(neighborTYXTuple);
                }
            }
        }

        return null;
    }

    private List<YXTuple> getNeighbors(YXTuple yxTuple) {
        List<YXTuple> neighbors = new ArrayList<>();

        for (YXTuple neighborOffset : NEIGHBOR_POSITIONS) {
            YXTuple neighborCandidate = yxTuple.add(neighborOffset);

            if ((neighborCandidate.getY() == 0 && neighborCandidate.getX()==1) || (neighborCandidate.getY() == this.mapHeight-1 && neighborCandidate.getX()==this.mapWidth-2)) {
                neighbors.add(neighborCandidate);
            } else if (neighborCandidate.getY()>=1 && neighborCandidate.getY()<=this.mapHeight-2 && neighborCandidate.getX()>=1 && neighborCandidate.getX()<=this.mapWidth-2) {
                neighbors.add(neighborCandidate);
            }
        }

        return neighbors;
    }

    public int runSolution(String[] args) throws URISyntaxException, FileNotFoundException, InterruptedException {
        // read input file
        List<String> strLines = readInputDataAsStrLines();

        // take note of map size
        this.mapHeight = strLines.size();
        this.mapWidth = strLines.get(0).length();

        // build storms
        this.storms = buildStorms(strLines);

        // identify storm movement pattern
        this.stormConfigs = findStormsMovementPattern();

        // find steps to destination
        return calculateSteps(0, new YXTuple(0, 1), new YXTuple(mapHeight-1, mapWidth-2));
    }

    public static void main(String[] args) throws URISyntaxException, FileNotFoundException, InterruptedException {
        long startTime = System.currentTimeMillis();

        System.out.println("RESULT: "+(new RunPart1().runSolution(args)));
        System.out.println(String.format("Time: %ds", (System.currentTimeMillis()-startTime)/1000));
 
    }

}
