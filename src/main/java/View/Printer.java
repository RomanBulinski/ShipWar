package View;
import Enums.CellStateEnum;
import Model.Cell;

public class Printer {

    private int indexLine = 1;

    public void printBoard( Cell[][] board ){
        this.printHead();
        for( Cell[] line : board){
            this.printLineIndex();
            this.printRow(line);
            this.goToNewRow();
        }
    }

    private void goToNewRow() {
        System.out.print( "\n");
        indexLine++;
    }

    //TODO change to ternary operator
    private void printLineIndex() {
        if(indexLine<10){
            System.out.print(" "+indexLine);
        }else{
            System.out.print(10);
        }
        System.out.print("  ");
    }

    private void printRow(Cell[] line) {
        for( Cell cell : line){
            if(cell.getCellStateEnum() == CellStateEnum.SEA_CELL ){
                System.out.print( ".  ");
            }else{
                System.out.print( "o  ");
            }

        }
    }

    private void printHead(){
        System.out.print("    A  B  C  D  E  F  G  H  I  J\n");
    }



}
