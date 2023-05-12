package edu.hitsz.supply.obs;
import edu.hitsz.basic.AbstractFlyingObject;
import java.util.*;

/**
 * Subject
 * @author Kosmischer
 * */

public class ObservedSubject {

    protected List<AbstractFlyingObject> observers = new ArrayList<>();

    public void attach(AbstractFlyingObject observer){
        this.observers.add(observer);
    }

    public void detach(){
        observers.clear();
    }

    public void notifyObserver(){
        for(AbstractFlyingObject observer: this.observers){
            observer.response();
        }
        this.detach();
    }
}
