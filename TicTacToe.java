import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;
import java.util.ArrayList;

public class TicTacToe implements ActionListener {
    JFrame frame = new JFrame("Tic-Tac-Toe");
    JPanel titlePanel = new JPanel();
    JLabel message = new JLabel();
    JTextField inputBox = new JTextField("Please enter a username here");
    JPanel inputPanel = new JPanel();
    JButton[] buttons = new JButton[9];
    JButton startButton = new JButton("Start");
    JButton replayButton = new JButton("Re-play");
    boolean player;
    String name;
    int counter;
    
    TicTacToe() {
        makeFrame();
        makeMenuBar(frame);
        makeGrid(frame);
        player = true;
        frame.setSize(1600, 900);
    }
    
    private void makeFrame() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container contentPane = frame.getContentPane();
        contentPane.setLayout(null);
        
        message.setForeground(new Color(0, 25, 255));
        message.setFont(new Font("Ink Free", Font.BOLD, 75));
        message.setHorizontalAlignment(JLabel.CENTER);
        message.setText("Welcome! What's your name?");
        message.setOpaque(true);
        
        titlePanel.setLayout(new BorderLayout());
        titlePanel.setBounds(0, 0, 1600, 100);
        titlePanel.add(message);  
        
        frame.add(titlePanel, BorderLayout.NORTH);
        
        Font font1 = new Font("SansSerif", Font.BOLD, 20);
        inputBox.setFont(font1);
        
        inputPanel.setLayout(new BorderLayout());
        inputPanel.setBounds(0, 700, 1600, 30);
        inputPanel.add(inputBox);
        
        startButton.setBounds(650, 150, 80, 40);
        startButton.setFocusable(false);
        startButton.addActionListener(e -> validateUsername());
        
        replayButton.setBounds(650, 200, 80, 40);
        replayButton.setFocusable(false);
        replayButton.setEnabled(false);
        replayButton.addActionListener(e -> replayGame());
        
        frame.add(startButton);
        frame.add(replayButton);
        frame.add(inputPanel);
        
