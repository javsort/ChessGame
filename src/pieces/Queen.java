package pieces;

import main.Board;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Queen extends Piece {
    public Queen(Board board, int col, int row, boolean white) throws IOException {
        super(board);
        this.column = col;
        this.row = row;
        this.white = white;
        this.arrayPos = 5;

        this.xPosition = col * board.size4Tiles;
        this.yPosition = row * board.size4Tiles;

        pieceName = "Queen";

        this.piece_img = chooseColor(white);
    }

    public BufferedImage chooseColor(boolean white) throws IOException {
        BufferedImage new_img;
        if(white){
            new_img = ImageIO.read(ClassLoader.getSystemResourceAsStream("Queen_W.png"));
        } else {
            new_img = ImageIO.read(ClassLoader.getSystemResourceAsStream("Queen_B.png"));
        }
        return new_img;
    }

    public boolean isMoveValid(int col, int row) {
        return this.column == col || this.row == row || Math.abs(this.column - col) == Math.abs(this.row - row);
    }

    public boolean moveColliding(int col, int row){
        if(this.column == col || this.row == row) {
            //Left
            if (this.column > col) {
                for (int c = this.column - 1; c > col; c--)
                    if (board.getPiece(c, this.row) != null)
                        return true;
            }
            //Right
            if (this.column < col) {
                for (int c = this.column + 1; c < col; c++)
                    if (board.getPiece(c, this.row) != null)
                        return true;
            }
            //Up
            if (this.row > row) {
                for (int r = this.row - 1; r > row; r--)
                    if (board.getPiece(this.column, r) != null)
                        return true;
            }
            //Down
            if (this.row < row) {
                for (int r = this.row + 1; r < row; r++)
                    if (board.getPiece(this.column, r) != null)
                        return true;
            }
        } else {
            //Up Left
            if(this.column > col && this.row > row){
                for(int i = 1; i < Math.abs(this.column - col); i++){
                    if(board.getPiece(this.column - i, this.row - i) != null){
                        return true;
                    }
                }
            }

            // Up Right
            if(this.column < col && this.row > row){
                for(int i = 1; i < Math.abs(this.column - col); i++){
                    if(board.getPiece(this.column + i, this.row - i) != null){
                        return true;
                    }
                }
            }

            //Down Left
            if(this.column > col && this.row < row){
                for(int i = 1; i < Math.abs(this.column - col); i++){
                    if(board.getPiece(this.column - i, this.row + i) != null){
                        return true;
                    }
                }
            }

            // Down Right
            if(this.column < col && this.row < row){
                for(int i = 1; i < Math.abs(this.column - col); i++){
                    if(board.getPiece(this.column + i, this.row + i) != null){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
