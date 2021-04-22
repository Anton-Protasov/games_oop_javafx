package ru.job4j.chess;

public class ImpossibleMoveException extends OccupiedCellException {
    public ImpossibleMoveException(String message) {
        super(message);
    }
}
