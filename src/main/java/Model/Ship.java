package Model;

import Enums.ShipCellTypeEnum;
import Enums.ShipTypeEnum;

public class Ship implements CellInterface {

    ShipTypeEnum shipTypeEnum;
    ShipCellTypeEnum shipCellTypeEnum;

    public Ship(int amountOfMasts) {
        shipCellTypeEnum = ShipCellTypeEnum.LIVE;
        if(amountOfMasts==1){
            shipTypeEnum = ShipTypeEnum.ONE_MAST;
        } else if(amountOfMasts==2){
            shipTypeEnum = ShipTypeEnum.TWO_MASTS;
        }else if(amountOfMasts==3){
            shipTypeEnum = ShipTypeEnum.TREE_MASTS;
        }else if(amountOfMasts==4){
            shipTypeEnum = ShipTypeEnum.FOUR_MAST;
        }
    }

    @Override
    public void hit() {
        this.shipCellTypeEnum = ShipCellTypeEnum.DEAD;
    }

    @Override
    public Enum getType() {
        return shipCellTypeEnum;
    }

    @Override
    public void setEmptySpace() {

    }

    public ShipTypeEnum getShipTypeEnum() {
        return shipTypeEnum;
    }

    public void setShipTypeEnum(ShipTypeEnum shipTypeEnum) {
        this.shipTypeEnum = shipTypeEnum;
    }

    public ShipCellTypeEnum getShipCellTypeEnum() {
        return shipCellTypeEnum;
    }

    public void setShipCellTypeEnum(ShipCellTypeEnum shipCellTypeEnum) {
        this.shipCellTypeEnum = shipCellTypeEnum;
    }

}
