package fi.smaa.polysample;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math.optimization.OptimizationException;
import org.apache.commons.math.optimization.linear.LinearConstraint;
import org.apache.commons.math.optimization.linear.Relationship;
import org.junit.Test;
import static org.junit.Assert.*;


@SuppressWarnings("deprecation")
public class BoundingBoxTest {
	
	@Test
	public void testBoundingBox() throws OptimizationException {
		LinearConstraint c1 = new LinearConstraint(new double[]{1.0, 0.0, 0.0}, Relationship.GEQ, 0.0);
		LinearConstraint c2 = new LinearConstraint(new double[]{0.0, 1.0, 0.0}, Relationship.GEQ, 0.0);
		LinearConstraint c3 = new LinearConstraint(new double[]{0.0, 0.0, 1.0}, Relationship.GEQ, 0.0);
		LinearConstraint c4 = new LinearConstraint(new double[]{1.0, 1.0, 1.0}, Relationship.EQ, 1.0);
		List<LinearConstraint> consts = new ArrayList<LinearConstraint>();
		consts.add(c1);
		consts.add(c2);
		consts.add(c3);
		consts.add(c4);
		BoundingBox box = new BoundingBox(consts);
		double[] mins = box.getMins();
		double[] maxs = box.getMaxs();
				
		assertArrayEquals(new double[] {0.0, 0.0, 0.0}, mins, 0.00001);
		assertArrayEquals(new double[] {1.0, 1.0, 1.0}, maxs, 0.00001);
	}
}
