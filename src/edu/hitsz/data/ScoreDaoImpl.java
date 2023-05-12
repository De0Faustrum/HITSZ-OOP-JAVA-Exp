package edu.hitsz.data;
import java.io.*;
import java.util.*;

/**
 * Concrete DAO class
 * @author Kosmischer
 * */

public class ScoreDaoImpl implements ScoreDao{

    private List<ScoreRecord> records = new ArrayList<>();

    @Override
    public void findRecordById(int id){
        for(ScoreRecord record : records){
            if(record.getId() == id){
                System.out.println("Record Info: Id: " + record.getId() + ", player: "
                        + record.getPlayer() + ", score: " + record.getScore());
                return;
            }
        }
        System.out.println("Cannot find record");
    }

    @Override
    public List<ScoreRecord> getAllScore(){
        return records;
    }

    @Override
    public void putAllRecords(){
        System.out.println("Ranking List:");
        System.out.println("**********************************************************");
        for(ScoreRecord record : records){
            System.out.println("Record Info: Id: " + record.getId() + ", player: "
                    + record.getPlayer() + ", score: " + record.getScore());
        }
        System.out.println("**********************************************************");
    }

    @Override
    public boolean insertScoreRecord(ScoreRecord newRecord){
        records.add(newRecord);
        resortArrayList();
        return true;
    }

    @Override
    public boolean deleteScoreRecord(int id){
        for(ScoreRecord record : records){
            if(record.getId() == id){
                records.remove(record);
                return true;
            }
        }
        System.out.println("Id " + id + "Not Valid");
        return false;
    }

    /**
     * Method to write record into file
     * */
    public void writeObjectToFile(){
        File file =new File("src/data/record.dat");
        FileOutputStream out;
        try {
            out = new FileOutputStream(file);
            ObjectOutputStream objOut=new ObjectOutputStream(out);
            objOut.writeObject(this.records);
            objOut.flush();
            objOut.close();
        }
        catch(IOException e){
            System.out.println("write object failed");
            e.printStackTrace();
        }
    }


    /**
     * Method to read record from file
     * */
    public void readObjectFromFile(){
        ScoreRecord temp = null;
        File file =new File("src/data/record.dat");
        FileInputStream in;
        try {
            in = new FileInputStream(file);
            ObjectInputStream objIn=new ObjectInputStream(in);
            this.records=(List<ScoreRecord>)objIn.readObject();
            objIn.close();
        }
        catch (IOException e) {
            System.out.println("read object failed");
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sort all the records in descending order
     * */
    public void resortArrayList(){
        for(int i=0; i<records.size()-1; i++){
            for(int j=i+1; j<records.size(); j++){
                if(records.get(i).getScore() < records.get(j).getScore()){
                    Collections.swap(records, i, j);
                }
            }
        }
    }

    @Override
    public int getRecordSize(){
        return records.size();
    }

    public int getNextId(){
        int maxId = 0;
        for(ScoreRecord record : records){
            if(maxId < record.getId()){
                maxId = record.getId();
            }
        }
        return maxId + 1;
    }
}
