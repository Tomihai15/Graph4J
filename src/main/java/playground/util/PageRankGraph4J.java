package playground.util;

import org.graph4j.Digraph;
import org.graph4j.Graph;
import org.graph4j.networks.PageRank;

import java.util.Map;

public class PageRankGraph4J implements PerformanceTest {

    private Graph graph;

    @Override
    public void loadGraph(String datasetName) {
        graph = LoadGraphsGraph4J.readGraphFromFile(datasetName);
    }

    @Override
    public long runPageRankTest(boolean print) {
        PageRank pageRank = new PageRank((Digraph) graph, 0.85, 100);
        long startTime = System.currentTimeMillis();
        pageRank.compute();
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        Map ranks = pageRank.getRanksAsMap();
        if (print) {
            System.out.println("PageRank values:");
            for (Object vertex : ranks.keySet()) {
                double rank = (double) ranks.get(vertex);
                System.out.println("Vertex " + graph.getVertexLabel((int) vertex) + ": " + rank);
            }
        }
//        double[] ranks = pageRank.getRanksAsArray();
//        System.out.println("PageRank values:");
//        for (int i = 0; i < ranks.length; i++) {
//            System.out.println("Vertex " + graph.getVertexLabel(i) + ": " + ranks[i]);
//        }
        return totalTime;
    }
}
