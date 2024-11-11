package projektavimas.fxControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import projektavimas.checkers.Board;
import projektavimas.checkers.Game;
import projektavimas.checkers.Player;

public class MainController {

    @FXML
    private GridPane boardGrid;
    @FXML
    private Button startGameButton;
    @FXML
    private Label turnLabel;

    private Board board;
    private Game game;

    @FXML
    public void initialize() {
        board = new Board(boardGrid);
        game = new Game(board);
        addCellClickListeners();
        startGameButton.setDisable(false);
        updateTurnLabel();
    }

    private void addCellClickListeners() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                int finalRow = row;
                int finalCol = col;
                board.getCell(row, col).setOnMouseClicked(event -> {
                    if (game.isGameStarted()) {
                        game.handleCellClick(board.getCell(finalRow, finalCol));
                        updateTurnLabel();
                        checkEndGame();
                    }
                });
            }
        }
    }

    @FXML
    public void startGame() {
        game.startGame();
        startGameButton.setDisable(true);
        updateTurnLabel();
    }

    private void updateTurnLabel() {
        Player currentPlayer = game.getCurrentPlayer();
        if (game.isGameStarted()) {
            turnLabel.setText("Turn: " + (currentPlayer.isWhite() ? "White" : "Black"));
        }
    }

    private void checkEndGame() {
        if (!game.isGameStarted()) {
            String winner = game.getCurrentPlayer().isWhite() ? "Black" : "White";
            turnLabel.setText("Gg Winner: " + winner);
        }
    }
}
