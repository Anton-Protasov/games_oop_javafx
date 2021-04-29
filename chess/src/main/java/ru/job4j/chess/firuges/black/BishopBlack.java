package ru.job4j.chess.firuges.black;

import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;
import ru.job4j.chess.ImpossibleMoveException;

import java.util.Objects;

public class BishopBlack implements Figure {
    private final Cell position;

    public BishopBlack(final Cell ps) {
        position = ps;
    }

    @Override
    public Cell position() {
        return position;
    }

    /*
     * 1. Движение слона происходит по диагонали. Мы знаем начальную (клетка в которой мы создали объект)
     * и конечную точку движения фигуры (клетка, которую мы передали в метод).
     * По этим координатам мы можем понять, движется ли слон по диагонали.
     * 2. Если он движется по диагонали, то мы можем вычислить дельты шагов.
     * 3. Слон может двигаться в четыре стороны. Эти движения можно описать двумя дельтами +1 -1.
     * Например, если слон двигается вниз влево, то дельты будут -1 -1.
     */
    @Override
    public Cell[] way(Cell dest) throws ImpossibleMoveException {

        /*
         * Валидация входных параметров
         * Пользователь может перетащить слона на клетку, которая отличается от диагонали.
         * В этом случае наш код не будет работать.
         */

        if (!isDiagonal(position, dest)) {
            throw new ImpossibleMoveException (
                    String.format("Could not move by diagonal from %s to %s", position, dest)
            );
        }
            int size = Math.abs(position.getX() - dest.getX());
            Cell[] steps = new Cell[size];
            int deltaX = dest.getX() - position.getX() > 0 ? 1 : -1;
            int deltaY = dest.getY() - position.getY() > 0 ? 1 : -1;
            int x = position.getX();
            int y = position.getY();
            for (int i = 0; i < size; i++) {
                x += deltaX;
                y += deltaY;
                steps[i] = Cell.findBy(x, y);
            }
            return steps;
    }

    public boolean isDiagonal(Cell source, Cell dest) {
        return Math.abs(source.getX() - dest.getX()) == Math.abs(source.getY() - dest.getY());
    }

    @Override
    public Figure copy(Cell dest) {
        return new BishopBlack(dest);
    }

    /*
     * Переопределяем метод equals для объектов BishopBlack для возможности работать с объектами
     * (сравнивать и тд и тп.)
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BishopBlack that = (BishopBlack) o;
        return position == that.position;
    }

    @Override
    public int hashCode() {
        return Objects.hash(position);
    }
}
