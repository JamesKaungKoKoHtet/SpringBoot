package co.hotel.controller.helper;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
/**
 * 
 * @author james
 * Helper class for external Library and common methods
 */
public class Helper {
	/**
	 * 
	 * @return current time with Timestamp datatype
	 */
	public static Timestamp currentTime() {
		Date currentDate = new Date();
		return new Timestamp(currentDate.getTime());
	}
	
	/**
	 * 
	 * @param selectedRooms : selected room id data from HotelController booking method
	 * @return String of Room id and booking completed text
	 */
	public static String roomListText(List<Integer> selectedRooms) {
		String selectedRoomList = "";
		for (int i : selectedRooms) {
			selectedRoomList += "Room " + i + ", ";
		}
		return selectedRoomList + "を予約しました。";
	}

}
