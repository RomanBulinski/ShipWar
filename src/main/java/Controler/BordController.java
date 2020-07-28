package Controler;

import Model.Board;
import Model.CellInterface;
import View.InputOutput;
import View.Printer;

public class BordController {

    Board board = new Board();
    Printer printer = new Printer();
    InputOutput inputOutput = new InputOutput();

    public BordController(){

        printer.printMessage("START GAME");
        printer.gap();

        board.setBoard();
        board.setShipsOnBoard( );
        printer.printBoard( board ) ;

        while(true){

            printer.printMessage("Podaj rzad : ");
            int row = inputOutput.getInput();
            printer.printMessage("Podaj kolumne : ");
            int column = inputOutput.getInput();

            board.hitBoard(row,column,board.getBoard());
            printer.printBoard( board ) ;

        }
    }
}
