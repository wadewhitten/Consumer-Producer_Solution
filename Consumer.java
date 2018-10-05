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

    //Declare 3 Semaphore objects
    private Semaphore mutex;
    private Semaphore full;
    private Semaphore empty;
    public char[] bufferArray;

    char consumedData;

    //Consumer object constructor
    public Consumer(Semaphore m, Semaphore f, Semaphore e, char[] bA){

        mutex = m;
        full = f;
        empty = e;
        bA = bufferArray;

    }   //End of Consumer constructor


    //Run method
    public void run() {  //DO I NEED VOID HERE?

        //Begin non-terminating while loop
        while (true) {

            /*
            Checks the value of the sem integer of the full semaphore
            to see if another process is using the critical section.

            If the value of sem is > 1, the process will continue, and
            the value of sem will be decremented by 1, notifying other
            processes that critical section is in use.

            If sem <= 0, the process will suspend and be placed into the
            full semaphore queue.
            */
            full.semaphore_Wait();

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


            removeDataElement(bufferArray);


            /*
            Calls the signal method on the mutex semaphore which
            release mutual exclusion of the critical section by
            increments the sem integer of the mutex semaphore,
            allowing any process at the head of the mutex semaphore
            queue to continue execution.
            */
            mutex.signal();   //release mutual exclusion


            /*
            Calls the signal method on the empty semaphore which
            release mutual exclusion of the critical section by
            increments the sem integer of the empty semaphore,
            allowing any process at the head of the empty semaphore
            queue to continue execution.
            */
            empty.signal();

            consumeData(consumedData);

        }   //End of while loop

    }   //End of run method

    public char [] removeDataElement(char[] bufferArray){

        boolean itemConsumed = false;

        //A for loop that will consume a data element in the first
        // open spot of the buffer
        for (int i = 0; i <= 100; i++){

            //Checks if the buffer at index i is null
            if (bufferArray[i] != '\u0000'){

                //A data element is consumed from the buffer and
                // initialized to the variable consumedData
               consumedData = bufferArray[i];

               //Data element is removed from the buffer
               bufferArray[i] = '\u0000';


               System.out.println("Removing Data Element [" +
                       consumedData + "] from Buffer index: " + i);

               //A data element has now been consumed
               itemConsumed = true;

            }   //End of if statement

            //Break loop if data element has been consumed
            if (itemConsumed)
                break;

        }   //End of for loop

        //Return the bufferArray without the newly consumed data element
        return bufferArray;

    }   //End of removeDataElement method

    public void consumeData(char dataElement){

        System.out.println("After some number crunching, we have come to" +
                "the conclusion that the next letter of your encryption" +
                " key should be: [" + consumedData + "]");

    }   //End of consumeData method

}  //End of class Consumer