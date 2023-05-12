package edu.hitsz.application.ThreadPackage;
import edu.hitsz.application.Game;

/**
 * The top thread of music control
 * which schedules the switching of bgm and the use of sound effects
 * @author Kosmischer
 * */

public class MusicProxyThread implements Runnable{

    public static boolean musicSwitch;
    public static boolean musicEndFlag = true;
    public static MusicThread bgmThread = new MusicThread("src/videos/bgm.wav", true);

    /**
     * Scheduling for the bgm switching when boss descending and vanishing
     * */
    @Override
    public void run(){
        while(Game.musicSwitch && !Game.gameOverFlag){
            System.out.print("");
            if(Game.gameOverFlag){
                bgmThread.terminate();
            }
            if(!Game.bgmType && musicEndFlag){
                bgmThread = new MusicThread("src/videos/bgm.wav", true);
                Game.executorService.execute(bgmThread);
                musicEndFlag = false;
            }
            else if(Game.bgmType && musicEndFlag){
                bgmThread = new MusicThread("src/videos/bgm_boss.wav", true);
                Game.executorService.execute(bgmThread);
                musicEndFlag = false;
            }
        }
    }

    /**
     * Schedule for all sorts of sound effects, listed beneath:
     * 1 for the activation of health and fire supply
     * 2 for the explosion of bomb supply
     * 3 for the sound effect of boss coming
     * 4 for the sound effect for game over
     * 5 for the shoot action of hero
     * 6 for the sound effect when enemy hit by a bullet
     * One possible defect of this method is to use many anonymous object
     * */
    public static void musicEffect(int effectType){
        if(Game.musicSwitch){
            String effectFileSrc = "";
            switch (effectType){
                case 1: effectFileSrc = "src/videos/get_supply.wav"; break;
                case 2: effectFileSrc = "src/videos/bomb_explosion.wav"; break;
                case 3: effectFileSrc = "src/videos/effect.wav"; break;
                case 4: effectFileSrc = "src/videos/game_over.wav"; break;
                case 5: effectFileSrc = "src/videos/bullet.wav"; break;
                case 6: effectFileSrc = "src/videos/bullet_hit.wav"; break;
                default:break;
            }
            Game.executorService.execute(new MusicThread(effectFileSrc, false));
        }
    }
}
