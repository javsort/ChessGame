package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

// Menu on the right-side of the game
public class eastMenu extends JPanel implements ActionListener {
    // Inner panels for the menu
    JPanel southButtons;
    JPanel playerSection;

    static TakenPieces takenPiecesP1, takenPiecesP2;

    // Buttons declaration
    JButton createNewG;

    // Player Section
    static JLabel player1Data, player2Data;

    // 'eastMenu' object declaration
    public eastMenu() throws IOException {
        setBackground(new Color(255, 176, 89));
        setBorder(BorderFactory.createLineBorder(new Color(157, 115, 58)));
        setLayout(new BorderLayout());
        setSize(160, 110);

        // Buttons section
        southButtons = new JPanel();
        southButtons.setBackground(new Color(255, 176, 89));
        southButtons.setBorder(BorderFactory.createLineBorder(new Color(157, 115, 58)));
        southButtons.setLayout(new GridLayout(1,2, 2,0));

            // "New Game" Button
        createNewG = new JButton("New Game");
        createNewG.setBackground(new Color(157, 115, 58));
        createNewG.addActionListener(this);

        southButtons.add(createNewG);

        // Display player information in menu section
        playerSection = new JPanel();
        playerSection.setBorder(BorderFactory.createTitledBorder("Players:"));
        playerSection.setBackground(new Color(255, 176, 89));
        playerSection.setLayout(new BoxLayout(playerSection, BoxLayout.PAGE_AXIS));

            // Panels for the information about the players
        player1Data = new JLabel();
        player1Data.setText("White: " + PlayerSetWindow.player1.getName());
        player1Data.setBackground(new Color(157, 115, 58));

        player2Data = new JLabel();
        player2Data.setText("Black: " + PlayerSetWindow.player2.getName());
        player2Data.setBackground(new Color(157, 115, 58));

            // Panels for the pieces that have been taken
        takenPiecesP1 = new TakenPieces();
        takenPiecesP2 = new TakenPieces();

            // Setup configuration for the menu
        playerSection.add(player2Data);
        playerSection.add(Box.createRigidArea(new Dimension(0,3)));
        playerSection.add(takenPiecesP2);
        playerSection.add(Box.createRigidArea(new Dimension(0,3)));
        playerSection.add(player1Data);
        playerSection.add(Box.createRigidArea(new Dimension(0,3)));
        playerSection.add(takenPiecesP1);

        // Add to the menu's container
        add(playerSection, BorderLayout.CENTER);
        add(southButtons, BorderLayout.SOUTH);
    }

    public static void updateUsers(){
        player1Data.setText("White: " + PlayerSetWindow.player1.getName());
        player2Data.setText("Black: " + PlayerSetWindow.player2.getName());
    }

    public void actionPerformed(ActionEvent event) {
        if(event.getSource() instanceof JButton) {
            Board.resetPieces();

            takenPiecesP1.resetPanel();
            takenPiecesP2.resetPanel();
            player1Data.setText("White: ");
            player2Data.setText("Black: ");

            chessWindow.startGame.setVisible(true);
        }
    }
}
