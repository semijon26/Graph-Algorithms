package Graph_Adj_List;

public class Node {

    Edge edge;
    Vertex destVertex;

    public Node (Edge edge, Vertex destVertex) {
        this.edge = edge;
        this.destVertex = destVertex;
    }

    public Edge getEdge() {
        return edge;
    }

    public Vertex getDestVertex() {
        return destVertex;
    }

    @Override
    public String toString() {
        return this.destVertex + " (" + this.edge.weight + ")";
    }

}
