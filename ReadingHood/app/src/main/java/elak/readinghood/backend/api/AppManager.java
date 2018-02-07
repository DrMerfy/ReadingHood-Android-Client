package elak.readinghood.backend.api;

import elak.readinghood.backend.profileClasses.UserProfile;
import elak.readinghood.backend.serverClasses.ServerRequest;
import elak.readinghood.backend.serverClasses.ServerUpdate;
import elak.readinghood.backend.threadsClasses.Tag;
import elak.readinghood.backend.threadsClasses.Thread;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * @author Spiros
 */
public class AppManager {
    private static StartUpManager startUpManager = new StartUpManager();;
    private static UserProfile userProfile;

    /**
     * You use this function to login or register (look into StartUpManager class to understand how it works)
     *
     * @return a static object of startUpManager
     */
    public static StartUpManager getStartUpManager() {
        return startUpManager;
    }

    /**
     * This function sets the user
     */
    public static void setUserProfile() {
        userProfile = startUpManager.getUserProfile();
        startUpManager = null;
    }

    /**
     * You use this function ONLY if you have log in and then you what to log out
     * When this function is used you return the
     *
     * @return "Successful log out"
     */
    public static String logOut() {
        userProfile = null;
        startUpManager = new StartUpManager();
        return "Successful log out";
    }

    /**
     * @return the profile of the user
     */
    public static UserProfile getUserProfile() {
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
    public static String createThread(String title, String text, ArrayList<String> tags) {
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
    public static ArrayList<Thread> getPopularThreadsOfNewsFeed() {
        return getThreads("threads/popular");
    }

    /**
     * This function return the favourite threads of the user
     *
     * @return the favourite threads of the user
     */
    public static ArrayList<Thread> getFavouritesThreads() {
        return getThreads("profile/favorites");
    }

    /**
     * This function returns the recent threads of the news feed
     *
     * @return the recent threads of the news feed
     */
    public static ArrayList<Thread> getRecentThreadsOfNewsFeed() {
        return getThreads("threads/recent");
    }

    /**
     * This function returns the threads with the given tag name
     *
     * @param tagName the user given tag name
     * @return the threads with this tag
     */
    public static ArrayList<Thread> getThreadsAccordingToATag(String tagName) {
        return getThreads("tags/threads?name=" + tagName);
    }

    /**
     * This function returns the threads of the connected user
     *
     * @return the threads of the connected user
     */
    public static ArrayList<Thread> getTheThreadsOfTheUser() {
        return getThreads("threads/created");
    }

    /**
     * This function returns all the threads
     *
     * @return all the threads
     */
    public static ArrayList<Thread> getAllTheThreads() {
        return getThreads("threads/all");
    }

    /**
     * This function returns the threads according to a specific user given text which might be included somewhere
     *
     * @return the threads according to a specific user given text which might be included somewhere
     */
    public static ArrayList<Thread> getThreadsAccordingToText(String text) {
        try {
            return getThreads("threads/search?text=" + URLEncoder.encode(text, "UTF-8"));
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    /**
     * This function returns the most used tags in descending order
     *
     * @return the most used tags in descending order
     */
    public static ArrayList<Tag> getMostUsedTags() {
        return getTags("mostUsed");
    }

    /**
     * This function returns the tags that include the name that was given
     *
     * @param name the name that was given
     * @return the tags that include the name that was given
     */
    public static ArrayList<Tag> getTagsAccordingToName(String name) {
        try {
            return getTags("search?name=" + URLEncoder.encode(name, "UTF-8"));
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    /**
     * Returns threads according to the option asked
     *
     * @param option the option that is asked
     * @return the threads that have been asked to be delivered
     */
    private static ArrayList<Thread> getThreads(String option) {
        return ServerRequest.getThreads(userProfile, option);
    }

    /**
     * This function return tags according to an option
     *
     * @param option is the option asked
     * @return the tags according to an option
     */
    private static ArrayList<Tag> getTags(String option) {
        return ServerRequest.getTags(userProfile, option);
    }
}