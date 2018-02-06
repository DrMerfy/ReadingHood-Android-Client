package elak.readinghood.backend;

import elak.readinghood.backend.Api.AppManager;

import java.util.ArrayList;

public class Main {
    /*
     * initialize this line in the stating class and then create a static function getAppmanager
     * or you set this variable as public to have access
     */
    private static AppManager appManager = new AppManager();


    public static void main(String[] args) {
        //registration example
        //appManager.getStartUpManager().registration(1, "melissourgos@hotmail.com", "georgeMeli", null, null, null);
        //appManager.getStartUpManager().registration(2, null, null, "Informatics", null, null);
        //appManager.getStartUpManager().registration(3, null, null, null, "a1234567", "a1234567");
        //appManager.getStartUpManager().createUser();

        // example of login
        appManager.getStartUpManager().login("melissourgos@hotmail.com", "a1234567");
        appManager.setUserProfile();

        // exmample of getting info of user and edit name and surname example
        //System.out.println(appManager.getUserProfile().getId());
        //System.out.println(appManager.getUserProfile().getEmail());
        //System.out.println(appManager.getUserProfile().getPassword());
        //System.out.println(appManager.getUserProfile().getUsername());
        //appManager.getUserProfile().editName("George");
        //System.out.println(appManager.getUserProfile().getName());
        //appManager.getUserProfile().editSurname("Melissourgos");
        //System.out.println(appManager.getUserProfile().getSurname());
        //System.out.println(appManager.getUserProfile().getDepartment());

        // example of the creating of a thread
        //ArrayList<String> tags = new ArrayList<>();
        //tags.add("takis");
        //System.out.println(appManager.createThread("katsavakis", "takis man", tags));

        // NOT working yet examples of getting threads
        // System.out.println(appManager.getPopularThreadsOfNewsFeed());
        // System.out.println(appManager.getRecentThreadsOfNewsFeed());


    }
}
