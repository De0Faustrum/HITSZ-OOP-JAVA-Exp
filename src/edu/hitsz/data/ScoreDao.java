package edu.hitsz.data;
import java.util.List;

/**
 * DAO interface
 * @author Kosmischer
 * */

public interface ScoreDao {

    /**
     * Search a record by its id, output record info if found
     * @param id The record id you want to search
     */
    public abstract void findRecordById(int id);

    /**
     * Get all stored records
     * @return List of records
     * */
    public abstract List<ScoreRecord> getAllScore();

    /**
     * Output all the records
     * */
    public abstract void putAllRecords();

    /**
     * Insert a record
     * @param newRecord the record to be operated
     * @return Return true if Success, else return false
     * */
    public abstract boolean insertScoreRecord(ScoreRecord newRecord);

    /**
     * Delete a record
     * @param id The id of the record to be deleted
     * @return Return true if Success, else return false
     * */
    public abstract boolean deleteScoreRecord(int id);

    /**
     * Size of Array List
     * @return Return the number of existing records
     * */
    public abstract int getRecordSize();
}
