package pieces;

import main.Board;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Rook extends Piece {
    public static int colOriginB = 3;
    public static int rowOriginB = 0;

    public static int colOriginW = 0;
    public static int rowOriginW = 4;

    public Rook(Board board, int col, int row, boolean white) throws IOException {
        super(board);
        this.column = col;
        this.row = row;
        this.white = white;
        this.arrayPos = 4;

        this.xPosition = col * board.size4Tiles;
        this.yPosition = row * board.size4Tiles;

        pieceName = "Rook";

        this.piece_img = chooseColor(white);
    }

    public BufferedImage chooseColor(boolean white) throws IOException {
        BufferedImage new_img;
        if(white){
            new_img = ImageIO.read(ClassLoader.getSystemResourceAsStream("Tower_W.png"));
        } else {
            new_img = ImageIO.read(ClassLoader.getSystemResourceAsStream("Tower_B.png"));
        }
        return new_img;
    }

    public boolean isMoveValid(int col, int row) {
        return this.column == col || this.row == row;
    }

    public boolean moveColliding(int col, int row){
        //Left
        if(this.column > col){
            for(int c = this.column - 1; c > col; c--)
                if(board.getPiece(c, this.row) != null)
                    return true;
        }
        //Right
        if(this.column < col){
            for(int c = this.column + 1; c < col; c++)
                if(board.getPiece(c, this.row) != null)
                    return true;
        }
        //Up
        if(this.row > row){
            for(int r = this.row - 1; r > row; r--)
                if(board.getPiece(this.column, r) != null)
                    return true;
        }
        //Down
        if(this.row < row){
            for(int r = this.row + 1; r < row; r++)
                if(board.getPiece(this.column, r) != null)
                    return true;
        }
        return false;
    }

    public void newGame(){
        if(this.white){
            this.row = this.rowOriginW;
            this.column = this.colOriginW;

            this.xPosition = this.colOriginW * board.size4Tiles;
            this.yPosition = this.rowOriginW * board.size4Tiles;
        } else {
            this.row = rowOriginB;
            this.column = colOriginB;

            this.xPosition = this.colOriginB * board.size4Tiles;
            this.yPosition = this.rowOriginB * board.size4Tiles;
        }
    }
}
