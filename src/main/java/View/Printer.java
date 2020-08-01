package View;

import Enums.SeaCellTypeEnum;
import Enums.ShipCellTypeEnum;
import Enums.ShipTypeEnum;
import Model.Board;
import Model.CellInterface;
import Model.SeaCell;
import Model.ShipCell;

import java.util.Map;
import java.util.Set;
import static java.lang.Thread.sleep;

public class Printer {

    private int indexLine = 1;

    public void printBoard( Board board ) {
        this.printHead();
        this.goToNewRow();
        for (CellInterface[] line : board.getBoard()) {
            this.printLineIndex();
            this.printRow(line);
            this.goToNewRow();
            indexLine++;
        }
        indexLine = 1;
        this.printMessage(  board.getOwner().getName() );
        this.gap();
    }

    public void printTwoBoards( Board board1, Board board2 ) {
        this.printHeads();

        CellInterface[][] rows1 = board1.getBoard();
        CellInterface[][] rows2 = board2.getBoard();

        for(int i = 0; i<rows1.length; i++){
            this.printLineIndex();
            this.printRow(rows1[i]);
            this.printEmptySpace();
            this.printLineIndex();
            this.printRow(rows2[i]);
            this.goToNewRow();
            indexLine++;
        }
        indexLine = 1;
        this.printMessage(  board1.getOwner().getName() );
        this.printMessage(  "                              ");
        this.printMessage(  board2.getOwner().getName() );
        this.gap();

        printShipList( board1.getShipsInList() );
        goToNewRow();
        printShipList( board2.getShipsInList() );
        goToNewRow();

    }

    private void goToNewRow() {
        printMessage("\n");
    }

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
            if (cell instanceof ShipCell) {
                if (((ShipCell) cell).getShipTypeEnum() == ShipTypeEnum.ONE_MAST) {
                    printShipCell((ShipCell) cell, "1  ");
                } else if (((ShipCell) cell).getShipTypeEnum() == ShipTypeEnum.TWO_MASTS) {
                    printShipCell((ShipCell) cell, "2  ");
                } else if (((ShipCell) cell).getShipTypeEnum() == ShipTypeEnum.TREE_MASTS) {
                    printShipCell((ShipCell) cell, "3  ");
                } else if (((ShipCell) cell).getShipTypeEnum() == ShipTypeEnum.FOUR_MAST) {
                    printShipCell((ShipCell) cell, "4  ");
                }
            } else if (((SeaCell) cell).getSeaCellTypeEnum() == SeaCellTypeEnum.EMPTY_SPACE) {
                printMessage("   ");
            } else if (((SeaCell) cell).getSeaCellTypeEnum() == SeaCellTypeEnum.DEAD) {
                printMessage(".  ");
            } else {
                printMessage("   ");
            }
        }
    }

    private void printShipCell(ShipCell cell, String s) {
        if (cell.getShipCellTypeEnum() == ShipCellTypeEnum.DEAD) {
            printMessage("X  ");
        } else {
            printMessage(s);
        }
    }

    private void printHead() {
        System.out.print("    A  B  C  D  E  F  G  H  I  J");
    }

    private void printHeads() {
        System.out.print("    A  B  C  D  E  F  G  H  I  J");
        printEmptySpace();
        printEmptySpace();
        printEmptySpace();
        System.out.print("A  B  C  D  E  F  G  H  I  J");
        this.goToNewRow();
    }

    public void printMessage(String message) { System.out.print(message); }

    public void gap() {
        System.out.print("\n\n");
    }

    public void gotoNextLine() { System.out.print("\n"); }

    public void printEmptySpace() {
        System.out.print("   ");
    }

    public void printShipList( Map<String,Integer> shipsInMap  ){
        Set<String> ships = shipsInMap.keySet();

        printMessage("Okręty");
        gotoNextLine();
        printGroupOfShips( "1",ships, shipsInMap);
        printGroupOfShips( "2",ships, shipsInMap);
        printGroupOfShips( "3",ships, shipsInMap);
        printGroupOfShips( "4",ships, shipsInMap);
    }

    public void sleepPrint(int milliseconds){
        try {
                sleep(milliseconds);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }

    private void printGroupOfShips ( String amountmMasts, Set<String> ships , Map<String,Integer> shipsInMap  ){
        printMessage(amountmMasts+ " masztowe : ");
        ships.stream().sorted().filter( x-> x.contains(amountmMasts)).forEach(x->printMessage(x+" "+shipsInMap.get(x)+", "));
        gotoNextLine();
    }



}
