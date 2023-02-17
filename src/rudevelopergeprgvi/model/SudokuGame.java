package rudevelopergeprgvi.model;

import java.util.Random;


public class SudokuGame {
//    Размер поля
    private static int boardSize = 9;
    private static GameSolution gameSolution;
    private static boolean trueSolution;

    public static boolean getGameSolution() {
        return trueSolution;
    }

    //    Метод генерации карты игры
    public static int[][] generate() {
        int[][] board = new int[boardSize][boardSize];
        int numberOne = 1, numberTwo = 1;
        for (int i = 0; i < boardSize; i++) {
            numberOne = numberTwo;
            for (int j = 0; j < boardSize; j++) {
                if (numberOne <= boardSize) {
                    board[i][j] = numberOne;
                    numberOne++;
                } else {
                    numberOne = 1;
                    board[i][j] = numberOne;
                    numberOne++;
                }
            }
            numberTwo = numberOne + 3;
            if (numberOne == 10)
                numberTwo = 4;
            if (numberTwo > boardSize)
                numberTwo = (numberTwo % boardSize) + 1;
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
            int tempNumber = 0;
            for (int j = 0; j < board.length; j++) {
                tempNumber = board[t][j];
                board[t][j] = board[m][j];
                board[m][j] = tempNumber;
            }
        }
        return board;
    }

    //    Метод перемешивания столбцов
    public static int[][] mixCol(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            int t = new Random().nextInt(0, 9);
            int m = new Random().nextInt(0, 9);
            int tempNumber = 0;
            for (int j = 0; j < board.length; j++) {
                tempNumber = board[j][t];
                board[j][t] = board[j][m];
                board[j][m] = tempNumber;
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
