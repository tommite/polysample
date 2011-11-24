package fi.smaa.prefsample;

import static org.junit.Assert.*;

import org.apache.commons.math.linear.ArrayRealVector;
import org.apache.commons.math.linear.RealVector;
import org.apache.commons.math.optimization.linear.LinearConstraint;
import org.apache.commons.math.optimization.linear.Relationship;
import org.junit.Before;
import org.junit.Test;

public class SimplexSamplerTest {
	
	private SimplexSampler sampler;
	private RealVector vec;
	
	@Before
	public void setUp() {
		vec = new ArrayRealVector(new double[] {1.0, 2.0, 3.0});		
		sampler = new SimplexSampler(10, vec) { };
	}

	@Test
	public void testGetNrSamples() {		
		assertEquals(10, sampler.getNrSamples()); 
	}
	
	@Test
	public void testConstructors() {
		assertEquals(vec, sampler.getStartingPoint());
		assertEquals(3, sampler.getDim());		
		sampler = new SimplexSampler(2, 20) { };
		assertEquals(null, sampler.getStartingPoint());
		assertEquals(2, sampler.getDim());
	}
	
	@Test
	public void testAddConstraint() {
		LinearConstraint lc = new LinearConstraint(new double[] { 2.0, 3.0}, Relationship.EQ, 2.0);
		LinearConstraint lc2 = new LinearConstraint(new double[] { 2.0, 4.0}, Relationship.EQ, 2.0);
		sampler.addConstraint(lc);
		sampler.addConstraint(lc2);
		assertEquals(2, sampler.getAdditionalConstraints().size());
		assertTrue(sampler.getAdditionalConstraints().contains(lc));
		assertTrue(sampler.getAdditionalConstraints().contains(lc2));
	}
}
