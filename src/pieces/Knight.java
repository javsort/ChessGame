package pieces;

import main.Board;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Knight extends Piece {
    public static int colOriginB = 1;
    public static int rowOriginB = 0;

    public static int colOriginW = 2;
    public static int rowOriginW = 4;

    public Knight(Board board, int col, int row, boolean white) throws IOException {
        super(board);
        this.column = col;
        this.row = row;
        this.white = white;
        this.arrayPos = 3;

        this.xPosition = col * board.size4Tiles;
        this.yPosition = row * board.size4Tiles;

        pieceName = "Knight";

        this.piece_img = chooseColor(white);
    }

    public BufferedImage chooseColor(boolean white) throws IOException {
        BufferedImage new_img;
        if(white){
            new_img = ImageIO.read(ClassLoader.getSystemResourceAsStream("Caballo_W.png"));
        } else {
            new_img = ImageIO.read(ClassLoader.getSystemResourceAsStream("Caballo_B.png"));
        }
        return new_img;
    }

    public boolean isMoveValid(int col, int row){
        return Math.abs(col - this.column) * Math.abs(row - this.row) == 2;
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
