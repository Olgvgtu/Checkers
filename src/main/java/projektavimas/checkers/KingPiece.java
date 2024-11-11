package projektavimas.checkers;

import static java.lang.Math.abs;

public class KingPiece extends Piece {


    public KingPiece(int row, int column, int pieceID, Player player) {
        super(row, column, pieceID, player);
    }

    @Override
    public boolean isValidMove(int newRow, int newColumn, Player player) {
        return abs(newColumn - getColumn()) == 1 && abs(newRow - getRow()) == 1;
    }


    @Override
    public boolean isValidCapture(int newRow, int newColumn, Player player) {
        return abs(newColumn - getColumn()) == 2 && abs(newRow - getRow()) == 2;
    }
}
