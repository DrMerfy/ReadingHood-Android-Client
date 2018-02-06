package elak.readinghood.backend.Api;

import elak.readinghood.backend.ProfileClasses.UserProfile;
import elak.readinghood.backend.ServerClasses.ServerRequest;
import elak.readinghood.backend.ServerClasses.ServerUpdate;
import elak.readinghood.backend.Threads.Post;
import elak.readinghood.backend.Threads.Tag;
import elak.readinghood.backend.Threads.Thread;

import java.util.ArrayList;

/**
 * @author Spiros
 */
public class AppManager {
    private StartUpManager startUpManager;
    private UserProfile userProfile;

    /**
     * trivial constructor
     */
    public AppManager() {
        startUpManager = new StartUpManager();
    }

    /**
     * You use this function to login or register (look into StartUpManager class to understand how it works)
     *
     * @return a static object of startUpManager
     */
    public StartUpManager getStartUpManager() {
        return startUpManager;
    }

    /**
     * This function sets the user
     */
    public void setUserProfile() {
        userProfile = startUpManager.getUserProfile();
    }

    /**
     * @return the profile of the user
     */
    public UserProfile getUserProfile() {
        return userProfile;
    }

    /**
     * This function creates a Thread.
     * You need the title,  text of the first post of the thread and the tags.
     * You will get as a result if the creation was correct or not with an error text
     * <p>
     * Error0 = "Success" (which means the creating was successful)
     * Error1 = "Error connecting with server"
     *
     * @param title is the title of the thread
     * @param text  is the text of the first post of the thread
     * @param tags  are the tags of the thread
     */
    public String createThread(String title, String text, ArrayList<String> tags) {
        https:
//readinghood.tk:8443/threads/new?title=What is Json&text=my question is what is json...&tags=json,spring
        if (ServerUpdate.createThread(userProfile, title, text, tags)) {
            return "Success";
        } else {
            return "Error connecting with server";
        }
    }

    /**
     * This function returns the popular threads of the news feed
     *
     * @return the popular threads of the news feed
     */
    public ArrayList<Thread> getPopularThreadsOfNewsFeed() {
        return getThreads("threads/popular");
    }

    /**
     * This function returns the recent threads of the news feed
     *
     * @return
     */
    public ArrayList<Thread> getRecentThreadsOfNewsFeed() {
        return getThreads("threads/recent");
    }

    /**
     * Returns threads according to the option asked
     * @param option the option that is asked
     * @return the threads that have been asked to be delivered
     */
    private ArrayList<Thread> getThreads(String option) {
        return ServerRequest.getThreads(userProfile, option);
    }
}
