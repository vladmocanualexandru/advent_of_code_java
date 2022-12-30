package mov.aoc.y2022.d24;

public class Storm {
    YXTuple location;
    Direction direction;

    public Storm(int y, int x, Direction direction) {
        this.location = new YXTuple(y, x);
        this.direction = direction;
    }

    public YXTuple getLocation() {
        return location;
    }

    public void setLocation(YXTuple location) {
        this.location = location;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void move(int height, int width) {
        location.addToY(direction.getYOffset());
        location.addToX(direction.getXOffset());

        if (location.getX() == 0) {
            location.setX(width - 2);
        } else if (location.getX() == width - 1) {
            location.setX(1);
        }

        if (location.getY() == 0) {
            location.setY(height - 2);
        } else if (location.getY() == height - 1) {
            location.setY(1);
        }
    }

    @Override
    public String toString() {
        return "Storm [location=" + location + ", direction=" + direction + "]";
    }

}
