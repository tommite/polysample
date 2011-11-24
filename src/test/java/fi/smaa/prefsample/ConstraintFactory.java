package fi.smaa.prefsample;

import org.apache.commons.math.optimization.linear.LinearConstraint;
import org.apache.commons.math.optimization.linear.Relationship;

public class ConstraintFactory {

	/**
	 * Create a constraint expressing that w1 >= w2
	 * @param dim dimension
	 * @param w1 index of w1
	 * @param w2 index of w2
	 * @return
	 */
	public static LinearConstraint createOrdinalConstraint(int dim, int w1, int w2) {
		double[] coefficients = new double[dim];
		coefficients[w1] = -1;
		coefficients[w2] = 1;
		LinearConstraint c = new LinearConstraint(coefficients, Relationship.LEQ, 0.0);
		return c;
	}
}
