package projektavimas.checkers;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Cell extends StackPane {
    private final int column;
    private final int row;
    private boolean isEmpty = true;
    private Rectangle background;
    private Piece piece;

    public Cell(int row, int column, Color color) {
        this.row = row;
        this.column = column;
        this.background = new Rectangle(50, 50);
        this.background.setFill(color);
        this.getChildren().add(background);
    }

    public void setPiece(Piece piece, Player player) {
        this.piece = piece;
        this.isEmpty = false;
        this.getChildren().add(piece.getPieceShape());
    }

    public void removePiece() {
        this.piece = null;
        this.isEmpty = true;

        this.getChildren().clear();
        this.getChildren().add(background);
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public boolean setIsEmpty(boolean isEmpty) {
        return this.isEmpty = isEmpty;
    }

    public Piece getPiece() {
        return piece;
    }
}
