package edu.hitsz.bullet;
import edu.hitsz.application.Main;
import edu.hitsz.basic.AbstractFlyingObject;

/**
 * Base bullet type
 * @author hitsz
 * */

public class BaseBullet extends AbstractFlyingObject {

    private int power = 10;

    public BaseBullet(int locationX, int locationY, int speedX, int speedY, int power){
        super(locationX, locationY, speedX, speedY);
        this.power = power;

    }

    public int getPower() {
        return power;
    }

    @Override
    public void forward() {
        super.forward();
        if (locationX <= 0 || locationX >= Main.WINDOW_WIDTH) {
            vanish();
        }
        if (locationY <= 0){
            vanish();
        }
    }

}
