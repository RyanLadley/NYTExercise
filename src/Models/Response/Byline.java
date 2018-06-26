package Models.Response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import Models.CompareOverview;
import Utilities.CompareException;
import Utilities.ReflectionUtility;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Byline {

	@JsonProperty("person")
	public Person[] Person;
	
	@JsonProperty("original")
	public String Original;
	
	@JsonProperty("organization")
	public String Organization;
	
	/**path to the object in the JSON file**/
	private String location = "Docs.Byline";
	
	
	/** Compares this instance to provided object of the same time. Results are added to the provided overview object
	 * @param byline Object to compare to this
	 * @param overview Stored the results
	 * @return The updated overview object
	 * @throws CompareException 
	 */
	public CompareOverview compare(Byline byline, CompareOverview overview) throws CompareException{
		
		overview = ReflectionUtility.CompareObjectPrimatives(this, byline, location, overview);
		overview = comparePerson(byline.Person, overview);
		
		return overview;
	}
	
	private CompareOverview comparePerson(Person[] person, CompareOverview overview) throws CompareException {
		
		int thisLength = this.Person.length;
		int otherLength = person.length;
		
		if(thisLength != otherLength)
			overview.addDetails(location, "Person Lengths");
		
		for(int i = 0 ; i < Math.min(thisLength, otherLength); i++) {
			overview = this.Person[i].compare(person[i], overview, i);
		}
		
		return overview;
	}
	
}
