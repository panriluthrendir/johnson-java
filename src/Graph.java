
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;


public class Graph {

    private Map<Integer, Node> nodes;

    public Graph() {
        nodes = new HashMap<>();
    }

    public int order() {
        return nodes.size();
    }

    public int size() {
        int result = 0;
        Node node;
        for (Map.Entry<Integer, Node> entry : nodes.entrySet()) {
            node = entry.getValue();
            result += node.inEdges.size();
        }
        return result;
    }

    public boolean hasNegativeEdge() {
        boolean result = false;
        Node node;
        for (int label : getNodeLabels()) {
            for (Map.Entry<Integer, Integer> entry : 
                    getOutNeighbours(label).entrySet()) {
                        result = entry.getValue() < 0;
            }
        }
        return result;
    }

    public void addNode(int label) {
        if (!hasNode(label)) {
            nodes.put(label, new Node(label));
        }
    }

    public boolean hasNode(int label) {
        return nodes.containsKey(label);
    }

    public Set<Integer> getNodeLabels() {
        return nodes.keySet();
    }

    public Map<Integer, Integer> getOutNeighbours(int label) {
        Map<Integer, Integer> result = new HashMap<>();
        if (hasNode(label)) {
            Node node = nodes.get(label);
            for (Map.Entry<Node, Integer> entry : node.outEdges.entrySet()) {
                result.put(entry.getKey().label, entry.getValue());
            }
        }
        return result;
    }

    public Map<Integer, Integer> getInNeighbours(int label) {
        Map<Integer, Integer> result = new HashMap<>();
        if (hasNode(label)) {
            Node node = nodes.get(label);
            for (Map.Entry<Node, Integer> entry : node.inEdges.entrySet()) {
                result.put(entry.getKey().label, entry.getValue());
            }
        }
        return result;
    }

    public void addEdge(int label0, int label1, int weight) {
        addNode(label0);
        addNode(label1);
        Node tail = nodes.get(label0);
        Node head = nodes.get(label1);
        tail.outEdges.put(head, weight);
        head.inEdges.put(tail, weight);
    }

    public boolean hasEdge(int label0, int label1) {
        return hasNode(label0) &&
               nodes.get(label0).outEdges.containsKey(nodes.get(label1));
    }

    public Graph extend(int[] labels) {
        Graph result = copy();
        for (int label : labels) {
            for (int outLabel : getNodeLabels()) {
                result.addEdge(label, outLabel, 0);
            }
        }
        return result;
    }

    public Graph reweight(Map<Integer, Integer> deflection) {
        Graph result = new Graph();
        for (int label : getNodeLabels()) {
            for (
                Map.Entry<Integer, Integer> entry : 
                    getOutNeighbours(label).entrySet()
                ) {
                int outLabel = entry.getKey();
                int newWeight = entry.getValue();
                newWeight += deflection.get(label);
                newWeight -= deflection.get(outLabel);
                result.addEdge(label, outLabel, newWeight);
            }
        }
        return result;

    }

    public Graph copy() {
        Graph result = new Graph();
        for (int label : getNodeLabels()) {
            for (
                Map.Entry<Integer, Integer> entry : 
                    getOutNeighbours(label).entrySet()
                ) {
                result.addEdge(label, entry.getKey(), entry.getValue());
            }
        }
        return result;
    }

    private class Node {

        private final int label;
        private Map<Node, Integer> inEdges;
        private Map<Node, Integer> outEdges;

        public Node(int label) {
            this.label = label;
            inEdges = new HashMap<>();
            outEdges = new HashMap<>();
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        String LN = System.lineSeparator();
        for (int label : getNodeLabels()) {
            for (
                Map.Entry<Integer, Integer> entry : 
                    getOutNeighbours(label).entrySet()
                ) {
                builder.append(LN);
                builder.append(
                    label + " " + entry.getKey() + " " + entry.getValue()
                );
            }
        }
        return builder.toString();    
    }
}