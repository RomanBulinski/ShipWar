package View;

import Enums.SeaCellTypeEnum;
import Enums.ShipCellTypeEnum;
import Enums.ShipTypeEnum;
import Model.Board;
import Model.CellInterface;
import Model.Sea;
import Model.Ship;

public class Printer {

    private int indexLine = 1;

    public void printBoard( Board board ) {
        this.printHead();
        for (CellInterface[] line : board.getBoard()) {
            this.printLineIndex();
            this.printRow(line);
            this.goToNewRow();
        }
        indexLine = 1;
        this.printMessage(  board.getOwner().getName() );
        this.gap();
    }

    private void goToNewRow() {
        printMessage("\n");
        indexLine++;
    }

    //TODO change to ternary operator
    private void printLineIndex() {
        if (indexLine < 10) {
            printMessage(" " + indexLine);
        } else {
            printMessage("" + 10);
        }
        printMessage("  ");
    }

    private void printRow(CellInterface[] line) {
        for (CellInterface cell : line) {
            if (cell instanceof Ship) {
                if (((Ship) cell).getShipTypeEnum() == ShipTypeEnum.ONE_MAST) {
                    printShipCell((Ship) cell, "1  ");
                } else if (((Ship) cell).getShipTypeEnum() == ShipTypeEnum.TWO_MASTS) {
                    printShipCell((Ship) cell, "2  ");
                } else if (((Ship) cell).getShipTypeEnum() == ShipTypeEnum.TREE_MASTS) {
                    printShipCell((Ship) cell, "3  ");
                } else if (((Ship) cell).getShipTypeEnum() == ShipTypeEnum.FOUR_MAST) {
                    printShipCell((Ship) cell, "4  ");
                }

            } else if (((Sea) cell).getSeaCellTypeEnum() == SeaCellTypeEnum.EMPTY_SPACE) {
                printMessage(".  ");
            } else if (((Sea) cell).getSeaCellTypeEnum() == SeaCellTypeEnum.DEAD) {
                printMessage("x  ");
            } else {
                printMessage(".  ");
            }
        }
    }

    private void printShipCell(Ship cell, String s) {
        if (cell.getShipCellTypeEnum() == ShipCellTypeEnum.DEAD) {
            printMessage("X  ");
        } else {
            printMessage(s);
        }
    }

    private void printHead() {
        System.out.print("    A  B  C  D  E  F  G  H  I  J\n");
    }

    public void printMessage(String message) {
        System.out.print(message);
    }

    public void gap() {
        System.out.print("\n\n");
    }
}
