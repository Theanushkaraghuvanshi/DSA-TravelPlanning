import java.util.*;

public class TravelPlanner {
    private Graph graph;
    private Queue<String> updates;

    public TravelPlanner(Graph graph) {
        this.graph = graph;
        this.updates = new LinkedList<>();
    }

    public List<String> findShortestPath(String start, String end) {
        Map<Node, Integer> distances = new HashMap<>();
        Map<Node, Node> previousNodes = new HashMap<>();
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(distances::get));

        Node startNode = graph.getNode(start);
        if (startNode == null) return Collections.emptyList();

        distances.put(startNode, 0);
        for (String nodeName : graph.getNodes().keySet()) {
            Node node = graph.getNode(nodeName);
            if (!node.getName().equals(start)) {
                distances.put(node, Integer.MAX_VALUE);
            }
            queue.add(node);
        }

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            if (current.getName().equals(end)) break;

            for (Edge edge : current.getEdges()) {
                Node neighbor = edge.getDestination();
                int newDist = distances.get(current) + edge.getWeight();
                if (newDist < distances.get(neighbor)) {
                    queue.remove(neighbor);
                    distances.put(neighbor, newDist);
                    previousNodes.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
        }

        List<String> path = new ArrayList<>();
        for (Node at = graph.getNode(end); at != null; at = previousNodes.get(at)) {
            path.add(at.getName());
        }
        Collections.reverse(path);
        return path.isEmpty() ? Collections.singletonList("No path found") : path;
    }
    public void addUpdate(String update) {
        updates.add(update);
    }

    public void processUpdates() {
        while (!updates.isEmpty()) {
            String update = updates.poll();
            String[] parts = update.split(" ");
            if (parts.length == 3) {
                String source = parts[0];
                String destination = parts[1];
                int newWeight = Integer.parseInt(parts[2]);
                graph.addEdge(source, destination, newWeight);
            }
        }
    }
}
