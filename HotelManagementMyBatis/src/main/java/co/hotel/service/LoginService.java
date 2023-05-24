package co.hotel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.hotel.dto.LoginDto;
import co.hotel.mapper.CustomerMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 * 
 * @author james Service class for Login ,checking login user and storing in
 *         session
 */
@Service
public class LoginService {

	@Autowired
	CustomerMapper _customerMapper;
	@Autowired
	HttpServletRequest request;

	/**
	 * 
	 * @param login :LoginDto from controller that came from view model
	 * @return true and set userID into session if getIdByUserMailAndPassword method
	 *         return userId or null if the matched mail and password catch error if
	 *         userID null and return false
	 * 
	 */
	public boolean login(LoginDto login) {
		HttpSession session = request.getSession();
		try {
			session.setAttribute("userId",
					this._customerMapper.getIdByUserMailAndPassword(login.getMail(), login.getPassword()));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 
	 * @return int of stored session attribute "userId"
	 */
	public int storedLogin() {
		HttpSession session = request.getSession();
		return Integer.parseInt(session.getAttribute("userId").toString());
	}

	/**
	 * 
	 * @return boolean if attribute is in session
	 */
	public boolean loginCheck() {
		HttpSession session = request.getSession();
		Object obj = session.getAttribute("userId");
		if (obj == null) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 
	 * @return user name of the userID that is returned from storedLogin method
	 */
	public String loginUserName() {
		return this._customerMapper.getUserNameById(storedLogin());
	}

}
