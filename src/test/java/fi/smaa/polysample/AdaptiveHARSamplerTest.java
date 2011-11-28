package fi.smaa.polysample;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.apache.commons.math.linear.ArrayRealVector;
import org.apache.commons.math.linear.RealMatrix;
import org.apache.commons.math.linear.RealVector;
import org.apache.commons.math.optimization.OptimizationException;
import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("deprecation")
public class AdaptiveHARSamplerTest {
	
	private AdaptiveHARSampler sampler;
	private RealVector sp;
	
	@Before
	public void setUp() {
		sp = new ArrayRealVector(new double[] { 0.0, 0.2, 0.8} );
		sampler = new AdaptiveHARSampler(3, 10000, 0, 1, sp);
	}

	@Test
	public void testSample() throws OptimizationException {
		sampler = new AdaptiveHARSampler(3, 10000, 10, 2, null) { };
		RealMatrix pts = sampler.sample();
		assertEquals(10000, pts.getRowDimension());
	}
	
	@Test
	public void testCreateChain() {
		fail();
	}
}
