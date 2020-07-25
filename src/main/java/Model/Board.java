package Model;

import Enums.CellStateEnum;
import Enums.ShipTypeEnum;

import java.util.Random;

public class Board {

    public CellInterface[][] createBoard() {
        CellInterface[][] fullBoard = new CellInterface[10][];
        for (int j = 0; j < 10; j++) {
            CellInterface[] row = new CellInterface[10];
            for (int i = 0; i < 10; i++) {
                row[i] = new Sea();
            }
            fullBoard[j] = row;
        }
        return fullBoard;
    }

    public CellInterface[][] setShipsOnBoard(CellInterface[][] bordOnStart) {
        CellInterface[][] boardWithShips = bordOnStart;
        createShip(boardWithShips, 4);
        createShip(boardWithShips, 3);
        createShip(boardWithShips, 3);
        createShip(boardWithShips, 2);
        createShip(boardWithShips, 2);
        createShip(boardWithShips, 2);
        createShip(boardWithShips, 1);
        createShip(boardWithShips, 1);
        createShip(boardWithShips, 1);
        createShip(boardWithShips, 1);
        return boardWithShips;
    }

    private CellInterface[][] createShip(CellInterface[][] bordOnStart, int amuoutOfMasts) {

        CellInterface[][] board = bordOnStart;
        int[] head = setShipHead();
        boolean isHorizonDirection = isHorizontalDirection();
        if (isHorizonDirection) {
            if (isPossibleToCreate(board, head, amuoutOfMasts, isHorizonDirection)) {
                for (int i = 0; i < amuoutOfMasts; i++) {
                    if (amuoutOfMasts == 4) {
                        bordOnStart[head[0]][head[1] + i] = new Ship(4);
                    }
                    if (amuoutOfMasts == 3) {
                        bordOnStart[head[0]][head[1] + i] = new Ship(3);
                    }
                    if (amuoutOfMasts == 2) {
                        bordOnStart[head[0]][head[1] + i] = new Ship(2);
                    }
                    if (amuoutOfMasts == 1) {
                        bordOnStart[head[0]][head[1] + i] = new Ship(1);
                    }
                }
            }
        } else {
            if (isPossibleToCreate(board, head, amuoutOfMasts, isHorizonDirection)) {
                for (int i = 0; i < amuoutOfMasts; i++) {
                    if (amuoutOfMasts == 4) {
                        bordOnStart[head[0] + i][head[1]] = new Ship(4);
                    }
                    if (amuoutOfMasts == 3) {
                        bordOnStart[head[0] + i][head[1]] = new Ship(3);
                    }
                    if (amuoutOfMasts == 2) {
                        bordOnStart[head[0] + i][head[1]] = new Ship(2);
                    }
                    if (amuoutOfMasts == 1) {
                        bordOnStart[head[0] + i][head[1]] = new Ship(1);
                    }
                }
            }
        }

        return bordOnStart;
    }

    private int[] setShipHead() {
        Random ran = new Random();
        int row = ran.nextInt(6);
        int column = ran.nextInt(6);
        return new int[]{row, column};
    }

    private boolean isHorizontalDirection() {
        Random ran = new Random();
        int result = ran.nextInt(2);
        return result == 0;
    }

    private boolean isPossibleToCreate(CellInterface[][] board, int[] head, int amountOfMasts, boolean isHorizontalDirection) {
        boolean result = true;
        if (isHorizontalDirection) {
            for (int i = 0; i < amountOfMasts; i++) {
                if (board[head[0]][head[1] + i] instanceof Ship) {
                    result = false;
                }
            }
        } else {
            for (int j = 0; j < amountOfMasts; j++) {
                if (board[head[0] + j][head[1]] instanceof Ship) {
                    result = false;
                }
            }
        }
        return result;
    }

}
