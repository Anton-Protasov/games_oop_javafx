package ru.job4j.puzzle;

/*
Класс Win осуществляет проверку выигрышной комбинации на игровом поле
Лучше всего будет добавить еще 2 дополнительных метода, которые будут проверять строку и столбец
на нашем игровом поле, которые мы будем вызывать в методе check() при определенных условиях.

Решение:
Так или иначе если строка заполнена или столбец заполнен, то будет пересечение с дигональю.
Т.е. Необходимо взять диагональ и проверить:
1 - есть ни на диагонале заполненная ячека;
2 - запоминаем индексы элемента, если такой найден;
3 - метод 1 - прогоняет все столбцы по заданному индесу строки;
4 - метод 2 - прогоняет все строки по заданному индесу столбца;
5 - выйгрышная комбинация тогда - когда либо метод 1 либо метод 2 - true

p.s. по коду теста видим, что заполненная ячейка - ячейка в которой стоит 1.
 */

public class Win {
    public static boolean check(int[][] board) {
        boolean rsl = false;
        for (int i = 0; i < board.length; i++) {
            if (board[i][i] == 1) {
                if (checkRow(board) || checkCollumn(board)) {
                    rsl = true;
                    break;
                }
            }
        }
        return rsl;
    }

    // Проверяем заполнена ли строка:

    public static boolean checkRow(int[][] array) {
        boolean row = true;
        for (int i = 0; i < array.length; i++) {
            if (array[i][i] == 1) {
                for (int j = 0; j < array.length; j++) {
                    if (array[i][j] != 1) {
                        row = false;
                        break;
                    }
                }
            }
        }
        return row;
    }

    // Проверяем заполненен ли столбец:

    public static boolean checkCollumn(int[][] array) {
        boolean collumn = true;
        for (int i = 0; i < array.length; i++) {
            if (array[i][i] == 1) {
                for (int j = 0; j < array.length; j++) {
                    if (array[j][i] != 1) {
                        collumn = false;
                        break;
                    }
                }
            }
        }
        return collumn;
    }
}