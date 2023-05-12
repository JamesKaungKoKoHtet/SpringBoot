package co.hotel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.hotel.mapper.RoomMapper;
import co.hotel.model.Room;

@Service
public class RoomService {
	@Autowired
	RoomMapper _roomMapper;

	public List<Room> getRoomList() {

		return this._roomMapper.getRoomList();
	}
}
