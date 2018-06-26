package Models.Response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import Models.CompareOverview;
import Utilities.CompareException;
import Utilities.ReflectionUtility;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Person {
	
	@JsonProperty("organization")
	public String Organization;
	
	@JsonProperty("role")
	public String Role;
	
	@JsonProperty("rank")
	public Integer Rank;
	
	@JsonProperty("firstname")
	public String FirstName;
	
	@JsonProperty("lastname")
	public String LastName;
	
	/**path to the object in the JSON file**/
	private String location = "Docs.Byline.Person";
	
	/** Compares this instance to provided object of the same time. Results are added to the provided overview object
	 * @param person Object to compare to this
	 * @param overview Stored the results
	 * @param index Index of this specific instance
	 * @return The updated overview object
	 * @throws CompareException 
	 */
	public CompareOverview compare(Person person, CompareOverview overview, int index) throws CompareException{
		overview = ReflectionUtility.CompareObjectPrimatives(this, person, location +"[" +index +"]", overview);
		
		return overview;
	}
}
