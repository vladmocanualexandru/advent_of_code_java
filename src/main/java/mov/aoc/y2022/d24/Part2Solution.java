package mov.aoc.y2022.d24;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;

public class Part2Solution {

    public int run(String[] args) throws URISyntaxException, FileNotFoundException, InterruptedException {

        Part1Solution part1 = new Part1Solution();
        part1.init();

        int height = part1.getMapHeight();
        int width = part1.getMapWidth();

        YXTuple source = new YXTuple(0, 1);
        YXTuple destination = new YXTuple(height-1, width-2);

        // distance from source to destination
        int sourceDestResult = part1.calculateSteps(0, source, destination);

        // distance from destination to source
        int destSourceResult = part1.calculateSteps(sourceDestResult, destination, source);
        
        // // distance from source to destination
        int sourceDestResult2 = part1.calculateSteps(destSourceResult, source, destination);

        return sourceDestResult2;
    }

    public static void main(String[] args) throws URISyntaxException, FileNotFoundException, InterruptedException {
        long startTime = System.currentTimeMillis();

        int result = (new Part2Solution().run(args));

        System.out.println(String.format("RESULT: %d (in %ds)", result, (System.currentTimeMillis()-startTime)/1000));
    }

}
