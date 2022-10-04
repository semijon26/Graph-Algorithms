package Graph;

public class PrimNode implements Comparable<PrimNode> {

    private Vertex vertex;

    private int weight;

    private PrimNode predecessor;

    public PrimNode(Vertex vertex, int weight) {
        this.vertex = vertex;
        this.weight = weight;
    }

    public Vertex getVertex() {
        return vertex;
    }

    public void setVertex(Vertex vertex) {
        this.vertex = vertex;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public PrimNode getPredecessor() {
            return predecessor;
    }

    public void setPredecessor(PrimNode predecessor) {
        this.predecessor = predecessor;
    }

    @Override
    public int compareTo(PrimNode other) {
        if (weight < other.weight) {
            return -1;
        } else if (weight > other.weight) {
            return 1;
        }
        return 0;
    }

    @Override
    public String toString() {
            return vertex + " (" + weight + ")";
    }

}
