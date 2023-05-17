package main;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

// Sub panel for holding the game's board
public class GameArea extends JPanel {

    // Creates a 'Board' object for the game
    public static Board usedboard;

    // Board class needs to handle IOExceptions
    static {
        try {
            usedboard = new Board();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 'GameArea' object declaration
    public GameArea(){
        setLayout(new GridBagLayout());
        setVisible(true);
        setBackground(new Color(255, 176, 89));
        add(usedboard);
    }
}
