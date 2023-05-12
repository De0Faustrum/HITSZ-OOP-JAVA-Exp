package edu.hitsz.aircraft;
import edu.hitsz.application.Game;
import edu.hitsz.strategy.TrajectoryStrategy;
import edu.hitsz.supply.*;
import java.util.List;

/**
 * Mob enemy, cannot shoot bullet
 * @author hitsz
 * */

public class MobEnemy extends AbstractEnemy {

    public MobEnemy(int locationX, int locationY, int speedX, int speedY, int hp, TrajectoryStrategy trajectoryStrategy) {
        super(locationX, locationY, speedX, speedY, hp, trajectoryStrategy);
        this.shootNum = 0;
        this.direction = 1;
        this.shootDamage = 20;
    }

    @Override
    public void supplySummon(List<AbstractSupply> propSupplys) {}

}
