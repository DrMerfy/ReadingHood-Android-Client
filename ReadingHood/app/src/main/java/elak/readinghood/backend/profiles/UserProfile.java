package elak.readinghood.backend.profiles;

import elak.readinghood.backend.server.ServerUpdate;

import java.io.IOException;

/**
 * @author Spiros
 */
public class UserProfile extends Profile {

    private String email, password;

    /**
     * trivial constructor
     */
    public UserProfile() {

    }

    /**
     * trivial contructor
     *
     * @param profile  the profile of the user
     * @param email    the user given email
     * @param password the user given password
     */
    public UserProfile(Profile profile, String email, String password) {
        this.id = profile.id;
        this.reputation = profile.reputation;
        this.username = profile.username;
        this.name = profile.name;
        this.surname = profile.surname;
        this.department = profile.department;
        this.email = email;
        this.password = password;
    }

    /**
     * YOU NEVER USE THIS FUNCTION as front end developer.
     *
     * @return the id of the UserProfile
     */
    public int getId() {
        return id;
    }

    /**
     * YOU NEVER USE THIS FUNCTION as front end developer.
     *
     * @return the email of the user
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * YOU NEVER USE THIS FUNCTION as front end developer.
     *
     * @return the password of the user
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * This function edits the name of the user.
     * <p>
     * Error0 = "Success" (which means that the name was changed successfully)
     * Error404 = "Fill every field" (which means that the user must fill all the given fields)
     *
     * @param newName the user given newName
     * @return an error text
     * @throws IOException Can not Connect to server
     */
    public String editName(String newName) throws IOException {
        if (newName.isEmpty()) {
            return "Fill every field";
        }

        ServerUpdate.editName(newName);
        this.name = newName;
        return "Success";
    }

    /**
     * This function edits the surname of the user.
     * <p>
     * Error0 = "Success" (which means that the surname was changed successfully)
     * Error404 = "Fill every field" (which means that the user must fill all the given fields)
     *
     * @param newSurname the user given newName
     * @return an error text
     * @throws IOException Can not Connect to server
     */
    public String editSurname(String newSurname) throws IOException {
        if (newSurname.isEmpty()) {
            return "Fill every field";
        }
        ServerUpdate.editSurname(newSurname);
        this.surname = newSurname;
        return "Success";
    }
}