package Model;

public class Board {

    public Cell[][] createBoard() {
        Cell[][] fullBoard = new Cell[10][];
        for (int j = 0; j < 10; j++) {
            Cell[] row = new Cell[10];
            for (int i = 0; i < 10; i++) {
                row[i] = new Cell();
            }
            fullBoard[j] = row;
        }
        return fullBoard;
    }




}
