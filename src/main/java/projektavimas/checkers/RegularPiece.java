package projektavimas.checkers;

import static java.lang.Math.abs;

public class RegularPiece extends Piece {

    public RegularPiece(int row, int column, int pieceID, Player player) {
        super(row, column, pieceID, player);
    }

    @Override
    public boolean isValidMove(int newRow, int newColumn, Player player) {
        int direction = player.isWhite() ? 1 : -1;
        return abs(newColumn - getColumn()) == 1 && (newRow - getRow()) == direction;
    }


    @Override
    public boolean isValidCapture(int newRow, int newColumn, Player player) {
        int direction = player.isWhite() ? 1 : -1;
        return abs(newColumn - getColumn()) == 2 && (newRow - getRow()) == direction * 2;
    }
}

