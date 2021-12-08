package Graph_Adj_List;

public class Vertex {

    public String label;

    public Vertex (String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }

    public String getLabel() {
        return label;
    }
}
