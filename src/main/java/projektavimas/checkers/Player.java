package projektavimas.checkers;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private boolean isWhite;
    private int piecesCaptured;
    private List<Piece> piecesList;

    public Player(boolean isWhite, int piecesCaptured) {
        this.piecesCaptured = piecesCaptured;
        this.isWhite = isWhite;
        this.piecesList = new ArrayList<Piece>();
    }

    public boolean isWhite() {
        return isWhite;
    }

    public void addPiece(Piece piece) {
        this.piecesList.add(piece);
    }

    public boolean belongsPiece(Piece piece) {
        return piecesList.contains(piece);
    }

    public void addPiecesCaptures() {
        piecesCaptured++;
    }

    public int getPiecesCaptured() {
        return piecesCaptured;
    }
}
