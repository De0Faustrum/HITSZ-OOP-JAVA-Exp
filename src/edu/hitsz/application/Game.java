package edu.hitsz.application;
import edu.hitsz.aircraft.*;
import edu.hitsz.application.GameMode.StaticConfiguration;
import edu.hitsz.application.ThreadPackage.*;
import edu.hitsz.bullet.*;
import edu.hitsz.basic.*;
import edu.hitsz.data.*;
import edu.hitsz.factory.AircraftFactory.*;
import edu.hitsz.supply.*;
import edu.hitsz.supply.obs.ObservedSubject;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;
import java.util.concurrent.*;

/**
 * Some Method will be overridden in concrete game mode
 * @author hitsz
 * */

public class Game extends JPanel {

    /**The instance of the global record manager*/
    public static ScoreDaoImpl RECORD_MANAGER = new ScoreDaoImpl();

    /**The global subject of observer pattern*/
    public static final ObservedSubject GLOBAL_OBSERVED_SUBJECTS = new ObservedSubject();

    /**The clock of the main thread*/
    public static int TIME = 0;

    /**Current score, +10 after destroying an enemy aircraft*/
    public static int SCORE = 0;

    /**Controller of the fire reinforce time*/
    public static int FIRE_REINFORCE_TIME = 0;

    /**
     * @Boolean true -> hero bullet scattering flying
     * @Boolean false -> hero bullet direct flying
     * */
    public static boolean heroFireReinforceFlag = false;

    /**@Boolean Flag to identify if the game is over*/
    public static boolean gameOverFlag = false;

    /**
     * @Boolean true -> Music ON
     * @Boolean false -> Music OFF
     * */
    public static boolean musicSwitch;

    /**
     * @Boolean true -> Play common bgm
     * @Boolean false -> Play boss bgm
     * */
    public static boolean bgmType = false;

    /**
     * @Boolean true -> Special effect activated
     * @Boolean false -> Special effect sleeping
     * */
    protected boolean seBossComingFlag = false;

    /**Image Manager*/
    private final ImageManager imageManager;

    /**Back image rolling controller*/
    private int backGroundTop = 0;

    /**Scheduled Thread Pool*/
    public static final ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(30);

    /**Enemy shoot interval, 2 cycle for a shot*/
    private int enemyShootInterval = 0;

    /**All sorts of aircraft instance*/
    protected final HeroAircraft heroAircraft = HeroAircraft.getHeroAircraftInstance();
    public static final List<AbstractEnemy> enemyAircrafts = new LinkedList<>();
    public static final List<BaseBullet> heroBullets = new LinkedList<>();
    public static final List<BaseBullet> enemyBullets = new LinkedList<>();
    public static final List<AbstractSupply> propSupplies = new LinkedList<>();
    protected AbstractEnemy bossEnemy;

    /**All Sorts of enemy factory*/
    protected final AircraftFactory mobFactory = new MobFactory();
    protected final AircraftFactory eliteFactory = new EliteFactory();
    protected final AircraftFactory bossFactory = new BossFactory();
    protected static AircraftFactory AircraftFactory;

    /**Record time in a cycle, mod cycleDuration*/
    private int cycleTime = 0;

    /**
     * Constructor, initializing hero controller and image manager
     * */
    public Game() {
        new HeroController(this, heroAircraft);
        imageManager = new ImageManager();
    }

    /**
     * Entrance of the Game
     * Put threads into the thread pool and execute
     * */
    public void action() {

        GameLogicThread gameLogicThread = new GameLogicThread(this, heroAircraft);
        FireReinforceThread fireReinforceThread = new FireReinforceThread(heroAircraft);
        MusicProxyThread musicProxyThread = new MusicProxyThread();

        executorService.scheduleWithFixedDelay(gameLogicThread, StaticConfiguration.timeInterval, StaticConfiguration.timeInterval, TimeUnit.MILLISECONDS);
        executorService.scheduleWithFixedDelay(fireReinforceThread, StaticConfiguration.timeInterval, StaticConfiguration.timeInterval, TimeUnit.MILLISECONDS);
        executorService.execute(musicProxyThread);
    }

    /**
     * Below are the methods which will be called in game logic thread to implement the main game action logic
     * */

