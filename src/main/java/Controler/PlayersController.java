package Controler;

import Enums.PlayerTypeEnum;
import Model.Board;
import Model.Player;
import View.InputOutput;
import View.Printer;

public class PlayersController {

    InputOutput inputOutput;
    Printer printer;

    Board board1;
    Board board2;
    Player player1;
    Player player2;

    public PlayersController(Board board1, Board board2, Printer printer, InputOutput inputOutput) {
        this.board1 = board1;
        this.board2 = board2;
        this.printer = printer;
        this.inputOutput = inputOutput;

        createAndChoosePlayersType();
    }

    private void createAndChoosePlayersType() {
        printer.printMenu(new String[]{"Human vs. Human", "Human vs. computer", "computer vs. computer"});
        printer.gap();
        printer.printMessage("Choose type game : ");
        int chosenGameType = inputOutput.getIntInput();
        if (chosenGameType == 1) {
            createPlayers(PlayerTypeEnum.HUMAN, PlayerTypeEnum.HUMAN, board1, board2);
        } else if (chosenGameType == 2) {
            createPlayers(PlayerTypeEnum.HUMAN, PlayerTypeEnum.COMPUTER, board1, board2);
        } else {
            createPlayers(PlayerTypeEnum.COMPUTER, PlayerTypeEnum.COMPUTER, board1, board2);
        }
    }

    private void createPlayers(PlayerTypeEnum playerType1, PlayerTypeEnum playerType2, Board board1, Board board2) {
        player1 = new Player("Jarosław", playerType1, board1);
        player2 = new Player("Lech", playerType2, board2);
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }
}
