package Model;

import Enums.SeaCellTypeEnum;

public class SeaCell implements CellInterface {

    SeaCellTypeEnum seaCellTypeEnum;

    public SeaCell() {
        seaCellTypeEnum = SeaCellTypeEnum.LIVE_CELL;
    }

    @Override
    public void hitCell() {
        this.seaCellTypeEnum = SeaCellTypeEnum.DEAD_CELL;
    }

    @Override
    public Enum getType() {
        return seaCellTypeEnum;
    }


    @Override
    public void setEmptySpace() {
            this.seaCellTypeEnum = SeaCellTypeEnum.EMPTY_SPACE;
    }

    @Override
    public void setId(String id) {
        //TODO nothing to do
    }

    public SeaCellTypeEnum getSeaCellTypeEnum() {
        return seaCellTypeEnum;
    }

    public void setSeaCellTypeEnum(SeaCellTypeEnum seaCellTypeEnum) {
        this.seaCellTypeEnum = seaCellTypeEnum;
    }
}
