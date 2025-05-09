package playground.util;

public class Result {
    String datasetName;
    String algorithmName;
    String graphType;
    int numVertices;
    int numEdges;

    long executionTimeGraph4J;
    long executionTimeJGraphT;

    public String getDatasetName() {
        return datasetName;
    }

    public void setDatasetName(String datasetName) {
        this.datasetName = datasetName;
    }

    public String getAlgorithmName() {
        return algorithmName;
    }

    public void setAlgorithmName(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    public String getGraphType() {
        return graphType;
    }

    public void setGraphType(String graphType) {
        this.graphType = graphType;
    }

    public int getNumVertices() {
        return numVertices;
    }

    public void setNumVertices(int numVertices) {
        this.numVertices = numVertices;
    }

    public int getNumEdges() {
        return numEdges;
    }

    public void setNumEdges(int numEdges) {
        this.numEdges = numEdges;
    }

    public long getExecutionTimeGraph4J() {
        return executionTimeGraph4J;
    }

    public void setExecutionTimeGraph4J(long executionTimeGraph4J) {
        this.executionTimeGraph4J = executionTimeGraph4J;
    }

    public long getExecutionTimeJGraphT() {
        return executionTimeJGraphT;
    }

    public void setExecutionTimeJGraphT(long executionTimeJGraphT) {
        this.executionTimeJGraphT = executionTimeJGraphT;
    }

    @Override
    public String toString() {
        return "Result{" +
                "datasetName='" + datasetName + '\'' +
                ", algorithmName='" + algorithmName + '\'' +
                ", graphType='" + graphType + '\'' +
                ", numVertices=" + numVertices +
                ", numEdges=" + numEdges +
                ", executionTimeGraph4J=" + executionTimeGraph4J +
                ", executionTimeJGraphT=" + executionTimeJGraphT +
                '}';
    }
}
