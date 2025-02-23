import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

/**
 * UI screen for when the computer is guessing a number
 *
 * Displays the computer's guesses and processes human's answers
 * Tracks the computer's guesses
 *
 * TODO: refactor this class
 */
public class ComputerGuessesPanel extends JPanel {

    private ComputerGuessesGame cpu;

    public ComputerGuessesPanel(JPanel cardsPanel, Consumer<GameResult> gameFinishedCallback){
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        JLabel guessMessage = new JLabel("I guess ___.");
        guessMessage.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.add(guessMessage);
        guessMessage.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.add(Box.createRigidArea(new Dimension(0, 40)));

        JLabel prompt = new JLabel("Your number is...");
        this.add(prompt);
        prompt.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(Box.createRigidArea(new Dimension(0,10)));

        JButton lowerBtn = new JButton("Lower");
        lowerBtn.addActionListener(e -> {
            cpu.guessLower();
            guessMessage.setText("I guess " + cpu.getLastGuess() + ".");
        });
        this.add(lowerBtn);
        lowerBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(Box.createRigidArea(new Dimension(0,10)));

        JButton correctBtn = new JButton("Equal");
        correctBtn.addActionListener(e -> {
            guessMessage.setText("I guess ___.");

            // Send the result of the finished game to the callback
            GameResult result = new GameResult(false, cpu.getLastGuess(), cpu.getNumGuesses());
            gameFinishedCallback.accept(result);

            CardLayout cardLayout = (CardLayout) cardsPanel.getLayout();
            cardLayout.show(cardsPanel, ScreenID.GAME_OVER.name());
        });
        this.add(correctBtn);
        correctBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(Box.createRigidArea(new Dimension(0,10)));

        JButton higherBtn = new JButton("Higher");
        higherBtn.addActionListener(e -> {
            cpu.guessHigher();
            guessMessage.setText("I guess " + cpu.getLastGuess() + ".");
        });
        this.add(higherBtn);
        higherBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent e) {
                cpu = new ComputerGuessesGame();
                guessMessage.setText("I guess " + cpu.getLastGuess() + ".");
            }
        });
    }

}
