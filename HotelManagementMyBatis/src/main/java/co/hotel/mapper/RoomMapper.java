package co.hotel.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import co.hotel.model.Room;

@Mapper
public interface RoomMapper {

	public List<Room> getRoomList();
}
