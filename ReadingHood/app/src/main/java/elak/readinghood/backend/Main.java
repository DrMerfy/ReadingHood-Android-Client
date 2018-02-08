package elak.readinghood.backend;

import elak.readinghood.backend.api.AppManager;
import elak.readinghood.backend.posts.Post;
import elak.readinghood.backend.tags.Tags;
import elak.readinghood.backend.threads.Thread;
import elak.readinghood.backend.threads.Threads;

import java.util.HashSet;

public class Main {

    public static void main(String[] args) {
        // !!!!!!!!! MANY FUNCTIONS HAVE STRING RESULTS or booleans. YOU CAN CHECK WITH SYSTEM.OUT.PRINTLN()!!!!!!!!!

        // Start Panel
        // registration example
        // System.out.println(AppManager.getStartUpManager().registrationSetEMailAndUsername("spyridon97@hotmail.com", "spyridon97"));
        // System.out.println(AppManager.getStartUpManager().registrationSetDepartment("Informatics"));
        // System.out.println(AppManager.getStartUpManager().registrationSetPasswordAndRePassword("a1234567", "a1234567"));
        // System.out.println(AppManager.getStartUpManager().createUserProfile());


        // example of login
        System.out.println(AppManager.getStartUpManager().login("spyridon97@hotmail.com", "a1234567"));
        // System.out.println(AppManager.getStartUpManager().login("melissourgos@hotmail.com", "a1234567"));

        AppManager.setUserProfile();


        Threads threads;

        // Profile panel
        // System.out.println(AppManager.getUserProfile().getUsername());
        // System.out.println(AppManager.getUserProfile().getName());
        // System.out.println(AppManager.getUserProfile().getSurname());
        // System.out.println(AppManager.getUserProfile().getDepartment());
        // System.out.println(AppManager.getUserProfile().getReputation());
        // AppManager.getUserProfile().setActivity();
        // System.out.println(AppManager.getUserProfile().getActivity().getLatestUpVotedPostText());
        // System.out.println(AppManager.getUserProfile().getActivity().getLatestDownVotedPostText());
        // System.out.println(AppManager.getUserProfile().getActivity().getLatestCreatedPostText());
        // System.out.println(AppManager.getUserProfile().getActivity().getLatestCreatedThreadTitle());
        // threads = AppManager.getTheThreadsOfTheUser(); // this will be click button that will return threads

        // Thread creation Example
        // HashSet<String> tagsStrins = new HashSet<>();
        // tagsStrins.add("Json");
        /// System.out.println(AppManager.createThread("Json Basics", "Can somebody help me json basics for java?", tagsStrins));

        // These are for the newsFeed

        // threads = AppManager.getAllTheThreads();
        // threads = AppManager.getPopularThreadsOfNewsFeed();
        // threads = AppManager.getRecentThreadsOfNewsFeed();
        // threads = AppManager.getFavoritesThreads();
        // threads = AppManager.getThreadsAccordingToTheDepartmentOfTheUser();

        // Search bar
        // threads = AppManager.getThreadsAccordingToText("c++");

        Tags tags;

        // Tag search
        // tags = AppManager.getMostUsedTags();
        // tags = AppManager.getTagsAccordingToName("C++");
        // tags = AppManager.getThreadsAccordingToATag("C++");

        /*
        // View Tags example
        System.out.println("\nAsked Tags");
        for (int i = 0; i < tags.size(); i++) {
            System.out.println("Name = " + tags.getTag(i).getName() + ", Usages = " + tags.getTag(i).getUsages() + " , id = " + tags.getTag(i).getId());
        }
        */

        /*
        // View of threads example.
        for (int i = 0; i < threads.size(); i++) {
            Thread thread = threads.getThread(i);
            System.out.println("Thread title = " + thread.getTitle());
            System.out.println("Question = " + thread.getQuestionPost().getText());
            System.out.println("Author = " + thread.getThreadCreatorProfile().getUsername());
            System.out.println("Views = " + thread.getViews());
            System.out.println();
        }
        //*/

        System.out.println();


        // How to see a thread
        /*
        Thread chosenThread = threads.seeThread(5);
        System.out.println(chosenThread.getId());
        System.out.println("Thread title = " + chosenThread.getTitle());
        System.out.println("Author = " + chosenThread.getThreadCreatorProfile().getUsername());
        System.out.println("Views = " + chosenThread.getViews());
        */

        // get posts of a thread
        /*
        System.out.println("number of posts = " + chosenThread.getAnswerPosts().size() + 2);
        for (int i = 0; i < chosenThread.getAnswerPosts().size() + 1; i++) {
            Post post;
            if (i == 0) {
                post = chosenThread.getQuestionPost();
                System.out.println("Question Text = " + post.getText());
                System.out.println("Number of Votes = " + post.getNumberOfVotes());
            } else {
                post = chosenThread.getAnswerPosts().getPost(i - 1);
                System.out.println("Answer Text = " + post.getText());
                System.out.println("Author = " + post.getAuthor().getUsername());
                System.out.println("Number of Votes = " + post.getNumberOfVotes());
            }
        }
        //*/
        // upVote example
        /*
        Post chosenPost = chosenThread.getQuestionPost();
        if (chosenPost.canYouUpVote()) {
            chosenPost.upVoteThisPost();
            System.out.println("New number of votes: " + chosenPost.getNumberOfVotes());
        }
        //*/

        /*
        // downVote example
        Post chosenPost = chosenThread.getQuestionPost();
        if (chosenPost.canYouDownVote()) {
            chosenPost.downVoteThisPost();
            System.out.println("New number of votes: " + chosenPost.getNumberOfVotes());
        }
        //*/

        // Answer Thread
        /*
        System.out.println("makis is here " + chosenThread.answerThreadWithAPost("I can help you out. Send me and email"));
        System.out.println(chosenThread.getTheLatestAddedPost());
        //*/


        // Settings panel
        // System.out.println(AppManager.getUserProfile().editName("Spyridon"));
        // System.out.println(AppManager.getUserProfile().editSurname("Tsalikis"));
        System.out.println(AppManager.logOut());

        // https://readinghood.tk:8443/posts/upvote?post_id=2 changes the reputation of the user correctly but does not
        // change the number of votes of the post
    }
}