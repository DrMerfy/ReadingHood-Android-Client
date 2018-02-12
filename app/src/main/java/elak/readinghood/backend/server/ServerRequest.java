package elak.readinghood.backend.server;

import android.nfc.Tag;

import elak.readinghood.backend.posts.Post;
import elak.readinghood.backend.posts.Posts;
import elak.readinghood.backend.profiles.Profile;
import elak.readinghood.backend.hashTags.HashTag;
import elak.readinghood.backend.hashTags.HashTags;
import elak.readinghood.backend.api.AppManager;
import elak.readinghood.backend.profiles.Activity;
import elak.readinghood.backend.profiles.UserProfile;
import elak.readinghood.backend.threads.Thread;
import elak.readinghood.backend.threads.Threads;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * @author Spiros
 */
public class ServerRequest {

    /**
     * This function checks if the given email exists in the database.
     *
     * @param email is the user given email
     * @return a boolean value if the given email exists in the database
     * @throws IOException Can not Connect to server
     */
    public static boolean existenceOfEmail(String email) throws IOException {

        String url = "https://readinghood.tk:8443/accounts/searchEmail?email=" + URLEncoder.encode(email, "UTF-8");
        String jsonResult = ServerConnection.sendSimpleRequest(url, "GET");
        try {
            JSONObject jsonObject = new JSONObject(jsonResult);
            String jsonEmail = jsonObject.getString("email");
            return jsonEmail.equals(email);
        } catch (JSONException J) {
            return false;
        }

    }

    /**
     * This function checks if the given password matches the emails's password.
     *
     * @param email    the user given email
     * @param password the user given password
     * @return a boolean value if the given password matches the email's password
     * @throws IOException Can not Connect to server
     */
    public static boolean checkPasswordForEmail(String email, String password) throws IOException {
        try {
            String url = "https://readinghood.tk:8443/verify";
            ServerConnection.sendAuthenticatedRequest(url, email, password, "GET");
            return true;
        } catch (IOException e) {
            if (e.getMessage().equals("401")) {
                // there is no user with this email and password
                return false;
            } else {
                e.printStackTrace();
                throw e;
            }
        }
    }

    /**
     * This function searches for profiles
     *
     * @param name    the name of the profile ot be searched
     * @param surname the surname of the profile ot be searched
     * @return the results that follows the can come for the current input
     * @throws IOException Can not Connect to server
     */
    public static ArrayList<Profile> getProfiles(String name, String surname) throws IOException {
        ArrayList<Profile> profiles = new ArrayList<>();
        String url = "https://readinghood.tk:8443/accounts/search?";
        if (!name.isEmpty()) {
            url = url + "name=" + URLEncoder.encode(name, "UTF-8");
        }
        if (!surname.isEmpty()) {
            url = url + "&surname=" + URLEncoder.encode(surname, "UTF-8");
        }
        if (name.isEmpty() && surname.isEmpty()) {
            return new ArrayList<>();
        }
        String jsonResult = ServerConnection.sendSimpleRequest(url, "GET");
        try {
            JSONArray jsonProfiles = new JSONArray(jsonResult);
            for (int i = 0; i < jsonProfiles.length(); i++) {
                profiles.add(getProfile(jsonProfiles.getJSONObject(i).toString()));
            }
            return profiles;
        } catch (JSONException J) {
            return profiles;
        }
    }

    /**
     * This function is initialized after successfully giving the correct email and password in login
     * and returns the profile of the user.
     *
     * @param email    the user given email
     * @param password the user given password
     * @return the profile of the user
     * @throws IOException Can not Connect to server
     */
    public static UserProfile getUserProfile(String email, String password) throws IOException {
        String url = "https://readinghood.tk:8443/accounts/searchEmail?email=" + URLEncoder.encode(email, "UTF-8");
        String jsonResult = ServerConnection.sendSimpleRequest(url, "GET");
        try {
            Profile profile = getProfile(new JSONObject(jsonResult).getJSONObject("profile").toString());
            return new UserProfile(profile, email, password);
        } catch (JSONException J) {
            return new UserProfile();
        }
    }

    /**
     * This function returns a thread by a certain id
     *
     * @param threadId is the id of the thread
     * @return the asked thread
     * @throws IOException Can not Connect to server
     */
    public static Thread getThreadById(int threadId) throws IOException {
        String url = "https://readinghood.tk:8443/threads/searchById?id=" + Integer.toString(threadId);
        String jsonResult = ServerConnection.sendAuthenticatedRequest(url, AppManager.getUserProfile().getEmail(), AppManager.getUserProfile().getPassword(), "GET");
        try {
            JSONObject thread = new JSONObject(jsonResult);
            //getting id
            int id = thread.getInt("id");

            //getting title

            String title = thread.getString("title");
            //getting views
            int views = thread.getInt("views");

            //getting hashTags
            HashTags tags = getHashTags(2, thread.getJSONArray("tags").toString());

            //getting posts
            Posts testPosts = getPosts(2, thread.getJSONArray("posts").toString());

            Post questionPost = testPosts.getPost(0);
            Posts answerPosts = new Posts();
            for (int j = 1; j < testPosts.size(); j++) {
                answerPosts.addPost(testPosts.getPost(j));
            }
            return new Thread(id, title, views, tags, questionPost, answerPosts);
        } catch (JSONException J) {
            return null;
        }
    }

