package elak.readinghood.backend.ServerClasses;

import elak.readinghood.backend.ProfileClasses.UserProfile;
import elak.readinghood.backend.Threads.Post;
import elak.readinghood.backend.Threads.Tag;
import elak.readinghood.backend.Threads.Thread;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URLEncoder;
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

                int responseID = jsonObject.getInt("id");

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
     * This function returns the threads that it has been asked to deliver
     *
     * @param userProfile is the user
     * @param option      is the required question
     * @return the threads that it has been asked to deliver
     */
    public static ArrayList<Thread> getThreads(UserProfile userProfile, String option) {
        ArrayList<Thread> threads = new ArrayList<>();
        try {
            String url = "https://readinghood.tk:8443/" + option;
            System.out.println(url);
            String jsonResult = ConnectionWithServer.sendAuthenticatedRequest(url, userProfile.getEmail(), userProfile.getPassword(), "GET");
            // System.out.println(jsonResult);
            try {
                JSONArray jsonThreads = new JSONArray(jsonResult);
                for (int i = 0; i < jsonThreads.length(); i++) {
                    try {
                        JSONObject thread = jsonThreads.getJSONObject(i);
                        System.out.println("Thread id = " + i + "\n" + thread);

                        //getting id
                        int id = thread.getInt("id");

                        //getting title
                        String title = thread.getString("title");

                        //getting views
                        int views = thread.getInt("views");
                        //System.out.println(id + " , " + title + " , ");

                        //getting tags
                        ArrayList<Tag> tags = new ArrayList<>();
                        try {
                            JSONArray tagsArray = thread.getJSONArray("tags");
                            for (int j = 0; j < tagsArray.length(); j++) {
                                JSONObject jsonTag = tagsArray.getJSONObject(0);
                                //System.out.println(jsonTag);
                                try {
                                    tags.add(new Tag(jsonTag.getInt("id"), jsonTag.getInt("usages"), jsonTag.getString("name")));
                                    //System.out.println(tags.get(j).getId() + " , " + tags.get(j).getUsages() + " , " + tags.get(j).getName());
                                } catch (JSONException J) {

                                }
                            }
                        } catch (JSONException J) {
                        }

                        //getting posts
                        ArrayList<Post> posts = new ArrayList<>();
                        try {
                            JSONArray postsArray = thread.getJSONArray("posts");

                            //getting question post
                            try {
                                JSONObject jsonPost = postsArray.getJSONObject(0);
                                System.out.println(jsonPost);
                            } catch (JSONException J) {
                            }
                            for (int j = 1; j < postsArray.length(); j++) {
                                JSONObject jsonPost = postsArray.getJSONObject(j);
                                System.out.println(jsonPost);
                                //int numberOfPosts = jsonPost
                                /*
                                try {
                                    tags.add(new Tag(jsonTag.getInt("id"), jsonTag.getInt("usages"), jsonTag.getString("name")));
                                    //System.out.println(tags.get(j).getId() + " , " + tags.get(j).getUsages() + " , " + tags.get(j).getName());
                                } catch (JSONException J) {

                                }
                                */
                            }
                        } catch (JSONException J) {
                        }
                        //System.out.println();

                    } catch (JSONException J) {
                    }
                }
                return threads;
            } catch (JSONException J) {
                return threads;
            }
        } catch (IOException e) {
            return threads;
        }
    }

    /**
     * This functions returns the tags that has been asked to deliver
     *
     * @param userProfile is the user
     * @param option      is the specific way that is asked
     * @return the tags that has been asked to deliver
     */
    public static ArrayList<Tag> getTags(UserProfile userProfile, String option) {
        ArrayList<Tag> tags = new ArrayList<>();
        try {
            String url = "https://readinghood.tk:8443/tags/" + option;
            String jsonResult = ConnectionWithServer.sendAuthenticatedRequest(url, userProfile.getEmail(), userProfile.getPassword(), "GET");
            try {
                JSONArray jsonTags = new JSONArray(jsonResult);
                for (int i = 0; i < jsonTags.length(); i++) {
                    try {
                        JSONObject jsonTag = jsonTags.getJSONObject(i);

                        // get id
                        int id = jsonTag.getInt("id");
                        //System.out.println(id);

                        // get usages
                        int usages = jsonTag.getInt("usages");
                        //System.out.println(usages);

                        // get tag name
                        String tagName = jsonTag.getString("name");
                        //System.out.println(tagName);

                        tags.add(new Tag(id, usages, tagName));
                    } catch (JSONException J) {

                    }
                }
                return tags;
            } catch (JSONException J) {
                return tags;
            }
        } catch (IOException e) {
            return tags;
        }

    }
}