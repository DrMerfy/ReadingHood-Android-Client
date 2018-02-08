package elak.readinghood.backend.profiles;

import elak.readinghood.backend.server.ServerRequest;

/**
 * @author Spiros
 */
public class Profile {
    protected int id, reputation;
    protected String username, name, surname, department;
    protected Activity activity;

    /**
     * trivial constructor
     */
    public Profile() {

    }

    /**
     * trivial constructor
     *
     * @param id         the id of the user
     * @param reputation is the reputation of the user
     * @param username   the username of the user
     * @param name       the user given name
     * @param surname    the user given surname
     * @param department the user given department
     */
    public Profile(int id, int reputation, String username, String name, String surname, String department) {
        this.id = id;
        this.reputation = reputation;
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.department = department;
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
     * @return the username of the user
     */
    public String getUsername() {
        return username;
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

    /**
     * This function returns the reputation of the User.
     *
     * @return the reputation of the User
     */
    public int getReputation() {
        this.reputation = ServerRequest.getReputation(id);
        return reputation;
    }

    /**
     * This functions sets the Activity of the user.
     * You use the function every time you wanna se the profile of somebody.
     */
    public void setActivity() {
        activity = ServerRequest.getActivity(id);
    }

    /**
     * This function lets you get the activity of the user.
     *
     * @return the activity of the user
     */
    public Activity getActivity() {
        return activity;
    }

    @Override
    public boolean equals(Object profile) {
        if (profile == null) {
            System.out.println("makis");
            return false;
        }
        if (!Profile.class.isAssignableFrom(profile.getClass())) {
            System.out.println("takis");
            return false;
        }

        final Profile other = (Profile) profile;

        if (this.getId() != other.getId()) {
            //System.out.println("katsavakis");
            return false;
        }

        return true;
    }
}