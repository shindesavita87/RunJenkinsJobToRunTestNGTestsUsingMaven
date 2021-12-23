package testngsuite;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

// http://testng.org/doc/documentation-main.html
@Test(singleThreaded = true, suiteName = "test1")
public class AnnotationsTestNG {
	static int lol;
	static int lel;
	int a = 0;
	int iter = 0;
	String b = null;
	String c = "c";
	String d = "d";
	String e = "e";
	int f = 60;
	int g = 1;


	@Test(enabled = false,alwaysRun = true)
	public int dependantMethod() {
		return 5;
	}

	@Test(alwaysRun = true) //this makes this method always run
	public void alwayzRun() {
		System.out.println("Always Run: " + a);
	}

	@DataProvider(name = "datProvider") //same
	public Object[][] lol() {
		return new Object[][] { { 1,2,45 }, {3,4,5 } };
	}

	@Test(dataProvider = "datProvider") //same //name of the dataprovider for this method
	public void datProvi(int[] lol) {
		System.out.println("hi");
	}

	@Test(description = "default description") //simply writes description before executing test method
	public void descrip() {
		System.out.println("Description ABOVE!");
	}

	@Test(enabled = true) //enables this test method to run if true, won't run if false
	public void enabler() {
		System.out.println("Enabler: TRUE");
	}

	@Test(expectedExceptions = NullPointerException.class) //expects a specified Exception
	public void expectExcept() throws Exception {
		try {
			//System.out.println(b.substring(2));
			System.out.println(d.substring(3));
		} catch (NullPointerException e) {
			System.out.println("Caught exception");
		}
	}

	@Test(expectedExceptions = NullPointerException.class, expectedExceptionsMessageRegExp = "hello")
	//expects a message that matches the REGEX
	public void expectExceptMsgREGEX() throws Exception {
		throw new NullPointerException("hello");
	}

	@Test(groups = "1") //name the group takes string argument, the list of groups this class/method belongs to
	public void gruop() {
		System.out.println("GROUP Method: ");
	}

	@Test(groups = "2", ignoreMissingDependencies = true) //ignore any dependencies, requires depend parameter probably
	public void igMissDepen() {
		System.out.println("IGNOREMISSINGDEPENDENCIES: ");
		System.out.println(dependantMethod());
	}

	@Test(invocationCount = 2) //specify how many times to run
	public void invoCnt() {
		System.out.println("InvocationCount: 2");
	}

	@Test(invocationTimeOut = 100, invocationCount = 10)
	//specify how long all of the invocations totally can the test run without failure
	public void invoTimOut() {
		System.out.println("InvocationTimeOut: 5 for 10000");
	}


	@Test(priority = -1234) //list priority with other annotated priority methods
	public void runFirst() {
		System.out.println("Priority: This method runs FIRST among all priority specified methods");
	}

	@Test(singleThreaded = true) //use single thread
	public void seq() {
		System.out.println("Sequential: Using one thread");
	}

	@Test(skipFailedInvocations = true, invocationCount = 3) //skips failed invocations
	public void skipFail() {
		b = "hello";
		if (iter >= 1) {
			try {
				System.out.println(b.substring(1, 3));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("SkipFailedInvocations: True");
	}

	@Test(successPercentage = 100) //state expected success %
	public void succPerc() {
		System.out.println("SuccessPercentage: should be 100%");
	}

	@Test(suiteName = "TestSuite") //names the test suite this class should be placed in
	public void suiteNamer() {
		System.out.println("SuiteName: TestSuite");
	}

	@Test(testName = "TestMethods2") //names the test name this class should be placed in
	public void testNamer() {
		System.out.println("TestName: TestMethods");
	}

	@Test(threadPoolSize = 3, invocationCount = 10, timeOut = 10000)
	//specify size of threadpool for method need invocationCount
	public void threadPool() {
		System.out.println("ThreadPoolSize: 3");
	}

	@Test(timeOut = 10000) //specify how long (ms) this test can take
	public void timOut() {
		System.out.println("TimeOut: length 10000");
	}
}
