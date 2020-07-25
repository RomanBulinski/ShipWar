package View;

import Enums.ShipTypeEnum;
import Model.CellInterface;
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
            if(cell instanceof Ship){
                if (((Ship) cell).getShipTypeEnum() == ShipTypeEnum.ONE_MAST) {
                    System.out.print("1  ");
                }else if (((Ship) cell).getShipTypeEnum() == ShipTypeEnum.TWO_MASTS) {
                    System.out.print("2  ");
                }else if (((Ship) cell).getShipTypeEnum() == ShipTypeEnum.TREE_MASTS) {
                    System.out.print("3  ");
                }else if (((Ship) cell).getShipTypeEnum() == ShipTypeEnum.FOUR_MAST) {
                    System.out.print("4  ");
                }
            }else{
                System.out.print(".  ");
            }
        }
    }

    private void printHead() {
        System.out.print("    A  B  C  D  E  F  G  H  I  J\n");
    }


}
