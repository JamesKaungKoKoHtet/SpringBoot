package co.hotel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import co.hotel.controller.helper.Helper;
import co.hotel.dto.LoginDto;
import co.hotel.dto.SignUpDto;
import co.hotel.service.LoginService;
import co.hotel.service.RoomService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

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
		} else {
			model.addAttribute("login", new LoginDto());
			return "login";
		}
	}

	@PostMapping(value = "/login")
	public String login(Model model, @ModelAttribute("login") LoginDto login, RedirectAttributes redirAttr) {

		if (!this._loginService.login(login)) {
			model.addAttribute("error", "正しい認証情報を入力してください。");
			return "login";
		} else {
			redirAttr.addFlashAttribute("user", this._loginService.loginUserName());
			return "redirect:/";
		}

	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("user");
		session.removeAttribute("userId");
		return "redirect:/";
	}

	@GetMapping("/singUp")
	public String signup(Model model) {
		System.err.println("singup get called");
		model.addAttribute("singUp", new SignUpDto());
		return "singUp";
	}

	@PostMapping("/singUp")
	public String signup(Model model , @Valid @ModelAttribute("singUp") SignUpDto signup, BindingResult result) {
		System.err.println("singup post called");
		if (!signup.getPassword().equals(signup.getConfirmPassword())) {
			result.addError(new FieldError("signup", "confirmPassword", "*パスワードが一致しません 。"));
		}
		
		if(result.hasErrors()) {
			System.err.println("has error");
			return "singUp";
		}
		//do smth
		return "redirect:/";
	}
}
