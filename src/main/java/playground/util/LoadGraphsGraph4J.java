package playground.util;

import org.graph4j.Graph;
import org.graph4j.GraphBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LoadGraphsGraph4J {

    public static Graph readGraphFromFile(String fileName) {
        Graph graph = null;
        try {
            var lines = Files.readAllLines(Paths.get(fileName));

            graph = GraphBuilder.empty().buildDigraph();
            for (String line : lines) {
                if (line.startsWith("#")) {
                    continue;
                }
                String[] parts = line.split("\\s+");
                Integer u = Integer.parseInt(parts[0]);
                Integer v = Integer.parseInt(parts[1]);
                if (graph.findVertex(u) == -1) {
                    graph.addLabeledVertex(u);
                }
                if (graph.findVertex(v) == -1) {
                    graph.addLabeledVertex(v);
                }

                graph.addEdge(u, v);
            }
        } catch (IOException ex) {
            System.err.println(ex);
        }
        return graph;
    }
}
