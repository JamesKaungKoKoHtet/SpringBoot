package co.hotel.entity;

import lombok.Data;

@Data
public class Customer {
	private int user_id;
	private String name;
	private String mail;
	private String password;

}
