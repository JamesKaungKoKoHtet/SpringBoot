package co.hotel.entity;

import lombok.Data;
/**
 * 
 * @author james
 * Entity class for customer
 */
@Data
public class Customer {
	private int userId;
	private String name;
	private String mail;
	private String password;

}
