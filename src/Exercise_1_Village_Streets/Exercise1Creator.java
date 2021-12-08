package Exercise_1_Village_Streets;

import Graph_Adj_List.Vertex;

public class Exercise1Creator {

    public static void create() {

        VillageGraph graph = new VillageGraph();

        Vertex a = new Vertex("a");
        Vertex b = new Vertex("b");
        Vertex c = new Vertex("c");
        Vertex d = new Vertex("d");
        Vertex e = new Vertex("e");
        Vertex f = new Vertex("f");
        Vertex g = new Vertex("g");
        Vertex h = new Vertex("h");
        Vertex i = new Vertex("i");

        graph.addVertex(a);
        graph.addVertex(b);
        graph.addVertex(c);
        graph.addVertex(d);
        graph.addVertex(e);
        graph.addVertex(f);
        graph.addVertex(g);
        graph.addVertex(h);
        graph.addVertex(i);

        graph.addEdge(a, b, 8);
        graph.addEdge(a, d, 4);
        graph.addEdge(b, e, 2);
        graph.addEdge(b, c, 7);
        graph.addEdge(b, i, 4);
        graph.addEdge(c, f, 9);
        graph.addEdge(a, g, 11);
        graph.addEdge(d, g, 8);
        graph.addEdge(e, g, 7);
        graph.addEdge(e, h, 6);
        graph.addEdge(c, i, 14);
        graph.addEdge(f, i, 10);
        graph.addEdge(g, h, 1);
        graph.addEdge(h, i, 2);

        System.out.println(graph);

        VillagePathFinder s = new VillagePathFinder(graph);


    }

}
