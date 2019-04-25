package com.java.string;

import java.util.HashMap;
import java.util.Iterator;

// 1. Declare the class as final so that it cannot be extended
public final class FinalClassDemo {

	// 2. Declare all the fields as private so that direct access is not allowed
	// 3. Make all the fields final so that value can be assigned only once.
	private final int id;
	private final String name;
	private final HashMap<String, String> testMap;

	// 4. Don't provide setter method for variables
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	// 5. Perform cloning of objects in the getter methods to return a copy
	// rather than returning the actual object reference.
	public HashMap<String, String> getTestMap() {
		// return testMap;
		return (HashMap<String, String>) testMap.clone();
	}

	// 6. Initialize all the fields via a constructor performing deep copy.
	public FinalClassDemo(int id, String name, HashMap<String, String> testMap) {
		this.id = id;
		this.name = name;

		// HashMap values get changed because of shallow copy in the constructor
		// and providing a direct reference to the original object in the getter
		// function (getTestMap()).
		// this.testMap=testMap;

		HashMap<String, String> tempMap = new HashMap<>();
		Iterator<String> iterator = testMap.keySet().iterator();
		String key = null;
		while (iterator.hasNext()) {
			key = iterator.next();

			tempMap.put(key, testMap.get(key));
		}

		this.testMap = tempMap;
	}
	
	/*Constructor performing Shallow Copy*/
	/*public FinalClassDemo(int id, String name, HashMap<String, String> testMap) {
		this.id=id;
		this.name=name;
		this.testMap=testMap;
	}*/
	
	

	/**
	 * To test the consequences of Shallow Copy and how to avoid it with Deep
	 * Copy for creating immutable classes
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		HashMap<String, String> h1 = new HashMap<String, String>();
		h1.put("1", "first");
		h1.put("2", "second");

		String s = "original";

		int i = 10;

		FinalClassDemo ce = new FinalClassDemo(i, s, h1);

		// Lets see whether its copy by field or reference
		System.out.println(s == ce.getName());
		System.out.println(h1 == ce.getTestMap());
		
		// print the ce values
		System.out.println("\n");
		System.out.println("ce id:" + ce.getId());
		System.out.println("ce name:" + ce.getName());
		System.out.println("ce testMap:" + ce.getTestMap());
		
		// change the local variable values
		i = 20;
		s = "modified";
		h1.put("3", "third");
		
		// print the values again
		System.out.println("\n");
		System.out.println("ce id after local variable change:" + ce.getId());
		System.out.println("ce name after local variable change:" + ce.getName());
		System.out.println("ce testMap after local variable change:" + ce.getTestMap());

		HashMap<String, String> hmTest = ce.getTestMap();
		hmTest.put("4", "new");
		
		System.out.println("\n");
		System.out.println("ce testMap after changing variable from accessor methods:" + ce.getTestMap());

	}
}
