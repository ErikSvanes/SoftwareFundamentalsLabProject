package edu.ncsu.csc216.pack_scheduler.course;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


/**
 * Test class to test the functionality of the check conflict method in the Activity class.
 * @author Chance Cheatham
 */
class ActivityTest {

	/**
	 * Checks to ensure no exception is thrown for two activities with no overlap.
	 */
	@Test
	public void testCheckConflictFalse() {
	    Activity a1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 10, "MW", 1330, 1445);
	    Activity a2 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 10, "TH", 1330, 1445);
	    Activity a3 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 10, "A", 0, 0);
	    
	    assertDoesNotThrow(() -> a1.checkConflict(a2));
	    assertDoesNotThrow(() -> a2.checkConflict(a1));
	    assertDoesNotThrow(() -> a1.checkConflict(a3));
	    assertDoesNotThrow(() -> a3.checkConflict(a1));
	}
	
	/**
	 * Checks to ensure an exception is thrown for two activities with various time and day overlap.
	 */
	@Test
	public void testCheckConflictTrue() {
	    
	    Activity a1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 10, "MW", 1330, 1445);
	    Activity a2 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 10, "M", 1330, 1445);
		
	    Exception e1 = assertThrows(ConflictException.class, () -> a1.checkConflict(a2));
	    assertEquals("Schedule conflict.", e1.getMessage());
		
	    Exception e2 = assertThrows(ConflictException.class, () -> a2.checkConflict(a1));
	    assertEquals("Schedule conflict.", e2.getMessage());
	    
	    Activity a3 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 10, "MW", 1330, 1445);
	    Activity a4 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 10, "M", 1445, 1600);
		
	    Exception e3 = assertThrows(ConflictException.class, () -> a3.checkConflict(a4));
	    assertEquals("Schedule conflict.", e3.getMessage());
		
	    Exception e4 = assertThrows(ConflictException.class, () -> a4.checkConflict(a3));
	    assertEquals("Schedule conflict.", e4.getMessage());
	}

}
