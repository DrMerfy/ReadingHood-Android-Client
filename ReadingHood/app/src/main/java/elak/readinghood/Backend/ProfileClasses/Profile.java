package elak.readinghood.backend.ProfileClasses;

/**
 * @author Spiros
 */
public class Profile {
    protected int id;
    protected String name, surname, department;

    public Profile(){

    }
    /**
     * trivial constructor
     * @param id the id of the user
     * @param name the user given name
     * @param surname the user given surname
     * @param department the user given department
     */
    public Profile(int id, String name, String surname, String department) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.department = department;
    }

    /**
     * @return the id of the UserProfile
     */
    public int getId() {
        return id;
    }

    /**
     * @return the name of the user
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return the surname of the user
     */
    public String getSurname() {
        return this.surname;
    }

    /**
     * @return the department of the user
     */
    public String getDepartment() {
        return this.department;
    }
}