package edu.hitsz.strategy;
import edu.hitsz.aircraft.*;
import edu.hitsz.bullet.*;
import java.util.*;

/**
 * Concrete strategy class of scattered shoot
 * @author Kosmischer
 * */

public class ScatteredShootStrategy implements TrajectoryStrategy{
    @Override
    public List<BaseBullet> shootStrategy(AbstractAircraft subject){
        List<BaseBullet> res = new LinkedList<>();
        int x = subject.getLocationX();
        int y = subject.getLocationY() + subject.getDirection()*2;
        if(subject instanceof AbstractEnemy){
            EnemyBullet bullet;
            for(int i=0; i<subject.getShootNum(); i++){
                bullet = new EnemyBullet(x + (i*2 - subject.getShootNum() + 1)*10, y, 4*i-4, 15, subject.getShootDamage());
                res.add(bullet);
            }
        }
        if(subject instanceof HeroAircraft){
            HeroBullet bullet;
            for(int i=0; i<subject.getShootNum(); i++){
                bullet = new HeroBullet(x , y, i-(int)(0.5*(subject.getShootNum()-1)) , -10, subject.getShootDamage());
                res.add(bullet);
            }
        }
        return res;
    }
}


