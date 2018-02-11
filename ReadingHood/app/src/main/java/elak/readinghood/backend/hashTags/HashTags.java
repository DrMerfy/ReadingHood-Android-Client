package elak.readinghood.backend.hashTags;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * @author Spiros
 */
public class HashTags {
    private HashSet<HashTag> hashTags;

    public HashTags() {
        hashTags = new HashSet<>();
    }

    public HashTags(HashSet<HashTag> hashTags) {
        this.hashTags = hashTags;
    }

    /**
     * This function returnS the asked HashTag.
     *
     * @param number of the wanted HashTag
     * @return the wanted HashTag
     */
    public HashTag getTag(int number) {
        return new ArrayList<>(hashTags).get(number);
    }

    /**
     * @return the size of the HashTags that you have
     */
    public int size() {
        return hashTags.size();
    }

    /**
     * @return a boolean value which indicates if the HashTags are empty
     */
    public boolean isEmpty() {return hashTags.isEmpty();}
}