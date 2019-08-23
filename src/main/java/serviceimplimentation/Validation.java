package serviceimplimentation;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
	private static Pattern pattern;
	private static Matcher matcher;
	private static final String PASSWORD_PATTERN ="^(?=.*[a-zA-Z])((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,})";
/*
->password should be of minimum length 8
->password should contain alphabets, numbers and atleast one special character
->the first character of password should be an alphabet
->This regular expression pattern is very useful to implement a strong and complex password.*/
	public Validation() {
		pattern = Pattern.compile(PASSWORD_PATTERN);
	}

	public Boolean passwordValidation(final String password) {
		  matcher = pattern.matcher(password);
		  return matcher.matches();
	    	    
	}

	public String passwordGeneration() {
		String password = "null";
		final Random RANDOM = new Random();
		String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		String digits = "0123456789";
		String splchar = "@#$%";
		StringBuilder returnValue = new StringBuilder(9);
		for (int i = 0; i < 4; i++) {
			returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
		}
		returnValue.append(digits.charAt(RANDOM.nextInt(digits.length())));
		returnValue.append(splchar.charAt(RANDOM.nextInt(splchar.length())));
		returnValue.append(digits.charAt(RANDOM.nextInt(digits.length())));
		returnValue.append(splchar.charAt(RANDOM.nextInt(splchar.length())));
		password = returnValue.toString();
		return password;
	}

}
