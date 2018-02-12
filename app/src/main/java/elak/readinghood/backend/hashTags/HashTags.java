package elak.readinghood.backend.hashTags;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author Spiros
 */
public class HashTags implements Serializable {
    private HashSet<HashTag> hashTags;

    public HashTags() {
        hashTags = new HashSet<>();
    }

    public HashTags(HashSet<HashTag> hashTags) {
        this.hashTags = hashTags;
    }

    /**
     * This function returns the asked HashTag.
     *
     * @param number of the wanted HashTag
     * @return the wanted HashTag
     */
    public HashTag getHashTag(int number) {
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
    public boolean isEmpty() {
        return hashTags.isEmpty();
    }

    /**
     * This function convert all the hashTag names into a string
     *
     * @return all the hashTag names as a string
     */
    public String toString() {
        String string = "";
        List<HashTag> tagsList = new ArrayList<>();
        tagsList.addAll(hashTags);

        for (int i = 0; i < tagsList.size(); i++) {
            if (i != tagsList.size() - 1) {
                string += tagsList.get(i).getName() + ", ";
            } else {
                string += tagsList.get(i).getName();
            }
        }
        return string;
    }

    /**
     * @return this function returns an ArrayList that contains the tags
     */
    public ArrayList<HashTag> getListOfHashTags() {
        return new ArrayList<>(hashTags);
    }
}