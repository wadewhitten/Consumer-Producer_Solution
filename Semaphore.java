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
    /*Checks the value of sem. If sem > 0, the critical section
      is not being used and the process may proceed. If sem <= 0,
      the critical section is in use, the process is suspended, and
      the process is put into the semaphore queue to must wait.*/
    public synchronized void wait() {
        while (sem <= 0) {
            try {
//                swait();
            } catch (Exception e) {
                System.exit(0);
            }

        } //End of while loop

        sem--;  //Decreases the value of sem by 1
        /*This lets other processes know the critical
          section is being used*/

    }//End of swait() method


    public synchronized void signal() {
        sem++;
        notify();
    }


    //Semaphore object constructor
    public Semaphore(int intval) {
        sem = intval;   //Initialize attribute sem
        //If sem = 1, Semaphore will be used for mutual exclusion
        //If sem = 0, Semaphore will be used for execution ordering

    }   //End of Semaphore constructor

}   //End of class Semaphore
