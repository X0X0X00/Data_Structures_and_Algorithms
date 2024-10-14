import java.util.*;

public class StreetMap {

	public static void main(String[] args) {
		// handle command line arguments
		if (args.length <= 1) {
			throw new IllegalArgumentException("Usage: java StreetMap map.txt [--show] [--directions startIntersection endIntersection]");
		}
		boolean showMap = false;
		boolean directions = false;
		// if the args command contains --show / --directions and pop the corresponding args
		String startIntersection = null;
		String endIntersection = null;
		for (int i = 0; i < args.length; i++) {
			if (args[i].equals("--show")) {
				showMap = true;
			} else if (args[i].equals("--directions")) {
				directions = true;
				if (i + 2 < args.length) {
					startIntersection = args[i + 1];
					endIntersection = args[i + 2];
				} else {
					throw new IllegalArgumentException("Invalid --directions arguments");
				}
			}
		}
		Graph graph = new Graph(args[0]);
		// if the command contains --show, show the map
		if (showMap) {
			// if the command contains --directions, find the shortest path and show
			if (directions) {
				List<Node> path = dijkstra(graph, startIntersection, endIntersection);
				// print the path to terminal, and calculate the total distance
				double totalDistance = 0;
				for (int i = 0; i < path.size() - 1; i++) {
					Node node1 = path.get(i);
					Node node2 = path.get(i + 1);
					double dist = calculateDistance(node1, node2);
					totalDistance += dist;
					// print node id
					System.out.println(node1.getID());
				}
				// print the last node id
				System.out.println(path.get(path.size() - 1).getID());
				System.out.println("Total distance is " + totalDistance + " miles.");


				GraphWindow window = new GraphWindow(graph, path);
				window.setVisible(true);
			}
			else {
				GraphWindow window = new GraphWindow(graph);
				window.setVisible(true);
			}
		}
		else {
			if(directions) {
				List<Node> path = dijkstra(graph, startIntersection, endIntersection);
				// print the path to terminal, and calculate the total distance
				double totalDistance = 0;
				for (int i = 0; i < path.size() - 1; i++) {
					Node node1 = path.get(i);
					Node node2 = path.get(i + 1);
					double dist = calculateDistance(node1, node2);
					totalDistance += dist;
					// print node id
					System.out.println(node1.getID());
				}
				// print the last node id
				System.out.println(path.get(path.size() - 1).getID());
				System.out.println("Total distance is " + totalDistance + " miles.");
			}
		}


	}

	public static List<Node> dijkstra(Graph graph, String start, String end) {
		Node[] nodes = graph.getNodes();
		Edge[] edges = graph.getEdges();
		// create the distance array, key is Node id, value is distance
		Map<String, Double> distance = new HashMap<String, Double>();
		// create the previous array
		Map<String, String> previous = new HashMap<String, String>();
		// create the visited set
		Set<String> visited = new HashSet<String>();
		// initialize the distance array and unvisited array
		for (Node node : nodes) {
			distance.put(node.getID(), Double.MAX_VALUE);  // 初始化距离为无穷大
			previous.put(node.getID(), null);  // 初始化前驱节点为null
		}

		// implement Dijkstra's algorithm
		// set the distance of the start node to 0
		distance.put(start, 0.0);
		System.out.println("There are " + nodes.length + " nodes and " + edges.length + " edges.");
		// while there are unvisited nodes
		while (!visited.contains(end)) {
//			System.out.println("Visited " + visited.size() + " nodes.");
			// 获取当前最近的节点
			String current = getClosestNode(distance, visited);
//			System.out.println("Current node: " + current);
			visited.add(current);  // 将当前节点标记为已访问

			for (Edge edge : edges) {
				// 如果当前节点是边的起点
				if (edge.getNode1().equals(current)) {
					double newDistance = distance.get(current) + edge.getWeight();
					String target = edge.getNode2();

					if (!visited.contains(target) && newDistance < distance.get(target)) {
						distance.put(target, newDistance);  // 更新距离
						previous.put(target, current);  // 更新前驱节点
					}
				}
				// 如果当前节点是边的终点
				else if (edge.getNode2().equals(current)) {
					double newDistance = distance.get(current) + edge.getWeight();
					String source = edge.getNode1();
					if (!visited.contains(source) && newDistance < distance.get(source)) {
						distance.put(source, newDistance);  // 更新距离
						previous.put(source, current);  // 更新前驱节点
					}
				}

			}
		}
		// if the distance of the end node is infinity, then there is no path
		List<Node> path = new ArrayList<>();
		if (distance.get(end) == Double.POSITIVE_INFINITY) {
			System.out.println("No path");
		}
		else {
			// 构建最短路径
			String current = end;
			while (current != null) {
				path.add(0, graph.getNode(current));
				current = previous.get(current);
			}
		}
		return path;
	}

	// 获取未访问节点中距离最近的节点
	private static String getClosestNode(Map<String, Double> distance, Set<String> visited) {
		double minDistance = Double.MAX_VALUE;
		String closestNode = null;

		for (Map.Entry<String, Double> entry : distance.entrySet()) {
			String node = entry.getKey();
			double dist = entry.getValue();

			if (!visited.contains(node) && dist < minDistance) {
				minDistance = dist;
				closestNode = node;
			}
		}

		return closestNode;
	}

	public static double calculateDistance(Node node1, Node node2) {
		// calculate the distance between two nodes, convert the latitude and longitude to miles
		double earth_radius = 3958.8;
		double dLat = Math.toRadians(node2.getLatitude() - node1.getLatitude());
		double dLon = Math.toRadians(node2.getLongitude() - node1.getLongitude());
		double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
				Math.cos(Math.toRadians(node1.getLatitude())) * Math.cos(Math.toRadians(node2.getLatitude())) *
						Math.sin(dLon / 2) * Math.sin(dLon / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		return earth_radius * c;
	}
}
