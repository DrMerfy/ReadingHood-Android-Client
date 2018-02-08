package elak.readinghood.backend.tags;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * @author Spiros
 */
public class Tags {
    private HashSet<Tag> tags;

    public Tags() {
        tags = new HashSet<>();
    }

    public Tags(HashSet<Tag> tags) {
        this.tags = tags;
    }

    /**
     * This function returnS the asked tag.
     *
     * @param number of the wanted Tag
     * @return the wanted Tag
     */
    public Tag getTag(int number) {
        return new ArrayList<>(tags).get(number);
    }

    /**
     * @return the size of the tags that you have
     */
    public int size() {
        return tags.size();
    }

    /**
     * @return a boolean value which indicates if the tags are empty
     */
    public boolean isEmpty() {return tags.isEmpty();}
}