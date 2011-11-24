package fi.smaa.prefsample;
import static org.junit.Assert.*;

import org.apache.commons.math.optimization.linear.LinearConstraint;
import org.apache.commons.math.optimization.linear.Relationship;
import org.junit.Test;

import fi.smaa.prefsample.ConstraintFactory;


public class ConstraintFactoryTest {
	
	@Test
	public void testCreateOrdinalConstraint() {
		LinearConstraint c = ConstraintFactory.createOrdinalConstraint(5, 0, 2);
	
		assertEquals(Relationship.LEQ, c.getRelationship());
		assertEquals(0.0, c.getValue(), 0.000001);
		assertArrayEquals(new double[]{-1.0, 0.0, 1.0, 0.0, 0.0}, c.getCoefficients().getData(), 0.000001);
	}
	
	@Test
	public void testCreateUpperRatioConstraint() {
		LinearConstraint c = ConstraintFactory.createUpperRatioConstraint(5, 0, 2, 1.2);
	
		assertEquals(Relationship.LEQ, c.getRelationship());
		assertEquals(0.0, c.getValue(), 0.000001);
		assertArrayEquals(new double[]{1.0, 0.0, -1.2, 0.0, 0.0}, c.getCoefficients().getData(), 0.000001);
	}
	
	@Test
	public void testCreateLowerRatioConstraint() {
		LinearConstraint c = ConstraintFactory.createLowerRatioConstraint(5, 0, 2, 1.2);
	
		assertEquals(Relationship.LEQ, c.getRelationship());
		assertEquals(0.0, c.getValue(), 0.000001);
		assertArrayEquals(new double[]{-1.0, 0.0, 1.2, 0.0, 0.0}, c.getCoefficients().getData(), 0.000001);
	}	
	
	
	@Test
	public void testCreateUpperBoundConstraint() {
		LinearConstraint c = ConstraintFactory.createUpperBoundConstraint(5, 0, 0.8);
		
		assertEquals(Relationship.LEQ, c.getRelationship());
		assertEquals(0.8, c.getValue(), 0.000001);
		assertArrayEquals(new double[]{1.0, 0.0, 0.0, 0.0, 0.0}, c.getCoefficients().getData(), 0.000001);
	}
	
	@Test
	public void testCreateLowerBoundConstraint() {
		LinearConstraint c = ConstraintFactory.createLowerBoundConstraint(5, 0, 0.8);
		
		assertEquals(Relationship.LEQ, c.getRelationship());
		assertEquals(-0.8, c.getValue(), 0.000001);
		assertArrayEquals(new double[]{-1.0, 0.0, 0.0, 0.0, 0.0}, c.getCoefficients().getData(), 0.000001);
	}	

}
