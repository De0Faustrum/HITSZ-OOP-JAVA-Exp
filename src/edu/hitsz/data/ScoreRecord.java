package edu.hitsz.data;
import java.util.Date;
import java.io.Serializable;

/**
 * Record class
 * @author Kosmischer
 * */

public class ScoreRecord implements Serializable {
    private final int id;
    private final String player;
    private final int score;
    private final Date date;

    public ScoreRecord(int id, String player, int score, Date date){
        this.id = id;
        this.player = player;
        this.score = score;
        this.date = date;
    }

    public int getId(){
        return this.id;
    }

    public String getPlayer(){
        return this.player;
    }

    public int getScore(){
        return this.score;
    }

    public Date getDate(){
        return date;
    }
}
