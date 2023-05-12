package edu.hitsz.bullet;

import edu.hitsz.application.Game;

/**
 * Bullet of enemy
 * @author hitsz
 * */

public class EnemyBullet extends BaseBullet {

    public EnemyBullet(int locationX, int locationY, int speedX, int speedY, int power) {
        super(locationX, locationY, speedX, speedY, power);
        Game.GLOBAL_OBSERVED_SUBJECTS.attach(this);
    }

}
