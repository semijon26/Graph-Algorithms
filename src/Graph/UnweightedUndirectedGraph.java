package Graph;

public class UnweightedUndirectedGraph extends UnweightedGraph{

    @Override
    public void addEdge(Vertex source, Vertex destination) throws IllegalArgumentException {
        if (source == destination) {
            throw new IllegalArgumentException("Start- und Zielknoten einer Kante müssen unterschiedlich sein!");
        }
        Edge e1 = new Edge(source, destination);
        Edge e2 = new Edge(destination, source);
        Node n1 = new Node(e1, destination);
        Node n2 = new Node(e2, source);
        map.get(source).add(n1);
        map.get(destination).add(n2);
    }

    @Override
    public int countEdges() {
        int counter = 0;

        for (Vertex v : map.keySet()) {
            counter = counter + map.get(v).size();
        }
        return counter/2;
    }
}
