package experiment;

public class BadConfigFormatException extends Exception {
	BadConfigFormatException(){
		System.out.println("This file is in a bad format!");
	}
	BadConfigFormatException(String s){
		System.out.println("This is invalid: " + s);
	}
	
}
