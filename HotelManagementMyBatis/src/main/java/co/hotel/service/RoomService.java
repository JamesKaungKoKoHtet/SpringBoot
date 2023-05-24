package co.hotel.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.hotel.controller.helper.Helper;
import co.hotel.dto.RoomDto;
import co.hotel.mapper.RoomMapper;
/**
 * 
 * @author james
 * Service class for booking room , cancel(checkout) room and getting room list
 */
@Service
public class RoomService {
	@Autowired
	RoomMapper _roomMapper;
	@Autowired
	LoginService _LoginService;

	/**
	 * if loginCheck return true then get loginId from storedLogin method else put 0 into loginId
	 * @return List of RoomDto objects that has [roomID , status , booked ] by searching with loginId
	 */
	public List<RoomDto> getRoomList() {
		int loginId = this._LoginService.loginCheck() ? this._LoginService.storedLogin() : 0;
		return this._roomMapper.getRoomList(loginId);
	}

	/**
	 * 
	 * @param selectedRooms : from roomList view parameter that user selected radio checkboxes 
	 * looped through selectedRoom to book room 
	 * validation is handled in view page to select room when submitting form
	 */
	public void bookRooms(List<Integer> selectedRooms) {
		for (int i = 0; i < selectedRooms.size(); i++) {
			this._roomMapper.bookRooms(this._LoginService.storedLogin(),
					Integer.parseInt(selectedRooms.get(i).toString()), Helper.currentTime());
		}

	}

	/**
	 * 
	 * @param cancelRoom : clicked roomId from roomList view
	 */
	public void checkOutRoom(Integer cancelRoom) {
		this._roomMapper.checkOutRoom(this._LoginService.storedLogin(),cancelRoom.intValue(),Helper.currentTime());
		
	}
}
