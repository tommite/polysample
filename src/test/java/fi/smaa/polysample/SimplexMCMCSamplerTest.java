package fi.smaa.polysample;

import static org.junit.Assert.assertEquals;

import org.apache.commons.math.linear.ArrayRealVector;
import org.apache.commons.math.linear.RealMatrix;
import org.apache.commons.math.linear.RealVector;
import org.junit.Before;
import org.junit.Test;

import fi.smaa.polysample.HitFunction;
import fi.smaa.polysample.SimplexMCMCSampler;

public class SimplexMCMCSamplerTest {

	private SimplexMCMCSampler sampler;
	private RealVector sp;
	
	@Before
	public void setUp() {
		sp = new ArrayRealVector(new double[] { 0.0, 0.2, 0.8} );
		sampler = new SimplexMCMCSampler(3, 10000, 0, 1, sp) {
			@Override
			public RealMatrix createChain(int nrPoints, BoundingBox bounds,
					HitFunction hitFunc) {
				// TODO Auto-generated method stub
				return null;
			} };
	}
	
	@Test
	public void testGetStartingPoint() {
		assertEquals(sp, sampler.getStartingPoint());
	}
	
	@Test
	public void testGetBurnIn() {
		assertEquals(0, sampler.getBurnIn());
	}
	
	@Test
	public void testGetThinningFactor() {
		assertEquals(1, sampler.getThinningFactor());
	}
}
