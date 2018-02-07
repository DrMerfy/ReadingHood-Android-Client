package elak.readinghood.backend;

import elak.readinghood.backend.api.AppManager;
import elak.readinghood.backend.threadsClasses.Tag;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        // registration example
        // AppManager.getStartUpManager().registrationSet(1, "makis4@makis.com", "makis4", null, null, null);
        // AppManager.getStartUpManager().registrationSet(2, null, null, "Informatics", null, null);
        // AppManager.getStartUpManager().registrationSet(3, null, null, null, "a1234567", "a1234567");
        // AppManager.getStartUpManager().createUser();

        // example of login
        AppManager.getStartUpManager().login("makis4@makis.com", "a1234567");
        AppManager.setUserProfile();

        // example of getting info of user and edit name and surname example
        // System.out.println(AppManager.getUserProfile().getId());
        // System.out.println(AppManager.getUserProfile().getEmail());
        // System.out.println(AppManager.getUserProfile().getPassword());
        // System.out.println(AppManager.getUserProfile().getUsername());
        // AppManager.getUserProfile().editName("Makis");
        System.out.println(AppManager.getUserProfile().getName());
        // AppManager.getUserProfile().editSurname("Katsavakis");
        System.out.println(AppManager.getUserProfile().getSurname());
        // System.out.println(AppManager.getUserProfile().getDepartment());

        // example of the creating of a thread
        // ArrayList<String> tags = new ArrayList<>();
        // tags.add("Makis4 found");
        // System.out.println(AppManager.createThread("Who is makis katsanevakis?", "Who the fuck is he?", tags));

        // examples of getting threadsClasses but there are not working because getting threadsClasses function in ServerRequest is not finished
        // But you can see the threadsClasses s json objects

        // These are for the newsFeed
        // System.out.println(AppManager.getAllTheThreads());
        // System.out.println(AppManager.getPopularThreadsOfNewsFeed());
        // System.out.println(AppManager.getRecentThreadsOfNewsFeed());
        // System.out.println(AppManager.getFavouritesThreads());

        // This will be included in the profile of the user
        // System.out.println(AppManager.getTheThreadsOfTheUser());

        // This is for the search bar
        // System.out.println(AppManager.getThreadsAccordingToText("takis"));

        // this is for the tag menu search
        // System.out.println(AppManager.getThreadsAccordingToATag("MakisGuy"));

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
        ArrayList<Tag> mostUsedTags = AppManager.getMostUsedTags();
        System.out.println("\nMost Used Tags");
        for (Tag tag : mostUsedTags) {
            System.out.println("Name = " + tag.getName() + ", Usages = " + tag.getUsages() + " , id = " + tag.getId());
        }

        ArrayList<Tag> tagsAccordingToName = AppManager.getTagsAccordingToName("C++");
        //System.out.println("\nTags according to C++");
        for (Tag tag : tagsAccordingToName) {
            //System.out.println("Name = " + tag.getName() + ", Usages = " + tag.getUsages() + " , id = " + tag.getId());
        }

        // log out example
        System.out.println(AppManager.logOut());

        // todo  finish getThreads, upVote, downVote, favorite a thread, activity, increase views when you see a post,
        // todo  get username from profile, unique tags with a set and override equals for tag with name test
        // todo  fix author different id than creator
    }
}
