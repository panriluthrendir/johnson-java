import java.lang.Math;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;


public class GraphReader {

    public static Graph readFromFile(String path) {
        Graph result = new Graph();
        try {
            BufferedReader reader =
                new BufferedReader(
                    new FileReader(
                        new File(path)
                    )
                );
            String line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] s = line.split("\\s");
                result.addEdge
                (
                    Integer.parseInt(s[0]),
                    Integer.parseInt(s[1]),
                    Integer.parseInt(s[2])
                );
            }
            reader.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}