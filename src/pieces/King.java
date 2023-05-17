package pieces;

import main.Board;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class King extends Piece {

    public static int colOriginB = 0;
    public static int rowOriginB = 0;

    public static int colOriginW = 3;
    public static int rowOriginW = 4;

    public King(Board board, int col, int row, boolean white) throws IOException {
        super(board);
        this.column = col;
        this.row = row;
        this.white = white;
        this.arrayPos = 1;

        this.xPosition = col * board.size4Tiles;
        this.yPosition = row * board.size4Tiles;

        pieceName = "King";

        this.piece_img = chooseColor(white);
    }

    public BufferedImage chooseColor(boolean white) throws IOException {
        BufferedImage new_img;
        if(white){
            new_img = ImageIO.read(ClassLoader.getSystemResourceAsStream("King_W.png"));
        } else {
            new_img = ImageIO.read(ClassLoader.getSystemResourceAsStream("King_B.png"));
        }
        return new_img;
    }

    public boolean isMoveValid(int col, int row) {
        return Math.abs((col - this.column) * (row - this.row)) == 1 || Math.abs(col - this.column) + Math.abs(row - this.row) == 1;
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
