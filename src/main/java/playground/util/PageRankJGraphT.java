package playground.util;

import org.jgrapht.Graph;
import org.jgrapht.alg.scoring.PageRank;
import org.jgrapht.graph.DefaultEdge;

public class PageRankJGraphT implements PerformanceTest {

    Graph<String, DefaultEdge> graph;

    @Override
    public void loadGraph(String datasetName) {
        graph = LoadGraphsJGraphT.readGraphFromFile(datasetName);
    }

    @Override
    public long runPageRankTest(boolean print) {
        long startTime = System.currentTimeMillis();
        PageRank<String, DefaultEdge> pageRank = new PageRank<>(graph, 0.85);
        pageRank.getScores();
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        if (print) {
            System.out.println("PageRank values:");
            for (String vertex : graph.vertexSet()) {
                double rank = pageRank.getVertexScore(vertex);
                System.out.println("Vertex " + vertex + ": " + rank);
            }
        }
        return totalTime;
    }
}
