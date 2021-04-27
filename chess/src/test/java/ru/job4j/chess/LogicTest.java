package ru.job4j.chess;

// import org.junit.Ignore;
import org.junit.Assert;
import org.junit.Test;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.black.BishopBlack;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class LogicTest {

    //    @Ignore
    @Test
    public void testAdd() throws FigureNotFoundException {
        Logic logic = new Logic();
        logic.add(new BishopBlack(Cell.C1));
        logic.add(new BishopBlack(Cell.B2));
        logic.add(new BishopBlack(Cell.F3));
        assertThat(logic.findBy(Cell.B2), is(1));
    }

    @Test
    public void testFreeFalse1()
            throws FigureNotFoundException, OccupiedCellException, ImpossibleMoveException {
        Logic logic = new Logic();
        logic.add(new BishopBlack(Cell.C1));
        logic.add(new BishopBlack(Cell.E3));
        boolean rsl = logic.free(new Cell[] {Cell.D2, Cell.E3, Cell.F4, Cell.G5});
        assertThat(rsl,is(false));
    }

    @Test(expected = OccupiedCellException.class)
    public void testFreeFalse2() throws OccupiedCellException {
        Logic logic = new Logic();
        logic.add(new BishopBlack(Cell.C1));
        logic.add(new BishopBlack(Cell.E3));
        boolean rsl = logic.free(new Cell[] {Cell.D2, Cell.E3, Cell.F4, Cell.G5});
    }

    @Test
    public void testFreeCorrect() throws OccupiedCellException {
        Logic logic = new Logic();
        logic.add(new BishopBlack(Cell.C1));
        logic.add(new BishopBlack(Cell.E5));
        boolean rsl = logic.free(new Cell[] {Cell.D2, Cell.E3, Cell.F4, Cell.G5});
        assertThat(rsl,is(true));
    }

    @Test
    public void testMoveAvailable()
            throws FigureNotFoundException, OccupiedCellException, ImpossibleMoveException {
        Logic logic = new Logic();
        logic.add(new BishopBlack(Cell.C1));
        logic.add(new BishopBlack(Cell.E5));
        logic.move(Cell.C1, Cell.G5);
        assertThat(logic.findBy(Cell.G5), is(0));
    }

    @Test
    public void testMoveImpossible()
            throws FigureNotFoundException, OccupiedCellException, ImpossibleMoveException {
        Logic logic = new Logic();
        logic.add(new BishopBlack(Cell.C1));
        logic.add(new BishopBlack(Cell.E3));
        logic.move(Cell.C1, Cell.G5);
        assertThat(logic.findBy(Cell.G5), is(0));
    }
}

