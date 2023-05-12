package edu.hitsz.items;

import java.util.*;

import edu.hitsz.aircraft.AbstractEnemy;
import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.factory.SupplyFactory.HealthSupplyFactory;
import edu.hitsz.factory.SupplyFactory.SupplyFactory;
import edu.hitsz.supply.AbstractSupply;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class HealthSupplyTest {

    private AbstractSupply supply;
    private SupplyFactory factory;
    private final HeroAircraft hero = HeroAircraft.getHeroAircraftInstance();

    @BeforeEach
    void setUp() {
        factory = new HealthSupplyFactory();                            //Create an item factory
        supply = factory.createSupply(100,200);                         //Call factory methods to create an instance
    }

    @AfterEach
    void tearDown() {
        factory = null;                                                 //Destruct factory object
        supply = null;                                                  //Destruct tested object
    }

    @AfterAll
    static void endTest(){
        System.out.println("This is all for @Test of class HealthSupply");
    }

    @Test
    void crash() {
             //Create a hero
        hero.setLocation(200,200);                                      //Set Hero Location
        supply.setLocation(200,200);                                    //Set Supply Location
        assertTrue(supply.crash(hero));                                 //If crashed, return true
        if(supply.crash(hero)){
            System.out.println("true");
        }
    }

    @Test
    void supplyActive() {     //Create a hero
        List<AbstractEnemy> enemies = new LinkedList<>();            //Create a new enemy list
        hero.decreaseHp(10000);                                         //Decrease 10000, 90000 HP left
        supply.supplyActive(hero, enemies);                             //Supply Active, default increase 60 HP
        assertEquals(90060, hero.getHp());                              //Final HP should be 90000 + 60 = 90600 HP
        System.out.println(hero.getHp());
    }
}