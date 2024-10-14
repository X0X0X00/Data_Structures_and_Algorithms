
public class Edge {
	private String id;
	private String node1;
	private String node2;
	private double weight;

	public Edge(String id, String node1, String node2, double weight) {
		this.id = id;
		this.node1 = node1;
		this.node2 = node2;
		this.weight = weight;
	}
	public String getID() {
		return id;
	}
	public String getNode1() {
		return node1;
	}
	public String getNode2() {
		return node2;
	}

	public double getWeight() {
		return weight;
	}
}
