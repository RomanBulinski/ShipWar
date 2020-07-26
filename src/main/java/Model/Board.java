package Model;

import Enums.SeaCellTypeEnum;

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

        Integer[] head = setShipHead();
        CellInterface[][] board = bordOnStart;
        boolean isHorizonDirection = isHorizontalDirection();
        boolean flag = true;

        while (flag) {
            if (isHorizonDirection) {
                if (isPossibleToCreate(board, head, amuoutOfMasts, isHorizonDirection)) {
                    for (int i = 0; i < amuoutOfMasts; i++) {
                        //TODO here is exception
                        bordOnStart[head[0]][head[1] + i] = new Ship(amuoutOfMasts);

                        if (head[1] + i - 1 >= 0 && head[1] + i - 1 < 10) {
                            bordOnStart[head[0]][head[1] + i - 1].setEmptySpace();
                        }
                        if (head[1] + i + 1 >= 0 && head[1] + i + 1 < 10) {
                            bordOnStart[head[0]][head[1] + i + 1].setEmptySpace();
                        }
                        if (head[0] + 1 >= 0 && head[0] + 1 < 10) {
                            bordOnStart[head[0] + 1][head[1] + i].setEmptySpace();
                        }
                        if (head[0] - 1 >= 0 && head[0] - 1 < 10) {
                            bordOnStart[head[0] - 1][head[1] + i].setEmptySpace();
                        }
                        if (head[1] + i - 1 >= 0 && head[1] + i - 1 < 10 && head[0] - 1 >= 0 && head[0] - 1 < 10) {
                            bordOnStart[head[0] - 1][head[1] + i - 1].setEmptySpace();
                        }
                        if (head[1] + i + 1 >= 0 && head[1] + i + 1 < 10 && head[0] + 1 >= 0 && head[0] + 1 < 10) {
                            bordOnStart[head[0] + 1][head[1] + i + 1].setEmptySpace();
                        }
                        if (head[1] + i + 1 >= 0 && head[1] + i + 1 < 10 && head[0] - 1 >= 0 && head[0] - 1 < 10) {
                            bordOnStart[head[0] - 1][head[1] + i + 1].setEmptySpace();
                        }
                        if (head[1] + i - 1 >= 0 && head[1] + i - 1 < 10 && head[0] + 1 >= 0 && head[0] + 1 < 10) {
                            bordOnStart[head[0] + 1][head[1] + i - 1].setEmptySpace();
                        }
                    }
                    flag = false;
                } else {
                    head = setShipHead();
                }
            } else {
                if (isPossibleToCreate(board, head, amuoutOfMasts, isHorizonDirection)) {
                    for (int i = 0; i < amuoutOfMasts; i++) {
                        //TODO here is exception
                        bordOnStart[head[0] + i][head[1]] = new Ship(amuoutOfMasts);

                        if (head[1] - 1 >= 0 && head[1] - 1 < 10) {
                            bordOnStart[head[0] + i][head[1] - 1].setEmptySpace();
                        }
                        if (head[1] + 1 >= 0 && head[1] + 1 < 10) {
                            bordOnStart[head[0] + i][head[1] + 1].setEmptySpace();
                        }
                        if (head[0] + i + 1 >= 0 && head[0] + i + 1 < 10) {
                            bordOnStart[head[0] + i + 1][head[1]].setEmptySpace();
                        }
                        if (head[0] + i - 1 >= 0 && head[0] + i - 1 < 10) {
                            bordOnStart[head[0] + i - 1][head[1]].setEmptySpace();
                        }
                        if (head[1] - 1 >= 0 && head[1] - 1 < 10 && head[0] - 1 >= 0 && head[0] - 1 < 10) {
                            bordOnStart[head[0] + i - 1][head[1] - 1].setEmptySpace();
                        }
                        if (head[1] + 1 >= 0 && head[1] + 1 < 10 && head[0] + i + 1 >= 0 && head[0] +i+ 1 < 10) {
                            bordOnStart[head[0] + i + 1][head[1] + 1].setEmptySpace();
                        }
                        if (head[1] + 1 >= 0 && head[1] + 1 < 10 && head[0] + i - 1 >= 0 && head[0] + i - 1 < 10) {
                            bordOnStart[head[0] + i - 1][head[1] + 1].setEmptySpace();
                        }
                        if (head[1] - 1 >= 0 && head[1] - 1 < 10 && head[0] + i + 1 >= 0 && head[0] + i + 1 < 10) {
                            bordOnStart[head[0] + i + 1][head[1] - 1].setEmptySpace();
                        }
                    }
                    flag = false;
                } else {
                    head = setShipHead();
                }
            }
        }
        return bordOnStart;
    }

    private Integer[] setShipHead() {
        Random ran = new Random();
        int row = ran.nextInt(10);
        int column = ran.nextInt(10);
        return new Integer[]{row, column};
    }

    private boolean isHorizontalDirection() {
        Random ran = new Random();
        int result = ran.nextInt(2);
        return result == 0;
    }

    private boolean isPossibleToCreate(CellInterface[][] board, Integer[] head, int amountOfMasts, boolean isHorizontalDirection) {
        boolean result = true;
        if (isHorizontalDirection) {
            for (int i = 0; i < amountOfMasts; i++) {
                if ( head[1] + i <0 ||
                        head[1] + i > 9
                        || board[head[0]][head[1] + i] instanceof Ship
                        || board[head[0]][head[1] + i].getType() == SeaCellTypeEnum.EMPTY_SPACE) {
                    result = false;
                }
            }
        } else {
            for (int j = 0; j < amountOfMasts; j++) {
                if (  head[0]+ j < 0
                        || head[0]+ j > 9
                        || board[head[0] + j][head[1]] instanceof Ship
                        || board[head[0] + j][head[1]].getType() == SeaCellTypeEnum.EMPTY_SPACE) {
                    result = false;
                }
            }
        }
        return result;
    }
}
