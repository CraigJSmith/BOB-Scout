package com.example.craig.bobscout;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by Craig on 2/20/2018.
 */

@Dao
public interface ActionDao {

    @Query("SELECT * FROM 'action'")
    List<ActionEntity> getAll();

    @Insert
    void insertAll(ActionEntity... actions);

    @Delete
    void delete(ActionEntity action);
}