package com.coolbanter.examprep.data;

import androidx.annotation.WorkerThread;
import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

/**
 * Room data access object.
 */
@Dao
public interface SmileyDao {

    /**
     * Returns all data in table for Paging.
     *
     *
     */

//    @WorkerThread
    @Query("SELECT * FROM smiley ORDER BY name ASC")
    DataSource.Factory<Integer, Smiley> getAll();


    /**
     * Returns LiveData of random Smileys.
     *
     * @param limit number of return
     */

    @WorkerThread
    @Query("SELECT * FROM smiley ORDER BY random() =:limit LIMIT 1")
    LiveData<List<Smiley>> getRandom(int limit);

    /**
     * Returns a random Smiley.
     */
    @WorkerThread
    @Query("SELECT * FROM smiley ORDER BY random()")
    Smiley getSmiley();

    @WorkerThread
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Smiley... smiley);

    @WorkerThread
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Smiley smiley);

    @WorkerThread
    @Delete
    void delete(Smiley smiley);

}
