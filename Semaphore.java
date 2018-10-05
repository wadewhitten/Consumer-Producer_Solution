/*
Wade Whitten
CS 3502 - 02
Dr. Jose M. Garrido
M/W 5:00 - 6:15 PM
Assignment #3
Java Implementation of the Producer-Consumer Problem
October 4, 2018
 */

class Semaphore {

    //Semaphore integer variable
    private int sem;


    //@Override
    /*
            Checks the value of the sem integer of the semaphore object
            to see if another process is using the critical section.

            If the value of sem is > 1, the process will continue, and
            the value of sem will be decremented by 1, notifying other
            processes that critical section is in use.

            If sem <= 0, the process will suspend and be placed into the
            semaphore object's queue.
            */
    public synchronized void semaphore_Wait() {
        while (sem <= 0) {
            try {
                wait(2500);
            } catch (Exception e) {
                System.out.println("There was an error at semaphore_Wait()");
                System.exit(0);
            }

        }   //End of while loop

        sem--;  //Decreases the value of sem by 1
        /*This lets other processes know the critical
          section is being used*/

    }   //End of semaphore_Wait() method


    //A method to increment the integer sem, notifying other processes that
    // the critical section is not currently in use
    public synchronized void signal() {

        //Increment sem by 1
        sem++;


        notify();

    }   //End of signal method


    //Semaphore object constructor
    public Semaphore(int intval) {
        sem = intval;   //Initialize attribute sem
        //If sem = 1, Semaphore will be used for mutual exclusion
        //If sem = 0, Semaphore will be used for execution ordering

    }   //End of Semaphore constructor

}   //End of class Semaphore
