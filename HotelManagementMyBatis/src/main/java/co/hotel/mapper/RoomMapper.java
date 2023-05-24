package co.hotel.mapper;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import co.hotel.dto.RoomDto;

@Mapper
public interface RoomMapper {

	// public List<Room> getRoomList();
	public List<RoomDto> getRoomList(int id);

	public void bookRooms(@Param("userId")int userId,@Param("roomId") int roomId,@Param("date") Timestamp date);

	public void checkOutRoom(@Param("userId") int userId,@Param("roomId") int roomId,@Param("date") Timestamp date);


}
