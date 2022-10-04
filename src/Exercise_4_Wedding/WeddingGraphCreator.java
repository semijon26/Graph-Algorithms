package Exercise_4_Wedding;

import Graph.Vertex;
import Graph.WeightedDirectedGraph;
import Interfaces.IGraphCreator;

import java.io.IOException;

public class WeddingGraphCreator implements IGraphCreator {

    WeightedDirectedGraph graph = new WeightedDirectedGraph();

    @Override
    public void create() throws IOException {
        System.out.println("Ein neuer Graph für die Hochzeitsplanung wird erstellt.");
        System.out.println("Geben Sie die Anzahl der Personen ein: (Ganze, gerade Zahl zwischen 2 und 1000)");
        int vertexCount = scanner.nextInt();
        if (vertexCount < 2 || vertexCount > 1000 || vertexCount % 2 != 0) {
            throw new IllegalArgumentException();
        }

        System.out.println("Geben Sie die Namen aller Männer ein und bestätigen Sie jeweils mit Enter. (Keine Duplikate erlaubt)");

        for (int i = 0; i < vertexCount / 2; i++) {
            System.out.println("Person " + (i + 1) + ": ");
            String label = bufferedReader.readLine();
            if (label.equals("x")) {
                throw new IllegalArgumentException("Person darf nicht x heißen!");
            }
            Vertex vertex = new Vertex(label, 1);
            labelMap.put(label, vertex);
            graph.addVertex(vertex);
        }

        System.out.println("Geben Sie die Namen aller Frauen ein und bestätigen Sie jeweils mit Enter. (Keine Duplikate erlaubt)");

        for (int i = 0; i < vertexCount / 2; i++) {
            System.out.println("Person " + (i + 1) + ": ");
            String label = bufferedReader.readLine();
            if (label.equals("x")) {
                throw new IllegalArgumentException("Person darf nicht x heißen!");
            }
            Vertex vertex = new Vertex(label, 2);
            labelMap.put(label, vertex);
            graph.addVertex(vertex);
        }

        System.out.println("Geben Sie die Personen ein, die die Person heiraten will: \n" +
                "Jede Person darf sich 1 - 3 andere Personen aussuchen. \n" +
                "Wenn nur 1 oder 2, dann bei den restlichen Personen einfach ein \"x\" eingeben");
        int edgeCount = vertexCount * 2;

        // alle Männer:
        for (Vertex v : graph.getMap().keySet()) {

            if (v.getType() == 1) {
                System.out.println(v.getLabel() + ": ");

                System.out.println("Person 1: ");
                String dest1 = bufferedReader.readLine();
                if (labelMap.get(dest1).getType() != 2) {
                    throw new IllegalArgumentException("Gleichgeschlechtliche Ehe war früher leider verboten!");
                }
                graph.addEdge(labelMap.get(v.getLabel()), labelMap.get(dest1), 1);

                System.out.println("Person 2: ");
                String dest2 = bufferedReader.readLine();
                if (dest2.equals("x")) {
                    continue;
                }
                if (labelMap.get(dest2).getType() != 2) {
                    throw new IllegalArgumentException("Gleichgeschlechtliche Ehe war früher leider verboten!");
                }
                graph.addEdge(labelMap.get(v.getLabel()), labelMap.get(dest2), 1);

                System.out.println("Person 3: ");
                String dest3 = bufferedReader.readLine();
                if (dest3.equals("x")) {
                    continue;
                }
                if (labelMap.get(dest3).getType() != 2) {
                    throw new IllegalArgumentException("Gleichgeschlechtliche Ehe war früher leider verboten!");
                }
                graph.addEdge(labelMap.get(v.getLabel()), labelMap.get(dest3), 1);
            }
        }

        // alle Frauen:
        for (Vertex v : graph.getMap().keySet()) {

            if (v.getType() == 2) {
                System.out.println(v.getLabel() + ": ");

                System.out.println("Person 1: ");
                String dest1 = bufferedReader.readLine();
                if (labelMap.get(dest1).getType() != 1) {
                    throw new IllegalArgumentException("Gleichgeschlechtliche Ehe war früher leider verboten!");
                }
                graph.addEdge(labelMap.get(v.getLabel()), labelMap.get(dest1), 1);

                System.out.println("Person 2: ");
                String dest2 = bufferedReader.readLine();
                if (dest2.equals("x")) {
                    continue;
                }
                if (labelMap.get(dest2).getType() != 1) {
                    throw new IllegalArgumentException("Gleichgeschlechtliche Ehe war früher leider verboten!");
                }
                graph.addEdge(labelMap.get(v.getLabel()), labelMap.get(dest2), 1);

                System.out.println("Person 3: ");
                String dest3 = bufferedReader.readLine();
                if (dest3.equals("x")) {
                    continue;
                }
                if (labelMap.get(dest3).getType() != 1) {
                    throw new IllegalArgumentException("Gleichgeschlechtliche Ehe war früher leider verboten!");
                }
                graph.addEdge(labelMap.get(v.getLabel()), labelMap.get(dest3), 1);
            }
        }

        for (Vertex v : graph.getMap().keySet()) {
            if (graph.getAdjVertices(v).size() < 1) {
                throw new IllegalArgumentException("Not every Vertex has an edge!");
            }
            if (v.getLabel().equals("source") || v.getLabel().equals("trap")) {
                throw new IllegalArgumentException("Label can't be source or trap!");
            }
        }

        addHelperSourceAndTrap();
        System.out.println(graph);
        System.out.println("Graph: ");
        WeddingGraphAlgorithm weddingGraphAlgorithm = new WeddingGraphAlgorithm(graph);

    }

