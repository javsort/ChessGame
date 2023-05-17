package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayerSetWindow extends JFrame implements ActionListener {
    static JButton readyButton;
    static JTextField playerBlack;
    static JTextField playerWhite;

    static JTextField white;
    static JTextField black;

    boolean namesSet;

    static Player player1 = new Player();
    static Player player2 = new Player();

    static JPanel players;

    public PlayerSetWindow(){
        setTitle("Set Names");
        setResizable(false);
        setSize(275,150);
        setLocation(640, 350);
        setAlwaysOnTop(true);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        readyButton = new JButton("Ready");
        readyButton.setBackground(new Color(157, 115, 58));
        readyButton.addActionListener(this);

        white = new JTextField();
        white.setEditable(false);
        white.setText("White Pieces: ");
        white.setBackground(new Color(255, 176, 89));
        playerWhite = new JTextField(10);
        playerWhite.addActionListener(this);

        black = new JTextField();
        black.setEditable(false);
        black.setText("Black Pieces: ");
        black.setBackground(new Color(255, 176, 89));
        playerBlack = new JTextField(10);
        playerBlack.addActionListener(this);

        players = new JPanel();
        players.setLayout(new FlowLayout());
        players.setBackground(new Color(255, 176, 89));
        players.add(white);
        players.add(playerWhite);
        players.add(black);
        players.add(playerBlack);

        setVisible(true);

        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        container.setBackground(new Color(255,176, 89));

        container.add(players, BorderLayout.CENTER);
        container.add(readyButton, BorderLayout.SOUTH);
    }

    public void clearTextFields(){
        playerBlack.setText("");
        playerWhite.setText("");
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(readyButton)){
            player1.setName(playerWhite.getText());
            player1.setColor(true);
            player2.setName(playerBlack.getText());
            player2.setColor(false);
            namesSet = true;

            eastMenu.updateUsers();

            clearTextFields();

            dispose();
        }
    }
}
