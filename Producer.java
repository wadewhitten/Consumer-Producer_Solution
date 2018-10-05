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
    public char[] bufferArray;

    char producedData = 'a';

    //Producer object constructor
    public Producer(Semaphore m, Semaphore f, Semaphore e, char[] bA) {

        mutex = m;
        full = f;
        empty = e;
        bA = bufferArray;

        System.out.println("TEST1");

    }   //End of Producer constructor


    //Run Method
    public void run() {

       //Begin non-terminating while loop
       while (true) {

            //Produces a random character
            producedData = produceDataElement();
           System.out.println("TEST4");


            /*
            Checks the value of the sem integer of the empty semaphore
            to see if another process is using the critical section.

            If the value of sem is > 1, the process will continue, and
            the value of sem will be decremented by 1, notifying other
            processes that critical section is in use.

            If sem <= 0, the process will suspend and be placed into the
            empty semaphore queue.
            */
            empty.semaphore_Wait();
           System.out.println("TEST5");


            /*
            Checks the value of the sem integer of the mutex semaphore
            to see if another process is using the critical section.

            If the value of sem is > 1, the process will continue, and
            the value of sem will be decremented by 1, notifying other
            processes that critical section is in use.

            If sem <= 0, the process will suspend and be placed into the
            mutex semaphore queue.
            */
            mutex.semaphore_Wait();
           System.out.println("TEST6");


           //Adds the newly produced data element to an empty slot
           // in the buffer Array

           try {
               addDataElement(bufferArray, producedData);
               System.out.println("TEST7");
           }
           catch (java.lang.NullPointerException e){
               System.out.println("CAUGHT");
           }


            /*
            Calls the signal method on the mutex semaphore which
            release mutual exclusion of the critical section by
            increments the sem integer of the mutex semaphore,
            allowing any process at the head of the mutex semaphore
            queue to continue execution.
            */
            mutex.signal();
           System.out.println("TEST8");


            /*
            Calls the signal method on the full semaphore which
            release mutual exclusion of the critical section by
            increments the sem integer of the full semaphore,
            allowing any process at the head of the full semaphore
            queue to continue execution.
            */
            full.signal();
           System.out.println("TEST9");


       }   //End of while loop

    }   //End of run method



    //A method to produce a data element
    public char produceDataElement(){

        //An important information message
        System.out.println("Generating Terabytes " +
        "of meaningful data...");

        //Random ASCII character generator
        char randomChar = (char)((Math.random() * 127) + 1);

        //Return created data element
        return randomChar;

    }   //End of produceItem method


    //A method to add a data element to the shared buffer
    public char[] addDataElement(char[] bufferArray,
                                   char producedDataElement){
        this.bufferArray = bufferArray;

        //A data item has not yet been produced
        boolean itemProduced = false;

        //A for loop that will deposit the newly created data
        // element in the first open spot of the buffer
        for (int i = 0; i < 100; i++){

            //Checks if the buffer at index i is null
            if (bufferArray[i] == '\u0000'){

                //An empty buffer slot is filled with
                // the producedDataElement
                bufferArray[i] = producedDataElement;

                System.out.println("Adding Data Element [" +
                        producedDataElement + "] to Buffer index" + i);

                //An item has now been produced
                itemProduced = true;

            }   //End of if statement

            //Break loop if produced data has been added to the buffer
            if (itemProduced)
                break;

        }   //End of for loop

        System.out.println("TEST5");


        //Return the bufferArray with the newly added data element
        return bufferArray;

    }   //End of addDataElement method

}   //End of class Producer

