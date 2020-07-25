package Controler;

import Model.Board;
import Model.Cell;
import View.Printer;


public class BordController {

    Board board = new Board();
    Printer printer = new Printer();

    public BordController(){

        Cell[][] wholeboard = board.createBoard();
        board.setShipsOnBoard( wholeboard );
        printer.printBoard( wholeboard ) ;

    }

}
