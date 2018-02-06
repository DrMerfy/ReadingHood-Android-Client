package elak.readinghood.backend.Threads;

import elak.readinghood.backend.ProfileClasses.Profile;

import java.util.ArrayList;

/**
 * @author Spiros
 */
public class Post {
    private int id;
    private String text;
    private ArrayList<Profile> upVoters;
    private ArrayList<Profile> downVoters;

    /**
     * trivial constructor
     */
    public Post(int id, String text, ArrayList<Profile> upVoters, ArrayList<Profile> downVoters) {
        this.id = id;
        this.text = text;
        this.upVoters = upVoters;
        this.downVoters = downVoters;
    }
}
