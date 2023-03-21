package com.cruise_ship_task2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CruiseShip {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String SelectedOption;

        //Initializing both menus to true. Used for loop
        Boolean Loop1=true;
        Boolean Loop2=true;

        //Running the method to initialise each cabin array into 'e'
        initialise();

        System.out.println("Welcome.\n");

        while (Loop1) {
            while (Loop2) {
                System.out.println("\nPlease select an option.\n\n");

                System.out.println("A: Book A New Cabin.");
                System.out.println("V: View All Cabins.");
                System.out.println("E: Display Empty Cabins.");
                System.out.println("D: Delete Customer From Cabin.");
                System.out.println("F: Find Cabin From Customer Name.");
                System.out.println("S: Store Program Data Into File.");
                System.out.println("L: Load Program Data From File.");
                System.out.println("O: View Passengers Ordered Alphabetically By Name.");
                System.out.println("T: Print Expenses.");

                //getting the input option the user wants
                SelectedOption = input.next().toUpperCase();
                System.out.println("\nYour selected option is: " + SelectedOption + "\n");


                //switch case has been to run the method of the selected option
                switch (SelectedOption) {
                    case "A":
                        Book();
                        break;
                    case "V":
                        View();
                        break;
                    case "E":
                        DisplayEmpty();
                        break;
                    case "D":
                        DeleteCustomer();
                        break;
                    case "F":
                        FindCustomer();
                        break;
                    case "S":
                        StoringProgram();
                        break;
                    case "L":
                        LoadingProgram();
                        break;
                    case "O":
                        OrderedAlphabetically();
                        break;
                    case "T":
                        PrintExpenses();
                        break;
                    default:
                        System.out.println("Invalid Output.");
                }

                System.out.println("\nWould You Like To Select Another Option?\n'1' For Yes\n'2' For No");
                if (input.nextInt() == 1) {
                    //if 1 is pressed the option loop will repeat
                    Loop2 = true;
                } else {
                    //if 2 is pressed the option loop will stop and continue to program loop
                    Loop2 = false;
                }
            }

            Loop2=true;
            System.out.println("\nWould You Like To Continue With Program?\n'1' for Yes\n'2' for No");
            if (input.nextInt()==1){
                //if 1 is pressed the program loop will repeat
                Loop1=true;
            }
            else {
                //the program will stop at this point
                System.out.println("Thank You For Using Our Service!");
                break;
            }
        }
    }


    //this array is used to store values for 12 cabins
    public static Cabin [] cabin = new Cabin[12];
    //this array will store the value for the no of guests for each cabin
    public static Passenger [] numberOfGuests= new Passenger[12];
    //this array will store the value for the expenses for each cabin
    public static Passenger [] guestsExpenses = new Passenger[12];

    //this 2d array is used to get the guests firstname and surname of the given number of guests (maximum 3)
    public static Passenger [] [] guestsFirstname = new Passenger[12][3];
    public static Passenger [] [] guestsSurname = new Passenger[12][3];



    private static void initialise (){
        //this method will make all the cabins and passenger details empty and also total expenses to 0
        for (int x = 0; x < 12; x++) {
            cabin[x] = new Cabin("e", new Passenger[][]{new Passenger[]{new Passenger(0, "e", "e", 0)}});
        }
        for (int i = 0; i < 12; i++) {
            numberOfGuests[i] = new Passenger(0, "e", "e", 0);
            guestsExpenses[i] = new Passenger(0, "e", "e", 0);
            for (int y = 0; y < 3; y++) {
                guestsFirstname[i][y] = new Passenger(0, "e", "e", 0);
                guestsSurname[i][y] = new Passenger(0, "e", "e", 0);
            }
        }
    }


    private static void Book (){
        Scanner input= new Scanner(System.in);
        //setting the string variable to null every time the option 'A' is selected
        String firstNameInput = null;
        String surNameInput = null;
        int count=0;

        for (int x = 0; x < 12; x++) {
            if (!(cabin[x].cruiseCabin.equals("e"))) {
                count ++;
            }
        }

        if (count == 12) {
            System.out.println("We are sorry! All the cabins are booked.");
        }else{

            System.out.println("Please Enter The Cabin Number You Wish To Book (1-12) : ");
            int CabinNumber = input.nextInt()-1;


            if (cabin[CabinNumber].cruiseCabin.equals("e")) { //checks if the cabin is already books
                System.out.println("How Many Guests Are Travelling (1-3) :");
                //exception used if the user enters wrong value
                try{
                    int guestTravelling= input.nextInt();

                    if ((guestTravelling <= 3) && (guestTravelling >= 1)) { //checks if the number of guests are 1, 2 or 3
                        numberOfGuests[CabinNumber].passengerCount = guestTravelling;

                        System.out.println("Please Enter The Name Of The Paying Guest : ");
                        String payingGuest= input.next().toUpperCase();

                        for (int x = 0; x < guestTravelling; x++) {
                            System.out.println("Enter firstname of the Guest " + (x + 1) + " :");
                            firstNameInput = input.next().toUpperCase();
                            guestsFirstname[CabinNumber][x].firstName = firstNameInput;

                            System.out.println("Enter surname of the Guest " + (x + 1) + " :");
                            surNameInput = input.next().toUpperCase();
                            guestsSurname[CabinNumber][x].surName = surNameInput;
                        }

                        double amount = guestTravelling*200;
                        guestsExpenses[CabinNumber].totalExpense = amount;
                        cabin[CabinNumber] = new Cabin(payingGuest, new Passenger[][]{new Passenger[]{new Passenger(guestTravelling, firstNameInput, surNameInput, amount)}});
                        System.out.println("The Cabin Number (" + (CabinNumber + 1) + ") Has Been Booked Under The Name of " + payingGuest);
                    }
                } catch (Exception e) {
                    System.out.println("Invalid Number of Guests.");
                }
            }else {
                System.out.println("The Cabin Number "+ (CabinNumber+1)+" is already booked.");
            }

        }



    }


    public static void View (){
        //displays all the data of cabins
        for (int x = 0; x < 12 ; x++) {
            if (cabin[x].cruiseCabin.equals("e")) {
                System.out.println("\nCabin " + (x+1) + " is empty");
            } else {
                System.out.println("\nCabin " + (x + 1) + " occupied by " + cabin[x].cruiseCabin);
                System.out.println("Number of Guests Travelling: " + numberOfGuests[x].passengerCount);
                for (int i = 0; i < numberOfGuests[x].passengerCount; i++) {
                    System.out.println("Guest " + (i + 1) + " : " + guestsSurname[x][i].surName + " " + guestsFirstname[x][i].firstName);
                }
            }
        }
    }


    private static void DisplayEmpty (){
        System.out.println("Displaying Empty Cabins.");
        //loops through the 'Cabin' array to check which cabin equals to 'e'
        for (int x = 0; x < 12; x++) {
            if (cabin[x].cruiseCabin.equals("e")){
                System.out.println("Cabin " + (x + 1) + " Is Empty");
            }
        }
    }

    private static void DeleteCustomer (){
        Scanner input = new Scanner(System.in);
        System.out.println("Please Enter The Cabin Number You Wish To Delete (1-12) :");
        int CabinNumber = input.nextInt();
        CabinNumber -= 1;

        //exception has been used if the user enters invalid cabin number
        try {
           //initializing the given cabin number again to delete.
            cabin[CabinNumber] = new Cabin("e", new Passenger[][]{new Passenger[]{new Passenger(0, "e", "e", 0)}});
            numberOfGuests[CabinNumber] = new Passenger(0, "e", "e", 0);
            guestsExpenses[CabinNumber] = new Passenger(0, "e", "e", 0);
            for (int y = 0; y < 3; y++) {
                guestsFirstname[CabinNumber][y] = new Passenger(0, "e", "e", 0);
                guestsSurname[CabinNumber][y] = new Passenger(0, "e", "e", 0);
            }
            System.out.println("Cabin Number " + (CabinNumber+1) + " has been deleted.");
        } catch (Exception e) {
            System.out.println("Invalid Cabin Number.");
        }
    }

    private static void FindCustomer (){
        String CustomerName;
        Scanner input = new Scanner(System.in);
        boolean nameFound = false; //boolean variable initialized to false at the start

        System.out.println("Please Enter The Name Of The Guest :");
        CustomerName = input.next().toUpperCase();

        //loops through the 'Cabin' array to check the cabin number of the given customer name
        //this loop goes through first name, last name and the name of the paying guest to check
        for (int x = 0; x < 12; x++) {
            if (cabin[x].cruiseCabin.equals(CustomerName)) {
                System.out.println(CustomerName + " Has Been Found In Cabin Number : " + (x + 1));
                nameFound = true;
            }

                for (int y = 0; y < 3; y++) {
                    if (guestsFirstname[x][y].firstName.equals(CustomerName)) {
                        System.out.println(CustomerName + " Has Been Found In Cabin Number : " + (x + 1));
                        nameFound = true;
                    }
                }

                for (int y = 0; y < 3; y++) {
                    if (guestsSurname[x][y].surName.equals(CustomerName)) {
                        System.out.println(CustomerName + " Has Been Found In Cabin Number : " + (x + 1));
                        nameFound = true;
                    }
                }
            }

        if (nameFound==false) {
            System.out.println("Guest Not Found."); //if the input name has not been found this will be displayed
        }
    }


    private static void StoringProgram (){
        try{
            //creates a file name called 'hotelData'
            FileWriter myWriter = new FileWriter("C:\\Users\\Admin\\Documents\\w1869931_class_version\\Task_2\\hotelDataTask2.txt");
            //myWriter.write is used to write the given data into the file
            myWriter.write("Cruise Ship Customers Information Data.\n\n");

            for (int x = 0; x < 12 ; x++) {
                if (cabin[x].cruiseCabin.equals("e")) {
                    myWriter.write("\nCabin " + (x+1) + " is empty\n");
                } else {
                    myWriter.write("\nCabin " + (x + 1) + " occupied by " + cabin[x].cruiseCabin+"\n");
                    myWriter.write("Number of Guests Travelling: " + numberOfGuests[x].passengerCount+"\n");
                    for (int i = 0; i < numberOfGuests[x].passengerCount; i++) {
                        myWriter.write("Guest " + (i + 1) + " : " + guestsSurname[x][i].surName + " " + guestsFirstname[x][i].firstName+"\n");
                    }
                }
                myWriter.write("Expense: " + "$"+ guestsExpenses[x].totalExpense+"\n");
            }
            myWriter.close();//ends the writing of the file
        }catch (IOException e){
            System.out.println("Error");
        }
        System.out.println("File Saved.");
    }

    private static void LoadingProgram () {
        try {
            //opens the written file into the java program
            File CabinFile = new File("hotelDataTask2.txt");
            Scanner CabinFileReader = new Scanner(CabinFile);
            //reads each line from the file
            while (CabinFileReader.hasNextLine()){
                String data = CabinFileReader.nextLine();
                System.out.println(data);
            }
            CabinFileReader.close();//stops the reading
        } catch (IOException e){
            System.out.println("File Is Not Created.");//displays if file is not created
        }
    }

    private static void OrderedAlphabetically () {
        String[] alphabet = new String[12];


        //adding the Cabin elements to another array called "alphabet"
        for (int x = 0; x < 12; x++) {
            alphabet[x] = cabin[x].cruiseCabin;
        }

        //bubble sorting has been used to arrange in alphabetical order
        for (int i = 0; i < alphabet.length; i++) {
            for (int j = i + 1; j < alphabet.length; j++) {
                if (alphabet[i].compareTo(alphabet[j]) > 0) {
                    String temp = alphabet[i];
                    alphabet[i] = alphabet[j];
                    alphabet[j] = temp;
                }
            }
        }

        System.out.println("\nDisplaying Names in Alphabetical Order.\n");
        for (int i = 0; i < alphabet.length; i++) {
            if(alphabet[i].equals("e")){
                System.out.print("");
            }else{
                System.out.println(alphabet[i]);//displays the name of the customer alphabetically
            }
        }

        //using a for loop to see how many elements are empty to create another array
        int count = 0;
        for (int x = 0; x < 12; x++) {
            for (int i = 0; i < 3; i++) {
                if (!(guestsFirstname[x][i].firstName.equals("e"))) {
                    count += 1;
                }
            }
        }

        int y = 0;
        String[] guestAlphabet = new String[count];
        for (int x = 0; x < 12; x++) {
            for (int i = 0; i < 3; i++) {
                if (!(guestsFirstname[x][i].firstName.equals("e"))) {
                    //adding the First name elements to another array called "guestAlphabet"
                    guestAlphabet[y] = guestsSurname[x][i].surName+" "+guestsFirstname[x][i].firstName;
                    y += 1;
                }
            }
        }


        //bubble sorting has been used to arrange in alphabetical order
        for (int i = 0; i < guestAlphabet.length; i++) {
            for (int j = i + 1; j < guestAlphabet.length; j++) {
                if (guestAlphabet[i].compareTo(guestAlphabet[j]) > 0) {
                    String temp = guestAlphabet[i];
                    guestAlphabet[i] = guestAlphabet[j];
                    guestAlphabet[j] = temp;
                }
            }
        }

        System.out.println("\nDisplaying Guest Names in Alphabetical Order.\n");
        for (int i = 0; i < guestAlphabet.length; i++) {
            System.out.println(guestAlphabet[i]);//displays the name of the customer alphabetically
        }
    }

    private static void PrintExpenses (){
        System.out.println("\nThe price per person to travel is $200.\n");

        double totalExpenses = 0;
        for (int x = 0; x < 12; x++) {
            totalExpenses = totalExpenses + guestsExpenses[x].totalExpense;
        }
        System.out.println("\nThe total expenses for all 12 cabins : " + "$" + totalExpenses);


            //displaying the price of all cabins
            for (int x = 0; x < 12; x++) {
                System.out.println("The total expense for cabin "+ (x+1) + " is : " + "$" + guestsExpenses[x].totalExpense);
            }
        }
}
