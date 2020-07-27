package Model;

import Enums.SeaCellTypeEnum;

import java.util.Random;

public class Board {

    private final int TOP_LIMIT = 10;
    private final int ZERO = 0;
    private final String[] shipsId = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};

    public CellInterface[][] createBoard() {
        CellInterface[][] fullBoard = new CellInterface[TOP_LIMIT][];
        for (int j = ZERO; j < TOP_LIMIT; j++) {
            CellInterface[] row = new CellInterface[TOP_LIMIT];
            for (int i = ZERO; i < TOP_LIMIT; i++) {
                row[i] = new Sea();
            }
            fullBoard[j] = row;
        }
        return fullBoard;
    }

    public void setShipsOnBoard(CellInterface[][] bordOnStart) {
        int amountOfShips = 4;
        int indexIdis = ZERO;
        for (int masts = 1; masts < 5; masts++) {
            for (int j = amountOfShips; j > ZERO; j--) {
                String shipId = shipsId[indexIdis];
                createShipCell(bordOnStart, masts, shipId);
            }
            indexIdis++;
            amountOfShips = amountOfShips - 1;
        }
    }

    public void hitBoard(int row, int column, CellInterface[][] bordOnStart) {
        bordOnStart[row][column].hit();
    }

    private void createShipCell(CellInterface[][] bordOnStart, int amuoutOfMasts, String shipId) {

        Integer[] head = setShipHead();
        CellInterface[][] board = bordOnStart;
        boolean isHorizonDirection = isHorizontalDirection();
        boolean flag = true;

        while (flag) {
            if (isHorizonDirection) {
                if (isPossibleToCreate(board, head, amuoutOfMasts, isHorizonDirection)) {
                    int row = head[0];
                    int column = head[1];
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
                    int column = head[1];
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

    private void setGapsForHorizontalShip(CellInterface[][] bordOnStart, int row, int column, int i) {
        if (row + 1 < TOP_LIMIT) {
            bordOnStart[row + 1][column + i].setEmptySpace();
        }
        if (row - 1 >= ZERO && row - 1 < TOP_LIMIT) {
            bordOnStart[row - 1][column + i].setEmptySpace();
        }
        if (column + i - 1 >= ZERO && column + i - 1 < TOP_LIMIT) {
            bordOnStart[row][column + i - 1].setEmptySpace();
        }
        if (column + i + 1 >= ZERO && column + i + 1 < TOP_LIMIT) {
            bordOnStart[row][column + i + 1].setEmptySpace();
        }
        if (column + i - 1 >= ZERO && column + i - 1 < TOP_LIMIT && row - 1 >= 0 && row - 1 < TOP_LIMIT) {
            bordOnStart[row - 1][column + i - 1].setEmptySpace();
        }
        if (column + i + 1 >= ZERO && column + i + 1 < TOP_LIMIT && row + 1 < TOP_LIMIT) {
            bordOnStart[row + 1][column + i + 1].setEmptySpace();
        }
        if (column + i + 1 >= ZERO && column + i + 1 < TOP_LIMIT && row - 1 >= 0 && row - 1 < TOP_LIMIT) {
            bordOnStart[row - 1][column + i + 1].setEmptySpace();
        }
        if (column + i - 1 >= ZERO && column + i - 1 < 10 && row + 1 < TOP_LIMIT) {
            bordOnStart[row + 1][column + i - 1].setEmptySpace();
        }
    }

    private void setGapsForVerticalShip(CellInterface[][] bordOnStart, int row, int column, int i) {
        if (column - 1 >= ZERO && column - 1 < TOP_LIMIT) {
            bordOnStart[row + i][column - 1].setEmptySpace();
        }
        if (column + 1 < TOP_LIMIT) {
            bordOnStart[row + i][column + 1].setEmptySpace();
        }
        if (row + i + 1 >= 0 && row + i + 1 < TOP_LIMIT) {
            bordOnStart[row + i + 1][column].setEmptySpace();
        }
        if (row + i - 1 >= ZERO && row + i - 1 < TOP_LIMIT) {
            bordOnStart[row + i - 1][column].setEmptySpace();
        }
        if (column - 1 >= ZERO && column - 1 < TOP_LIMIT && row - 1 >= ZERO && row - 1 < TOP_LIMIT) {
            bordOnStart[row + i - 1][column - 1].setEmptySpace();
        }
        if (column + 1 < TOP_LIMIT && row + i + 1 >= 0 && row + i + 1 < TOP_LIMIT) {
            bordOnStart[row + i + 1][column + 1].setEmptySpace();
        }
        if (column + 1 < TOP_LIMIT && row + i - 1 >= 0 && row + i - 1 < TOP_LIMIT) {
            bordOnStart[row + i - 1][column + 1].setEmptySpace();
        }
        if (column - 1 >= ZERO && column - 1 < TOP_LIMIT && row + i + 1 >= ZERO && row + i + 1 < TOP_LIMIT) {
            bordOnStart[row + i + 1][column - 1].setEmptySpace();
        }
    }

    private Integer[] setShipHead() {
        Random ran = new Random();
        int row = ran.nextInt(TOP_LIMIT);
        int column = ran.nextInt(TOP_LIMIT);
        return new Integer[]{row, column};
    }

    private boolean isHorizontalDirection() {
        Random ran = new Random();
        int result = ran.nextInt(2);
        return result == ZERO;
    }

    private boolean isPossibleToCreate(CellInterface[][] board, Integer[] head, int amountOfMasts, boolean isHorizontalDirection) {
        if (isHorizontalDirection) {
            for (int i = 0; i < amountOfMasts; i++) {
                if (head[1] + i < ZERO
                        || head[1] + i > 9
                        || board[head[0]][head[1] + i] instanceof Ship
                        || board[head[0]][head[1] + i].getType() == SeaCellTypeEnum.EMPTY_SPACE) {
                    return false;
                }
            }
        } else {
            for (int j = 0; j < amountOfMasts; j++) {
                if (head[0] + j < 0
                        || head[0] + j > 9
                        || board[head[0] + j][head[1]] instanceof Ship
                        || board[head[0] + j][head[1]].getType() == SeaCellTypeEnum.EMPTY_SPACE) {
                    return false;
                }
            }
        }
        return true;
    }
}
