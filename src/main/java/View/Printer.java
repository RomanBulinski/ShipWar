package View;

import Enums.SeaCellTypeEnum;
import Enums.ShipCellTypeEnum;
import Enums.ShipMastsTypeEnum;
import Model.Board;
import Model.CellInterface;
import Model.SeaCell;
import Model.ShipCell;

import java.util.Map;
import java.util.Set;

import static java.lang.Thread.sleep;

public class Printer {

    private final char[] indexes = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
    private int index = 0;

    public void printBoard(Board board) {
        this.printHead();
        this.goToNewRow();
        for (CellInterface[] line : board.getBoard()) {
            this.printLineIndex(index);
            this.printRow(line);
            this.goToNewRow();
            index++;
        }
        index = 0;
        this.printMessage(board.getOwner().getName());
        this.gap();
    }

    public void printTwoBoards(Board board1, Board board2) {
        this.printHeads();

        CellInterface[][] rows1 = board1.getBoard();
        CellInterface[][] rows2 = board2.getBoard();

        for (int i = 0; i < rows1.length; i++) {
            this.printLineIndex(index);
            this.printRow(rows1[i]);
            this.printEmptySpace();
            this.printLineIndex(index);
            this.printRow(rows2[i]);
            this.goToNewRow();
            index++;
        }
        index = 0;
        this.printMessage(board1.getOwner().getName());
        this.printMessage("                              ");
        this.printMessage(board2.getOwner().getName());
        this.gap();

        printShipList(board1.getShipsInList(), board1.getOwner().getName());
        goToNewRow();
        printShipList(board2.getShipsInList(), board2.getOwner().getName());
        goToNewRow();

    }

    private void goToNewRow() {
        printMessage("\n");
    }

    private void printLineIndex(int index) {
        printMessage(" " + indexes[index] + "  ");
    }

    private void printRow(CellInterface[] line) {
        for (CellInterface cell : line) {
            if (cell instanceof ShipCell) {
                if (((ShipCell) cell).getShipTypeEnum() == ShipMastsTypeEnum.ONE_MAST) {
                    printShipCell((ShipCell) cell, "1  ");
                } else if (((ShipCell) cell).getShipTypeEnum() == ShipMastsTypeEnum.TWO_MASTS) {
                    printShipCell((ShipCell) cell, "2  ");
                } else if (((ShipCell) cell).getShipTypeEnum() == ShipMastsTypeEnum.TREE_MASTS) {
                    printShipCell((ShipCell) cell, "3  ");
                } else if (((ShipCell) cell).getShipTypeEnum() == ShipMastsTypeEnum.FOUR_MASTS) {
                    printShipCell((ShipCell) cell, "4  ");
                }
            } else if (((SeaCell) cell).getSeaCellTypeEnum() == SeaCellTypeEnum.EMPTY_SPACE) {
                printMessage("   ");
            } else if (((SeaCell) cell).getSeaCellTypeEnum() == SeaCellTypeEnum.DEAD_CELL) {
                printMessage(".  ");
            } else {
                printMessage("   ");
            }
        }
    }

    private void printShipCell(ShipCell cell, String s) {
        if (cell.getShipCellTypeEnum() == ShipCellTypeEnum.DEAD_SHIP) {
            printMessage("#  ");
        } else if (cell.getShipCellTypeEnum() == ShipCellTypeEnum.DEAD_CELL) {
            printMessage("X  ");
        } else {
            printMessage(s);
        }
    }

    private void printHead() {
        System.out.print("    1  2  3  4  5  6  7  8  9  10");
    }

    private void printHeads() {
        System.out.print("    1  2  3  4  5  6  7  8  9  10");
        printEmptySpace();
        printEmptySpace();
        System.out.print("  1  2  3  4  5  6  7  8  9  10");
        this.goToNewRow();
    }

    public void printMessage(String message) {
        System.out.print(message);
    }

    public void gap() {
        System.out.print("\n\n");
    }

    public void gotoNextLine() {
        System.out.print("\n");
    }

    public void printEmptySpace() {
        System.out.print("   ");
    }

    public void printShipList(Map<String, Integer> shipsInMap, String owner) {
        Set<String> ships = shipsInMap.keySet();

        printMessage("Okręty " + owner);
        gotoNextLine();
        printGroupOfShips("1", ships, shipsInMap);
        printGroupOfShips("2", ships, shipsInMap);
        printGroupOfShips("3", ships, shipsInMap);
        printGroupOfShips("4", ships, shipsInMap);
    }

    public void sleepPrint(int milliseconds) {
        try {
            sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void printGroupOfShips(String amountmMasts, Set<String> ships, Map<String, Integer> shipsInMap) {
        printMessage(amountmMasts + " masztowe : ");
        ships.stream().sorted().filter(ship -> ship.contains(amountmMasts))
                .filter(ship ->shipsInMap.get(ship)>0 )
                .forEach(ship -> printMessage(ship + " " + shipsInMap.get(ship) + ", "));
        gotoNextLine();
    }

    public void printMenu( String[] menu ){
        int index = 1;
        for(String position:menu){
            gotoNextLine();
            printMessage(index + " "+ position);
            index++;
        }
    }

}
