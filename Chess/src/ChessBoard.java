public class ChessBoard {
    private ChessPiece[][] board;

    public ChessBoard() {
        board = new ChessPiece[8][8];
    }

    private boolean isValidMove(int fromRow, int fromCol, int toRow, int toCol) {
        ChessPiece piece = board[fromRow][fromCol];
        Object nowPlayer = null;
        if (piece == null || !piece.getColor().equals(nowPlayer)) return false;

        switch (piece.getClass().getSimpleName()) {
            case "Rook":
                return isValidRookMove(fromRow, fromCol, toRow, toCol);
            case "Knight":
                return isValidKnightMove(fromRow, fromCol, toRow, toCol);
            default: 
                return Math.abs(fromRow - toRow) <= 1 && Math.abs(fromCol - toCol) <= 1;
        }
    }

    private boolean isValidRookMove(int fromRow, int fromCol, int toRow, int toCol) {
        return fromRow == toRow || fromCol == toCol;
    }

    private boolean isValidKnightMove(int fromRow, int fromCol, int toRow, int toCol) {
        int rowDiff = Math.abs(fromRow - toRow);
        int colDiff = Math.abs(fromCol - toCol);
        return (rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2); 
    }

    public boolean moveToPosition(int fromRow, int fromCol, int toRow, int toCol) {
        if (isValidMove(fromRow, fromCol, toRow, toCol)) {
            board[toRow][toCol] = board[fromRow][fromCol];
            board[fromRow][fromCol] = null;
            return true;
        }
        return false;
    }

    public void printBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == null) {
                    System.out.print("- ");
                } else {
                    System.out.print(board[i][j].toString() + " ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        ChessBoard chess = new ChessBoard();
        chess.initializeBoard();
        chess.printBoard();

        System.out.println("\nAttempting to move Knight from (0,1) to (2,2)...");
        if (chess.moveToPosition(0, 1, 2, 2)) {
            System.out.println("Move successful!");
            chess.printBoard();
        } else {
            System.out.println("Move failed!");
        }
    }

    private void initializeBoard() {

    }

    private class ChessPiece {
        private String color;

        public ChessPiece(String color) {
            this.color = color;
        }

        public String getColor() {
            return color;
        }

        @Override
        public String toString() {
            return "-";
        }
    }

}
