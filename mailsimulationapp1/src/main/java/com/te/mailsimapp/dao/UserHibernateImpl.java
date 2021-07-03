package com.te.mailsimapp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.hibernate.cache.spi.support.EntityTransactionalAccess;

import com.te.mailsimapp.dto.MailBox;
import com.te.mailsimapp.dto.UserData;

public class UserHibernateImpl implements UserDAO{

	EntityManagerFactory factory=Persistence.createEntityManagerFactory("emailinfo");
	EntityManager manager=factory.createEntityManager();
	EntityTransaction transaction=manager.getTransaction();
	
	@Override
	public UserData compose(String from,String to) {
		String from_user=userEmailCheck(from);
		String to_user=userEmailCheck(to);
		if(from_user.equalsIgnoreCase(from) && to_user.equalsIgnoreCase(to)){
			try {
				
				transaction.begin();
				MailBox box=new MailBox();
			    box.setFromUser(from);
				box.setToUser(to);
				box.setSubject("job interview");
				box.setText("Your selected for 2nd round of technical interview. congragulation!!!!!!!");
				manager.persist(box);   
				transaction.commit();
				System.out.println("Mail delivered successfully"); 	
			    }catch (Exception e) {
				System.out.println("Mail delivered Failed");
				e.printStackTrace();
				transaction.rollback();
			}
		   
		}else {
			System.err.println("email address is not present");
		}
		return null;
	}

	private String userEmailCheck(String email) {
		try {
			String q="select email from UserData where email=:email";
			Query query=manager.createQuery(q);
			query.setParameter("email",email);
			String em= (String)query.getSingleResult();
			return em;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public UserData showBox(String email) {
		try {
			String q="from MailBox where fromUser=:email or toUser=:email ";
			Query query=manager.createQuery(q);
			query.setParameter("email",email);
			List<MailBox> mailboxs= (List<MailBox>)query.getResultList();
			for (MailBox mailBox : mailboxs) {
				System.out.println(mailBox.getToUser()+":");
				System.out.println(mailBox.getSubject());
				System.out.println(mailBox.getText());
				System.out.println("............................................");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@SuppressWarnings("null")
	@Override
	public UserData createAccount(String name, String password, String email) {
		 UserData userData = new UserData();
		try {
		
			transaction.begin();
			
			userData.setEmail(email);
			userData.setName(name);
			userData.setPassword(password);
			System.out.println(userData.getEmail());
			manager.persist(userData);
			transaction.commit();
			System.out.println("Inserted successfully"); 	
		    }catch (Exception e) {
			System.out.println("Insertion failed");
			e.printStackTrace();
			transaction.rollback();
		}
		return userData;
	}

	@Override
	public boolean validate(String emailinput, String passwordinput) {
		String q=" from UserData where email=:emailinput";
		Query query=manager.createQuery(q);
		query.setParameter("emailinput",emailinput);
		boolean isCorrect=false;
	    
		UserData userdata= (UserData)query.getSingleResult();
		if(userdata.getEmail().equalsIgnoreCase(emailinput) && userdata.getPassword().equalsIgnoreCase(passwordinput)){
			 isCorrect=true;
		}
		
		return isCorrect;
	}
    
}
