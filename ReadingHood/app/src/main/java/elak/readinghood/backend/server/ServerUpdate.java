package elak.readinghood.backend.server;

import elak.readinghood.backend.api.AppManager;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * @author Spiros
 */
public class ServerUpdate {
    /**
     * This function creates a user.
     *
     * @param email      is the user given email
     * @param username   is the user given username
     * @param password   is the user given  password
     * @param department is the user given department
     * @return a boolean value which indicates if the user was created succefully
     */
    public static boolean createUser(String email, String username, String password, String department) {
        try {
            String url = "https://readinghood.tk:8443/register?username=" + URLEncoder.encode(username, "UTF-8") +
                    "&email=" + URLEncoder.encode(email, "UTF-8") +
                    "&password=" + password;
            if (!department.isEmpty()) {
                url = url + "&department=" + URLEncoder.encode(department, "UTF-8");
            }
            ConnectionWithServer.sendSimpleRequest(url, "GET");
            return true;
        } catch (IOException e) {
            // todo error dialog
            return false;
        }
    }

    /**
     * This function edits the name of the user.
     *
     * @param newName the user given new name
     * @return a boolean value which indicates if the name was updates successfully
     */
    public static boolean editName(String newName) {

        try {
            String url = "https://readinghood.tk:8443/profiles/editName?name=" + URLEncoder.encode(newName, "UTF-8");
            ConnectionWithServer.sendAuthenticatedRequest(url, AppManager.getUserProfile().getEmail(), AppManager.getUserProfile().getPassword(), "GET");
            return true;
        } catch (IOException e) {
            // todo error dialog
            return false;
        }

    }

    /**
     * This function edits the surname of the user.
     *
     * @param newSurname the user given new surname
     * @return a boolean value which indicates if the surname was updates successfully
     */
    public static boolean editSurname(String newSurname) {
        try {
            String url = "https://readinghood.tk:8443/profiles/editSurname?surname=" + URLEncoder.encode(newSurname, "UTF-8");
            ConnectionWithServer.sendAuthenticatedRequest(url, AppManager.getUserProfile().getEmail(), AppManager.getUserProfile().getPassword(), "GET");
            return true;
        } catch (IOException e) {
            // todo error dialog
            return false;
        }
    }

    /**
     * This function creates a Thread.
     * You need the title, the tags and the text of the first post of the thread.
     * You will get as a result if the creation was correct or not.
     *
     * @param title is the title of the thread
     * @param tags  are the tags of the thread
     * @return a boolean value which indicates if the creation was successful or not
     */
    public static boolean createThread(String title, String text, HashSet<String> tags) {
        try {
            String url = "https://readinghood.tk:8443/threads/new?title=";
            String restOfUrl = URLEncoder.encode(title, "UTF-8") + "&text=" + URLEncoder.encode(text, "UTF-8");

            if (!tags.isEmpty()) {
                restOfUrl = restOfUrl + "&tags=" + URLEncoder.encode(new ArrayList<>(tags).get(0), "UTF-8");
            }
            for (int i = 1; i < tags.size(); i++) {
                restOfUrl = restOfUrl + "," + URLEncoder.encode(new ArrayList<>(tags).get(i), "UTF-8");
            }
            ConnectionWithServer.sendAuthenticatedRequest(url + restOfUrl, AppManager.getUserProfile().getEmail(), AppManager.getUserProfile().getPassword(), "GET");
            return true;
        } catch (IOException e) {
            // todo error dialog
            return false;
        }
    }

    /**
     * This function answers a thread by creating a post with a specific text.
     *
     * @param id   is the id of the thread that is gonna be answered
     * @param text is the text of the post that the user is gonna write
     * @return a boolean value which indicates if the adding was done successfully
     */
    public static boolean answerThread(int id, String text) {
        try {
            String url = "https://readinghood.tk:8443/posts/new?thread_id=" + Integer.toString(id) + "&text=" + URLEncoder.encode(text, "UTF-8");
            ConnectionWithServer.sendAuthenticatedRequest(url, AppManager.getUserProfile().getEmail(), AppManager.getUserProfile().getPassword(), "GET");
            return true;
        } catch (IOException e) {
            // todo error dialog
            return false;
        }
    }

    /**
     * This function updates the view of a specific Thread.
     *
     * @param id is the id of the thread you wanna see
     * @return a boolean value which indicates if the increase of views was successful
     */
    public static boolean seeThread(int id) {
        try {
            String url = "https://readinghood.tk:8443/threads/viewed?id=" + Integer.toString(id);
            ConnectionWithServer.sendAuthenticatedRequest(url, AppManager.getUserProfile().getEmail(), AppManager.getUserProfile().getPassword(), "GET");
            return true;
        } catch (IOException e) {
            // todo error dialog
            return false;
        }
    }

    /**
     * This function add this thread to the favorites of the user.
     *
     * @param id is the id of the thread you wanna see
     * @return a boolean value which indicates if the addition to the favorite threads was successful
     */
    public static boolean addThreadToFavorites(int id) {
        try {
            String url = "https://readinghood.tk:8443/threads/favorite?thread_id=" + Integer.toString(id);
            ConnectionWithServer.sendAuthenticatedRequest(url, AppManager.getUserProfile().getEmail(), AppManager.getUserProfile().getPassword(), "GET");
            return true;
        } catch (IOException e) {
            // todo error dialog
            return false;
        }
    }

    /**
     * This function upVotes the post with id  = id.
     *
     * @param id is the id of the post that is going to be upVoted
     * @return a boolean value which indicates if the upVote was Successful or not
     */
    public static boolean upVotePost(int id) {
        try {
            String url = "https://readinghood.tk:8443/posts/upvote?post_id=" + Integer.toString(id);
            ConnectionWithServer.sendAuthenticatedRequest(url, AppManager.getUserProfile().getEmail(), AppManager.getUserProfile().getPassword(), "GET");
            return true;
        } catch (IOException e) {
            // todo error dialog
            return false;
        }
    }

    /**
     * This function downVotes the post with id  = id.
     *
     * @param id is the id of the post that is going to be downVoted
     * @return a boolean value which indicates if the downVote was Successful or not
     */
    public static boolean downVotePost(int id) {
        try {
            String url = "https://readinghood.tk:8443/posts/downvote?post_id=" + Integer.toString(id);
            ConnectionWithServer.sendAuthenticatedRequest(url, AppManager.getUserProfile().getEmail(), AppManager.getUserProfile().getPassword(), "GET");
            return true;
        } catch (IOException e) {
            // todo error dialog
            return false;
        }
    }
}