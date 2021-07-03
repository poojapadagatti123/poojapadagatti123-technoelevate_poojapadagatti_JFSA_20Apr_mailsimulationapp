package com.te.mailsimapp.dto;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;


@Data
@Table(name="mailbox")
@Entity
public class MailBox implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column
     private int emailId;
	@Column
     private String fromUser;
	@Column
     private String toUser;
	@Column
     private String subject;
	@Column
     private String text;
	
	@ManyToMany(cascade= {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.DETACH})
	@JoinTable(name="mail_info",joinColumns=@JoinColumn(name="emailId"),inverseJoinColumns = @JoinColumn(name="id"))
	private List<UserData> userdata;
}
