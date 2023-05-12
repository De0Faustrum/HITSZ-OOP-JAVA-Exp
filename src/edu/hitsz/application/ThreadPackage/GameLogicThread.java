package edu.hitsz.application.ThreadPackage;

import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.application.Game;
import edu.hitsz.application.GameMode.StaticConfiguration;
import edu.hitsz.application.Main;

/***
 * This thread controls the core logic of the game
 * including calling all sorts of behavioral functions defined in Game.java
 * @author Kosmischer
 */

public class GameLogicThread implements Runnable{

    /**
     * Reference of the instance of the game and hero in main thread
     * */
    private final Game game;
    private final HeroAircraft hero;

    public GameLogicThread(Game game, HeroAircraft hero){
        this.game = game;
        this.hero = hero;
    }

    @Override
    public void run(){
        Game.TIME += StaticConfiguration.timeInterval;

        // Actions, refresh once every cycle
        if (game.timeCountAndNewCycleJudge()) {
            game.summonEnemy();
            game.shootAction();
        }

        // Actions, refresh once every timeInterval (30ms)
        game.bulletsMoveAction();
        game.enemyMoveAction();
        game.supplyMoveAction();
        game.crashCheckAction();
        game.vanishCheck();
        game.postProcessAction();
        game.repaint();

        // Game Over
        if (hero.getHp() <= 0) {
            MusicProxyThread.musicEffect(4);
            Game.executorService.shutdown();
            Game.gameOverFlag = true;
            System.out.println("Game Over!");
            Game.RECORD_MANAGER.readObjectFromFile();
            Main.displayRanking();
            Main.scoreCheck();
            Game.RECORD_MANAGER.writeObjectToFile();
        }
    }
}
