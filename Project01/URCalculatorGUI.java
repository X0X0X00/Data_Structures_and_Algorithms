/*

Zhenhao Zhang
zzh133@u.rochester.edu
32277234

 */



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class URCalculatorGUI extends JFrame {
	private JTextField inputField;
	private JButton calculateButton;
	private JLabel resultLabel;

	public URCalculatorGUI() {
		setLayout(new FlowLayout()); // FlowLayout: left to right, top to bottom, default

		inputField = new JTextField(15);
		add(inputField);

		calculateButton = new JButton("Calculate");
		calculateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculate();
			}
		});
		add(calculateButton);

		resultLabel = new JLabel("Result：");
		add(resultLabel);

		setTitle("URCalculator");
		setSize(250, 100);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setLocationRelativeTo(null);

		setVisible(true);
	}

	private void calculate() {
		Expr expr = new Expr(inputField.getText());
		resultLabel.setText("Result：" + expr.evaluate());
	}


	// main
	public static void main(String[] args) {
		URCalculatorGUI gui = new URCalculatorGUI();
	}
}