        frame.pack();
        frame.setVisible(true);
    }
    
    private void makeMenuBar(JFrame frame) {
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        
        JButton resetButton = new JButton("Reset");
        resetButton.setFocusable(false);
        resetButton.addActionListener(e -> resetGame());
        menuBar.add(resetButton);
        
        JButton quitButton = new JButton("Quit");
        quitButton.setFocusable(false);
        quitButton.addActionListener(e -> quitGame());
        menuBar.add(quitButton);
    }

    /**
     * Creates a 3x3 grid of buttons within a JPanel and adds it to the specified JFrame.
     * @param frame
     */
    private void makeGrid(JFrame frame) {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 3, 3, 3));
        buttonPanel.setBounds(100, 150, 500, 500);
        
        for(int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            buttonPanel.add(buttons[i]);
            buttons[i].setFont(new Font("MV Boli", Font.BOLD, 120));
            buttons[i].setFocusable(false);
            
        }
        
        frame.add(buttonPanel);
    }

    // Validates the entered username and updates the UI accordingly.
    private void validateUsername() {
        name = inputBox.getText();
        message.setHorizontalAlignment(JLabel.CENTER);
        
        if (name.equals("") || name.equals("Please enter a username here")) {
            message.setFont(new Font("Ink Free", Font.BOLD, 30));
            message.setText("Please enter a valid username");
        }
        else {
            startButton.setEnabled(false);
            message.setFont(new Font("Ink Free", Font.BOLD, 30));
            message.setText(name + ", please place a nought. Your opponent will place a cross after you.");
            for (int i=0; i < 9; i++) {
                buttons[i].addActionListener(this);
            }
        }
    }

    /**
     * Resets the game board, move counter, player turn
     * And prompts the current player for their move.
     */

    private void replayGame() {
        for (int i = 0; i < 9; i ++) {
            buttons[i].setText("");
            buttons[i].setEnabled(true);
        } 
        counter = 0;
        player = true;
        
        message.setText(name + ", please place a nought. Your opponent will place a cross after you.");
    }
    
    // Resets the game.
    private void resetGame() {
        frame.dispose();
        new TicTacToe();
    }
    
    // Terminates the program, ending the game.
    private void quitGame() {
        System.exit(0);
    }
    
    public void actionPerformed(ActionEvent event) {
        for(int i = 0; i < 9; i++) {
            if(event.getSource() == buttons[i]) {
                replayButton.setEnabled(true);
                if(buttons[i].getText().equals("")) {
                    if(player) {
                        buttons[i].setForeground(new Color(0, 0, 255));
                        buttons[i].setText("O");
                    } else {
                        buttons[i].setForeground(new Color(255, 0, 0));
                        buttons[i].setText("X");
                    }
                    player = !player; // Toggle the player
                    counter++;
                    checkWin();
                }
            }
        }
    }

    public void checkWin() {
        if (
            (buttons[0].getText() == "X") && 
            (buttons[1].getText() == "X") &&
            (buttons[2].getText() == "X")) {
                xWins();
        }
        
        else if (
            (buttons[3].getText() == "X") && 
            (buttons[4].getText() == "X") &&
            (buttons[5].getText() == "X")) {
                xWins();  
        }
        
        else if (
            (buttons[6].getText() == "X") && 
            (buttons[7].getText() == "X") &&
            (buttons[8].getText() == "X")) {
                xWins();
        }
        
        else if (
            (buttons[0].getText() == "X") && 
            (buttons[3].getText() == "X") &&
            (buttons[6].getText() == "X")) {
                xWins(); 
        }
        
        else if (
            (buttons[1].getText() == "X") && 
            (buttons[4].getText() == "X") &&
            (buttons[7].getText() == "X")) {
                xWins();  
        }
        
        else if (
            (buttons[2].getText() == "X") && 
            (buttons[5].getText() == "X") &&
            (buttons[8].getText() == "X")) {
                xWins();    
        }
        
        else if (
            (buttons[0].getText() == "X") && 
            (buttons[4].getText() == "X") &&
            (buttons[8].getText() == "X")) {
                xWins();     
        }
        
        else if (
            (buttons[2].getText() == "X") && 
            (buttons[4].getText() == "X") &&
            (buttons[6].getText() == "X")) {
                xWins();   
        }
        
        else if (
            (buttons[0].getText() == "O") && 
            (buttons[1].getText() == "O") &&
            (buttons[2].getText() == "O")) {
                oWins();  
        }
        
        else if (
            (buttons[3].getText() == "O") && 
            (buttons[4].getText() == "O") &&
            (buttons[5].getText() == "O")) {
                oWins();     
        }
        
        else if (
            (buttons[6].getText() == "O") && 
            (buttons[7].getText() == "O") &&
            (buttons[8].getText() == "O")) {
                oWins();   
        }
        
        else if (
            (buttons[0].getText() == "O") && 
            (buttons[3].getText() == "O") &&
            (buttons[6].getText() == "O")) {
                oWins();    
        }
        
        else if (
            (buttons[1].getText() == "O") && 
            (buttons[4].getText() == "O") &&
            (buttons[7].getText() == "O")) {
                oWins();    
        }
        
        else if (
            (buttons[2].getText() == "O") && 
            (buttons[5].getText() == "O") &&
            (buttons[8].getText() == "O")) {
                oWins(); 
        }
        
        else if (
            (buttons[0].getText() == "O") && 
            (buttons[4].getText() == "O") &&
            (buttons[8].getText() == "O")) {
                oWins();    
        }
        
        else if (
            (buttons[2].getText() == "O") && 
            (buttons[4].getText() == "O") &&
            (buttons[6].getText() == "O")) {
                oWins();     
        }
        
        else if (counter == 9) {
            message.setText("It's a draw");
        }
        
    }
    
    public void xWins() {
        for(int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false);
        }
        message.setText("Opponent wins!!!");
    }
    
    public void oWins() {
        for(int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false);
        }
        message.setText("You win!!!");
    }

    public static void main(String[] args) {

        TicTacToe tic = new TicTacToe();
    }
}