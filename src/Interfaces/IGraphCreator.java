package Interfaces;

import Graph.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public interface IGraphCreator {

    Scanner scanner = new Scanner(System.in);
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    Map<String, Vertex> labelMap = new HashMap<>();

    void create() throws IOException;

    void runExample();

    default void checkIfisZusammenhaengenderGraph(Graph graph) throws Exception {

        Graph testGraph = graph;
        boolean isDirected = false;

        if (graph.getClass().getName().equals(UnweightedDirectedGraph.class.getName())) {
            testGraph = new UnweightedDirectedGraph();
            isDirected = true;
        } else if (graph.getClass().getName().equals(WeightedDirectedGraph.class.getName())) {
            testGraph = new WeightedDirectedGraph();
            isDirected = true;
        }

        if (isDirected) {
            for (Vertex v : graph.getMap().keySet()) {
                testGraph.addVertex(new Vertex(v.getLabel()));
            }

            for (Vertex v : graph.getMap().keySet()) {
                for (Node n : graph.getMap().get(v)) {
                    Vertex edgeSrc = testGraph.getVertex(v.getLabel());
                    Vertex edgeDst = testGraph.getVertex(n.getDestVertex().getLabel());
                    testGraph.addEdge(edgeSrc, edgeDst);
                    testGraph.addEdge(edgeDst, edgeSrc);
                }
            }
        }

        Vertex s = null;

        for (Vertex v : testGraph.getMap().keySet()) {
            s = v;
            break;
        }

        int expectedVertexCount = testGraph.countVertices() - 1;
        int countedVertices = 0;

        for (Vertex t : testGraph.getMap().keySet()) {
            // BFS:
            HashSet<Vertex> visitedNodes = new HashSet<>();
            LinkedList<Vertex> queue = new LinkedList<>();

            visitedNodes.add(s);
            queue.addLast(s);

            //so lange noch knoten da sind:
            while (!queue.isEmpty()) {

                // setze s als erstes in die queue:
                Vertex current = queue.poll();

                // für jeden nachbarknoten vom obersten queue-knoten:
                for (Node node : testGraph.getAdjVertices(current)){

                    // falls der Knoten nicht schon besucht wurde (und die kante existiert / > 0 ist):
                    if (!visitedNodes.contains(node.getDestVertex())) {

                        //falls der Knoten der Zielknoten ist: gib true zurück
                        if (node.getDestVertex().getLabel().equals(t.getLabel())) {
                            countedVertices++;
                        }
                        // markiere den knoten als gesehen, füge ihn Traversierungsliste hinzu und tu ihn in die Queue
                        queue.addLast(node.getDestVertex());
                        visitedNodes.add(node.getDestVertex());
                    }
                }
            }
        }
        //System.out.println("BFS trying to find the other vertices, starting from " + s + ":");
        //System.out.println("Expected: " + expectedVertexCount + "   found: " + countedVertices);
        if (countedVertices != expectedVertexCount) {
            throw new Exception("Graph nicht zusammenhängend!");
        }
    }

    default void checkIfhasConnectionFromSourceToTrap (Graph graph) throws Exception {

        Graph testGraph = graph;
        boolean isDirected = false;

        if (graph.getClass().getName().equals(UnweightedDirectedGraph.class.getName())) {
            testGraph = new UnweightedDirectedGraph();
            isDirected = true;
        } else if (graph.getClass().getName().equals(WeightedDirectedGraph.class.getName())) {
            testGraph = new WeightedDirectedGraph();
            isDirected = true;
        }

        if (isDirected) {
            for (Vertex v : graph.getMap().keySet()) {
                testGraph.addVertex(new Vertex(v.getLabel()));
            }

            for (Vertex v : graph.getMap().keySet()) {
                for (Node n : graph.getMap().get(v)) {
                    Vertex edgeSrc = testGraph.getVertex(v.getLabel());
                    Vertex edgeDst = testGraph.getVertex(n.getDestVertex().getLabel());
                    testGraph.addEdge(edgeSrc, edgeDst);
                    testGraph.addEdge(edgeDst, edgeSrc);
                }
            }
        }

        testGraph.setSource(testGraph.getVertex(graph.getSource().getLabel()));
        testGraph.setTrap(testGraph.getVertex(graph.getTrap().getLabel()));

        Vertex s = testGraph.getSource();
        Vertex t = testGraph.getTrap();

        // BFS:
        HashSet<Vertex> visitedNodes = new HashSet<>();
        LinkedList<Vertex> queue = new LinkedList<>();

        visitedNodes.add(s);
        queue.addLast(s);
        boolean isPathFromSToT = false;

        //so lange noch knoten da sind:
        while (!queue.isEmpty()) {
            if (isPathFromSToT) {
                break;
            }

            // setze s als erstes in die queue:
            Vertex current = queue.poll();

            // für jeden nachbarknoten vom obersten queue-knoten:
            for (Node node : testGraph.getAdjVertices(current)){

                // falls der Knoten nicht schon besucht wurde (und die kante existiert / > 0 ist):
                if (!visitedNodes.contains(node.getDestVertex())) {

                    //falls der Knoten der Zielknoten ist: gib true zurück
                    if (node.getDestVertex().getLabel().equals(t.getLabel())) {
                        isPathFromSToT = true;
                        break;
                    }
                    // markiere den knoten als gesehen, füge ihn Traversierungsliste hinzu und tu ihn in die Queue
                    queue.addLast(node.getDestVertex());
                    visitedNodes.add(node.getDestVertex());
                }
            }
        }
        if (!isPathFromSToT) {
            throw new Exception("No connection from source to trap!");
        }
    }

}
