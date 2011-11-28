package fi.smaa.polysample;

import java.util.Arrays;

import org.apache.commons.math.linear.Array2DRowRealMatrix;
import org.apache.commons.math.linear.ArrayRealVector;
import org.apache.commons.math.linear.RealMatrix;
import org.apache.commons.math.linear.RealVector;
import org.apache.commons.math.random.UnitSphereRandomVectorGenerator;

public class AdaptiveHARSampler extends SimplexMCMCSampler {
	
	private UnitSphereRandomVectorGenerator dirGen;

	public AdaptiveHARSampler(int dim, int nrSamples, int burnIn, int thinningFactor, RealVector startingPoint) {
		super(dim, nrSamples, burnIn, thinningFactor, startingPoint);
		dirGen = new UnitSphereRandomVectorGenerator(dim-1);
	}

	/**
	 * PRECOND: nrPoints > 0
	 */
	@Override
	public RealMatrix createChain(int nrPoints, BoundingBox boundBox, HitFunction hitFunc){		
		assert(nrPoints > 0);
		
		RealMatrix pts = new Array2DRowRealMatrix(nrPoints, dim-1);
		// first point is the starting point
		pts.setRowVector(0, startingPoint);

		RealVector curPoint = startingPoint;
		for (int i=1;i<nrPoints;i++) {
			RealVector dir = new ArrayRealVector(dirGen.nextVector());
			
			RealVector lowBounds = new ArrayRealVector(boundBox.getMins());
			RealVector upBounds = new ArrayRealVector(boundBox.getMaxs());
			
			// get the length of line to sample to both directions
			double[] lbsd = lowBounds.subtract(curPoint).ebeDivide(dir).getData();
			double[] ubsd = upBounds.subtract(curPoint).ebeDivide(dir).getData();
			
			// bounds
			double bmin = Double.MAX_VALUE;
			double bmax = Double.MIN_VALUE;
			// sanity check
			for (int j=0;j<lbsd.length;j++) {
				bmax = Math.max(bmax, Math.min(lbsd[j], ubsd[j]));
				bmin = Math.min(bmin, Math.max(lbsd[j], ubsd[j]));
			}			
			
		}
		
		return pts;
	}

}
