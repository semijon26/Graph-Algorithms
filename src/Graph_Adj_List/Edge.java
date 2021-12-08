package Graph_Adj_List;

public class Edge {

    Vertex src, dest;
    int weight;

    public Edge (Vertex src, Vertex dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    public Vertex getSrc() {
        return src;
    }

    public Vertex getDest() {
        return dest;
    }

    public int getWeight() {
        return weight;
    }
}
