package elak.readinghood.Backend.ProfileClasses;

/**
 * @author Spiros, Nasos
 */
public class Profile {
    protected String email, username, password, name, surname, department;

    public Profile(){

    }
    /**
     * trivial contructor
     * @param email the user given email
     * @param username the user given username
     * @param password the user given password
     * @param name the user given name
     * @param surname the user given surname
     * @param department the user given department
     */
    public Profile(String email, String username, String password, String name, String surname, String department) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.department = department;
    }

    /**
     * @return the email of the user
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * @return the username of the user
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * @return the password of the user
     */
    public String getPassword() {
        return this.password;
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