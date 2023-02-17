package rudevelopergeprgvi.model;

public class GameSolution {
    private static int[][] board;
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

    public static void setBoard(int board, int i, int j) {
        GameSolution.board[i][j] = board;
    }

    //    Получение решенной карты игры
    public static int[][] getmBoard() {
        return board;
    }

    //    Иницилизация решения
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
//                System.out.println(board[i][j] + "  " + value);
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

    public boolean decision(int[][] board) {
        int temp;
        //  Проверка по столбцам
        int count = 0;
        for (int i = 0; i < board.length; i++) {
//            System.out.println("i " + i);
            for (int j = 0; j < board.length; j++) {
                temp = board[i][j];
                if (temp != 0) {
//                    System.out.println("temp " + temp);
                    for (int k = 0; k < board.length; k++) {
                        if (temp == board[i][k]) {
                            count++;
                        }
                    }
                    if (count > 1) {
                        return false;
                    }
                }
                count = 0;
            }
        }

        //  Проверка по строкам
        count = 0;
        for (int i = 0; i < board.length; i++) {
//            System.out.println("i " + i);
            for (int j = 0; j < board.length; j++) {
                temp = board[j][i];
                if (temp != 0) {
//                    System.out.println("temp " + temp);
                    for (int k = 0; k < board.length; k++) {
                        if (temp == board[k][i]) {
                            count++;
                        }
                    }
                    if (count > 1) {
                        return false;
                    }
                }
                count = 0;
            }
        }

        //  Проверка по ячейка 3х3
        count = 0;
        for (int i = 0; i < board.length; i += 3) {

            for (int j = 0; j < board.length; j += 3) {
                for (int t = i; t < boxSize + i; t++) {
                    for (int k = j; k < boxSize + j; k++) {
                        temp = board[t][k];
                        for (int e = i; e < boxSize + i; e++) {
                            for (int y = j; y < boxSize + j; y++) {
                                if (temp != 0) {
                                    if (temp == board[e][y]) {
                                        count++;
//                                        System.out.println(count);
                                    }
                                }
                                if (count >= 2) {
                                    System.out.println("count" + count);
                                    return false;
                                }
                            }
                        }
                        count = 0;
                    }

                }
            }
        }

        return true;
    }

}
