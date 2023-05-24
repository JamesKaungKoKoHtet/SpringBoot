package co.hotel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.hotel.dto.LoginDto;
import co.hotel.mapper.CustomerMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Service
public class LoginService {

	@Autowired
	CustomerMapper _customerMapper;
	@Autowired
	HttpServletRequest request;

	public boolean login(LoginDto login) {
		HttpSession session = request.getSession();
		try {
		session.setAttribute("userId",this._customerMapper.getIdByUserMailAndPassword(login.getMail(), login.getPassword()));
		return true;
		}
		catch (Exception e) {
			return false;
		}
	}

	public int storedLogin() {
		HttpSession session = request.getSession();
		return  Integer.parseInt(session.getAttribute("userId").toString());
	}

	public boolean loginCheck() {
		HttpSession session = request.getSession();
		Object obj = session.getAttribute("userId");
		if (obj == null) {
			return false;
		} else {
			return true;
		}
	}
	
	public String loginUserName() {
		return this._customerMapper.getUserNameById(storedLogin());
	}

}
