/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadtriproute;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.Double.parseDouble;
import java.util.ArrayList;

public class GraphMap {
    /*public static final int MAX_VERTEX = 200;
    private ArrayList<Vertex> intersectionList;
    private Graph GeographicGraph;
    
    public Graph getGeographicGraph() {
        return GeographicGraph;
    }
    
    public void GraphMap(String IntersectionFile) {
        GeographicGraph = new Graph(MAX_VERTEX);
        intersectionList = new ArrayList<Vertex>(MAX_VERTEX);
        
        try (BufferedReader br = new BufferedReader(new FileReader(IntersectionFile))) {
            String CurrentLine;
            while (!(CurrentLine = br.readLine()).equals("***")) {
                String [] res = CurrentLine.split(" ");
                String idVertex = res[0];
                Double latitude = parseDouble(res[1]);
                Double longitude = parseDouble(res[2]);
                GeographicGraph.AddVertex(idVertex, latitude, longitude);
            }
            while ((CurrentLine = br.readLine()) != null) {
                String [] res = CurrentLine.split(" ");
                String idV1 = res[0];
                String idV2 = res[1];
                Vertex V1 = GeographicGraph.FindVertex(idV1);
                Vertex V2 = GeographicGraph.FindVertex(idV2);
                GeographicGraph.AddEdge(V1, V2);
            }
	} catch (IOException e) {
            e.printStackTrace();
	}
        
        intersectionList = GeographicGraph.getVertices();
    }*/
}
