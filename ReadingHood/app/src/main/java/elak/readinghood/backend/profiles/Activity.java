package elak.readinghood.backend.profiles;

/**
 * @author Spiros
 */
public class Activity {
    // you need a thumbs up icon here and this string next to it
    private String latestUpVotedPostText;

    // you need a thumbs down icon here and this string next to it
    private String latestDownVotedPostText;

    // you need a post icon here and this string next to it
    private String latestCreatedPostText;

    // you need a thread icon here and this string next to it
    private String latestCreatedThreadTitle;


    /**
     * trivial constructor
     */
    public Activity() {
        latestUpVotedPostText = "";
        latestDownVotedPostText = "";
        latestCreatedPostText = "";
        latestCreatedThreadTitle = "";
    }

    /**
     * trivial constructor
     *
     * @param latestUpVotedPostText    is the Text from the latest upVoted Post
     * @param latestDownVotedPostText  is the Text from the latest downVoted Post
     * @param latestCreatedPostText    is the Text from the latest created Post
     * @param latestCreatedThreadTitle is the Title of the lastest created Thread
     */
    public Activity(String latestUpVotedPostText, String latestDownVotedPostText, String latestCreatedPostText, String latestCreatedThreadTitle) {
        this.latestUpVotedPostText = latestUpVotedPostText;
        this.latestDownVotedPostText = latestDownVotedPostText;
        this.latestCreatedPostText = latestCreatedThreadTitle;
        this.latestCreatedThreadTitle = latestCreatedThreadTitle;
    }

    /**
     * @return the Text from the latest upVoted Post
     */
    public String getLatestUpVotedPostText() {
        return latestUpVotedPostText;
    }

    /**
     * @return the Text from the latest downVoted Post
     */
    public String getLatestDownVotedPostText() {
        return latestDownVotedPostText;
    }

    /**
     * @return the Text from the latest created Post
     */
    public String getLatestCreatedPostText() {
        return latestCreatedPostText;
    }

    /**
     * @return the Title of the lastest created Thread
     */
    public String getLatestCreatedThreadTitle() {
        return latestCreatedThreadTitle;
    }
}