package Exercise_1_Village_Streets;

import Graph.Node;
import Graph.PrimNode;
import Graph.Vertex;
import Graph.WeightedUndirectedGraph;

import java.util.LinkedList;
import java.util.PriorityQueue;

public class VillagePathAlgorithm {

    private final WeightedUndirectedGraph initialGraph;
    private final WeightedUndirectedGraph resultGraph = new WeightedUndirectedGraph();
    private final PriorityQueue<PrimNode> prioQueue = new PriorityQueue<>();
    private final LinkedList<PrimNode> prioQueueHelper = new LinkedList<>();
    private final LinkedList<PrimNode> resultList = new LinkedList<>();


    public VillagePathAlgorithm(WeightedUndirectedGraph graph) {
        this.initialGraph = graph;
        prim();
        makeResultGraph();
        System.out.println("Straßen müssen auf folgenden Strecken gebaut werden: ");
        System.out.println(resultGraph);
    }


    private void prim() {

        for (Vertex v : initialGraph.getMap().keySet()) {
            PrimNode n = new PrimNode(v, Integer.MAX_VALUE);
            n.setPredecessor(null);
            prioQueue.add(n);
            prioQueueHelper.add(n);
            resultGraph.addVertex(v);
            resultList.add(n);
        }

        prioQueue.peek().setWeight(0);


        while (!prioQueue.isEmpty()) {

            // We have to clear the prio queue and add its elements again to
            // make it sorted again.
            prioQueue.clear();
            prioQueue.addAll(prioQueueHelper);

            PrimNode current = prioQueue.poll();
            prioQueueHelper.remove(current);

            for (Node neighbour : initialGraph.getAdjVertices(current.getVertex())) {

                PrimNode neigbourRef = null;

                for (PrimNode primNode : prioQueueHelper) {
                    if (primNode.getVertex() == neighbour.getDestVertex()) {
                        neigbourRef = primNode;
                    }
                }
                if (prioQueueHelper.contains(neigbourRef) && (neighbour.getEdge().getWeight() < neigbourRef.getWeight())) {
                    neigbourRef.setPredecessor(current);
                    neigbourRef.setWeight(neighbour.getEdge().getWeight());

                }
            }
        }
        System.out.println();
    }

    private void makeResultGraph() {
        for (PrimNode n : resultList) { // O(n)
            if (n.getPredecessor() != null) {
                resultGraph.addEdge(n.getPredecessor().getVertex(), n.getVertex(), n.getWeight());
            }
        }
    }
}
