import javax.swing.JFrame;
import java.awt.*;
import java.util.List;

public class GraphWindow extends JFrame {

	public static final int WindowWidth = 800;
	public static final int WindowHeight = 700;
	public static final int bias = 80;

	private Graph graph;
	private double minLat;
	private double maxLat;
	private double minLon;
	private double maxLon;

	private boolean showPath;

	private List<Node> path;

	public GraphWindow(Graph graph) {
		setTitle("Show graph result");
		setSize(WindowWidth, WindowHeight);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		this.graph = graph;
		this.minLat = Double.POSITIVE_INFINITY;
		this.maxLat = Double.NEGATIVE_INFINITY;
		this.minLon = Double.POSITIVE_INFINITY;
		this.maxLon = Double.NEGATIVE_INFINITY;
		this.showPath = false;
		for (Node node : graph.getNodes()) {
			if (node.getLatitude() < minLat) {
				minLat = node.getLatitude();
			}
			if (node.getLatitude() > maxLat) {
				maxLat = node.getLatitude();
			}
			if (node.getLongitude() < minLon) {
				minLon = node.getLongitude();
			}
			if (node.getLongitude() > maxLon) {
				maxLon = node.getLongitude();
			}
		}
	}

	public GraphWindow(Graph graph, List<Node> path) {
		setTitle("Show graph result with path");
		setSize(WindowWidth, WindowHeight);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		this.graph = graph;
		this.minLat = Double.POSITIVE_INFINITY;
		this.maxLat = Double.NEGATIVE_INFINITY;
		this.minLon = Double.POSITIVE_INFINITY;
		this.maxLon = Double.NEGATIVE_INFINITY;
		this.showPath = true;
		this.path = path;
		for (Node node : graph.getNodes()) {
			if (node.getLatitude() < minLat) {
				minLat = node.getLatitude();
			}
			if (node.getLatitude() > maxLat) {
				maxLat = node.getLatitude();
			}
			if (node.getLongitude() < minLon) {
				minLon = node.getLongitude();
			}
			if (node.getLongitude() > maxLon) {
				maxLon = node.getLongitude();
			}
		}
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Edge[] edges = graph.getEdges();
		// zoom in on the map according to the max and min latitudes and longitudes
		double zoom = Math.min(getWidth() / (maxLon - minLon), getHeight() / (maxLat - minLat));
		zoom *= 0.75;
		for (Edge edge : edges) {
			Node node1 = graph.getNode(edge.getNode1());
			Node node2 = graph.getNode(edge.getNode2());
			int x1 = (int) ((node1.getLongitude() - minLon) * zoom);
			int y1 = getHeight() - (int) ((node1.getLatitude() - minLat) * zoom) - bias;
			int x2 = (int) ((node2.getLongitude() - minLon) * zoom);
			int y2 = getHeight() - (int) ((node2.getLatitude() - minLat) * zoom) - bias;
			g.drawLine(x1, y1, x2, y2);
		}

		// if need to show path, draw the path in red color and stroke width
		if (showPath) {
			g.setColor(Color.RED);
			((Graphics2D) g).setStroke(new BasicStroke(3));
			for (int i = 0; i < path.size() - 1; i++) {
				Node node1 = path.get(i);
				Node node2 = path.get(i + 1);
				int x1 = (int) ((node1.getLongitude() - minLon) * zoom);
				int y1 = getHeight() - (int) ((node1.getLatitude() - minLat) * zoom) - bias;
				int x2 = (int) ((node2.getLongitude() - minLon) * zoom);
				int y2 = getHeight() - (int) ((node2.getLatitude() - minLat) * zoom) - bias;
				g.drawLine(x1, y1, x2, y2);
			}
		}



	}
}