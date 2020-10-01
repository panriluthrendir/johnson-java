
import java.util.Map;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Comparator;


public class DijkstraRouter implements Router {

    @Override
    public Map<Integer, Integer> shortestPaths(Graph graph, int source) {
        Map<Integer, Integer> costs = new HashMap<>();
        for (int label : graph.getNodeLabels()) {
            if (label == source) {
                costs.put(label, 0);
            } else {
                costs.put(label, 1000000);
            }
        }
        PriorityQueue<Integer> frontier = 
            new PriorityQueue<>
                (new Comparator<Integer>() {

                    @Override
                    public int compare(Integer a, Integer b) {
                        return costs.get(a) - costs.get(b);
                    }
                });
        for (int label : graph.getNodeLabels()) {
            frontier.add(label);
        }
        while (!frontier.isEmpty()) {
            int nextLabel = frontier.remove();
            for (
                    Map.Entry<Integer, Integer> entry : 
                    graph.getOutNeighbours(nextLabel).entrySet()
                ) {
                    int outLabel = entry.getKey();
                    int weight = entry.getValue();
                    int newCost = costs.get(nextLabel) + weight;
                    if (frontier.contains(outLabel) &&
                        newCost < costs.get(outLabel)) {
                            costs.put(outLabel, newCost);
                            frontier.remove(outLabel);
                            frontier.add(outLabel);
                    }
                    
            }
        }
        return costs;
    }
}