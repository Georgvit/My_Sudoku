package rudevelopergeprgvi.view;

import rudevelopergeprgvi.controller.GameController;
import rudevelopergeprgvi.controller.TextRules;
import rudevelopergeprgvi.model.GameSolution;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class SudokuGUI extends JPanel {
    //  Размер сетки
    private static final int GROUP = 3;
    //  Максимальное количество строк
    private static final int MAX_ROWS = 9;
    //  Шрифт текста в ячейках
    private static final float FONT_SIZE = 32f;
    //  Толщина разметки между группами
    private static final int BORDER = 2;
    //  Цвет разметки
    private static final Color BG = Color.BLACK;

    //  Цвет фона при загрузке решения
    private static final Color SOLVED_BG = Color.LIGHT_GRAY;
    private static final Button[] buttons = new Button[MAX_ROWS];
    private static Button button;
    private static JButton buttonTestSolution;
    private static GameSolution gameSolution;


    private static int[][] tempsMap = new int[MAX_ROWS][MAX_ROWS];


    //  Время задержки
    public static final int TIMER_DELAY = 300;

    // Массив текстовых полей
    private static JTextField[][] fieldGrid = new JTextField[MAX_ROWS][MAX_ROWS];

    public SudokuGUI() {
        JPanel mainPanel = new JPanel(new GridLayout(GROUP, GROUP));
        JPanel sPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel wPanel = new JPanel();
        JPanel newwPanel = new JPanel();
        JPanel ePanel = new JPanel();
        JTextArea textField = new JTextArea();
        Box buttonBox = Box.createHorizontalBox();
        Box buttonBox2 = Box.createHorizontalBox();
        buttonBox.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(BORDER, BORDER, BORDER, BORDER));
        mainPanel.setBackground(BG);
        JPanel[][] panels = new JPanel[GROUP][GROUP];
        for (int i = 0; i < panels.length; i++) {
            for (int j = 0; j < panels[i].length; j++) {
                panels[i][j] = new JPanel(new GridLayout(GROUP, GROUP, 1, 1));
                panels[i][j].setBackground(BG);
                panels[i][j].setBorder(BorderFactory.createEmptyBorder(BORDER, BORDER, BORDER, BORDER));
                mainPanel.add(panels[i][j]);
            }
        }

        for (int row = 0; row < fieldGrid.length; row++) {
            for (int col = 0; col < fieldGrid[row].length; col++) {
                fieldGrid[row][col] = createField(row, col);
                fieldGrid[row][col].setBackground(Color.WHITE);
                int i = row / 3;
                int j = col / 3;
                panels[i][j].add(fieldGrid[row][col]);
                fieldGrid[row][col].setEditable(false);

            }
        }


        for (int i = 0; i < buttons.length; i++) {
            button = new Button();
            button.setPreferredSize(new Dimension(61, 40));
            button.setLabel(String.valueOf(i + 1));
            int finalI = i;
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    GameController.clicedMouseСhoiceNumber(String.valueOf(finalI + 1));
                }
            });
            buttonBox.add(button);
        }

        buttonTestSolution = new JButton("Проверить решение");
        buttonTestSolution.setPreferredSize(new Dimension(235, 40));
        buttonTestSolution.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        buttonTestSolution.setBackground(Color.LIGHT_GRAY);
        buttonTestSolution.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameController.liteMenu();
            }
        });
        newwPanel.add(buttonTestSolution);
//        newwPanel.setComponentOrientation(ComponentOrientation.UNKNOWN);
        buttonBox2.add(newwPanel);
        wPanel.add(buttonBox2);
        textField.setPreferredSize(new Dimension(250, 500));
        textField.setText(TextRules.textRules());
        textField.setFont(new Font(TextRules.textRules(), Font.BOLD, 16));
        textField.setEditable(false);
        ePanel.add(textField);

        sPanel.add(buttonBox).setBackground(Color.BLACK);
        sPanel.add(wPanel);
        sPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        setBorder(BorderFactory.createEmptyBorder(BORDER, BORDER, BORDER, BORDER));
        setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);
        add(ePanel, BorderLayout.EAST);
        add(sPanel, BorderLayout.SOUTH);
