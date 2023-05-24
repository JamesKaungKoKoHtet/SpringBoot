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
import co.hotel.service.SingUpService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import java.util.List;

/**
 * 
 * @author james
 *
 */
@Controller
public class HotelController {
	@Autowired
	RoomService _roomService;
	@Autowired
	SingUpService _singUpService;
	@Autowired
	LoginService _loginService;

	/**
	 * 
	 * @param model : view model object check if logged in user exist and if so then
	 *              set user name into model attr get room list of RoomDto that has
	 *              [roomId , status , booked ] into model attr
	 * @return roomList view along with added attrs
	 */
	@GetMapping(value = "/")
	public String roomList(Model model) {
		if (this._loginService.loginCheck()) {
			model.addAttribute("user", this._loginService.loginUserName());
		}
		model.addAttribute("rooms", this._roomService.getRoomList());
		return "roomList";
	}

	/**
	 * 
	 * @param selectedRooms : selected room parameter from form tag Validation is
	 *                      handled on roomList view to select rooms before booking
	 * @param redirAttr     : to redirect flash attr for success model
	 * @return redirect to roomlist url "/"
	 */
	@PostMapping(value = "/booking")
	public String booking(@RequestParam List<Integer> selectedRooms, RedirectAttributes redirAttr) {
		this._roomService.bookRooms(selectedRooms);
		redirAttr.addFlashAttribute("bookedRooms", Helper.roomListText(selectedRooms));
		return "redirect:/";

	}

	/**
	 * 
	 * @param cancelRoom : selected roomId from roomlist view
	 * @return redirect to roomlist url "/"
	 */
	@PostMapping(value = "/cancel")
	public String booking(@RequestParam Integer cancelRoom) {
		this._roomService.checkOutRoom(cancelRoom);
		return "redirect:/";

	}

	/**
	 * 
	 * @param model : view model to add attr
	 * @return login view but if logged in user exist then redirect to roomlist u
	 */
	@GetMapping(value = "/login")
	public String login(Model model) {
		if (this._loginService.loginCheck()) {
			return "redirect:/";
		} else {
			model.addAttribute("login", new LoginDto());
			return "login";
		}
	}

	/**
	 * 
	 * @param model     : view model to add attr as error message
	 * @param login     : LoginDto from login view
	 * @param redirAttr : to add user name as flash attr for redirected view
	 * @return if login is failed than return login view else redirect to roomlist
	 *         view
	 */
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

	/**
	 * 
	 * @param session : to remove saved userName and userId in the session
	 * @return redirect to roomlist view
	 */
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("user");
		session.removeAttribute("userId");
		return "redirect:/";
	}

	/**
	 * 
	 * @param model : view model to add attr
	 * @return if logged in user exust then redirect to roomList view else show
	 *         signUp view
	 */
	@GetMapping("/singUp")
	public String signup(Model model) {
		if (this._loginService.loginCheck()) {
			return "redirect:/";
		}
		model.addAttribute("singUp", new SignUpDto());
		return "singUp";
	}

	/**
	 * 
	 * @param model : view model to add attr
	 * @param signUp : Validated SignUpDto from view
	 * @param result : BindingResult for validation 
	 * @param redirectAttr : to redirect SignUp success to Login view
	 * 
	 * @return 
	 * if password and confirmPassword is not matching then create new error field and return signUp view
	 * if validation of not empty and min password words are not correct then return signUp view
	 * if signUp is success then add signUpSuccess message as flash attr and redirect to login view
	 * 		else add signUpMailError model and return to signUp view becuaase the mail already exist
	 * 
	 */
	@PostMapping("/singUp")
	public String signup(Model model, @Valid @ModelAttribute("singUp") SignUpDto signUp, BindingResult result,
			RedirectAttributes redirectAttr) {
		System.out.println("called signup");
		if (!signUp.getPassword().equals(signUp.getConfirmPassword())) {
			result.addError(new FieldError("signup", "confirmPassword", "*パスワードが一致しません 。"));
			return "singUp";
		}
		if (result.hasErrors()) {
			return "singUp";
		}
		if (this._singUpService.signUp(signUp)) {
			redirectAttr.addFlashAttribute("singUpSuccess", "サインアップ成功！");
			return "redirect:/login";
		} else {
			model.addAttribute("singUpMailError", "err");
			return "singUp";
		}
	}
}
