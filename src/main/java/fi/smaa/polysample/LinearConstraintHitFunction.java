package fi.smaa.polysample;

import java.util.List;

import org.apache.commons.math.linear.RealVector;
import org.apache.commons.math.optimization.linear.LinearConstraint;

public class LinearConstraintHitFunction implements HitFunction {

	private List<LinearConstraint> constraints;

	public LinearConstraintHitFunction(List<LinearConstraint> constraints) {
		this.constraints = constraints;
	}
	
	@Override
	public boolean hit(RealVector point) {
		for (LinearConstraint c : constraints) {
			double prod = c.getCoefficients().dotProduct(point);
			switch(c.getRelationship()) {
			case GEQ:
				if (prod < c.getValue()) {
					return false;
				}
				break;
			case LEQ:
				if (prod > c.getValue()) {
					return false;
				}				
				break;
			case EQ:
				throw new IllegalArgumentException("EQ constraints not supported");
			}
		}
		return true;
	}

}
