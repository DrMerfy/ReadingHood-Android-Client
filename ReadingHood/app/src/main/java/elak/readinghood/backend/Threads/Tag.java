package elak.readinghood.backend.Threads;

/**
 * @author Spiros
 */
public class Tag {
    private int id, usages;
    private String name;

    /**
     * @param id     is the id of the of tag
     * @param usages is the usages of the tag
     * @param name   is the name of the tag
     */
    public Tag(int id, int usages, String name) {
        this.id = id;
        this.usages = usages;
        this.name = name;
    }

    /**
     * @return the of the tag
     */
    public int getId() {
        return id;
    }

    /**
     * @return the usages of the tag
     */
    public int getUsages() {
        return usages;
    }

    /**
     * @return the name of the tag
     */
    public String getName() {
        return name;
    }
}
