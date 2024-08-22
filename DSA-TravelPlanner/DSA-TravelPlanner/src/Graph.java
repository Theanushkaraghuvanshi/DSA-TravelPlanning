import java.util.HashMap;
import java.util.Map;

public class Graph {
    private Map<String, Node> nodes;

    public Graph() {
        nodes = new HashMap<>();
    }

    public void addNode(String name) {
        nodes.putIfAbsent(name, new Node(name));
    }

    public void addEdge(String source, String destination, int weight) {
        Node s = nodes.get(source);
        Node d = nodes.get(destination);
        if (s != null && d != null) {
            s.getEdges().add(new Edge(d, weight));
        }
    }

    public Node getNode(String name) {
        return nodes.get(name);
    }
    public Map<String, Node> getNodes() {
        return nodes;
    }
    public void removeNode(String name) {
        nodes.remove(name);
        for (Node node : nodes.values()) {
            node.getEdges().removeIf(edge -> edge.getDestination().getName().equals(name));
        }
    }

    public void removeEdge(String source, String destination) {
        Node s = nodes.get(source);
        if (s != null) {
            s.getEdges().removeIf(edge -> edge.getDestination().getName().equals(destination));
        }
    }
    public void displayGraph() {
        for (Map.Entry<String, Node> entry : nodes.entrySet()) {
            System.out.print(entry.getKey() + " -> ");
            for (Edge edge : entry.getValue().getEdges()) {
                System.out.print(edge.getDestination().getName() + "(" + edge.getWeight() + ") ");
            }
            System.out.println();
        }
    }

}
