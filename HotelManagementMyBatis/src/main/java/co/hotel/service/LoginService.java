package co.hotel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.hotel.mapper.CustomerMapper;
import co.hotel.model.Login;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Service
public class LoginService {

	@Autowired
	CustomerMapper _customerMapper;
	@Autowired
	HttpServletRequest request;

	public void login(Login login) {
		HttpSession session = request.getSession();
		int user_id = this._customerMapper.getIdByUserNameAndPassword(login.getMail(), login.getPassword());
		session.setAttribute("userId", user_id);
		System.out.println(session.getAttribute("userId"));
	}

	public String storedLogin() {
		HttpSession session = request.getSession();

		return session.getAttribute("userId").toString();
	}

	public boolean loginCheck() {
		HttpSession session = request.getSession();
		Object obj = session.getAttribute("userId");
		if (obj == null) {
			System.out.println("OBJ is null");
			return false;
		} else {
			System.out.println(obj.toString()+"<<<<<<<<<<<<<");
			return true;
		}
	}

}
