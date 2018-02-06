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
     * This function return the favourite threads of the user
     *
     * @return the favourite threads of the user
     */
    public ArrayList<Thread> getFavouritesThreads() {
        return getThreads("profiles/favorites");
    }

    /**
     * This function returns the recent threads of the news feed
     *
     * @return the recent threads of the news feed
     */
    public ArrayList<Thread> getRecentThreadsOfNewsFeed() {
        return getThreads("threads/recent");
    }

    /**
     * This function returns the threads with the given tag name
     *
     * @param tagName the user given tag name
     * @return the threads with this tag
     */
    public ArrayList<Thread> getThreadsAccordingToATag(String tagName) {
        return getThreads("tags/threads?name=" + tagName);
    }

    /**
     * This function returns the threads of the connected user
     *
     * @return the threads of the connected user
     */
    public ArrayList<Thread> getTheThreadsOfTheUser() {
        return getThreads("threads/created");
    }

    /**
     * This function returns all the threads
     *
     * @return all the threads
     */
    public ArrayList<Thread> getAllTheThreads() {
        return getThreads("threads/all");
    }

    /**
     * This function returns the threads according to a specific user given text which might be included somewhere
     *
     * @return the threads according to a specific user given text which might be included somewhere
     */
    public ArrayList<Thread> getThreadsAccordingToText(String text) {
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
    public ArrayList<Tag> getMostUsedTags() {
        return getTags("mostUsed");
    }

    /**
     * This function returns the tags that include the name that was given
     *
     * @param name the name that was given
     * @return the tags that include the name that was given
     */
    public ArrayList<Tag> getTagsAccordingToName(String name) {
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
    private ArrayList<Thread> getThreads(String option) {
        return ServerRequest.getThreads(userProfile, option);
    }

    /**
     * This function return tags according to an option
     *
     * @param option is the option asked
     * @return the tags according to an option
     */
    private ArrayList<Tag> getTags(String option) {
        return ServerRequest.getTags(userProfile, option);
    }
}