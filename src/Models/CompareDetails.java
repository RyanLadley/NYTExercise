package Models;


/**
 * Holds the location (IE Object path) of the comparison and the detailed field of what is different
 *
 */
public class CompareDetails {

	public String Location;
	public String Details;
	
	public CompareDetails(String location, String details) {
		Location = location;
		Details = details;
	}
	
}
