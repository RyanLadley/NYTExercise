package Models.Response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import Models.CompareOverview;
import Utilities.CompareException;
import Utilities.ReflectionUtility;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LegacyMedia {
	
	@JsonProperty("thumbnailheight")
	public Integer  ThumbnailHeight;
	
	@JsonProperty("thumbnail")
	public String Thumbnail;
	
	@JsonProperty("thumbnailwidth")
	public Integer  ThumbnailWidth;
	
	@JsonProperty("xlargewidth")
	public Integer  XLargeWidth;
	
	@JsonProperty("xlarge")
	public String XLarge;
	
	@JsonProperty("xlargeheight")
	public Integer  XLargeHeight;
	
	@JsonProperty("widewidth")
	public Integer  WideWidth;
	
	@JsonProperty("wide")
	public String Wide;
	
	@JsonProperty("wideheight")
	public Integer  WideHeight;
	
	
	/** Compares this instance to provided object of the same time. Results are added to the provided overview object
	 * @param legacy Object to compare to this
	 * @param overview Stored the results
	 * @param index Index of this specific instance
	 * @return The updated overview object
	 * @throws CompareException 
	 */
	public CompareOverview compare(LegacyMedia legacy, CompareOverview overview, int index) throws CompareException {
		
		overview = ReflectionUtility.CompareObjectPrimatives(this, legacy,  "Docs.Multimedia["+index+"].Legacy", overview);
		
		return overview;
	}
}
