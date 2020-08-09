package Controler;

import Enums.PlayerTypeEnum;
import Model.Board;
import Model.Game;
import Model.Player;
import View.InputOutput;
import View.Printer;

import java.util.Random;

public class GameController {

    Game game = new Game();
    Board board1 = new Board();
    Board board2 = new Board();
    Player player1;
    Player player2;
    Printer printer = new Printer();
    InputOutput inputOutput = new InputOutput();
    boolean isCellHited;

    public GameController() {

        boolean isPlayer1CanHit = true;
        boolean isGameAlive ;

        board1.setBoard();
        board1.setShipsOnBoard();
        board2.setBoard();
        board2.setShipsOnBoard();

        game.setPlayers();
        game.addPlayer(player1);
        game.addPlayer(player2);

        printer.printMessage("START GAME");
        printer.gotoNextLine();

        printer.printMenu(new String[]{"Human vs. Human", "Human vs. computer", "computer vs. computer"});
        printer.gotoNextLine();
        printer.printMessage("Choose type game : ");
//        printer.gotoNextLine();
        int chosenGameType = inputOutput.getIntInput();

        if(chosenGameType==1){
            setPlayers(PlayerTypeEnum.HUMAN, PlayerTypeEnum.HUMAN);
        }else if(chosenGameType==2){
            setPlayers(PlayerTypeEnum.HUMAN, PlayerTypeEnum.COMPUTER);
        }else{
            setPlayers(PlayerTypeEnum.COMPUTER, PlayerTypeEnum.COMPUTER);
        }

        do {
            printer.gap();
            printer.printTwoBoards(board1, board2);

            if (isPlayer1CanHit) {
                boolean isHitPossible = true;
                while (isHitPossible) {
                    int[] coordinates;
                    if(player1.getPlayerTypeEnum() == PlayerTypeEnum.HUMAN){
                        coordinates = getInputCoordinatesFromHuman(player1.getName() + " podaj wspolrzedne : ");
                    }else{
                        coordinates = getCoordinatesFromComputer();
                    }
                    isCellHited = board2.isCellHitted(coordinates);
                    if(!isCellHited){
                        player1.hitEnemyBoard(coordinates[0], coordinates[1], board2);
                        isPlayer1CanHit = false;
                        isHitPossible = false;
                    }else{
                        isHitPossible = true;
                    }
                }
            } else {
                boolean hitFlag2 = true;
                while (hitFlag2) {
                    int[] coordinates;
                    if (player2.getPlayerTypeEnum() == PlayerTypeEnum.HUMAN) {
                        coordinates = getInputCoordinatesFromHuman(player2.getName() + " podaj wspolrzedne : ");
                    } else {
                        coordinates = getCoordinatesFromComputer();
                    }
                    isCellHited = board1.isCellHitted(coordinates);
                    if (!isCellHited) {
                        player2.hitEnemyBoard(coordinates[0], coordinates[1], board1);
                        isPlayer1CanHit = true;
                        hitFlag2 = false;
                    } else {
                        hitFlag2 = true;
                    }
                }
            }
            isGameAlive = isGameAlive();
//            printer.sleepPrint(500);
        } while (isGameAlive);
    }

//    private boolean checkIfIsHited(int[] coordinates){
//        if()
//
//        return true;
//    }

    private void setPlayers(PlayerTypeEnum playerType1, PlayerTypeEnum playerType2) {
        player1 = new Player("Jarosław", playerType1, board1);
        player2 = new Player("Lech", playerType2, board2);
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

    private int[] getInputCoordinatesFromHuman(String s) {
        printer.printMessage(s);
        return inputOutput.getCoordinates();
    }

    private int[] getCoordinatesFromComputer () {
        Random ran = new Random();
        int[] result = new int[2];
        result[0] = ran.nextInt(10);
        result[1] = ran.nextInt(10);
        return result;
    }
}
