package edu.hitsz.application.GameMode;
import edu.hitsz.application.Game;

/**
 * Easy Mode
 * There will be no boss enemy
 * @author Kosmischer
 * */

public class EasyGame extends Game {
    public EasyGame(){
        super();
        StaticConfiguration.timeInterval = 40;
        StaticConfiguration.enemyMaxNumber = 5;
        StaticConfiguration.cycleDuration = 500;
        StaticConfiguration.difficultyMode = 1;
        StaticConfiguration.mobSpeedY = 5;
        StaticConfiguration.eliteSpeedY = 5;
        StaticConfiguration.eliteShootInterval = 2;
        StaticConfiguration.heroBasicFire = 3;
        StaticConfiguration.mobHp = 30;
        StaticConfiguration.eliteHp = 30;
        StaticConfiguration.bossHp = 800;
        StaticConfiguration.ratioOfEliteEnemy = 0.5;
    }

    @Override
    public void summonEnemy(){
        if (enemyAircrafts.size() < StaticConfiguration.enemyMaxNumber) {
            double randomizer = Math.random();
            if(randomizer > StaticConfiguration.ratioOfEliteEnemy){
                AircraftFactory = eliteFactory;
                enemyAircrafts.add(
                        AircraftFactory.createAircraft()
                );
            }
            else {
                AircraftFactory = mobFactory;
                enemyAircrafts.add(
                        AircraftFactory.createAircraft()
                );
            }
        }
    }
}
