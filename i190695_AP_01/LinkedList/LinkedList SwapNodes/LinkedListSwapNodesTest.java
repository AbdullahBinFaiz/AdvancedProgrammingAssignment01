import static org.junit.Assert.*;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.junit.Test;

// All functions in this piece of code have a void return type. Therefore, it is impossible to normally test them.
// I've added a custom function that returns an integer array of the linked list created, so that testing
// is possible.

@FixMethodOrder(MethodSorters.DEFAULT)
public class LinkedListSwapNodesTest {

	private LinkedListSwapNodes listObj;
	
	@Before
	public void Init() {
		listObj = new LinkedListSwapNodes();
	}
	
	// Let's first test pushing at the front.
	@Test
	public void TestPushFront() {
		int[] valuesToInsert = {9, 12, 25, 5, 23, 17};
		for (int i = 0; i < valuesToInsert.length; i++) {
			listObj.pushFront(valuesToInsert[i]);
		}
		int [] expectedArray = {17, 23, 5, 25, 12, 9};
		listObj.display();
		int [] actualArray = listObj.getArray();
		
		Assert.assertArrayEquals(expectedArray, actualArray);
	}

	// Now let's test pushing at the back
	@Test
	public void TestPushBack() {
		int [] valuesToInsert = {10, 2, 56, 12, 23, 5, 7, 1};
		for (int i = 0; i < valuesToInsert.length; i++) {
			listObj.pushBack(valuesToInsert[i]);
		}
		int [] expectedArray = {10, 2, 56, 12, 23, 5, 7, 1};
		listObj.display();
		int [] actualArray = listObj.getArray();
		
		Assert.assertArrayEquals(expectedArray, actualArray);
	}
	
	// Pushing from back and front together?
	@Test
	public void TestPushes() {
		listObj.pushFront(10);
		listObj.pushFront(33);
		listObj.pushBack(5);
		listObj.pushFront(35);
		listObj.pushBack(28);
		listObj.pushBack(0);
		listObj.pushBack(7);
		listObj.pushFront(18);
		listObj.pushFront(42);
		listObj.pushBack(2);
		listObj.pushFront(9);
		listObj.pushBack(25);
		
		int [] expectedArray = {9, 42, 18, 35, 33, 10, 5, 28, 0, 7, 2, 25};
		int [] actualArray = listObj.getArray();
		
		Assert.assertArrayEquals(expectedArray, actualArray);
	}
	
	// Does swapping work?
	@Test
	public void TestSwap() {
		int [] valuesToInsert = {6, 20, 24, 58, 1, 223};
		for (int i = 0; i < valuesToInsert.length; i++) {
			listObj.pushBack(valuesToInsert[i]);
		}
		int [] expectedArray = {223, 20, 24, 58, 1, 6};
		listObj.swap();
		int [] actualArray = listObj.getArray();
		
		Assert.assertArrayEquals(expectedArray, actualArray);
	}
	
	// Does it appropriately return when there's only 0 or 1 value?
	@Test
	public void TestNoSwap() {
		int [] expectedEmptyArray = {};
		int [] expectedSingleValue = {5};
		
		listObj.display(); // calling for code coverage
		listObj.swap();
		
		int [] actualEmptyArray = listObj.getArray();
		
		Assert.assertArrayEquals(expectedEmptyArray, actualEmptyArray);
		
		listObj.pushFront(5);
		listObj.swap();
		int [] actualSingleValueArray = listObj.getArray();
		
		Assert.assertArrayEquals(expectedSingleValue, actualSingleValueArray);
	}
	
	/*
	 * After thorough testing and excluding the main function in "LinkedListSwapNodes.java",
	 * code coverage for both "LinkedListSwapNodes.java" and "LinkedListSwapNodesTest.java" was at 100%.
	 * No bugs or errors were identified during testing procedures.
	 */
}
