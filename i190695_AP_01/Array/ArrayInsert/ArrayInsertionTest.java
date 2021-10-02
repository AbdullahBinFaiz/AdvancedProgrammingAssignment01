import static org.junit.Assert.*;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.junit.Test;

@FixMethodOrder(MethodSorters.DEFAULT)
public class ArrayInsertionTest {
	
	private int [] values;
	
	@Before
	public void Init() {
		values = new int[10];
	}
	
	// Does the function correctly insert value at a specified location in the array??
	@Test // (Should pass)
	public void TestInArrayInsertion() {
		values = ArrayInsertion.insert(values, 14, 6);
		int expectedValue = 14;
		
		Assert.assertEquals(expectedValue, values[6]);
	}
	
	// And insertion at the end?
	@Test // (Should pass)
	public void TestAtEndOfArray() {
		values = ArrayInsertion.insert(values, 12, 9);
		int expectedValue = 12;
		
		Assert.assertEquals(expectedValue, values[9]);
	}
	
	// Does the value get replaced if I insert it at the same index as the previous one?
	// If it doesn't get replaced, does it get right shifted?
	// I ran this test case it confirm whether the current value got shifted to right or got replaced. Therefore, this test case will fail one way or another.
	@Test
	public void TestReplacementInArray() {
		values = ArrayInsertion.insert(values, 19, 6);
		values = ArrayInsertion.insert(values, 25, 6);
		int expectedCurrentValue = 25;
		int expectedShiftedValue = 19;

		Assert.assertEquals(expectedCurrentValue, values[6]); // If this fails, the previous value was not replaced
		Assert.assertEquals(expectedShiftedValue, values[7]); // If this fails, the previous value was not shifted
	}
	
	// What if the array is full? Does it insert then?
	@Test // (Should pass, but will throw an error)
	public void TestFullArrayInsertion() {
		// First, instantiate an array which contains the same values as our "values" array will be populated with.
		int [] expectedArray = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		// Now, populate the values inside our values array with the same values as in the expected array
		int [] testValues = new int[10];
		for (int i = 0; i < 10; i++) {
			// Here is where population of values occurs in our array being tested.
			testValues = ArrayInsertion.insert(testValues, i+1, i);
		}	
		// We insert a value in the now full array
		testValues = ArrayInsertion.insert(testValues, 27, 6);
		// Now we test whether the insertion was possible or not
		// by checking if both arrays are equal or not
		Assert.assertArrayEquals(testValues, expectedArray);
		
		// * There is an error situated within the programmer's code.
		// * Whenever the last insertion is being done at any point, anywhere,
		// * the program will explicitly throw an ArrayIndexOutOfBounds exception. 
		// * This happens because the program, when right-shifting, starts from the
		// * size of the array + 1 as its starting index point.
		// * To simulate an example, I have an integer array of size 10.
		// * Up till the first 9 values, I will be able to insert without problems.
		// * When now I insert the value at index 9 of the array (sized 10), notice the function below:
		//   for(int i=size+1; i>indextoinsertat; i--){
		//       array[i] = array[i-1];
		//   }
		// * "indextoinsertat" is where I want to insert, and "size" increments as I insert a value inside the array.
		// * "size" at this final insertion step, is at the value 9, and my array's size is 10
		// * Array's indexing for an array of size 10 goes from 0 to 9.
		// * Over here, I'm assigning the index variable "i" size+1, which makes it 10.
		// * Being 10, this by definition becomes an out-of-bound error
		// * since the largest index I can have is 9, and here, I start with 10, so the
		// * lvalue variable throws an exception error.
		//
		// * For code coverage, lines 10-11 (inside the if condition) of "ArrayInsertion.java" never get checked because of this error.
		
	}
	/*
	 * After thorough testing and excluding the main function in "ArrayInsertion.java",
	 * code coverage for "ArrayInsertion.java" was at 90.2%,
	 * code coverage for "ArrayInsertionTest.java" was at 87.8%.
	 * One test case was failed intentionally to confirm during insertion whether replacement or shifting of existing value occurred.
	 * One critical bug was identified within array insertion, where it is impossible to fully populate the array, and thus, not verify a part of code's working. 
	 */
}
