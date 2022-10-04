package Exercise_4_Wedding;

import Graph.BfsNode;
import Graph.Node;
import Graph.Vertex;
import Graph.WeightedDirectedGraph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

public class WeddingGraphAlgorithm {

    private final WeightedDirectedGraph initialGraph;
    private final WeightedDirectedGraph clearedGraph = new WeightedDirectedGraph();
    private final WeightedDirectedGraph residualGraph = new WeightedDirectedGraph();
    private final WeightedDirectedGraph resultGraph = new WeightedDirectedGraph();


    public WeddingGraphAlgorithm(WeightedDirectedGraph graph) {
        initialGraph = graph;

        createClearedGraph();

        fordFulkerson();

        createResultGraph();

        showResult();
    }


    private void fordFulkerson() {

        // erstellt deep copy
        createResidualGraph();


        // so lange es einen Erweiterungspfad gibt:
        while (isPath_bfs(residualGraph.getSource(), residualGraph.getTrap())) {

            int flow = Integer.MAX_VALUE;

            // Pfad speichern
            LinkedList<Vertex> path = createPath_bfs(residualGraph.getSource(), residualGraph.getTrap());

            int counter = 0;

            // flow = minimum aller Kanten des Paths
            for (Vertex v : path) {
                LinkedList<Node> nodes = (LinkedList<Node>) residualGraph.getAdjVertices(v);

                Vertex destVertex = path.get((path.indexOf(v)) + 1);
                for (Node n : nodes) {
                    if (n.getDestVertex() == destVertex) {
                        if (n.getEdge().getWeight() < flow) {
                            flow = n.getEdge().getWeight();
                        }
                    }
                }
                // counter, damit für die Senke nicht mehr nach dem nachbar geschaut wird.
                counter++;
                if (counter == path.size() - 1) {
                    // counter zurücksetzen
                    counter = 0;
                    break;
                }
            }

            // jeder kante des Weges die kapazität abziehen
            for (Vertex v : path) {
                LinkedList<Node> nodes = (LinkedList<Node>) residualGraph.getAdjVertices(v);
                Vertex destVertex = path.get((path.indexOf(v)) + 1);
                for (Node n : nodes) {
                    if (n.getDestVertex() == destVertex) {
                        n.getEdge().setWeight(n.getEdge().getWeight() - flow);
                    }
                }

                counter++;
                if (counter == path.size() - 1) {
                    break;
                }
            }

            // und der umgekehrten kante die kapazität addieren
            for (int i = path.size() - 1; i > 0; i--) {

                Vertex v = path.get(i);

                // nachbarn von v
                LinkedList<Node> nodes = (LinkedList<Node>) residualGraph.getAdjVertices(v);
                Vertex destVertex = path.get((path.indexOf(v)) - 1);

                for (Node n : nodes) {
                    if (n.getDestVertex() == destVertex) {
                        n.getEdge().setWeight(n.getEdge().getWeight() + flow);
                    }
                }
            }
        }
    }

    private void createClearedGraph() {

        for (Vertex v : initialGraph.getMap().keySet()) { // O(n)
            clearedGraph.addVertex(v);
        }


        for (Vertex v : initialGraph.getMap().keySet()) {
            for (Node n : initialGraph.getAdjVertices(v)) {
                for (Vertex w : initialGraph.getMap().keySet()) {
                    for (Node m : initialGraph.getAdjVertices(w)) {
                        if (m.getEdge().getDest() == n.getEdge().getSrc() && m.getEdge().getSrc() == n.getEdge().getDest()) {
                            if (n.getEdge().getSrc().getType() == 1) {
                                clearedGraph.addEdge(n.getEdge().getSrc(), n.getEdge().getDest(), 1);

                            }
                        }
                    }
                }
            }
        }

        Vertex source = initialGraph.getSource();
        Vertex trap = initialGraph.getTrap();
        clearedGraph.setSource(source);
        clearedGraph.setTrap(trap);


        for (Vertex v : clearedGraph.getMap().keySet()) {
            if (v.getType() == 1) {
                clearedGraph.addEdge(source, v, 1);
            } else if (v.getType() == 2) {
                clearedGraph.addEdge(v, trap, 1);
            }
        }

    }

