package Utilities;
import java.lang.reflect.Field;

import Models.CompareOverview;

/**
 * Reflection can be messy. To Keep Object Code clean, this Utility Class handles the use reflection techniques.
 *
 */
public class ReflectionUtility {

	
	/** This static method takes in Two Objects and compares all their 'Primitive' properties (Currently String and Integer). 
	 *  The results of these comparisons will be stored as CompareDetails in the provided CompareOverview object.
	 * @param objA Object to be compared
	 * @param objB Object to be compared
	 * @param location The string to be placed within the Details 'Location' attribute
	 * @param overview The CompareOverview where we will add our Comparison results
	 * @return An updated CompareOverview
	 * @throws CompareException Thrown if there is an issue comparing the objects
	 */
	public static CompareOverview CompareObjectPrimatives(Object objA, Object objB, String location, CompareOverview overview) throws CompareException {
		
		try {
			//If the two objects are not the same class, this method cannot process them
			if(!objA.getClass().equals( objB.getClass())) {
				throw new CompareException("Objects must be the same type to compare them");
			}
			
			//Get all the property fields for the given object
			Field[] fields = objA.getClass().getFields(); 
			
			//Loop through all fields and perform necessary comparison on the 'primitives'
			for(Field field : fields){
				
				//Check Strings
				if(field.getType() == String.class) {
					
					String stringA = (String) field.get(objA);
				    String stringB = (String) field.get(objB);
			
					if(!(stringA == null ? stringB == null : stringA.equals(stringB))) {
						 overview.addDetails(location, field.getName());
					}
					
				}
					
				//Check Integers
				else if(field.getType() == Integer.class) {
					Integer intA = (Integer) field.get(objA);
					Integer intB = (Integer) field.get(objB);
					
					if(!(intA == null ? intB == null : intA.equals(intB)))
						 overview.addDetails(location, field.getName());
				}
				
			}
			
		} catch (IllegalArgumentException | IllegalAccessException e) {
			throw new CompareException("Could Not Preform Primitve Comparison");
		}
		
		return overview;
	}
}
