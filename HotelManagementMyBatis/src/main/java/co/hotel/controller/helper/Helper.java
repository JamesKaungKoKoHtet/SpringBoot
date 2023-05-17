package co.hotel.controller.helper;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import co.hotel.dto.RoomDto;
import co.hotel.entity.Room;

public class Helper {
	public static List<RoomDto> dtoToEntityRoom(List<Room> room) {
		List<RoomDto> dto = new ArrayList<>();

		for (Room r : room) {
			RoomDto rDto = new RoomDto();
			rDto.setRoom_id(r.getRoom_id());
			rDto.setStatus(r.isStatus());
			// rDto.setBooked(false);
			dto.add(rDto);

		}

		return dto;
	}

	public static Timestamp currentTime() {
		Date currentDate = new Date();
		return new Timestamp(currentDate.getTime());
	}

	public static String selectedRoomList(List<Integer> selectedRooms) {
		String selectedRoomList ="";
		for(int i : selectedRooms) {
			selectedRoomList+="Room "+i+", ";
		}
		return selectedRoomList+"を予約しました。";
	}
}
