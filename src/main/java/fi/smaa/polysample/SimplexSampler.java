package fi.smaa.polysample;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math.linear.RealMatrix;
import org.apache.commons.math.optimization.linear.LinearConstraint;

public abstract class SimplexSampler {
	
	protected int dim;
	protected int nrSamples;
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
	
	public abstract RealMatrix sample();
}
