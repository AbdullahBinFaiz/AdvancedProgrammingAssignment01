import static org.junit.Assert.*;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.junit.Test;

// In theory, Queues and Stacks, when programmed using linked lists, are identical.
// Only the position the pointers point at is different.
// Testing for this should be similar to "StackwithLinkedList"

@FixMethodOrder(MethodSorters.DEFAULT)
public class QueuewithLinkedListTest {

	private QueuewithLinkedList queueObj;
	
	@Before
	public void Init() {
		queueObj = new QueuewithLinkedList();
	}
	
	// As usual, let's test Enqueue (through Dequeue) and Dequeue itself.
	@Test
	public void TestEnqueueAndDequeue() {
		for (int i = 1; i < 6; i++) {
			queueObj.enqueue(i*i*i); //cubed numbers starting from 1
		}
		// 1 8 27 64 125
		// Dequeuing 2 times, I should get the number "8"
		// Dequeuing 4 times, I should get the number "64"
		int expectedValue = 8;
		int actualValue = 0;
		for (int i = 0; i < 2; i++) {
			actualValue = queueObj.dequeue();
		}
		
		Assert.assertEquals(expectedValue, actualValue);
		
		expectedValue = 64;
		for (int i = 0; i < 2; i++) {
			actualValue = queueObj.dequeue();
		}
		
		Assert.assertEquals(expectedValue, actualValue);
		
		// Let's enqueue a relatively large set of numbers;
		for (int i = 0; i < 240; i++) {
			queueObj.enqueue(i+1); // additive insertion starting from 1
		}
		// And relevantly display it
		queueObj.display();
		// Now if I dequeue 48 times, I should get 47 (since one more value from the initial 5 enqueued numbers exist)
		expectedValue = 47;
		for (int i = 0; i < 48; i++) {
			actualValue = queueObj.dequeue();
		}
		
		Assert.assertEquals(expectedValue, actualValue);
		
	}
	
	// Does the dequeue function return -1 if the queue is empty?
	@Test
	public void TestEmptyDequeue() {
		int expectedValue = -1;
		int actualValue = queueObj.dequeue();
		
		Assert.assertEquals(expectedValue, actualValue);
		
		// Let's just run the display function to check if it prints "Empty queue" message
		queueObj.display();
		
		// What about after enqueueing and dequeuing multiple times?
		for (int i = 0; i < 51; i++) {
			queueObj.enqueue(i); // additive insertion starting from 0
		}
		for (int i = 0; i < 152; i++) {
			actualValue = queueObj.dequeue();
		}
		
		Assert.assertEquals(expectedValue, actualValue);
		
	}
	
	/*
	 * After thorough testing and excluding the main function in "QueuewithLinkedList.java",
	 * code coverage for both "QueuewithLinkedList.java" and "QueuewithLinkedListTest.java were at 100%.
	 * No bugs or errors were identified within the code.
	 */

}
