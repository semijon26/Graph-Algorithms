package Exercise_1_Village_Streets;

import Graph_Adj_List.Edge;
import Graph_Adj_List.Vertex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class VillageCreator {

    public static void create () throws IOException {

        VillageGraph graph = new VillageGraph();

        System.out.println("Ein neuer Graph wird erstellt.");
        System.out.println("Geben Sie die Anzahl der Knoten an: (Ganze Zahl zwischen 1 und 1000)");

        Scanner scanner = new Scanner(System.in);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int vertexCount = scanner.nextInt();

        System.out.println("Geben Sie die Bezeichnungen alle Knoten ein und bestätigen Sie jeweils mit Enter. (Keine Duplikate erlaubt)");

        for (int i = 0; i < vertexCount; i++) {
            System.out.println("Knoten " + (i+1) + ": ");
            String label = bufferedReader.readLine();
            Vertex vertex = new Vertex(label);
            graph.addVertex(vertex);
        }

        System.out.println("Geben Sie die Anzahl der Kanten ein: (Ganze Zahl zwischen 1 und 1000)");
        int edgeCount = scanner.nextInt();

        for (int i = 0; i < edgeCount; i++) {
            System.out.println("Kante " + (i+1) + ": ");

            System.out.println("Startknoten: ");
            String source = bufferedReader.readLine();
            System.out.println("Zielknoten: ");
            String dest = bufferedReader.readLine();
            System.out.println("Kantengewicht: ");
            int weight = scanner.nextInt();
            Vertex s = graph.getVertex(source);
            System.out.println(s);
            Vertex d = graph.getVertex(dest);
            System.out.println(d);
            //graph.addEdge(s, d, weight);
        }

        System.out.println(graph);

        /*

        Vertex A = new Vertex("A");
        Vertex B = new Vertex("B");
        Vertex C = new Vertex("C");
        Vertex D = new Vertex("D");
        Vertex E = new Vertex("E");
        Vertex F = new Vertex("F");
        Vertex G = new Vertex("G");
        Vertex H = new Vertex("H");
        Vertex I = new Vertex("I");
        Vertex J = new Vertex("J");

        graph.addVertex(A);
        graph.addVertex(B);
        graph.addVertex(C);
        graph.addVertex(D);
        graph.addVertex(E);
        graph.addVertex(F);
        graph.addVertex(G);
        graph.addVertex(H);
        graph.addVertex(I);
        graph.addVertex(J);

        graph.addEdge(A, B, 2);
        graph.addEdge(A, E, 3);
        graph.addEdge(A, F, 3);
        graph.addEdge(B, C, 4);
        graph.addEdge(B, D, 5);
        graph.addEdge(B, E, 4);
        graph.addEdge(C, D, 3);
        graph.addEdge(C, H, 5);
        graph.addEdge(E, D, 4);
        graph.addEdge(E, G, 4);
        graph.addEdge(E, F, 2);
        graph.addEdge(F, G, 3);
        graph.addEdge(F, J, 4);
        graph.addEdge(G, D, 3);
        graph.addEdge(G, H, 2);
        graph.addEdge(G, J, 3);
        graph.addEdge(H, I, 3);
        graph.addEdge(H, J, 4);
        graph.addEdge(J, I, 2);

        System.out.println(graph);

        VillagePathFinder f = new VillagePathFinder(graph);

*/
    }

}
