import static org.junit.Assert.*;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.junit.Test;

// As with all other conveniently and similarly named files, the name had to be changed so Eclipse wouldn't throw an error.
// This set of test cases will go with "LinkedList.java" inside "LinkedListImplementation" folder, if you're confused.
// Unfortunately, this is the only LinkedList file that has return types other than void,
// so test cases for "LinkedList" are extremely limited.

@FixMethodOrder(MethodSorters.DEFAULT)
public class LinkedListWorkingTest {

	private LinkedListWorking listObj;
	
	@Before
	public void Init() {
		listObj = new LinkedListWorking();
	}
	
	/*
	 * The problem is that the data members inside the class are statically defined.
	 * Moreover, ONLY the next pointer is equated to null when the constructor is called.
	 * This makes an issue arise where multiple test cases will lead to almost all test cases
	 * being failed and/or throwing exceptions. Since the head and tail pointers are statically
	 * defined, no matter how many objects of the class are instantiated, the head and tail pointers
	 * will always remain in the main position regardless of what object you're controlling.
	 */
	
	// Let's try testing all of the functions within one single test case.
	@Test
	public void TestAll() throws Exception {
		int [] listArray = {5, 2, 9, 7 ,21, 15, 3, 19};
		for (int i = 0; i < 8; i++) {
			listObj.add(listArray[i]);
		}
		listObj.display();
		int [] expectedArray = {5, 2, 9, 7 ,21, 15, 3, 19};
		int [] actualArray = listObj.getArray();
		
		Assert.assertArrayEquals(expectedArray, actualArray);
		
		// Pop out all of the values inserted
		for (int i = 0; i < 8; i++) {
			listObj.popFront();
		}
		
		listArray[0] = 6;
		listArray[1] = 8;
		listArray[2] = 2;
		listArray[3] = 4;
		listArray[4] = 2;
		listArray[5] = 3;
		listArray[6] = 12;
		listArray[7] = 9;
		
		// Insert them through another function.
		for (int i = 0; i < 8; i++) {
			listObj.pushFront(listArray[i]);
		}
		listObj.display();
		// Set up the expected array to be reverse of listArray
		for (int i = 0; i < 8; i++) {
			expectedArray[i] = listArray[listArray.length-i-1];
		}
		actualArray = listObj.getArray();
		
		Assert.assertArrayEquals(expectedArray, actualArray);
		
		// See if topFront and topBack work
		int expectedFrontValue = 9;
		int expectedBackValue = 6;
		int actualFrontValue = listObj.topFront();
		int actualBackValue = listObj.topBack();
		
		Assert.assertEquals(expectedFrontValue, actualFrontValue);
		Assert.assertEquals(expectedBackValue, actualBackValue);
		
		// Let's test is the size function returns correctly.
		for (int i = 0; i < 5; i++) {
			listObj.popFront();
		}
		int expectedSize = 3;
		int actualSize = listObj.size();
		
		Assert.assertEquals(expectedSize, actualSize);
		
		for (int i = 0; i < 3; i++) {
			listObj.popFront();
		}
		
		// Do topBack, topFront, and popFront functions throw exceptions properly?
		// For topBack:
		try {
			listObj.topBack();
		}
		catch (Exception e) {
			String expectedMessage = "LinkedList is empty!";
			String actualMessage = e.getMessage();
			Assert.assertEquals(expectedMessage, actualMessage);
		}
		// For topFront:
		try {
			listObj.topFront();
		}
		catch (Exception e) {
			String expectedMessage = "LinkedList is empty";
			String actualMessage = e.getMessage();
			Assert.assertEquals(expectedMessage, actualMessage);
		}
		// For popFront:
		try {
			listObj.popFront();
		}
		catch (Exception e) {
			String expectedMessage = "LinkedList is empty";
			String actualMessage = e.getMessage();
			Assert.assertEquals(expectedMessage,  actualMessage);
		}
		
		// Finally, one more thinkg to check is to use pushBack function
		listArray[0] = 15;
		listArray[1] = 2;
		listArray[2] = 67;
		listArray[3] = 8;
		listArray[4] = 20;
		listArray[5] = 5;
		listArray[6] = 31;
		listArray[7] = 49;
		
		for (int i = 0; i < 8; i++) {
			listObj.pushBack(listArray[i]);
		}
		for (int i = 0; i < 8; i++) {
			expectedArray[i] = listArray[i];
		}
		actualArray = listObj.getArray();
		
		Assert.assertArrayEquals(expectedArray, actualArray);
	}
	
	/*
	 * After a single thorough test involving multiple functions usage,
	 * code coverage for "LinkedListWorking.java" was at 100%,
	 * code coverage for "LinkedListWorkingTest.java" was at 95.9%.
	 * "LinkedListImplementation.java" was not included as it only contained the main function.
	 * Any errors that were identified were handled with exceptions. However,
	 * it still remains that statically declaring the pointers caused problems in
	 * instantiating new objects which caused virtually no change in the previous
	 * objects' list. If working with multiple linked list objects of this class,
	 * this is a serious issue and can cause the system to not crash, but to
	 * produce invalid answers which can cause more confusion.
	 * What makes this more confusing is that other LinkedList classes also have
	 * static declarations of data members, yet do not suffer from the same problem.
	 */
	
}
