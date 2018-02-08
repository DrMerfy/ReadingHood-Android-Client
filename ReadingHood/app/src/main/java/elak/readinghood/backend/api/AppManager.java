package elak.readinghood.backend.api;

import elak.readinghood.backend.profiles.UserProfile;
import elak.readinghood.backend.server.ServerRequest;
import elak.readinghood.backend.server.ServerUpdate;
import elak.readinghood.backend.tags.Tags;
import elak.readinghood.backend.threads.Threads;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashSet;

/**
 * @author Spiros
 */
public class AppManager {
    private static StartUpManager startUpManager = new StartUpManager();
    ;
    private static UserProfile userProfile;

    /**
     * You use this function to login or register (look into StartUpManager class to understand how it works).
     *
     * @return a static object of startUpManager
     */
    public static StartUpManager getStartUpManager() {
        return startUpManager;
    }

    // Account related functions

    /**
     * This function sets the user
     */
    public static void setUserProfile() {
        userProfile = startUpManager.getUserProfile();
        startUpManager = null;
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
     * You will get as a result if the creation was correct or not with an error text.
     * <p>
     * Error0 = "Success" (which means the creating was successful)
     * Error1 = "Error connecting with server"
     * Error2 = "Fill the fields" (when title and text are empty or full of spaces)
     * Error3 = "Tag(s) must not have spaces" (a tag Must not contain spaces)
     *
     * @param title is the title of the thread
     * @param text  is the text of the first post of the thread
     * @param tags  are the tags of the thread
     */
    public static String createThread(String title, String text, HashSet<String> tags) {
        boolean titleFullOfSpaces = title.replaceAll("\\s+", "").isEmpty();
        boolean textFullOfSpaces = text.replaceAll("\\s+", "").isEmpty();
        if (title.isEmpty() || text.isEmpty() || textFullOfSpaces || titleFullOfSpaces) {
            return "Fill the fields";
        }

        for (String tag : tags) {
            if (tag.contains(" ")) {
                return "Tag(s) must not have spaces";
            }
        }
        if (ServerUpdate.createThread(title, text, tags)) {
            return "Success";
        } else {
            return "Error connecting with server";
        }
    }

    /**
     * You use this function ONLY if you have log in and then you what to log out.
     * When this function is used you return an appropriate message.
     *
     * @return "Successful log out"
     */
    public static String logOut() {
        userProfile = null;
        startUpManager = new StartUpManager();
        return "Successful log out";
    }

    // Threads related functions

    /**
     * This function returns all the threads.
     *
     * @return all the threads
     */
    public static Threads getAllTheThreads() {
        return getThreads("threads/all");
    }

    /**
     * This function returns the popular threads of the news feed.
     * Possible place to be used : NewsFeed.
     *
     * @return the popular threads of the news feed
     */
    public static Threads getPopularThreadsOfNewsFeed() {
        return getThreads("threads/popular");
    }

    /**
     * This function returns the recent threads.
     * Possible place to be used : NewsFeed.
     *
     * @return the recent threads of the news feed
     */
    public static Threads getRecentThreadsOfNewsFeed() {
        return getThreads("threads/recent");
    }

    /**
     * This function return the favourite threads of the user.
     * Possible place to be used : NewsFeed.
     *
     * @return the favourite threads of the user
     */
    public static Threads getFavoritesThreads() {
        return getThreads("profile/favorites");
    }

    /**
     * This function returns the threads from the same department as the user.
     * Possible place to be used : NewsFeed.
     *
     * @return the threads from the same department as the user
     */
    public static Threads getThreadsAccordingToTheDepartmentOfTheUser() {
        return getThreads("threads/sameDepartment");
    }

    /**
     * This function returns the threads according to a specific user given text which might be included somewhere.
     * Place to be used : Search bar.
     *
     * @return the threads according to a specific user given text which might be included somewhere
     */
    public static Threads getThreadsAccordingToText(String text) {
        try {
            return getThreads("threads/search?text=" + URLEncoder.encode(text, "UTF-8"));
        } catch (IOException e) {
            return new Threads();
        }
    }

    /**
     * This function returns the threads of that have been created from the connected user.
     * Possible place to be used : My profile.
     *
     * @return the threads of the connected user
     */
    public static Threads getTheThreadsOfTheUser() {
        return getThreads("threads/created?profile_id=" + Integer.toString(AppManager.getUserProfile().getId()));
    }

    // Tags related functions

    /**
     * This function returns the threads with the given tag name.
     * Place to be used : Tags.
     *
     * @param tagName the user given tag name
     * @return the threads with this tag
     */
    public static Threads getThreadsAccordingToATag(String tagName) {
        return getThreads("tags/threads?name=" + tagName);
    }

    /**
     * This function returns the most used tags in descending order.
     * Place to be used : Tags.
     *
     * @return the most used tags in descending order
     */
    public static Tags getMostUsedTags() {
        return getTags("mostUsed");
    }

    /**
     * This function returns the tags that include the name that was given.
     * Place to be used : Tags.
     *
     * @param name the name that was given
     * @return the tags that include the name that was given
     */
    public static Tags getTagsAccordingToName(String name) {
        try {
            return getTags("search?name=" + URLEncoder.encode(name, "UTF-8"));
        } catch (IOException e) {
            return new Tags();
        }
    }

    // Helping Functions

    /**
     * Helping function.
     * Returns threads according to the option asked.
     *
     * @param option the option that is asked
     * @return the threads that have been asked to be delivered
     */
    private static Threads getThreads(String option) {
        return ServerRequest.getThreads(option);
    }

    /**
     * Helping function.
     * This function return tags according to an option.
     *
     * @param option is the option asked
     * @return the tags according to an option
     */
    private static Tags getTags(String option) {
        return ServerRequest.getTags(1, option);
    }
}