package projektavimas.checkers;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private final Cell[][] cells;
    private final GridPane grid;

    public Board(GridPane grid) {
        this.cells = new Cell[8][8];
        this.grid = grid;
    }

    public void initializeBoard() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Color color = (row + col) % 2 == 0 ? Color.SADDLEBROWN : Color.ROSYBROWN;
                cells[row][col] = new Cell(row, col, color);
                grid.add(cells[row][col], col, row);
                cells[row][col].setIsEmpty(true);
            }
        }
    }


    public void initializeRegularPieces(Player whitePlayer, Player blackPlayer) {
        int pieceID=0;
        for (int row = 1; row < 7; row++) {
            for (int col = 0; col < 8; col++) {
                if(row <3 && (row +col) % 2 != 0) {
                    Piece whitePiece = new RegularPiece(row, col, pieceID, whitePlayer);
                    whitePlayer.addPiece(whitePiece);
                    cells[row][col].setPiece(whitePiece, whitePlayer);
                    cells[row][col].setIsEmpty(false);
                    pieceID++;
                } else if (row > 4 && (row +col) % 2 != 0) {
                    Piece blackPiece = new RegularPiece(row, col, pieceID, blackPlayer);
                    blackPlayer.addPiece(blackPiece);
                    cells[row][col].setPiece(blackPiece, blackPlayer);
                    cells[row][col].setIsEmpty(false);
                    pieceID++;
                }
            }
        }
    }

    public void initializeKingPieces(Player whitePlayer, Player blackPlayer) {
        int pieceID=0;
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if(row <3 && (row +col) % 2 != 0 && row == 0) {
                    Piece whitePiece = new KingPiece(row, col, pieceID, whitePlayer);
                    whitePlayer.addPiece(whitePiece);
                    cells[row][col].setPiece(whitePiece, whitePlayer);
                    cells[row][col].setIsEmpty(false);
                    pieceID++;
                } else if (row > 4 && (row +col) % 2 != 0 && row == 7) {
                    Piece blackPiece = new KingPiece(row, col, pieceID, blackPlayer);
                    blackPlayer.addPiece(blackPiece);
                    cells[row][col].setPiece(blackPiece, blackPlayer);
                    cells[row][col].setIsEmpty(false);
                    pieceID++;
                }
            }
        }
    }

    public void movePiece(Piece piece, int newRow, int newColumn) {
        Cell oldCell = getCell(piece.getRow(), piece.getColumn());
        Cell newCell = getCell(newRow, newColumn);

            oldCell.removePiece();
            piece.setPosition(newRow, newColumn);
            newCell.setPiece(piece, piece.getPlayer());
            oldCell.setIsEmpty(true);
            newCell.setIsEmpty(false);
    }

    public Cell getCell(int row, int col) {
        return cells[row][col];
    }
}
