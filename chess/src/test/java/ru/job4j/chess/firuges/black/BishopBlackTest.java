package ru.job4j.chess.firuges.black;

import org.junit.Test;
import ru.job4j.chess.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class BishopBlackTest {

    /**
     * Создайте объект и вызовите у него метод position.
     * Проверьте, что он занимает ту же ячейку, что и при создании объекта.
     */
    @Test
    public void testPosition() {
        Figure bishopBlack = new BishopBlack(Cell.A7);
        assertThat(bishopBlack.position(), is(Cell.A7));
    }

    /**
     * Создайте объект и вызовите у него метод copy.
     * Проверьте, что, возвращенный объект имеет правильную позицию.
     */
    @Test
    public void testCopy() {
        Figure bishopBlack = new BishopBlack(Cell.A8);
        assertThat(bishopBlack.copy(Cell.A2), is(new BishopBlack(Cell.A2)));
    }

    /**
     * Нужно проверить, что если мы передали начальную и конечную ячейки,
     * через которые нельзя провести диагональ, нужно выкинуть исключение.
     */
    @Test(expected = ImpossibleMoveException.class)
    public void testWay() throws ImpossibleMoveException {
        Figure bishopBlack = new BishopBlack(Cell.C1);
        bishopBlack.way(Cell.C3);
    }
}