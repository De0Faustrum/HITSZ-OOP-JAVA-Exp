package edu.hitsz.factory.SupplyFactory;
import edu.hitsz.supply.*;

/**
 * Abstract factory interface of supplies
 * @author Kosmischer
 * */

public interface SupplyFactory {

    /**
     * Abstract factory method to create prop supplies
     * @param locationX X coordinate
     * @param locationY Y coordinate
     * @return An instance of a concrete supply class
     * */
     public abstract AbstractSupply createSupply(int locationX, int locationY);
}
