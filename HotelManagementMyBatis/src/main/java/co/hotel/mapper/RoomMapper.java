package co.hotel.mapper;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import co.hotel.dto.RoomDto;
/**
 * 
 * @author james
 * Mapper class for Booking table 
 */
@Mapper
public interface RoomMapper {

	/**
	 * 
	 * @param id : from RoomService calling LoginService checking if there's a logged in user and return id if exist else 0
	 * @return roomId , status : type boolean ( available or not ) , booked : type boolean ( logged in user booked or not) 
	 */
	public List<RoomDto> getRoomList(int id);
	
	/**
	 * 
	 * @param userId : from RoomService that called LoginService to get current session userId
	 * @param roomId : looped roomId from selectedRoom list from view 
	 * @param date : current time from Helper class
	 */

	public void bookRooms(@Param("userId")int userId,@Param("roomId") int roomId,@Param("date") Timestamp date);
	
	/**
	 * 
	 * @param userId : from RoomService that called LoginService to get current session userId
	 * @param roomId : clicked roomId from roomList view
	 * @param date : current time from Helper class
	 */

	public void checkOutRoom(@Param("userId") int userId,@Param("roomId") int roomId,@Param("date") Timestamp date);


}
