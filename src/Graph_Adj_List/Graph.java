package Graph_Adj_List;

import java.util.*;

public abstract class Graph<T> {

    private Map<T, List<T>> map;

    public Graph() {
        map = new HashMap<>();
    }


    public void addVertex (T s) {
        map.put(s, new LinkedList<T>());
    }

    public void addEdge (T source, T destination) {

        boolean bidirectional = true;

        if(!map.containsKey(source)) {
            addVertex(source);
        }
        if (!map.containsKey(destination)) {
            addVertex(destination);
        }
        if (bidirectional == true) {
            map.get(source).add(destination);
            map.get(destination).add(source);
        }
    }

    public int countVertices() {
        return map.keySet().size();
    }

    public int countEdges()
    {
        boolean bidirection = true;
        int count = 0;
        for (T v : map.keySet()) {
            count = count + map.get(v).size();
        }
        if (bidirection == true) {
            count = count / 2;
        }
        return count;
    }

    public boolean containsVertex (T s) {
        if (map.containsKey(s)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean containsEdge (T src, T dest) {
        if (map.get(src).contains(dest)) {
            return true;
        } else {
            return false;
        }
    }



    public List<T> getAdjVertices(T s) {
        return map.get(s);
    }

    public String toString()
    {
        StringBuilder builder = new StringBuilder();

        for (T v : map.keySet()) {
            builder.append(v.toString() + ": ");
            for (T w : map.get(v)) {
                builder.append(w.toString() + " ");
            }
            builder.append("\n");
        }
        return (builder.toString());
    }
}
