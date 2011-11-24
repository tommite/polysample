package fi.smaa.polysample;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.apache.commons.math.linear.RealMatrix;
import org.apache.commons.math.optimization.linear.LinearConstraint;
import org.apache.commons.math.optimization.linear.Relationship;
import org.junit.Before;
import org.junit.Test;

import fi.smaa.polysample.SimplexSampler;

public class SimplexSamplerTest {
	
	private SimplexSampler sampler;
	
	@Before
	public void setUp() {
		sampler = new SimplexSampler(3, 10000) {
			@Override
			public RealMatrix sample() {
				// TODO Auto-generated method stub
				return null;
			} };
	}

	@Test
	public void testGetNrSamples() {		
		assertEquals(10000, sampler.getNrSamples()); 
	}
	
	@Test
	public void testConstructor() {
		assertEquals(3, sampler.getDim());		
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
