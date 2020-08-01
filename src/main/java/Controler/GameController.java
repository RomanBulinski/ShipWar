package Controler;

import Enums.PlayerTypeEnum;
import Model.Board;
import Model.Game;
import Model.Player;
import View.InputOutput;
import View.Printer;

import java.util.Random;

import static java.lang.Thread.sleep;

public class GameController {

    Game game = new Game();
    Board board1 = new Board();
    Board board2 = new Board();
    Player player1;
    Player player2;
    Printer printer = new Printer();
    InputOutput inputOutput = new InputOutput();

    public GameController()  {

        board1.setBoard();
        board1.setShipsOnBoard();

        board2.setBoard();
        board2.setShipsOnBoard();

        game.setPlayers();
        game.addPlayer(player1);
        game.addPlayer(player2);

        player1 = new Player("Jarosław", PlayerTypeEnum.HUMAN, board1);
        player2 = new Player("Lech", PlayerTypeEnum.COMPUTER, board2);

        boolean flag = true;

        do {
            printer.gap();
            printer.printTwoBoards(board1, board2);

//            Random ran = new Random();
//            int row = ran.nextInt(10);
//            int column = ran.nextInt(10);

            if (flag) {
                int row = getInput(player1.getName()+" podaj rzad : ");
                int column = getInput(player1.getName()+"Podaj kolumne : ");
                player1.hitEnemyBoard(row, column, board2);
                flag = false;
            } else {
                int row = getInput(player2.getName()+" podaj rzad : ");
                int column = getInput(player2.getName()+"Podaj kolumne : ");
                player2.hitEnemyBoard(row, column, board1);
                flag = true;
            }
//            printer.sleepPrint(500);
        } while (true);
    }

    private int getInput(String s) {
        printer.printMessage(s);
        return inputOutput.getInput();
    }


}
