package edu.hitsz.strategy;
import edu.hitsz.aircraft.*;
import edu.hitsz.bullet.*;
import java.util.*;

/**
 * Concrete strategy class of direct shoot
 * @author Kosmischer
 * */

public class DirectShootStrategy implements TrajectoryStrategy{
    @Override
    public List<BaseBullet> shootStrategy(AbstractAircraft subject){
        List<BaseBullet> res = new LinkedList<>();
        int x = subject.getLocationX();
        int y = subject.getLocationY() + subject.getDirection()*2;
        //Judge subject type
        if(subject instanceof AbstractEnemy) {
            EnemyBullet bullet;
            for (int i = 0; i < subject.getShootNum(); i++) {
                bullet = new EnemyBullet(x + (i * 2 - subject.getShootNum() + 1) * 10, y, 0, 10, subject.getShootDamage());
                res.add(bullet);
            }
        }
        else if(subject instanceof HeroAircraft){
            HeroBullet bullet;
            for (int i = 0; i < subject.getShootNum(); i++) {
                bullet = new HeroBullet(x + (i * 2 - subject.getShootNum() + 1) * 10, y, 0, -10, subject.getShootDamage());
                res.add(bullet);
            }
        }
        return res;
    }
}
