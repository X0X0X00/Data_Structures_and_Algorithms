/// Zhenhao Zhang zzh133



public class Node {
	public final String id;
	public final double latitude;
	public final double longitude;

	public Node prev; // for path finding

	public Node(String id, double latitude, double longitude) {
		this.id = id;
		this.latitude = latitude;
		this.longitude = longitude;
		this.prev = null;
	}

	public void setNode(Node node) {
		this.prev = node;
	}
}