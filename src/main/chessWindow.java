package main;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class chessWindow extends JFrame {
    private static final int width = 700;                      // Window's width
    private static final int height = 600;                      // Window's height
    private static final int xOrigin = 450;                     // Window display point in X
    private static final int yOrigin = 100;                     // Window display point in Y

    static PlayerSetWindow startGame;


    public chessWindow() throws IOException {
        startGame = new PlayerSetWindow();

        Container contentPane;

        // main.Main panels
        GameArea mainGame = new GameArea();
        eastMenu mainMenu = new eastMenu();

        // main window characteristics
        setSize(width, height);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("Micro Chess");
        setLocation(xOrigin, yOrigin);
        setBackground(new Color(255, 176, 89));

        // main.Main container declaration
        contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.setBackground(new Color(255, 176, 89));

        contentPane.add(mainGame, BorderLayout.CENTER);
        contentPane.add(mainMenu, BorderLayout.EAST);

        setVisible(true);
    }
}
