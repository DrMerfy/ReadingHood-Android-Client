package elak.readinghood.Backend.ProfileClasses;

/**
 * @author Spiros, Nasos
 */
public class UserProfile extends Profile {

    private String id;

    /**
     * trivial contructor
     * @param email the user given email
     * @param username the user given username
     * @param password the user given password
     * @param name the user given name
     * @param surname the user given surname
     * @param department the user given department
     */
    UserProfile(String id, String email, String username, String password, String name, String surname, String department) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.department = department;
    }

    /**
     * @return the id of the UserProfile
     */
    public int getId() {
        return 0;
    }
}