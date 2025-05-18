package playground;

import java.nio.file.Files;
import java.nio.file.Paths;

public class DatasetsNames {
    public static final String WEB_GOOGLE = "web-Google.txt"; //directed
    public static final String YOUTUBE = "yt.txt"; //directed
    public static final String AMAZON = "amazon0312.txt"; //directed
    public static final String WIKITALK = "WikiTalk.txt"; //directed

    private static int getNumVerticesOrEdges(String datasetName, boolean vertex) {
        int n = 0, m = 0, i = 0;
        try {
            var lines = Files.readAllLines(Paths.get(datasetName));
            while (lines.get(i).startsWith("#")) {
                String[] parts = lines.get(i).split("\\s+");
                if (parts[1].equals("Nodes:")) {
                    n = Integer.parseInt(parts[2]);
                }
                if (parts[3].equals("Edges:")) {
                    m = Integer.parseInt(parts[4]);
                    break;
                }
                i++;
            }
        } catch (Exception ex) {
            System.err.println(ex);
        }
        if (vertex) {
            return n;
        } else {
            return m;
        }
    }

    public static int getNumVertices(String datasetName) {
        return getNumVerticesOrEdges(datasetName, true);
    }

    public static int getNumEdges(String datasetName) {
        return getNumVerticesOrEdges(datasetName, false);
    }
}
