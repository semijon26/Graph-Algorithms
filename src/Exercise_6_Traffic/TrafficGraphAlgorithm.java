package Exercise_6_Traffic;

import Graph.BfsNode;
import Graph.Node;
import Graph.Vertex;
import Graph.WeightedDirectedGraph;

import java.util.HashSet;
import java.util.LinkedList;

public class TrafficGraphAlgorithm {


    private final WeightedDirectedGraph initialGraph;
    private WeightedDirectedGraph residualGraph = new WeightedDirectedGraph();
    private WeightedDirectedGraph resultGraph = new WeightedDirectedGraph();


    public TrafficGraphAlgorithm(WeightedDirectedGraph graph) {
        initialGraph = graph;

        fordFulkerson();
        createResultGraph();

        showResult();
    }


    private void fordFulkerson () {

        // erstellt deep copy
        createResidualGraph();

        // so lange es einen Erweiterungspfad gibt:
        while (isPath_bfs(residualGraph.getSource(), residualGraph.getTrap())) {

            int flow = Integer.MAX_VALUE;

            // Pfad speichern
            LinkedList<Vertex> path = createPath_bfs(residualGraph.getSource(), residualGraph.getTrap());

            int counter = 0;

            // flow = minimum aller Kanten
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
                if (counter == path.size()-1) {
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
                if (counter == path.size()-1) {
                    break;
                }
            }

            // und der umgekehrten kante die kapazität addieren
            for (int i = path.size()-1; i > 0; i--) {

                Vertex v = path.get(i);

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

    private void createResidualGraph() {

        for (Vertex v : initialGraph.getMap().keySet()) {
            Vertex vertex = new Vertex(v.getLabel());
            residualGraph.addVertex(vertex);
        }
        residualGraph.setSource(residualGraph.getVertex(initialGraph.getSource().getLabel()));
        residualGraph.setTrap(residualGraph.getVertex(initialGraph.getTrap().getLabel()));

        for (Vertex v : initialGraph.getMap().keySet()) {
            for (Node n : initialGraph.getMap().get(v)) {
                Vertex edgeSrc = residualGraph.getVertex(v.getLabel());
                Vertex edgeDst = residualGraph.getVertex(n.getDestVertex().getLabel());
                int edgeWeight = n.getEdge().getWeight();
                residualGraph.addEdge(edgeSrc, edgeDst, edgeWeight);
                // create back edge with weight 0:
                residualGraph.addEdge(edgeDst, edgeSrc, 0);
            }
        }

    }

    private void createResultGraph () {
        for (Vertex v : initialGraph.getMap().keySet()) {
            resultGraph.addVertex(v);
        }

        // füge resultgraph alle Nodes von initialgraph hinzu
        for (Vertex v : initialGraph.getMap().keySet()) {
            for (Node n : initialGraph.getAdjVertices(v)) {
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

    private boolean isPath_bfs (Vertex s, Vertex t) {

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
            for (Node node : residualGraph.getAdjVertices(current)){

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
            for (Node node : residualGraph.getAdjVertices(current)){

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

        return extractPathFromBfsList(traversedNodes, s, t);
    }

    private LinkedList<Vertex> extractPathFromBfsList (LinkedList<BfsNode> list, Vertex s, Vertex t) {
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


    private void showResult() { // O(n*m)
        System.out.println("Maximale Autos pro Straße: ");
        for (Vertex v : resultGraph.getMap().keySet()) {
           for (Node n : resultGraph.getAdjVertices(v)) {
               System.out.println(n.getEdge().getWeight() + " Autos von " + v.getLabel() + " nach " + n.getDestVertex().getLabel());
           }
        }
    }
}
