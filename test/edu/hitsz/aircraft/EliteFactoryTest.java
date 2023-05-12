package edu.hitsz.aircraft;

import edu.hitsz.factory.AircraftFactory.AircraftFactory;
import edu.hitsz.factory.AircraftFactory.EliteFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EliteFactoryTest {

    AircraftFactory factory;

    @BeforeEach
    void setUp() {
        factory = new EliteFactory();
    }                    //Create a new factory instance

    @AfterEach
    void tearDown() {
        factory = null;
    }                               //Destruct tested object

    @AfterAll
    static void endTest(){
        System.out.println("This is all for @Test of class EliteFactory");
    }

    @Test
    void createAircraft() {
        AbstractAircraft eliteEnemy = factory.createAircraft();       //Call factory method to create aircraft
        assertNotNull(eliteEnemy);                                    //If factory method is effective, the instance shouldn't be null
        assertEquals(3,eliteEnemy.getSpeedY());
        assertEquals(60,eliteEnemy.getHp());
        System.out.println("@createAircraft():非null");
        System.out.println("@createAircraft():"+eliteEnemy.getSpeedY());
        System.out.println("@createAircraft():"+eliteEnemy.getHp());
    }
}