package com.cruise_ship_task3;

public class Cabin {
    //this class will hold passenger details in object of arrays in 2d array
    public String cruiseCabin;
    public static Passenger[][] passenger = new Passenger[12][3];

    public Cabin(String cruiseCabin, Passenger [] [] passenger) {
        this.cruiseCabin = cruiseCabin;
        this.passenger = passenger;
    }
}
