/// Zhenhao Zhang zzh133


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class StreetMap {
	private static final double EARTH_RADIUS = 3959; // miles
	public static List<Node> nodes = new ArrayList<>();
	public static List<Edge> edges = new ArrayList<>();

	public static int nodeNum;
	public static int edgeNum;
	public static Graph graph;
	public static Map<String, Node> nodeMap = new HashMap<>();
	public static Map<String, List<String>> neighbors = new HashMap<>();

	public static double[] finalDistance;
	public static double INFINITY = Double.MAX_VALUE;
	public static void main(String[] args) {
		// args[0]是地图文件名
		// 读取文件，构建图
		String filename = args[0];
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			String str = null;
			while ((str = reader.readLine()) != null) {
				String[] word = str.split("\t");
				String identifier = word[0];
				if (identifier.equals("i")) {
					Node node = new Node(word[1], Double.parseDouble(word[2]), Double.parseDouble(word[3]));
					nodes.add(node);
					nodeMap.put(word[1], node);
				}
				if (identifier.equals("r")) {
					String node1 = word[2];
					String node2 = word[3];
					Edge edge = new Edge(word[1], word[2], word[3], dist(
							nodeMap.get(node1).latitude, nodeMap.get(node1).longitude,
							nodeMap.get(node2).latitude, nodeMap.get(node2).longitude)
					);
					edges.add(edge);
				}
			}
			reader.close();
		} catch (IOException e) {
			throw new RuntimeException("Error reading file " + filename, e);
		}

		graph = new Graph(nodes, edges, nodeMap);
		nodeNum = nodes.size();
		edgeNum = edges.size();
		// args[1]是--show或者--directions
		// 如果只有--show
		if (args[1].equals("--show") && args.length == 2) {
			MyFrame g = new MyFrame(graph, new ArrayList<Node>(), false);
			return;
		}
		// 如果只有--directions
		if (args[1].equals("--directions") && args.length == 4) {
			printPath(find(graph, args[2], args[3]));
		}

		// 也可能是同时有--show --directions startIntersection endIntersection
		if (args[1].equals("--show") && args[2].equals("--directions")) {
			List<Node> path = find(graph, args[3], args[4]);
			MyFrame g = new MyFrame(graph, path, true);
			printPath(path);

		}
	}

	public static double dist(double lat1, double lon1, double lat2, double lon2) {
		double lat_delta = Math.toRadians(lat2 - lat1);
		double lon_delta = Math.toRadians(lon2 - lon1);
		double a = Math.sin(lat_delta / 2) * Math.sin(lat_delta / 2) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.sin(lon_delta / 2) * Math.sin(lon_delta / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		return EARTH_RADIUS * c;
	}

	// 通过id找到node在nodes数组中的下标
	public static int fid(String id) {
		for (int i = 0; i < nodeNum; i++) {
			if (nodes.get(i).id.equals(id)) {
				return i;
			}
		}
		return -1;
	}

	public static List<Node> find(Graph g, String startIntersection, String endIntersection) {
		double[] distance = new double[nodeNum];
		boolean[] visited = new boolean[nodeNum];
		// 初始化距离和visited数组
		for (int i = 0; i < nodeNum; i++) {
			distance[i] = INFINITY;
			visited[i] = false;
		}

		// 将起始节点的距离设为0
		distance[fid(startIntersection)] = 0;
		while (!visited[fid(endIntersection)]) {
			double min = INFINITY;
			String t = null; // 下一个要访问的节点
			// 找到距离起始节点最近的节点
			for (int i = 0; i < nodeNum; i++) {
				if (visited[i]) {
					continue;
				}
				if (distance[i] < min) {
					min = distance[i];
					t = nodes.get(i).id;
				}
			}
			visited[fid(t)] = true;
			for (String next : g.neighbors.get(t)) {
				// 如果next已经被访问过，跳过
				if (visited[fid(next)]) {
					continue;
				}
				// 一条路径，其中一个顶点是current，另一个顶点是next
				Edge nextEdge = g.getEdge(t, next); // 从current到next的边，或者从next到current的边
				double dist = distance[fid(t)] + nextEdge.weight;
				// 如果新的距离比原来的距离小，更新距离和前驱节点
				if (dist < distance[fid(next)]) {
					distance[fid(next)] = dist; // 更新距离
					nodeMap.get(next).prev = nodeMap.get(t); // 更新前驱节点，用于构建最短路径
				}
			}
		}

		String current = endIntersection;
		// 使用栈来存储路径，因为栈是先进后出的，所以最后需要将栈中的元素取出来即为顺序的路径
		Stack<String> stack = new Stack<>();
		while (current != null) {
			stack.push(current);
			if (nodeMap.get(current).prev != null) {
				// 更新循环node
				current = nodeMap.get(current).prev.id;
			}
			else {
				current = null;
			}
		}
		List<Node> rev = new ArrayList<>();
		while (!stack.isEmpty()) {
			String nodeID = stack.pop();
			rev.add(nodeMap.get(nodeID));
		}
		finalDistance = distance;
		return rev;
	}

	public static void printPath(List<Node> path) {
		System.out.println("The path is: ");
		for (Node node : path) {
			System.out.print(node.id + " ");
		}
		System.out.println();
		System.out.println("Distance: " + finalDistance[fid(path.get(path.size() - 1).id)] + " miles");
	}

}
