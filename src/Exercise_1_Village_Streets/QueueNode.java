package Exercise_1_Village_Streets;

import Graph_Adj_List.Vertex;

public class QueueNode implements Comparable<QueueNode> {

    private Vertex vertex;

    private int weight;

    private QueueNode predecessor;

    public QueueNode(Vertex vertex, int weight) {
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

    public QueueNode getPredecessor() {
        return predecessor;
    }

    public void setPredecessor(QueueNode predecessor) {
        this.predecessor = predecessor;
    }

    @Override
    public int compareTo(QueueNode other) {
        if (weight < other.weight) {
            return -1;
        } else if (weight > other.weight) {
            return 1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return vertex + " (" + weight + ", " + predecessor.vertex + ")";
    }

}
