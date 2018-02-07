package elak.readinghood.backend.threadsClasses;

import elak.readinghood.backend.profileClasses.Profile;

import java.util.ArrayList;

/**
 * @author Spiros
 */
public class Post {
    private int id, numberOfVotes;
    private String text;
    private Profile author;
    private ArrayList<Profile> upVoters;
    private ArrayList<Profile> downVoters;


    /**
     * trivial constructor
     *
     * @param id            is the id of the post
     * @param numberOfVotes is the number of votes
     * @param text          is the text of the post
     * @param author        is the authos of the post
     * @param upVoters      are the upVoters users
     * @param downVoters    are the downVoters users
     */
    public Post(int id, int numberOfVotes, String text, Profile author, ArrayList<Profile> upVoters, ArrayList<Profile> downVoters) {
        this.id = id;
        this.numberOfVotes = numberOfVotes;
        this.text = text;
        this.author = author;
        this.upVoters = upVoters;
        this.downVoters = downVoters;
    }

    /**
     * @return the id of the post
     */
    public int getId() {
        return id;
    }

    /**
     * @return the number of votes of the post
     */
    public int getNumberOfVotes() {
        return numberOfVotes;
    }

    /**
     * @return the text of the post
     */
    public String getText() {
        return text;
    }

    /**
     * @return the author of the post
     */
    public Profile getAuthor() {
        return author;
    }

    /**
     * @return the the upVoters of the post
     */
    public ArrayList<Profile> getUpVoters() {
        return upVoters;
    }

    /**
     * @return the downVoters of the post
     */
    public ArrayList<Profile> getDownVoters() {
        return downVoters;
    }
}
