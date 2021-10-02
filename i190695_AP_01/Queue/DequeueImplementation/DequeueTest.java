import static org.junit.Assert.*;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.junit.Test;

@FixMethodOrder(MethodSorters.DEFAULT)
public class DequeueTest {

	private Dequeue dequeueObj;
	
	@Before
	public void Init() {
		dequeueObj = new Dequeue(10);
	}
	
	// Let's test if the array in Dequeue can be arranged. 
	@Test
	public void TestPushSides() {
		dequeueObj.addLeft(5);
		dequeueObj.addLeft(2);
		dequeueObj.addLeft(8);
		dequeueObj.addLeft(3);
		dequeueObj.addRight(7);
		dequeueObj.addRight(14);
		dequeueObj.addRight(9);
		dequeueObj.addLeft(23);
		dequeueObj.addLeft(14);
		dequeueObj.addLeft(1);
		// Array should be (5,2,8,3,23,14,1,9,14,7)
		dequeueObj.display();
		int [] expectedArray = {5,2,8,3,23,14,1,9,14,7};
		
		Assert.assertArrayEquals(expectedArray, dequeueObj.array);;
	}
	
	// Let's test only addLeft and addRight functions now.
	@Test
	public void TestAddLeft() {
		for (int i = 0; i < 12; i++) {
			dequeueObj.addLeft(i+1); // additive insertion starting from 1
		}
		int [] expectedArray = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		
		Assert.assertArrayEquals(expectedArray, dequeueObj.array);
	}
	
	@Test
	public void TestAddRight() {
		for (int i = 0; i < 12; i++) {
			dequeueObj.addRight(i+1); // additive insertion starting from 1
		}
		int [] expectedArray = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
		
		Assert.assertArrayEquals(expectedArray, dequeueObj.array);
	}

	/*
	 * After some testing,
	 * code coverage for "Dequeue.java" was at 78%,
	 * code coverage for "DequeueTest.java" was at 100%.
	 * A bug and issue were identified within the code in "Dequeue.java", the bug highlighting the decrease in code coverage of "Dequeue.java".
	 * - Lines 23-29 of "AddLeft" function and lines 43-49 of "AddRight" function can technically never be accessed for code coverage.
	 *   The if conditions are structured as so that line 23, for example, in "addLeft" would be executed
	 *   if and only if "addLeft" is continuously called. However, when this happens,
	 *   the if condition at line 20 will always execute and become true, as nothing was added from the right.
	 *   Similarly, when only "addRight" is called until the array is full, "leftPointer" becomes 0. But "rightPointer" is at -1,
	 *   and at line 40, the condition becomes true, which means the if condition at line 43 is never accessed.
	 *   This issue stems in "Queue.java" in "QueueImplementation" folder, which explains why it doesn't have a 100% code coverage.
	 * - All functions have a void return type. However, the array can be accessed as if it is public.
	 *   In theory, this should not happen as data members and data functions in classes are always
	 *   instantiated as private, and to make them public, the keyword "public" is explicitly stated before the variable.
	 *   This allows this patch of code to be tested. However, in my opinion, it violates the working of a class.
	 */
	
}
