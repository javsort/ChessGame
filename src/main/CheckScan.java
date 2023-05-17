package main;

import pieces.*;

public class CheckScan {
    Board board;

    public CheckScan(Board board){
        this.board = board;
    }

    public boolean kingInCheck(MakeMove move){
        Piece king = null;

        int columnK = 0;
        int rowK = 0;

        if(move.clickedPiece.white && move.clickedPiece.getPieceName().equals("King")){
            king = Board.kingW;
            columnK = king.column;
            rowK = king.row;
        } else if (!move.clickedPiece.white && move.clickedPiece.getPieceName().equals("King")) {
            king = Board.kingB;
            columnK = king.column;
            rowK = king.row;
        }

        if(board.selectedPiece != null && board.selectedPiece.getPieceName().equals("King")){
            columnK = move.newCol;
            rowK = move.newRow;
        }

        if(king == null){
            return false;
        } else {
            return  rookHits(move.newCol, move.newRow, king, columnK, rowK, 0 ,1) || // Up
                    rookHits(move.newCol, move.newRow, king, columnK, rowK, 1 ,0) || // Right
                    rookHits(move.newCol, move.newRow, king, columnK, rowK, 0 ,-1) || // Down
                    rookHits(move.newCol, move.newRow, king, columnK, rowK, -1 ,0) || // Left

                    bishopHits(move.newCol, move.newRow, king, columnK, rowK, -1 ,-1) || // Up left
                    bishopHits(move.newCol, move.newRow, king, columnK, rowK, 1 ,-1) || // Up right
                    bishopHits(move.newCol, move.newRow, king, columnK, rowK, 1 ,1) || // Down right
                    bishopHits(move.newCol, move.newRow, king, columnK, rowK, /*rowK*/ - 1 ,1) || // Down left

                    knightHits(move.newCol, move.newRow, king, columnK, rowK) ||

                    kingHits(king, columnK, rowK) ||

                    pawnHits(move.newCol, move.newRow, king, columnK, rowK);
        }
    }

    private boolean rookHits(int col, int row, Piece king, int columnK, int rowK, int colVal, int rowVal){
        for(int i = 1; i < 8; i++){
            if(columnK + (i * colVal) == col && rowK + (i * rowVal) == row){
                break;
            }

            Piece piece = board.getPiece(columnK + (i * colVal), rowK + (i * rowVal));
            if(piece != null && piece != board.selectedPiece){
                if (!board.isItSameColor(piece, king) && (piece.pieceName.equals("Rook") || piece.pieceName.equals("Queen")) ){
                    return true;

                }
                break;
            }
        }
        return false;
    }

    private boolean bishopHits(int col, int row, Piece king, int columnK, int rowK, int colVal, int rowVal){
        for(int i = 1; i < 8; i++){
            if(columnK - (i * colVal) == col && rowK - (i * rowVal) == row ){
                break;
            }

            Piece piece = board.getPiece(columnK - (i * colVal), rowK - (i * rowVal));
            if(piece != null && piece != board.selectedPiece){
                if (!board.isItSameColor(piece, king) && (piece.pieceName.equals("Bishop") || piece.pieceName.equals("Queen")) ){
                    return true;
                }
                break;
            }

        }
        return false;
    }

    private boolean knightHits(int col, int row, Piece king, int columnK , int rowK){
        return knightCheck(board.getPiece(columnK - 1, rowK - 2), king , col, row) ||
                knightCheck(board.getPiece(columnK + 1, rowK - 2), king , col, row) ||
                knightCheck(board.getPiece(columnK + 2, rowK - 1), king , col, row) ||
                knightCheck(board.getPiece(columnK + 2, rowK + 1), king , col, row) ||
                knightCheck(board.getPiece(columnK + 1, rowK + 2), king , col, row) ||
                knightCheck(board.getPiece(columnK - 1, rowK + 2), king , col, row) ||
                knightCheck(board.getPiece(columnK - 2, rowK + 1), king , col, row) ||
                knightCheck(board.getPiece(columnK - 2, rowK - 1), king , col, row);

    }

    private boolean knightCheck(Piece p, Piece k, int col, int row){
        return p != null && !board.isItSameColor(p, k) && p.pieceName.equals("Knight") && !(p.column == col && p.row == row);
    }

    private boolean kingHits(Piece king, int columnK, int rowK){
        return kingCheck(board.getPiece(columnK - 1, rowK - 1), king) ||
                kingCheck(board.getPiece(columnK + 1, rowK - 1), king) ||
                kingCheck(board.getPiece(columnK, rowK - 1), king) ||
                kingCheck(board.getPiece(columnK - 1, rowK), king) ||
                kingCheck(board.getPiece(columnK + 1, rowK), king) ||
                kingCheck(board.getPiece(columnK - 1, rowK + 1), king) ||
                kingCheck(board.getPiece(columnK + 1, rowK + 1), king) ||
                kingCheck(board.getPiece(columnK, rowK + 1), king);

    }

    private boolean kingCheck(Piece p, Piece k){
        return p != null  && !board.isItSameColor(p, k) && p.pieceName.equals("King");
    }

    private boolean checkMate(){

        return false;
    }

    private boolean pawnHits(int col, int row, Piece king, int columnK, int rowK){
        int clr = king.white ? -1  : 1;
        return pawnCheck(board.getPiece(columnK + 1, rowK + clr), king, col, row) ||
                pawnCheck(board.getPiece(columnK - 1, rowK + clr), king, col, row);
    }

    private boolean pawnCheck(Piece p, Piece k, int col, int row){
        return p != null && !board.isItSameColor(p, k) && p.pieceName.equals("Pawn") && !(p.column == col && p.row == row);
    }
}
