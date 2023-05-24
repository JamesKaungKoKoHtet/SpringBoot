package co.hotel.dto;

import lombok.Data;
/**
 * 
 * @author james
 *	RoomDto Class to carry data to Room List view 
 */
@Data
public class RoomDto {
	private int roomId;
	private boolean status;
	private boolean booked;

}
