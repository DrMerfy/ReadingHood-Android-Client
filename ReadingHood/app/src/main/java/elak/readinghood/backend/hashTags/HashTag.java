package elak.readinghood.backend.hashTags;

/**
 * @author Spiros
 */
public class HashTag {
    private int id, usages;
    private String name;

    /**
     * trivial constructor
     */
    public HashTag() {

    }

    /**
     * @param id     is the id of the of HashTag
     * @param usages is the usages of the HashTag
     * @param name   is the name of the HashTag
     */
    public HashTag(int id, int usages, String name) {
        this.id = id;
        this.usages = usages;
        this.name = name;
    }

    /**
     * @return the of the HashTag
     */
    public int getId() {
        return id;
    }

    /**
     * @return the usages of the HashTag
     */
    public int getUsages() {
        return usages;
    }

    /**
     * @return the name of the HashTag
     */
    public String getName() {
        return name;
    }

    /**
     * This function checks if 2 HashTags are equal based on their name
     *
     * @param hashTag is the compared HashTag
     * @return a boolean value which indicated if the 2 compared HashTags are equal based on their name
     */
    @Override
    public boolean equals(Object hashTag) {
        if (hashTag == null) {
            return false;
        }
        if (!HashTag.class.isAssignableFrom(hashTag.getClass())) {
            return false;
        }

        final HashTag other = (HashTag) hashTag;
        return this.name.equals(other.name);
    }
}