package edu.hitsz.aircraft;

import java.util.*;

import edu.hitsz.bullet.BaseBullet;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

class HeroAircraftTest {

    private HeroAircraft heroAircraftInstance = null;

    @BeforeEach
    void setUp() {
        heroAircraftInstance = HeroAircraft.getHeroAircraftInstance();
    }   //Get Singleton instance

    @AfterEach
    void tearDown() {
        heroAircraftInstance = null;                                    //Destruct tested object
    }

    @AfterAll
    static void endTest(){
        System.out.println("This is all for @Test of class HeroAircraft");
    }

    @Test
    void getHeroAircraftInstance() {
        assertNotNull(heroAircraftInstance);                            //If get-instance is effective, the instance shouldn't be null
        if(heroAircraftInstance != null) {
            System.out.println("@getHeroAircraftInstance():非空");
        }
    }


    @Test
    void decreaseHp() {
        heroAircraftInstance.increaseHp(100000);                        //Set HP full (Limit is 100000)
        heroAircraftInstance.decreaseHp(20000);                         //Decrease 20000 HP
        assertEquals(80000,heroAircraftInstance.getHp());               //There should be 80000 HP left
        System.out.println("@decreaseHp():"+heroAircraftInstance.getHp());
    }

    @Test
    void increaseHp() {
        heroAircraftInstance.increaseHp(100000);                        //Set HP full (Limit is 100000)
        heroAircraftInstance.decreaseHp(60000);                         //Decrease 60000 HP, HP should be 40000
        heroAircraftInstance.increaseHp(2000);                          //Increase 2000 HP
        assertEquals(42000,heroAircraftInstance.getHp());               //The final HP should be 40000 + 2000 = 42000
        System.out.println("@increaseHp():"+heroAircraftInstance.getHp());
    }

    @Test
    void getLocationX() {
        heroAircraftInstance.setLocation(160,90);                       //Set Location
        assertEquals(160, heroAircraftInstance.getLocationX());         //Get X Coordinate
        System.out.println("@getLocationX():"+heroAircraftInstance.getLocationX());
    }

    @Test
    void getLocationY() {
        heroAircraftInstance.setLocation(160,90);                       //Set Location
        assertEquals(90, heroAircraftInstance.getLocationY());          //Get Y Coordinate
        System.out.println("@getLocationY():"+heroAircraftInstance.getLocationY());
    }

    @Test
    void shoot() {
        List<BaseBullet> bulletsTest = new LinkedList<>();
        bulletsTest.addAll(heroAircraftInstance.shoot());    //Create bullet list and call shoot function
        assertNotEquals(0, bulletsTest.size());                         //If effective, the linked list shouldn't be empty
        System.out.println("@shoot():非空");
    }

    @Test
    void bulletReinforce() {
        int temp;
        assertEquals(2, temp = heroAircraftInstance.bulletReinforce());        //Default shoot num is 1, so it should be 2 after
        System.out.println("@bulletReinforce():"+temp);
    }

}