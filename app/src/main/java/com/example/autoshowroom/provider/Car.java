package com.example.autoshowroom.provider;

import android.provider.BaseColumns;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "car")
public class Car {

    public static final String TABLE_NAME = "car";
    public static final String COLUMN_ID = BaseColumns._ID;


    @NonNull
    @ColumnInfo(name = "Model")
    private final String carModel;
    @ColumnInfo(name = "Maker")
    @NonNull
    private final String carMaker;
    @ColumnInfo(name = "Year")
    @NonNull
    private final int year;
    @ColumnInfo(name = "Colour")
    private final String colour;
    @ColumnInfo(name = "Seats")
    private final int seats;
    @ColumnInfo(name = "Price")
    private final int price;
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "carId")
    private int carId;

    public Car(String carMaker, String carModel, int year, String colour, int seats, int price) {
        this.carMaker = carMaker;
        this.carModel = carModel;
        this.year = year;
        this.colour = colour;
        this.seats = seats;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public int getSeats() {
        return seats;
    }

    public int getYear() {
        return year;
    }

    @NonNull
    public String getCarMaker() {
        return carMaker;
    }

    @NonNull
    public String getCarModel() {
        return carModel;
    }

    public String getColour() {
        return colour;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(@NonNull int carId) {
        this.carId = carId;
    }
}
