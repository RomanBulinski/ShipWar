package Controler;

import Enums.PlayerTypeEnum;
import Model.Board;
import Model.Player;
import View.InputOutput;
import View.Printer;

import java.util.ArrayList;
import java.util.List;

public class PlayersController {

    //TODO get it from GameController
    InputOutput inputOutput = new InputOutput();
    //TODO get it from GameController
    Printer printer = new Printer();

    Player player1;
    Player player2;
    List<Player> players = new ArrayList<Player>();

    public List<Player>  getPlayers() {
        return players;
    }

    public void setPlayers() {
        this.players = players;
    }

    public void addPlayer(Player player){
        players.add(player);
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

    private void choosePlayersType() {
        printer.printMenu(new String[]{"Human vs. Human", "Human vs. computer", "computer vs. computer"});
        printer.gap();
        printer.printMessage("Choose type game : ");
        int chosenGameType = inputOutput.getIntInput();
        if (chosenGameType == 1) {
            createPlayers(PlayerTypeEnum.HUMAN, PlayerTypeEnum.HUMAN);
        } else if (chosenGameType == 2) {
            createPlayers(PlayerTypeEnum.HUMAN, PlayerTypeEnum.COMPUTER);
        } else {
            createPlayers(PlayerTypeEnum.COMPUTER, PlayerTypeEnum.COMPUTER);
        }
    }


}
