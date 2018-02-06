package elak.readinghood.backend.ServerClasses;

import elak.readinghood.backend.ProfileClasses.UserProfile;
import elak.readinghood.backend.Threads.Thread;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Spiros
 */
public class ServerRequest {

    /**
     * trivial constructor
     */
    public ServerRequest() {

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
            String jsonResult = ConnectionWithServer.sendSimpleRequest(url + email, "GET");
            //System.out.println("Response from '" + url + email + "':\n" + jasonResult);
            try {
                JSONObject jsonObject = new JSONObject(jsonResult);
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
            String pingResult = ConnectionWithServer.sendAuthenticatedRequest(url, email, password, "GET");
            //System.out.println("Response from '" + url + email + "':\n" + pingResult);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * This function is initialized after successfully giving the correct email and password in login
     * and returns the profile of the user
     *
     * @param email    the user given email
     * @param password the user given password
     * @return the profile of the user
     */
    public static UserProfile getUserProfile(String email, String password) {
        try {
            String url = "https://readinghood.tk:8443/accounts/searchEmail?email=";
            String jsonResult = ConnectionWithServer.sendSimpleRequest(url + email, "GET");
            //System.out.println("Response from '" + url + email + "':\n" + jsonResult);
            try {
                JSONObject jsonObject = new JSONObject(jsonResult);

                int responseID = Integer.parseInt(jsonObject.get("id").toString());

                String responseUsername = jsonObject.getString("username");

                String responseName;
                try {
                    responseName = jsonObject.getJSONObject("profile").getString("name");
                } catch (JSONException J) {
                    //System.out.println("empty key");
                    responseName = null;
                }

                String responseSurname;
                try {
                    responseSurname = jsonObject.getJSONObject("profile").getString("surname");
                } catch (JSONException J) {
                    //System.out.println("empty key");
                    responseSurname = null;
                }

                String responseDepartment;
                try {
                    responseDepartment = jsonObject.getJSONObject("profile").getString("department");
                } catch (JSONException J) {
                    //System.out.println("empty key");
                    responseDepartment = null;
                }

                return new UserProfile(responseID, email, responseUsername, password, responseName, responseSurname, responseDepartment);
            } catch (JSONException J) {
                System.out.println("wrong json");
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * This function returns the threads of
     *
     * @param userProfile
     * @param option
     * @return
     */
    public static ArrayList<Thread> getThreads(UserProfile userProfile, String option) {

        try {
            String url = "https://readinghood.tk:8443/" + option;
            String jsonResult = ConnectionWithServer.sendAuthenticatedRequest(url  ,userProfile.getEmail(), userProfile.getPassword(), "GET");
            System.out.println(jsonResult);
            try {
                JSONObject jsonObject = new JSONObject(jsonResult);
                System.out.println(jsonObject);
                System.out.println(jsonObject.toJSONArray(jsonObject.getJSONArray("id")).getJSONObject(1));
                String test = jsonObject.getJSONArray("1").getJSONObject(1).toString();
                System.out.println(test);
            } catch (JSONException J) {
                return null;
            }
            //System.out.println("Response from '" + url + email + "':\n" + pingResult);
        } catch (IOException e) {

        }
        return null;
    }
}