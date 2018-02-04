import elak.readinghood.Backend.AccountManager;

public class Main {
    public static void main(String[] args) {
        AccountManager accountManager = new AccountManager();
        System.out.println(accountManager.registration(1,"takis4@takis.com","takis", "", "", ""));
        System.out.println(accountManager.registration(2,"","", "Informatics", "", ""));
        System.out.println(accountManager.registration(3,"","", "", "a1234567", "a1234567"));
        System.out.println(accountManager.createUser());
        System.out.println(accountManager.login("takis4@takis.com", "a1234567"));

        /*
        try {

            // Authenticated Request Example
            String url = "https://readinghood.tk:8443/verify";
            String email = "a@a.a";
            String password = "a";
            String reqResponse = sendAuthenticatedRequest(url, email, password, "GET");
            System.out.println("Response from '" + url + "':\n" + reqResponse);

            // Simple Request Example
            url = "https://readinghood.tk:8443/";
            reqResponse = sendSimpleRequest(url, "GET");
            System.out.println("Response from '" + url + "':\n" + reqResponse);

        } catch (IOException e) {
            e.printStackTrace();
        }
        */
    }
}
