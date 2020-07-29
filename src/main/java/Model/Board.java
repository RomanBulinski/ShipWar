package Model;

import Enums.SeaCellTypeEnum;

import java.util.Random;

public class Board {

    private final int TOP_LIMIT = 10;
    private final int ZERO = 0;
    private final int ONE = 1;
    private final String[] shipsId = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};
    CellInterface[][] fullBoard = new CellInterface[TOP_LIMIT][];
    Player owner;
    Random ran;


    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public void setBoard() {
        for (int j = ZERO; j < TOP_LIMIT; j++) {
            CellInterface[] row = new CellInterface[TOP_LIMIT];
            for (int i = ZERO; i < TOP_LIMIT; i++) {
                row[i] = new Sea();
            }
            fullBoard[j] = row;
        }
    }

    public CellInterface[][] getBoard() {
        return this.fullBoard;
    }

    public void setShipsOnBoard() {
        int amountOfShips = 4;
        int indexIdis = ZERO;
        for (int masts = ONE; masts < 5; masts++) {
            for (int j = amountOfShips; j > ZERO; j--) {
                String shipId = shipsId[indexIdis];
                createShipCell(fullBoard, masts, shipId);
            }
            indexIdis++;
            amountOfShips--;
        }
    }

    private void createShipCell(CellInterface[][] bordOnStart, int amuoutOfMasts, String shipId) {

        Integer[] head = setShipHead();
        CellInterface[][] board = bordOnStart;
        boolean isHorizonDirection = isHorizontalDirection();
        boolean flag = true;

        while (flag) {
            if (isHorizonDirection) {
                if (isPossibleToCreate(board, head, amuoutOfMasts, isHorizonDirection)) {
                    int row = head[ZERO];
                    int column = head[ONE];
                    for (int i = ZERO; i < amuoutOfMasts; i++) {
                        bordOnStart[row][column + i] = new Ship(amuoutOfMasts);
                        bordOnStart[row][column + i].setId(shipId);
                        setGapsForHorizontalShip(bordOnStart, row, column, i);
                    }
                    flag = false;
                } else {
                    head = setShipHead();
                }
            } else {
                if (isPossibleToCreate(board, head, amuoutOfMasts, isHorizonDirection)) {
                    int row = head[0];
                    int column = head[ONE];
                    for (int i = ZERO; i < amuoutOfMasts; i++) {
                        bordOnStart[row + i][column] = new Ship(amuoutOfMasts);
                        bordOnStart[row + i][column].setId(shipId);
                        setGapsForVerticalShip(bordOnStart, row, column, i);
                    }
                    flag = false;
                } else {
                    head = setShipHead();
                }
            }
        }
    }

    public void hitBoard(int row, int column, CellInterface[][] bordOnStart) {
        bordOnStart[row][column].hit();
    }

    private void setGapsForHorizontalShip(CellInterface[][] bordOnStart, int row, int column, int i) {
        //TODO implment
//        int leftCell = column - ONE;
//        int rightCell = column + ONE;
//        int upCell = row + i - ONE;
//        int bottomCell = row + i + ONE;

        if (row + ONE < TOP_LIMIT) {
            bordOnStart[row + ONE][column + i].setEmptySpace();
        }
        if (row - ONE >= ZERO && row - ONE < TOP_LIMIT) {
            bordOnStart[row - ONE][column + i].setEmptySpace();
        }
        if (column + i - ONE >= ZERO && column + i - ONE < TOP_LIMIT) {
            bordOnStart[row][column + i - ONE].setEmptySpace();
        }
        if (column + i + ONE >= ZERO && column + i + ONE < TOP_LIMIT) {
            bordOnStart[row][column + i + ONE].setEmptySpace();
        }
        if (column + i - ONE >= ZERO && column + i - ONE < TOP_LIMIT && row - ONE >= 0 && row - ONE < TOP_LIMIT) {
            bordOnStart[row - ONE][column + i - ONE].setEmptySpace();
        }
        if (column + i + ONE >= ZERO && column + i + ONE < TOP_LIMIT && row + ONE < TOP_LIMIT) {
            bordOnStart[row + ONE][column + i + ONE].setEmptySpace();
        }
        if (column + i + ONE >= ZERO && column + i + ONE < TOP_LIMIT && row - ONE >= 0 && row - ONE < TOP_LIMIT) {
            bordOnStart[row - ONE][column + i + ONE].setEmptySpace();
        }
        if (column + i - ONE >= ZERO && column + i - ONE < 10 && row + ONE < TOP_LIMIT) {
            bordOnStart[row + ONE][column + i - ONE].setEmptySpace();
        }
    }

    private void setGapsForVerticalShip(CellInterface[][] bordOnStart, int row, int column, int i) {
        int leftCell = column - ONE;
        int rightCell = column + ONE;
        int upCell = row + i - ONE;
        int bottomCell = row + i + ONE;

        if (ZERO <= leftCell && leftCell < TOP_LIMIT) {
            bordOnStart[row + i][leftCell].setEmptySpace();
        }
        if (rightCell < TOP_LIMIT) {
            bordOnStart[row + i][rightCell].setEmptySpace();
        }
        if (row + i + ONE >= 0 && row + i + ONE < TOP_LIMIT) {
            bordOnStart[bottomCell][column].setEmptySpace();
        }
        if (  ZERO <= upCell && upCell < TOP_LIMIT) {
            bordOnStart[upCell][column].setEmptySpace();
        }
        if (leftCell >= ZERO && leftCell < TOP_LIMIT && row - ONE >= ZERO && row - ONE < TOP_LIMIT) {
            bordOnStart[upCell][leftCell].setEmptySpace();
        }
        if (rightCell < TOP_LIMIT && bottomCell>= 0 && bottomCell < TOP_LIMIT) {
            bordOnStart[bottomCell][rightCell].setEmptySpace();
        }
        if (rightCell < TOP_LIMIT && upCell >= 0 && upCell < TOP_LIMIT) {
            bordOnStart[upCell][rightCell].setEmptySpace();
        }
        if (ZERO <= leftCell && leftCell < TOP_LIMIT && ZERO <= bottomCell && bottomCell < TOP_LIMIT) {
            bordOnStart[bottomCell][leftCell].setEmptySpace();
        }
    }

    private Integer[] setShipHead() {
        ran = new Random();
        int row = ran.nextInt(TOP_LIMIT);
        int column = ran.nextInt(TOP_LIMIT);
        return new Integer[]{row, column};
    }

    private boolean isHorizontalDirection() {
        ran = new Random();
        int result = ran.nextInt(2);
        return result == ZERO;
    }

    private boolean isPossibleToCreate(CellInterface[][] board, Integer[] head, int amountOfMasts, boolean isHorizontalDirection) {
        if (isHorizontalDirection) {
            for (int i = 0; i < amountOfMasts; i++) {
                if (head[ONE] + i < ZERO
                        || head[ONE] + i > 9
                        || board[head[0]][head[ONE] + i] instanceof Ship
                        || board[head[0]][head[ONE] + i].getType() == SeaCellTypeEnum.EMPTY_SPACE) {
                    return false;
                }
            }
        } else {
            for (int j = 0; j < amountOfMasts; j++) {
                if (head[0] + j < 0
                        || head[0] + j > 9
                        || board[head[0] + j][head[ONE]] instanceof Ship
                        || board[head[0] + j][head[ONE]].getType() == SeaCellTypeEnum.EMPTY_SPACE) {
                    return false;
                }
            }
        }
        return true;
    }
}
