package com.te.mailsimapp.controller;
import java.util.Scanner;

import com.te.mailsimapp.dao.UserDAO;
import com.te.mailsimapp.dao.UserHibernateImpl;
import com.te.mailsimapp.service.UserInfoServiceImpl;
import com.te.mailsimapp.service.UserService;
import com.te.mailsimapp.service.ValidationCode;

public class UserController {
	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		UserDAO dao=new UserHibernateImpl();
		UserService service=new UserInfoServiceImpl();
		while(true) {
			System.out.println("Enter the number");
			System.out.println("Press 1 to login");
			System.out.println("Press 2 to register");
			System.out.println("Press any number other than 1&2 for exit");
			int choice = sc.nextInt();
			switch (choice) {
				case 1: {
					
					System.out.println("Enter the email id");
					String email=ValidationCode.emailValidation();
					System.out.println("Enter the password");
					String password=sc.next();
						//	ValidationCode.passwordValidation();
					boolean isValid=dao.validate(email, password);
					if(isValid) {
						System.out.println("Your successfully logged in");
					}
					else {
						System.out.println("Provide proper email or password");
					}
						System.out.println("Press A for the compose");
						System.out.println("Press B for the inbox");
						String key = sc.next();
						switch (key) {
							case "A": {
								service.composed(email);
								
							//	userInfoServiceImpl2.compose(r_email_id);
								break;
							}
							case "B": {
								service.showBox(email);
								break;
							}
							default: {
								System.out.println("not possible");
								System.exit(0);
								break;
							}
						}//end of switch
					}//end of if
//					else {
//						System.out.println("Wrong and Not Possible");
//					}
//					break;
//				}
				case 2: {
					  service.createAccount();
					break;
				}
				default: {
					System.out.println("Bye");
					System.exit(0);
				}
			}
			System.out.println("--------------");
		}
	}
}
