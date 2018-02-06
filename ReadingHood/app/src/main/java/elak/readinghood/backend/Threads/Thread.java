package elak.readinghood.backend.Threads;

import elak.readinghood.backend.ProfileClasses.Profile;
import elak.readinghood.backend.ProfileClasses.UserProfile;

import java.util.ArrayList;

/**
 * @author Spiros
 */
public class Thread {
    private Profile profile;
    private int id, views;
    private String title;
    private ArrayList<Tag> tags;
    private ArrayList<Post> posts;

    /**
     * When you initialize a Thread, you need The title, the tags and the posts of it
     *
     * @param id    is the id of the thread
     * @param title is the title of the thread
     * @param tags  are the tags of the thread
     * @param posts are the posts of the thread
     */
    public Thread(Profile profile, int id, String title, ArrayList<Tag> tags, ArrayList<Post> posts) {
        this.profile = profile;
        this.id = id;
        this.title = title;
        this.tags = tags;
        this.posts = posts;
    }

    /**
     * @return the user of the creator of the thread
     */
    public Profile getThreadCreatorProfile() {
        return profile;
    }

    /**
     * @return the id of the thread
     */
    public int getId() {
        return id;
    }

    /**
     * @return the views of the thread
     */
    public int getViews() {
        return views;
    }

    /**
     * @return the title of the user
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return the tags of the thread
     */
    public ArrayList<Tag> getTags() {
        return tags;
    }

    /**
     * @return the posts of the thread
     */
    public ArrayList<Post> getPosts() {
        return posts;
    }
}
