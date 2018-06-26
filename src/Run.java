import java.util.List;

import Models.CompareDetails;
import Models.CompareOverview;
import Utilities.CompareException;

public class Run {
	
	public static void main(String[] args) {

		if(args.length != 2) {
			System.out.println("Two Files Are Required To Run This Program.");
			System.out.println("Exiting");
			return;
		}

		try {
			
			JsonComparer comparer = new JsonComparer();
			List<CompareOverview> compareOverviews = comparer.CompareResultFiles(args[0], args[1]);
			
			printOverviews(compareOverviews);

			
		} catch (CompareException ex) { //Program caught and handled an exception. Print the message and exit the program
			System.out.println(ex.getMessage());
			
		}catch (Exception ex) { //If I screwed Up, this will catch an unknown exception and exit gracefully
			System.out.println("Oops. An Unknown Exception Occured");
		}
		finally {
			System.out.println("\nExiting");
		}

		
	}
	

	
	/** Prints the provided CompareOverviews in a stunning fashion (in columns)
	 * @param compareOverviews
	 */
	private static void printOverviews(List<CompareOverview> compareOverviews) {		
		System.out.println("---------------------------------------------------------");
		System.out.println("JSON Docs Diffrences");
		System.out.println("---------------------------------------------------------\n");
		
		for(CompareOverview overview: compareOverviews) {
			
			if(!overview.AreEqual) {
				System.out.println("Index: " + overview.Index);
				
				for(CompareDetails details : overview.Details) {
					System.out.printf("%-30.30s  %-30.30s%n", "\t" +details.Location, details.Details);
				}
			}
		}
	}

}
