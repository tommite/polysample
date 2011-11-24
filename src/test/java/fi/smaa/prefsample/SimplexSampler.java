package fi.smaa.prefsample;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math.linear.RealVector;
import org.apache.commons.math.optimization.linear.LinearConstraint;

public abstract class SimplexSampler {
	
	protected int dim;
	protected int nrSamples;
	protected RealVector startingPoint;
	protected List<LinearConstraint> additionalConstraints = new ArrayList<LinearConstraint>();
			
	/**
	 * Sampler without a starting point specified.
	 * 
	 * @param dim
	 * @param nrSamples
	 */
	public SimplexSampler(int dim, int nrSamples) {
		assert(dim > 0);
		this.dim = dim;
		this.nrSamples = nrSamples;
		startingPoint = null;
	}
	
	/**
	 * Sampler with defining the starting point. Dimension will be the same as for the starting point.
	 * 
	 * @param nrSamples
	 * @param startingPoint
	 */
	public SimplexSampler(int nrSamples, RealVector startingPoint) {
		dim = startingPoint.getDimension();
		this.nrSamples = nrSamples;
		this.startingPoint = startingPoint;
	}
	
	public int getDim() {
		return dim;
	}
	
	public void addConstraint(LinearConstraint cons) {
		additionalConstraints.add(cons);
	}
	
	public List<LinearConstraint> getAdditionalConstraints() {
		return additionalConstraints;
	}
	
	public int getNrSamples() {
		return nrSamples;
	}

	public RealVector getStartingPoint() {
		return startingPoint;
	}

}