//        add(wPanel, BorderLayout.WEST);

        JMenuBar menuBar = new JMenuBar();
        // Добавление в главное меню выпадающих пунктов меню
        menuBar.add(createFileMenu());
        menuBar.add(createViewMenu());
        // Подключаем меню к интерфейсу приложения
        add(menuBar, BorderLayout.NORTH);
        createGameMap();


    }


    private JMenu createFileMenu() {
        // Создание выпадающего меню
        JMenu file = new JMenu("Игра");
        // Пункт меню "Открыть"
        JMenuItem open = new JMenuItem("Новая игра");
        // Пункт меню из команды с выходом из программы
        JMenuItem exit = new JMenuItem("Выход");

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        // Добавим в меню пункта open
        file.add(open);
        // Добавление разделителя
        file.add(exit);

        open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                new GameController();
                clearGameMap();

                createGameMap();
            }
        });
        return file;
    }


    private JMenu createViewMenu() {
        // создадим выпадающее меню
        JMenu viewMenu = new JMenu("Помощь");
        // меню-переключатели
        JMenuItem one = new JMenuItem(new SolveAction("Показать решение"));
        JMenuItem two = new JMenuItem("О программе");
        two.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameController.liteMenuAutor();
            }
        });
        // организуем переключатели в логическую группу
        ButtonGroup bg = new ButtonGroup();
        bg.add(one);
        bg.add(two);
        // добавим все в меню
        // разделитель можно создать и явно
        viewMenu.add(new JSeparator());
        viewMenu.add(one);
        viewMenu.add(two);
        return viewMenu;
    }

    private JTextField createField(int row, int col) {
        JTextField field = new JTextField(2);
        field.setHorizontalAlignment(JTextField.CENTER);
        field.setFont(field.getFont().deriveFont(Font.BOLD, FONT_SIZE));
        return field;
    }

    public static JTextField[][] getFieldGrid() {
        return fieldGrid;
    }


    private void createGameMap() {
        for (int i = 0; i < MAX_ROWS; i++) {
            for (int j = 0; j < MAX_ROWS; j++) {
                if (GameSolution.getmBoard()[i][j] != 0) {
                    int number = GameSolution.getmBoard()[i][j];
                    fieldGrid[i][j].setText(String.valueOf(number));
                } else {
                    GameController.startMouseListner();
                    fieldGrid[i][j].addMouseListener(GameController.clicedMouseMap(i, j));
                }
            }
        }
    }

    private void clearGameMap() {
        for (int i = 0; i < MAX_ROWS; i++) {
            for (int j = 0; j < MAX_ROWS; j++) {
                fieldGrid[i][j].setText(null);
                fieldGrid[i][j].setBackground(Color.WHITE);

            }
        }
    }

    public static int[][] tempMap() {
        for (int i = 0; i < fieldGrid.length; i++) {
            for (int j = 0; j < fieldGrid.length; j++) {
                if (fieldGrid[i][j].getText() != null) {
                    tempsMap[i][j] = Integer.parseInt(fieldGrid[i][j].getText());
                    System.out.println(tempsMap[i][j]);
                } else {
                    tempsMap[i][j] = 0;
                }
            }
        }
        return tempsMap;
    }

    private class SolveAction extends AbstractAction {

        public SolveAction(String name) {
            super(name);
            int mnemonic = (int) name.charAt(0);
            putValue(MNEMONIC_KEY, mnemonic);
        }


        @Override
        public void actionPerformed(ActionEvent e) {
            int[][] tmp = GameController.consoleMass();
            if (GameController.truFunction()) {
                GameController.consolePribt();
                new Timer(TIMER_DELAY, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        for (int i = 0; i < MAX_ROWS; i++) {
                            for (int j = 0; j < MAX_ROWS; j++) {
                                int number = tmp[i][j];
                                fieldGrid[i][j].setBackground(SOLVED_BG);
                                fieldGrid[i][j].setText(String.valueOf(number));
                                GameController.stopMouseListner();
                                ((Timer) e.getSource()).stop();
                            }
                            ((Timer) e.getSource()).stop();
                        }
                    }
                }).start();

            } else {
                GameController.liteMenu();
            }
        }

    }


    public static void createAndShowGui() {
        SudokuGUI mainPanel = new SudokuGUI();

        JFrame frame = new JFrame("СУДОКУ");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setResizable(false);
        frame.setVisible(true);
    }

}

