package co.hotel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.hotel.dto.LoginDto;
import co.hotel.entity.Room;
import co.hotel.service.LoginService;
import co.hotel.service.RoomService;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HotelController {
	@Autowired
	RoomService _roomService;
	@Autowired
	LoginService _loginService;


	@GetMapping(value = "/")
	public String roomList(Model model) {
		model.addAttribute("rooms", this._roomService.getRoomList());
		return "roomList";
	}

	@PostMapping(value = "/booking")
	public String booking(@RequestParam List<Integer> selectedRooms) {
		for(int i =0 ; i<selectedRooms.size(); i++) {
			System.out.println(selectedRooms.get(i)+"<<<<");
		}
		
//		if (this._loginService.loginCheck()) {
//			return "roomList";
//		} else {
//			return "redirect:/login";
//		}
		
		return "redirect:/";
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
