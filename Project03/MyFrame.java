/// Zhenhao Zhang zzh133

import javax.swing.JFrame;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MyFrame extends JFrame {
	public Graph gr;
	public List<Node> path;
	public double resize;
	public boolean directions;

	public MyFrame(Graph graph, List<Node> path, boolean directions) {
		setSize(800, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		this.gr = graph;
		this.path = path;
		this.directions = directions;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		List<Edge> edges = gr.edges;
		// 画出所有的边
		// 此处需要设置缩放，因为本身的各个点的坐标值之差太小，所以需要放大
		List<Double> latitudes = new ArrayList<>();
		List<Double> longitudes = new ArrayList<>();
		for (Node node : gr.nodeMap.values()) {
			latitudes.add(node.latitude);
			longitudes.add(node.longitude);
		}
		// 找到最大最小的纬度和经度，以便计算缩放比例
		double maxLatitude = Collections.max(latitudes);
		double minLatitude = Collections.min(latitudes);
		double maxLongitude = Collections.max(longitudes);
		double minLongitude = Collections.min(longitudes);
		resize = Math.min(getWidth() / (maxLongitude - minLongitude), getHeight() / (maxLatitude - minLatitude));
		for (Edge edge : edges) {
			Node node1 = gr.nodeMap.get(edge.node1);
			Node node2 = gr.nodeMap.get(edge.node2);
			g.drawLine((int) ((node1.longitude - minLongitude) * resize),
					(int) (getHeight() - (node1.latitude - minLatitude) * resize),
					(int) ((node2.longitude - minLongitude) * resize),
					(int) (getHeight() - (node2.latitude - minLatitude) * resize));
		}
		if (directions) {
			// 用绿色画出路径
			g.setColor(Color.GREEN);
			((Graphics2D) g).setStroke(new BasicStroke(6));
			for (int i = 0; i < path.size() - 1; i++) {
				Node node1 = path.get(i);
				Node node2 = path.get(i + 1);
				g.drawLine((int) ((node1.longitude - minLongitude) * resize),
						(int) (getHeight() - (node1.latitude - minLatitude) * resize),
						(int) ((node2.longitude - minLongitude) * resize),
						(int) (getHeight() - (node2.latitude - minLatitude) * resize));
			}
			// 恢复画笔颜色和宽度
			g.setColor(Color.black);
			((Graphics2D) g).setStroke(new BasicStroke(1));
		}





	}

}