package co.hotel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.hotel.dto.SignUpDto;
import co.hotel.mapper.CustomerMapper;

/**
 * 
 * @author james Service class that specifically handle signUp process
 */
@Service
public class SingUpService {
	@Autowired
	CustomerMapper _customerMapper;

	/**
	 * 
	 * @param signUp : SignUpDto from view model that came from controller after
	 *               validations are checked
	 * 
	 * @return checked if mail already exist and return false if so and if not
	 *         signUp and return true
	 */
	public boolean signUp(SignUpDto signUp) {
		if (this._customerMapper.findMail(signUp.getMail())) {
			return false;
		} else {
			this._customerMapper.signUp(signUp);
			return true;
		}

	}

}
