package mov.aoc.y2022.d24;

public enum Direction {
    N(-1,0), E(0,1), S(1,0), W(0,-1);

    private int xOffset, yOffset;

    private Direction(int yOffset, int xOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    public int getXOffset() {
        return xOffset;
    }

    public int getYOffset() {
        return yOffset;
    }
}
