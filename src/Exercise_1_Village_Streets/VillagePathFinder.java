package Exercise_1_Village_Streets;

import Graph_Adj_List.Node;
import Graph_Adj_List.Vertex;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

// Diese Klasse erstellt den MST des übergebenen Graphen
public class VillagePathFinder {

    VillageGraph graph;

    PriorityQueue<QueueNode> prioQueue = new PriorityQueue<>();
    ArrayList<QueueNode> prioQueueHelper = new ArrayList<>();



    public VillagePathFinder(VillageGraph graph) {

        this.graph = graph;
        prim();

    }


    public void prim () {

        int iterationCounter = 0;

        for (Vertex v : graph.getMap().keySet()) {
            QueueNode n = new QueueNode(v, 999999999);
            n.setPredecessor(null);
            prioQueue.add(n);
            prioQueueHelper.add(n);
        }

        prioQueue.peek().setWeight(0);


        while (!prioQueue.isEmpty()) {
            iterationCounter++;

            System.out.println(iterationCounter + ". Iteration: " + prioQueue);

            // We have to clear the prio queue and add its elements again to
            // make it sorted again.
            prioQueue.clear();
            for (int i = 0; i < prioQueueHelper.size(); i++) {
                prioQueue.add(prioQueueHelper.get(i));
            }

            QueueNode u = prioQueue.poll();
            prioQueueHelper.remove(u);

            List<Node> adjList = graph.getAdjVertices(u.getVertex());


            for (Node v : adjList) {

                QueueNode vReference = null;

                for (int i = 0; i < prioQueueHelper.size(); i++) {
                    if (prioQueueHelper.get(i).getVertex() == v.getDestVertex()) {
                        vReference = prioQueueHelper.get(i);
                    }
                }
                if (prioQueueHelper.contains(vReference) && (v.getEdge().getWeight() < vReference.getWeight())) {
                    vReference.setPredecessor(u);
                    vReference.setWeight(v.getEdge().getWeight());
                }
            }
        }

    }



}
