package Graph;

//bidirectional
public abstract class WeightedGraph extends Graph {

    // overridden because addEdge from class Graph doesn't have weight parameter.
    public void addEdge(Vertex source, Vertex destination, int weight) {
        if (source == destination) {
            throw new IllegalArgumentException("Start- und Zielknoten einer Kante müssen unterschiedlich sein!");
        }
        Edge e1 = new Edge(source, destination, weight);
        Edge e2 = new Edge(destination, source, weight);
        Node n1 = new Node(e1, destination);
        Node n2 = new Node(e2, source);
        map.get(source).add(n1);
        map.get(destination).add(n2);
    }

}
