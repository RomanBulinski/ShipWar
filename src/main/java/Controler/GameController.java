package Controler;

import Enums.PlayerTypeEnum;
import Model.Board;
import Model.Game;
import Model.Player;
import View.InputOutput;
import View.Printer;

import java.util.Random;

public class GameController {

    Printer printer = new Printer();
    InputOutput inputOutput = new InputOutput();

    Game game;
    Board board1;
    Board board2;
    Player player1;
    Player player2;

    boolean isNewGame;

    public GameController() {

        isNewGame = true;
        while (isNewGame) {
            prepareGame();
            choosePlayersType();
            runGame();
            startNewGameOrFinish();
        }

    }

    private void startNewGameOrFinish() {
        printer.printMessage("New Game ? y/n : ");
        if (inputOutput.getStringInput().equals("y")) {
            isNewGame = true;
        } else if (inputOutput.getStringInput().equals("n")) {
//        } else {
            isNewGame = false;
        }

//        switch (inputOutput.getStringInput()) {
//            case "y":
//                isNewGame = true;
//                break;
//            case "n":
//                isNewGame = false;
//                break;
//        }
    }

    private void choosePlayersType() {
        printer.printMenu(new String[]{"Human vs. Human", "Human vs. computer", "computer vs. computer"});
        printer.gap();
        printer.printMessage("Choose type game : ");
        int chosenGameType = inputOutput.getIntInput();
        if (chosenGameType == 1) {
            setPlayers(PlayerTypeEnum.HUMAN, PlayerTypeEnum.HUMAN);
        } else if (chosenGameType == 2) {
            setPlayers(PlayerTypeEnum.HUMAN, PlayerTypeEnum.COMPUTER);
        } else {
            setPlayers(PlayerTypeEnum.COMPUTER, PlayerTypeEnum.COMPUTER);
        }
    }

    private void prepareGame() {
        game = new Game();
        board1 = new Board();
        board2 = new Board();

        board1.setBoard();
        board2.setBoard();
        board1.setShipsOnBoard();
        board2.setShipsOnBoard();

        game.setPlayers();
        game.addPlayer(player1);
        game.addPlayer(player2);

        printer.gotoNextLine();
        printer.printMessage(" ---- START GAME ----");
        printer.gotoNextLine();
    }

    private void runGame() {
        boolean isCellHited;
        boolean isPlayer1CanHit = true;
        boolean isRoundAlive;
        do {
            printer.gap();
            printer.printTwoBoards(board1, board2);

            if (isPlayer1CanHit) {
                boolean isHitPossible = true;
                while (isHitPossible) {
                    int[] coordinates;
                    if (player1.getPlayerTypeEnum() == PlayerTypeEnum.HUMAN) {
                        coordinates = getInputCoordinatesFromHuman(player1.getName() + " podaj wspolrzedne : ");
                    } else {
                        coordinates = getCoordinatesFromComputer();
                    }
                    isCellHited = board2.isCellHitted(coordinates);
                    if (!isCellHited) {
                        player1.hitEnemyBoard(coordinates[0], coordinates[1], board2);
                        isPlayer1CanHit = false;
                        isHitPossible = false;
                    } else {
                        isHitPossible = true;
                    }
                }
            } else {
                boolean isHitPossible2 = true;
                while (isHitPossible2) {
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
                        isHitPossible2 = false;
                    } else {
                        isHitPossible2 = true;
                    }
                }
            }
            isRoundAlive = isRoundAlive();
        } while (isRoundAlive);
    }

    private void setPlayers(PlayerTypeEnum playerType1, PlayerTypeEnum playerType2) {
        player1 = new Player("Jarosław", playerType1, board1);
        player2 = new Player("Lech", playerType2, board2);
    }

    private boolean isRoundAlive() {
        boolean isRoundAliveTemp = true;
        isRoundAliveTemp = isBoardRoundAlive(isRoundAliveTemp, board1, board2);
        isRoundAliveTemp = isBoardRoundAlive(isRoundAliveTemp, board2, board1);
        return isRoundAliveTemp;
    }

    private boolean isBoardRoundAlive(boolean isRoundAliveTemp, Board boardA, Board boardB) {
        if (boardA.isAllShipsDead()) {
            printer.printMessage("The winner is : " + boardB.getOwner().getName());
            printer.gap();
            isRoundAliveTemp = false;
        }
        return isRoundAliveTemp;
    }

    private int[] getInputCoordinatesFromHuman(String s) {
        printer.printMessage(s);
        return inputOutput.getCoordinates();
    }

    private int[] getCoordinatesFromComputer() {
        Random ran = new Random();
        int[] result = new int[2];
        result[0] = ran.nextInt(10);
        result[1] = ran.nextInt(10);
        return result;
    }
}
