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

    public GameController() {

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
        boolean isGameAlive = true;

        printer.printMessage("START GAME");
        do {
            printer.gap();
            printer.printTwoBoards(board1, board2);

            if (flag) {
                int[] coordinates = getComputerCoordinates();
//                int[] coordinates = getInputCoordinates(player1.getName() + " podaj wspolrzedne : ");
                player1.hitEnemyBoard(coordinates[0], coordinates[1], board2);
                flag = false;
            } else {
                int[] coordinates = getComputerCoordinates();
//                int[] coordinates = getInputCoordinates(player2.getName() + " podaj wspolrzedne : ");
                player2.hitEnemyBoard(coordinates[0], coordinates[1], board1);
                flag = true;
            }
            isGameAlive = isGameAlive();
//            printer.sleepPrint(500);
        } while (isGameAlive);
    }

    private boolean isGameAlive( ) {
        boolean isGameAliveTemp  = true;
        if ( board1.isAllShipsDead()){
            printer.printMessage("The winner is : "+board2.getOwner().getName() );
            isGameAliveTemp = false;
        }
        if ( board2.isAllShipsDead()){
            printer.printMessage("The winner is : "+board1.getOwner().getName() );
            isGameAliveTemp = false;
        }
        return isGameAliveTemp;
    }

    private int[] getInputCoordinates(String s) {
        printer.printMessage(s);
        return inputOutput.getCoordinates();
    }

    private int[] getComputerCoordinates () {
        Random ran = new Random();
        int[] result = new int[2];
        result[0] = ran.nextInt(10);
        result[1] = ran.nextInt(10);
        return result;
    }
}
