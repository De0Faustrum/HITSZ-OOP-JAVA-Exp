package edu.hitsz.supply;
import java.util.ArrayList;
import java.util.List;
import edu.hitsz.aircraft.*;
import edu.hitsz.application.Main;
import edu.hitsz.basic.AbstractFlyingObject;
import edu.hitsz.supply.obs.AbstractObserver;

/**
 * Superclass of 3 concrete prop supplies
 * @author Kosmischer
 * */

public abstract class AbstractSupply extends AbstractFlyingObject {



    public AbstractSupply(int locationX, int locationY, int speedX, int speedY){
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void forward() {
        super.forward();
        if (locationY >= Main.WINDOW_HEIGHT ) {
            vanish();
        }
    }

    /**
     * Abstract method for supply to act
     * @param heroAircraft Hero
     * @param enemyAircrafts Enemy List
     * */
    public abstract void supplyActive(HeroAircraft heroAircraft, List<AbstractEnemy> enemyAircrafts);

}
