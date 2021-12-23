package testngwithxml;

import org.testng.annotations.Test;

public class TestEnableTestNG {


    @Test(enabled = true,groups = { "sanity" })
    public void enableFalse() {
        System.out.println("This test will not run as enable is false.");
        System.out.print("This is sanity test..");
    }

    @Test(enabled = false,groups = {"sanity","smoke"})
    public void enableTrue() {
        System.out.println("This test will run as enable is true.");
    }
}
