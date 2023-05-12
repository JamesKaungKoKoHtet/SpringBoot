package co.hotel.model;

import lombok.Data;

@Data
public class Customer {
	private int user_id;
	private String name;
	private String mail;
	private String password;

}
