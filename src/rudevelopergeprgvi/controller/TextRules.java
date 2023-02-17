package rudevelopergeprgvi.controller;

import java.io.*;

public class TextRules {
    
    public static String textRules(){
        String textRules = """
                Помните!
                В каждой ячейке может
                находиться одно
                единственное число.
                Для заполнения Судоку
                таблицы используйте
                следующие ряд чисел:
                1, 2, 3, 4, 5, 6, 7, 8 и 9.
                В каждом секторе 3×3 клетки
                должны быть
                расположены числа
                от 1 до 9 без повторений.
                В каждом вертикальном
                столбце должны быть 
                расположены числа
                от 1 до 9 без повторений.
                В каждой горизонтальной
                строке должны быть
                расположены числа
                от 1 до 9 без повторений.""";

        return textRules;
    }

    public static String textAutor(){
        String textAutor = """
                Автор:
                Georgvit
                https://github.com/Georgvit/My_Sudoku""";

        return textAutor;
    }

    public static String textResult(){
        String textResult = "Поздравляем!\nВы выиграли!!!";
        return textResult;
    }
}
