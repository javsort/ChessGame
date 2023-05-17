package main;

import pieces.Piece;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.IOException;

public class TakenPieces extends JPanel {

    BufferedImage kingWSprite, queenWSprite, rookWSprite, knightWSprite, bishopWSprite, pawnWSprite;
    BufferedImage kingBSprite, queenBSprite, rookBSprite, knightBSprite, bishopBSprite, pawnBSprite;

    ImageIcon kingWIcon, queenWIcon, rookWIcon, knightWIcon, bishopWIcon, pawnWIcon;
    ImageIcon kingBIcon, queenBIcon, rookBIcon, knightBIcon, bishopBIcon, pawnBIcon;

    JLabel kingSp, queenSp, rookSp, knightSp, bishopSp, pawnSp;


    public TakenPieces() throws IOException {
        setSize(160, 110);
        setBackground(new Color(157, 115, 58));
        setBorder(BorderFactory.createLineBorder(new Color(157, 115, 58)));
        setLayout(new GridLayout(2 ,3));
        setSprites();

        this.kingSp = new JLabel();
        this.queenSp = new JLabel();
        this.rookSp = new JLabel();
        this.knightSp = new JLabel();
        this.bishopSp = new JLabel();
        this.pawnSp = new JLabel();

        add(pawnSp);
        add(bishopSp);
        add(knightSp);
        add(rookSp);
        add(queenSp);
        add(kingSp);

        setVisible(true);
    }

    private void setSprites() throws IOException {
        kingWSprite   = ImageIO.read(ClassLoader.getSystemResourceAsStream("miniKing_W.png"));
        queenWSprite  = ImageIO.read(ClassLoader.getSystemResourceAsStream("miniQueen_W.png"));
        rookWSprite   = ImageIO.read(ClassLoader.getSystemResourceAsStream("miniTower_W.png"));
        knightWSprite = ImageIO.read(ClassLoader.getSystemResourceAsStream("miniCaballo_W.png"));
        bishopWSprite = ImageIO.read(ClassLoader.getSystemResourceAsStream("miniAlfil_W.png"));
        pawnWSprite   = ImageIO.read(ClassLoader.getSystemResourceAsStream("miniPeon_W.png"));

        kingWIcon = new ImageIcon(kingWSprite);
        queenWIcon = new ImageIcon(queenWSprite);
        rookWIcon = new ImageIcon(rookWSprite);
        knightWIcon = new ImageIcon(knightWSprite);
        bishopWIcon = new ImageIcon(bishopWSprite);
        pawnWIcon = new ImageIcon(pawnWSprite);

        kingBSprite   = ImageIO.read(ClassLoader.getSystemResourceAsStream("miniKing_B.png"));
        queenBSprite  = ImageIO.read(ClassLoader.getSystemResourceAsStream("miniQueen_B.png"));
        rookBSprite   = ImageIO.read(ClassLoader.getSystemResourceAsStream("miniTower_B.png"));
        knightBSprite = ImageIO.read(ClassLoader.getSystemResourceAsStream("miniCaballo_B.png"));
        bishopBSprite = ImageIO.read(ClassLoader.getSystemResourceAsStream("miniAlfil_B.png"));
        pawnBSprite   = ImageIO.read(ClassLoader.getSystemResourceAsStream("miniPeon_B.png"));

        kingBIcon = new ImageIcon(kingBSprite);
        queenBIcon = new ImageIcon(queenBSprite);
        rookBIcon = new ImageIcon(rookBSprite);
        knightBIcon = new ImageIcon(knightBSprite);
        bishopBIcon = new ImageIcon(bishopBSprite);
        pawnBIcon = new ImageIcon(pawnBSprite);
    }

    public void takePiece(Piece piece2assign){
        if(piece2assign.getPieceName().equals("King")){
            if(piece2assign.white){
                kingSp.setIcon(kingWIcon);
            } else {
                kingSp.setIcon(kingBIcon);
            }
        } else if (piece2assign.getPieceName().equals("Queen")){
            if(piece2assign.white){
                queenSp.setIcon(queenWIcon);
            } else {
                queenSp.setIcon(queenBIcon);
            }
        } else if (piece2assign.getPieceName().equals("Rook")){
            if(piece2assign.white){
                rookSp.setIcon(rookWIcon);
            } else {
                rookSp.setIcon(rookBIcon);
            }
        } else if (piece2assign.getPieceName().equals("Knight")){
            if(piece2assign.white){
                knightSp.setIcon(knightWIcon);
            } else {
                knightSp.setIcon(knightBIcon);
            }
        } else if (piece2assign.getPieceName().equals("Bishop")){
            if(piece2assign.white){
                bishopSp.setIcon(bishopWIcon);
            } else {
                bishopSp.setIcon(bishopBIcon);
            }
        } else if (piece2assign.getPieceName().equals("Pawn")){
            if(piece2assign.white){
                pawnSp.setIcon(pawnWIcon);
            } else {
                pawnSp.setIcon(pawnBIcon);
            }
        }
    }

    public void resetPanel(){
        this.kingSp.setIcon(null);
        this.queenSp.setIcon(null);
        this.rookSp.setIcon(null);
        this.knightSp.setIcon(null);
        this.bishopSp.setIcon(null);
        this.pawnSp.setIcon(null);
    }
}
