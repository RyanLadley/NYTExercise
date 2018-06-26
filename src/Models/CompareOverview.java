package Models;

import java.util.ArrayList;
import java.util.List;

/**
 * Overview Object For Comparisons. Holds the Index and Whether or not the objects it is testing are equal.
 * Also Holds a detailed list of what the differences between the objects are.
 */
public class CompareOverview {

	public int Index;
	public boolean AreEqual;
	public List<CompareDetails> Details;
	
	public CompareOverview(int index) {
		Index = index;
		AreEqual = true;
		Details = new ArrayList<CompareDetails>();
	}
	
	
	/**
	 * Adds new Details to the object. If this is called, it means the objects are not equal, so 'AreEqual' is set accordingly
	 * @param location
	 * @param details
	 */
	public void addDetails(String location, String details) {
		AreEqual = false;
		Details.add(new CompareDetails(location, details));
	}
	
}
