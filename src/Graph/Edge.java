package Graph;

public class Edge {

    Vertex src, dest;
    int weight;

    public Edge (Vertex src, Vertex dest) {
        this.src = src;
        this.dest = dest;
        weight = 0;
    }

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

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "[" + src + "->" + dest + " (" + weight + ")]";
    }
}
