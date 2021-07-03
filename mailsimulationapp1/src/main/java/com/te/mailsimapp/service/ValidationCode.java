package com.te.mailsimapp.service;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationCode {
    static Scanner sc=new Scanner(System.in);
	public static String emailValidation() {
		boolean counter;
		String email_id;
		String email;
		do {
			String regx ="^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}";  
			email_id= sc.next();
			Pattern pattern = Pattern.compile(regx, Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(email_id);
			if (matcher.find()) {
				email=email_id;
				counter = true;
				return email;
			} else {
				System.err.println("Enter a valid Email id");
				counter = false;
				return emailValidation();
			}
		} while (!counter);
	}
	
	public static String passwordValidation() {
		boolean counter;
		String password_id;
		String password;
		do {
			String regx = "^(?=.*[0-9])"
                    + "(?=.*[a-z])(?=.*[A-Z])"; 
//                    + "(?=.*[@#$%^&+=])"
//                   + "(?=\\S+$).{8,20}$"
			password_id= sc.nextLine();
			Pattern pattern = Pattern.compile(regx, Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(password_id);
			if (matcher.find()) {
				password=password_id;
				counter = true;
				return password;
			} else {
				System.err.println("Enter a valid Paasword");
				counter = false;
				return passwordValidation();
			}
		} while (!counter);
	}
   
	
}
