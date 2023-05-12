package co.hotel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.hotel.controller.helper.Helper;
import co.hotel.dto.RoomDto;
import co.hotel.mapper.RoomMapper;
import co.hotel.entity.Room;

@Service
public class RoomService {
	@Autowired
	RoomMapper _roomMapper;

	public List<RoomDto> getRoomList() {

		return Helper.dtoToEntityRoom(this._roomMapper.getRoomList());
	}
}
