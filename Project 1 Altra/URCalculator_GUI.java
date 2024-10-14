import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class URCalculator_GUI extends JFrame {
    private JTextField inputField;
    private JButton calculateButton;

    private JButton resetButton;
    private JLabel resultLabel;


    public URCalculator_GUI(){

        setLayout(new FlowLayout()); // FlowLayout: left to right, top to bottom, default

        inputField = new JTextField(15);
        add(inputField);

        calculateButton = new JButton("Calculate");
        resetButton = new JButton("Reset");

        // 计算
        calculateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculate();
            }
        });
        add(calculateButton); // add canvas

        // 清零
        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                inputField.setText(" ");
                resultLabel.setText("Result: ");
            }
        });
        add(resetButton);


        resultLabel = new JLabel("Result：");
        add(resultLabel);

        setTitle("URCalculator");
        setSize(300, 100);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        setVisible(true);

    }

    private void calculate() {
        String expression = inputField.getText();
        resultLabel.setText("Result: " + URCalculator.evaluatePostfix(URCalculator.infixToPostfix(URCalculator.tokenize(expression))));
    }



    public static void main(String[] args) {
        URCalculator_GUI GUI = new URCalculator_GUI();
    }



}

