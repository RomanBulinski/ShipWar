package Model;

import Enums.SeaCellTypeEnum;
import Enums.ShipCellTypeEnum;
import Enums.ShipMastsTypeEnum;

import java.util.*;

public class Board {

    private final int TOP_LIMIT = 10;
    private final int ZERO = 0;
    private final int ONE = 1;
    private final String[] shipsId = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};

    CellInterface[][] fullBoard = new CellInterface[TOP_LIMIT][];
    Map<String, Integer> shipsListInMap = new HashMap<>();
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
                row[i] = new SeaCell();
            }
            fullBoard[j] = row;
        }
    }

    public CellInterface[][] getBoard() {
        return this.fullBoard;
    }

    public void setShipsOnBoard() {
        int amountOfships = 4;
        int indexIdis = ZERO;
        for (int amuoutOfMasts = ONE; amuoutOfMasts < 5; amuoutOfMasts++) {
            for (int j = amountOfships; j > ZERO; j--) {
                String cellId = shipsId[indexIdis];
                createShip(fullBoard, amuoutOfMasts, cellId);
                indexIdis++;
            }
            amountOfships--;
        }
    }

    private void createShip(CellInterface[][] bordOnStart, int amuoutOfMasts, String cellId) {

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
                        bordOnStart[row][column + i] = new ShipCell(amuoutOfMasts);
                        bordOnStart[row][column + i].setId(cellId);
                        setGapsForHorizontalShip(bordOnStart, row, column, i);
                        infillListOfShips(amuoutOfMasts, cellId);
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
                        bordOnStart[row + i][column] = new ShipCell(amuoutOfMasts);
                        bordOnStart[row + i][column].setId(cellId);
                        setGapsForVerticalShip(bordOnStart, row, column, i);
                        infillListOfShips(amuoutOfMasts, cellId);
                    }
                    flag = false;
                } else {
                    head = setShipHead();
                }
            }
        }
    }

    public void hitBoard(int row, int column, CellInterface[][] bord) {
        // hit board
        bord[row][column].hitcell();
        // save info in list
        if (bord[row][column] instanceof ShipCell) {
            String shipId = ((ShipCell) bord[row][column]).getId();
            ShipMastsTypeEnum shipMastsType = ((ShipCell) bord[row][column]).getShipTypeEnum();
//            ShipCellTypeEnum aliveType= ((ShipCell) bordOnStart[row][column]).getShipCellTypeEnum();
            String shipMastsTypeString = mapEnumToString(shipMastsType);
            String cellId = shipId + shipMastsTypeString;
            shipsListInMap.put(cellId, shipsListInMap.get(shipId + shipMastsTypeString) - 1);
            //check if all cells are dead and set ShipCellTypeEnum.DEAD_SHIP for this cells
            if (shipsListInMap.get(cellId) == 0) {
                Arrays.stream(bord).forEach(line -> Arrays.stream(line)
                        .filter(cell -> cell instanceof ShipCell)
                        .filter(shipCell -> ((ShipCell) shipCell).getId().equals(shipId))
                        .forEach(shipCell -> ((ShipCell) shipCell)
                                .setShipCellTypeEnum(ShipCellTypeEnum.DEAD_SHIP)));
            }
        }
    }

    public Map<String, Integer> getShipsInList() {
        return shipsListInMap;
    }

    public boolean isAllShipsDead() {
        Object[] values = shipsListInMap.values().stream().filter(value -> value > 0).toArray();
        if(values.length == 0){
            return  true;
        }
        return  false;
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
        if (ZERO <= upCell && upCell < TOP_LIMIT) {
            bordOnStart[upCell][column].setEmptySpace();
        }
        if (leftCell >= ZERO && leftCell < TOP_LIMIT && row - ONE >= ZERO && row - ONE < TOP_LIMIT) {
            bordOnStart[upCell][leftCell].setEmptySpace();
        }
        if (rightCell < TOP_LIMIT && bottomCell >= 0 && bottomCell < TOP_LIMIT) {
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
                        || board[head[0]][head[ONE] + i] instanceof ShipCell
                        || board[head[0]][head[ONE] + i].getType() == SeaCellTypeEnum.EMPTY_SPACE) {
                    return false;
                }
            }
        } else {
            for (int j = 0; j < amountOfMasts; j++) {
                if (head[0] + j < 0
                        || head[0] + j > 9
                        || board[head[0] + j][head[ONE]] instanceof ShipCell
                        || board[head[0] + j][head[ONE]].getType() == SeaCellTypeEnum.EMPTY_SPACE) {
                    return false;
                }
            }
        }
        return true;
    }

    private void infillListOfShips(int amuoutOfMasts, String cellId) {
        if (shipsListInMap.get(cellId + amuoutOfMasts) == null) {
            shipsListInMap.put(cellId + amuoutOfMasts, 1);
        } else {
            Integer amountOfCell = shipsListInMap.get(cellId + amuoutOfMasts);
            shipsListInMap.put(cellId + amuoutOfMasts, amountOfCell + 1);
        }
    }

    private String mapEnumToString(ShipMastsTypeEnum shipMastsType) {
        String shipMastsTypeString = "";
        if (shipMastsType == ShipMastsTypeEnum.FOUR_MASTS) {
            shipMastsTypeString = "4";
        } else if (shipMastsType == ShipMastsTypeEnum.TREE_MASTS) {
            shipMastsTypeString = "3";
        } else if (shipMastsType == ShipMastsTypeEnum.TWO_MASTS) {
            shipMastsTypeString = "2";
        } else if (shipMastsType == ShipMastsTypeEnum.ONE_MAST) {
            shipMastsTypeString = "1";
        }
        return shipMastsTypeString;
    }


}
