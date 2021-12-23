package testngwithxml;

import org.junit.Test;


import static org.testng.AssertJUnit.*;

  public class RunJUnitTestsFromTestNg {
    
	  @Test
     public void testAdd() {
        assertEquals("this is message","Junit testing using TestNG","Running JUnit from TestNG");
     }
	  
	 @Test
     public void testAdd2() {
	  assertEquals("This is message", true,true);
     }

  }