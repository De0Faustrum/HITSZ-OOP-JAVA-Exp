package edu.hitsz.factory.SupplyFactory;
import edu.hitsz.supply.*;

/**
 * Concrete factory class
 * @author Kosmischer
 * */

public class HealthSupplyFactory implements SupplyFactory{
    @Override
    public AbstractSupply createSupply(int locationX, int locationY){
        return new HealthSupply(locationX, locationY, 0, 5);
    }
}
