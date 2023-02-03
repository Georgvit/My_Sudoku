import rudevelopergeprgvi.model.GameSolution;
import rudevelopergeprgvi.model.SudokuGame;

public class Main {
    public static void main(String[] args) {

        int[][] f = SudokuGame.generate();
        SudokuGame s = new SudokuGame();
        GameSolution gs = new GameSolution(f);
        System.out.print("Сгенирированная сетка:\n");
        for (int i = 0; i < f.length; i++) {
            for (int j = 0; j < f.length; j++) {
                System.out.print(f[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.print("Начальная сетка:\n");
        s.print(f);
        if (gs.solve()) {
            System.out.print("\nРешение:\n");
            s.print(gs.getmBoard());
        } else {
            System.out.println("\nРешения нет!");
        }


    }


}