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
    static int[][] realMass;

    static SudokuGame sudokuGame;
    static GameSolution gameSolution;

    public GameController() {
        realMass = SudokuGame.generate();
        sudokuGame = new SudokuGame();
        gameSolution = new GameSolution(realMass);
    }

    public static void consolePribt() {
        System.out.print("Начальная сетка:\n");
        sudokuGame.print(realMass);
        gameSolution.solve();
        if (gameSolution.decision(GameSolution.getmBoard())) {
            System.out.print("\nРешение:\n");
            sudokuGame.print(GameSolution.getmBoard());
        } else {
            System.out.println("\nРешения нет!");
        }
    }

    public static boolean truFunction() {
        return gameSolution.decision(GameSolution.getmBoard());
    }

    public static int[][] consoleMass() {
        gameSolution.solve();
        if (!truFunction()) {
            return null;
//            liteMenu();
        }
        return GameSolution.getmBoard();
    }

    public static SudokuGUI getSudokuGUI() {
        return sudokuGUI;
    }

    public static int[][] getRealMass() {
        return realMass;
    }

    public static SudokuGame getSudokuGame() {
        return sudokuGame;
    }

    public static GameSolution getGameSolution() {
        return gameSolution;
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
        }
    }

    public static void stopMouseListner() {
        mouseListenerIsActive = false;
    }

    public static void startMouseListner() {
        mouseListenerIsActive = true;
    }

    public static void liteMenu() {
        JFrame jFrame = new JFrame();
        if (gameSolution.decision(realMass)) {
            JOptionPane.showMessageDialog(jFrame, "Решение существует","Решение", JOptionPane.PLAIN_MESSAGE);
        } else
            JOptionPane.showMessageDialog(jFrame, "Решение не существует","Решение", JOptionPane.PLAIN_MESSAGE);

    }

    public static void liteMenuAutor() {
        JFrame jFrame = new JFrame();
        JOptionPane.showMessageDialog(jFrame, TextRules.textAutor(), "О программе", JOptionPane.PLAIN_MESSAGE);
    }
}
