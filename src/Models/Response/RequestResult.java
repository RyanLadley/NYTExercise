package Models.Response;

import com.fasterxml.jackson.annotation.JsonProperty;


/**
 *  Java Representation of the complete JSON file
 *
 */
public class RequestResult {
	
	@JsonProperty("response")
	public Response Response;
	
	@JsonProperty("status")
	public String Status;
	
	@JsonProperty("copyright")
	public String Copyright;
}
