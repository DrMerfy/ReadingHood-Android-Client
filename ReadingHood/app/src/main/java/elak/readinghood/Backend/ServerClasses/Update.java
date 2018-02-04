package elak.readinghood.Backend.ServerClasses;

import java.io.IOException;

/**
 * @author Spiros, Nasos
 */
public class Update extends ConnectionWithServer {

    /**
     * a trivial constructor
     */
    public Update() {

    }

    /**
     * This function creates a user
     *
     * @param email      is the user given email
     * @param username   is the user given username
     * @param password   is the user given  password
     * @param department is the user given department
     */
    public static String createUser(String email, String username, String password, String department) {
        try {
            String url = "https://readinghood.tk:8443/register?username=" + username +
                    "&email=" + email +
                    "&password=" + password;
            if (!department.isEmpty()) {
                url = url +  "&department=" + department;
            }
            System.out.println(department + "\n");
            return sendSimpleRequest(url, "GET");

        } catch (IOException e) {
            return "NOT OK";
        }
    }
}
