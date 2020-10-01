import java.util.HashMap;
import java.util.Map;

public class Johnson {

    public static Map<IntPair, Integer> allPairShortestPaths(Graph graph) {
        Map<IntPair, Integer> result = new HashMap<>();
        Graph extGraph = graph.extend(new int[] {0});
        Router bellman = new BellmanFordRouter();
        Router dijkstra = new DijkstraRouter();
        try {
           Map<Integer, Integer> deflection = bellman.shortestPaths(extGraph, 0);
            Graph rwGraph = graph.reweight(deflection);
            for (int start : rwGraph.getNodeLabels()) {
                System.out.println(start);
                Map<Integer, Integer> singleShortestPaths =
                        dijkstra.shortestPaths(rwGraph, start);
                for (int end : singleShortestPaths.keySet()) {
                        result.put
                                (
                                        new IntPair(start, end),
                                        singleShortestPaths.get(end) - deflection.get(start) + deflection.get(end)
                                );
                }
            }
        } catch (NegativeCycleException e) {
            e.printStackTrace();
        }
        return result;
    }
}