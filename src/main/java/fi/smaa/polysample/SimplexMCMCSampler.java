package fi.smaa.polysample;

import java.util.List;

import org.apache.commons.math.linear.Array2DRowRealMatrix;
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
		return getSampledPoints(chain, tf);
	}

	protected int getNrChainPoints() {
		return burnIn + nrSamples * thinningFactor;
	}
	
	private RealMatrix getSampledPoints(RealMatrix chain, Transformation tf) {
		assert(chain.getRowDimension() == getNrChainPoints()); // sanity check
		
		RealMatrix res = new Array2DRowRealMatrix(nrSamples, dim);
		
		int location = burnIn;
		for (int i=0;i<nrSamples;i++) {
			RealVector point = tf.transformBack(chain.getRowVector(location));
			res.setRowVector(i, point);
			location += thinningFactor;
		}
		return res;
	}

	/**
	 * Create dim-1 dimensional points 
	 * @param nrPoints the amount of points desired
	 * @param hitFunction function to check for hits
	 */
	protected abstract RealMatrix createChain(int nrPoints, HitFunction hitFunction);
}
