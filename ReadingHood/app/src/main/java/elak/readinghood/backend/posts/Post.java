package elak.readinghood.backend.posts;

import elak.readinghood.backend.api.AppManager;
import elak.readinghood.backend.profiles.Profile;
import elak.readinghood.backend.server.ServerUpdate;

import java.io.IOException;
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
     */
    public Post() {

    }

    /**
     * trivial constructor
     *
     * @param id            is the id of the post
     * @param numberOfVotes is the number of votes
     * @param text          is the text of the post
     * @param author        is the author of the post
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
     * YOU NEVER USE THIS FUNCTION as front end developer.
     *
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
     * Don't Use this function yet.
     *
     * @return the the upVoters of the post
     */
    private ArrayList<Profile> getUpVoters() {
        return upVoters;
    }

    /**
     * Don't Use this function yet.
     *
     * @return the downVoters of the post
     */
    private ArrayList<Profile> getDownVoters() {
        return downVoters;
    }

    /**
     * This function tells the user if he can upVote this post.
     *
     * @return a boolean value which indicates if the user will have the ability to press the upVote button
     */
    public boolean canYouUpVote() {
        if (AppManager.getUserProfile().equals(author)) {
            return false;
        }

        for (Profile profile : upVoters) {
            if (AppManager.getUserProfile().equals(profile)) {
                return false;
            }
        }
        return true;
    }

    /**
     * This function tells the user if he can downVote this post.
     *
     * @return a boolean value which indicates if the user will have the ability to press the downVote button
     */
    public boolean canYouDownVote() {
        if (AppManager.getUserProfile().equals(author)) {
            return false;
        }

        for (Profile profile : downVoters) {
            if (AppManager.getUserProfile().equals(profile)) {
                return false;
            }
        }
        return true;
    }

    /**
     * THIS FUNCTION IS ONLY USED IF THE USER canUpVote this post. It upVotes this post
     *
     * @throws IOException Can not Connect to server
     */
    public void upVoteThisPost() throws IOException {
        ServerUpdate.upVotePost(id);
        upVoters.add(AppManager.getUserProfile());
        refreshNumberOfVotes();
    }

    /**
     * THIS FUNCTION IS ONLY USED IF THE USER canDownVote this post. It downVotes this post
     *
     * @throws IOException Can not Connect to server
     */
    public void downVoteThisPost() throws IOException {
        ServerUpdate.downVotePost(id);
        downVoters.add(AppManager.getUserProfile());
        refreshNumberOfVotes();
    }

    /**
     * This function refreshed the number of votes of a user
     */
    private void refreshNumberOfVotes() {
        this.numberOfVotes = upVoters.size() - downVoters.size();
    }
}