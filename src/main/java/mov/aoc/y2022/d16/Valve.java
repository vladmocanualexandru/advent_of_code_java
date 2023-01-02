package mov.aoc.y2022.d16;

import java.util.ArrayList;
import java.util.List;

public class Valve {
    int flowRate;
    List<Valve> connections = new ArrayList<>();

    public Valve(int flowRate) {
        this.flowRate = flowRate;
    }
    public int getFlowRate() {
        return flowRate;
    }
    public void setFlowRate(int flowRate) {
        this.flowRate = flowRate;
    }
    public List<Valve> getConnections() {
        return connections;
    }
    public void setConnections(List<Valve> connections) {
        this.connections = connections;
    }
    @Override
    public String toString() {
        return "Valve [flowRate=" + flowRate + ", connections=" + connections + "]";
    }
    
}
