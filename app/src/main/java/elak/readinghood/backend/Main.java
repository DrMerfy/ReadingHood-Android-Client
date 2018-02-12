package elak.readinghood.backend;

import elak.readinghood.backend.api.AppManager;
import elak.readinghood.backend.hashTags.HashTags;
import elak.readinghood.backend.posts.Post;
import elak.readinghood.backend.profiles.Profile;
import elak.readinghood.backend.threads.Thread;
import elak.readinghood.backend.threads.Threads;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class Main {

    public static void main(String[] args) {
        // !!!!!!!!! MANY FUNCTIONS HAVE STRING RESULTS or booleans. YOU CAN CHECK WITH SYSTEM.OUT.PRINTLN()!!!!!!!!!
        try {
            // Start Panel
            // registration example
            /*
            System.out.println(AppManager.getStartUpManager().registrationSetEMailAndUsername("spyridon97@hotmail.com", "spyridon97"));
            AppManager.getStartUpManager().registrationSetDepartment("Informatics");
            System.out.println(AppManager.getStartUpManager().registrationSetPasswordAndRePassword("a1234567", "a1234567"));
            AppManager.getStartUpManager().createUserProfile();
            //*/

            // example of login
            System.out.println(AppManager.getStartUpManager().login("spyridon97@hotmail.com", "a1234567"));
            // System.out.println(AppManager.getStartUpManager().login("melissourgos@hotmail.com", "a1234567"));

            AppManager.setUserProfile();


            Threads threads;

            // Profile panel of the user
            /*
            System.out.println(AppManager.getUserProfile().getUsername());
            System.out.println(AppManager.getUserProfile().getName());
            System.out.println(AppManager.getUserProfile().getSurname());
            System.out.println(AppManager.getUserProfile().getDepartment());
            System.out.println(AppManager.getUserProfile().getRespect());
            AppManager.getUserProfile().setActivity();
            System.out.println(AppManager.getUserProfile().getActivity().getLatestUpVotedPostText());
            System.out.println(AppManager.getUserProfile().getActivity().getLatestDownVotedPostText());
            System.out.println(AppManager.getUserProfile().getActivity().getLatestCreatedThreadTitle());
            //*/

            // profile panel in general, when you have a profile object
            /*
            Profile profile = new Profile();
            System.out.println(profile.getUsername());
            System.out.println(profile.getName());
            System.out.println(profile.getSurname());
            System.out.println(profile.getDepartment());
            System.out.println(profile.getRespect());
            profile.setActivity();
            System.out.println(profile.getActivity().getLatestUpVotedPostText());
            System.out.println(profile.getActivity().getLatestDownVotedPostText());
            System.out.println(profile.getActivity().getLatestCreatedThreadTitle());
            //*/

            // Thread creation Example
            /*
            HashSet<String> tagsStrings = new HashSet<>();
            tagsStrings.add("Json");
            System.out.println(AppManager.createThread("Json Basics", "Can somebody help me json basics for java?", tagsStrings));
            // then you can see the created post as shown in mock-ups with this function belows with this exact index
            Thread thread = AppManager.getUserProfile().getThreadsCreatedByThisProfile().seeThread(0);
            //*/

            // These are for the newsFeed
            /*
            threads = AppManager.getThreadsAccordingToTheDepartmentOfTheUser();
            threads = AppManager.getPopularThreadsOfNewsFeed();
            threads = AppManager.getRecentThreadsOfNewsFeed();
            //*/

            // Search bar
            /*
            threads = AppManager.getThreadsAccordingToText("java");
            ArrayList<Profile> profilesAsked = AppManager.getProfilesAccordingToText("Spyridon Tsalikis");
            //*/


            HashTags hashTags;

            // HashTag search
            /*
            hashTags = AppManager.getMostUsedHashTags();
            hashTags = AppManager.getHashTagsAccordingToName("C++");
            threads = AppManager.getThreadsAccordingToAHashTag("C++");
            //*/

            /*
            // View HashTags example
            System.out.println("\nAsked HashTags");
            for (int i = 0; i < hashTags.size(); i++) {
                System.out.println("Name = " + hashTags.getHashTag(i).getName() + ", Usages = " + hashTags.getHashTag(i).getUsages() + " , id = " + hashTags.getHashTag(i).getId());
            }
            //*/

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


            // How to select a thread that you wanna see
            // Thread chosenThread = threads.seeThread(5);

            // how to view a thread and it's posts
            /*
            System.out.println("Thread title = " + chosenThread.getTitle());
            System.out.println("Author = " + chosenThread.getThreadCreatorProfile().getUsername());
            System.out.println("Views = " + chosenThread.getViews());
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

            // Answer a Thread
            /*
            // if a thread is not blocked you can answer it
            if (chosenThread.canYouAnswerThisThread()) {
                System.out.println(chosenThread.answerThreadWithAPost("I can help you out. Send me and email"));
                chosenThread.getTheLatestAddedPost();

            }
            //*/

            // Settings panel
            /*
            System.out.println(AppManager.getUserProfile().editName("Spyridon"));
            System.out.println(AppManager.getUserProfile().editSurname("Tsalikis"));
            System.out.println(AppManager.logOut());
            //*/
        } catch (IOException e) {
            // you will put an error dialog here
            System.out.println("Can't Connect Right Now");
        }
    }
}