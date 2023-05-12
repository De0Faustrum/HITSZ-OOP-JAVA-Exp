package edu.hitsz.strategy;
import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.bullet.*;
import java.util.*;

/**
 * Strategy Interface to control the trajectory of bullet shot
 * @author Kosmischer
 * */
public interface TrajectoryStrategy {

    /**
     * Abstract strategy method of interface
     * @param subject The aircraft who use this strategy
     * @return List of created bullet
     * */
    public abstract List<BaseBullet> shootStrategy(AbstractAircraft subject);
}
