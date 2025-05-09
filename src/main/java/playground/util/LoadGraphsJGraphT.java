package playground.util;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.builder.GraphTypeBuilder;
import org.jgrapht.util.SupplierUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LoadGraphsJGraphT {
    public static Graph<String, DefaultEdge> readGraphFromFile(String fileName) {
        Graph<String, DefaultEdge> graph = GraphTypeBuilder.<String, DefaultEdge>directed().allowingMultipleEdges(false).allowingSelfLoops(false).edgeClass(DefaultEdge.class).vertexSupplier(SupplierUtil.createStringSupplier()).buildGraph();
        try {
            var lines = Files.readAllLines(Paths.get(fileName));

            for (String line : lines) {
                if (line.startsWith("#")) {
                    continue;
                }
                String[] parts = line.split("\\s+");
                String u = parts[0];
                String v = parts[1];
                if (!graph.containsVertex(u)) graph.addVertex(u);
                if (!graph.containsVertex(v)) graph.addVertex(v);
                graph.addEdge(u, v);
            }
        } catch (IOException ex) {
            System.err.println(ex);
        }

        return graph;
    }
}
