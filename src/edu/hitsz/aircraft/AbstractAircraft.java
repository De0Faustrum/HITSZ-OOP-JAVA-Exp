package edu.hitsz.aircraft;
import edu.hitsz.application.*;
import edu.hitsz.factory.SupplyFactory.*;
import edu.hitsz.bullet.*;
import edu.hitsz.basic.*;
import edu.hitsz.strategy.*;
import java.util.List;

/**
 * Superclass of all aircraft
 * @author hitsz
 * */

public abstract class AbstractAircraft extends AbstractFlyingObject {

    protected int maxHp;

    protected int hp;

    protected int shootNum;

    protected int direction;

    protected int shootDamage;

    protected SupplyFactory supplyFactory;

    protected TrajectoryStrategy trajectoryStrategy;

    public AbstractAircraft(int locationX, int locationY, int speedX, int speedY, int hp, TrajectoryStrategy trajectoryStrategy) {
        super(locationX, locationY, speedX, speedY);
        this.hp = hp;
        this.maxHp = hp;
        this.trajectoryStrategy = trajectoryStrategy;
    }

    public void decreaseHp(int decrease){
        hp -= decrease;
        if(hp <= 0){
            hp=0;
            vanish();
        }
    }

    public void increaseHp(int increaseValue){
        if (this.hp + increaseValue > maxHp){
            this.hp = maxHp;
        }
        else {
            this.hp += increaseValue;
        }
    }

    public int getShootNum(){
        return this.shootNum;
    }

    public int getDirection(){
        return this.direction;
    }

    public int getShootDamage(){
        return this.shootDamage;
    }

    public int getHp() {
        return hp;
    }

    public List<BaseBullet> shoot(){
        return trajectoryStrategy.shootStrategy(this);
    }

    @Override
    public void forward(){
        super.forward();
        if(this.getLocationX() < Main.WINDOW_WIDTH * 0.1 || this.getLocationX() > Main.WINDOW_WIDTH * 0.9){
            this.speedX = -1 * this.speedX;
        }
    }

}


