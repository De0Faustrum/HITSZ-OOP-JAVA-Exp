package edu.hitsz.bullet;

import edu.hitsz.aircraft.HeroAircraft;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class EnemyBulletTest {

    BaseBullet bullet;
    @BeforeEach
    void setUp() {
        bullet = new EnemyBullet(200,200,1,2,20);                       //Create an instance
    }

    @AfterEach
    void tearDown() {
        bullet = null;                                                  //Destruct the tested object
    }

    @AfterAll
    static void endTest(){
        System.out.println("This is all for @Test of class EnemyBullet");
    }

    @Test
    void getPower() {
        assertEquals(20, bullet.getPower());                            //Default damage power is 20
        System.out.println("@getPower():"+bullet.getPower());
    }

    @Test
    void crash() {
        HeroAircraft hero = HeroAircraft.getHeroAircraftInstance();     //Create a hero instance
        hero.setLocation(200,200);                                      //Set hero location
        bullet.setLocation(200,200);                                    //Set supply location
        assertTrue(bullet.crash(hero));                                 //If Crashed, return true
        System.out.println("@crash():"+bullet.crash(hero));
    }

    @Test
    void getSpeedY() {
        assertEquals(2, bullet.getSpeedY());                            //Default speed Y is 2
        System.out.println("@getSpeedY():"+bullet.getSpeedY());
    }
}