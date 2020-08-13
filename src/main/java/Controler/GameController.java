package Controler;

import Enums.PlayerTypeEnum;
import Model.Board;
import Model.Player;
import View.InputOutput;
import View.Printer;

import static Enums.PlayerTypeEnum.HUMAN;

public class GameController {

    Printer printer;
    InputOutput inputOutput;

    BoardsController boardsController;
    Board board1;
    Board board2;
    PlayersController playersController;
    Player player1;
    Player player2;

    boolean isNewGame;

    public GameController() {
        init();
    }

    public void run(){
        isNewGame = true;
        while (isNewGame) {
            prepareGame();
            runGame();
            startNewGameOrFinish();
        }
    }

    private void init(){
        printer = new Printer();
        inputOutput = new InputOutput();
    }

    private void prepareGame() {

        printer.gotoNextLine();
        printer.printMessage(" ---- START GAME ----");
        printer.gotoNextLine();

        boardsController = new BoardsController();
        board1 = boardsController.getBoard1();
        board2 = boardsController.getBoard2();

        playersController = new PlayersController( board1, board2 , printer, inputOutput);
        player1 = playersController.getPlayer1();
        player2 = playersController.getPlayer2();

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
                    if (player1.getPlayerTypeEnum() == HUMAN) {
                        coordinates = inputOutput.getInputCoordinatesFromHuman(player1.getName() + " podaj wspolrzedne : ");
                    } else {
                        coordinates = inputOutput.getCoordinatesFromComputer();
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
                        coordinates = inputOutput.getInputCoordinatesFromHuman(player2.getName() + " podaj wspolrzedne : ");
                    } else {
                        coordinates = inputOutput.getCoordinatesFromComputer();
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

    private void startNewGameOrFinish() {
        printer.printMessage("New Game ? y/n : ");
        if (inputOutput.getStringInput().equals("y")) {
            isNewGame = true;
        } else if (inputOutput.getStringInput().equals("n")) {
            isNewGame = false;
        }
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

}
