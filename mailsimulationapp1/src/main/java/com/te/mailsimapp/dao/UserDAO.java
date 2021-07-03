package com.te.mailsimapp.dao;
import com.te.mailsimapp.dto.UserData;

public interface UserDAO {
	
	public UserData showBox(String email);
	public UserData createAccount(String name,String password,String email);
	public boolean validate(String email,String password);
	public UserData compose(String from, String to);
}
