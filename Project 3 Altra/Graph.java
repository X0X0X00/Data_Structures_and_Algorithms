import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

// Graph class: constructs a graph using Node and Edge
public class Graph {
	private Node[] nodes;
	private Edge[] edges;

	private Map<String, Node> id2node;

	private Map<String, Edge> id2edge;

	private Map<String, List<Edge>> adjacencyList;

	private Map<String, Map<String, Edge>> adjacencyMatrix;

	// Constructor
	public Graph() {
		this.nodes = null;
		this.edges = null;
		this.initMaps();
	}

	public Graph(String filename) {
		this();
		this.from_file(filename);
	}

	public Graph(Node[] nodes, Edge[] edges) {
		this.nodes = nodes;
		this.edges = edges;
		this.initMaps();
	}

	public void initMaps() {
		this.id2node = new HashMap<>();
		this.id2edge = new HashMap<>();
		this.adjacencyList = new HashMap<>();
		this.adjacencyMatrix = new HashMap<>();
	}

	public void from_file(String filename) {
		// read from the file
		try {
			FileReader fr = new FileReader(filename);
			BufferedReader br = new BufferedReader(fr);
			String line;
			LinkedList<Node> nodes = new LinkedList<Node>();
			LinkedList<Edge> edges = new LinkedList<Edge>();
			while ((line = br.readLine()) != null) {
				// split with \t
				String[] parts = line.split("\t");
				if (parts[0].equals("i")) {
					// create a new node
					String id = parts[1];
					double latitude = Double.parseDouble(parts[2]);
					double longitude = Double.parseDouble(parts[3]);
					Node node = new Node(id, latitude, longitude);
					nodes.add(node);
					id2node.put(id, node);
				}
				else if (parts[0].equals("r")) {
					// create a new edge
					String id = parts[1];
					String node1 = parts[2];
					String node2 = parts[3];
					Edge edge = new Edge(id, node1, node2, StreetMap.calculateDistance(id2node.get(node1), id2node.get(node2)));
					edges.add(edge);
					id2edge.put(id, edge);
					// add the edge to the adjacency list, undirected graph
					if (!adjacencyList.containsKey(node1)) {
						adjacencyList.put(node1, new LinkedList<Edge>());
					}
					adjacencyList.get(node1).add(edge);
					if (!adjacencyList.containsKey(node2)) {
						adjacencyList.put(node2, new LinkedList<Edge>());
					}
					adjacencyList.get(node2).add(edge);
					// add the edge to the adjacency matrix, undirected graph
					if (!adjacencyMatrix.containsKey(node1)) {
						adjacencyMatrix.put(node1, new HashMap<String, Edge>());
					}
					adjacencyMatrix.get(node1).put(node2, edge);
					if (!adjacencyMatrix.containsKey(node2)) {
						adjacencyMatrix.put(node2, new HashMap<String, Edge>());
					}
					adjacencyMatrix.get(node2).put(node1, edge);
				}
				else {
					System.out.println("Error: unknown line type " + parts[0]);
				}
			}
			br.close();
			fr.close();
			this.nodes = nodes.toArray(new Node[0]);
			this.edges = edges.toArray(new Edge[0]);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}


	public Node getNode(String id) {
		return id2node.getOrDefault(id, null);
	}

	public List<Edge> getNeighbors(String id) {
		LinkedList<Edge> neighbors = new LinkedList<Edge>();
		for (Edge edge : edges) {
			if (edge.getNode1().equals(id)) {
				neighbors.add(edge);
			}
		}
		return neighbors;
	}

	public Node[] getNodes() {
		return nodes;
	}
	public Edge[] getEdges() {
		return edges;
	}
}
