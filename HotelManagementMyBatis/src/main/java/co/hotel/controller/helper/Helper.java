package co.hotel.controller.helper;

import java.util.ArrayList;
import java.util.List;

import co.hotel.dto.RoomDto;
import co.hotel.entity.Room;

public class Helper {
	public static List<RoomDto> dtoToEntityRoom(List<Room> room) {
		List<RoomDto> dto = new ArrayList<>();

		for (Room r : room) {
			RoomDto rDto = new RoomDto();
			rDto.setRoom_id(r.getRoom_id());
			rDto.setStatus(r.get);
		
		
		}

		return null;
	}
}