    private void createResidualGraph() {

        for (Vertex v : clearedGraph.getMap().keySet()) {
            residualGraph.addVertex(new Vertex(v.getLabel()));
        }
        residualGraph.setSource(residualGraph.getVertex(clearedGraph.getSource().getLabel()));
        residualGraph.setTrap(residualGraph.getVertex(clearedGraph.getTrap().getLabel()));

        for (Vertex v : clearedGraph.getMap().keySet()) {
            for (Node n : clearedGraph.getMap().get(v)) {
                Vertex edgeSrc = residualGraph.getVertex(v.getLabel());
                Vertex edgeDst = residualGraph.getVertex(n.getDestVertex().getLabel());
                int edgeWeight = n.getEdge().getWeight();
                residualGraph.addEdge(edgeSrc, edgeDst, edgeWeight);
                // create back edge with weight 0:
                residualGraph.addEdge(edgeDst, edgeSrc, 0);
            }
        }
    }

    private boolean isPath_bfs(Vertex s, Vertex t) {

        HashSet<Vertex> visitedNodes = new HashSet<>();
        LinkedList<Vertex> queue = new LinkedList<>();
        LinkedList<BfsNode> traversedNodes = new LinkedList<>();

        visitedNodes.add(s);
        queue.addLast(s);
        traversedNodes.add(new BfsNode(s));

        //so lange noch knoten da sind:
        while (!queue.isEmpty()) {

            // setze s als erstes in die queue:
            Vertex current = queue.poll();

            // für jeden nachbarknoten vom obersten queue-knoten:
            for (Node node : residualGraph.getAdjVertices(current)) {

                // falls der Knoten nicht schon besucht wurde (und die kante existiert / > 0 ist):
                if ((!visitedNodes.contains(node.getDestVertex())) && (node.getEdge().getWeight() > 0)) {

                    //falls der Knoten der Zielknoten ist: gib true zurück
                    if (node.getDestVertex().getLabel().equals(t.getLabel())) {
                        traversedNodes.add(new BfsNode(node.getDestVertex()));
                        traversedNodes.getLast().setParent(current);
                        return true;
                    }
                    // markiere den knoten als gesehen, füge ihn Traversierungsliste hinzu und tu ihn in die Queue
                    queue.addLast(node.getDestVertex());
                    visitedNodes.add(node.getDestVertex());
                    traversedNodes.add(new BfsNode(node.getDestVertex()));
                    traversedNodes.getLast().setParent(current);
                }
            }
        }
        return false;
    }

    private LinkedList<Vertex> createPath_bfs(Vertex s, Vertex t) {
        HashSet<Vertex> visitedNodes = new HashSet<>();
        LinkedList<Vertex> queue = new LinkedList<>();
        LinkedList<BfsNode> traversedNodes = new LinkedList<>();

        visitedNodes.add(s);
        queue.addLast(s);
        traversedNodes.add(new BfsNode(s));

        //so lange noch knoten da sind:
        while (!queue.isEmpty()) {

            // setze s als erstes in die queue:
            Vertex current = queue.poll();

            // für jeden nachbarknoten vom obersten queue-knoten:
            for (Node node : residualGraph.getAdjVertices(current)) {

                // falls der Knoten nicht schon besucht wurde (und die kante existiert / > 0 ist):
                if ((!visitedNodes.contains(node.getDestVertex())) && (node.getEdge().getWeight() > 0)) {

                    //falls der Knoten der Zielknoten ist: gib true zurück
                    if (node.getDestVertex().getLabel().equals(t.getLabel())) {
                        traversedNodes.add(new BfsNode(node.getDestVertex()));
                        traversedNodes.getLast().setParent(current);
                        queue.clear();
                        break;
                    }
                    // markiere den knoten als gesehen, füge ihn Traversierungsliste hinzu und tu ihn in die Queue
                    queue.addLast(node.getDestVertex());
                    visitedNodes.add(node.getDestVertex());
                    traversedNodes.add(new BfsNode(node.getDestVertex()));
                    traversedNodes.getLast().setParent(current);
                }
            }
        }

        return extractPath(traversedNodes, s, t);
    }

