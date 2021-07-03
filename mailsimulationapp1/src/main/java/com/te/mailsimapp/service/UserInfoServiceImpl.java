package com.te.mailsimapp.service;

import java.util.Scanner;

import com.te.mailsimapp.dao.UserDAO;
import com.te.mailsimapp.dao.UserHibernateImpl;
import com.te.mailsimapp.dto.UserData;

public class UserInfoServiceImpl implements UserService{
    Scanner sc=new Scanner(System.in);
    UserDAO dao=new UserHibernateImpl();
	@Override
	public UserData createAccount() {
		System.out.println("Enter your name");
		String name=sc.next();
		System.out.println("Enter Email address");
		String email=ValidationCode.emailValidation();
		System.out.println("Enter your Password");
		String password=sc.next();
				//ValidationCode.passwordValidation();
		return dao.createAccount(name, password, email);
		
		
	}

	@Override
	public UserData composed(String from) {
		System.out.println();
		System.out.print("To:");
		String to=sc.next();
		System.out.println();
		return dao.compose(from,to);
	}
	@Override
	public UserData showBox(String email) {
		return dao.showBox(email);
		
	}

   
}
