package Models.Response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import Models.CompareOverview;
import Utilities.CompareException;
import Utilities.ReflectionUtility;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Document {
	@JsonProperty("web_url")
	public String WebUrl;
	
	@JsonProperty("snippet")
	public String Snippet;
	
	@JsonProperty("lead_paragraph")
	public String LeadParagraph;
	
	@JsonProperty("abstract")
	public String Abstract;
	
	@JsonProperty("print_page")
	public Integer PrintPage;
	
	@JsonProperty("blog")
	public Object[] Blog;
	
	@JsonProperty("source")
	public String Source;
	
	@JsonProperty("multimedia")
	public Multimedia[] Multimedia;
	
	@JsonProperty("headline")
	public Headline Headline;
	
	@JsonProperty("keywords")
	public KeyWord[] KeyWords;
	
	@JsonProperty("pub_date")
	public String PubDate;
	
	@JsonProperty("document_type")
	public String DocumentType;
	
	@JsonProperty("news_desk")
	public String NewsDesk;
	
	@JsonProperty("section_name")
	public String SectionName;
	
	@JsonProperty("byline")
	public Byline Byline;
	
	@JsonProperty("type_of_material")
	public String TypeOfMaterial;
	
	@JsonProperty("_id")
	public String Id;
	
	@JsonProperty("word_count")
	public Integer  WordCount;
	
	@JsonProperty("slide_show_credits")
	public String SlideShowCredits;
	
	@JsonProperty("score")
	public Integer  Score;
	
	
	/**path to the object in the JSON file**/
	private String location = "Docs";
	
	/** Compares this instance to provided object of the same time. Results are added to the provided overview object
	 * @param document Object to compare to this
	 * @param overview Stored the results
	 * @throws CompareException 
	 */
	public CompareOverview compare(Document document, CompareOverview overview) throws CompareException{
		
		//Use Reflection utility to compare all Strings and Integers of the class
		overview = ReflectionUtility.CompareObjectPrimatives(this, document, location, overview);

		//Compare non-primitive attributes
		overview = this.Headline.compare(document.Headline, overview);
		overview = this.Byline.compare(document.Byline, overview);
		overview = compareKeywords(document.KeyWords, overview);
		overview = compareMultimedia(document.Multimedia, overview);
		overview = compareBlogs(document.Blog, overview);;
		
		return overview;
	}
	
	
	private CompareOverview compareBlogs(Object[] blogs, CompareOverview overview) {
		
		//There is no example of what a blog object looks like, so we will just check the length of the array
		if(this.Blog.length != blogs.length)
			overview.addDetails(location, "Blog Length");
		
		return overview;
	}


	private CompareOverview compareMultimedia(Multimedia[] multimedia, CompareOverview overview) throws CompareException {
		
		int thisLength = this.Multimedia.length;
		int otherLength = multimedia.length;
		
		//Check if lengths are the same.
		if(thisLength != otherLength)
			overview.addDetails(location, "Multimedia Lengths");
		
		//Loop through array and add comparisons to the overview object
		for(int i = 0 ; i < Math.min(thisLength, otherLength); i++) {
			overview = this.Multimedia[i].compare(multimedia[i], overview, i);
		}
		
		return overview;
	}
	
	
	private CompareOverview compareKeywords(KeyWord[] keyWords, CompareOverview overview) throws CompareException {
		
		int thisLength = this.KeyWords.length;
		int otherLength = keyWords.length;
		
		//Check if lengths are the same
		if(thisLength != otherLength)
			overview.addDetails(location, "Keyword Lengths");

		//Loop through array and add comparisons to the overview object
		for(int i = 0 ; i < Math.min(thisLength, otherLength); i++) {
			overview = this.KeyWords[i].compare(keyWords[i], overview, i);
		}
		
		return overview;
	}
	
}
	
