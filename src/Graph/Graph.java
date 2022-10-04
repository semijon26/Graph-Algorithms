package Graph;

import java.util.*;

public abstract class Graph {

    protected Map<Vertex, List<Node>> map;

    protected Vertex source;
    protected Vertex trap;

    public Graph() {
        map = new HashMap<>();
    }

    public void addVertex(Vertex vertex) throws IllegalArgumentException {

        for (Vertex v : map.keySet()) {
            if (v.getLabel().equals(vertex.getLabel())) {
                throw new IllegalArgumentException("Keine Duplikate erlaubt!");
            }
        }
        map.put(vertex, new LinkedList<>());
    }

    public abstract void addEdge(Vertex source, Vertex destination) throws IllegalArgumentException;

    public int countVertices() {
        return map.keySet().size();
    }

    public abstract int countEdges();

    public boolean containsVertex(Vertex vertex) {
        return map.containsKey(vertex);
    }

    public boolean containsEdge(Edge edge) {

        for (Vertex v : map.keySet()) {
            for (Node n : map.get(v)) {
                if (edge.getSrc() == n.getEdge().getSrc() && edge.getDest() == n.getEdge().getDest()) {
                    return true;
                }
            }
        }
        return false;
    }

    public void removeEdge(Vertex src, Vertex dest, boolean removeOppositeDirection) {
        for (Vertex v : map.keySet()) {
            for (Node n : map.get(v)) {
                if (src == n.getEdge().getSrc() && dest == n.getEdge().getDest()) {
                    map.get(v).remove(n);
                }
                if (removeOppositeDirection) {
                    if (dest == n.getEdge().getSrc() && src == n.getEdge().getDest()) {
                        map.get(v).remove(n);
                    }
                }
            }
        }
    }

    public Vertex getSource() {
        return source;
    }

    public void setSource(Vertex source) {
        this.source = source;
    }

    public Vertex getTrap() {
        return trap;
    }

    public void setTrap(Vertex trap) {
        this.trap = trap;
    }

    public List<Node> getAdjVertices(Vertex vertex) {
        return map.get(vertex);
    }

    public Vertex getVertex(String label) {
        for (Vertex v : map.keySet()) {
            if (label.equals(v.getLabel())) {
                return v;
            }
        }
        throw new NoSuchElementException();
    }


    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (Vertex v : map.keySet()) {
            builder.append(v.toString()).append(":  ");
            for (Node w : map.get(v)) {
                builder.append(w.toString()).append("  ");
            }
            builder.append("\n");
        }
        return (builder.toString());
    }

    public Map<Vertex, List<Node>> getMap() {
        return map;
    }

}
