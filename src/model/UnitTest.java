package model;
import static org.junit.Assert.*;

import org.junit.Test;

public class UnitTest {

	public static final double EPSILON = 1e-6;
			
	@Test
	public void constructor_LegalCase() {
		Unit testUnit = new Unit(3.1, 1.1, 5.9, "James O'Hara", 50, 50, 25, 55, true);
		assertEquals(3.1, testUnit.getPosition().getRealX(), EPSILON);
		assertEquals(1.1, testUnit.getPosition().getRealY(), EPSILON);
		assertEquals(5.9, testUnit.getPosition().getRealZ(), EPSILON);		
		
		assertEquals("James O'Hara", testUnit.getName());
		assertEquals(50, testUnit.getStrength());
		assertEquals(50, testUnit.getAgility());
		assertEquals(25, testUnit.getToughness());
		assertEquals(55, testUnit.getWeight());
		
		assertEquals(Math.PI/2.0, testUnit.getOrientation(), EPSILON);
		assertFalse(testUnit.canStartDefaultBehaviour());
		
		assertEquals(28, testUnit.getHitpoints(), EPSILON);
		assertEquals(28, testUnit.getStaminaPoints(), EPSILON);	
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void Constructor_IllegalName() throws Exception {
		new Unit(3.1, 1.1, 5.9, "james O'Hara", 50, 50, 25, 55, true);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void Constructor_IllegalPositionX() throws Exception {
		new Unit(53.1, 1.1, 5.9, "James O'Hara", 50, 50, 25, 55, true);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void Constructor_IllegalPositionY() throws Exception {
		new Unit(3.1, 51.1, 5.9, "James O'Hara", 50, 50, 25, 55, true);
	}

}
