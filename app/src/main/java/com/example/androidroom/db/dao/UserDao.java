package com.example.androidroom.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.androidroom.model.User;

import java.util.List;
import java.util.Observable;

import io.reactivex.Flowable;

@Dao
public interface UserDao {

    @Insert
    void insert(User user);

    @Update
    void update(User user);

    @Delete
    void delete(User user);

    @Query("DELETE FROM User")
    void deleteAll();

    @Query("SELECT * FROM User ORDER BY id DESC")
    Flowable<List<User>> getAll();






}
