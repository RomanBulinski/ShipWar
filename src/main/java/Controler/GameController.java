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

//            int row = getInput("Podaj rzad : ");
//            int column = getInput("Podaj kolumne : ");

            Random ran = new Random();
            int row = ran.nextInt(10);
            int column = ran.nextInt(10);

            if (flag) {
                player1.hitEnemyBoard(row, column, board2);
                flag = false;
            } else {
                player2.hitEnemyBoard(row, column, board1);
                flag = true;
            }
            try {
                sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (true);
    }

    private int getInput(String s) {
        printer.printMessage(s);
        return inputOutput.getInput();
    }


}
