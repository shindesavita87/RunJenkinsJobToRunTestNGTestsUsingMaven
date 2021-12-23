package testngsuite;

import org.testng.annotations.Test;
/*
 *  Points to take away:
 *  Negative priority run highest to low.
 *  If no priority defined then it will run first than one priority is defined.
 *  Positive priority is lowest to high.
 *  without priority is run in alphabetical order.`
 *  If priority is not given then it will run in alphabetical order
 *
 *  Output of this code is:
 *  BWithNegativePriority method test...
 *  AWithNegativePriority method test...
 *  CWithNegativePriority method test...
 *  DWithoutPriority method test...
 *  FWithoutPriority method test...
 *  HWithPositivePriority method test...
 *  EWithPositivePriority method test...
 *  GWithPositivePriority method test...
 *  */
public class TestNgTestPriority {
    @Test(priority = -5)
    public void AWithNegativePriority()
    {
        System.out.println("AWithNegativePriority method test...");
    }

    @Test(priority = -7)
    public void BWithNegativePriority()
    {
        System.out.println("BWithNegativePriority method test...");
    }

    @Test(priority = -3)
    public void CWithNegativePriority()
    {
        System.out.println("CWithNegativePriority method test...");
    }

    @Test
    public void testWithoutPriority()
    {
        System.out.println("DWithoutPriority method test...");
    }


    @Test(priority = 1,invocationCount = 5)
    public void EWithPositivePriority()
    {
        System.out.println("EWithPositivePriority method test...");
    }

    @Test
    public void testFWithoutPriority()
    {
        System.out.println("FWithoutPriority method test...");
    }

    @Test(priority = 2)
    public void GWithPositivePriority()
    {
        System.out.println("GWithPositivePriority method test...");
    }


    @Test(priority = 0,description = "This is own description")
    public void HWithPositivePriority()
    {
        System.out.println("HWithPositivePriority method test...");
    }
}

