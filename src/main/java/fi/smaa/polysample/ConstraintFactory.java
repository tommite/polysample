package fi.smaa.polysample;

import org.apache.commons.math.optimization.linear.LinearConstraint;
import org.apache.commons.math.optimization.linear.Relationship;

public class ConstraintFactory {

	/**
	 * Create a constraint expressing that w1 >= w2
	 * PRECOND: w1 >= 0 && w1 < dim
	 * PRECOND: w2 >= 0 && w2 < dim
	 * @param dim dimension
	 * @param w1 index of w1
	 * @param w2 index of w2
	 * @return
	 */
	public static LinearConstraint createOrdinalConstraint(int dim, int w1, int w2) {
		assert (w1 >= 0 && w1 < dim);
		assert (w2 >= 0 && w2 < dim);
		double[] coefficients = new double[dim];
		coefficients[w1] = -1;
		coefficients[w2] = 1;
		LinearConstraint c = new LinearConstraint(coefficients, Relationship.LEQ, 0.0);
		return c;
	}
	
	/**
	 * Create a constraint expressing that w1/w2 <= x
	 * PRECOND: w1 >= 0 && w1 < dim
	 * PRECOND: w2 >= 0 && w2 < dim
	 * @param dim dimension
	 * @param w1 index of w1
	 * @param w2 index of w2
	 * @param x the ratio upper bound
	 * @return
	 */
	public static LinearConstraint createUpperRatioConstraint(int dim, int w1, int w2, double x) {
		assert (w1 >= 0 && w1 < dim);
		assert (w2 >= 0 && w2 < dim);
		double[] coefficients = new double[dim];
		coefficients[w1] = 1;
		coefficients[w2] = -x;
		LinearConstraint c = new LinearConstraint(coefficients, Relationship.LEQ, 0.0);
		return c;
	}	
	
	/**
	 * Create a constraint expressing that w1/w2 >= x
	 * PRECOND: w1 >= 0 && w1 < dim
	 * PRECOND: w2 >= 0 && w2 < dim
	 * @param dim dimension
	 * @param w1 index of w1
	 * @param w2 index of w2
	 * @param x the ratio lower bound
	 * @return
	 */
	public static LinearConstraint createLowerRatioConstraint(int dim, int w1, int w2, double x) {
		assert (w1 >= 0 && w1 < dim);
		assert (w2 >= 0 && w2 < dim);
		double[] coefficients = new double[dim];
		coefficients[w1] = -1;
		coefficients[w2] = x;
		LinearConstraint c = new LinearConstraint(coefficients, Relationship.LEQ, 0.0);
		return c;
	}	
	
	/**
	 * Create a constraint expressing w1 <= bound
	 * PRECOND: bound <= 1.0 && bound >= 0.0
	 * @param dim
	 * @param w1
	 * @param bound
	 * @return
	 */
	public static LinearConstraint createUpperBoundConstraint(int dim, int w1, double bound) {
		assert(bound <= 1.0 && bound >= 0.0);
		
		double[] coefficients = new double[dim];
		coefficients[w1] = 1;
		LinearConstraint c = new LinearConstraint(coefficients, Relationship.LEQ, bound);
		return c;
	}
	
	/**
	 * Create a constraint expressing w1 >= bound
	 * PRECOND: bound <= 1.0 && bound >= 0.0
	 * @param dim
	 * @param w1
	 * @param bound
	 * @return
	 */
	public static LinearConstraint createLowerBoundConstraint(int dim, int w1, double bound) {
		assert(bound <= 1.0 && bound >= 0.0);
		
		double[] coefficients = new double[dim];
		coefficients[w1] = -1;
		LinearConstraint c = new LinearConstraint(coefficients, Relationship.LEQ, -bound);
		return c;
	}
	
}
