package elak.readinghood.backend.tags;

/**
 * @author Spiros
 */
public class Tag {
    private int id, usages;
    private String name;

    /**
     * trivial constructor
     */
    public Tag() {

    }

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

    /**
     * This function checks if 2 tags are equal based on their name
     *
     * @param tag is the compared tag
     * @return a boolean value which indicated if the 2 compared tags are equal based on their name
     */
    @Override
    public boolean equals(Object tag) {
        if (tag == null) {
            return false;
        }
        if (!Tag.class.isAssignableFrom(tag.getClass())) {
            return false;
        }

        final Tag other = (Tag) tag;
        return this.name.equals(other.name);
    }
}