    public boolean timeCountAndNewCycleJudge() {
        cycleTime += StaticConfiguration.timeInterval;
        if (cycleTime >= StaticConfiguration.cycleDuration) {
            // Enter a new cycle, the frequency is controlled by cycleDuration
            cycleTime %= StaticConfiguration.cycleDuration;
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Enemy Summoner, specified in readme.md
     * */
    public void summonEnemy(){
        if((SCORE+400)%700 <= 30){
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

    /**
     * Traverse all the existing aircraft and call shoot action
     * */
    public void shootAction() {
        enemyShootInterval++;
        enemyShootInterval = enemyShootInterval % StaticConfiguration.eliteShootInterval;
        if(enemyShootInterval == 0) {
            for (AbstractEnemy enemyAircraft : enemyAircrafts) {
                enemyBullets.addAll(enemyAircraft.shoot());
            }
        }
        heroBullets.addAll(heroAircraft.shoot());
        MusicProxyThread.musicEffect(5);
    }

    /**
     * Traverse all the existing bullets and call forward action
     * */
    public void bulletsMoveAction() {
        for (BaseBullet bullet : heroBullets) {
            bullet.forward();
        }
        for (BaseBullet bullet : enemyBullets) {
            bullet.forward();
        }
    }

    /**
     * Traverse all the existing enemies and call forward action
     * This does not include hero, which is controlled by the cursor of mouse
     * */
    public void enemyMoveAction() {
        for (AbstractEnemy enemyAircraft : enemyAircrafts) {
            enemyAircraft.forward();
        }
    }

    /**
     * Traverse all the existing supplies and call forward action
     * This does not include hero, which is controlled by the cursor of mouse
     * */
    public void supplyMoveAction(){
        for(AbstractSupply dropSupply : propSupplies) {
            dropSupply.forward();
        }
    }


    /**
     * 碰撞检测：
     * 1. 敌机攻击英雄
     * 2. 英雄攻击/撞击敌机
     * 3. 英雄获得补给
     */
    public void crashCheckAction() {
        for(BaseBullet enemybullet : enemyBullets) {
            if (enemybullet.notValid()) {
                continue;
            }
                if (heroAircraft.notValid()) {
                    continue;
                }
                if (heroAircraft.crash(enemybullet)) {
                    heroAircraft.decreaseHp(enemybullet.getPower());
                    enemybullet.vanish();
                }
        }

        // 英雄子弹攻击敌机
        for (BaseBullet bullet : heroBullets) {
            if (bullet.notValid()) {
                continue;
            }
            for (AbstractEnemy enemyAircraft : enemyAircrafts) {
                if (enemyAircraft.notValid()) {
                    // 已被其他子弹击毁的敌机，不再检测
                    // 避免多个子弹重复击毁同一敌机的判定
                    continue;
                }
                if (enemyAircraft.crash(bullet)) {
                    // 敌机撞击到英雄机子弹
                    // 敌机损失一定生命值
                    enemyAircraft.decreaseHp(bullet.getPower());
                    MusicProxyThread.musicEffect(6);
                    bullet.vanish();

                }
                // 英雄机 与 敌机 相撞，均损毁
                if (enemyAircraft.crash(heroAircraft) || heroAircraft.crash(enemyAircraft)) {
                    enemyAircraft.vanish();
                    heroAircraft.decreaseHp(Integer.MAX_VALUE);
                }
            }
        }

        for(AbstractSupply dropSupply : propSupplies){
            if (heroAircraft.crash(dropSupply) || dropSupply.crash(heroAircraft)){
                dropSupply.supplyActive(heroAircraft, enemyAircrafts);
            }
        }

    }


    public void vanishCheck(){
        for(AbstractEnemy enemyAircraft: enemyAircrafts){
            if (enemyAircraft.notValid()) {
                SCORE += 10;
                enemyAircraft.supplySummon(propSupplies);
            }
        }
    }

    /**
     * 后处理：
     * 1. 删除无效的子弹
     * 2. 删除无效的敌机
     * 无效的原因可能是撞击或者飞出边界
     */
    public void postProcessAction() {
        enemyBullets.removeIf(AbstractFlyingObject::notValid);
        heroBullets.removeIf(AbstractFlyingObject::notValid);
        enemyAircrafts.removeIf(AbstractFlyingObject::notValid);
        propSupplies.removeIf(AbstractFlyingObject::notValid);
    }


    //***********************
    //      Paint 各部分
    //***********************

    /**重写paint方法
     * 通过重复调用paint方法，实现游戏动画*/
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // 绘制背景,图片滚动
        g.drawImage(imageManager.getBackgroundImage(), 0, this.backGroundTop - Main.WINDOW_HEIGHT, null);
        g.drawImage(imageManager.getBackgroundImage(), 0, this.backGroundTop, null);
        this.backGroundTop += 1;
        if (this.backGroundTop == Main.WINDOW_HEIGHT) {
            this.backGroundTop = 0;
        }

        // 先绘制子弹，后绘制飞机
        // 这样子弹显示在飞机的下层
        paintImageWithPositionRevised(g, enemyBullets);
        paintImageWithPositionRevised(g, heroBullets);
        paintImageWithPositionRevised(g, enemyAircrafts);
        paintImageWithPositionRevised(g, propSupplies);

        g.drawImage(ImageManager.HERO_IMAGE, heroAircraft.getLocationX() - ImageManager.HERO_IMAGE.getWidth() / 2,
                heroAircraft.getLocationY() - ImageManager.HERO_IMAGE.getHeight() / 2, null);

        //绘制得分和生命值
        paintScoreAndLife(g);
        if(seBossComingFlag){
            paintSpecialEffect(g);
        }


    }

    private void paintImageWithPositionRevised(Graphics g, List<? extends AbstractFlyingObject> objects) {
        if (objects.size() == 0) {
            return;
        }

        for (AbstractFlyingObject object : objects) {
            BufferedImage image = object.getImage();
            assert image != null : objects.getClass().getName() + " has no image! ";
            g.drawImage(image, object.getLocationX() - image.getWidth() / 2,
                    object.getLocationY() - image.getHeight() / 2, null);
        }
    }

    private void paintScoreAndLife(Graphics g) {
        int x = 10;
        int y = 25;
        g.setColor(new Color(16711680));
        g.setFont(new Font("SansSerif", Font.BOLD, 22));
        g.drawString("SCORE:" + SCORE, x, y);
        y = y + 20;
        g.drawString("LIFE:" + this.heroAircraft.getHp(), x, y);
    }

    private void paintSpecialEffect(Graphics g) {
        int x = 125;
        int y = 200;
        g.drawImage(ImageManager.SE_BOSS_COMING, x, y, null);
    }

}
