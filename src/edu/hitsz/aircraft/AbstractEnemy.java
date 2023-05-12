package edu.hitsz.aircraft;
import edu.hitsz.application.Game;
import edu.hitsz.supply.AbstractSupply;
import edu.hitsz.strategy.*;
import java.util.List;

/**
 * Superclass of enemy aircraft
 * @author Kosmischer
 * */

public abstract class AbstractEnemy extends AbstractAircraft{

    public AbstractEnemy(int locationX, int locationY, int speedX, int speedY, int hp, TrajectoryStrategy trajectoryStrategy){
        super(locationX, locationY, speedX, speedY, hp, trajectoryStrategy);
        Game.GLOBAL_OBSERVED_SUBJECTS.attach(this);
    }

    /**生成道具
     * @param propSupplies 道具链表
     */
    public abstract void supplySummon(List<AbstractSupply> propSupplies);
}
