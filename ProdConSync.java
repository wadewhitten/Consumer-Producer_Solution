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

    //Method to create a new buffer array
    public static char[] createBuffer(int bufferSize){

        //Declare a new buffer array
        char newBuffer[] = new char[bufferSize];


        //Initialize all slots of buffer to null
        for (int i = 0; i < bufferSize; i++){
            newBuffer[i] = '\u0000';
        }

        System.out.println("TEST - 1");

        //return the newly created buffer array
        return newBuffer;

    }   //End of createBuffer method

    //Main Method
    public static void main (String args[]) {

        //Create new Semaphore object, mutex
        //Initializing to 1, meaning used for mutual exclusion
        Semaphore mutex = new Semaphore (1);

        //Create new Semaphore object, full
        //Initializing to 0, meaning used for execution ordering
        Semaphore full = new Semaphore (0);

        //Create new Semaphore object, empty
        //Initializing to N, meaning if N > 1, it is a counting semaphore
        Semaphore empty = new Semaphore (N);


        //Creates a new buffer to be used by the producer and consumer
        char[] bufferArray = createBuffer(N);

        System.out.println("TEST0");


        //Create new Producer object
        Producer prod = new Producer (mutex, full, empty, bufferArray);

        System.out.println("TEST2");


        //Create new Consumer object
        Consumer cons = new Consumer (mutex, full, empty, bufferArray);

        System.out.println("TEST3");


        //Run Producer object's start method
        prod.start() ;

        //Run Consumer object's start method
        cons.start();

    }   //End of main method

}   //End of class ProdConSync