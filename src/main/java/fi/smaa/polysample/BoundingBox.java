package fi.smaa.polysample;

import java.util.List;

import org.apache.commons.math.optimization.GoalType;
import org.apache.commons.math.optimization.OptimizationException;
import org.apache.commons.math.optimization.RealPointValuePair;
import org.apache.commons.math.optimization.linear.LinearConstraint;
import org.apache.commons.math.optimization.linear.LinearObjectiveFunction;
import org.apache.commons.math.optimization.linear.SimplexSolver;

@SuppressWarnings("deprecation")
public class BoundingBox {
	
	private List<LinearConstraint> constraints;
	private int dim;
	private double[] mins;
	private double[] maxs;

	/**
	 * PRECOND: polytope.size() > 0
	 * PRECOND: all constraints of the same dimensionality
	 * @param polytope
	 * @throws OptimizationException 
	 */
	public BoundingBox(List<LinearConstraint> polytope) throws OptimizationException {
		assert (polytope.size() > 0);
		dim = polytope.get(0).getCoefficients().getDimension();

		for (int i=1;i<polytope.size();i++) {
			assert(polytope.get(i).getCoefficients().getDimension() == dim);
		}
		
		this.constraints = polytope;
		computeMinMax();		
	}
	
	private void computeMinMax() throws OptimizationException  {
		mins = new double[dim];
		maxs = new double[dim];
		for (int i=0;i<dim;i++) {
			SimplexSolver solver = new SimplexSolver();
			double[] coeff = new double[dim];
			coeff[i] = 1;
			LinearObjectiveFunction objective = new LinearObjectiveFunction(coeff, 0);
			RealPointValuePair min = solver.optimize(objective, constraints, GoalType.MINIMIZE, true);
			RealPointValuePair max = solver.optimize(objective, constraints, GoalType.MAXIMIZE, true);
			mins[i] = min.getValue();
			maxs[i] = max.getValue();
		}
	}
	
	public double[] getMins() {
		return mins;
	}
	
	public double[] getMaxs() {
		return maxs;
	}	
}
