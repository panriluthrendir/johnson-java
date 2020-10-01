import java.util.Map;
import java.util.HashMap;
import java.util.Set;

public class BellmanFordRouter implements Router {

    @Override
    public Map<Integer, Integer> shortestPaths(Graph graph, int source) {
        Map<Integer, Integer> current = new HashMap<>();
        Map<Integer, Integer> last = new HashMap<>();
        Set<Integer> labels = graph.getNodeLabels();
        for (int label : labels) {
            if (label == source) {
                current.put(label, 0);
            } else {
                current.put(label, 1000000);
            }
        }
        for (int i = 1; i <= graph.order(); i++) {
            last = current;
            current = new HashMap<>();
            for (int label : labels) {
                int min = last.get(label);
                for (
                        Map.Entry<Integer, Integer> entry :
                        graph.getInNeighbours(label).entrySet()
                    ) {
                        int other = last.get(entry.getKey()) + 
                                        entry.getValue();
                        if (other < min) {
                            if (i < graph.order()) {
                                min = other;
                            } else {
                                throw new NegativeCycleException
                                (
                                    "Negative cost cycle detected"
                                );
                            }
                           
                        }
                    }
                current.put(label, min);
            }
            
        }
        return current;
    }
}