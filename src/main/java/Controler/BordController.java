package Controler;

import Model.Board;
import Model.Cell;
import View.Printer;


public class BordController {

    Board board = new Board();
    Printer printer = new Printer();

    Cell[][] allboard = board.createBoard();

    public BordController(){

        printer.printBoard( allboard) ;

    }

}
