package Graph;

import Graph.Vertex;

public class BfsNode {

    private Vertex vertex;
    private Vertex parent;

    public BfsNode(Vertex vertex) {
        this.vertex = vertex;
        parent = null;
    }

    public Vertex getVertex() {
        return vertex;
    }

    public Vertex getParent() {
        return parent;
    }

    public void setParent(Vertex parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return vertex + "(" + parent + ")";
    }
}
