package co.hotel.mapper;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import co.hotel.dto.RoomDto;
import co.hotel.entity.Room;

@Mapper
public interface RoomMapper {

	// public List<Room> getRoomList();
	public List<RoomDto> getRoomList(int id);

	public void bookRooms(int userId, int roomId, Timestamp date);

}
