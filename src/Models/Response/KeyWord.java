package Models.Response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import Models.CompareOverview;
import Utilities.CompareException;
import Utilities.ReflectionUtility;

@JsonIgnoreProperties(ignoreUnknown = true)
public class KeyWord {
	
	@JsonProperty("isMajor")
	public String IsMajor;
	
	@JsonProperty("rank")
	public Integer Rank;
	
	@JsonProperty("name")
	public String Name;
	
	@JsonProperty("value")
	public String Value;
	
	/**path to the object in the JSON file**/
	private String location = "Docs.Keywords";
	
	/** Compares this instance to provided object of the same time. Results are added to the provided overview object
	 * @param keyWord Object to compare to this
	 * @param overview Stored the results
	 * @param index Index of this specific instance
	 * @return The updated overview object
	 * @throws CompareException 
	 */
	public CompareOverview compare(KeyWord keyWord, CompareOverview overview,int index ) throws CompareException {
		
		overview = ReflectionUtility.CompareObjectPrimatives(this, keyWord, location + "["+index+ "]", overview);
		
		return overview;
	}
}
