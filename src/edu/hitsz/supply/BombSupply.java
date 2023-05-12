package edu.hitsz.supply;
import edu.hitsz.aircraft.*;
import edu.hitsz.application.Game;
import edu.hitsz.application.ThreadPackage.*;
import java.util.List;

/**
 * Bomb Supply
 * @author Kosmischer
 * */

public class BombSupply extends AbstractSupply {

    public BombSupply(int locationX, int locationY, int speedX, int speedY){
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void supplyActive(HeroAircraft heroAircraft, List<AbstractEnemy> enemyAircrafts){
        Game.GLOBAL_OBSERVED_SUBJECTS.notifyObserver();
        MusicProxyThread.musicEffect(2);
        this.vanish();
    }

}
