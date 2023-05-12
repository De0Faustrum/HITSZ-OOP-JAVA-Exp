package edu.hitsz.application.ThreadPackage;
import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.application.Game;

/**
 * When Fire Supply Activated, This Thread Allows HeroAircraft To Use Scattered Trajectory For 3 Seconds
 * @author Kosmischer
 * */

public class FireReinforceThread implements Runnable{

    private static final int FIRE_REINFORCE_DURATION = 3000;
    private final HeroAircraft hero;
    public FireReinforceThread(HeroAircraft hero){
        this.hero = hero;
    }

    /**
     * This thread uses the synchronized clock of the main thread
     * */
    @Override
    public void run(){
        try{
            if(Game.heroFireReinforceFlag && Math.abs(Game.TIME - Game.FIRE_REINFORCE_TIME) < FIRE_REINFORCE_DURATION) {
                hero.bulletReinforce();
            }
            else {
                hero.bulletRecover();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
