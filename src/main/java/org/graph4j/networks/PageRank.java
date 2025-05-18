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
    private final int DEFAULT_MAXIMUM_ITERATIONS = 100;

    public PageRank(Digraph graph, double alpha, int maxIterations) {
        super(graph);
        this.alpha = alpha;
        this.maxIterations = maxIterations;
    }
    public PageRank(Digraph graph) {
        super(graph);
        maxIterations = DEFAULT_MAXIMUM_ITERATIONS;
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
                for (int j : graph.neighbors(i)) {//incoming edges?
                    nextRanks[i] += alpha * currentRanks[j] / graph.outdegree(j);
                }
            }
            double t = (1 - alpha) / n;
            for (int i = 0; i < n; i++) {
                nextRanks[i] += t;
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
    public double[] getRanks()
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
    private Map ranksMap = null;
    public <V> Map<V, Double> getRanksAsMap() {
        if (ranks == null) {
            compute();
        }
        if (ranksMap == null) {
            ranksMap = new HashMap<>();
            for (int i = 0; i < ranks.length; i++) {
                ranksMap.put((V) graph.getVertexLabel(i), ranks[i]);
            }
        }
        return ranksMap;
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
