package edu.hitsz.factory.SupplyFactory;
import edu.hitsz.supply.*;

/**
 * Concrete factory class
 * @author Kosmischer
 * */

public class FireSupplyFactory implements SupplyFactory{
    @Override
    public AbstractSupply createSupply(int locationX, int locationY){
        return new FireSupply(locationX, locationY, 0, 5);
    }
}
