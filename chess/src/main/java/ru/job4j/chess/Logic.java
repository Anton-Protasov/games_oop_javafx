package ru.job4j.chess;

import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;
import java.util.Arrays;
import ru.job4j.chess.OccupiedCellException;
import ru.job4j.chess.FigureNotFoundException;
import ru.job4j.chess.ImpossibleMoveException;

public final class Logic {
    private final Figure[] figures = new Figure[32];
    private int index = 0;

    public void add(Figure figure) {
        figures[index++] = figure;
    }

    /*
     * Метод move:
     * 1. По объекту типа Cell source в массиве figures найти объект типа Figure.
     * Для этого используется метод findBy. Он возвращает индекс ячейки или выкидывает исключение.
     * 2. Если объект найден, то нужно получить его ходы до клетки Cell dest.
     * Это нужно сделать через метод way объекта Figure.
     * 3. Дальше нужно проверить, что массив клеток от метода way не заполнен другими объектами класса Figure.
     * Если он не заполнен, то нужно в массив figures в позицию, полученную в пункте 1, записать новый объект,
     * полученный из метода figure.copy.
     */
    public void move(Cell source, Cell dest)
            throws FigureNotFoundException,OccupiedCellException, ImpossibleMoveException {
            int index = findBy(source);
            Cell[] steps = figures[index].way(dest);
            free(steps);
            figures[index] = figures[index].copy(dest);
    }

    /*
     * Метод free должен пройтись по массиву figures и проверить, что фигуры не занимают элементы из массива steps.
     * Если они занимают ячейки steps, то метод должен кинуть исключение.
     */
    private boolean free(Cell[] steps) throws OccupiedCellException {
        for (Cell stepCheck: steps) {
            for (Figure fig: figures) {
                if (fig != null && fig.position().equals(stepCheck)) {
                    throw new OccupiedCellException (
                            String.format("Cell %s is occupied by %s", stepCheck, fig.position())
                    );
                }
            }
        }
        return true;
    }

    public void clean() {
        Arrays.fill(figures, null);
        index = 0;
    }

    private int findBy(Cell cell) throws FigureNotFoundException {
        for (int index = 0; index != figures.length; index++) {
            Figure figure = figures[index];
            if (figure != null && figure.position().equals(cell)) {
                return index;
            }
        }
        throw new FigureNotFoundException("Figure not found");
    }
}
