package org.graph4j.networks;

import org.graph4j.Digraph;
import org.graph4j.DirectedGraphAlgorithm;

import java.util.HashMap;
import java.util.Map;


/**
 * Computes the PageRankTesting of the vertices in the digraph.
 */
public class PageRank extends DirectedGraphAlgorithm {
    private double alpha = 0.85d;//damping factor, best default value
    private final int maxIterations;
    private final double tolerance = 0.0001;
    private double[] ranks;

    public PageRank(Digraph graph, double alpha, int maxIterations) {
        super(graph);
        this.alpha = alpha;
        this.maxIterations = maxIterations;
    }

    public void compute(){
        int n = graph.numVertices();
        double[] currentRanks = new double[n];
        for (int i = 0; i < n; i++) {
            currentRanks[i] = 1.0 / n;
        }

        for (int iter = 0; iter < maxIterations; iter++) {
            double[] nextRanks = new double[n];
            for (int i = 0; i < n; i++) {
                for (int j : graph.neighbors(i)) {
                    nextRanks[j] +=  alpha* currentRanks[i] / graph.outdegree(i);
                }
            }
            for (int i = 0; i < n; i++) {
                nextRanks[i] += (1 - alpha) / n;
            }
            if (isUnderTolerance(currentRanks, nextRanks)) {
                break;
            }
            currentRanks = nextRanks;
        }
        ranks = currentRanks;
    }
    private boolean isUnderTolerance(double[] currentRanks, double[] nextRanks) {
        for (int i = 0; i < currentRanks.length; i++) {
            if (Math.abs(currentRanks[i] - nextRanks[i]) > tolerance) {
                return false;
            }
        }
        return true;
    }
    public double[] getRanksAsArray()
    {
        if (ranks == null) {
            compute();
        }
        return ranks;
    }
    public double getRank(int v) {
        if (ranks == null) {
            compute();
        }
        return ranks[v];
    }
    public <V> Map<V, Double> getRanksAsMap() {
        if (ranks == null) {
            compute();
        }
        Map<V, Double> rankMap = new HashMap<>();
        for (int i = 0; i < ranks.length; i++) {
            rankMap.put((V) graph.getVertexLabel(i), ranks[i]);
        }
        return rankMap;
    }
    public <V> double getVertexScore(V v) {
        if (ranks == null) {
            compute();
        }
        int i = graph.findVertex(v);
        if (i == -1) {
            throw new IllegalArgumentException("Vertex not found in the graph");
        }
        return ranks[i];
    }
}
