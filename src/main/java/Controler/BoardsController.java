package Controler;

import Model.Board;

public class BoardsController {

    Board board1;
    Board board2;

    public BoardsController(){
        board1 = new Board();
        board2 = new Board();
        board1.setBoard();
        board2.setBoard();
        board1.setShipsOnBoard();
        board2.setShipsOnBoard();
    }

    public Board getBoard1() {
        return board1;
    }

    public Board getBoard2() {
        return board2;
    }






}