    private LinkedList<Vertex> extractPath(LinkedList<BfsNode> list, Vertex s, Vertex t) {
        LinkedList<BfsNode> path = new LinkedList<>();
        BfsNode sNode = null;
        for (BfsNode bfsNode : list) {
            if (bfsNode.getVertex().equals(t)) {
                path.add(bfsNode);
            }
            if (bfsNode.getVertex().equals(s)) {
                sNode = bfsNode;
            }
        }

        while (!path.getLast().getParent().equals(s)) {
            for (BfsNode n : list) {
                if (n.getParent() != null) {
                    if (path.getLast().getParent().equals(n.getVertex())) {
                        path.add(n);
                    }
                }
            }
        }
        path.add(sNode);

        LinkedList<Vertex> returnList = new LinkedList<>();
        for (BfsNode node : path) {
            returnList.addFirst(node.getVertex());
        }
        return returnList;
    }

    private void createResultGraph() {
        for (Vertex v : clearedGraph.getMap().keySet()) {
            resultGraph.addVertex(v);
        }

        // füge resultgraph alle Nodes hinzu
        for (Vertex v : clearedGraph.getMap().keySet()) {
            for (Node n : clearedGraph.getAdjVertices(v)) {
                resultGraph.getMap().get(v).add(n);
            }
        }

        for (Vertex v : resultGraph.getMap().keySet()) {
            for (Node n : resultGraph.getAdjVertices(v)) {
                // suche nach den umgekehrten kanten und nimm das gewicht:
                for (Vertex vResidual : residualGraph.getMap().keySet()) {
                    for (Node nResidual : residualGraph.getAdjVertices(vResidual)) {
                        if ((nResidual.getEdge().getSrc().getLabel().equals(n.getEdge().getDest().getLabel())) &&
                                nResidual.getEdge().getDest().getLabel().equals(n.getEdge().getSrc().getLabel())) {
                            n.getEdge().setWeight(nResidual.getEdge().getWeight());
                        }
                    }
                }
            }
        }
    }

    private void showResult() {
        System.out.println("Ergebnis, wer wen heiratet: ");
        // Vorbereitung zur einfacheren Darstellung:
        for (Node n : resultGraph.getMap().get(resultGraph.getVertex("source"))) {
            n.getEdge().setWeight(0);
        }
        for (Vertex v : resultGraph.getMap().keySet()) {
            for (Node n : resultGraph.getAdjVertices(v)) {
                if (n.getDestVertex().getLabel().equals("trap")) {
                    n.getEdge().setWeight(0);
                }
            }
        }

        ArrayList<Vertex> personsWhoFoundSomeone = new ArrayList<>();

        for (Vertex v : resultGraph.getMap().keySet()) {
            if (v.getType() == 1) {
                for (Node n : resultGraph.getAdjVertices(v)) {
                    if (n.getEdge().getWeight() == 1) {
                        System.out.println(v.getLabel() + " heiratet " + n.getDestVertex().getLabel() + ".");
                        personsWhoFoundSomeone.add(n.getEdge().getSrc());
                        personsWhoFoundSomeone.add(n.getEdge().getDest());
                    }
                }
            }
        }

        if (personsWhoFoundSomeone.size() != clearedGraph.getMap().size() - 2) {
            for (Vertex v : clearedGraph.getMap().keySet()) {
                if (!personsWhoFoundSomeone.contains(v) && v.getType() != 0) {
                    System.out.println(v.getLabel() + " hat leider niemanden gefunden.");
                }
            }
        }
    }
}
