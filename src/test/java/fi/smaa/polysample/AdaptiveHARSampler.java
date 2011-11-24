package fi.smaa.polysample;

import org.apache.commons.math.linear.Array2DRowRealMatrix;
import org.apache.commons.math.linear.RealMatrix;
import org.apache.commons.math.linear.RealVector;

public class AdaptiveHARSampler extends SimplexMCMCSampler {

	public AdaptiveHARSampler(int dim, int nrSamples, int burnIn, int thinningFactor, RealVector startingPoint) {
		super(dim, nrSamples, burnIn, thinningFactor, startingPoint);
	}

	@Override
	protected RealMatrix createChain(int nrPoints, HitFunction hitFunction) {
		int ndim = dim-1; // sample in n-1 dims
		
		RealMatrix pts = new Array2DRowRealMatrix(nrPoints, ndim);
		
		return pts;
	}

}
