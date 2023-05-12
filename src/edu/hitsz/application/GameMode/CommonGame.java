package edu.hitsz.application.GameMode;
import edu.hitsz.application.*;

/**
 * Common Mode
 * @author Kosmischer
 * */
public class CommonGame extends Game {
    public CommonGame(){
        super();
        StaticConfiguration.timeInterval = 30;
        StaticConfiguration.enemyMaxNumber = 7;
        StaticConfiguration.cycleDuration = 450;
        StaticConfiguration.difficultyMode = 2;
        StaticConfiguration.mobSpeedY = 7;
        StaticConfiguration.eliteSpeedY = 7;
        StaticConfiguration.eliteShootInterval = 2;
        StaticConfiguration.heroBasicFire = 2;
        StaticConfiguration.mobHp = 30;
        StaticConfiguration.eliteHp = 60;
        StaticConfiguration.bossHp = 1200;
        StaticConfiguration.ratioOfEliteEnemy = 0.65;
    }

}
