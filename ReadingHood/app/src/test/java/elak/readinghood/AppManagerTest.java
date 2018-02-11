/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elak.readinghood.backend.api;

import java.io.IOException;
import java.util.HashSet;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Δημήτρης Σβίγγας - Αλέξανδρος Χαλκίδης
 */
public class AppManagerTest {

    public AppManagerTest() {
    }

    /**
     * Test of createThread method, of class AppManager.
     * @throws java.io.IOException
     */
    @Test
    public void testCreateThread() throws NullPointerException, IOException, Exception {
        try {
            System.out.println(AppManager.getStartUpManager().login("spyridon97@hotmail.com", "a1234567"));
            AppManager.setUserProfile();
            HashSet<String> tags = new HashSet<String>();
            
            tags.add("No_Spaces");
            String test1 = AppManager.createThread("#Title is-not_empty@", "Hello mother i am here", tags);
            
            tags.clear();
            tags.add("With Spaces");
            String test2 = AppManager.createThread("#Title is-not_empty@", "Hello mother i am here", tags);
            
            tags.clear();
            tags.add("No_Spaces");
            String test3 = AppManager.createThread("#Title is-not_empty@", "     ", tags);
            
            tags.clear();
            tags.add("With Spaces");
            String test4 = AppManager.createThread("#Title is-not_empty@", "     ", tags);
            
            tags.clear();
            tags.add("No_Spaces");
            String test5 = AppManager.createThread("        ", "Hello mother i am here", tags);
            
            tags.clear();
            tags.add("With Spaces");
            String test6 = AppManager.createThread("        ", "Hello mother i am here", tags);
            
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
            tags.add("No_Spaces!No_Spaces!No_Spaces!No_Spaces!No_Spaces!No_Spaces!No_Spaces!No_Spaces!No_Spaces!No_Spaces!No_Spaces!No_Spaces!No_Spaces!No_Spaces!No_Spaces!No_Spaces!No_Spaces!No_Spaces!No_Spaces!No_Spaces!No_Spaces!No_Spaces!No_Spaces!No_Spaces!No_Spaces!12345");//255 letters
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
            assertEquals(test1, "Success");
            assertEquals(test2, "Tag(s) must not have spaces");
            assertEquals(test3, "Fill the fields");
            assertEquals(test4, "Fill the fields");
            assertEquals(test5, "Fill the fields");
            assertEquals(test6, "Fill the fields");
            assertEquals(test7, "Fill the fields");
            assertEquals(test8, "Fill the fields");
            assertEquals(test9, "Fill the fields");
            assertEquals(test10, "Fill the fields");
            assertEquals(test11, "Success");
            assertEquals(test12, "Tag(s) must not have spaces");
            assertEquals(test13, "Success");
            //assertEquals(test14, "Some msg");
            assertEquals(test15, "Success");
            //assertEquals(test16, "Some msg");
            assertEquals(test17, "Success");
            //assertEquals(test18, "Some msg");
            
        } catch (NullPointerException e) {
            throw new NullPointerException();
        } catch (IOException e2){
            throw new IOException();
        } catch (Exception e3) {
            throw new Exception();
        }
    }

}
