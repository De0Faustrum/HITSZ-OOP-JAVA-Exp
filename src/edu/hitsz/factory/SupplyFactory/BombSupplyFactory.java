package edu.hitsz.factory.SupplyFactory;
import edu.hitsz.supply.*;

/**
 * Concrete factory class
 * @author Kosmischer
 * */

public class BombSupplyFactory implements SupplyFactory{
    @Override
    public AbstractSupply createSupply(int locationX, int locationY){
        return new BombSupply(locationX, locationY, 0, 5);
    }
}

