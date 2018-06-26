package Models.Response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import Models.CompareOverview;
import Utilities.CompareException;
import Utilities.ReflectionUtility;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Headline {
	
	@JsonProperty("main")
	public String Main;
	
	@JsonProperty("print_headline")
	public String PrintHeadline;
	
	/**path to the object in the JSON file**/
	private String location = "Docs.Headline";
	
	/** Compares this instance to provided object of the same time. Results are added to the provided overview object
	 * @param headline Object to compare to this
	 * @param overview Stored the results
	 * @return The updated overview object
	 * @throws CompareException 
	 */
	public CompareOverview compare(Headline headline, CompareOverview overview) throws CompareException{
		overview = ReflectionUtility.CompareObjectPrimatives(this, headline, location, overview);
		
		return overview;
	}
}

