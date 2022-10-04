package Exercise_1_Village_Streets;

import Graph.Vertex;
import Graph.WeightedUndirectedGraph;
import Interfaces.IGraphCreator;

import java.io.IOException;

public class VillageGraphCreator implements IGraphCreator {

    WeightedUndirectedGraph graph = new WeightedUndirectedGraph();

    public void create() throws IOException {
        System.out.println("Ein neuer Graph für das Straßennetz wird erstellt.");
        System.out.println("Geben Sie die Anzahl der Knoten ein: (Ganze Zahl zwischen 2 und 1000)");
        int vertexCount = scanner.nextInt();
        if (vertexCount < 2 || vertexCount > 1000) {
            throw new IllegalArgumentException();
        }

        System.out.println("Geben Sie die Bezeichnungen aller Knoten ein: (Keine Duplikate erlaubt)");
        for (int i = 0; i < vertexCount; i++) {
            System.out.println("Knoten " + (i + 1) + ": ");
            String label = bufferedReader.readLine();
            Vertex vertex = new Vertex(label);
            labelMap.put(label, vertex);
            graph.addVertex(vertex);
        }

        System.out.println("Geben Sie die Anzahl der Kanten ein: (ganze Zahl zwischen 1 und 1000)");
        int edgeCount = scanner.nextInt();
        if (edgeCount < 1 || edgeCount > 1000) {
            throw new IllegalArgumentException();
        }

        for (int i = 0; i < edgeCount; i++) {
            System.out.println("Kante " + (i + 1) + ": ");

            System.out.println("von: ");
            String source = bufferedReader.readLine();
            System.out.println("nach: ");
            String dest = bufferedReader.readLine();
            System.out.println("Kantengewicht (1 - 10000): ");
            int weight = scanner.nextInt();
            if (weight < 1 || weight > 10000) {
                throw new IllegalArgumentException();
            }

            graph.addEdge(labelMap.get(source), labelMap.get(dest), weight);
        }

        System.out.println("Graph: ");
        System.out.println(graph);

        try {
            checkIfisZusammenhaengenderGraph(graph);
            VillagePathAlgorithm a = new VillagePathAlgorithm(graph);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void runExample() {

        Vertex A = new Vertex("A");
        Vertex B = new Vertex("B");
        Vertex C = new Vertex("C");
        Vertex D = new Vertex("D");
        Vertex E = new Vertex("E");
        Vertex F = new Vertex("F");
        Vertex G = new Vertex("G");
        Vertex H = new Vertex("H");
        Vertex I = new Vertex("I");
        Vertex J = new Vertex("J");

        graph.addVertex(A);
        graph.addVertex(B);
        graph.addVertex(C);
        graph.addVertex(D);
        graph.addVertex(E);
        graph.addVertex(F);
        graph.addVertex(G);
        graph.addVertex(H);
        graph.addVertex(I);
        graph.addVertex(J);

        graph.addEdge(A, B, 2);
        graph.addEdge(A, E, 3);
        graph.addEdge(A, F, 3);
        graph.addEdge(B, C, 4);
        graph.addEdge(B, D, 5);
        graph.addEdge(B, E, 4);
        graph.addEdge(C, D, 3);
        graph.addEdge(C, H, 5);
        graph.addEdge(E, D, 4);
        graph.addEdge(E, G, 4);
        graph.addEdge(E, F, 2);
        graph.addEdge(F, G, 3);
        graph.addEdge(F, J, 4);
        graph.addEdge(G, D, 3);
        graph.addEdge(G, H, 2);
        graph.addEdge(G, J, 3);
        graph.addEdge(H, I, 3);
        graph.addEdge(H, J, 4);
        graph.addEdge(J, I, 2);

        System.out.println("Graph: ");
        System.out.println(graph);

        VillagePathAlgorithm a = new VillagePathAlgorithm(graph);

    }

}
