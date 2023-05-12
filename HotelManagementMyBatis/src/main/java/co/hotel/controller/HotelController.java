package co.hotel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import co.hotel.dto.LoginDto;
import co.hotel.entity.Room;
import co.hotel.service.LoginService;
import co.hotel.service.RoomService;

@Controller
public class HotelController {
	@Autowired
	RoomService _roomService;
	@Autowired
	LoginService _loginService;

	/**
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping(value = "/")
	public String roomList(Model model) {
		System.out.println("room called");
		model.addAttribute("rooms", this._roomService.getRoomList());
		return "roomList";
	}

	@PostMapping(value = "/booking")
	public String booking(Model model , @ModelAttribute("")Room rooms ) {
		System.out.println(rooms.getRoom_id());
		System.out.println(rooms.getRoom_id());
		System.out.println("login check is called");
		if (this._loginService.loginCheck()) {
			return "roomList";
		} else {
			return "redirect:/login";
		}
	}

	@GetMapping(value = "/login")
	public String login(Model model) {
		model.addAttribute("login", new LoginDto());
		return "login";
	}

	@PostMapping(value = "/login")
	public String login(@ModelAttribute LoginDto login) {
		System.out.println("login post is called");
		this._loginService.login(login);
		return "redirect:/";
	}


}
