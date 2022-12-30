package mov.aoc.y2022.d24;

public class TemporalYXTuple {
    int minute;
    YXTuple yxTuple;

    public TemporalYXTuple(int minute, YXTuple yxTuple) {
        this.minute = minute;
        this.yxTuple = yxTuple;
    }
    public int getMinute() {
        return minute;
    }
    public void setMinute(int minute) {
        this.minute = minute;
    }
    public YXTuple getYxTuple() {
        return yxTuple;
    }
    public void setYxTuple(YXTuple yxTuple) {
        this.yxTuple = yxTuple;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + minute;
        result = prime * result + ((yxTuple == null) ? 0 : yxTuple.hashCode());
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
        TemporalYXTuple other = (TemporalYXTuple) obj;
        if (minute != other.minute)
            return false;
        if (yxTuple == null) {
            if (other.yxTuple != null)
                return false;
        } else if (!yxTuple.equals(other.yxTuple))
            return false;
        return true;
    }
    @Override
    public String toString() {
        return "TemporalYXTuple [minute=" + minute + ", yxTuple=" + yxTuple + "]";
    }

    
}
