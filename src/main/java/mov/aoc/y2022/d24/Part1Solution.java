package mov.aoc.y2022.d24;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import mov.aoc.templates.AbstractSolution;

public class Part1Solution extends AbstractSolution{

    public Part1Solution() {
        super(2022, 24);
    }

    private YXTuple[] NEIGHBOR_POSITIONS = new YXTuple[]{new YXTuple(-1, 0),new YXTuple(0, 0),new YXTuple(1, 0),new YXTuple(0, -1),new YXTuple(0, 1)};
    
    private int mapHeight;
    private int mapWidth;
    private List<Storm> storms;
    private List<StormConfig> stormConfigs;

    public int getMapHeight() {
        return mapHeight;
    }

    public void setMapHeight(int mapHeight) {
        this.mapHeight = mapHeight;
    }

    public int getMapWidth() {
        return mapWidth;
    }

    public void setMapWidth(int mapWidth) {
        this.mapWidth = mapWidth;
    }

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

    private boolean isPositionFree(TemporalYXTuple tYXTuple) {
        return stormConfigs.get(tYXTuple.getMinute() % stormConfigs.size()).isPositionFree(tYXTuple.getYxTuple());
    }

    public Integer calculateSteps(int startMinute, YXTuple from, YXTuple to) {

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

                if (isPositionFree(neighborTYXTuple) && !visitedQueue.contains(neighborTYXTuple)) {
                    unvisitedQueue.add(neighborTYXTuple);
                    visitedQueue.add(neighborTYXTuple);
                }
            }
        }

        return -1;
    }

    private List<YXTuple> getNeighbors(YXTuple yxTuple) {
        List<YXTuple> neighbors = new ArrayList<>();

        for (YXTuple neighborOffset : NEIGHBOR_POSITIONS) {
            YXTuple neighborCandidate = yxTuple.add(neighborOffset);

            if ((neighborCandidate.getY() == 0 && neighborCandidate.getX()==1) || (neighborCandidate.getY() == this.mapHeight-1 && neighborCandidate.getX()==this.mapWidth-2)) {
                neighbors.add(neighborCandidate);
            } else 
            if (neighborCandidate.getY()>=1 && neighborCandidate.getY()<=this.mapHeight-2 && neighborCandidate.getX()>=1 && neighborCandidate.getX()<=this.mapWidth-2) {
                neighbors.add(neighborCandidate);
            }
        }

        return neighbors;
    }

    public void init() throws FileNotFoundException, URISyntaxException {
        // read input file
        List<String> strLines = inputDataReader.asStringLines();

        // take note of map size
        this.mapHeight = strLines.size();
        this.mapWidth = strLines.get(0).length();

        // build storms
        this.storms = buildStorms(strLines);

        // identify storm movement pattern
        this.stormConfigs = findStormsMovementPattern();
    }


    public int run(String[] args) throws URISyntaxException, FileNotFoundException {
        init();

        // find steps to destination
        return calculateSteps(0, new YXTuple(0, 1), new YXTuple(mapHeight-1, mapWidth-2));
    }

    public static void main(String[] args) throws URISyntaxException, FileNotFoundException{
        long startTime = System.currentTimeMillis();

        int result = (new Part1Solution().run(args));

        System.out.println(String.format("RESULT: %d (in %ds)", result, (System.currentTimeMillis()-startTime)/1000));
    }

}
