public class Demo {

    public static void main(String[] args) {
        Graph graph = GraphReader.readFromFile("src/g3.txt");
        System.out.println(Johnson.allPairShortestPaths(graph));
    }
}
