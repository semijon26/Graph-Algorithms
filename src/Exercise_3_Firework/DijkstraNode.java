package Exercise_3_Firework;

import Graph.Vertex;

    public class DijkstraNode implements Comparable<DijkstraNode> {

        private Vertex vertex;

        private int distance;

        private DijkstraNode predecessor;

        public DijkstraNode(Vertex vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        public Vertex getVertex() {
            return vertex;
        }

        public void setVertex(Vertex vertex) {
            this.vertex = vertex;
        }

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        public DijkstraNode getPredecessor() {
            return predecessor;
        }

        public void setPredecessor(DijkstraNode predecessor) {
            this.predecessor = predecessor;
        }

        @Override
        public int compareTo(DijkstraNode other) {
            if (distance < other.distance) {
                return -1;
            } else if (distance > other.distance) {
                return 1;
            }
            return 0;
        }

        @Override
        public String toString() {
            return vertex + " (" + distance + ")";
        }
    }
