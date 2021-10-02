import static org.junit.Assert.*;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.junit.Test;

// The name for "Queue.java" in "QueuewithArray" folder had to be renamed to "QueuewithArray.java"
// because of conflicts with an existing "Queue.java" inside the "Queue" parent folder
// which presented errors within Eclipse to detect. For testing purposes, the name and class was changed.

@FixMethodOrder(MethodSorters.DEFAULT)
public class QueuewithArrayTest {

	private QueuewithArray queueObj;
	
	@Before
	public void Init() {
		queueObj = new QueuewithArray(10);
	}

	// Let's first test if dequeueing an empty queue returns -1
	@Test
	public void TestEmptyDequeue() {
		int expectedValue = -1;
		int actualValue = queueObj.dequeue();
		
		// Let's see if displaying an empty queue prints the empty queue message.
		queueObj.display();
		
		Assert.assertEquals(expectedValue, actualValue);
	}
	
	// Now let's check if enqueueing and dequeueing work properly by stressing all functions.
	@Test
	public void TestStress() {
		// Since no exceptions are thrown by these functions, only dequeue can be checked for assertions.
		for (int i = 0; i < 11; i++) {
			queueObj.enqueue(i+1); // additive insertion starting from 1
		}
		queueObj.display();
		// After 4 dequeues, I should have "4" as the expected value.
		int expectedValue = 4;
		int actualValue = 0;
		for (int i = 0; i < 4; i++) {
			actualValue = queueObj.dequeue();
		}
		// 5 6 7 8 9 10 11
		Assert.assertEquals(expectedValue, actualValue);
		
		// Let's enqueue and dequeue again.
		for (int i = 2; i < 8; i++) {
			queueObj.enqueue(i*i); // squared numbers starting from 2
		}
		// 5 6 7 8 9 10 11 4 9 16 25
		queueObj.display();
		// After 8 dequeues, I should have 9 as the expected value.
		expectedValue = 9;
		for (int i = 0; i < 8; i++) {
			actualValue = queueObj.dequeue();
		}
		queueObj.display();
		// 16 25
		for (int i = 0; i < 4; i++) {
			queueObj.enqueue(i+i); // even numbers starting from 0
		}
		queueObj.display();
		// 16 25 0 2 4 6 
		// After 3 dequeues, I should have 0 as the expected value now.
		for (int i = 0; i < 3; i++) {
			actualValue = queueObj.dequeue();
		}
		expectedValue = 0;
		// 4 6
		// Let's test by checking display
		queueObj.display();
		
		Assert.assertEquals(expectedValue, actualValue);
	}
	
	/*
	 * After thorough testing and excluding the main function in "QueuewithArray",
	 * code coverage for "QueuewithArray.java" was at 92.2%,
	 * code coverage for "QueuewithArrayTest.java' was, unfortunately, at 59.0%.
	 * An exception error was identified during enqueueing and dequeueing of the queue.
	 * - When enqueuing, dequeueing, and then enqueuing (as seen in "TestStress"),
	 *   the program throws an error exception: "ArrayIndexOutOfBoundsException".
	 *   The error is most likely because of the condition part of the for loop at line 72 of "QueuewithArray.java".
	 *   When the removePointer's value is greater than 0 and combined with size value, the value becomes larger than that array's own size,
	 *   causing the exception error in the first place.
	 *   Code coverage for both "QueuewithArray.java" and "QueuewithArrayTest.java" can actually be 100%,
	 *   ONLY if, in "TestStress" test function, the display function at line 57 is commented out.
	 *   When the condition at line 53 of "QueuewithArray.java" becomes true, display can properly be called again.
	 *   However, this still counts as a major error in display functions, as at some point during enqueues and dequeues,
	 *   the removePointer's value will exceed beyond 0 and will cause the program to crash.
	 *   I've left the function being called at line 57 so that the test case throws errors.
	 *   There is a great chance this error can be replicated in "Queue.java" inside "QueueImplementation" folder,
	 *   as the update segment inside the for loop of that display function could also potentially cause the program to crash.
	 *   Further testing is required to confirm this theory.
	 *   Update: Testing revealed similar error(s) in "Queue.java" in "QueueImplementation".
	*/
	 
}
