package elak.readinghood.backend;

import elak.readinghood.backend.api.AppManager;
import elak.readinghood.backend.threadsClasses.Tag;

import java.util.ArrayList;

public class Main {
    /*
     * initialize this line in the stating class and then create a static function getAppmanager
     * or you set this variable as public to have access
     */
    private static AppManager appManager = new AppManager();


    public static void main(String[] args) {
        // registration example
        // appManager.getStartUpManager().registration(1, "melissourgos@hotmail.com", "georgeMeli", null, null, null);
        // appManager.getStartUpManager().registration(2, null, null, "Informatics", null, null);
        // appManager.getStartUpManager().registration(3, null, null, null, "a1234567", "a1234567");
        // appManager.getStartUpManager().createUser();

        // example of login
        appManager.getStartUpManager().login("melissourgos@hotmail.com", "a1234567");
        appManager.setUserProfile();

        // example of getting info of user and edit name and surname example
        // System.out.println(appManager.getUserProfile().getId());
        // System.out.println(appManager.getUserProfile().getEmail());
        // System.out.println(appManager.getUserProfile().getPassword());
        // System.out.println(appManager.getUserProfile().getUsername());
        // appManager.getUserProfile().editName("George");
        System.out.println(appManager.getUserProfile().getName());
        // appManager.getUserProfile().editSurname("Melissourgos");
        System.out.println(appManager.getUserProfile().getSurname());
        // System.out.println(appManager.getUserProfile().getDepartment());

        // example of the creating of a thread
        // ArrayList<String> tags = new ArrayList<>();
        // tags.add("C++11");
        // System.out.println(appManager.createThread("What is c++11", "I wanna learn c++11", tags));

        // examples of getting threadsClasses but there are not working because getting threadsClasses function in ServerRequest is not finished
        // But you can see the threadsClasses s json objects

        // These are for the newsFeed
        // System.out.println(appManager.getAllTheThreads());
        // System.out.println(appManager.getPopularThreadsOfNewsFeed());
        // System.out.println(appManager.getRecentThreadsOfNewsFeed());
        // System.out.println(appManager.getFavouritesThreads());

        // This will be included in the profile of the user
        // System.out.println(appManager.getTheThreadsOfTheUser());

        // This is for the search bar
        // System.out.println(appManager.getThreadsAccordingToText("takis"));

        // this is for the tag menu search
        // System.out.println(appManager.getThreadsAccordingToATag("takis"));

        /*
         * these 2 are working and it also for the tag menu search
         * and if someone wants to select one of those you will have to use
         * getMostUsedTags() and then
         * getMostUsedTags().get(tagNumberFromTheArrayList).getName() or
         * if someone searches for something according to a name
         * getTagsAccordingToName("name") and then
         * getTagsAccordingToName("name").get(tagNumberFromTheArrayList).getName()
         * and then getThreads according to that tag from the function above
         */
        ArrayList<Tag> mostUsedTags = appManager.getMostUsedTags();
        System.out.println("\nMost Used Tags");
        for (Tag tag : mostUsedTags) {
            System.out.println("Name = " + tag.getName() + ", Usages = " + tag.getUsages() + " , id = " + tag.getId());
        }

        ArrayList<Tag> tagsAccordingToName = appManager.getTagsAccordingToName("C++");
        System.out.println("\nTags according to C++");
        for (Tag tag : tagsAccordingToName) {
            System.out.println("Name = " + tag.getName() + ", Usages = " + tag.getUsages() + " , id = " + tag.getId());
        }
        // todo finish getThreads, upVote, downVote, addTo favorites, activity, increase views when you see a post
    }
}