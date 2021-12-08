package Exercise_1_Village_Streets;


import Graph_Adj_List.*;
import java.util.*;

public class VillageGraph {


    private Map<Vertex, List<Node>> map;

    public VillageGraph() {
        map = new HashMap<>();
    }


    public void addVertex (Vertex vertex) {
        map.put(vertex, new LinkedList<Node>());
    }

    public void addEdge (Vertex source, Vertex destination, int weight) {
        Edge e1 = new Edge(source, destination, weight);
        Edge e2 = new Edge(destination, source, weight);
        Node n1 = new Node(e1, destination);
        Node n2 = new Node(e2, source);
        map.get(source).add(n1);
        map.get(destination).add(n2);
    }

    public int countVertices() {
        return map.keySet().size();
    }

    public boolean containsVertex (Vertex s) {
        if (map.containsKey(s)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean containsEdge (Vertex src, Vertex dest) {
        for (Vertex v : map.keySet()) {
            for (Node n : map.get(v)) {
                if(src == n.getEdge().getSrc() && dest == n.getEdge().getDest()) {
                    return true;
                }
            }
        }
        return false;
    }

    public List<Node> getAdjVertices(Vertex vertex) {
        return map.get(vertex);
    }

    public String toString()
    {
        StringBuilder builder = new StringBuilder();

        for (Vertex v : map.keySet()) {
            builder.append(v.toString() + ": ");
            for (Node w : map.get(v)) {
                builder.append(w.toString() + " ");
            }
            builder.append("\n");
        }
        return (builder.toString());
    }

    public Vertex getVertex (String label) {
        for (Vertex v : map.keySet()) {
            if (label == v.label) {
                return v;
            }
        }
        //throw new NoSuchElementException();
    }

    public Map<Vertex, List<Node>> getMap() {
        return map;
    }
}