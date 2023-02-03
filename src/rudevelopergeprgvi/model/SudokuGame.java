package rudevelopergeprgvi.model;

import java.util.Random;

public class SudokuGame {
    private static int boardSize = 9;

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

        for (int i = 0; i < 40; i++) {
            int t = new Random().nextInt(0, 9);
            int m = new Random().nextInt(0, 9);
            board[t][m] = 0;

        }
        return board;
    }

    public void print(int[][] board) {
        int num = (int) Math.sqrt(board.length);
        for (int i = 0; i < board.length; i++) {
            if (i % num == 0) {
                System.out.println(" -----------------------");
            }
            for (int j = 0; j < board.length; j++) {
                if (j % num == 0) {
                    System.out.print("| ");
                }
                System.out.print(board[i][j] != 0 ? ((Object) (Integer.valueOf(board[i][j]))) : "-");
                System.out.print(' ');
            }
            System.out.println("|");


        }

        System.out.println(" -----------------------");
    }

}
