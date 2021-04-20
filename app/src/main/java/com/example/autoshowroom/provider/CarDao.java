package com.example.autoshowroom.provider;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CarDao {

    @Query("select * from car")
    LiveData<List<Car>> getAllCars();

    @Insert
    void addCar(Car car);


    @Query("delete FROM car")
    void deleteAllCar();


}
