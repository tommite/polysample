package fi.smaa.polysample;

import org.apache.commons.math.linear.RealVector;

public interface HitFunction {
	/**
	 * See whether the given point hits within the polytope
	 * @param point the point to check
	 * @return true, is point belongs to the polytope, false otherwise
	 */
	public boolean hit(RealVector point);
}