    /**
     * This function gets the views of the user.
     *
     * @param id of the user
     * @return the reputation of the user
     * @throws IOException Can not Connect to server
     */
    public static int getRespect(int id) throws IOException {
        String url = "https://readinghood.tk:8443/profiles/votes?profile_id=" + Integer.toString(id);
        return Integer.parseInt(ServerConnection.sendAuthenticatedRequest(url, AppManager.getUserProfile().getEmail(), AppManager.getUserProfile().getPassword(), "GET"));
    }

    /**
     * This function returns the latest post of a thread.
     *
     * @param id of the thread
     * @return the latest post of a thread
     * @throws IOException Can not Connect to server
     */
    public static Post getLatestPostOfAThread(int id) throws IOException {
        Posts posts = getPosts(1, "posts/byThread?thread_id=" + Integer.toString(id));

        if (posts.size() > 1) {
            if (posts.size() == 2) {
                return posts.getPost(1);
            } else {
                return posts.getPost(posts.size() - 1);
            }
        }
        return new Post();
    }

    /**
     * This function returns the Activity of the User
     *
     * @param id is the id of the user
     * @return The Activity of the User
     * @throws IOException Can not Connect to server
     */
    public static Activity getActivity(int id) throws IOException {
        ArrayList<String> info = new ArrayList<>();
        Posts upVotedPosts = getPosts(1, "posts/upvoted?profile_id=" + Integer.toString(id));
        if (upVotedPosts.size() != 0) {
            info.add(upVotedPosts.getPost(0).getText());
        } else {
            info.add("");
        }

        Posts downVotedPosts = getPosts(1, "posts/downvoted?profile_id=" + Integer.toString(id));
        if (downVotedPosts.size() != 0) {
            info.add(downVotedPosts.getPost(0).getText());
        } else {
            info.add("");
        }

        Threads createdThreads = getThreads("threads/created?profile_id=" + Integer.toString(id));
        if (createdThreads.size() != 0) {
            info.add(createdThreads.getThread(0).getTitle());
        } else {
            info.add("");
        }
        return new Activity(info.get(0), info.get(1), info.get(2));
    }

    /**
     * This function returns the threads that it has been asked to deliver.
     *
     * @param option is the required question
     * @return the threads that it has been asked to deliver
     * @throws IOException Can not Connect to server
     */
    public static Threads getThreads(String option) throws IOException {
        ArrayList<Thread> threads = new ArrayList<>();
        String url = "https://readinghood.tk:8443/" + option;
        String jsonResult = ServerConnection.sendAuthenticatedRequest(url, AppManager.getUserProfile().getEmail(), AppManager.getUserProfile().getPassword(), "GET");
        try {
            JSONArray jsonThreads = new JSONArray(jsonResult);
            for (int i = 0; i < jsonThreads.length(); i++) {
                try {
                    JSONObject thread = jsonThreads.getJSONObject(i);

                    //getting id
                    int id = thread.getInt("id");

                    //getting title

                    String title = thread.getString("title");
                    //getting views
                    int views = thread.getInt("views");

                    //getting hashTags
                    HashTags hashTags = getHashTags(2, thread.getJSONArray("tags").toString());

                    //getting posts
                    Posts testPosts = getPosts(2, thread.getJSONArray("posts").toString());

                    Post questionPost = testPosts.getPost(0);
                    Posts answerPosts = new Posts();
                    for (int j = 1; j < testPosts.size(); j++) {
                        answerPosts.addPost(testPosts.getPost(j));
                    }
                    threads.add(new Thread(id, title, views, hashTags, questionPost, answerPosts));
                } catch (JSONException J) {
                }
            }
            return new Threads(threads);
        } catch (JSONException J) {
            return new Threads(threads);
        }
    }

    /**
     * This function returns the hashTags that has been asked to deliver.
     * if way = 1 it means that is is asked to ping to the server and get the posts.
     * if way = 2 it means that is asked to unwrap a string and get the posts.
     *
     * @param way    is that way that is asked to act
     * @param option is the specific way that is asked
     * @return the hashTags that has been asked to deliver
     * @throws IOException Can not Connect to server
     */
    public static HashTags getHashTags(int way, String option) throws IOException {
        HashSet<HashTag> hashTags = new HashSet<>();
        String jsonResult;
        if (way == 1) {
            boolean tagFullOfSpaces = option.replaceAll("\\s+", "").isEmpty();
            if (tagFullOfSpaces || option.isEmpty()) {
                return new HashTags(new HashSet<HashTag>());
            }
            String url = "https://readinghood.tk:8443/tags/" + option;
            jsonResult = ServerConnection.sendAuthenticatedRequest(url, AppManager.getUserProfile().getEmail(), AppManager.getUserProfile().getPassword(), "GET");

        } else {
            jsonResult = option;
        }
        try {
            JSONArray jsonHashTags = new JSONArray(jsonResult);
            for (int i = 0; i < jsonHashTags.length(); i++) {
                try {
                    JSONObject jsonTag = jsonHashTags.getJSONObject(i);

                    // get id
                    int id = jsonTag.getInt("id");

                    // get usages
                    int usages = jsonTag.getInt("usages");

                    // get tag name
                    String tagName = jsonTag.getString("name");

                    hashTags.add(new HashTag(id, usages, tagName));
                } catch (JSONException J) {
                }
            }
            return new HashTags(hashTags);
        } catch (JSONException J) {
            return new HashTags(hashTags);
        }
    }

