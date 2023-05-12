package edu.hitsz.aircraft;
import edu.hitsz.application.GameMode.StaticConfiguration;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;
import edu.hitsz.strategy.*;

/**
 * Hero, manipulated by player
 * @author hitsz
 * */

public class HeroAircraft extends AbstractAircraft {

    /**The singular instance of Hero*/
    static HeroAircraft instance;

    /**
     * Private Constructor
     * */
    private HeroAircraft(int locationX, int locationY, int speedX, int speedY, int hp, TrajectoryStrategy trajectoryStrategy) {
        super(locationX, locationY, speedX, speedY, hp, trajectoryStrategy);
        this.shootNum = StaticConfiguration.heroBasicFire;
        this.shootDamage = 30;
        this.direction = -1;
    }

    /**
     * Get singular instance
     * @return instance of hero
     * */
    public static HeroAircraft getHeroAircraftInstance() {
        if (instance == null) {
            instance = new HeroAircraft(Main.WINDOW_WIDTH / 2,
                    Main.WINDOW_HEIGHT - ImageManager.HERO_IMAGE.getHeight(),
                    0, 0, 100000, new DirectShootStrategy());
        }
        return instance;
    }

    /**
     * Hero is manipulated by mouse, not through forward method
     * */
    @Override
    public void forward() {}

    /**
     * Called after Fire Supply activated
     * @return Fire after Reinforcement
     * */
    public int bulletReinforce() {
        this.shootNum = 5;
        this.trajectoryStrategy = new ScatteredShootStrategy();
        return this.shootNum;
    }

    public void bulletRecover(){
        this.shootNum = StaticConfiguration.heroBasicFire;
        this.trajectoryStrategy = new DirectShootStrategy();
    }

    public void damageReinforce(){
        this.shootDamage += 10;
    }

}
