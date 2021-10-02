import static org.junit.Assert.*;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.junit.Test;

@FixMethodOrder(MethodSorters.DEFAULT)
public class CircularLinkedListTest {

	private CircularLinkedList listObj;
	
	@Before
	public void Init() {
		listObj = new CircularLinkedList();
	}
	
	// Let's first test pushing at the front.
	@Test
	public void TestPushFront() {
		int [] values = {5, 2, 8, 9, 4, 0, 1};
		for (int i = 0; i < values.length; i++) {
			listObj.pushFront(values[i]);
		}
		int [] expectedArray = {1, 0, 4, 9, 8, 2, 5};
		int [] actualArray = listObj.getArray();
		
		Assert.assertArrayEquals(expectedArray, actualArray);
	}
	
	// Now let's test pushing at the back.
	@Test
	public void TestPushBack() {
		int [] values = {23, 19, 14, 0, 25, 52, 12, 38};
		for (int i = 0; i < values.length; i++) {
			listObj.pushBack(values[i]);
		}
		int [] expectedArray = {23, 19, 14, 0, 25, 52, 12, 38};
		listObj.display();
		int [] actualArray = listObj.getArray();
		
		Assert.assertArrayEquals(expectedArray, actualArray);
	}
	
	// Let's try popping from the front.
	@Test
	public void TestPopFront() {
		int [] values = {35, 39, 10, 23, 51, 86, 73};
		for (int i = 0; i < values.length; i++) {
			listObj.pushFront(values[i]);
		}
		// After 4 pops from front, I should have this array:
		int [] expectedArray = {10, 39, 35};
		for (int i = 0; i < 4; i++) {
			listObj.popFront();
		}
		int [] actualArray = listObj.getArray();
		
		Assert.assertArrayEquals(expectedArray, actualArray);
		
		// And what if I were to keep popping even when the array was empty?
		for (int i = 0; i < 10; i++) {
			listObj.popFront();
		}
		int [] expectedEmptyArray = {};
		listObj.display(); // merely calling for completing code coverage
		int [] actualEmptyArray = listObj.getArray();
		
		Assert.assertArrayEquals(expectedEmptyArray, actualEmptyArray);
	}

	// Now let's pop from the back.
	@Test
	public void TestPopBack() {
		int [] values = {21, 7, 62, 84, 5, 12, 51, 4, 63};
		for (int i = 0; i < values.length; i++) { 
			listObj.pushFront(values[i]);
		}
		// After 3 pops, I should have this array:
		int [] expectedArray = {63, 4, 51, 12, 5, 84};
		for (int i = 0; i < 3; i++) {
			listObj.popBack();
		}
		int [] actualArray = listObj.getArray();
		
		Assert.assertArrayEquals(expectedArray, actualArray);
		
		// And what if I were to keep popping even when the linked list became empty?
		for (int i = 0; i < 10; i++) {
			listObj.popBack();
		}
		int [] expectedEmptyArray = {};
		int [] actualEmptyArray = listObj.getArray();
		
		Assert.assertArrayEquals(expectedEmptyArray, actualEmptyArray);
	}
	
	/*
	 * After thorough testing and excluding the main function in "CircularLinkedList.java",
	 * code coverage for both "CircularLinkedList.java" and "CircularLinkedListTest.java" were at 100%.
	 * No bugs or errors were identified within the code that could hamper the code's working at any point.
	 */
}
