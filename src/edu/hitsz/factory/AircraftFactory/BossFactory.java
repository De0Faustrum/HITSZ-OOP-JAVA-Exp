package edu.hitsz.factory.AircraftFactory;
import edu.hitsz.aircraft.*;
import edu.hitsz.application.*;
import edu.hitsz.application.GameMode.StaticConfiguration;
import edu.hitsz.strategy.*;

/**
 * Concrete factory class
 * @author Kosmischer
 * */

public class BossFactory implements AircraftFactory{
    @Override
    public AbstractEnemy createAircraft(){
        return new BossEnemy(
                (int) (0.5* Main.WINDOW_WIDTH - ImageManager.ELITE_ENEMY_IMAGE.getWidth()),
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.05)+80,2,0, StaticConfiguration.bossHp, new ScatteredShootStrategy());
    }
}
