package elak.readinghood.backend.posts;

import java.util.ArrayList;

/**
 * @author Spiros
 */
public class Posts {
    private ArrayList<Post> posts;

    /**
     * trivial constructor
     */
    public Posts() {
        posts = new ArrayList<>();
    }

    /**
     * trivial constructor
     *
     * @param posts are the posts
     */
    public Posts(ArrayList<Post> posts) {
        this.posts = posts;
    }

    /**
     * This function is used to get post that you want.
     *
     * @param number the number of the post
     * @return the post that you want
     */
    public Post getPost(int number) {
        return posts.get(number);
    }

    /**
     * THIS FUNCTION IS NEVER USER FROM FRONT END. It adds a post to the posts.
     */
    public void addPost(Post post) {
        posts.add(post);
    }

    /**
     * @return the amount of posts.
     */
    public int size() {
        return posts.size();
    }
}