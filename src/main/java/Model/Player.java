package Model;

import Enums.PlayerTypeEnum;

public class Player {

    String name;
    PlayerTypeEnum playerTypeEnum;
    Board board;

    public Player(String name, PlayerTypeEnum playerTypeEnum, Board board) {
        this.name = name;
        this.playerTypeEnum = playerTypeEnum;
        this.board = board;

        this.board.setOwner(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public PlayerTypeEnum getPlayerTypeEnum() {
        return playerTypeEnum;
    }

    public void setPlayerTypeEnum(PlayerTypeEnum playerTypeEnum) {
        this.playerTypeEnum = playerTypeEnum;
    }

}
