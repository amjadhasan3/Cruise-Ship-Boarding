package com.cruise_ship_task2;

public class Passenger {
    //this class will get firstname, surname, no of guests, and total expenses for each cabin
    public int passengerCount;
    public String firstName;
    public String surName;
    public double totalExpense;


    public Passenger(int passengerCount,  String firstName, String surName, double totalExpense){
        this.passengerCount = passengerCount;
        this.firstName = firstName;
        this.surName = surName;
        this.totalExpense = totalExpense;
    }


}
