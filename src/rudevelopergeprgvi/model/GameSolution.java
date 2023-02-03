package rudevelopergeprgvi.model;

public class GameSolution {
    private int board[][];
    private int boardSize;
    private int boxSize;
    private boolean rowSubset[][];
    private boolean colSubset[][];
    private boolean boxSubset[][];

    public GameSolution(int board[][]) {
        this.board = board;
        boardSize = board.length;
        boxSize = (int) Math.sqrt(boardSize);
        initSubsets();
    }

    public int[][] getmBoard() {
        return board;
    }

    public void initSubsets() {
        rowSubset = new boolean[boardSize][boardSize];
        colSubset = new boolean[boardSize][boardSize];
        boxSubset = new boolean[boardSize][boardSize];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                int value = board[i][j];
                if (value != 0) {
                    setSubsetValue(i, j, value, true);
                }
            }
        }
    }

    private void setSubsetValue(int i, int j, int value, boolean present) {
        rowSubset[i][value - 1] = present;
        colSubset[j][value - 1] = present;
        boxSubset[computeBoxNo(i, j)][value - 1] = present;
    }


    public boolean solve() {
        return solve(0, 0);
    }

    public boolean solve(int i, int j) {
        if (i == boardSize) {
            i = 0;
            if (++j == boardSize) {
                return true;
            }
        }
        if (board[i][j] != 0) {
            return solve(i + 1, j);
        }
        for (int value = 1; value <= boardSize; value++) {
            if (isValid(i, j, value)) {
                board[i][j] = value;
                System.out.print(board[i][j] + "  ");
                setSubsetValue(i, j, value, true);
                if (solve(i + 1, j)) {
                    return true;
                }
                setSubsetValue(i, j, value, false);
            }
        }

        board[i][j] = 0;
        return false;
    }

    private boolean isValid(int i, int j, int val) {
        val--;
        boolean isPresent = rowSubset[i][val] || colSubset[j][val] || boxSubset[computeBoxNo(i, j)][val];
        return !isPresent;
    }

    private int computeBoxNo(int i, int j) {
        int boxRow = i / boxSize;
        int boxCol = j / boxSize;
        int k = boxRow * boxSize + boxCol;
        return boxRow * boxSize + boxCol;
    }

}
