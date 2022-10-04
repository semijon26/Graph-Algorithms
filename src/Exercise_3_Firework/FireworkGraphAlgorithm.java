package Exercise_3_Firework;

import Graph.Node;
import Graph.Vertex;
import Graph.WeightedUndirectedGraph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class FireworkGraphAlgorithm {

    private final WeightedUndirectedGraph graph;
    private final PriorityQueue<DijkstraNode> prioQueue = new PriorityQueue<>();
    private final LinkedList<DijkstraNode> prioQueueHelper = new LinkedList<>();
    private final LinkedList<DijkstraNode> resultList = new LinkedList<>();


    public FireworkGraphAlgorithm(WeightedUndirectedGraph graph) {
        this.graph = graph;
        dijkstra();
        sortResultList();
        showResult();
    }


    private void dijkstra() {

        // initialize
        for (Vertex v : graph.getMap().keySet()) {
            DijkstraNode n = new DijkstraNode(v, Integer.MAX_VALUE);
            n.setPredecessor(null);
            prioQueue.add(n);
            prioQueueHelper.add(n);
            resultList.add(n);
            if (v == graph.getStart()) {
                n.setDistance(0);
            }
        }

        while (!prioQueue.isEmpty()) {

            // We have to clear the prio queue and add its elements again to
            // make it sorted again.
            prioQueue.clear();
            for (int i = 0; i < prioQueueHelper.size(); i++) {
                prioQueue.add(prioQueueHelper.get(i));
            }

            DijkstraNode current = prioQueue.poll();
            prioQueueHelper.remove(current);


            for (Node n : graph.getAdjVertices(current.getVertex())) {

                DijkstraNode neigbourRef = null;

                for (DijkstraNode dn : prioQueueHelper) {
                    if (dn.getVertex() == n.getDestVertex()) {
                        neigbourRef = dn;
                    }
                }
                if (prioQueueHelper.contains(neigbourRef)) {
                    int alternativ = current.getDistance() + n.getEdge().getWeight();

                    if (alternativ < neigbourRef.getDistance()) {
                        neigbourRef.setDistance(alternativ);
                        neigbourRef.setPredecessor(current);
                    }
                }
            }
        }
        System.out.println();
    }

    private void sortResultList() {
        PriorityQueue<DijkstraNode> temp = new PriorityQueue<>();
        for (DijkstraNode n : resultList) {
            temp.add(n);
        }
        resultList.clear();
        while (!temp.isEmpty()) {
            resultList.add(temp.poll());
        }
        resultList.removeFirst();
    }

    private void showResult() {
        ArrayList<DijkstraNode> ignitedBombs = new ArrayList<>();
        int counter = 1;
        System.out.println("Reihenfolge und Abstand von der Zündstelle: ");
        for (DijkstraNode dijkstraNode : resultList) {
            if (dijkstraNode.getDistance() < Integer.MAX_VALUE) {
                System.out.println(counter + ". " + dijkstraNode.getVertex().getLabel() + " - Abstand: " + dijkstraNode.getDistance());
                counter++;
            } else {
                ignitedBombs.add(dijkstraNode);
            }
        }
        for (DijkstraNode dijkstraNode : ignitedBombs) {
            System.out.println(dijkstraNode.getVertex().getLabel() + " ist nicht an die Zündschnur angebunden.");
        }
    }
}
