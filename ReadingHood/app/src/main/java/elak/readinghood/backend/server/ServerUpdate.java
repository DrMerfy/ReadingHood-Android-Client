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
     * @throws IOException Can not Connect to server
     */
    public static void createUser(String email, String username, String password, String department) throws IOException {
        try {
            String url = "https://readinghood.tk:8443/register?username=" + URLEncoder.encode(username, "UTF-8") +
                    "&email=" + URLEncoder.encode(email, "UTF-8") +
                    "&password=" + URLEncoder.encode(password, "UTF-8");
            if (!department.isEmpty()) {
                url = url + "&department=" + URLEncoder.encode(department, "UTF-8");
            }
            ServerConnection.sendSimpleRequest(url, "GET");
        } catch (IOException e) {
            throw new IOException();
        }
    }

    /**
     * This function edits the name of the user.
     *
     * @param newName the user given new name
     * @throws IOException Can not Connect to server
     */
    public static void editName(String newName) throws IOException {

        try {
            String url = "https://readinghood.tk:8443/profiles/editName?name=" + URLEncoder.encode(newName, "UTF-8");
            ServerConnection.sendAuthenticatedRequest(url, AppManager.getUserProfile().getEmail(), AppManager.getUserProfile().getPassword(), "GET");
        } catch (IOException e) {
            throw new IOException();
        }
    }

    /**
     * This function edits the surname of the user.
     *
     * @param newSurname the user given new surname
     * @throws IOException Can not Connect to server
     */
    public static void editSurname(String newSurname) throws IOException {
        try {
            String url = "https://readinghood.tk:8443/profiles/editSurname?surname=" + URLEncoder.encode(newSurname, "UTF-8");
            ServerConnection.sendAuthenticatedRequest(url, AppManager.getUserProfile().getEmail(), AppManager.getUserProfile().getPassword(), "GET");
        } catch (IOException e) {
            throw new IOException();
        }
    }

    /**
     * This function creates a Thread.
     * You need the title, the tags and the text of the first post of the thread.
     * You will get as a result if the creation was correct or not.
     *
     * @param title is the title of the thread
     * @param tags  are the tags of the thread
     * @throws IOException Can not Connect to server
     */
    public static void createThread(String title, String text, HashSet<String> tags) throws IOException {
        try {
            String url = "https://readinghood.tk:8443/threads/new?title=";
            String restOfUrl = URLEncoder.encode(title, "UTF-8") + "&text=" + URLEncoder.encode(text, "UTF-8");

            if (!tags.isEmpty()) {
                restOfUrl = restOfUrl + "&tags=" + URLEncoder.encode(new ArrayList<>(tags).get(0), "UTF-8");
            }
            for (int i = 1; i < tags.size(); i++) {
                restOfUrl = restOfUrl + "," + URLEncoder.encode(new ArrayList<>(tags).get(i), "UTF-8");
            }
            ServerConnection.sendAuthenticatedRequest(url + restOfUrl, AppManager.getUserProfile().getEmail(), AppManager.getUserProfile().getPassword(), "GET");
        } catch (IOException e) {
            throw new IOException();
        }
    }

    /**
     * This function answers a thread by creating a post with a specific text.
     *
     * @param id   is the id of the thread that is gonna be answered
     * @param text is the text of the post that the user is gonna write
     * @throws IOException Can not Connect to server
     */
    public static void answerThread(int id, String text) throws IOException {
        try {
            String url = "https://readinghood.tk:8443/posts/new?thread_id=" + Integer.toString(id) + "&text=" + URLEncoder.encode(text, "UTF-8");
            ServerConnection.sendAuthenticatedRequest(url, AppManager.getUserProfile().getEmail(), AppManager.getUserProfile().getPassword(), "GET");
        } catch (IOException e) {
            throw new IOException();
        }
    }

    /**
     * This function updates the views of a specific Thread.
     *
     * @param id is the id of the thread you wanna see
     * @throws IOException Can not Connect to server
     */
    public static void seeThread(int id) throws IOException {
        try {
            String url = "https://readinghood.tk:8443/threads/viewed?id=" + Integer.toString(id);
            ServerConnection.sendAuthenticatedRequest(url, AppManager.getUserProfile().getEmail(), AppManager.getUserProfile().getPassword(), "GET");
        } catch (IOException e) {
            throw new IOException();
        }
    }

    /**
     * This function upVotes the post with the given id.
     *
     * @param id is the id of the post that is going to be upVoted
     * @throws IOException Can not Connect to server
     */
    public static void upVotePost(int id) throws IOException {
        try {
            String url = "https://readinghood.tk:8443/posts/upvote?post_id=" + Integer.toString(id);
            ServerConnection.sendAuthenticatedRequest(url, AppManager.getUserProfile().getEmail(), AppManager.getUserProfile().getPassword(), "GET");
        } catch (IOException e) {
            throw new IOException();
        }
    }

    /**
     * This function downVotes the post with the given id.
     *
     * @param id is the id of the post that is going to be downVoted
     * @throws IOException Can not Connect to server
     */
    public static void downVotePost(int id) throws IOException {
        try {
            String url = "https://readinghood.tk:8443/posts/downvote?post_id=" + Integer.toString(id);
            ServerConnection.sendAuthenticatedRequest(url, AppManager.getUserProfile().getEmail(), AppManager.getUserProfile().getPassword(), "GET");
        } catch (IOException e) {
            throw new IOException();
        }
    }
}