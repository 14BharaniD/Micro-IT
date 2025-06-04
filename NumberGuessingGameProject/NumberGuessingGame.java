import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class NumberGuessingGame extends JFrame implements ActionListener {

    private final JTextField inputField;
    private final JButton guessButton, restartButton;
    private final JLabel messageLabel, attemptsLabel;

    private int targetNumber;
    private int remainingAttempts;

    public NumberGuessingGame() {
        setTitle("Number Guessing Game");
        setSize(400, 250);
        setLayout(new GridLayout(5, 1, 10, 10));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        messageLabel = new JLabel("Guess a number between 1 and 100", SwingConstants.CENTER);
        messageLabel.setFont(new Font("Arial", Font.BOLD, 16));

        inputField = new JTextField();
        inputField.setHorizontalAlignment(JTextField.CENTER);
        inputField.setFont(new Font("Arial", Font.PLAIN, 18));

        guessButton = new JButton("Guess");
        restartButton = new JButton("Restart");

        attemptsLabel = new JLabel("Attempts left: 10", SwingConstants.CENTER);
        attemptsLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        add(messageLabel);
        add(inputField);
        add(guessButton);
        add(attemptsLabel);
        add(restartButton);

        // Listener registration — placed after component setup
        guessButton.addActionListener(this);
        restartButton.addActionListener(this);

        startNewGame();
        setVisible(true);
    }

    private void startNewGame() {
        Random rand = new Random();
        targetNumber = rand.nextInt(100) + 1; // 1 to 100
        remainingAttempts = 10;
        messageLabel.setText("Guess a number between 1 and 100");
        inputField.setText("");
        inputField.setEditable(true);
        guessButton.setEnabled(true);
        attemptsLabel.setText("Attempts left: " + remainingAttempts);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == guessButton) {
            String inputText = inputField.getText();
            try {
                int guess = Integer.parseInt(inputText);
                if (guess < 1 || guess > 100) {
                    messageLabel.setText("Please enter a number between 1 and 100.");
                    return;
                }

                remainingAttempts--;
                attemptsLabel.setText("Attempts left: " + remainingAttempts);

                if (guess == targetNumber) {
                    messageLabel.setText("🎉 Correct! You guessed it!");
                    inputField.setEditable(false);
                    guessButton.setEnabled(false);
                } else if (guess < targetNumber) {
                    messageLabel.setText("Too low! Try again.");
                } else {
                    messageLabel.setText("Too high! Try again.");
                }

                if (remainingAttempts == 0 && guess != targetNumber) {
                    messageLabel.setText("Game Over! Number was: " + targetNumber);
                    inputField.setEditable(false);
                    guessButton.setEnabled(false);
                }

            } catch (NumberFormatException ex) {
                messageLabel.setText("Invalid input! Enter a number.");
            }
        } else if (e.getSource() == restartButton) {
            startNewGame();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new NumberGuessingGame());
    }
}
