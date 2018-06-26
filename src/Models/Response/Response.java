package Models.Response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Response {
	
	//We don't currently care about the other fields, so we'll just add use the Docs
	
	@JsonProperty("docs")
	public Document[] Docs;
}
