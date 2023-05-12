package edu.hitsz.factory.AircraftFactory;
import edu.hitsz.aircraft.*;
import edu.hitsz.application.*;
import edu.hitsz.application.GameMode.StaticConfiguration;
import edu.hitsz.strategy.*;

/**
 * Concrete factory class
 * @author Kosmischer
 * */

public class MobFactory implements AircraftFactory{
    @Override
    public AbstractEnemy createAircraft(){
        return new MobEnemy((int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.MOB_ENEMY_IMAGE.getWidth())),
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.05),
                0, StaticConfiguration.mobSpeedY, StaticConfiguration.mobHp, new DirectShootStrategy());
    }
}
