package elak.readinghood.Backend.ServerClasses;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * @author Spiros, Nasos
 */
public class Request extends ConnectionWithServer {

    /**
     * trivial constructor
     */
    public Request() {

    }

    /**
     * This function checks if the given email exists in the database
     *
     * @param email is the user given email
     * @return a boolean value if the given email exists in the database
     */
    public static boolean existenceOfEmail(String email) {
        try {
            String url = "https://readinghood.tk:8443/accounts/searchEmail?email=";
            String jasonResult = sendSimpleRequest(url + email, "GET");
            System.out.println("Response from '" + url + email + "':\n" + jasonResult);
            try {
                JSONObject jsonObject = new JSONObject(jasonResult);
                String resposeEmail = jsonObject.getString("email");
                if (resposeEmail.equals(email)) {
                    return true;
                } else {
                    return false;
                }
            } catch (JSONException J) {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * This function checks if the given password matches the emails's password
     *
     * @param email    the usergiven email
     * @param password the usergiven password
     * @return a boolean value if the given password matches the email's password
     */
    public static boolean checkPasswordForEmail(String email, String password) {
        try {
            String url = "https://readinghood.tk:8443/verify?email=" + email +
                    "&password=" + password;
            String pingResult = sendAuthenticatedRequest(url, email, password, "GET");
            System.out.println("Response from '" + url + email + "':\n" + pingResult);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * This function is initialized after successfully giving the correct email and password in login
     * and returns the profile of the user
     *
     * @param email //@return profile
     */
    public void getUserProfile(String email) {

    }
}