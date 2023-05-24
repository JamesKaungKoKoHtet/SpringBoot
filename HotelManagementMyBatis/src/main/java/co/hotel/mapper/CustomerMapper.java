package co.hotel.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import co.hotel.dto.SignUpDto;
/**
 * 
 * @author james
 * Mapper class for customer table
 */
@Mapper
public interface CustomerMapper {
	/**
	 * 
	 * @param mail
	 * @param password
	 * @return id of user whose mail and password are matched
	 */
	public int getIdByUserMailAndPassword(@Param("mail") String mail, @Param("password") String password);

	/**
	 * 
	 * @param id : return data of storedLogin method that find stored session userId
	 * @return user name of the id from customer table
	 */
	public String getUserNameById(int id);
	
	/**
	 * 
	 * @param mail : from signUpDto that is in SignUpService for checking before sign up
	 * @return true if mail exist in the table else false
	 */

	public boolean findMail(@Param("mail") String mail);
	
	/**
	 * 
	 * @param signUp : from signUpDto that is in SignUpService for sign up
	 */
	public void signUp(SignUpDto signUp);
}
