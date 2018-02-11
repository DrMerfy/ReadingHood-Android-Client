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
}