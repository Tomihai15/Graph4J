package playground;

import playground.util.PageRankGraph4J;
import playground.util.PageRankJGraphT;
import playground.util.PerformanceTest;
import playground.util.Result;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class PageRankDashboard {
    public static Result runTest(String datasetName)
    {
        Result result = new Result();
        PerformanceTest performanceTest;
        result.setAlgorithmName("PageRank");
        result.setDatasetName(datasetName);
        result.setGraphType("Directed");
        result.setNumVertices(DatasetsNames.getNumVertices(datasetName));
        result.setNumEdges(DatasetsNames.getNumEdges(datasetName));

        performanceTest= new PageRankJGraphT();
        performanceTest.loadGraph(datasetName);
        long time = performanceTest.runPageRankTest(false);
        result.setExecutionTimeJGraphT(time);

        performanceTest = new PageRankGraph4J();
        performanceTest.loadGraph(datasetName);
        time = performanceTest.runPageRankTest(false);
        result.setExecutionTimeGraph4J(time);

        return  result;
    }

    public static void writeResultsToFile(List<Result> results, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Result result : results) {
                writer.write(result.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        List<Result> results = new ArrayList<>();
        String[] datasets = {
                DatasetsNames.YOUTUBE,
                DatasetsNames.WEB_GOOGLE
        };

        for (String dataset : datasets) {
            Result r = runTest(dataset);
            results.add(r);
        }
//        var r = runTest(DatasetsNames.YOUTUBE);
//        results.add(r);
//
//        r = runTest(DatasetsNames.WEB_GOOGLE);
//        results.add(r);

        writeResultsToFile(results, "results.txt");
    }

}
