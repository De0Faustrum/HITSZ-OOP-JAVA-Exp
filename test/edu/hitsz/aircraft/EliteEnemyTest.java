package edu.hitsz.aircraft;

import java.util.*;

import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.factory.AircraftFactory.AircraftFactory;
import edu.hitsz.factory.AircraftFactory.EliteFactory;
import edu.hitsz.supply.AbstractSupply;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EliteEnemyTest {

    private AbstractAircraft testedEnemy;

    @BeforeEach
    void setUp(){
        AircraftFactory factory = new EliteFactory();       //Create new enemy factory
        testedEnemy = factory.createAircraft();             //Create an instance of elite enemy
    }

    @AfterEach
    void tearOut(){
        testedEnemy = null;                                 //destruct the tested object
    }

    @AfterAll
    static void endTest(){
        System.out.println("This is all for @Test of class EliteEnemy");
    }

    @Test
    void decreaseHp() {
        testedEnemy.decreaseHp(20);                         //Default hp is 60, so after this sentence hp should be 40
        assertEquals(40, testedEnemy.getHp());              //Test Sentence
        System.out.println("@decreaseHp():"+testedEnemy.getHp());
    }

    @Test
    void shoot() {
        List<BaseBullet> bulletsTest = testedEnemy.shoot();  //Create a new bulletList and call shoot function
        assertNotEquals(0, bulletsTest.size());              //After "Shoot", the linked list shouldn't be empty
        System.out.println("@shoot():非空");
    }


}