package Exercise_6_Traffic;

import Graph.Vertex;
import Graph.WeightedDirectedGraph;
import Interfaces.IGraphCreator;

import java.io.IOException;

public class TrafficGraphCreator implements IGraphCreator {

    private final WeightedDirectedGraph graph = new WeightedDirectedGraph();

    @Override
    public void create() throws IOException {
        System.out.println("Ein neuer Graph für die Verkehrsplanung wird erstellt.");
        System.out.println("Geben Sie die Anzahl der Knoten, bestehend aus Ampeln, Start- und Endpunkt, ein: (Ganze Zahl zwischen 2 und 1000)");
        int vertexCount = scanner.nextInt();
        if(vertexCount < 2 || vertexCount > 1000) {
            throw new IllegalArgumentException();
        }

        System.out.println("Geben Sie die Bezeichnungen aller Knoten ein: (Keine Duplikate erlaubt)");

        System.out.println("Startpunkt: ");
        String label = bufferedReader.readLine();
        Vertex flowSource = new Vertex(label);
        labelMap.put(label, flowSource);
        graph.addVertex(flowSource);
        graph.setSource(flowSource);

        System.out.println("Endpunkt: ");
        label = bufferedReader.readLine();
        Vertex flowTrap = new Vertex(label);
        labelMap.put(label, flowTrap);
        graph.addVertex(flowTrap);
        graph.setTrap(flowTrap);

        for (int i = 0; i < vertexCount-2; i++) {
            System.out.println("Knoten " + (i+1) + ": ");
            label = bufferedReader.readLine();
            Vertex vertex = new Vertex(label);
            labelMap.put(label, vertex);
            graph.addVertex(vertex);
        }

        System.out.println("Geben Sie die Anzahl der Kanten ein: (Ganze Zahl zwischen 1 und 1000)");
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
            System.out.println("Kantengewicht: ");
            int weight = scanner.nextInt();
            if(weight < 1 || weight > 10000) {
                throw new IllegalArgumentException();
            }

            graph.addEdge(labelMap.get(source), labelMap.get(dest), weight);
        }

        System.out.println("Graph: ");
        System.out.println(graph);

        try {
            checkIfisZusammenhaengenderGraph(graph);
            TrafficGraphAlgorithm trafficGraphAlgorithm  = new TrafficGraphAlgorithm(graph);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void runExample() {

        Vertex s = new Vertex("Autobahn");
        Vertex a = new Vertex("Ampel_1");
        Vertex b = new Vertex("Ampel_2");
        Vertex c = new Vertex("Ampel_3");
        Vertex d = new Vertex("Ampel_4");
        Vertex t = new Vertex("Parkplatz");

        graph.addVertex(s);
        graph.addVertex(a);
        graph.addVertex(b);
        graph.addVertex(c);
        graph.addVertex(d);
        graph.addVertex(t);

        graph.setSource(s);
        graph.setTrap(t);

        graph.addEdge(s, a, 14);
        graph.addEdge(s, b, 16);
        graph.addEdge(b, a, 6);
        graph.addEdge(a, c, 15);
        graph.addEdge(b, d,15);
        graph.addEdge(c, b, 4);
        graph.addEdge(c, d, 7);
        graph.addEdge(c, t, 10);
        graph.addEdge(d, t, 17);

        System.out.println("Graph: ");
        System.out.println(graph);

        TrafficGraphAlgorithm trafficGraphAlgorithm  = new TrafficGraphAlgorithm(graph);

    }
}
