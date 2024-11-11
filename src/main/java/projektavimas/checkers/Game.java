package projektavimas.checkers;

public class Game {
    private final Board board;
    private Piece selectedPiece;
    private Cell selectedCell;
    private Player currentPlayer;
    private final Player whitePlayer;
    private final Player blackPlayer;
    private boolean gameStarted = false;

    public Game(Board board) {
        this.board = board;
        this.whitePlayer = new Player(true, 7);
        this.blackPlayer = new Player(false, 0);
        this.currentPlayer = whitePlayer; // Assume white starts
        board.initializeBoard();
        board.initializeRegularPieces(whitePlayer, blackPlayer);
        board.initializeKingPieces(whitePlayer, blackPlayer);
        addListeners();
    }

    private void addListeners() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Cell cell = board.getCell(row, col);
                cell.setOnMouseClicked(event -> handleCellClick(cell));
            }
        }
    }

    public void startGame() {
        gameStarted = true;
    }

    public void handleCellClick(Cell cell) {
        if (!gameStarted) {
            System.out.println("Game hasn't started");
            return;
        }

        if (selectedPiece == null) {
            selectedPiece = cell.getPiece();
            if (!cell.isEmpty() && currentPlayer.belongsPiece(selectedPiece)) {
                System.out.println("Piece selected at (" + cell.getRow() + ", " + cell.getColumn() + ")");
                selectedCell = cell;
                selectedPiece.highlightPiece();
            } else {
                selectedPiece = null;
            }
        } else {
            if (!cell.isEmpty() && selectedCell == cell) {
                selectedPiece.removeHighlight();
                selectedPiece = null;
            } else if (cell.isEmpty() && selectedPiece.isValidCapture(cell.getRow(), cell.getColumn(), currentPlayer) && currentPlayer.belongsPiece(selectedPiece)) { //groovy capture
                int middleRow = (selectedCell.getRow() + cell.getRow()) / 2;
                int middleCol = (selectedCell.getColumn() + cell.getColumn()) / 2;
                Piece capturedPiece = board.getCell(middleRow, middleCol).getPiece();
                Cell captureCell = board.getCell(middleRow, middleCol);
                if (capturedPiece != null && !currentPlayer.belongsPiece(capturedPiece)) {
                    captureCell.removePiece();
                    captureCell.setIsEmpty(true);
                    currentPlayer.addPiecesCaptures();
                    board.movePiece(selectedPiece, cell.getRow(), cell.getColumn());
                    selectedPiece.removeHighlight();
                    selectedPiece = null;
                    System.out.println("Gotcha at (" + middleRow + ", " + middleCol + ")");
                    if(currentPlayer.getPiecesCaptured() ==8) {
                        switchTurn();
                    }
                }
            } else if (cell.isEmpty() && selectedPiece.isValidMove(cell.getRow(), cell.getColumn(), currentPlayer) && currentPlayer.belongsPiece(selectedPiece)) { //groovy mooooves
                board.movePiece(selectedPiece, cell.getRow(), cell.getColumn());
                selectedPiece.removeHighlight();
                selectedPiece = null;
                System.out.println("We moving");
                switchTurn();
            } else {
                System.out.println("No go");
            }
        }

        System.out.println(currentPlayer.getPiecesCaptured());
        checkGameOver();
    }

    public void switchTurn() {
        if (selectedPiece != null) {
            selectedPiece.removeHighlight();
            selectedPiece = null;
            selectedCell = null;
        }
        currentPlayer = (currentPlayer == whitePlayer) ? blackPlayer : whitePlayer;
    }

    public void checkGameOver() {
        if (whitePlayer.getPiecesCaptured() == 8 || blackPlayer.getPiecesCaptured() == 8) {
            endGame();
        }
    }

    public void endGame() {
        Player winner = (currentPlayer == whitePlayer) ? blackPlayer : whitePlayer;
        gameStarted = false;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Player getBlackPlayer() {
        return blackPlayer;
    }

    public Player getWhitePlayer() {
        return whitePlayer;
    }

    public boolean isGameStarted() {
        return gameStarted;
    }
}
