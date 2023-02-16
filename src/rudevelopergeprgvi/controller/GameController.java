package rudevelopergeprgvi.controller;

import rudevelopergeprgvi.model.GameSolution;
import rudevelopergeprgvi.model.SudokuGame;
import rudevelopergeprgvi.view.SudokuGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class GameController implements Action {
    private static MouseListener mouseListener;

    private static SudokuGUI sudokuGUI;
    private static int tempI;
    private static int tempJ;

    private static boolean mouseListenerIsActive;
    static int[][] f = SudokuGame.generate();

    static SudokuGame s = new SudokuGame();
    static GameSolution gs = new GameSolution(f);


    public static void consolePribt() {
        System.out.print("Начальная сетка:\n");
        s.print(f);
        gs.solve();
        if (gs.decision(GameSolution.getmBoard())) {
            System.out.print("\nРешение:\n");
            s.print(GameSolution.getmBoard());
        } else {
            System.out.println("\nРешения нет!");
        }
    }


    public static MouseListener clicedMouseMap(int i, int j) {
        mouseListener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (mouseListenerIsActive) {
                    tempI = i;
                    tempJ = j;
                    SudokuGUI.getFieldGrid()[i][j].setBackground(Color.RED);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        };

        return mouseListener;
    }

    public static void clicedMouseСhoiceNumber(String num) {
        if (mouseListenerIsActive) {
            SudokuGUI.getFieldGrid()[tempI][tempJ].setText(num);
            SudokuGUI.getFieldGrid()[tempI][tempJ].setBackground(Color.GREEN);
            f[tempI][tempJ] = Integer.parseInt(num);
        }
    }

    public static void stopMouseListner() {
        mouseListenerIsActive = false;
    }

    public static void startMouseListner() {
        mouseListenerIsActive = true;
    }

    public  static void liteMenu(){
        JFrame jFrame = new JFrame();
        if(gs.decision(f)){
            JOptionPane.showMessageDialog(jFrame, "Решение существует");
        } else
            JOptionPane.showMessageDialog(jFrame, "Решение не существует");

    }
}
