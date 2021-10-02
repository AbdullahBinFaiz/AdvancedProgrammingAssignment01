import static org.junit.Assert.*;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.junit.Test;

@FixMethodOrder(MethodSorters.DEFAULT)
public class MyStackTest {

	private MyStack stackObj;
	
	@Before
	public void Init() {
		stackObj = new MyStack(10);
		// Calling the display function here to test if it displays an empty stack
		stackObj.displayStack();
	}
	
	// Let's test if the push function correctly places the most current value on top
	@Test
	public void TestTop() throws Exception {
		for (int i = 1; i < 6; i++) {
			stackObj.push(50/i); // numbers divided by 50 and applied the floor function
		}
		// Calling display function here again to see if it displays correctly
		stackObj.displayStack();
		int expectedValue = 10;
		int actualValue = stackObj.top();
		
		Assert.assertEquals(expectedValue, actualValue);
	}

	// Does it throw an exception error when you insert more values than stack array size?
	@Test(expected = Exception.class)
	public void TestPushException() throws Exception {
		for (int i = 1; i < 20; i++) {
			stackObj.push(50/i);
		}
		fail("It keeps inserting without throwing exception error");
	}
	
	// Does it throw an exception when the stack array is empty and I check the top?
	@Test(expected = Exception.class)
	public void TestTopException() throws Exception {
		stackObj.top();
		fail("The function could not throw Exception from top");
	}
	
	// Let's pop and get a desired value
	@Test
	public void TestPop() throws Exception {
		for (int i = 2; i < 8; i++) {
			stackObj.push(i*i);
		}
		int expectedValue = 25;
		int actualValue = 0;
		
		for (int i = 0; i < 3; i++) {
			actualValue = stackObj.pop();
		}
		
		Assert.assertEquals(expectedValue, actualValue);
	}
	
	// What if I pop when stack is empty? Does it throw an exception like the code should?
	@Test(expected = Exception.class)
	public void TestPopException() throws Exception {
		stackObj.pop();
		fail("The exception could not be thrown from pop.");
	}
	
	// Does the size function work?
	@Test
	public void TestSize() throws Exception {
		for (int i = 0; i < 8; i++) {
			stackObj.push(i+1);
		}
		int expectedValue = 8;
		int actualValue = stackObj.size();
		
		Assert.assertEquals(expectedValue, actualValue);
		
		// If it passes, let's see if popping adjusts the size correctly.
		expectedValue = 3;
		for (int i = 0; i < 5; i++) {
			stackObj.pop();
		}
		actualValue = stackObj.size();
		
		Assert.assertEquals(expectedValue, actualValue);
		
	}
	
	/*
	 * After thorough testing,
	 * "MyStack.java" has 100% code coverage,
	 * "MyStackTest.java" has 88.8% code coverage.
	 * "StackImplementation.java" is not considered since it is purely a main function for manual testing.
	 * No bugs or errors were identified within this code.
	*/
}
