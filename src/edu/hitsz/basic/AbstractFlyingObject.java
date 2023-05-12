package edu.hitsz.basic;
import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.application.*;
import edu.hitsz.supply.obs.AbstractObserver;

import java.awt.image.BufferedImage;

/**
 * Fundamental superclass of all aircraft, bullet and supply
 * @author hitsz
 * */

public abstract class AbstractFlyingObject implements AbstractObserver {

    protected int locationX;
    protected int locationY;
    protected int speedX;
    protected int speedY;
    protected BufferedImage image = null;
    protected int width = -1;
    protected int height = -1;
    protected boolean isValid = true;

    public AbstractFlyingObject(int locationX, int locationY, int speedX, int speedY) {
        this.locationX = locationX;
        this.locationY = locationY;
        this.speedX = speedX;
        this.speedY = speedY;
    }

    public void forward() {
        locationX += speedX;
        locationY += speedY;
        if (locationY >= Main.WINDOW_HEIGHT ) {
            vanish();
        }
    }

    public boolean crash(AbstractFlyingObject flyingObject) {

        int factor = this instanceof AbstractAircraft ? 2 : 1;
        int fFactor = flyingObject instanceof AbstractAircraft ? 2 : 1;
        int x = flyingObject.getLocationX();
        int y = flyingObject.getLocationY();
        int fWidth = flyingObject.getWidth();
        int fHeight = flyingObject.getHeight();
        return x + (fWidth+this.getWidth())/2 > locationX
                && x - (fWidth+this.getWidth())/2 < locationX
                && y + ( fHeight/fFactor+this.getHeight()/factor )/2 > locationY
                && y - ( fHeight/fFactor+this.getHeight()/factor )/2 < locationY;
    }

    public int getLocationX() {
        return locationX;
    }

    public int getLocationY() {
        return locationY;
    }

    public void setLocation(double locationX, double locationY){
        this.locationX = (int) locationX;
        this.locationY = (int) locationY;
    }

    public int getSpeedY() {
        return speedY;
    }

    public BufferedImage getImage() {
        if (image == null){
            image = ImageManager.get(this);
        }
        return image;
    }

    public int getWidth() {
        if (width == -1){
            // 若未设置，则查询图片宽度并设置
            width = ImageManager.get(this).getWidth();
        }
        return width;
    }

    public int getHeight() {
        if (height == -1){
            height = ImageManager.get(this).getHeight();
        }
        return height;
    }

    public boolean notValid() {
        return !this.isValid;
    }

    public void vanish() {
        isValid = false;
    }

    @Override
    public void response(){

        this.vanish();
    }
}

