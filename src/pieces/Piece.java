package pieces;

import main.Board;

import java.awt.*;
import java.awt.image.BufferedImage;

// Main 'Piece' class. All the other classes for the pieces extend from here
public class Piece {

    // For current column and row position
    public int column, row;

    // For setting the piece on the current column and row position or for dragging
    public int xPosition, yPosition;

    // false = black piece, true = white piece
    public boolean white;

    // String for setting the name of the piece
    public String pieceName;

    public int arrayPos;

    // Buffered Image object for keeping the png of each piece
    BufferedImage piece_img;

    Board board;

    // Assign pieces to the board
    public Piece(Board board){
        this.board = board;

    }

    public boolean isMoveValid(int col, int row){
         return true;
    }

    public boolean moveColliding(int col, int row){
        return false;
    }

    // Draws the image of the piece
    public void Paint(Graphics2D boardThing){
        boardThing.drawImage(piece_img, xPosition, yPosition, null);
    }

    public void newGame(){

    }

    public String getPieceName(){
        return pieceName;
    }

}
