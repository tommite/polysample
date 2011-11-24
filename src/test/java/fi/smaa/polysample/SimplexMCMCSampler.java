package fi.smaa.polysample;

import java.util.List;

import org.apache.commons.math.linear.RealMatrix;
import org.apache.commons.math.linear.RealVector;
import org.apache.commons.math.optimization.linear.LinearConstraint;

public abstract class SimplexMCMCSampler extends SimplexSampler {

	protected RealVector startingPoint;
	protected int burnIn;
	protected int thinningFactor;

	/**
	 * PRECOND: burnin >= 0
	 * PRECOND: thinningFactor >= 1 (1 = no thinning)
	 * @param dim
	 * @param nrSamples
	 * @param burnIn
	 * @param thinningFactor
	 * @param startingPoint
	 */	
	public SimplexMCMCSampler(int dim, int nrSamples, int burnIn, int thinningFactor, RealVector startingPoint) {
		super(dim, nrSamples);
		this.burnIn = burnIn;
		this.thinningFactor = thinningFactor;
		this.startingPoint = startingPoint;
	}
	
	public RealVector getStartingPoint() {
		return startingPoint;
	}
	
	public int getBurnIn() {
		return burnIn;
	}
	
	public int getThinningFactor() {
		return thinningFactor;
	}
	
	@Override
	public RealMatrix sample() {
		Transformation tf = new Transformation(dim);
		List<LinearConstraint> tfConstraints = tf.getTransformedConstraints(additionalConstraints);
		RealMatrix chain = createChain(getNrChainPoints(), new LinearConstraintHitFunction(tfConstraints));
		return getSampledPoints(chain);
	}

	protected int getNrChainPoints() {
		return burnIn + nrSamples * thinningFactor;
	}
	
	private RealMatrix getSampledPoints(RealMatrix chain) {
		return null;
	}

	/**
	 * Create dim-1 dimensional points 
	 * @param nrPoints the amount of points desired
	 * @param hitFunction function to check for hits
	 */
	protected abstract RealMatrix createChain(int nrPoints, HitFunction hitFunction);
}
