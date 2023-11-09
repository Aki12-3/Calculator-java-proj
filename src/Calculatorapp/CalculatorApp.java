package Calculatorapp;
import java.awt.*;
import java.awt.event.*;

public class CalculatorApp extends Frame implements ActionListener {

    private TextField textField;
    private String operator;
    private double firstNumber;

    public CalculatorApp() {
        operator = "";
        firstNumber = 0.0;

        setTitle("Calculator");
        setSize(300, 400);
        setLayout(new BorderLayout());

        textField = new TextField();
        textField.setFont(new Font("Arial", Font.PLAIN, 24));
        textField.setEditable(false);
        add(textField, BorderLayout.NORTH);

        Panel buttonPanel = new Panel();
        buttonPanel.setLayout(new GridLayout(4, 4));

        String[] buttonLabels = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "C", "0", "=", "+"
        };

        for (String label : buttonLabels) {
            Button button = new Button(label);
            button.setFont(new Font("Arial", Font.PLAIN, 18));
            button.addActionListener(this);
            buttonPanel.add(button);
        }

        add(buttonPanel, BorderLayout.CENTER);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        String action = ae.getActionCommand();

        if (action.matches("[0-9]")) {
            textField.setText(textField.getText() + action);
        } else if (action.equals("C")) {
            textField.setText("");
            operator = "";
            firstNumber = 0.0;
        } else if (action.equals("=")) {
            double secondNumber = Double.parseDouble(textField.getText());
            double result = 0.0;

            switch (operator) {
                case "+":
                    result = firstNumber + secondNumber;
                    break;
                case "-":
                    result = firstNumber - secondNumber;
                    break;
                case "*":
                    result = firstNumber * secondNumber;
                    break;
                case "/":
                    if (secondNumber != 0) {
                        result = firstNumber / secondNumber;
                    } else {
                        textField.setText("Error");
                        return;
                    }
                    break;
            }

            textField.setText(Double.toString(result));
            operator = "";
            firstNumber = result;
        } else {
            operator = action;
            firstNumber = Double.parseDouble(textField.getText());
            textField.setText("");
        }
    }

    public static void main(String[] args) {
        new CalculatorApp();
    }
}
