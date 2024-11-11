package projektavimas.checkers;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public abstract class Piece {
    private int row, column, pieceID;
    private Circle pieceShape;
    private Player player;

    public Piece(int row, int column, int pieceID, Player player) {
        this.row = row;
        this.column = column;
        this.pieceID = pieceID;
        this.pieceShape = new Circle(20, player.isWhite() ? Color.WHITE : Color.BLACK);
    }

    public abstract boolean isValidMove(int row, int column, Player player);


    public abstract boolean isValidCapture(int row, int column, Player player);

    public void setPosition(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public Player getPlayer() {
        return player;
    }

    public Circle getPieceShape() {
        return pieceShape;
    }

    public void highlightPiece() {
        pieceShape.setStroke(Color.RED);
        pieceShape.setStrokeWidth(5);
    }

    // Remove the highlight from the piece
    public void removeHighlight() {
        pieceShape.setStroke(null);
        pieceShape.setStrokeWidth(0);
    }

}
