package mov.aoc.y2022.d24;

public class Storm {
    int x, y;
    Direction direction;
    public Storm(int y,int x,  Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    public Direction getDirection() {
        return direction;
    }
    public void setDirection(Direction direction) {
        this.direction = direction;
    }
    @Override
    public String toString() {
        return "Storm [x=" + x + ", y=" + y + ", direction=" + direction + "]";
    }
}
