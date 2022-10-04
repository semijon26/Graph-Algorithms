package Exercise_7_Work;

import Graph.*;
import Interfaces.IGraphCreator;

import java.io.IOException;

public class WorkGraphCreator implements IGraphCreator {

    WeightedDirectedGraph graph = new WeightedDirectedGraph();

    @Override
    public void create() throws IOException {
        System.out.println("Ein neuer Graph für die Aufgabenverteilung wird erstellt.");
        System.out.println("Geben Sie die Anzahl der Arbeiter/Aufgaben an: (Ganze Zahl zwischen 2 und 1000)");
        int vertexCount = scanner.nextInt();
        if(vertexCount < 2 || vertexCount > 1000) {
            throw new IllegalArgumentException();
        }

        System.out.println("Geben Sie die Namen aller Arbeiter ein und bestätigen Sie jeweils mit Enter. (Keine Duplikate erlaubt)");

        for (int i = 0; i < vertexCount; i++) {
            System.out.println("Arbeiter " + (i+1) + ": ");
            String label = bufferedReader.readLine();
            Vertex vertex = new Vertex(label, 1);
            labelMap.put(label, vertex);
            graph.addVertex(vertex);
        }

        System.out.println("Geben Sie die Bezeichnungen aller Aufgaben ein und bestätigen Sie jeweils mit Enter. (Keine Duplikate erlaubt)");

        for (int i = 0; i < vertexCount; i++) {
            System.out.println("Aufgabe " + (i+1) + ": ");
            String label = bufferedReader.readLine();
            Vertex vertex = new Vertex(label, 2);
            labelMap.put(label, vertex);
            graph.addVertex(vertex);
        }

        System.out.println("Geben Sie die Anzahl der Kanten (Anzahl der Kompetenzen, die in der Liste stehen) ein: \n" +
                "(Jede Person mindestens 1 Kompetenz und jede Kompetenz mindestens 1 Person, die sie ausführen könnte)");
        int edgeCount = scanner.nextInt();
        if(edgeCount < vertexCount || edgeCount > 1000) {
            throw new IllegalArgumentException();
        }

        for (int i = 0; i < edgeCount; i++) {
            System.out.println("Kante " + (i+1) + ": ");

            System.out.println("von: ");
            String source = bufferedReader.readLine();
            System.out.println("nach: ");
            String dest = bufferedReader.readLine();

            graph.addEdge(labelMap.get(source), labelMap.get(dest), 1);
        }

        for (Vertex v : graph.getMap().keySet()) {
            if (v.getType() == 1) {
                if (graph.getAdjVertices(v).size() < 1) {
                    throw new IllegalArgumentException("Not every Vertex has an edge!");
                }
            }
            if (v.getLabel().equals("source") || v.getLabel().equals("trap")) {
                throw new IllegalArgumentException("Label can't be source or trap!");
            }
        }


        System.out.println("Graph: ");
        System.out.println(graph);
        addHelperSourceAndTrap();

        WorkGraphAlgorithm workGraphAlgorithm  = new WorkGraphAlgorithm(graph);

    }
    @Override
    public void runExample() {

        Vertex Maier = new Vertex("Maier", 1);
        Vertex Mueller = new Vertex("Müller", 1);
        Vertex Augst = new Vertex("Augst", 1);
        Vertex Schmidt = new Vertex("Schmidt", 1);
        Vertex Kunze = new Vertex("Kunze", 1);
        Vertex Hof = new Vertex("Hof", 1);
        Vertex Lustig = new Vertex("Lustig", 1);

        Vertex Strassen = new Vertex("Straßenbau", 2);
        Vertex Wasser = new Vertex("Wasserversorgung", 2);
        Vertex Verkehr = new Vertex("Verkehrsplanung", 2);
        Vertex Archaeologie = new Vertex("Archäologie", 2);
        Vertex Mode = new Vertex("Modeberatung", 2);
        Vertex Hochzeit = new Vertex("Hochzeitsplanung", 2);
        Vertex Wettkampf = new Vertex("Wettkampfausrichtung", 2);

        graph.addVertex(Maier);
        graph.addVertex(Mueller);
        graph.addVertex(Augst);
        graph.addVertex(Schmidt);
        graph.addVertex(Kunze);
        graph.addVertex(Hof);
        graph.addVertex(Lustig);

        graph.addVertex(Strassen);
        graph.addVertex(Wasser);
        graph.addVertex(Verkehr);
        graph.addVertex(Archaeologie);
        graph.addVertex(Mode);
        graph.addVertex(Hochzeit);
        graph.addVertex(Wettkampf);

        graph.addEdge(Maier, Strassen, 1);
        graph.addEdge(Maier, Verkehr, 1);
        graph.addEdge(Maier, Archaeologie, 1);
        graph.addEdge(Mueller, Mode, 1);
        graph.addEdge(Mueller, Hochzeit, 1);
        graph.addEdge(Augst, Strassen, 1);
        graph.addEdge(Augst, Wasser, 1);
        graph.addEdge(Schmidt, Strassen, 1);
        graph.addEdge(Schmidt, Verkehr, 1);
        graph.addEdge(Schmidt, Wettkampf, 1);
        graph.addEdge(Kunze, Archaeologie, 1);
        graph.addEdge(Kunze, Hochzeit, 1);
        graph.addEdge(Hof, Strassen, 1);
        graph.addEdge(Hof, Mode, 1);
        graph.addEdge(Lustig, Verkehr, 1);
        graph.addEdge(Lustig, Wettkampf, 1);


        System.out.println("Graph: ");
        System.out.println(graph);
        addHelperSourceAndTrap();
        WorkGraphAlgorithm workGraphAlgorithm  = new WorkGraphAlgorithm(graph);
    }


    public void addHelperSourceAndTrap () {
        Vertex source = new Vertex("source");
        Vertex trap = new Vertex("trap");

        graph.addVertex(source);
        graph.addVertex(trap);

        graph.setSource(source);
        graph.setTrap(trap);

        for (Vertex v : graph.getMap().keySet()) {
            if (v.getType() == 1) {
                graph.addEdge(source, v, 1);
            }
            else if (v.getType() == 2) {
                graph.addEdge(v, trap, 1);
            }
        }
    }
}
