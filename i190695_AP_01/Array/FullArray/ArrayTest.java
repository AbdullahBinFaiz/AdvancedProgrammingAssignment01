import static org.junit.Assert.*;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@FixMethodOrder(MethodSorters.DEFAULT)
public class ArrayTest {
	
	private Array array;
	
	@Before
	public void Init() {
		array = new Array(); // array of size 100 initialized
	}
	
	// First, test if linear searching works for number we want to search
	@Test
	public void TestLinearSearchTrue() {
		for (int i = 0; i < 20; i++) {
			array.insert(2*(i+1), i); // multiples of 2
		}
		int valueToSearch = 16;
		boolean actualAnswer = array.linearSearch(valueToSearch);
		
		Assert.assertTrue(actualAnswer);	
	}
	
	// Now test if linear searching returns false if number does not exist.
	@Test
	public void TestLinearSearchFalse() {
		for (int i = 0; i < 20; i++) {
			array.insert(3*(i+1), i); // multiples of 3
		}
		int valueToSearch = 14;
		boolean actualAnswer = array.linearSearch(valueToSearch);
		
		Assert.assertFalse(actualAnswer);
	}
	
	// Second, test if binary searching works for number we want to search
	@Test
	public void TestBinarySearchTrue() {
		for (int i = 0; i < 20; i++) {
			array.insert(9*(i+1), i);// multiples of 9
		}
		int valueToSearch = 54;
		boolean actualAnswer = array.binarySearch(valueToSearch, 0, 19);
		
		Assert.assertTrue(actualAnswer);
	}
	
	// Now test if binary searching returns false if number does not exist.
	@Test
	public void TestBinarySearchFalse() {
		for (int i = 0; i < 20; i++) {
			array.insert(6*(i+1), i); // multiples of 6
		}
		int valueToSearch = 85;
		boolean actualAnswer = array.binarySearch(valueToSearch, 0, 19);
		
		Assert.assertFalse(actualAnswer);
	}
	
	// Let's test if the predetermined array really holds 100 values.
	@Test
	public void TestArrayInsertFull() {
		for (int i = 0; i < 110; i++) {
			array.insert(i+1, 0); // additive insertion starting from 1; index is kept at 0 so insertion from front always happens
		}
	}
	
	// Binary search should return false if values are not ordered
	@Test
	public void TestBinarySearchUnordered() {
		array.insert(4, 0);
		array.insert(33, 2);
		array.insert(81, 8);
		array.insert(9, 3);
		array.insert(12, 9);
		array.insert(47, 5);
		array.insert(28, 6);
		array.insert(52, 4);
		array.insert(15, 1);
		array.insert(6, 7);
		
		int valueToSearch = 15;
		boolean actualAnswer = array.binarySearch(valueToSearch, 0, 9);
		
		Assert.assertFalse(actualAnswer);
	}
	
	// Now, after bubble sorting, binary search should return true for same values as in above test
	@Test
	public void TestBinarySearchSorted() {
		array.insert(4, 0);
		array.insert(33, 1);
		array.insert(81, 2);
		array.insert(9, 3);
		array.insert(12, 4);
		array.insert(47, 5);
		array.insert(28, 6);
		array.insert(52, 7);
		array.insert(15, 8);
		array.insert(6, 9);
		
		array.bubbleSort();
		
		// After insertion, let's call the display function to maximize code coverage and confirm the sorting function's working.
		array.display();
		
		int valueToSearch = 15;
		boolean actualAnswer = array.binarySearch(valueToSearch, 0, 9);
		
		Assert.assertTrue(actualAnswer);
	}
	
	// If delete function works, then I shouldn't be able to find the value I deleted.
	@Test
	public void TestSearchForDeletedNumber() {
		for (int i = 0; i < 20; i++) {
			array.insert(5*(i+1), i); // multiples of 5
		}
		array.delete(4); // This should delete "25" in the array
		int valueToSearch = 25; 
		boolean actualAnswer = array.linearSearch(valueToSearch);
		// Assertion should fail now
		Assert.assertFalse(actualAnswer);
	}
	
	/*
	 * After thorough testing and excluding both the main function and the getValues function in "Array" class,
	 * code coverage for both "Array.java" and "ArrayTest.java" were at 100%.
	 * A critical error was identified within the code.
	 * - The "insert" function is poorly handled, where the variable initializer part in the for loop can cause
	 *   the code at line 27 to throw an exception error: ArrayIndexOutOfBoundsException. The predefined size of 100
	 *   can be exceeded when a user continuously enters a value within the range more than a hundred times,
	 *   which is caused by the elements shifting to the right, and there is no check to prevent from further insertion.
	 * "getValues" function was not run since it asked for manual inputs from user and there was no way to identify expected values from the given set of values.
	 * I wanted to test searching times between Binary and Linear searching. However, the "timeout" tag within @Test annotation refused to work. 
	 * Despite the fact that search times in my testing took longer than the stated milliseconds I entered within the tag.
	 */
	
}
