import static org.junit.Assert.*;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.junit.Test;

@FixMethodOrder(MethodSorters.DEFAULT)
public class StackTest {

	private Stack stackObj;
	
	@Before
	public void Init() {
		// a default constructor is called where a predetermined size of array (100) is initialized.
		stackObj = new Stack();
	}

	// Unfortunately, this stack class only has push and pop, of which only pop has a return type.
	// A stress test can be done and see if pushing works (via popping) and popping itself.
	@Test
	public void TestStress() {
		// First, let's see if the display function prints the "Empty Stack." message.
		stackObj.display();
		// Now, let's add in 100+ values and pop a value to see if it pops the 100th value in the stack,
		// assuming that it does not push more than 100 values in the stack array.
		for (int i = 0; i < 140; i++) {
			stackObj.push(2*(i+1)); // multiples of 2 insertion
		}
		// Once more, call the display function to see if it lists all values (merely for code coverage).
		stackObj.display();
		// Through the loop, "200" should be the value at the 100th at the last index (or at the top) of the stack array.
		// Let's confirm that.
		int expectedValue = 200;
		int actualValue = stackObj.pop();
		
		Assert.assertEquals(expectedValue, actualValue);
		
		// If I pop it 64 times, I should get 72 with the 64th pop
		expectedValue = 72;
		for (int i = 0; i < 64; i++) {
			actualValue = stackObj.pop();
		}
		
		Assert.assertEquals(expectedValue, actualValue);
	}
	
	// Let's test if popping an empty stack does anything to the function.
	// By the given code, we should receive "-1"
	@Test
	public void TestPopError() {
		int expectedValue = -1;
		int actualValue = stackObj.pop();
		
		Assert.assertEquals(expectedValue, actualValue);
	}
	/*
	 * After testing, and excluding main function in "Stack.java",
	 * Code coverage for both "Stack.java" and "StackTest.java" is 100%.
	 * No bugs or errors were identified within the code.
	*/
}
