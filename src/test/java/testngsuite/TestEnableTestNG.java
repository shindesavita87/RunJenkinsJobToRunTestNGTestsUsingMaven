package testngsuite;

import org.testng.annotations.Test;

public class TestEnableTestNG {


    @Test(enabled = false,groups = { "sanity" })
    public void enableFalse() {
        System.out.println("This test will not run as enable is false.");
    }

    @Test(enabled = true,groups = { "snity","smoke" })
    public void enableTrue() {
        System.out.println("This test will run as enable is true.");
    }
}
