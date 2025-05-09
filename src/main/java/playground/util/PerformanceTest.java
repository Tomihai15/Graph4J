package playground.util;

public interface PerformanceTest {
    public void loadGraph(String datasetName);
    public long runPageRankTest(boolean print);
}
