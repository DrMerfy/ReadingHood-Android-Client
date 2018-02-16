package elak.readinghood.backend.api;

import org.junit.Test;

import java.io.IOException;
import java.util.HashSet;
import static org.junit.Assert.assertEquals;




/**
 * Created by Δημήτρης Σβίγγας + Αλέξανδρος Χαλκίδης on 2/9/2018.
 */
public class AppManagerTest {
    @Test
    public void createThread() throws Exception {
        try {
            System.out.println(AppManager.getStartUpManager().login("a@a.a", "a"));
            AppManager.setUserProfile();
            HashSet<String> tags = new HashSet<String>();

            tags.add("No_Spaces");
            String test1 = AppManager.createThread("#Title is-not_empty@", "Hello world!", tags);

            tags.clear();
            tags.add("With Spaces");
            String test2 = AppManager.createThread("#Title is-not_empty@", "Hello world!", tags);

            tags.clear();
            tags.add("No_Spaces");
            String test3 = AppManager.createThread("#Title is-not_empty@", "     ", tags);

            tags.clear();
            tags.add("With Spaces");
            String test4 = AppManager.createThread("#Title is-not_empty@", "     ", tags);

            tags.clear();
            tags.add("No_Spaces");
            String test5 = AppManager.createThread("        ", "Hello world!", tags);

            tags.clear();
            tags.add("With Spaces");
            String test6 = AppManager.createThread("        ", "Hello world!", tags);

            tags.clear();
            tags.add("No_Spaces");
            String test7 = AppManager.createThread("#Title is-not_empty@", "", tags);

            tags.clear();
            tags.add("With Spaces");
            String test8 = AppManager.createThread("#Title is-not_empty@", "", tags);

            tags.clear();
            tags.add("No_Spaces");
            String test9 = AppManager.createThread("", "Hello mother i am here", tags);

            tags.clear();
            tags.add("With Spaces");
            String test10 = AppManager.createThread("", "Hello mother i am here", tags);

            tags.clear();
            tags.add("No_Spaces");
            String test11 = AppManager.createThread("!", "@", tags);

            tags.clear();
            tags.add("With Spaces");
            String test12 = AppManager.createThread("!", "@", tags);

            tags.clear();
            tags.add("No_Spaces");
            String test13 = AppManager.createThread("#Title is-not_empty@", "I am good!I am good!I am good!I am good!I am good!I am good!I am good!I am good!I am good!I am good!I am good!I am good!I am good!I am good!I am good!I am good!I am good!I am good!I am good!I am good!I am good!I am good!I am good!I am good!I am good!12345", tags);//255 leters

            //tags.clear();
            //tags.add("No_Spaces");
            //String test14 = AppManager.createThread("#Title is-not_empty@", "I am good!I am good!I am good!I am good!I am good!I am good!I am good!I am good!I am good!I am good!I am good!I am good!I am good!I am good!I am good!I am good!I am good!I am good!I am good!I am good!I am good!I am good!I am good!I am good!I am good!123456", tags);//256 leters

            tags.clear();
            tags.add("No_SpacessNo_SpacessNo_SpacessNo_SpacessNo_SpacessNo_SpacessNo_SpacessNo_SpacessNo_SpacessNo_SpacessNo_SpacessNo_SpacessNo_SpacessNo_SpacessNo_SpacessNo_SpacessNo_SpacessNo_SpacessNo_SpacessNo_SpacessNo_SpacessNo_SpacessNo_SpacessNo_SpacessNo_Spacess12345");//255 letters
            String test15 = AppManager.createThread("#Title is-not_emptyTitle", "Hello mother i am here", tags);

            //tags.clear();
            //tags.add("No_Spaces!No_Spaces!No_Spaces!No_Spaces!No_Spaces!No_Spaces!No_Spaces!No_Spaces!No_Spaces!No_Spaces!No_Spaces!No_Spaces!No_Spaces!No_Spaces!No_Spaces!No_Spaces!No_Spaces!No_Spaces!No_Spaces!No_Spaces!No_Spaces!No_Spaces!No_Spaces!No_Spaces!No_Spaces!123456");//256 letters
            //String test16 = AppManager.createThread("#Title is-not_emptyTitle", "Hello mother i am here", tags);

            tags.clear();
            tags.add("No_Spaces");
            String test17 = AppManager.createThread("!@#$%!@#$%!@#$%!@#$%!@#$%!@#$%!@#$%!@#$%!@#$%!@#$%!@#$%!@#$%!@#$%!@#$%!@#$%!@#$%!@#$%!@#$%!@#$%!@#$%!@#$%!@#$%!@#$%!@#$%!@#$%!@#$%!@#$%!@#$%!@#$%!@#$%!@#$%!@#$%!@#$%!@#$%!@#$%!@#$%!@#$%!@#$%!@#$%!@#$%!@#$%!@#$%!@#$%!@#$%!@#$%!@#$%!@#$%!@#$%!@#$%!@#$%12345", "Hello mother i am here", tags);//255 letters

            //tags.clear();
            //tags.add("No_Spaces");
            //String test18 = AppManager.createThread("!@#$%!@#$%!@#$%!@#$%!@#$%!@#$%!@#$%!@#$%!@#$%!@#$%!@#$%!@#$%!@#$%!@#$%!@#$%!@#$%!@#$%!@#$%!@#$%!@#$%!@#$%!@#$%!@#$%!@#$%!@#$%!@#$%!@#$%!@#$%!@#$%!@#$%!@#$%!@#$%!@#$%!@#$%!@#$%!@#$%!@#$%!@#$%!@#$%!@#$%!@#$%!@#$%!@#$%!@#$%!@#$%!@#$%!@#$%!@#$%!@#$%!@#$%123456", "Hello mother i am here", tags);//256 letters

            tags.clear();
            tags.add("");
            String test19 = AppManager.createThread("Hello World", "Hello world!", tags);

            tags.clear();
            tags.add(" ");
            String test20 = AppManager.createThread("Hello World", "Hello world!", tags);

            tags.clear();
            tags.add("!");
            String test21 = AppManager.createThread("Hello World", "Hello world!", tags);

            tags.clear();
            String test22 = AppManager.createThread("Hello World", "Hello world!", tags);

            tags.clear();
            tags.add("n_");
            String test23 = AppManager.createThread("Hello World", "Hello world!", tags);

            tags.clear();
            tags.add("_n");
            String test24 = AppManager.createThread("Hello World", "Hello world!", tags);


            tags.clear();

            assertEquals(test1, "Success");
            assertEquals(test2, "Wrong hashTagFormat");
            assertEquals(test3, "Fill the fields");
            assertEquals(test4, "Fill the fields");
            assertEquals(test5, "Fill the fields");
            assertEquals(test6, "Fill the fields");
            assertEquals(test7, "Fill the fields");
            assertEquals(test8, "Fill the fields");
            assertEquals(test9, "Fill the fields");
            assertEquals(test10, "Fill the fields");
            assertEquals(test11, "Success");
            assertEquals(test12, "Wrong hashTagFormat");
            assertEquals(test13, "Success");
            //assertEquals(test14, "IOException"");
            assertEquals(test15, "Success");
            //assertEquals(test16, "IOException"");
            assertEquals(test17, "Success");
            //assertEquals(test18, "IOException");
            //assertEquals(test19, "Fill the fields");
            assertEquals(test20, "Wrong hashTagFormat");
            assertEquals(test21, "Wrong hashTagFormat");
            assertEquals(test22, "Thread must contain at least one hashTag");
            assertEquals(test23, "Wrong hashTagFormat");
            assertEquals(test24, "Wrong hashTagFormat");

        } catch (NullPointerException e) {
            e.printStackTrace();
            throw new NullPointerException();
        } catch (IOException e2){
            throw new IOException();
        } catch (Exception e3) {
            e3.printStackTrace();
            throw new Exception();
        }

    }

}
