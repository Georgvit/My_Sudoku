

import rudevelopergeprgvi.controller.GameController;

import javax.swing.*;

import static rudevelopergeprgvi.view.SudokuGUI.createAndShowGui;

public class Main {
    public static void main(String[] args) {
        new GameController();

        SwingUtilities.invokeLater(() -> {
            createAndShowGui();
        });

    }


}