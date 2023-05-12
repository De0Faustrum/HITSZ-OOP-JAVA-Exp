package edu.hitsz.aircraft;
import edu.hitsz.application.Game;
import edu.hitsz.factory.SupplyFactory.*;
import edu.hitsz.supply.*;
import edu.hitsz.strategy.TrajectoryStrategy;
import java.util.List;

/**
 * Elite Enemy
 * @author Kosmischer
 * */

public class EliteEnemy extends AbstractEnemy {

    public EliteEnemy(int locationX, int locationY, int speedX, int speedY, int hp, TrajectoryStrategy trajectoryStrategy) {
        super(locationX, locationY, speedX, speedY, hp, trajectoryStrategy);
        this.shootNum = 1;
        this.shootDamage = 20;
        this.direction = 1;

    }

    @Override
    public void supplySummon(List<AbstractSupply> propSupplies){
        final SupplyFactory healthSupplyFactory = new HealthSupplyFactory();
        final SupplyFactory fireSupplyFactory = new FireSupplyFactory();
        final SupplyFactory bombSupplyFactory = new BombSupplyFactory();
        final double bound1 = 0.3, bound2 = 0.6, bound3 = 0.9;
        double randomizer = Math.random();
        if(randomizer < bound1){
            supplyFactory = healthSupplyFactory;
            propSupplies.add(supplyFactory.createSupply(this.getLocationX(),this.getLocationY()));
        }
        if(randomizer >= bound1 && randomizer < bound2){
            supplyFactory = fireSupplyFactory;
            propSupplies.add(supplyFactory.createSupply(this.getLocationX(),this.getLocationY()));
        }
        if(randomizer >= bound2 && randomizer < bound3){
            supplyFactory = bombSupplyFactory;
            propSupplies.add(supplyFactory.createSupply(this.getLocationX(),this.getLocationY()));
        }
    }


}
