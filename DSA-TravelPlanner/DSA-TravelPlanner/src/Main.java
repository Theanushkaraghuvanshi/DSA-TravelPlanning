import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();
        TravelPlanner planner = new TravelPlanner(graph);
        Scanner scanner = new Scanner(System.in);

        // Add nodes and edges to the graph
        graph.addNode("A");
        graph.addNode("B");
        graph.addNode("C");
        graph.addEdge("A", "B", 1);
        graph.addEdge("B", "C", 2);
        graph.addEdge("A", "C", 4);

        while (true) {
            System.out.println("Enter command (1: Find Path, 2: Add Update, 3: Process Updates, 4: Exit, 5: Remove Node, 6: Remove Edge, 7: Display Graph): ");
            int command = scanner.nextInt();

            switch (command) {
                case 1:
                    System.out.println("Enter start and end destinations:");
                    String start = scanner.next();
                    String end = scanner.next();
                    System.out.println("Shortest path: " + planner.findShortestPath(start, end));
                    break;

                case 2:
                    System.out.println("Enter update (format: source destination newWeight): ");
                    scanner.nextLine();
                    String update = scanner.nextLine();
                    planner.addUpdate(update);
                    break;

                case 3:
                    planner.processUpdates();
                    System.out.println("Updates processed.");
                    break;

                case 4:
                    scanner.close();
                    System.exit(0);

                case 5:
                    System.out.println("Enter node to remove:");
                    String nodeToRemove = scanner.next();
                    graph.removeNode(nodeToRemove);
                    System.out.println("Node removed.");
                    break;

                case 6:
                    System.out.println("Enter edge to remove (format: source destination):");
                    String source = scanner.next();
                    String destination = scanner.next();
                    graph.removeEdge(source, destination);
                    System.out.println("Edge removed.");
                    break;

                case 7:
                    graph.displayGraph();
                    break;

                default:
                    System.out.println("Invalid command.");
            }
        }
    }
}
