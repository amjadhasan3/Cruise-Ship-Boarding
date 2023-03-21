package com.arrays;
import java.io.*;
import java.util.Scanner;

public class cruise_ship_task1 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String[] Cabin = new String[12]; //Array which will store the name of the customer booking
        String SelectedOption;
        int CabinNumber = 0;//Initializing cabin number to 0 at the start

        //Initializing both menus to true. Used for loop
        Boolean Loop1 = true;
        Boolean Loop2 = true;

        //Running the method to initialise each cabin array into 'e'
        initialise(Cabin);

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

                //getting the input option the user wants
                SelectedOption = input.next().toUpperCase();
                System.out.println("\nYour selected option is: " + SelectedOption + "\n");


                //switch case has been to run the method of the selected option
                switch (SelectedOption) {
                    case "A":
                        Book(Cabin, CabinNumber);
                        break;
                    case "V":
                        View(Cabin);
                        break;
                    case "E":
                        DisplayEmpty(Cabin);
                        break;
                    case "D":
                        DeleteCustomer(Cabin, CabinNumber);
                        break;
                    case "F":
                        FindCustomer(Cabin);
                        break;
                    case "S":
                        StoringProgram(Cabin);
                        break;
                    case "L":
                        LoadingProgram(Cabin);
                        break;
                    case "O":
                        OrderedAlphabetically(Cabin);
                        break;
                    default:
                        System.out.println("Invalid Output.");
                }

                System.out.println("Would You Like To Select Another Option?\n'1' For Yes\n'2' For No");
                if (input.nextInt() == 1) {
                    //if 1 is pressed the option loop will repeat
                    Loop2 = true;
                } else {
                    //if 2 is pressed the option loop will stop and continue to program loop
                    Loop2 = false;
                }
            }

            Loop2 = true;
            System.out.println("Would You Like To Continue With Program?\n'1' for Yes\n'2' for No");
            if (input.nextInt() == 1) {
                //if 1 is pressed the program loop will repeat
                Loop1 = true;
            } else {
                //the program will stop at this point
                System.out.println("Thank You For Using Our Service!");
                break;
            }
        }
    }


    private static void initialise(String Cabin[]) {
        //each element set to "e" initially.
        for (int x = 0; x < Cabin.length; x++) {
            Cabin[x] = "e";
        }
    }

    private static void Book(String Cabin[], int CabinNumber) {
        String CustomerName;
        Scanner input = new Scanner(System.in);


        System.out.println("Please Enter The Cabin Number You Wish To Book (1-12) : ");
        CabinNumber = input.nextInt();
        CabinNumber -= 1;

        //exception has been used if the user enters wrong value
        try {
            //checks if the room has been already booked by someone else.
            if (Cabin[CabinNumber].equals("e")) {
                System.out.println("Please Enter Your Name : ");
                CustomerName = input.next().toUpperCase();

                Cabin[CabinNumber] = CustomerName; //setting cabin number to the cabin name
                System.out.println("The Cabin Number (" + (CabinNumber + 1) + ") Has Been Booked Under The Name of " + CustomerName);
            } else {
                //displays if the given value is already booked
                System.out.println("The Cabin Number " + (CabinNumber + 1) + " is already booked.");
            }
        } catch (Exception e) {
            System.out.println("Invalid Cabin Number.");
        }
    }


    private static void View(String Cabin[]) {
        for (int x = 0; x < Cabin.length; x++) {
            if (Cabin[x].equals("e")) {
                System.out.println("Cabin " + (x + 1) + " Is Empty");
            } else {
                //"(x+1)" is put to add one to the index because the starting element is always 0
                System.out.println("Cabin " + (x + 1) + " occupied by " + Cabin[x]);
            }
        }
    }

    private static void DisplayEmpty(String Cabin[]) {
        System.out.println("Displaying Empty Cabins.");
        //loops through the 'Cabin' array to check which cabin equals to 'e'
        for (int x = 0; x < Cabin.length; x++) {
            if (Cabin[x].equals("e")) {
                System.out.println("Cabin " + (x + 1) + " Is Empty");
            }
        }
    }

    private static void DeleteCustomer(String Cabin[], int CabinNumber) {
        Scanner input = new Scanner(System.in);
        //exception has been used if the user enters wrong value
        try {
            System.out.println("Please Enter The Cabin Number You Wish To Delete (1-12) :");
            CabinNumber = input.nextInt();
            CabinNumber -= 1;

            //setting the deleting cabin number to 'empty'
            Cabin[CabinNumber] = "e";
            System.out.println("Cabin Number " + (CabinNumber + 1) + " Has Been Deleted.");
        } catch (Exception e) {
            System.out.println("Invalid Cabin Number.");
        }
    }

    private static void FindCustomer(String Cabin[]) {
        String CustomerName;
        Scanner input = new Scanner(System.in);
        boolean nameFound = false;

        System.out.println("Please Enter The Customer Name :");
        CustomerName = input.next().toUpperCase();

        //loops through the 'Cabin' array to check the cabin number of th given customer name
        for (int x = 0; x < Cabin.length; x++) {
            if (Cabin[x].equals(CustomerName)) {
                System.out.println("The Cabin Number Of " + CustomerName + " is : " + (x + 1));
                nameFound = true;
            }
        }
        if (nameFound==false) {
            System.out.println("Guest Not Found."); //if the input name has not been found this will be displayed
        }
    }

    private static void StoringProgram(String Cabin[]) {
        try {
            //creates a file name called 'hotelData'
            FileWriter myWriter = new FileWriter("C:\\Users\\Admin\\Documents\\w1869931_arrays_only\\Task 1\\hotelDataTask1.txt");
            //myWriter.write is used to write the given data into the file
            myWriter.write("List of Customer Name And Their Cabin Number\n\n");

            for (int x = 0; x < Cabin.length; x++) {
                if (Cabin[x].equals("e")) {
                    myWriter.write("Cabin " + (x + 1) + " Is Empty\n");
                } else {
                    //"(x+1)" is put to add one to the index because the starting element is always 0
                    myWriter.write("Cabin " + (x + 1) + " occupied by " + Cabin[x]+"\n");
                }
            }
            myWriter.close();//ends the writing of the file
        } catch (IOException e) {
            System.out.println("Error");
        }
        System.out.println("File Saved.");
    }

    private static void LoadingProgram(String Cabin[]) {
        try {
            //opens the written file into the java program
            File CabinFile = new File("hotelDataTask1.txt");
            Scanner CabinFileReader = new Scanner(CabinFile);
            //reads each line from the file
            while (CabinFileReader.hasNextLine()) {
                String data = CabinFileReader.nextLine();
                System.out.println(data);
            }
            CabinFileReader.close(); //stops the reading
        } catch (IOException e) {
            System.out.println("File Is Not Created.");//displays if file is not created
        }
    }

    private static void OrderedAlphabetically(String Cabin[]) {
        String[] alphabet = new String[12];

        //adding the Cabin elements to another array called "alphabet"
        for (int x = 0; x < 12; x++) {
            alphabet[x] = Cabin[x];
        }

        //bubble sorting has been used to arrange in alphabetical order
        for (int i = 0; i < alphabet.length; i++) {
            for (int j = i + 1; j < alphabet.length; j++) {
                if (alphabet[i].compareTo(alphabet[j]) > 0) { //(.compareTo) is to compare String value
                    String temp = alphabet[i];
                    alphabet[i] = alphabet[j];
                    alphabet[j] = temp;
                }
            }
        }

        System.out.println("\nDisplaying Names in Alphabetical Order.\n");
        for (int i = 0; i < alphabet.length; i++) {
            if (alphabet[i].equals("e")) {
                System.out.print("");
            } else {
                System.out.println(alphabet[i]);//displays the name of the customer alphabetically
            }
        }
    }
}