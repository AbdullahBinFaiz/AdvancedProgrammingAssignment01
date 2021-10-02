import static org.junit.Assert.*;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.junit.Test;

@FixMethodOrder(MethodSorters.DEFAULT)
public class StackwithLinkedListTest {
	
	// As with Queues and LinkedLists, the name for this java file had to be changed as well,
	// since "Stack.java" already existed in "StackwithArray" folder.

	private StackwithLinkedList stackObj;
	
	@Before
	public void Init() {
		stackObj = new StackwithLinkedList();
	}

	// Since we can now make a stack of variable size, let's see if the functions hold up and produce any bugs.
	// First, let's try pushing multiple numbers and seeing if "top" function correctly shows the top value.
	@Test
	public void TestTop() {
		for (int i = 0; i < 174; i++) {
			stackObj.push(i+1); // additive insertion starting from 1
		}
		// Let's see if the display function works when values are present in stack.
		stackObj.display();
		// I expect "174" to return when I call the "top" function.
		int expectedValue = 174;
		int actualValue = stackObj.top();
		
		Assert.assertEquals(expectedValue, actualValue);
	}
	
	// Does the top function correctly return -1 if the stack is empty?
	@Test
	public void TestEmptyTop() {
		// I'll call the display function here to see if it returns "Stack if empty." message
		stackObj.display();
		int expectedValue = -1;
		int actualValue = stackObj.top();
		
		Assert.assertEquals(expectedValue, actualValue);
	}
	
	// Does pop work properly?
	@Test
	public void TestPop() {
		for (int i = 0; i < 25; i++) {
			stackObj.push(i+1); // additive insertion starting from 1
		}
		// After popping 12 times, I should have "14" as the expected value
		int expectedValue = 14;
		int actualValue = 0;
		for (int i = 0; i < 12; i++) {
			actualValue = stackObj.pop();
		}
		
		Assert.assertEquals(expectedValue, actualValue);
		
		// What if the stack becomes empty? Does it return -1?
		expectedValue = -1;
		for (int i = 0; i < 14; i++) {
			actualValue = stackObj.pop();
		}
		
		Assert.assertEquals(expectedValue, actualValue);
	}
	
	// Let's test the "getSize" function now.
	@Test
	public void TestSizes() {
		// Let's run a series of push/pops and check expected sizes' values
		for (int i = 0; i < 34; i++) {
			stackObj.push(i); // additive insertion starting from 0
		}
		
		for (int i = 0; i < 2; i++) {
			stackObj.pop();
		}
		int expectedValue = 32;
		int actualValue = stackObj.getSize();
		
		Assert.assertEquals(expectedValue, actualValue);
		
		for (int i = 0; i < 6; i++) {
			stackObj.pop();
		}
		expectedValue = 26;
		actualValue = stackObj.getSize();
		
		Assert.assertEquals(expectedValue, actualValue);
		
		for (int i = 0; i < 7; i++) {
			stackObj.pop();
		}
		expectedValue = 19;
		actualValue = stackObj.getSize();
		
		Assert.assertEquals(expectedValue, actualValue);
		
		for (int i = 0; i < 12; i++) {
			stackObj.pop();
		}
		expectedValue = 7;
		actualValue = stackObj.getSize();
		
		Assert.assertEquals(expectedValue, actualValue);
		
		// One more test - check if value of size stays at 0 after continuous pops of empty stack
		for (int i = 0; i < 9; i++) {
			stackObj.pop();
		}
		expectedValue = 0;
		actualValue = stackObj.getSize();
		
		Assert.assertEquals(expectedValue, actualValue);
		
	}
	/*
	 * After thorough testing and excluding the main function inside "StackwithLinkedList.java",
	 * code coverage for both "StackwithLinkedList.java" and "StackwithLinkedListTest.java" were at 100%.
	 * No bugs or errors were found during testing and code debugging.
	 */
}
