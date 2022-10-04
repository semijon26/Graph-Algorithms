package Graph;

public class UnweightedDirectedGraph extends UnweightedGraph {

    @Override
    public void addEdge(Vertex source, Vertex destination) {
        // unlike in UnweightedGraph, this method doesn't create the back edge from dest to src
        if (source == destination) {
            throw new IllegalArgumentException();
        }
        Edge e = new Edge(source, destination);
        Node n = new Node(e, destination);
        map.get(source).add(n);
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
