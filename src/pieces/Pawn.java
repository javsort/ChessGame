package pieces;

import main.Board;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Pawn extends Piece {
    public static int colOriginB = 0;
    public static int rowOriginB = 1;

    public static int colOriginW = 3;
    public static int rowOriginW = 3;

    public Pawn(Board board, int col, int row, boolean white) throws IOException {
        super(board);
        this.column = col;
        this.row = row;
        this.white = white;
        this.arrayPos = 0;

        this.xPosition = col * board.size4Tiles;
        this.yPosition = row * board.size4Tiles;

        pieceName = "Pawn";

        this.piece_img = chooseColor(white);
    }

    public BufferedImage chooseColor(boolean white) throws IOException {
        BufferedImage new_img;
        if(white){
            new_img = ImageIO.read(ClassLoader.getSystemResourceAsStream("Peon_W.png"));
        } else {
            new_img = ImageIO.read(ClassLoader.getSystemResourceAsStream("Peon_B.png"));
        }
        return new_img;
    }

    public boolean isMoveValid(int col, int row) {
        int colorInd = white ? 1 : -1;

        //Move up 1
        if(this.column == col && row == this.row - colorInd && board.getPiece(col, row) == null)
            return true;

        //Attack left
        if(col == this.column - 1 && row == this.row - colorInd && board.getPiece(col, row) != null)
            return true;

        //Attack right
        if(col == this.column + 1 && row == this.row - colorInd && board.getPiece(col, row) != null)
            return true;

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
