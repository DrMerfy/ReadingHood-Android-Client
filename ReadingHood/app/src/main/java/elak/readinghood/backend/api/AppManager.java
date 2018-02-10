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
     * This function sets the user.
     *
     * @throws IOException Can not Connect to server
     */
    public static void setUserProfile() throws IOException {
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
     * You need the title,  text of the first post (question) of the thread and the tags.
     * You will get as a result if the creation was correct or not with an error text.
     * <p>
     * Error0 = "Success" (which means the creating of the profile was successful)
     * Error1 = "Fill the fields" (when title and text are empty or full of spaces)
     * Error2 = "Tag(s) must not have spaces" (a tag Must not contain spaces)
     *
     * @param title is the title of the thread
     * @param text  is the text of the first post of the thread
     * @param tags  are the tags of the thread
     * @throws IOException Can not Connect to server
     */
    public static String createThread(String title, String text, HashSet<String> tags) throws IOException {
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
        ServerUpdate.createThread(title, text, tags);
        return "Success";
    }

    /**
     * You use this function ONLY if you have logged in and then you what to log out.
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
     * This function returns the popular threads of the news feed.
     * Possible place to be used : NewsFeed.
     *
     * @return the popular threads of the news feed
     */
    public static Threads getPopularThreadsOfNewsFeed() throws IOException {
        return getThreads("threads/popular");
    }

    /**
     * This function returns the recent threads.
     * Possible place to be used : NewsFeed.
     *
     * @return the recent threads of the news feed
     * @throws IOException Can not Connect to server
     */
    public static Threads getRecentThreadsOfNewsFeed() throws IOException {
        return getThreads("threads/recent");
    }

    /**
     * This function returns the threads from the same department as the user in descending order based on the date
     * (id for the time being).
     * Possible place to be used : NewsFeed.
     *
     * @return the threads from the same department as the user
     * @throws IOException Can not Connect to server
     */
    public static Threads getThreadsAccordingToTheDepartmentOfTheUser() throws IOException {
        return getThreads("threads/sameDepartment");
    }

    /**
     * This function returns the threads according to a specific user given text which might be included somewhere.
     * Place to be used : Search bar.
     *
     * @return the threads according to a specific user given text which might be included somewhere
     * @throws IOException Can not Connect to server
     */
    public static Threads getThreadsAccordingToText(String text) throws IOException {
        try {
            return getThreads("threads/search?text=" + URLEncoder.encode(text, "UTF-8"));
        } catch (IOException e) {
            throw new IOException();
        }
    }

    // Tags related functions

    /**
     * This function returns the threads with the given tag name.
     * Place to be used : Tags.
     *
     * @param tagName the user given tag name
     * @return the threads with this tag
     * @throws IOException Can not Connect to server
     */
    public static Threads getThreadsAccordingToATag(String tagName) throws IOException {
        return getThreads("tags/threads?name=" + tagName);
    }

    /**
     * This function returns the most used tags in descending order.
     * Place to be used : Tags.
     *
     * @return the most used tags in descending order
     * @throws IOException Can not Connect to server
     */
    public static Tags getMostUsedTags() throws IOException {
        return getTags("mostUsed");
    }

    /**
     * This function returns the tags that include the name that was given.
     * Place to be used : Tags.
     *
     * @param name the name that was given
     * @return the tags that include the name that was given
     * @throws IOException Can not Connect to server
     */
    public static Tags getTagsAccordingToName(String name) throws IOException {
        try {
            return getTags("search?name=" + URLEncoder.encode(name, "UTF-8"));
        } catch (IOException e) {
            throw new IOException();
        }
    }

    // Helping Functions

    /**
     * Never Use THIS FUNCTION AS A FRONT END
     * Helping function.
     * Returns threads according to the option asked.
     *
     * @param option the option that is asked
     * @return the threads that have been asked to be delivered
     * @throws IOException Can not Connect to server
     */
    private static Threads getThreads(String option) throws IOException {
        return ServerRequest.getThreads(option);
    }

    /**
     * Helping function.
     * This function return tags according to an option.
     *
     * @param option is the option asked
     * @return the tags according to an option
     * @throws IOException Can not Connect to server
     */
    private static Tags getTags(String option) throws IOException {
        return ServerRequest.getTags(1, option);
    }
}