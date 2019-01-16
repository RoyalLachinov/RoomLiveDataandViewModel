package com.ca.roomlivedataandviewmodel.model;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.TypeConverters;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

import java.util.ArrayList;
import java.util.List;

@Dao
@TypeConverters(DateConverter.class)
public interface BorrowModelDao {

    @Query("Select * From BorrowModel")
    LiveData<List<BorrowModel>> getAllBorrowedItemsArrayListLiveData();

    @Query("Select * From BorrowModel Where id = :id")
    BorrowModel getItemById(String id);

    @Insert(onConflict = REPLACE)
    public void addBorrow(BorrowModel borrowModel);

    @Delete
    public void deleteBorrow(BorrowModel borrowModel);
}
