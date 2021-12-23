package testngwithxml;
import org.testng.annotations.Test;

public class IncludeExcludeDemo {
	
	@Test(description = "Include exclude description test1")
	public void test1() {
		System.out.println("This is the test1...");
	}
	
	@Test(description = "Include exclude description test2")
	public void test2() {
		System.out.println("This is the test2...");
	}
	
	@Test(description = "Include exclude description test3")
	public void test3() {
		System.out.println("This is the test3...");
	}
}