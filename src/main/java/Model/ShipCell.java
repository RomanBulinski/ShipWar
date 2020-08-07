package Model;

import Enums.ShipCellTypeEnum;
import Enums.ShipMastsTypeEnum;

public class ShipCell implements CellInterface {

    ShipMastsTypeEnum shipMastsTypeEnum;
    ShipCellTypeEnum shipCellTypeEnum;
    String shipCellId;

    public ShipCell(int amountOfMasts) {
        shipCellTypeEnum = ShipCellTypeEnum.LIVE_CELL;
        if (amountOfMasts == 1) {
            shipMastsTypeEnum = shipMastsTypeEnum.ONE_MAST;
        } else if (amountOfMasts == 2) {
            shipMastsTypeEnum = shipMastsTypeEnum.TWO_MASTS;
        } else if (amountOfMasts == 3) {
            shipMastsTypeEnum = shipMastsTypeEnum.TREE_MASTS;
        } else if (amountOfMasts == 4) {
            shipMastsTypeEnum = shipMastsTypeEnum.FOUR_MASTS;
        }
    }

    @Override
    public void hitcell() {
        this.shipCellTypeEnum = ShipCellTypeEnum.DEAD_CELL;
    }

    @Override
    public Enum getType() {
        return shipCellTypeEnum;
    }

    @Override
    public void setEmptySpace() {

    }
    public String getId() { return shipCellId; }

    public ShipMastsTypeEnum getShipTypeEnum() {
        return shipMastsTypeEnum;
    }

    public void setShipTypeEnum(ShipMastsTypeEnum shipTypeEnum) {
        this.shipMastsTypeEnum = shipTypeEnum;
    }

    public ShipCellTypeEnum getShipCellTypeEnum() {
        return shipCellTypeEnum;
    }

    public void setShipCellTypeEnum(ShipCellTypeEnum shipCellTypeEnum) {
        this.shipCellTypeEnum = shipCellTypeEnum;
    }

    @Override
    public void setId(String shipCellId) {
        this.shipCellId = shipCellId;
    }


}
