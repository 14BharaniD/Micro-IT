import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Calculator implements ActionListener {
    JFrame frame;
    JTextField textField;
    JButton[] numberButtons = new JButton[10];
    JButton[] functionButtons;
    JButton addButton, subButton, mulButton, divButton;
    JButton decButton, equButton, delButton, clrButton;
    JPanel panel;
    Font font = new Font("Arial", Font.PLAIN, 24);

    double num1 = 0, num2 = 0, result = 0;
    char operator;

    public Calculator() {
        frame = new JFrame("Calculator");
        frame.setSize(400, 550);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        textField = new JTextField();
        textField.setBounds(30, 40, 320, 50);
        textField.setFont(font);
        textField.setEditable(false);
        frame.add(textField);

        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("×");
        divButton = new JButton("");
        decButton = new JButton(".");
        equButton = new JButton("=");
        delButton = new JButton("←");
        clrButton = new JButton("C");

        functionButtons = new JButton[]{
            addButton, subButton, mulButton, divButton,
            decButton, equButton, delButton, clrButton
        };

        for (JButton btn : functionButtons) {
            btn.setFont(font);
            btn.setFocusable(false);
        }

        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].setFont(font);
            numberButtons[i].setFocusable(false);
        }

        delButton.setBounds(150, 430, 100, 40);
        clrButton.setBounds(260, 430, 100, 40);
        frame.add(delButton);
        frame.add(clrButton);

        panel = new JPanel();
        panel.setBounds(30, 100, 330, 300);
        panel.setLayout(new GridLayout(4, 4, 10, 10));

        // Add buttons to panel
        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(addButton);
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subButton);
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(mulButton);
        panel.add(decButton);
        panel.add(numberButtons[0]);
        panel.add(equButton);
        panel.add(divButton);

        frame.add(panel);
        frame.setVisible(true);

        // Register listeners AFTER setup
        initListeners();
    }

    private void initListeners() {
        for (JButton btn : functionButtons) {
            btn.addActionListener(this);
        }
        for (JButton btn : numberButtons) {
            btn.addActionListener(this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberButtons[i]) {
                textField.setText(textField.getText().concat(String.valueOf(i)));
                return;
            }
        }

        if (e.getSource() == decButton) {
            textField.setText(textField.getText().concat("."));
        }

        if (e.getSource() == addButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '+';
            textField.setText("");
        }

        if (e.getSource() == subButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '-';
            textField.setText("");
        }

        if (e.getSource() == mulButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '*';
            textField.setText("");
        }

        if (e.getSource() == divButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '/';
            textField.setText("");
        }

        if (e.getSource() == equButton) {
    if (!textField.getText().isEmpty() && operator != ' ') {
        try {
            num2 = Double.parseDouble(textField.getText());
        } catch (NumberFormatException ex) {
            textField.setText("Error");
            return;
        }
        
        // Use modern switch expression here if you want:
        result = switch (operator) {
            case '+' -> num1 + num2;
            case '-' -> num1 - num2;
            case '*' -> num1 * num2;
            case '/' -> {
                if (num2 == 0) {
                    textField.setText("Error");
                    operator = ' ';
                    yield 0; // value to satisfy compiler, won't be used due to return
                }
                yield num1 / num2;
            }
            case '^' -> Math.pow(num1, num2);
            default -> 0;
        };
        
        double epsilon = 1E-10;
        if (Math.abs(result - Math.round(result)) < epsilon) {
            textField.setText(String.valueOf(Math.round(result)));
        } else {
            textField.setText(String.valueOf(result));
        }
        
        operator = ' ';
    }
    return;
}


        if (e.getSource() == clrButton) {
            textField.setText("");
        }

        if (e.getSource() == delButton) {
            String text = textField.getText();
            if (!text.isEmpty()) {
                textField.setText(text.substring(0, text.length() - 1));
            }
        }
    }
}
