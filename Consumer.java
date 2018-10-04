/*
Wade Whitten
CS 3502 - 02
Dr. Jose M. Garrido
M/W 5:00 - 6:15 PM
Assignment #3
Java Implementation of the Producer-Consumer Problem
October 4, 2018
 */

public class Consumer extends Thread {

    private Semaphore mutex;
    private Semaphore full;
    private Semaphore empty;

    //Consumer Constructor
    public Consumer(Semaphore m, Semaphore f, Semaphore e) {
        mutex = m;
        full = f;
        empty = e;
    }   //End of Consumer constructor

    //Run Method
    //
    //
    //
    //
    public  run() {  //DO I NEED VOID HERE?
        //
        //
        //
        //
        //
        while (true) {
            full.wait();    //are there any full slots?
            mutex.wait();   //acquire exclusive access
//[[[remove an item from a full slot on the buffer]]]
            mutex.signal();   //release mutual exclusion
            empty.signal();    //increment empty slots
//[[[Consume data item]]]

        }   //End of while loop

    }   //End of run method

}  //End of class Consumer