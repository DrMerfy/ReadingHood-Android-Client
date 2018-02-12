package elak.readinghood.backend.threads;

import elak.readinghood.backend.server.ServerUpdate;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Spiros
 */
public class Threads {
    private ArrayList<Thread> threads;

    /**
     * trivial constructor
     */
    public Threads() {

    }

    /**
     * trivial constructor
     */
    public Threads(ArrayList<Thread> threads) {
        this.threads = threads;
    }

    /**
     * You use this function ONLY to structure your results.
     *
     * @param number of the thread that you wanna get
     * @return the thread that you wanna get
     */
    public Thread getThread(int number) {
        return threads.get(number);
    }

    /**
     * This function returns the thread that you wanna choose to see.
     * And then increases the views of the threads.
     *
     * @param number of the thread that you wanna see
     * @return the thread that you wanna see
     * @throws IOException Can not Connect to server
     */
    public Thread seeThread(int number) throws IOException{
        Thread chosenThread = getThread(number);
        ServerUpdate.seeThread(chosenThread.getId());
        chosenThread.increaseViews();

        return chosenThread;
    }

    /**
     * @return the size of the threads that you have
     */
    public int size() {
        return threads.size();
    }

    /**
     * @return a boolean value which indicates if the threads are empty
     */
    public boolean isEmpty() {
        return threads.isEmpty();
    }

    /**
     * @return the threads as an array list
     */
    public ArrayList<Thread> getListOfThreads() {
        return threads;
    }
}