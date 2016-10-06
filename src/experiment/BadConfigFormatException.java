package experiment;

public class BadConfigFormatException extends Exception {
	BadConfigFormatException(){
		super("This file is not readable!");
	}
	BadConfigFormatException(String s){
		System.out.println("This is invalid: " + s);
	}
	
}
