package testngwithxml;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(Listener.class)
public class UseListnerClassEx1 {
	@Test
	public void sum() {
		try {
			int sum = 0;
			int a = 5;
			int b = 7;
			sum = a + b;
			System.out.println(sum);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testtofail() {
		System.out.println("Test case has been failed");
		Assert.assertTrue(true);
	}
}