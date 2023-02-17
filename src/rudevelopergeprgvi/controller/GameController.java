package rudevelopergeprgvi.controller;

import rudevelopergeprgvi.model.GameSolution;
import rudevelopergeprgvi.model.SudokuGame;
import rudevelopergeprgvi.view.SudokuGUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class GameController {
    private static MouseListener mouseListener;
    private static int tempI;
    private static int tempJ;
    private static JFrame jFrame;
    private static boolean mouseListenerIsActive;
    static int[][] realMass;
    static SudokuGame sudokuGame;
    static GameSolution gameSolution;

    public GameController() {
        realMass = SudokuGame.generate();
        sudokuGame = new SudokuGame();
        gameSolution = new GameSolution(realMass);
    }


    public static boolean trueFunction() {
        return gameSolution.decision(GameSolution.getmBoard());
    }

    public static int[][] ArrayOfSolutions() {
        gameSolution.solve();
        if (!trueFunction()) {
            return null;
        }
        return GameSolution.getmBoard();
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
            GameSolution.setBoard(Integer.parseInt(num), tempI, tempJ);
            finalWindowGame();
        }
    }

    public static void stopMouseListner() {
        mouseListenerIsActive = false;
    }

    public static void startMouseListner() {
        mouseListenerIsActive = true;
    }

    public static void liteWindow() {
        jFrame = new JFrame();
        if (gameSolution.decision(realMass)) {
            JOptionPane.showMessageDialog(jFrame, "Решение существует", "Решение", JOptionPane.PLAIN_MESSAGE);
        } else
            JOptionPane.showMessageDialog(jFrame, "Решение не существует", "Решение", JOptionPane.PLAIN_MESSAGE);

    }

    public static void liteWindowAuthor() {
        jFrame = new JFrame();
        JOptionPane.showMessageDialog(jFrame, TextRules.textAuthor(), "О программе", JOptionPane.PLAIN_MESSAGE);
    }

    public static void finalWindowGame() {
        int[][] tempMass = SudokuGUI.tempMap();
        int count = 0;
        if (trueFunction()) {
            for (int i = 0; i < tempMass.length; i++) {
                for (int j = 0; j < tempMass.length; j++) {
                    if (tempMass[i][j] == 0) {
                        count++;
                    }
                }
            }
            if (count == 0) {
                jFrame = new JFrame();
                JOptionPane.showMessageDialog(jFrame, TextRules.textResult(), "Результат игры", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }
}
