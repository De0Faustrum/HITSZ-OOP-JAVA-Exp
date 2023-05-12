package edu.hitsz.application.GameMode;
import edu.hitsz.aircraft.AbstractEnemy;
import edu.hitsz.application.*;
import edu.hitsz.bullet.HeroBullet;
import javax.imageio.ImageIO;
import java.io.*;

/**
 * Hard Mode
 * Enemies will be stronger while game progressing
 * Also, your hero also get reinforcement when reach certain score threshold
 * @author Kosmischer
 * */

public class HardGame extends Game {

    private int bulletNumStage = 0;
    private int bulletDamageStage = 0;

    public HardGame(){
        super();
        StaticConfiguration.timeInterval = 20;
        StaticConfiguration.enemyMaxNumber = 9;
        StaticConfiguration.cycleDuration = 400;
        StaticConfiguration.difficultyMode = 3;
        StaticConfiguration.mobSpeedY = 6;
        StaticConfiguration.eliteSpeedY = 6;
        StaticConfiguration.eliteShootInterval = 1;
        StaticConfiguration.heroBasicFire = 1;
        StaticConfiguration.mobHp = 30;
        StaticConfiguration.eliteHp = 60;
        StaticConfiguration.bossHp = 1200;
        StaticConfiguration.ratioOfEliteEnemy = 0.75;
    }

    public void reinforceEnemyAndHero(){

        if(SCORE >= 500 && SCORE < 1500 && bulletNumStage == 0){
            StaticConfiguration.heroBasicFire = 2;
            bulletNumStage = 1;
            System.out.println("Hero Reinforced! You now have 2 bullets in one shot!");
        }
        if(SCORE >= 1000 && SCORE < 2500 && bulletDamageStage == 0){
            heroAircraft.damageReinforce();
            bulletDamageStage = 1;
            try {
                ImageManager.HERO_BULLET_IMAGE = ImageIO.read(new FileInputStream("src/images/bullet_hero_3.png"));
                ImageManager.CLASSNAME_IMAGE_MAP.put(HeroBullet.class.getName(), ImageManager.HERO_BULLET_IMAGE);
            }
            catch (IOException e){
                e.printStackTrace();
            }

            System.out.println("Hero Reinforced! Bullet damage higher!");
        }
        if(SCORE >= 1500 && SCORE < 3500 && bulletNumStage == 1){
            StaticConfiguration.heroBasicFire = 3;
            bulletNumStage = 2;
            System.out.println("Hero Reinforced! You now have 3 bullets in one shot!");

        }
        if(SCORE >= 2000 && SCORE < 3000 && bulletDamageStage == 1){
            heroAircraft.damageReinforce();
            bulletDamageStage = 2;
            try {
                ImageManager.HERO_BULLET_IMAGE = ImageIO.read(new FileInputStream("src/images/bullet_hero_2.png"));
                ImageManager.CLASSNAME_IMAGE_MAP.put(HeroBullet.class.getName(), ImageManager.HERO_BULLET_IMAGE);
            }
            catch (IOException e){
                e.printStackTrace();
            }
            System.out.println("Hero Reinforced! Bullet damage higher!");
        }
        if(SCORE >= 3000 && bulletDamageStage == 2){
            heroAircraft.damageReinforce();
            bulletDamageStage = 3;
            try {
                ImageManager.HERO_BULLET_IMAGE = ImageIO.read(new FileInputStream("src/images/bullet_hero_4.png"));
                ImageManager.CLASSNAME_IMAGE_MAP.put(HeroBullet.class.getName(), ImageManager.HERO_BULLET_IMAGE);
            }
            catch (IOException e){
                e.printStackTrace();
            }
            System.out.println("Hero Reinforced! Bullet damage higher!");
        }
    }

    @Override
    public void summonEnemy(){

        reinforceEnemyAndHero();

        if((SCORE+300)%600 <= 30){
            seBossComingFlag = true;
            boolean bossComingFlag = false;
            for(AbstractEnemy enemyAircraft: enemyAircrafts){
                if(enemyAircraft == bossEnemy){
                    bossComingFlag = true;
                    break;
                }
            }
            if(!bossComingFlag){
                AircraftFactory = bossFactory;
                bossEnemy = AircraftFactory.createAircraft();
                enemyAircrafts.add(bossEnemy);
                if(StaticConfiguration.bossHp <= 3000){
                    StaticConfiguration.bossHp = StaticConfiguration.bossHp + 300;
                    StaticConfiguration.eliteHp = StaticConfiguration.eliteHp + 15;
                    StaticConfiguration.mobHp = StaticConfiguration.mobHp + 5;
                    System.out.println("Enemy Reinforced! HP Multiply index: " + StaticConfiguration.bossHp/12.0 +"%");
                }

            }
        }
        else{
            seBossComingFlag = false;
        }

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
