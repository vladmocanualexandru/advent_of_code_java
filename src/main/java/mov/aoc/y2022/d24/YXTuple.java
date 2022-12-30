package mov.aoc.y2022.d24;

public class YXTuple {
    int x,y;

    public YXTuple(int y, int x) {
        this.x = x;
        this.y = y;
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

    public void addToY(int offset) {
        this.y += offset;
    }

    public void addToX(int offset) {
        this.x += offset;
    }

    public YXTuple clone() {
        return new YXTuple(y, x);
    }

    public YXTuple add(YXTuple t) {
        return new YXTuple(this.y+t.y, this.x+t.x);
    }

    @Override
    public String toString() {
        return "YXTuple [x=" + x + ", y=" + y + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + x;
        result = prime * result + y;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        YXTuple other = (YXTuple) obj;
        if (x != other.x)
            return false;
        if (y != other.y)
            return false;
        return true;
    }

    
}
