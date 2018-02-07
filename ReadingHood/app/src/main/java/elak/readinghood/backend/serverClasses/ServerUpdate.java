package elak.readinghood.backend.serverClasses;

import elak.readinghood.backend.profileClasses.UserProfile;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * @author Spiros
 */
public class ServerUpdate {

    /**
     * a trivial constructor
     */
    public ServerUpdate() {

    }

    /**
     * This function creates a user
     *
     * @param email      is the user given email
     * @param username   is the user given username
     * @param password   is the user given  password
     * @param department is the user given department
     * @return a boolean value which indicates if the user was created succefully
     */
    public static boolean createUser(String email, String username, String password, String department) {
        try {
            String url = "https://readinghood.tk:8443/register?username=" + username +
                    "&email=" + email +
                    "&password=" + password;
            if (!department.isEmpty()) {
                url = url + "&department=" + department;
            }
            String pingResult = ConnectionWithServer.sendSimpleRequest(url, "GET");
            //System.out.println(pingResult);
            return true;

        } catch (IOException e) {
            return false;
        }
    }

    /**
     * This function edits the name of the user
     *
     * @param email    the user's email
     * @param password the user's password
     * @param newName  the user given new name
     * @return a boolean value which indicates if the name was updates succefully
     */
    public static boolean editName(String email, String password, String newName) {

        try {
            String url = "https://readinghood.tk:8443/profiles/editName?name=" + URLEncoder.encode(newName, "UTF-8");
            String pingResult = ConnectionWithServer.sendAuthenticatedRequest(url, email, password, "GET");
            //System.out.println("Response from '" + url + "':\n" + pingResult);
            return true;
        } catch (IOException e) {
            return false;
        }

    }

    /**
     * This function edits the surname of the user
     *
     * @param email      the user's email
     * @param password   the user's password
     * @param newSurname the user given new surname
     * @return a boolean value which indicates if the surname was updates successfully
     */
    public static boolean editSurname(String email, String password, String newSurname) {
        try {
            String url = "https://readinghood.tk:8443/profiles/editSurname?surname=" + URLEncoder.encode(newSurname, "UTF-8");
            String pingResult = ConnectionWithServer.sendAuthenticatedRequest(url, email, password, "GET");
            //System.out.println("Response from '" + url + "':\n" + pingResult);
            return true;
        } catch (IOException e) {
            return false;
        }

    }

    /**
     * This function creates a Thread.
     * You need the title, the tags and the text of the first post of the thread.
     * You will get as a result if the creation was correct or not.
     *
     * @param userProfile is the user that wants to create a thread
     * @param title       is the title of the thread
     * @param tags        are the tags of the thread
     * @return a boolean value which indicates if the creation was successful or not
     */
    public static boolean createThread(UserProfile userProfile, String title, String text, ArrayList<String> tags) {
        try {
            String url = "https://readinghood.tk:8443/threads/new?title=";
            String restOfUrl = URLEncoder.encode(title, "UTF-8") + "&text=" + URLEncoder.encode(text, "UTF-8");
            if (!tags.isEmpty()) {
                restOfUrl = restOfUrl + "&tags=" + URLEncoder.encode(tags.get(0), "UTF-8");
            }


            for (int i = 1; i < tags.size(); i++) {
                restOfUrl = restOfUrl + "," + URLEncoder.encode(tags.get(i), "UTF-8");
            }
            System.out.println(url + restOfUrl);
            String pingResult = ConnectionWithServer.sendAuthenticatedRequest(url + restOfUrl, userProfile.getEmail(), userProfile.getPassword(), "GET");
            //System.out.println("Response from '" + url + email + "':\n" + pingResult);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
