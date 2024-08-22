import java.util.ArrayList;
import java.util.List;

public class Node {
    private String name;
    private List<Edge> edges;

    public Node(String name) {
        this.name = name;
        this.edges = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Edge> getEdges() {
        return edges;
    }
}
