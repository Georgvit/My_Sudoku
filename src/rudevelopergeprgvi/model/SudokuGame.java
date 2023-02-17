package rudevelopergeprgvi.model;

import java.util.Random;

public class SudokuGame {
    private static int boardSize = 9;
    private static GameSolution gameSolution;
    private static boolean trueSolution;

    public static boolean getGameSolution() {
        return trueSolution;
    }

    //    Метод генерации карты игры
    public static int[][] generate() {
        int[][] board = new int[boardSize][boardSize];
        int k = 1, n = 1;
        for (int i = 0; i < boardSize; i++) {
            k = n;
            for (int j = 0; j < boardSize; j++) {
                if (k <= boardSize) {
                    board[i][j] = k;
                    k++;
                } else {
                    k = 1;
                    board[i][j] = k;
                    k++;
                }
            }
            n = k + 3;
            if (k == 10)
                n = 4;
            if (n > boardSize)
                n = (n % boardSize) + 1;
        }
        setZoro(board);

        do {
            mixRow(board);
            mixCol(board);
            gameSolution = new GameSolution(board);
        } while (!gameSolution.solve());
        trueSolution = gameSolution.solve();

    setZoro(board);
        return board;
    }

    //    Метод перемешивания строк
    public static int[][] mixRow(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            int t = new Random().nextInt(0, 9);
            int m = new Random().nextInt(0, 9);
            int temp = 0;
            for (int j = 0; j < board.length; j++) {
                temp = board[t][j];
                board[t][j] = board[m][j];
                board[m][j] = temp;
            }
        }
        return board;
    }

    //    Метод перемешивания столбцов
    public static int[][] mixCol(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            int t = new Random().nextInt(0, 9);
            int m = new Random().nextInt(0, 9);
            int temp = 0;
            for (int j = 0; j < board.length; j++) {
                temp = board[j][t];
                board[j][t] = board[j][m];
                board[j][m] = temp;
            }
        }
        return board;
    }

    //    Установка нулевых значений на карте игры
    private static int[][] setZoro(int[][] board) {
        for (int i = 0; i < 80; i++) {
            int t = new Random().nextInt(0, 9);
            int m = new Random().nextInt(0, 9);
            board[t][m] = 0;

        }
        return board;
    }


}
