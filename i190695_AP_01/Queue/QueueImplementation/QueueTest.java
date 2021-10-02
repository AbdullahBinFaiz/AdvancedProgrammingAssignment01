import static org.junit.Assert.*;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.junit.Test;

@FixMethodOrder(MethodSorters.DEFAULT)
public class QueueTest {

	private Queue queueObj;
	
	@Before
	public void Init() {
		queueObj = new Queue(10); // Queue of size 10 is initialized
	}
	
	// First, check if dequeueing works
	@Test
	public void TestDequeueValue() throws Exception {
		for (int i = 0; i < 10; i++) {
			queueObj.enqueue(2*(i+1)); // multiples of 2
		}
		// display function is called to check correct insertion and to include in code coverage
		queueObj.display();

		int expectedValue = 2;
		int actualValue = queueObj.dequeue();
		
		Assert.assertEquals(expectedValue, actualValue);
	}
	
	// Let's see if it throws an exception error when enqueueing to full array
	@Test(expected = Exception.class)
	public void ExceptionOnEnqueue() throws Exception {
		for (int i = 0; i < 20; i++) { // size of queue is 10, and we are going to insert 20 times
			queueObj.enqueue(i); // additive insertion starting from 0
		}
		fail("Exception was not thrown while enqueuing.");
	}
	
	// Does dequeueing throw an error?
	@Test(expected = Exception.class)
	public void ExceptionOnDequeue() throws Exception {
		for (int i = 0; i < 20; i++) {
			queueObj.dequeue();
		}
		fail("Exception was not thrown while dequeuing.");
	}
	
	// Check if an empty queue returns appropriately
	@Test
	public void TestEmptyQueue() {
		// function is run to include in code coverage
		queueObj.display();
		
		boolean actualValue = queueObj.empty();
		
		Assert.assertTrue(actualValue);
	}
	
	// Check if "empty()" detects a non-empty queue
	@Test
	public void TestNonEmptyQueue() throws Exception {
		for (int i = 0; i < 5; i++) {
			queueObj.enqueue(2*(i+1)); // multiples of 2
		}
		boolean actualValue = queueObj.empty();
		
		Assert.assertFalse(actualValue);
	}
	
	// Does "size()" return the size of queue correctly?
	@Test
	public void TestQueueSize() throws Exception {
		// for an empty queue
		int expectedValue = 0;
		int actualValue = queueObj.size();
		
		Assert.assertEquals(expectedValue, actualValue);
		// for a non-empty queue
		for (int i = 0; i < 7; i++) {
			queueObj.enqueue(3*(i+1)); // multiples of 3
		}
		
		expectedValue = 7;
		actualValue = queueObj.size();
		
		Assert.assertEquals(expectedValue, actualValue);
	}
	
	// Let's stress test this function by repeatedly calling enqueue and dequeue. Does it return correctly then?
	@Test
	public void TestQueueCorrectSize() throws Exception {
		for (int i = 0; i < 7; i++) {
			queueObj.enqueue(2*(i+1)); // multiples of 2
		}
		
		for (int i = 0; i < 3; i++) {
			queueObj.dequeue();
		}
		
		for (int i = 0; i < 5; i++) {
			queueObj.enqueue(5*(i+1)); // multiples of 5
		}
		
		for (int i = 0; i < 2; i++) {
			queueObj.dequeue();
		}
		
		int expectedValue = 7;
		int actualValue = queueObj.size();
		
		Assert.assertEquals(expectedValue, actualValue);
	}
	
	// Let's try a stress test involving multiple functions usage.
	@Test // This test WILL throw error(s)
	public void TestStress() throws Exception {
		for (int i = 0; i < 10; i++) {
			queueObj.enqueue(2*(i+1)); // multiples of 2
		}

		// function is run to include in code coverage
		queueObj.display();
		
		for (int i = 0; i < 5; i++) {
			queueObj.dequeue();
		}
		
		queueObj.display();
		
		for (int i = 0; i < 5; i++) {
			queueObj.enqueue(3*(i+1)); // multiples of 3
		}
		
		queueObj.display();
		
		for (int i = 0; i < 9; i++) {
			queueObj.dequeue();
		}
		
		queueObj.display();
		
		int size = queueObj.size();
		int actualSize = 1;
		
		Assert.assertEquals(actualSize, size);
		
		for (int i = 0; i < 20; i++) {
			queueObj.enqueue(i*i); // squared numbers starting from 0
		}
		
		queueObj.display();
		
		size = queueObj.size();
		actualSize = 10;
		
		Assert.assertEquals(actualSize, size);
		
	}
	/*
	 * After thorough testing and excluding the main function inside "QueueImplementation.java",
	 * code coverage for "Queue.java" was at 95.7%,
	 * code coverage for "QueueTest.java" was, unfortunately, at 77.0%,
	 * since most of the code in "TestStress" was unable to run for reasons stated below.
	 * Two critical errors and one odd issue was identified within the test case.
	 * Odd issue:
	 * - In "TestStress", repeated multiple enqueues and dequeues led to an interesting dilemma, where the size of the queue was returned as 11.
	 *   This should be impossible since initially, the constructor took 10 as the initial size of the array.
	 *   However, inside the constructor, the array's size is initialized as [size+1]. This issue carries over in "QueuewithArray.java".
	 *   Inherently, this is not strictly a bug, but it is a glaring and misleading issue on the behest of the programmer.
	 * Critical errors:
	 * - Multiple enqueues and dequeues lead to an objectively large-scale issue: not being able to access the entire queue properly.
	 *   The issue is caused by the enqueuing and dequeuing functions. The "addPointer" and "removePointer" indices,
	 *   as tested by code coverage for this test case, does not properly follow the array. Particularly, the "removePointer"
	 *   index does not reset when enqueue and dequeue are called consecutively without having to completely fill and
	 *   completely erase the array. This, in part, can be seen when running the display function multiple times,
	 *   which is where the second issue arises.
	 * - Much like in "QueuewithArray.java", the display function has the same error where the condition segment of the for loop
	 *   is "i < size+removePointer". If the "removePointer" value becomes larger than 0, then the next line's "array[i]" throws
	 *   an "ArrayIndexOutOfBoundsException" error since our array's size was initialized to be [size+1], yet if "removePointer"
	 *   somehow becomes 4, then "i" can reach [size+3] which is essentially accessing an invalid address of the memory. 
	 */
}
