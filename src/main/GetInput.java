package main;

import pieces.Piece;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

// Class that handles mouse input on the board
public class GetInput extends MouseAdapter {
    Board board;

    // Assigns the current board for performing events
    public GetInput(Board board){
        this.board = board;

    }

    // MousePressed method - stores the piece, along with its location for possible move
    @Override
    public void mousePressed(MouseEvent e) {
        int col = e.getX() / board.size4Tiles;
        int row = e.getY() / board.size4Tiles;

        Piece chosenPiece = board.getPiece(col, row);

        if(chosenPiece != null) {                       // In case it doesn't actually click a piece
                board.selectedPiece = chosenPiece;
        }
    }

    // MouseDragged method - keeps moving the piece along with the cursor
    @Override
    public void mouseDragged(MouseEvent e) {
        if(board.selectedPiece != null){
            board.selectedPiece.xPosition = e.getX() - board.size4Tiles / 2;
            board.selectedPiece.yPosition = e.getY() - board.size4Tiles / 2;

            board.repaint();
        }
    }

    // MouseReleased method - calls the MakeMove class to check if it's a valid move, if true, it replaces the last piece while also updating the new location for the other piece
    @Override
    public void mouseReleased(MouseEvent e) {
        int col = e.getX() / board.size4Tiles;
        int row = e.getY() / board.size4Tiles;

        if(board.selectedPiece != null){
            MakeMove move_it= new MakeMove(board, board.selectedPiece, col, row);
            if(board.isMoveValid(move_it)){
                try {
                    board.doMove(move_it);

                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            } else {
                board.selectedPiece.xPosition = board.size4Tiles * board.selectedPiece.column;
                board.selectedPiece.yPosition = board.size4Tiles * board.selectedPiece.row;

            }
        }

        board.selectedPiece = null;
        board.repaint();
    }

}
