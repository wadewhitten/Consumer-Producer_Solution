/*
Wade Whitten
CS 3502 - 02
Dr. Jose M. Garrido
M/W 5:00 - 6:15 PM
Assignment #3
Java Implementation of the Producer-Consumer Problem
October 4, 2018
 */

public class ProdConSync {

    static final int N = 100;   //Number of slots in buffer

    //Main Method
    public static void main (String args[]) {

        //Create new Semaphore object, mutex
        //Initializing to 1, meaning used for mutual exclusion
        Semaphore mutex = new Semaphore (1);

        //Create new Semaphore object, full
        //Initializing to 0, meaning used for execution ordering
        Semaphore full= new Semaphore (0);

        //Create new Semaphore object, empty
        //Initializing to N, meaning if N > 1, it is a counting semaphore
        Semaphore empty = new Semaphore (N);

        //Create new Producer object
        Producer prod= new Producer (mutex, full, empty);

        //Create new Consumer object
        Consumer cons = new Consumer (mutex, full, empty);

        //Run Prouder object's run method
        prod.start() ;

        //Run Consumer object's run method
        cons.start();


    }   //End of main method

}//End of class ProdConSync