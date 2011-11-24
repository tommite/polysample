package fi.smaa.prefsample;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.math.linear.Array2DRowRealMatrix;
import org.apache.commons.math.linear.MatrixUtils;
import org.apache.commons.math.linear.QRDecomposition;
import org.apache.commons.math.linear.QRDecompositionImpl;
import org.apache.commons.math.linear.RealMatrix;
import org.apache.commons.math.optimization.linear.LinearConstraint;
import org.apache.commons.math.optimization.linear.Relationship;

public class Transformation {
	
	private int dim;

	/**
	 * PRECOND: dim > 1
	 * @param dim dimension
	 */
	public Transformation(int dim) {
		assert(dim > 1);
		
		this.dim = dim;
	}

	public RealMatrix getBasis() {
		double[][] data = new double[dim][dim-1];
		
		for (int i=0;i<dim-1;i++) {
			data[i][i] = 1;
		}
		for (int i=0;i<dim-1;i++) {
			data[dim-1][i] = -1; 
		}
		Array2DRowRealMatrix origMat = new Array2DRowRealMatrix(data);
		QRDecomposition qr = new QRDecompositionImpl(origMat);
		return qr.getQ().getSubMatrix(0, dim-1, 0, dim-2);
	}
	
	@SuppressWarnings("unchecked")
	public List<LinearConstraint> getTransformedConstraints() {
		return getTransformedConstraints(Collections.EMPTY_LIST);
	}
	
	/**
	 * PRECOND: each additional constraints must be of correct dimension
	 * @param additionalConstraints
	 * @return
	 */
	public List<LinearConstraint> getTransformedConstraints(List<LinearConstraint> additionalConstraints) {
		
		// basic constraints, w[i] >= 0 (RHS = 0)
		RealMatrix basicConstraints = MatrixUtils.createRealIdentityMatrix(dim);
		
		// add 0 to constraints for the homogeneous coordinate representation
		RealMatrix constraints = new Array2DRowRealMatrix(dim+additionalConstraints.size(), dim+1);
		constraints.setSubMatrix(basicConstraints.getData(), 0, 0);
		
		// add additional constraints
		int additionalIndex = 0;
		for (LinearConstraint c : additionalConstraints) {
			assert(c.getCoefficients().getDimension() == dim);
			constraints.setRowVector(additionalIndex+dim, c.getCoefficients().append(0.0)); // append 0 for the homogenous
			additionalIndex++;
		}
		
		RealMatrix basis = getBasis();
		// augment basis with extra row and extra column both (0, 0, ..., 1)
		RealMatrix augBasis = new Array2DRowRealMatrix(basis.getRowDimension()+1, basis.getColumnDimension()+1);
		augBasis.setSubMatrix(basis.getData(), 0,0);
		augBasis.setEntry(augBasis.getRowDimension()-1, augBasis.getColumnDimension()-1, 1.0);
		
		RealMatrix translation = MatrixUtils.createRealIdentityMatrix(dim+1);
		for (int i=0;i<=dim;i++) {
			translation.setEntry(i, dim, 1.0/dim);
		}
		translation.setEntry(dim, dim, 1.0);
		
		// apply translation and basis transformation
		RealMatrix transConstraints = constraints.multiply(translation).multiply(augBasis);

		// return the constraints as LinearConstraints
		List<LinearConstraint> constList = new ArrayList<LinearConstraint>();
		int index = 0;		
		// add basic constraints
		while(index < dim) {
			constList.add(new LinearConstraint(transConstraints.getRowVector(index), Relationship.GEQ, 0.0));
			index++;
		}
		// add user constraints
		for (LinearConstraint c : additionalConstraints) {
			constList.add(new LinearConstraint(transConstraints.getRowVector(index), c.getRelationship(), c.getValue()));
			index++;
		}
		// add constraint that the homogeneous coordinate equals 1
		double[] comps = new double[dim];
		comps[dim-1] = 1.0;
		constList.add(new LinearConstraint(comps, Relationship.EQ, 1.0));
			
		return constList;
	}
}