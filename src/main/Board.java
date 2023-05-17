package main;

import pieces.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

// Class in charge of handling the board
public class Board extends JPanel {

    // Size for the tiles in pixels (w: 110, h: 110)
    public int size4Tiles = 110;

    static boolean turn = true;        // True - white turn, false - black turn

    // Amount of rows and columns for the board
    int cols = 4;
    int rows = 5;

    // Array List of the pieces
    static ArrayList<Piece> piecesList = new ArrayList<>();
    static ArrayList<Piece> takenPiecesList = new ArrayList<>();

    //Piece that is being moved
    public Piece selectedPiece;

    //Creates a Get Input object, which handles the movement of the pieces
    GetInput input = new GetInput(this);

    CheckScan checkscan = new CheckScan(this);

    static Piece kingB, bishopB, knightB, pawnB, rookB, queenB;
    static Piece kingW, bishopW, knightW, pawnW, rookW, queenW;

    // Board object declaration
    public Board() throws IOException {
        this.setPreferredSize(new Dimension(cols * size4Tiles, rows * size4Tiles));
        this.addMouseListener(input);
        this.addMouseMotionListener(input);
        addPieces();
    }

    // Checks for a valid move within the board
    public boolean isMoveValid(MakeMove moveit) {
        if (isItSameColor(moveit.clickedPiece, moveit.capturedPiece)) {
            return false;
        }

        if(!moveit.clickedPiece.isMoveValid(moveit.newCol, moveit.newRow)){
            return false;
        }

        if(moveit.clickedPiece.moveColliding(moveit.newCol, moveit.newRow)){
            return false;
        }

        if(checkscan.kingInCheck(moveit)){
            return false;
        }

        if(!moveit.clickedPiece.white && getTurn()){
            return false;
        }

        if(moveit.clickedPiece.white && !getTurn()){
            return false;
        }

        return true;
    }

    // Method that doesn't allow the user to replace its own pieces
    public boolean isItSameColor(Piece P1, Piece P2) {
        if (P1 == null || P2 == null) {
            return false;
        } else if(P1.white == P2.white) return true;
        return false;
    }

    // Method that changes the locations of the pieces
    public void doMove(MakeMove moveit) throws IOException {
        if(moveit.clickedPiece.pieceName.equals("Pawn")){
            pawnPromo(moveit);
            switchTurn();
        } else {
            moveit.clickedPiece.column = moveit.newCol;
            moveit.clickedPiece.row = moveit.newRow;

            moveit.clickedPiece.xPosition = moveit.newCol * size4Tiles;
            moveit.clickedPiece.yPosition = moveit.newRow * size4Tiles;

            capture(moveit.capturedPiece);
            switchTurn();
        }
    }

    // Method that 'captures' a piece
    public void capture(Piece piece2Capture) {
        if(piece2Capture != null && piece2Capture.white){
            takenPiecesList.add(piece2Capture);
            piecesList.remove(piece2Capture);
            eastMenu.takenPiecesP1.takePiece(piece2Capture);
        } else if(piece2Capture != null && !piece2Capture.white) {
            takenPiecesList.add(piece2Capture);
            piecesList.remove(piece2Capture);
            eastMenu.takenPiecesP2.takePiece(piece2Capture);
        }
    }

    public void pawnPromo(MakeMove moveit) throws IOException {
        int colorId = moveit.clickedPiece.white ? 0 : 4;

        if(moveit.newRow == colorId){
            if(moveit.clickedPiece.white){
                queenW = new Queen(this, moveit.newCol, moveit.newRow,true);
                piecesList.add(queenW);
            } else {
                queenB = new Queen(this, moveit.newCol, moveit.newRow,false);
                piecesList.add(queenB);
            }

            capture(moveit.clickedPiece);
        }

        moveit.clickedPiece.column = moveit.newCol;
        moveit.clickedPiece.row = moveit.newRow;

        moveit.clickedPiece.xPosition = moveit.newCol * size4Tiles;
        moveit.clickedPiece.yPosition = moveit.newRow * size4Tiles;

        capture(moveit.capturedPiece);
    }


    // Method returns the piece at a certain location according to the 'col' and 'row' parameters
    public Piece getPiece(int col, int row){
        for(Piece piece : piecesList){
            if(piece.column == col && piece.row == row){
                return piece;
            }
        }
        return null;
    }

    public void addPieces() throws IOException {
        // Black pieces
        kingB = new King(this, 0, 0,false);
        knightB = new Knight(this, 1, 0, false);
        bishopB = new Bishop(this, 2, 0, false);
        rookB = new Rook(this, 3, 0, false);
        pawnB = new Pawn(this, 0, 1, false);

        piecesList.add(kingB);
        piecesList.add(knightB);
        piecesList.add(bishopB);
        piecesList.add(rookB);
        piecesList.add(pawnB);

        // White pieces
        kingW = new King(this, 3, 4,true);
        knightW = new Knight(this, 2, 4, true);
        bishopW = new Bishop(this, 1, 4, true);
        rookW = new Rook(this, 0, 4, true);
        pawnW = new Pawn(this, 3, 3, true);

        piecesList.add(kingW);
        piecesList.add(knightW);
        piecesList.add(bishopW);
        piecesList.add(rookW);
        piecesList.add(pawnW);
    }

    //Board characteristics
    public void paintComponent(Graphics a){
        // Generate a Graphics2D object that handles the entire board for the game and its pieces
        Graphics2D boardThing = (Graphics2D) a;

        // Paint the squares from the board
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                boardThing.setColor((i + j) % 2 == 0 ? new Color(255, 238, 225) : new Color(110, 95, 75));
                boardThing.fillRect(j * size4Tiles, i * size4Tiles, size4Tiles, size4Tiles);
            }
        }

        // Highlights for the movement
        if(selectedPiece != null)
            for(int i = 0; i < rows + 1; i++){
            for(int j = 0; j < cols + 1; j++){
                if(isMoveValid(new MakeMove(this, selectedPiece, i, j))){
                    boardThing.setColor(new Color(72, 18, 0));
                    boardThing.fillRect(i * size4Tiles, j * size4Tiles, size4Tiles, size4Tiles);
                }
            }
        }

        // Paint the pieces
        for(Piece piece : piecesList){
            piece.Paint(boardThing);
        }

    }

    public boolean getTurn(){
        return turn;
    }

    public void switchTurn(){
        turn = !turn;
    }

    public static void clearList(){
        takenPiecesList.clear();
    }

    public static void resetPieces() {
        turn = true;

        for (Piece piece : takenPiecesList) {
            if (piece == null) {
                break;
            } else {
                piecesList.add(piece);
            }
        }

        clearList();

        for(int i = 0; i < piecesList.size(); i++){
            Piece target = piecesList.get(i);

            if(target.getPieceName().equals("Queen")){
                piecesList.remove(i);
                i--;
            }
        }

        for (Piece piece : piecesList) {
            if (piece == null) {
                break;
            } else {
                piece.newGame();
            }
        }
    }
}