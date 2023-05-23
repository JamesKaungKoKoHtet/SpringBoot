package co.hotel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import co.hotel.controller.helper.Helper;
import co.hotel.dto.LoginDto;
import co.hotel.service.LoginService;
import co.hotel.service.RoomService;
import java.util.List;

@Controller
public class HotelController {
	@Autowired
	RoomService _roomService;
	@Autowired
	LoginService _loginService;

	@GetMapping(value = "/")
	public String roomList(Model model) {
		if (this._loginService.loginCheck()) {
			model.addAttribute("user", this._loginService.loginUserName());
		}
		model.addAttribute("rooms", this._roomService.getRoomList());
		return "roomList";
	}

	@PostMapping(value = "/booking")
	public String booking(Model model, @RequestParam List<Integer> selectedRooms, RedirectAttributes redirAttr) {
		this._roomService.bookRooms(selectedRooms);
		redirAttr.addFlashAttribute("bookedRooms", Helper.roomListText(selectedRooms));
		return "redirect:/";

	}

	@PostMapping(value = "/cancel")
	public String booking(@RequestParam Integer cancelRoom) {
		this._roomService.checkOutRoom(cancelRoom);
		return "redirect:/";

	}

	@GetMapping(value = "/login")
	public String login(Model model) {
		if (this._loginService.loginCheck()) {
			return "redirect:/";
		}
		model.addAttribute("login", new LoginDto());
		return "login";
	}

	@PostMapping(value = "/login")
	public String login(@ModelAttribute LoginDto login, RedirectAttributes redirAttr) {
		this._loginService.login(login);
		redirAttr.addFlashAttribute("user", this._loginService.loginUserName());
		return "redirect:/";
	}


}
