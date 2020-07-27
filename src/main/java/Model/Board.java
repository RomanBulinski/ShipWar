package Model;

import Enums.SeaCellTypeEnum;

import java.util.Random;

public class Board {

    private int TOP_LIMIT = 10;
    private String[] shipsId = { "a","b","c","d","e","f","g","h","i","j"};

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

    public void setShipsOnBoard(CellInterface[][] bordOnStart) {
        int amountOfShips = 4;
        int indexIdis = 0;
        for(int masts = 1; masts<5; masts++){
            for(int j = amountOfShips; j>0; j--){
                String shipId = shipsId[indexIdis];
                createShipCell(bordOnStart, masts, shipId);
            }
            indexIdis++;
            amountOfShips = amountOfShips-1;
        }
    }

    public void hitBoard(int row, int column, CellInterface[][] bordOnStart){
        bordOnStart[row][column].hit();
    }

    private void createShipCell(CellInterface[][] bordOnStart, int amuoutOfMasts, String shipId ) {

        Integer[] head = setShipHead();
        CellInterface[][] board = bordOnStart;
        boolean isHorizonDirection = isHorizontalDirection();
        boolean flag = true;

        while (flag) {
            if (isHorizonDirection) {
                if (isPossibleToCreate(board, head, amuoutOfMasts, isHorizonDirection)) {
                    int row = head[0];
                    int column = head[1];
                    for (int i = 0; i < amuoutOfMasts; i++) {
                        bordOnStart[row][column+ i] = new Ship(amuoutOfMasts);
                        bordOnStart[row][column+ i].setId(shipId);

                        if ( row+ 1 < 10) {
                            bordOnStart[row+ 1][column+ i].setEmptySpace();
                        }
                        if (row- 1 >= 0 && row- 1 < 10) {
                            bordOnStart[row- 1][column+ i].setEmptySpace();
                        }
                        if (column+ i - 1 >= 0 && column+ i - 1 < 10) {
                            bordOnStart[head[0]][column+ i - 1].setEmptySpace();
                        }
                        if (column+ i + 1 >= 0 && column+ i + 1 < 10) {
                            bordOnStart[head[0]][column+ i + 1].setEmptySpace();
                        }
                        if (column+ i - 1 >= 0 && column+ i - 1 < 10 && row- 1 >= 0 && row- 1 < 10) {
                            bordOnStart[row- 1][column+ i - 1].setEmptySpace();
                        }
                        if (column+ i + 1 >= 0 && column+ i + 1 < 10 && row+ 1 < 10) {
                            bordOnStart[row+ 1][column+ i + 1].setEmptySpace();
                        }
                        if (column+ i + 1 >= 0 && column+ i + 1 < 10 && row- 1 >= 0 && row- 1 < 10) {
                            bordOnStart[row- 1][column+ i + 1].setEmptySpace();
                        }
                        if (column+ i - 1 >= 0 && column+ i - 1 < 10 && row+ 1 < 10) {
                            bordOnStart[row+ 1][column+ i - 1].setEmptySpace();
                        }
                    }
                    flag = false;
                } else {
                    head = setShipHead();
                }
            } else {
                if (isPossibleToCreate(board, head, amuoutOfMasts, isHorizonDirection)) {
                    int row = head[0];
                    int column = head[1];
                    for (int i = 0; i < amuoutOfMasts; i++) {
                        bordOnStart[row + i][column] = new Ship(amuoutOfMasts);
                        bordOnStart[row + i][column].setId(shipId);

                        if (column - 1 >= 0 && column - 1 < 10) {
                            bordOnStart[row + i][column - 1].setEmptySpace();
                        }
                        if (column + 1 < 10) {
                            bordOnStart[row + i][column + 1].setEmptySpace();
                        }
                        if (row + i + 1 >= 0 && row + i + 1 < 10) {
                            bordOnStart[row + i + 1][column].setEmptySpace();
                        }
                        if (row + i - 1 >= 0 && row + i - 1 < 10) {
                            bordOnStart[row + i - 1][column].setEmptySpace();
                        }
                        if (column - 1 >= 0 && column - 1 < 10 && row - 1 >= 0 && row - 1 < 10) {
                            bordOnStart[row + i - 1][column - 1].setEmptySpace();
                        }
                        if (column + 1 < 10 && row + i + 1 >= 0 && row +i+ 1 < 10) {
                            bordOnStart[row + i + 1][column + 1].setEmptySpace();
                        }
                        if (column + 1 < 10 && row + i - 1 >= 0 && row + i - 1 < 10) {
                            bordOnStart[row + i - 1][column + 1].setEmptySpace();
                        }
                        if (column - 1 >= 0 && column - 1 < 10 && row + i + 1 >= 0 && row + i + 1 < 10) {
                            bordOnStart[row + i + 1][column - 1].setEmptySpace();
                        }
                    }
                    flag = false;
                } else {
                    head = setShipHead();
                }
            }
        }
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
        if (isHorizontalDirection) {
            for (int i = 0; i < amountOfMasts; i++) {
                if ( head[1] + i <0 ||
                        head[1] + i > 9
                        || board[head[0]][head[1] + i] instanceof Ship
                        || board[head[0]][head[1] + i].getType() == SeaCellTypeEnum.EMPTY_SPACE) {
                    return false;
                }
            }
        } else {
            for (int j = 0; j < amountOfMasts; j++) {
                if (  head[0]+ j < 0
                        || head[0]+ j > 9
                        || board[head[0] + j][head[1]] instanceof Ship
                        || board[head[0] + j][head[1]].getType() == SeaCellTypeEnum.EMPTY_SPACE) {
                    return false;
                }
            }
        }
        return true;
    }
}
