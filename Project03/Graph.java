/// Zhenhao Zhang zzh133


import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Graph {
	public List<Node> nodes;
	public List<Edge> edges;

	public Map<String, Node> nodeMap;
	public Map<String, List<String>> neighbors;

	public Graph(List<Node> nodes, List<Edge> edges, Map<String, Node> nodeMap) {
		this.nodes = nodes;
		this.edges = edges;
		this.nodeMap = nodeMap;
		neighbors = new HashMap<>();
		for (Edge edge : edges) {
			neighbors.putIfAbsent(edge.node1, new LinkedList<String>());
			neighbors.get(edge.node1).add(edge.node2);
			neighbors.putIfAbsent(edge.node2, new LinkedList<String>());
			neighbors.get(edge.node2).add(edge.node1);
		}

	}

	public Edge getEdge(String id1, String id2) {
		for(Edge edge : edges) {
			if ((edge.node1.equals(id1) && edge.node2.equals(id2)) || (edge.node1.equals(id2) && edge.node2.equals(id1))) {
				return edge;
			}
		}
		return null;
	}


}
