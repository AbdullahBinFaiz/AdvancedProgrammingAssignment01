import static org.junit.Assert.*;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.junit.Test;

// Unfortunately, in "LinkedList", out of four classes, three classes have all of their functions returning void.
// As a result, testing them is not possible, at least in a conventional manner.
// To bypass this issue, I've written a piece of code called "getArray" inside the classes of folders
// "LinkedList", "CircularLinkedList", and "LinkedList SwapNodes" so that I can access the array and
// verify the functions' working through inspecting the array I receive.

@FixMethodOrder(MethodSorters.DEFAULT)
public class LinkedListTest {

	private LinkedList listObj;
	
	@Before
	public void Init() {
		listObj = new LinkedList();
	}
	
	// Let's first test pushing at front
	@Test
	public void TestPushFront() {
		int [] values = {4, 6, 2, 29, 10, 16, 9, 1, 3};
		for (int i = 0; i < values.length; i++) {
			listObj.pushFront(values[i]);
		}
		
		int [] expectedArray = {3, 1, 9, 16, 10, 29, 2, 6, 4};
		int [] actualArray = listObj.getArray();
		
		Assert.assertArrayEquals(expectedArray, actualArray);
	}
	
	// And pushing at the back?
	@Test
	public void TestPushBack() {
		int [] values = {56, 21, 29, 42, 19, 14, 33};
		for (int i = 0; i < values.length; i++) {
			listObj.pushBack(values[i]);
		}
		
		int [] expectedArray = {56, 21, 29, 42, 19, 14, 33};
		int [] actualArray = listObj.getArray();
		
		Assert.assertArrayEquals(expectedArray, actualArray);
	}
	
	// Does popping at the back work?
	@Test
	public void TestPopBack() {
		int [] values = {18, 23, 4, 58, 95, 2, 86, 23};
		for (int i = 0; i < values.length; i++) {
			listObj.pushFront(values[i]);
		}
		listObj.display();
		// After 3 back pops, I should have this array:
		int [] expectedArray = {23, 86, 2, 95, 58};
		for (int i = 0; i < 3; i++) {
			listObj.popBack();
		}
		int [] actualArray = listObj.getArray();
		
		Assert.assertArrayEquals(expectedArray, actualArray);
		
		// Let's see if popping continuously leads us to an empty linked list.
		int [] expectedEmptyArray = {};
		for (int i = 0; i < 10; i++) {
			listObj.popBack();
		}
		int [] actualEmptyArray = listObj.getArray();
		
		Assert.assertArrayEquals(expectedEmptyArray, actualEmptyArray);
	}

	// What about popping from the front?
	@Test
	public void TestPopFront() {
		int [] values = {72, 44, 81, 23, 59, 40, 28, 31};
		for (int i = 0; i < values.length; i++) {
			listObj.pushBack(values[i]);
		}
		// After 5 pops, I should have this array:
		int [] expectedArray = {40, 28, 31};
		for (int i = 0; i < 5; i++) {
			listObj.popFront();
		}
		int [] actualArray = listObj.getArray();
		
		Assert.assertArrayEquals(expectedArray, actualArray);
		
		// Let's see if continuous front popping leads to an empty linked list
		for (int i = 0; i < 10; i++) {
			listObj.popFront();
		}
		listObj.display();
		int [] expectedEmptyArray = {};
		int [] actualEmptyArray = listObj.getArray();
		
		Assert.assertArrayEquals(expectedEmptyArray, actualEmptyArray);
	}
	
	// Let's see if popping at certain indices yields us correct answers
	@Test
	public void TestPopPositions() {
		int [] values = {2, 65, 32, 10, 51, 47, 83, 45, 12, 25, 18, 9, 5, 20};
		for (int i = 0; i < values.length; i++) {
			listObj.pushBack(values[i]);
		}
		// Let's insert multiple indices to see the behaviour of the function.
		listObj.pop(0);
		listObj.pop(5);
		listObj.pop(8);
		listObj.pop(2);
		listObj.pop(15);
		listObj.pop(-4);
		// Now the expected array should be as follows:
		int [] expectedArray = {65, 32, 51, 47, 45, 12, 25, 9, 5, 20};
		int [] actualArray = listObj.getArray();
		listObj.display();
		// Let's pop away all values to maximize code coverage for pop function.
		for (int i = 0; i < 10; i++) {
			listObj.pop(0);
		}
		
		Assert.assertArrayEquals(expectedArray, actualArray); // code will fail
		
	}
	
	/*
	 * After thorough testing and excluding the main function in "LinkedList.java",
	 * code coverage for "LinkedList.java" was at 100%
	 * code coverage for "LinkedListTest.java" was at 99.3%.
	 * One critical bug was identified during testing procedure.
	 * - In "pop" function, when a negative position was given, the function would
	 * always inexplicably remove the third value in the linked list. No input validation
	 * was provided in the case that any user would enter a negative position. This can
	 * lead to serious issues where the output will never be right if a single negative
	 * position is given to the function.
	 */
}
