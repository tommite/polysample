package fi.smaa.polysample;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.Collections;
import java.util.List;

import org.apache.commons.math.linear.RealMatrix;
import org.apache.commons.math.optimization.linear.LinearConstraint;
import org.apache.commons.math.optimization.linear.Relationship;
import org.junit.Before;
import org.junit.Test;

import fi.smaa.polysample.ConstraintFactory;
import fi.smaa.polysample.Transformation;

public class TransformationTest {

	private Transformation t;
	private static final int dim = 3;

	@Before
	public void setUp() {
		t = new Transformation(dim);
	}
	
	@Test
	public void testGetBasis() {
		RealMatrix b = t.getBasis();
		assertEquals(2, b.getColumnDimension());
		assertEquals(3, b.getRowDimension());
		
		double[][] rd = b.transpose().multiply(b).getData();
		assertEquals(1, rd[0][0], 0.00000001);
		assertEquals(0, rd[0][1], 0.00000001);
		assertEquals(0, rd[1][0], 0.00000001);
		assertEquals(1, rd[1][1], 0.00000001);
	}
	
	@Test
	public void testGetTransformedConstraintsNoAdditionalOnes() {
		List<LinearConstraint> con = t.getTransformedConstraints();
		assertEquals(4, con.size());
		LinearConstraint lastCon = con.get(dim);
		assertArrayEquals(new double[]{0.0, 0.0, 1.0}, lastCon.getCoefficients().getData(), 0.00001);
		assertEquals(Relationship.EQ, lastCon.getRelationship());
		assertEquals(1.0, lastCon.getValue(), 0.000001);
	}
	
	@Test
	public void testGetTransformedConstraintsWithAdditionalOnes() {
		List<LinearConstraint> con = t.getTransformedConstraints(
				Collections.singletonList(ConstraintFactory.createOrdinalConstraint(dim, 0, 1)));
		assertEquals(5, con.size());
		LinearConstraint userCon = con.get(dim);
		assertEquals(3, userCon.getCoefficients().getDimension());
		assertEquals(0.7071068, Math.abs(userCon.getCoefficients().getData()[0]), 0.0001);
		assertEquals(1.2247449, Math.abs(userCon.getCoefficients().getData()[1]), 0.0001);
		assertEquals(0.0, userCon.getCoefficients().getData()[2], 0.0001);
		assertEquals(Relationship.LEQ, userCon.getRelationship());
		assertEquals(0.0, userCon.getValue(), 0.0001);
	}
	
}
