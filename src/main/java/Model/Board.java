package Model;

import Enums.CellStateEnum;

import java.util.Random;

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

    public Cell[][] setShipsOnBoard(Cell[][] bordOnStart) {
        Cell[][] boardWithShips = bordOnStart;
        return createFourMastShip( boardWithShips );
    }

    private int[] setShipHead() {
        Random ran = new Random();
        int row = ran.nextInt(6);
        int column = ran.nextInt(6);
        return new int[]{row, column};
    }

    private Cell[][] createFourMastShip( Cell[][] bordOnStart ) {
        int[] head = setShipHead();
        for(int i =0; i<4; i++){
            bordOnStart[ head[0] ] [ head[1]+i ].setCellStateEnum(CellStateEnum.SHIP_CELL);
        }
        return bordOnStart;
    }


}
