package edu.hitsz.aircraft;
import edu.hitsz.application.Game;
import edu.hitsz.application.ThreadPackage.MusicProxyThread;
import edu.hitsz.factory.SupplyFactory.*;
import edu.hitsz.supply.*;
import edu.hitsz.strategy.TrajectoryStrategy;
import java.util.List;

/**
 * Boss
 * @author Kosmischer
 * */

public class BossEnemy extends AbstractEnemy{

    public BossEnemy(int locationX, int locationY, int speedX, int speedY, int hp, TrajectoryStrategy trajectoryStrategy){
        super(locationX, locationY, speedX, speedY, hp, trajectoryStrategy);
        this.shootNum = 3;
        this.direction = 1;
        this.shootDamage = 20;
        MusicProxyThread.musicEffect(3);
        Game.bgmType = true;
        MusicProxyThread.bgmThread.terminate();
    }

    @Override
    public void supplySummon(List<AbstractSupply> propSupplies) {
        final SupplyFactory healthSupplyFactory = new HealthSupplyFactory();
        final SupplyFactory fireSupplyFactory = new FireSupplyFactory();
        final SupplyFactory bombSupplyFactory = new BombSupplyFactory();
        SupplyFactory supplyFactory;
        double randomizer;
        for(int i=0 ; i<3; i++){
            randomizer = Math.random();
            if(randomizer < 0.33){
                supplyFactory = healthSupplyFactory;
                propSupplies.add(supplyFactory.createSupply(this.getLocationX() + 25*i -25,this.getLocationY()));
            }
            if(randomizer >= 0.33 && randomizer < 0.67){
                supplyFactory = fireSupplyFactory;
                propSupplies.add(supplyFactory.createSupply(this.getLocationX() + 25*i -25,this.getLocationY()));
            }
            if(randomizer >= 0.67){
                supplyFactory = bombSupplyFactory;
                propSupplies.add(supplyFactory.createSupply(this.getLocationX() + 25*i -25,this.getLocationY()));
            }
        }
    }

    @Override
    public void vanish(){
        super.vanish();
        Game.bgmType = false;
        MusicProxyThread.bgmThread.terminate();
    }

    @Override
    public void response(){
        this.hp = this.hp - 300;
    }

}

