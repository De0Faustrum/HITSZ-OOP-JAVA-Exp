package edu.hitsz.supply;
import edu.hitsz.aircraft.*;
import edu.hitsz.application.Game;
import edu.hitsz.application.ThreadPackage.*;

import java.util.List;

/**
 * Fire supply to reinforce bullet
 * @author kosmischer
 * */

public class FireSupply extends AbstractSupply {

    public FireSupply(int locationX, int locationY, int speedX, int speedY){
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void supplyActive(HeroAircraft heroAircraft, List<AbstractEnemy> enemyAircrafts){
        Game.heroFireReinforceFlag = true;
        Game.FIRE_REINFORCE_TIME = Game.TIME;
        MusicProxyThread.musicEffect(1);
        this.vanish();
    }
}
