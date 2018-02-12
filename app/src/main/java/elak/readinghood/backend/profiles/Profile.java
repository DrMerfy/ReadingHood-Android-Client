package elak.readinghood.backend.profiles;

import elak.readinghood.backend.server.ServerRequest;
import elak.readinghood.backend.threads.Threads;

import java.io.IOException;
import java.io.Serializable;

/**
 * @author Spiros
 */
public class Profile implements Serializable {
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
     * This function returns the respect of the User.
     *
     * @return the respect of the User
     * @throws IOException Can not Connect to server
     */
    public int getRespect() throws IOException {
        this.reputation = ServerRequest.getRespect(id);
        return reputation;
    }

    /**
     * This function returns the threads that have been created from this profile.
     * Possible place to be used : My profile.
     *
     * @return the threads of the connected user
     * @throws IOException Can not Connect to server
     */
    public Threads getThreadsCreatedByThisProfile() throws IOException {
        return ServerRequest.getThreads("threads/created?profile_id=" + Integer.toString(id));
    }

    /**
     * This function sets the Activity of the user.
     * You use the function every time you wanna see the profile of somebody.
     *
     * @throws IOException Can not Connect to server
     */
    public void setActivity() throws IOException {
        activity = ServerRequest.getActivity(id);
    }

    /**
     * This function is only USED if you have set the activity earlier
     * This function lets you get the activity of the user.
     *
     * @return the activity of the user
     */
    public Activity getActivity() {
        return activity;
    }

    /**
     * This function checks if 2 profiles are equal based on their id
     *
     * @param profile is the compared profile
     * @return a boolean value which indicated if the 2 compared profiles are equal based on their id
     */
    @Override
    public boolean equals(Object profile) {
        if (profile == null) {
            return false;
        }
        if (!Profile.class.isAssignableFrom(profile.getClass())) {
            return false;
        }

        final Profile other = (Profile) profile;
        return this.id == other.id;
    }
}