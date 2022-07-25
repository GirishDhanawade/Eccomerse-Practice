package com.app.pojos;

import java.time.LocalDate;
import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Entity
@Table(name="users")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class User extends BaseEntity{
//id | name    | email          | password | reg_amt | reg_date   | role 
	
	private String name;
	private String email;
	private String password;
	@Column(name="reg_amt")
	private double regAmount;
	@Column(name="reg_date")
	private LocalDate regDate;
	@Enumerated(EnumType.STRING)
	private Role role;
	public Role getRole() {
		// TODO Auto-generated method stub
		return null;
	}
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}
	
		
}
