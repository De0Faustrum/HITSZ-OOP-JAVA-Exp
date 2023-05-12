package edu.hitsz.supply;
import edu.hitsz.aircraft.*;
import edu.hitsz.application.ThreadPackage.*;

import java.util.List;

/**
 * Health Supply to recover HP
 * @author Kosmischer
 * */

public class HealthSupply extends AbstractSupply {

    public HealthSupply(int locationX, int locationY, int speedX, int speedY){
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void supplyActive(HeroAircraft heroAircraft, List<AbstractEnemy> enemyAircrafts){
        heroAircraft.increaseHp(60);
        MusicProxyThread.musicEffect(1);
        this.vanish();
    }
}


