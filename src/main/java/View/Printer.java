package View;

import Enums.SeaCellTypeEnum;
import Enums.ShipCellTypeEnum;
import Enums.ShipTypeEnum;
import Model.CellInterface;
import Model.Sea;
import Model.Ship;

public class Printer {

    private int indexLine = 1;

    public void printBoard(CellInterface[][] board) {
        this.printHead();
        for (CellInterface[] line : board) {
            this.printLineIndex();
            this.printRow(line);
            this.goToNewRow();
        }
        indexLine = 1;
    }

    private void goToNewRow() {
        System.out.print("\n");
        indexLine++;
    }

    //TODO change to ternary operator
    private void printLineIndex() {
        if (indexLine < 10) {
            System.out.print(" " + indexLine);
        } else {
            System.out.print(10);
        }
        System.out.print("  ");
    }

    private void printRow(CellInterface[] line) {
        for (CellInterface cell : line) {
            if (cell instanceof Ship) {
                if (((Ship) cell).getShipTypeEnum() == ShipTypeEnum.ONE_MAST) {
                    if (((Ship) cell).getShipCellTypeEnum() == ShipCellTypeEnum.DEAD) {
                        System.out.print("X  ");
                    }else{
                        System.out.print("1  ");
                    }
                } else if (((Ship) cell).getShipTypeEnum() == ShipTypeEnum.TWO_MASTS) {
                    if (((Ship) cell).getShipCellTypeEnum() == ShipCellTypeEnum.DEAD) {
                        System.out.print("X  ");
                    }else{
                        System.out.print("2  ");
                    }
                } else if (((Ship) cell).getShipTypeEnum() == ShipTypeEnum.TREE_MASTS) {
                    if (((Ship) cell).getShipCellTypeEnum() == ShipCellTypeEnum.DEAD) {
                        System.out.print("X  ");
                    }else{
                        System.out.print("3  ");
                    }
                } else if (((Ship) cell).getShipTypeEnum() == ShipTypeEnum.FOUR_MAST) {
                    if (((Ship) cell).getShipCellTypeEnum() == ShipCellTypeEnum.DEAD) {
                        System.out.print("X  ");
                    }else{
                        System.out.print("4 ");
                    }
                }

            } else if (((Sea) cell).getSeaCellTypeEnum() == SeaCellTypeEnum.EMPTY_SPACE) {
                System.out.print(".  ");
            } else if (((Sea) cell).getSeaCellTypeEnum() == SeaCellTypeEnum.DEAD) {
                System.out.print("x  ");
            } else {
                System.out.print(".  ");
            }
        }
    }

    private void printHead() {
        System.out.print("    A  B  C  D  E  F  G  H  I  J\n");
    }

    public void printMessage(String message){
        System.out.print( message );
    }

}
