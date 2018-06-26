# NYT Exercise

This Java project was written to solve the NYT take home assignment. 

## Built With
* [Java 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) 
* [Eclipse](https://www.eclipse.org/) - IDE
* [Jackson](https://github.com/FasterXML/jackson) - JSON Parser
* [JUnit](https://junit.org/junit5/) - Unit Testing

## My Approach

This problem was solved in three basic steps. 

1. Read and parse json files into solid Java objects.
2. Preform a deep compare of the two objects.
3. Print out the findings of the compare. 


### Read And Parse
This part is trivial. I read the file into a string using Java's standard File reader.

``` Java
Files.lines(filePath).forEach(
	line -> builder.append(line)
);
```

I then passed this string into the [Jackson JSON library](https://github.com/FasterXML/jackson) to parse the JSON into a solid object with I call `RequestResult`

``` Java
ObjectMapper objectMapper = new ObjectMapper();
return objectMapper.readValue(jsonString, RequestResult.class);
```

### Deep Compare

This is the meat and potatoes of the project. Within RequestResult.Response object, there is an Array called "Docs".  The class of this array, `Document`, has a method called `compare` This method takes another `Document` object and preforms an attribute by attribute comparison of the two objects. Primitives are compared using reflection, while the other solid objects have there own `compare` which follow the same templateThe results of this comparison are stored in another object called `CompareOverview`. 

### Print The Results
At the end of the program, a final method is called. `printCompareOverview` takes a `CompareOverview` object and prints it in stunning columns for your viewing pleasure.


## Testing

Along with normal spot checking,  there are several JUnit unit tests which aided in the testing of this program. These can be found in 'test' folder at the base of this project. Intuitive, I know. 

## Why did you do that?

There are a few items in this code which may have you asking "Why did  you do that?". Let's see if I can answer a few of those.

### Solid Objects
Instead of using somethins like JSON.simple's JSONObject, I decided to create a solid Java object for processing. I did this for one simple reason, I find objects like JSONObjects to be error prone since there is very little compiler/IDE support when using these object. I was definetly questioning myself when creating these objects though...

### Reflection

Instead of using a bunch of If statements to compare the 'primitive' attributes of the objects, I decided to create a Reflection utility. I did this for two reasons. 
1. It kept the code DRYer. There were a lot of comparisons to be made, this utility funneled the bulk of them into one spot where changes could be made easily. This came in handy when in my spot and unit testing I discovered I missed a few cases

2. Honestly, it was more fun. Writing dozens of comparions sounded tedious. Since I come from a largly C# background, I thought this would be wonderful time to learn how Java does reflection. I was right.

### CompareOverview

Instead of printing out the results as the program goes along, I decided to wait until the end to do it. This allows this simple application to have a small sample of "seperation of concerns". For this program that, means that the `compare` methods whole role is to populate the `CompareOverview` objects. It is up to the rest of the program to determine what to do with that information, which for us is simply to print it out.

## What Would I Have Done Differently

### Maven
I really dislike having random jar files sitting in a repository that have to be passed from dev to dev. Starting over, I probably would opt to use Maven, or some sort package manager  to help keep the repo clean.