package com.te.mailsimapp.service;

import com.te.mailsimapp.dto.UserData;

public interface UserService {
	public UserData createAccount();
	public UserData showBox(String email);
	UserData composed(String from);
}
