package co.hotel.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.hotel.controller.helper.Helper;
import co.hotel.dto.RoomDto;
import co.hotel.mapper.RoomMapper;

@Service
public class RoomService {
	@Autowired
	RoomMapper _roomMapper;
	@Autowired
	LoginService _LoginService;

	public List<RoomDto> getRoomList() {
		int loginId = this._LoginService.loginCheck() ? this._LoginService.storedLogin() : 0;
		return this._roomMapper.getRoomList(loginId);
	}

	public void bookRooms(List<Integer> selectedRooms) {
		for (int i = 0; i < selectedRooms.size(); i++) {
			this._roomMapper.bookRooms(this._LoginService.storedLogin(),
					Integer.parseInt(selectedRooms.get(i).toString()), Helper.currentTime());
		}

	}
}
