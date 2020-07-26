package Controler;

import Model.Board;
import Model.CellInterface;
import View.Printer;


public class BordController {

    Board board = new Board();
    Printer printer = new Printer();

    public BordController(){

        CellInterface[][] seaboard = board.createBoard();
        board.setShipsOnBoard( seaboard );
        printer.printBoard( seaboard ) ;
    }

}
