package edu.hitsz.factory.AircraftFactory;
import edu.hitsz.aircraft.AbstractEnemy;

/**
 * Abstract factory interface of enemy aircraft
 * @author Kosmischer
 * */

public interface AircraftFactory {

    /**
     * abstract factory method
     * @return return product enemy
     * */
    public abstract AbstractEnemy createAircraft();
}
