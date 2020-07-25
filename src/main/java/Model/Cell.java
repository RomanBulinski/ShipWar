package Model;

import Enums.CellStateEnum;

public class Cell {

    CellStateEnum cellStateEnum;

    public Cell(){
        setCellStateEnum(CellStateEnum.SEA_CELL);
    }

    public void setCellStateEnum(CellStateEnum cellStateEnum) {
        this.cellStateEnum = cellStateEnum;
    }

    public CellStateEnum getCellStateEnum() {
        return cellStateEnum;
    }




}
