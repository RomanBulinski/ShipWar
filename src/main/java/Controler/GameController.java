package Controler;

import Enums.PlayerTypeEnum;
import Model.Board;
import Model.Game;
import Model.Player;
import View.InputOutput;
import View.Printer;

public class GameController {

    Game game = new Game();
    Board board1 = new Board();
    Board board2 = new Board();
    Player player1;
    Player player2;
    Printer printer = new Printer();
    InputOutput inputOutput = new InputOutput();

    public GameController(){

        board1.setBoard();
        board1.setShipsOnBoard();

        board2.setBoard();
        board2.setShipsOnBoard();

        game.setPlayers();
        game.addPlayer(player1);
        game.addPlayer(player2);

        player1 = new Player("Jarosław", PlayerTypeEnum.HUMAN, board1);
        player2 = new Player("Lech", PlayerTypeEnum.COMPUTER, board2);

        printer.printBoard(board1);
        printer.printBoard(board2);

        while(true){

            printer.printMessage("Podaj rzad : ");
            int row = inputOutput.getInput();
            printer.printMessage("Podaj kolumne : ");
            int column = inputOutput.getInput();

            board1.hitBoard(row,column,board1.getBoard());
            printer.gap();
            printer.printBoard( board1 ) ;

            board2.hitBoard(row,column,board2.getBoard());
            printer.gap();
            printer.printBoard( board2 ) ;

        }

    }




}