    /**
     * This Function returns the posts that has been asked to deliver.
     * if way = 1 it means that is is asked to ping to the server and get the posts.
     * if way = 2 it means that is asked to unwrap a string and get the posts.
     *
     * @param way    is that way that is asked to act
     * @param option is the string that will unwrap of the ping that it is going to make
     * @return the posts that has been asked to deliver
     * @throws IOException Can not Connect to server
     */
    private static Posts getPosts(int way, String option) throws IOException {
        String jsonResult;
        ArrayList<Post> posts = new ArrayList<>();
        if (way == 1) {
            String url = "https://readinghood.tk:8443/" + option;
            jsonResult = ServerConnection.sendAuthenticatedRequest(url, AppManager.getUserProfile().getEmail(), AppManager.getUserProfile().getPassword(), "GET");

        } else {
            jsonResult = option;
        }
        try {
            JSONArray jsonPosts = new JSONArray(jsonResult);
            for (int i = 0; i < jsonPosts.length(); i++) {
                JSONObject jsonPost = jsonPosts.getJSONObject(i);
                // id of post
                int id = 0;
                try {
                    id = jsonPost.getInt("id");
                } catch (JSONException J) {
                }

                // text of post
                String text = "";
                try {
                    text = jsonPost.getString("text");
                    if (text == null) {
                        text = "";
                    }
                } catch (JSONException J) {
                }

                // number of votes of post
                int numberOfVotes = 0;
                try {
                    numberOfVotes = jsonPost.getInt("numberOfVotes");
                } catch (JSONException J) {
                }

                // author of the post
                Profile author = new Profile();
                try {
                    author = getProfile(jsonPost.getJSONObject("author").toString());
                } catch (JSONException J) {
                }

                // upVoters of the post
                JSONArray jsonUpVoters = new JSONArray();
                try {
                    jsonUpVoters = jsonPost.getJSONArray("upvoters");
                } catch (JSONException J) {
                }

                ArrayList<Profile> upVoters = new ArrayList<>();
                for (int j = 0; j < jsonUpVoters.length(); j++) {
                    upVoters.add(getProfile(jsonUpVoters.get(j).toString()));
                }

                // downVoters of the post
                JSONArray jsonDownVoters = new JSONArray();
                try {
                    jsonDownVoters = jsonPost.getJSONArray("downvoters");
                } catch (JSONException J) {
                }

                ArrayList<Profile> downVoters = new ArrayList<>();
                for (int j = 0; j < jsonDownVoters.length(); j++) {
                    downVoters.add(getProfile(jsonDownVoters.get(j).toString()));
                }
                posts.add(new Post(id, numberOfVotes, text, author, upVoters, downVoters));
            }
            return new Posts(posts);
        } catch (JSONException J) {
            return new Posts(posts);
        }
    }

    /**
     * This Function unwraps a jsonString Profile and returns the profile object.
     *
     * @param option the json profile that must be unwrapped
     * @return the extracted profile
     */
    private static Profile getProfile(String option) {
        try {
            JSONObject jsonProfile = new JSONObject(option);

            // the id of the profile
            int jsonId = 0;
            try {
                jsonId = jsonProfile.getInt("id");
            } catch (JSONException J) {
            }

            // the name of the profile
            String jsonName = "";
            try {
                jsonName = jsonProfile.getString("name");
                if (jsonName == null) {
                    jsonName = "";
                }
            } catch (JSONException J) {
            }

            // the surname of the profile
            String jsonSurname = "";
            try {
                jsonSurname = jsonProfile.getString("surname");
                if (jsonSurname == null) {
                    jsonSurname = "";
                }
            } catch (JSONException J) {
            }

            // the username of the profile
            String jsonUsername = "";
            try {
                jsonUsername = jsonProfile.getString("username");
                if (jsonUsername == null) {
                    jsonUsername = "";
                }
            } catch (JSONException J) {

            }

            // the department of the profile
            String jsonDepartment = "";
            try {
                jsonDepartment = jsonProfile.getString("department");
                if (jsonDepartment == null) {
                    jsonDepartment = "";
                }
            } catch (JSONException J) {
            }

            // the reputation of the profile
            int jasonReputation = 0;
            try {
                jasonReputation = jsonProfile.getInt("votes");
            } catch (JSONException J) {
            }

            return new Profile(jsonId, jasonReputation, jsonUsername, jsonName, jsonSurname, jsonDepartment);
        } catch (JSONException J) {
            return new Profile();
        }
    }
}