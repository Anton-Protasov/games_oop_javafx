package ru.job4j.chess;

public class OccupiedCellException extends FigureNotFoundException {
    public OccupiedCellException(String message){
        super(message);
    }
}
