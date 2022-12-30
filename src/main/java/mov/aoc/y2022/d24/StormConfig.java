package mov.aoc.y2022.d24;

import java.util.ArrayList;
import java.util.List;

public class StormConfig {
    List<YXTuple> stormLocations = new ArrayList<>();

    public StormConfig(List<Storm> storms) {
        for (Storm storm : storms) {
            stormLocations.add(storm.getLocation().clone());
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((stormLocations == null) ? 0 : stormLocations.hashCode());
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
        StormConfig other = (StormConfig) obj;
        
        if (this.stormLocations.size() != other.stormLocations.size())
            return false;

        for (int stormLocationIndex = 0; stormLocationIndex < other.stormLocations.size(); stormLocationIndex++) {
            if (!this.stormLocations.get(stormLocationIndex).equals(other.stormLocations.get(stormLocationIndex)))
                return false;
        } 

        return true;
    }

    public boolean isPositionFree(YXTuple yxTuple) {
        return !this.stormLocations.contains(yxTuple);
    }
    
}
