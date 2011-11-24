package fi.smaa.polysample;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math.linear.ArrayRealVector;
import org.apache.commons.math.linear.RealVector;
import org.apache.commons.math.optimization.linear.LinearConstraint;
import org.junit.Before;
import org.junit.Test;

import fi.smaa.polysample.ConstraintFactory;
import fi.smaa.polysample.LinearConstraintHitFunction;

public class LinearConstraintHitFunctionTest {

	private LinearConstraint c1;
	private LinearConstraint c2;
	private LinearConstraintHitFunction hf;

	@Before
	public void setUp() {
		c1 = ConstraintFactory.createOrdinalConstraint(3, 0, 1); // w1 >= w2
		c2 = ConstraintFactory.createLowerBoundConstraint(3, 1, 0.4); // w2 >= 0.4
		List<LinearConstraint> cs = new ArrayList<LinearConstraint>();
		cs.add(c1);
		cs.add(c2);
		hf = new LinearConstraintHitFunction(cs);
	}
	
	@Test
	public void testHit() {
		RealVector v = new ArrayRealVector(new double[] { 0.5, 0.39, 0.11});
		assertFalse(hf.hit(v));
		v = new ArrayRealVector(new double[] { 0.4, 0.2, 0.4});
		assertFalse(hf.hit(v));
		v = new ArrayRealVector(new double[] { 0.5, 0.41, 0.09});
		assertTrue(hf.hit(v));				
	}
}