    @Override
    public void runExample() {

        Vertex Marie = new Vertex("Marie", 2);
        Vertex Peter = new Vertex("Peter", 1);
        Vertex Susanne = new Vertex("Susanne", 2);
        Vertex Antonie = new Vertex("Antonie", 2);
        Vertex Lena = new Vertex("Lena", 2);
        Vertex Jonas = new Vertex("Jonas", 1);
        Vertex Felix = new Vertex("Felix", 1);
        Vertex Ida = new Vertex("Ida", 2);
        Vertex Mats = new Vertex("Mats", 1);
        Vertex Anna = new Vertex("Anna", 2);
        Vertex Aaron = new Vertex("Aaron", 1);
        Vertex Tom = new Vertex("Tom", 1);

        graph.addVertex(Marie);
        graph.addVertex(Peter);
        graph.addVertex(Susanne);
        graph.addVertex(Antonie);
        graph.addVertex(Lena);
        graph.addVertex(Jonas);
        graph.addVertex(Felix);
        graph.addVertex(Ida);
        graph.addVertex(Mats);
        graph.addVertex(Anna);
        graph.addVertex(Aaron);
        graph.addVertex(Tom);


        graph.addEdge(Marie, Felix, 1);
        graph.addEdge(Marie, Tom, 1);

        graph.addEdge(Peter, Lena, 1);
        graph.addEdge(Peter, Ida, 1);

        graph.addEdge(Susanne, Jonas, 1);
        graph.addEdge(Susanne, Tom, 1);
        graph.addEdge(Susanne, Peter, 1);

        graph.addEdge(Antonie, Aaron, 1);
        graph.addEdge(Antonie, Mats, 1);

        graph.addEdge(Lena, Peter, 1);
        graph.addEdge(Lena, Tom, 1);
        graph.addEdge(Lena, Jonas, 1);

        graph.addEdge(Jonas, Anna, 1);
        graph.addEdge(Jonas, Marie, 1);
        graph.addEdge(Jonas, Susanne, 1);

        graph.addEdge(Felix, Marie, 1);
        graph.addEdge(Felix, Lena, 1);
        graph.addEdge(Felix, Anna, 1);

        graph.addEdge(Ida, Tom, 1);
        graph.addEdge(Ida, Aaron, 1);
        graph.addEdge(Ida, Jonas, 1);

        graph.addEdge(Mats, Anna, 1);
        graph.addEdge(Mats, Antonie, 1);

        graph.addEdge(Anna, Peter, 1);
        graph.addEdge(Anna, Felix, 1);
        graph.addEdge(Anna, Mats, 1);

        graph.addEdge(Aaron, Susanne, 1);
        graph.addEdge(Aaron, Lena, 1);

        graph.addEdge(Tom, Anna, 1);
        graph.addEdge(Tom, Ida, 1);
        graph.addEdge(Tom, Lena, 1);


        System.out.println("Graph: ");
        System.out.println(graph);

        addHelperSourceAndTrap();

        // wir checken nicht ob zusammenhängend, da nicht nötig

        WeddingGraphAlgorithm weddingGraphAlgorithm = new WeddingGraphAlgorithm(graph);

    }


    public void addHelperSourceAndTrap() {
        Vertex source = new Vertex("source");
        Vertex trap = new Vertex("trap");

        graph.addVertex(source);
        graph.addVertex(trap);

        graph.setSource(source);
        graph.setTrap(trap);

        for (Vertex v : graph.getMap().keySet()) {
            if (v.getType() == 1) {
                graph.addEdge(source, v, 1);
            } else if (v.getType() == 2) {
                graph.addEdge(v, trap, 1);
            }
        }
    }

}