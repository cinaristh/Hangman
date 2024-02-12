import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    private JPanel panel1;
    private JButton resetButton;
    private JTextField inputTextField;
    private JLabel image1;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;

    private ImageIcon iNoose;
    private ImageIcon iGameOver;
    private ImageIcon i1;
    private ImageIcon i2;
    private ImageIcon i3;
    private ImageIcon i4;
    private ImageIcon i5;
    private ImageIcon i6;
    private ImageIcon i7;
    private ImageIcon i8;

    private String[] wordList = {"MYSTERY", "MELODY", "TREASURE", "HANGMAN", "PUDDING", "COMPUTER", "GRANDSON"};
    private String maskWord;
    private String wordToGuess;
    private Character input;
    private int guessCount;
    private int mistakeCount;

    public static void main(String[] args) {
        new Main();
    }

    public Main() {
        this.setSize(800, 700);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Hangman");

        panel1 = new JPanel();
        panel1.setBackground(new Color(240, 240, 240));
        panel1.setLayout(new GridLayout(4, 4, 10, 10));

        label1 = new JLabel("Welcome to Hangman");
        label1.setFont(new Font("Calibri", Font.BOLD, 24));
        label2 = new JLabel("Type a letter:");
        label2.setFont(new Font("Calibri", Font.PLAIN, 18));
        label3 = new JLabel();
        label4 = new JLabel();
        label5 = new JLabel();

        image1 = new JLabel();
        iNoose = new ImageIcon("noose.jpg");
        image1.setIcon(iNoose);

        inputTextField = new JTextField();
        inputTextField.setFont(new Font("Calibri", Font.BOLD, 40));
        inputTextField.setHorizontalAlignment(SwingConstants.CENTER);

        resetButton = new JButton("Reset");
        resetButton.setFont(new Font("Calibri", Font.BOLD, 16));

        panel1.add(label2);
        panel1.add(label1);
        panel1.add(inputTextField);
        panel1.add(resetButton);
        panel1.add(image1);
        panel1.add(label3);
        panel1.add(label4);
        panel1.add(label5);

        this.add(panel1);
        this.setVisible(true);

        inputTextField.addActionListener(e -> inputTextField_OnENTER());
        resetButton.addActionListener(e -> resetButton_onCLICK());

        wordToGuess = getRandomWord();
        maskWord = "*".repeat(wordToGuess.length());
        guessCount = 0;
        mistakeCount = 0;

        i1 = new ImageIcon("1.jpg");
        i2 = new ImageIcon("2.jpg");
        i3 = new ImageIcon("3.jpg");
        i4 = new ImageIcon("4.jpg");
        i5 = new ImageIcon("5.jpg");
        i6 = new ImageIcon("6.jpg");
        i7 = new ImageIcon("7.jpg");
        i8 = new ImageIcon("8.jpg");
        iGameOver = new ImageIcon("game_over.jpg");
    }

    private String getRandomWord() {
        int randomIndex = (int) (Math.random() * wordList.length);
        return wordList[randomIndex];
    }

    private void resetButton_onCLICK() {
        inputTextField.setEnabled(true);
        image1.setIcon(iNoose);
        wordToGuess = getRandomWord();
        maskWord = "*".repeat(wordToGuess.length());
        guessCount = 0;
        mistakeCount = 0;
        label3.setText("");
        label4.setText("");
        label5.setText("");
        inputTextField.setText("");
    }

    private void inputTextField_OnENTER() {
        String inputText = inputTextField.getText().toUpperCase();
        inputTextField.setText(inputText);
        inputTextField.setText("");

        if (inputText.length() > 1) {
            inputText = inputText.substring(0, 1);
            inputTextField.setText(inputText);
        } else if (inputText.equals(" ")) {
            inputText = "";
        }

        if (!inputText.matches("^[a-zA-Z]$")) {
            label5.setText("Please enter only a letter.");
            inputTextField.setText("");
            return;
        }
        label5.setText("");
        input = inputText.charAt(0);
        guessCount++;

        boolean found = false;
        StringBuilder updatedMaskWord = new StringBuilder(maskWord);
        for (int i = 0; i < wordToGuess.length(); i++) {
            if (input.equals(wordToGuess.charAt(i))) {
                found = true;
                updatedMaskWord.setCharAt(i, input);
            }
        }

        if (!found) {
            mistakeCount++;
            label4.setText("Mistakes: " + mistakeCount);
            if (mistakeCount == 9) {
                image1.setIcon(iGameOver);
                inputTextField.setEnabled(false);
                label3.setText("You are hanged!");
            } else {
                updateHangmanImage();
            }
        }

        maskWord = updatedMaskWord.toString();
        label3.setText("Word: " + maskWord);

        if (maskWord.equals(wordToGuess)) {
            inputTextField.setEnabled(false);
            label3.setText("Congratulations! You guessed the word!");
        }
    }

    private void updateHangmanImage() {
        switch (mistakeCount) {
            case 1:
                image1.setIcon(i1);
                break;
            case 2:
                image1.setIcon(i2);
                break;
            case 3:
                image1.setIcon(i3);
                break;
            case 4:
                image1.setIcon(i4);
                break;
            case 5:
                image1.setIcon(i5);
                break;
            case 6:
                image1.setIcon(i6);
                break;
            case 7:
                image1.setIcon(i7);
                break;
            case 8:
                image1.setIcon(i8);
                break;
        }
    }
}
