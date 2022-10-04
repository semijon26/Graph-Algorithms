package Exercise_3_Firework;

import Graph.Vertex;
import Graph.WeightedUndirectedGraph;
import Interfaces.IGraphCreator;

import java.io.IOException;

public class FireworkGraphCreator implements IGraphCreator {

    WeightedUndirectedGraph graph = new WeightedUndirectedGraph();

    public void create() throws IOException {
        System.out.println("Ein neuer Graph für die Feuerwerks-Choreographie wird erstellt.");
        System.out.println("Geben Sie die Anzahl der Bomben ein, inkl. Stelle, wo gezündet wird: (Ganze Zahl zwischen 2 und 1000)");
        int vertexCount = scanner.nextInt();
        if (vertexCount < 2 || vertexCount > 1000) {
            throw new IllegalArgumentException();
        }

        System.out.println("Geben Sie die Bezeichnungen aller Bomben ein: (Keine Duplikate erlaubt)");

        System.out.println("Zündstelle: ");
        String label = bufferedReader.readLine();
        Vertex src = new Vertex(label);
        labelMap.put(label, src);
        graph.addVertex(src);
        graph.setStart(src);

        for (int i = 0; i < vertexCount - 1; i++) {
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

        if (graph.getAdjVertices(graph.getStart()).isEmpty()) {
            throw new IllegalArgumentException("Startknoten hat keine Verbindungen zu anderen Knoten!");
        }

        System.out.println("Graph: ");
        System.out.println(graph);
        FireworkGraphAlgorithm algorithm = new FireworkGraphAlgorithm(graph);

    }

    public void runExample() {

        Vertex s = new Vertex("Zündholz");
        Vertex a = new Vertex("Bombe_A");
        Vertex b = new Vertex("Bombe_B");
        Vertex c = new Vertex("Bombe_C");
        Vertex d = new Vertex("Bombe_D");
        Vertex e = new Vertex("Bombe_E");
        Vertex f = new Vertex("Bombe_F");
        Vertex g = new Vertex("Bombe_G");
        Vertex h = new Vertex("Bombe_H");


        graph.addVertex(s);
        graph.addVertex(a);
        graph.addVertex(b);
        graph.addVertex(c);
        graph.addVertex(d);
        graph.addVertex(e);
        graph.addVertex(f);
        graph.addVertex(g);
        graph.addVertex(h);

        graph.setStart(s);

        graph.addEdge(s, a, 5);
        graph.addEdge(a, b, 21);
        graph.addEdge(b, c, 4);
        graph.addEdge(a, c, 4);
        graph.addEdge(a, e, 22);
        graph.addEdge(c, e, 9);
        graph.addEdge(e, d, 12);
        graph.addEdge(e, h, 7);
        graph.addEdge(d, f, 3);
        graph.addEdge(d, g, 8);
        graph.addEdge(f, g, 15);
        graph.addEdge(f, h, 13);

        System.out.println("Graph: ");
        System.out.println(graph);
        FireworkGraphAlgorithm algorithm = new FireworkGraphAlgorithm(graph);

    }
}
