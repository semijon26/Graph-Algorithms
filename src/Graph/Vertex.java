package Graph;

public class Vertex {

    private final String label;

    // type 0 = group A (worker)    type 1 = group B (task)    type 0 = no group (-> source, trap)
    private int type = 0;

    public Vertex(String label) {
        this.label = label;
    }

    public Vertex(String label, int type) {
        if (!label.equals("")) {
            this.label = label;
        } else {
            throw new IllegalArgumentException("Label can't be empty!");
        }
        if (type > 2 || type < 1) {
            throw new IllegalArgumentException("Type must be either 1 or 2!");
        }
        this.type = type;
    }

    public int getType() {
        return type;
    }

    @Override
    public String toString() {
        return label;
    }

    public String getLabel() {
        return label;
    }
}
