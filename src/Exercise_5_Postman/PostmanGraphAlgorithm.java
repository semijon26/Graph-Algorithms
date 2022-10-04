package Exercise_5_Postman;

import Graph.Edge;
import Graph.Node;
import Graph.Vertex;
import Graph.UnweightedUndirectedGraph;

import java.util.ArrayList;

public class PostmanGraphAlgorithm {

    private UnweightedUndirectedGraph graph;
    private UnweightedUndirectedGraph workGraph = new UnweightedUndirectedGraph();
    private ArrayList<Vertex> tour = new ArrayList<>();
    private ArrayList<Vertex> subtour = new ArrayList<>();
    private Vertex start = null;
    private Vertex akt = null;
    private int graphType;
    private ArrayList<Vertex> resultTour = null;


    public PostmanGraphAlgorithm(UnweightedUndirectedGraph graph) {
        this.graph = graph;

        createWorkGraph();

        graphType = checkGraphIfEulersch();
        if (graphType != 0) {
            resultTour = hierholzer();
            printResultTour();
        }

    }

    private void createWorkGraph() { // O(n*m)

        for (Vertex v : graph.getMap().keySet()) {
            workGraph.addVertex(v);
        }
        for (Vertex v : graph.getMap().keySet()) {
            for (Node n : graph.getAdjVertices(v)) {
                workGraph.getMap().get(v).add(n);
            }
        }


    }


    private ArrayList<Vertex> hierholzer (){

        // start wird bereits in checkGraph erstmals festgelegt
        tour.add(start);

        while (!isEulerWegOrTour()) {

            if (countFreeEdgesOfVertex(start) == 0) {
                for (Vertex v : workGraph.getMap().keySet()) {
                    if (countFreeEdgesOfVertex(v) != 0 && tour.contains(v)) {
                        start = v;
                    }
                }
            }

            akt = start;
            subtour.add(start);


            do {

                Edge unvisitedEdge = null;

                // suche freie Kante vom aktuellen Knoten:

                if (!workGraph.getAdjVertices(akt).isEmpty()) {
                    unvisitedEdge = workGraph.getAdjVertices(akt).get(0).getEdge();
                    workGraph.getAdjVertices(akt).remove(0);
                }

                // wenn keine Kanten mehr da sind, weil der Graph semi eulersch ist
                // und wir beim anderen ungeraden Knoten sind: gehe direkt zur Subtour-Integration
                if (unvisitedEdge == null && graphType == 2) {
                    break;
                }


                // auch umgekehrte richtung als visited markieren
                Vertex destVertex = unvisitedEdge.getDest();
                for (int i = 0; i < workGraph.getAdjVertices(destVertex).size(); i++) { // O(m)
                    Node n = workGraph.getAdjVertices(destVertex).get(i);
                    if (n.getDestVertex() == unvisitedEdge.getSrc()) {
                        workGraph.getAdjVertices(destVertex).remove(i);
                        break;
                    }
                }

                subtour.add(destVertex);

                akt = destVertex;

            } while (start != akt);


            insertSubtourIntoTour();
        }
        return tour;
    }


    private int countFreeEdgesOfVertex (Vertex vertex) {
        return workGraph.getAdjVertices(vertex).size();
    }

    private void insertSubtourIntoTour() {

        int replaceHere = tour.indexOf(subtour.get(0));

        // wenn semi eulersch: damit letzte subtour nicht am anfang eingefügt wird
        if (graphType == 2) {
            int sameElementCounter = 0;
            int elementPosition = 0;
            for (int i = 0; i < tour.size(); i++) {
                if (tour.get(i) == tour.get(replaceHere)) {
                    sameElementCounter++;
                    elementPosition = i;
                }
                if (sameElementCounter == 2) {
                    replaceHere = elementPosition;
                }
            }
        }
        tour.remove(replaceHere);
        for (int i = 0; i < subtour.size(); i++) {
            tour.add(replaceHere+i, subtour.get(i));
        }
        subtour.clear();

    }

    private boolean isEulerWegOrTour() {
        return workGraph.countEdges() == 0;
    }



    private int checkGraphIfEulersch () {
        // Output:  Eulergraph: 1    Semi-Eulerisch: 2     Ungültig: 0

        int counter = 0;

        for (Vertex v : graph.getMap().keySet()) { // O(n)
            if (graph.getAdjVertices(v).size() % 2 == 1) {
                counter++;
            }
        }

        if (counter == 0){
            for (Vertex v : graph.getMap().keySet()) {
                // nehme ersten knoten und gehe aus der schleife
                start = v;
                return 1;
            }
        }

        else if (counter == 2) {
            for (Vertex v : graph.getMap().keySet()) {
                if (graph.getAdjVertices(v).size() % 2 == 1) {
                    // start = ungerader knoten
                    start = v;
                    return 2;
                }
            }
        }

        System.out.println("Graph ungültig.");
        return 0;
    }


    private void printResultTour() {

        System.out.println("Die beste Route ist: ");

        for (int i = 0; i < resultTour.size(); i++) {
            System.out.print(resultTour.get(i));
            if (i < resultTour.size()-1) {
                System.out.print(" -> ");
            }
        }
        System.out.println();
    }
}


