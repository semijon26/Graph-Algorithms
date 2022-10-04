package Exercise_2_Water;

import Graph.Vertex;
import Graph.WeightedDirectedGraph;
import Interfaces.IGraphCreator;

import java.io.IOException;

public class WaterSupplyGraphCreator implements IGraphCreator {

    private final WeightedDirectedGraph graph = new WeightedDirectedGraph();

    private int waterRequirement = 0;

    // WaterRequirement darf nicht null oder 0 sein.


    public void create() throws IOException {
        System.out.println("Ein neuer Graph für die Wasserversorgung wird erstellt.");
        System.out.println("Geben Sie die Anzahl der Knoten ein, inkl. Wasserwerk und Ziel: (Ganze Zahl zwischen 2 und 1000)");
        int vertexCount = scanner.nextInt();
        if (vertexCount < 2 || vertexCount > 1000) {
            throw new IllegalArgumentException();
        }

        System.out.println("Geben Sie die Bezeichnungen aller Häuser ein: (Keine Duplikate erlaubt)");

        System.out.println("Quelle/Wasserwerk: ");
        String label = bufferedReader.readLine();
        Vertex flowSource = new Vertex(label);
        labelMap.put(label, flowSource);
        graph.addVertex(flowSource);
        graph.setSource(flowSource);

        System.out.println("Senke/Ziel: ");
        label = bufferedReader.readLine();
        Vertex flowTrap = new Vertex(label);
        labelMap.put(label, flowTrap);
        graph.addVertex(flowTrap);
        graph.setTrap(flowTrap);

        for (int i = 0; i < vertexCount - 2; i++) {
            System.out.println("Knoten " + (i + 1) + ": ");
            label = bufferedReader.readLine();
            Vertex vertex = new Vertex(label);
            labelMap.put(label, vertex);
            graph.addVertex(vertex);
        }

        System.out.println("Geben Sie die Anzahl der Kanten ein: (Ganze Zahl zwischen 1 und 1000)");
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

        System.out.println("Wie hoch ist der Wasserbedarf des Ziels? (1 - 10000)");

        waterRequirement = scanner.nextInt();

        if (waterRequirement < 1 || waterRequirement > 10000) {
            throw new IllegalArgumentException("Water Requirement out of range!");
        }

        System.out.println("Graph: ");
        System.out.println(graph);

        try {
            checkIfisZusammenhaengenderGraph(graph);
            WaterSupplyAlgorithm waterSupplyAlgorithm = new WaterSupplyAlgorithm(graph, waterRequirement);
        } catch (Exception exception) {
        }
    }


    public void runExample() {


        Vertex W = new Vertex("Wasserwerk");
        Vertex I = new Vertex("Ilona");
        Vertex B = new Vertex("Bogart");
        Vertex T = new Vertex("Thoma");
        Vertex D = new Vertex("Dogan");
        Vertex A = new Vertex("Adler");
        Vertex C = new Vertex("Club");
        Vertex H = new Vertex("Holler");
        Vertex S = new Vertex("Supermarkt");

        graph.addVertex(W);
        graph.addVertex(I);
        graph.addVertex(B);
        graph.addVertex(T);
        graph.addVertex(D);
        graph.addVertex(A);
        graph.addVertex(C);
        graph.addVertex(H);
        graph.addVertex(S);

        graph.setSource(W);
        graph.setTrap(S);

        graph.addEdge(W, T, 15);
        graph.addEdge(W, I, 6);
        graph.addEdge(W, C, 12);
        graph.addEdge(D, A, 5);
        graph.addEdge(D, B, 6);
        graph.addEdge(I, B, 3);
        graph.addEdge(I, H, 1);
        graph.addEdge(C, A, 5);
        graph.addEdge(C, H, 5);
        graph.addEdge(A, S, 10);
        graph.addEdge(B, S, 10);
        graph.addEdge(H, S, 7);
        graph.addEdge(T, D, 8);

        waterRequirement = 20;

        System.out.println("Graph: ");
        System.out.println(graph);
        WaterSupplyAlgorithm waterSupplyAlgorithm = new WaterSupplyAlgorithm(graph, waterRequirement);

    }

}
