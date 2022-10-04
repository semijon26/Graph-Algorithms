package Exercise_5_Postman;

import Exercise_1_Village_Streets.VillageGraphCreator;
import Graph.UnweightedUndirectedGraph;
import Graph.Vertex;
import Interfaces.IGraphCreator;

import java.io.IOException;

public class PostmanGraphCreator implements IGraphCreator {

    private UnweightedUndirectedGraph graph = new UnweightedUndirectedGraph();

    @Override
    public void create() throws IOException {

        System.out.println("Ein neuer Graph für das Straßennetz, wo der Postbote Einladungen verteilen soll, wird erstellt.");
        System.out.println("Geben Sie die Anzahl der Knoten ein: (Ganze Zahl zwischen 2 und 1000)");
        int vertexCount = scanner.nextInt();
        if(vertexCount < 2 || vertexCount > 1000) {
            throw new IllegalArgumentException();
        }

        System.out.println("Geben Sie die Bezeichnungen aller Knoten ein: (Keine Duplikate erlaubt)");
        for (int i = 0; i < vertexCount; i++) {
            System.out.println("Knoten " + (i+1) + ": ");
            String label = bufferedReader.readLine();
            Vertex vertex = new Vertex(label);
            labelMap.put(label, vertex);
            graph.addVertex(vertex);
        }

        System.out.println("Geben Sie die Anzahl der Kanten ein: (ganze Zahl zwischen 1 und 1000)");
        int edgeCount = scanner.nextInt();
        if(edgeCount < 1 || edgeCount > 1000) {
            throw new IllegalArgumentException();
        }

        for (int i = 0; i < edgeCount; i++) {
            System.out.println("Kante " + (i+1) + ": ");

            System.out.println("von: ");
            String source = bufferedReader.readLine();
            System.out.println("nach: ");
            String dest = bufferedReader.readLine();

            graph.addEdge(labelMap.get(source), labelMap.get(dest));
        }

        System.out.println("Graph: ");
        System.out.println(graph);

        try {
            checkIfisZusammenhaengenderGraph(graph);
            PostmanGraphAlgorithm postmanGraphAlgorithm  = new PostmanGraphAlgorithm(graph);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
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

        graph.addEdge(A, B);
        graph.addEdge(A, F);
        graph.addEdge(C, D);
        graph.addEdge(E, F);
        graph.addEdge(F, G);
        graph.addEdge(G, D);
        graph.addEdge(G, H);
        graph.addEdge(H, I);
        graph.addEdge(J, I);


        graph.addEdge(A, B);
        graph.addEdge(A, F);
        graph.addEdge(C, D);
        graph.addEdge(E, F);
        graph.addEdge(F, G);
        graph.addEdge(G, D);
        graph.addEdge(G, H);
        graph.addEdge(H, I);
        graph.addEdge(J, I);

        System.out.println("Graph: ");
        System.out.println(graph);

        PostmanGraphAlgorithm postmanGraphAlgorithm  = new PostmanGraphAlgorithm(graph);

    }
}
