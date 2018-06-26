package Models.Response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import Models.CompareOverview;
import Utilities.CompareException;
import Utilities.ReflectionUtility;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Multimedia {
	
	@JsonProperty("width")
	public Integer Width;
	
	@JsonProperty("url")
	public String Url;
	
	@JsonProperty("rank")
	public Integer Rank;
	
	@JsonProperty("height")
	public Integer Height;
	
	@JsonProperty("subtype")
	public String Subtype;
	
	@JsonProperty("type")
	public String Type;
	
	@JsonProperty("legacy")
	public LegacyMedia Legacy;
	
	/**path to the object in the JSON file**/
	private String location = "Docs.Multimedia" ;
	
	/** Compares this instance to provided object of the same time. Results are added to the provided overview object
	 * @param multimedia Object to compare to this
	 * @param overview Stored the results
	 * @param index Index of this specific instance
	 * @return The updated overview object
	 * @throws CompareException 
	 */
	public CompareOverview compare(Multimedia multimedia, CompareOverview overview,int index ) throws CompareException {
		
		overview = ReflectionUtility.CompareObjectPrimatives(this, multimedia, location + "["+index+ "]", overview);
		overview = Legacy.compare(multimedia.Legacy, overview, index);
		
		return overview;
	}
}
