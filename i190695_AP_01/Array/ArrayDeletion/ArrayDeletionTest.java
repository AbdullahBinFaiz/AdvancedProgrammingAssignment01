import static org.junit.Assert.*;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.junit.Test;

@FixMethodOrder(MethodSorters.DEFAULT)
public class ArrayDeletionTest {

	private int [] values;
	
	@Before
	public void Init() {
		values = new int[10];
	}
	
	// Let's see if the delete function first works
	@Test
	public void TestDeletionAtBack() {
		int [] expectedArray = {10, 9, 8, 7, 6, 5, 4, 3, 2, -1};
		for (int i = 0; i < 10; i++) {
			values[i] = 10-i;
		}
		// For some unknown reason, the function "delete" in "ArrayDeletion.java" is set private,
		// so even instantiating a class object of "ArrayDeletion" will not help us call said function.
		// A possible solution is to simply change the "private" keyword in "delete" to "public".
		// Sir Atif's suggested solution was to create a custom public function that calls the private function in the class.
		values = ArrayDeletion.callDelete(values, 9);
		
		Assert.assertArrayEquals(values, expectedArray);
	}
	
	// Does it work in the front?
	@Test
	public void TestDeletionAtFront() {
		int [] expectedArray = {9, 8, 7, 6, 5, 4, 3, 2, 1, -1};
		for (int i = 0; i < 10; i++) {
			values[i] = 10-i;
		}
		values = ArrayDeletion.callDelete(values, 0);
		
		Assert.assertArrayEquals(values, expectedArray);
	}
	
	// What about in the middle?
	@Test
	public void TestDeletionInMiddle() {
		int [] expectedArray = {10, 9, 8, 7, 5, 4, 1, -1, -1, -1};
		for (int i = 0; i < 10; i++) {
			values[i] = 10-i;
		}
		values = ArrayDeletion.callDelete(values, 4);
		values = ArrayDeletion.callDelete(values, 7);
		values = ArrayDeletion.callDelete(values, 6);
		
		// Display function is called to check its validity and working.
		ArrayDeletion.display(values);
		
		Assert.assertArrayEquals(values, expectedArray);
		
	}
	
	// What happens if I give it an incorrect index value?
	@Test
	public void TestDeletionIncorrectIndices() {
		int [] expectedArray = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
		for (int i = 0; i < 10; i++) {
			values[i] = 10-i;
		}
		values = ArrayDeletion.callDelete(values, 12); // larger than upper index limit
		
		Assert.assertArrayEquals(values, expectedArray);
		
		values = ArrayDeletion.callDelete(values,  -7); // smaller than lower index limit (a negative of index that is valid)
		
		Assert.assertArrayEquals(values, expectedArray);
		
	}
	/*
	 * After thorough testing and excluding the main function in "ArrayDeletion.java",
	 * code coverage for "ArrayDeletion.java" was at 95.5%,
	 * code coverage for "ArrayDeletionTest.java" was at 100%.
	 * One reason why "ArrayDeletion.java" is at 95.5%, even though all functions are working,
	 * could be because "ArrayDeletion" class's object was not instantiated as there were no data members that could be utilized.
	 * One critical mistake the programmer made was making the "delete" function in "ArrayDeletion" class private.
	 * Essentially, there was no method to call the function to check its validity.
	 * Sir Atif's suggestion was applied here to confirm the code's working.
	 */
}
