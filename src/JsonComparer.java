import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import Models.CompareOverview;
import Models.Response.RequestResult;
import Utilities.CompareException;

public class JsonComparer {

	
	/**
	 * This Method takes 2 paths to JSON files, then compares these files for specific differences. 
	 * These differences are then returned. 
	 * @param pathA Absolute path to JSON file
	 * @param pathB Absolute path to JSON file
	 * @return Returns The results of the comparison
	 * @throws CompareException Exception thrown if known Exception is caught
	 */
	public List<CompareOverview> CompareResultFiles(String pathA, String pathB) throws CompareException {
		
		RequestResult resultA = getRequestResultFromFile(pathA);
		RequestResult resultB = getRequestResultFromFile(pathB);

		List<CompareOverview> compareOverviews = new ArrayList<CompareOverview>();
		compareOverviews = compareResultDocuments(resultA, resultB, compareOverviews);
		
		return compareOverviews;
		
	}
	

	/**
	 * This method performs the comparison of two RequestResult objects. 
	 * Specifically, it compares compares the "docs" section of the two JSON files
	 * @param resultA RequestResult Object to Be Compared
	 * @param resultB RequestResult Object to Be Compared
	 * @param compareOverviews List of CompareOverview that stores our comparison results
	 * @return Results of the comparison
	 * @throws CompareException 
	 */
	public List<CompareOverview> compareResultDocuments(RequestResult resultA, RequestResult resultB, List<CompareOverview> compareOverviews) throws CompareException {
		
		int aLength = resultA.Response.Docs.length;
		int bLength = resultB.Response.Docs.length;
		
		//Check to see if the docs arrays are the same size between the two files
		if(aLength != bLength) {
			CompareOverview fileOverview = new CompareOverview(-1);
			fileOverview.addDetails("File", "Docs Length");
			compareOverviews.add(fileOverview);
		}
		
		//Loop Through Each doc (document) and add there comparisonOverview to our list
		//If the arrays are not the same size, we only loop up to the smaller of the lengths
		for(int i = 0; i < Math.min(aLength, bLength); i ++) {
			CompareOverview indexOverview = new CompareOverview(i);
			compareOverviews.add(resultA.Response.Docs[i].compare(resultB.Response.Docs[i], indexOverview));
		}
		
		return compareOverviews;
	}
	
	
	
	/**
	 * Takes the path to a JSON file, and returns this file parsed into a RequestResult object
	 * @param path Path to the JsonFile
	 * @return Parsed RequestResult Object
	 * @throws CompareException If there was an issue parsing or reading file, this exception will be thrown
	 */
	private RequestResult getRequestResultFromFile(String path) throws CompareException {
		
		try {
			
			String jsonString = readFile(path);
			
			//Jackson JSON Mapper Documentation: 
			//https://fasterxml.github.io/jackson-databind/javadoc/2.9/com/fasterxml/jackson/databind/ObjectMapper.html
			
			//Maps JSON to Solid Java Objects
			ObjectMapper objectMapper = new ObjectMapper();
			return objectMapper.readValue(jsonString, RequestResult.class);
			
		} catch (IOException e) {
			throw new CompareException("There was an error parsing " + path);
		}
	}
	
	
	
	/** Reads file associated with the provided path and returns its contents as a string
	 * @param path Path to file
	 * @return String of File contents
	 * @throws CompareException If the file cannot be read, this exception is thrown
	 */
	private String readFile(String path) throws CompareException {
		StringBuilder builder = new StringBuilder();
		Path filePath = Paths.get(path);
		try {
			
			Files.lines(filePath).forEach(
					line -> builder.append(line)
				);

			return builder.toString();
			
		} catch (IOException e) {
			throw new CompareException("There was an error reading the file " + filePath.toAbsolutePath().toString());
		}
	}
}
