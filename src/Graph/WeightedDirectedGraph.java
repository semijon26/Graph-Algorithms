package Graph;

public class WeightedDirectedGraph extends WeightedGraph {


    @Override
    public void addEdge(Vertex source, Vertex destination, int weight) {
        // unlike in UnweightedGraph, this method doesn't create the back edge from dest to src
        if (source == destination) {
            throw new IllegalArgumentException();
        }
        Edge e = new Edge(source, destination, weight);
        Node n = new Node(e, destination);
        map.get(source).add(n);
    }


    @Override
    public void addEdge(Vertex source, Vertex destination) throws IllegalArgumentException {
        if (source == destination) {
            throw new IllegalArgumentException("Start- und Zielknoten einer Kante müssen unterschiedlich sein!");
        }
        Edge e1 = new Edge(source, destination);
        Node n1 = new Node(e1, destination);
        map.get(source).add(n1);
    }

    @Override
    public int countEdges() {
        int counter = 0;

        for (Vertex v : map.keySet()) {
            counter = counter + map.get(v).size();
        }
        return counter;
    }
}
