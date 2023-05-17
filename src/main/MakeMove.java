package main;

import pieces.Piece;

public class MakeMove {

    // Variables for previous (X,Y) locations
    int prevCol;
    int prevRow;

    // Variables for new (X,Y) locations
    int newCol;
    int newRow;

    // Variables for the piece that was clicked and the one the user wants to move to
    Piece clickedPiece;
    Piece capturedPiece;

    // 'MakeMove' object declaration
    public MakeMove(Board board, Piece clickedPiece, int newCol, int newRow){
        this.prevCol = clickedPiece.column;
        this.prevRow = clickedPiece.row;
        this.newCol = newCol;
        this.newRow = newRow;

        this.clickedPiece = clickedPiece;
        this.capturedPiece = board.getPiece(newCol, newRow);
    }

}
