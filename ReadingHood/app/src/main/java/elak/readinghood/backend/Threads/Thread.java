package elak.readinghood.backend.Threads;

import elak.readinghood.backend.ProfileClasses.Profile;

import java.util.ArrayList;

/**
 * @author Spiros
 */
public class Thread {
    private Profile creator;
    private int id, views;
    private String title;
    private ArrayList<Tag> tags;
    private Post questionPost;
    private ArrayList<Post> posts;

    /**
     * When you initialize a Thread, you need The title, the tags and the posts of it
     *
     * @param creator      is the creator of the thread
     * @param id           is the id of the thread
     * @param title        is the title of the thread
     * @param views        are the amount of views of the thread
     * @param tags         are the tags of the thread
     * @param questionPost the question post of the thread
     * @param posts        are the posts of the thread
     */
    public Thread(Profile creator, int id, String title, int views, ArrayList<Tag> tags, Post questionPost, ArrayList<Post> posts) {
        this.creator = creator;
        this.id = id;
        this.views = views;
        this.title = title;
        this.tags = tags;
        this.questionPost = questionPost;
        this.posts = posts;
    }

    /**
     * @return the user of the creator of the thread
     */
    public Profile getThreadCreatorProfile() {
        return creator;
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
     * @return the title of the thread
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
     * @return the question post of the creator the thread
     */
    public Post getQuestionPost() {
        return questionPost;
    }

    /**
     * @return the posts of the thread
     */
    public ArrayList<Post> getPosts() {
        return posts;
    }
}