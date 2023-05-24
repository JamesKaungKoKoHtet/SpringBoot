package co.hotel.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CustomerMapper {
	public int getIdByUserMailAndPassword(@Param("mail") String mail ,@Param("password") String password);
	public String getUserNameById(int id);
}
