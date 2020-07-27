package Controler;

import Model.Board;
import Model.CellInterface;
import View.InputOutput;
import View.Printer;


public class BordController {

    Board board = new Board();
    Printer printer = new Printer();

    public BordController(){

        printer.printMessage("START GAME");

        CellInterface[][] seaboard = board.createBoard();
        InputOutput inputOutput = new InputOutput();
        board.setShipsOnBoard( seaboard );
        printer.printBoard( seaboard ) ;

        while(true){

            printer.printMessage("Podaj rzad : ");
            int row = inputOutput.getInput();
            printer.printMessage("Podaj kolumne : ");
            int column = inputOutput.getInput();

            board.hitBoard(row,column,seaboard);
            printer.printBoard( seaboard ) ;

        }



    }

}
