package elak.readinghood.backend.api;

import elak.readinghood.backend.profiles.UserProfile;
import elak.readinghood.backend.server.ServerRequest;
import elak.readinghood.backend.server.ServerUpdate;

import java.io.IOException;
import java.util.regex.Pattern;

/**
 * @author Spiros
 */
public class StartUpManager {
    // user given data
    private String email, username, password, department;

    /**
     * trivial constructor
     */
    protected StartUpManager() {
        this.email = "";
        this.username = "";
        this.password = "";
        this.department = "";
    }

    /**
     * This function verifies that the user given email exists in the database and that the given
     * password is the password of the user, sets the variables email and password if those are
     * correct and returns an error text based on the result of the given variables.
     * <p>
     * Error0 = "Success" (which means that the user gave correct input)
     * Error1 = "Wrong Email or Password" (which means that the email does not exist in the database)
     * Error2 = "Wrong Password" (which means that the given password does not match the email password)
     * Error404 = "Fill every field" (which means that the user must fill all the given fields)
     * <p>
     * You NEVER GIVE NULL VALUES. ONLY "".
     *
     * @param email    the user given email
     * @param password the user given password
     * @return the error text
     * @throws IOException Can not Connect to server
     */
    public String login(String email, String password) throws IOException {
        if (email.isEmpty() || password.isEmpty()) {
            return "Fill every field";
        }

        boolean existenceOfEmail = ServerRequest.existenceOfEmail(email.toLowerCase());

        if (existenceOfEmail) {
            boolean correctPassword = ServerRequest.checkPasswordForEmail(email.toLowerCase(), password);

            if (correctPassword) {
                this.email = email.toLowerCase();
                this.password = password;

                return "Success";
            } else {
                return "Wrong Password";
            }
        } else {
            return "Wrong Email or Password";
        }
    }

    /**
     * This function creates a user and it is initialized ONLY after finishing the assignment of the variables
     * of the user in registration.
     *
     * @throws IOException Can not Connect to server
     */
    public void createUserProfile() throws IOException {
        ServerUpdate.createUser(email, username, password, department);
    }

    /**
     * This function is called ONLY after succeeding log in and it returns the profile of the user.
     *
     * @return the profile of the user
     */
    public UserProfile getUserProfile() throws IOException {
        return ServerRequest.getUserProfile(email, password);
    }

    /**
     * This function sets the email and the username of the user if the given email is unique
     * and returns an error text based on the result of the given variables.
     * The username must not contain spaces.
     * <p>
     * Error0 = "Success" (which means that the given email is an email and it is unique and the username does not contains spaces)
     * Error1 = "This email already exists"
     * Error2 = "This isn't and email"
     * Error3 = "This username contains spaces"
     * Error404 = "Fill every field" (which means that the user must fill all the given fields)
     * <p>
     * You NEVER GIVE NULL VALUES. ONLY "".
     *
     * @param email    is the user given email
     * @param username is the user given username
     * @return the error text
     */
    public String registrationSetEMailAndUsername(String email, String username) throws IOException {
        if (email.isEmpty() || username.isEmpty()) {
            return "Fill every field";
        }

        Pattern VALID_EMAIL_ADDRESS_REGEX =
                Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{1,6}$", Pattern.CASE_INSENSITIVE);
        boolean correctEmailFormat = VALID_EMAIL_ADDRESS_REGEX.matcher(email.toLowerCase()).find();

        if (correctEmailFormat) {
            boolean existenceOfEmail = ServerRequest.existenceOfEmail(email);

            if (!existenceOfEmail) {
                boolean usernameContainsSpaces = username.contains(" ");

                if (!usernameContainsSpaces) {
                    this.email = email.toLowerCase();
                    this.username = username;
                    return "Success";
                } else {
                    return "This username contains spaces";
                }
            } else {
                return "This email already exists";
            }
        } else {
            return "This isn't and email";
        }
    }

    /**
     * This function is used ONLY AFTER "Success" message from registrationSetEMailAndUsername()
     * You NEVER GIVE NULL VALUES. ONLY "".
     *
     * @param department is the user given department
     */
    public void registrationSetDepartment(String department) {
        if (!department.isEmpty()) {
            this.department = department;
        }
    }

    /**
     * This function is used ONLY AFTER "Success" message from  registrationSetDepartment()
     * and sets the password of the user if the given password meets the format
     * (check the function correctPasswordFormat) of it and returns an error text based
     * on the result of the given variables.
     * <p>
     * Error0 = "Success" (which means that the password meets the password's requirements and
     * the rePassword matches with the password)
     * Error1 = "The password's requirements aren't met"
     * Error2 = "The rePassword doesn't not match"
     * Error404 = "Fill every field" (which means that the user must fill all the given fields)
     * <p>
     * You NEVER GIVE NULL VALUES. ONLY "".
     *
     * @param password   is the user given password
     * @param rePassword is the user given rePassword
     * @return the error text
     */
    public String registrationSetPasswordAndRePassword(String password, String rePassword) {
        if (password.isEmpty() || rePassword.isEmpty()) {
            return "Fill every field";
        }

        if (correctPasswordFormat(password)) {
            boolean passwordMatchRePassword = password.equals(rePassword);

            if (passwordMatchRePassword) {
                this.password = password;
                return "Success";
            } else {
                return "The rePassword doesn't not match";
            }
        } else {
            return "The password's requirements aren't met";
        }
    }

    /**
     * We assume that the password must contains at least one character and it must not contain spaces
     *
     * @return a boolean values which indicates if the password's format is correct
     */
    private boolean correctPasswordFormat(String password) {
        boolean enoughCharacters = (password.length() >= 1);
        boolean doesNotContainSpaces = !password.contains(" ");
        return enoughCharacters && doesNotContainSpaces;
    }
}