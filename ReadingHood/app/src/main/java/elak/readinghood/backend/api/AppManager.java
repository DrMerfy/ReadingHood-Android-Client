package elak.readinghood.backend.api;

import elak.readinghood.backend.profiles.Profile;
import elak.readinghood.backend.profiles.UserProfile;
import elak.readinghood.backend.server.ServerRequest;
import elak.readinghood.backend.server.ServerUpdate;
import elak.readinghood.backend.hashTags.HashTags;
import elak.readinghood.backend.threads.Threads;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
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
     * You need the title,  text of the first post (question) of the thread and the hashTags.
     * You will get as a result if the creation was correct or not with an error text.
     * <p>
     * Error0 = "Success" (which means the creating of the profile was successful)
     * Error1 = "Fill the fields" (when title and text are empty or full of spaces)
     * Error2 = "Thread must contain at least one hashTag"
     * Error3 = "HashTag(s) must not have spaces" (a hashTag Must not contain spaces)
     * Error4 = "Wrong hasHTag format"
     * <p>
     *
     * @param title    is the title of the thread
     * @param text     is the text of the first post of the thread
     * @param hashTags are the hashTags of the thread
     * @throws IOException Can not Connect to server
     */
    public static String createThread(String title, String text, HashSet<String> hashTags) throws IOException {
        boolean titleFullOfSpaces = title.replaceAll("\\s+", "").isEmpty();
        boolean textFullOfSpaces = text.replaceAll("\\s+", "").isEmpty();
        if (title.isEmpty() || text.isEmpty() || textFullOfSpaces || titleFullOfSpaces) {
            return "Fill the fields";
        }

        if (hashTags.isEmpty()) {
            return "Thread must contain at least one hashTag";
        }
        for (String hashTag : hashTags) {
            if (!correctHashTagFormat(hashTag)) {
                return "Wrong hashTagFormat";
            }
        }
        ServerUpdate.createThread(title, text, hashTags);
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

    /**
     * This function searches for profiles according to the given name and surname and returns the corresponding results.
     * This function is used on the search bar
     *
     * @param text is the user given text to search profiles
     * @return The threads that has been asked to deliver
     * @throws IOException Can not Connect to server
     */
    public static ArrayList<Profile> getProfilesAccordingToText(String text) throws IOException {
        String[] splited = text.split("\\s+");
        if (splited.length >= 3 || splited.length == 0) {
            return new ArrayList<>();
        } else if (splited.length == 2) {
            ArrayList<Profile> results = ServerRequest.getProfiles(splited[0], splited[1]);
            if (results.isEmpty()) {
                results = ServerRequest.getProfiles(splited[1], splited[0]);
                if (!results.isEmpty()) {
                    return results;
                }
            } else {
                return results;
            }
        } else {
            ArrayList<Profile> results = ServerRequest.getProfiles(splited[0], "");
            if (results.isEmpty()) {
                results = ServerRequest.getProfiles("", splited[0]);
                if (!results.isEmpty()) {
                    return results;
                }
            } else {
                return results;
            }
        }
        return new ArrayList<>();
    }

    // HashTags related functions

    /**
     * This function returns the threads with the given hashTag name.
     * Place to be used : HashTags.
     *
     * @param hashTagName the user given hashTag name
     * @return the threads with this hashTag
     * @throws IOException Can not Connect to server
     */
    public static Threads getThreadsAccordingToAHashTag(String hashTagName) throws IOException {
        return getThreads("tags/threads?name=" + hashTagName);
    }

    /**
     * This function returns the most used hashTags in descending order.
     * Place to be used : HashTags.
     *
     * @return the most used hashTags in descending order
     * @throws IOException Can not Connect to server
     */
    public static HashTags getMostUsedHashTags() throws IOException {
        return getHashTags("mostUsed");
    }

    /**
     * This function returns the hashTags that include the name that was given.
     * Place to be used : HashTags.
     *
     * @param name the name that was given
     * @return the hashTags that include the name that was given
     * @throws IOException Can not Connect to server
     */
    public static HashTags getHashTagsAccordingToName(String name) throws IOException {
        try {
            return getHashTags("search?name=" + URLEncoder.encode(name, "UTF-8"));
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
     * This function return hashTags according to an option.
     *
     * @param option is the option asked
     * @return the hashTags according to an option
     * @throws IOException Can not Connect to server
     */
    private static HashTags getHashTags(String option) throws IOException {
        return ServerRequest.getHashTags(1, option);
    }

    /**
     * This function checks if the hashTagFormat is correct.
     * <p>
     * According to the requirements, a hashTag must contain letters or numbers and  if it contains "_" they must be
     * between letters or numbers or a combination of them.
     *
     * @param hashTag is the given hashTag for check
     * @return a boolean value which indicates if a hashTag has a correct format
     */
    private static boolean correctHashTagFormat(String hashTag) {
        for (int i = 0; i < hashTag.length(); i++) {
            if (!Character.isLetter(hashTag.charAt(i)) || !Character.isDigit(hashTag.charAt(i)) || !(hashTag.charAt(i) == '_')) {
                return false;
            }

            if (i == 0) {
                if (hashTag.charAt(i) == '_') {
                    return false;
                }
            } else if (i == hashTag.length() - 1) {
                if (hashTag.charAt(i) == '_') {
                    return false;
                }
            } else {
                if (hashTag.charAt(i) == '_') {
                    if (hashTag.charAt(i - 1) == '_' || hashTag.charAt(i + 1) == '_') {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}