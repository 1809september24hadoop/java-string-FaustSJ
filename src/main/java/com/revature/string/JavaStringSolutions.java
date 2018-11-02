package com.revature.string;

import org.apache.log4j.Logger;

public class JavaStringSolutions implements JavaString {
	
	private static final Logger LOGGER = Logger.getLogger(JavaStringSolutions.class);

	@Override
	//converts the given string into an integer
	public int parseInteger(String number) throws IllegalArgumentException {
		//make sure they didn't send an empty or null string
		try {
			if((number==null)||(number.isEmpty())){
				throw new IllegalArgumentException();
			}
		}catch (IllegalArgumentException iae) {
			LOGGER.error("Cannot parse an empty string!");
		}
		
		//parse each char into an int to be combined later
		char[] charArr = number.toCharArray();
		int[] intArr = new int[number.length()];
		for(int i = 0; i<charArr.length; i++) {
			//make sure the character is a number
			try {
				if(!Character.isDigit(charArr[i])){
					throw new IllegalArgumentException();
				}
			}catch (IllegalArgumentException iae) {
				LOGGER.error("Cannot parse a letter into an int!");
			}
			
			//get and store the number
			intArr[i] = Character.getNumericValue(charArr[i]);
		}
		//now we have the list of intArr representing the digits of a single number
		
		int finalNumber = 0;
		int tens = 1;
		//we simply take each number, multiply it by is tens place, and add them all together
		for(int j = (intArr.length-1); j>=0; j--) {
			finalNumber += intArr[j]*tens;
			tens *= 10;
		}
		
		return finalNumber;
	}

	@Override
	//reverses and returns the given string
	public String reverse(String string) throws IllegalArgumentException {
		//make sure they didn't send an empty or null string
		try {
			if((string==null)||(string.isEmpty())){
				throw new IllegalArgumentException();
			}
		}catch (IllegalArgumentException iae) {
			LOGGER.error("Cannot reverse an empty string!");
		}
		
		char[] charArr = string.toCharArray();
		String reversed = new String("");
		
		for(int i = (charArr.length-1); i>=0; i--) {
			reversed += charArr[i];
		}
		
		return reversed;
	}

	@Override
	public void time(StringBuilder builder, StringBuffer buffer) throws IllegalArgumentException {
		//make sure they didn't send a null builder or buffer
		try {
			if((builder==null)||(buffer==null)){
				throw new IllegalArgumentException();
			}
		}catch (IllegalArgumentException iae) {
			LOGGER.error("Cannot append null!");
		}  
		
		//run and time Builder
		long startTimeBuilder = System.currentTimeMillis();
		for(int i = 0; i<1000000; i++) {
			builder.append("meowth");
		}
		long endTimeBuilder =  System.currentTimeMillis();
		
		//run and time Buffer
		long startTimeBuffer = System.currentTimeMillis();
		for(int i = 0; i<1000000; i++) {
			buffer.append("meowth");
		}
		long endTimeBuffer =  System.currentTimeMillis();
		
		//compare the times and print the results
		if((endTimeBuilder-startTimeBuilder)>(endTimeBuffer-startTimeBuffer)) {
			LOGGER.info("Buffer was faster than Builder by " + ((endTimeBuilder-startTimeBuilder)-(endTimeBuffer-startTimeBuffer)) + "ms");
		}else {
			LOGGER.info("Builder was faster than Buffer by " + ((endTimeBuffer-startTimeBuffer)-(endTimeBuilder-startTimeBuilder)) + "ms");
		}
	}

	@Override
	public boolean itContains(String string, String contains) throws IllegalArgumentException {
		//make sure they didn't send an empty or null string
		try {
			if((string==null)||(string.isEmpty())){
				throw new IllegalArgumentException();
			}
		}catch (IllegalArgumentException iae) {
			LOGGER.error("Cannot search within an empty string!");
		}
		
		return string.contains(contains); //since String IS A CharSequence
	}

	@Override
	//numerically and alphabetically sorts the given string
	public String sort(String string) throws IllegalArgumentException {
		//make sure they didn't send an empty or null string
		try {
			if((string==null)||(string.isEmpty())){
				throw new IllegalArgumentException();
			}
		}catch (IllegalArgumentException iae) {
			LOGGER.error("Cannot sort an empty string!");
		}
		
		char[] original = string.toCharArray();
		char[] sorted = new char[original.length];
		char currentMax;
		int maxIndex = 0;
		
		//takes one value out at a time and puts them in increasing order in the sorted array.
		for(int i=(original.length-1); i>=0; i--) {
			
			currentMax = 'a';
			for(int m=0; m<original.length; m++) {
				if(original[m]>currentMax) {
					currentMax = original[m];
					maxIndex = m;
				}
			}
			
			sorted[i] = currentMax;
			original[maxIndex] = 'a';
		}
		
		return new String(sorted);
	}

	@Override
	public String delete(String string, char c) throws IllegalArgumentException {
		//make sure they didn't send an empty or null string
		try {
			if((string==null)||(string.isEmpty())){
				throw new IllegalArgumentException();
			}
		}catch (IllegalArgumentException iae) {
			LOGGER.error("Cannot delete from an empty string!");
		}
		
		char[] original = string.toCharArray();
		char[] modified = new char[original.length];
		
		int modifiedIndex = 0;
		for(int i=0; i<original.length; i++) {
			
			if(original[i]!=c) {
				modified[modifiedIndex] = original[i];
				modifiedIndex++;
			}
		}
		
		String srt = new String(modified);
		
		
		return srt.trim();	
	}

	@Override
	public String upperLower(String string) throws IllegalArgumentException {
		//make sure they didn't send an empty, null, or uneven string
		try {
			if((string==null)||(string.isEmpty())||((string.length()%2)!=0)){
				throw new IllegalArgumentException();
			}
		}catch (IllegalArgumentException iae) {
			LOGGER.error("Cannot modify an empty, null, or uneven string!");
		}
		
		String firstHalf = string.substring(0, (string.length()/2)); //the endIndex is exclusive!!
		String secondHalf = string.substring(string.length()/2);
		
		return firstHalf.toUpperCase().concat(secondHalf.toLowerCase());
	}
	
	public static void main(String[] args) {
		JavaStringSolutions jss = new JavaStringSolutions();
		
		LOGGER.info(jss.parseInteger("586213")); //586213
		
		LOGGER.info(jss.reverse("Yolo")); //oloY
		
		LOGGER.info(jss.sort("lskdjfahs")); //adfhjklss
		
		String str = jss.delete("asldksjfnsdn", 'd');
		LOGGER.info(str + " of length " + str.length()); //aslksjfnsn of length 10
		
		StringBuilder sbl = new StringBuilder();
		StringBuffer sbf = new StringBuffer();
		jss.time(sbl, sbf);
		
		LOGGER.info(jss.upperLower("aSdFgEasIN")); //ASDFGeasin
		
		LOGGER.info(jss.itContains("ImHappy Go", "ppy Go")); //true
	}

}
