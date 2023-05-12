package edu.hitsz.application.GameMode;

/**
 * This static module stores some parameters of game configuration
 * @author Kosmischer
 * */

public class StaticConfiguration {

    /**The difficulty of the game, enum: Simple, Common, Hard*/
    public static int difficultyMode;

    /**The interval of paint refreshing and all sorts of check*/
    public static  int timeInterval = 30;

    /**The interval of enemy summon and shoot action*/
    public static int cycleDuration = 500;

    /**The max number of enemies exist in the window*/
    public static int enemyMaxNumber = 5;

    public static int mobSpeedY = 5;

    public static int eliteSpeedY = 3;

    public static int eliteShootInterval = 2;

    public static int heroBasicFire = 1;

    public static int mobHp = 30;

    public static int eliteHp = 30;

    public static int bossHp = 800;

    public static double ratioOfEliteEnemy = 0.75;

}
