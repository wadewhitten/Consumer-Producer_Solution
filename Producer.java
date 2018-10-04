/*
Wade Whitten
CS 3502 - 02
Dr. Jose M. Garrido
M/W 5:00 - 6:15 PM
Assignment #3
Java Implementation of the Producer-Consumer Problem
October 4, 2018
 */

public class Producer extends Thread {

    //Declare 3 Semaphore objects
    private Semaphore mutex;
    private Semaphore full;
    private Semaphore empty;

    protected int producedInteger = 0;

    //Producer Constructor
    public Producer(Semaphore m, Semaphore f, Semaphore e) {
        mutex = m;
        full = f;
        empty = e;
    }   //End of Producer constructor


    //Run Method
    public void run() {
        while (true) {
//[[[Produce an item]]]
            empty.wait();   // are there any empty slots?
            mutex.wait();   // acquire exclusive access
//[[[deposit an item into an empty slot of the buffer]]]
            mutex.signal();// release mutual exclusion
            full.signal();  // increment the full number slots

        }   //End of while loop

    }   //End of run method










}   //End of class Producer

