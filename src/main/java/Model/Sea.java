package Model;

import Enums.SeaCellTypeEnum;

public class Sea implements CellInterface{

    SeaCellTypeEnum seaCellTypeEnum;

    public Sea(){
        seaCellTypeEnum = SeaCellTypeEnum.LIVE;
    }

    @Override
    public void hit() {
       this.seaCellTypeEnum = SeaCellTypeEnum.DEAD;
    }

    @Override
    public Enum getType() {
        return seaCellTypeEnum;
    }

    public SeaCellTypeEnum getSeaCellTypeEnum() {
        return seaCellTypeEnum;
    }

    public void setSeaCellTypeEnum(SeaCellTypeEnum seaCellTypeEnum) {
        this.seaCellTypeEnum = seaCellTypeEnum;
    }
}
